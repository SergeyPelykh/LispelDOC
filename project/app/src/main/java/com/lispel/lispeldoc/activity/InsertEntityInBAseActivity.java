package com.lispel.lispeldoc.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.secondVersion.model.Client;
import com.lispel.lispeldoc.secondVersion.repositoriy.CartridgeRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ClientRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ComponentRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.StickerRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.TonerRepository;

import java.util.ArrayList;

public class InsertEntityInBAseActivity extends AppCompatActivity {

    Context context = InsertEntityInBAseActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_entity_in_base);

        ArrayList<String> extra = getIntent().getStringArrayListExtra("listEntityFields");

        TextView title = findViewById(R.id.addEntityTitleTextView);
        title.setText(getIntent().getStringExtra("titleActivity"));

        ArrayList<TextView> visionTextViews = new ArrayList<>();
        visionTextViews.add(findViewById(R.id.add_entity_field_textView));
        visionTextViews.add(findViewById(R.id.add_entity_field2_textView));
        visionTextViews.add(findViewById(R.id.add_entity_field3_textView));
        visionTextViews.add(findViewById(R.id.add_entity_field4_textView));
        visionTextViews.add(findViewById(R.id.add_entity_field5_textView));

        ArrayList<TextView> inscriptionTextViews = new ArrayList<>();
        inscriptionTextViews.add(findViewById(R.id.inscription_add_entity_field_TextView));
        inscriptionTextViews.add(findViewById(R.id.inscription_add_entity_field2_TextView));
        inscriptionTextViews.add(findViewById(R.id.inscription_add_entity_field3_TextView));
        inscriptionTextViews.add(findViewById(R.id.inscription_add_entity_field4_TextView));
        inscriptionTextViews.add(findViewById(R.id.inscription_add_entity_field5_TextView));

        ArrayList<EditText> inputEditTexts = new ArrayList<>();
        inputEditTexts.add(findViewById(R.id.add_entity_field_editText));
        inputEditTexts.add(findViewById(R.id.add_entity_field2_editText));
        inputEditTexts.add(findViewById(R.id.add_entity_field3_editText));
        inputEditTexts.add(findViewById(R.id.add_entity_field4_editText));
        inputEditTexts.add(findViewById(R.id.add_entity_field5_editText));

        for (int i = 0; i < extra.size()/2; i++){
            addInputField(context,
                    visionTextViews.get(i),
                    inputEditTexts.get(i),
                    inscriptionTextViews.get(i),
                    Integer.parseInt(extra.get(i + i + 1)),
                    extra.get(i + i));
        }

        AppCompatButton saveButton = findViewById(R.id.add_entity_in_base_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reviseInputField(visionTextViews, inscriptionTextViews, extra.size()/2)) {
                    ArrayList <String> titleFields = new ArrayList<>();
                    ArrayList <String> valueFields = new ArrayList<>();
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;
                    for (int i = 0; i < extra.size()/2; i++) {
                        temp = inscriptionTextViews.get(i).getText().toString();
                        titleFields.add(temp);
                        stringBuilder.append(temp);
                        temp = visionTextViews.get(i).getText().toString();
                        valueFields.add(temp);
                        stringBuilder.append(": " + temp + "\n");
                    }

                    new AlertDialog.Builder(InsertEntityInBAseActivity.this).setTitle("Добавить Новую запись в базу?")
                            .setMessage(stringBuilder.toString())
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    RepositoryService repository = null;
                                    String result = null;
                                    String typeEntity = null;
                                    switch (getIntent().getStringExtra("repository")){
                                        case "client":
                                            repository = new ClientRepository(getApplication());
                                            result = CreateOrderActivity.CLIENT_ID;
                                            typeEntity = "1";
                                            break;
                                        case "cartridge":
                                            repository = new CartridgeRepository(getApplication());
                                            result = CreateOrderActivity.CARTRIDGE_ID;
                                            typeEntity = "2";
                                            break;
                                    }
                                    MutableLiveData<Long> liveData = new MutableLiveData<Long>();
                                    RepositoryService finalRepository = repository;
                                    Thread thread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            System.out.println(valueFields);
                                            Long id = finalRepository.insertNewEntityInBase(valueFields);
                                            System.out.println(id + "- id new cartridge");
                                            liveData.postValue(id);
                                            //finalRepository.insertNewEntityInBase(valueFields);
                                        }
                                    });
                                    thread.start();
                                    String finalResult = result;
                                    String finalTypeEntity = typeEntity;
                                    liveData.observe(InsertEntityInBAseActivity.this, new Observer<Long>() {
                                        @Override
                                        public void onChanged(Long aLong) {
                                            Intent intent = new Intent();
                                            intent.putExtra(CreateOrderActivity.FIELD_VALUE, visionTextViews.get(0).getText().toString());
                                            intent.putExtra(CreateOrderActivity.TYPE_ENTITY, finalTypeEntity);
                                            intent.putExtra(finalResult, liveData.getValue());
                                            setResult(RESULT_OK, intent);
                                            onBackPressed();
                                        }
                                    });

                                }
                            })
                            .setNegativeButton("отмена", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).show();
                }
            }
        });
        AppCompatButton cancelButton = findViewById(R.id.cancel_add_entity_in_base_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
    private void insertEntityInBase(){

    }

    private void addInputField(Context context, TextView visionField, EditText inputField, TextView titleField, int inputType, String nameField){
        visionField.setVisibility(View.VISIBLE);
        titleField.setVisibility(View.VISIBLE);
        titleField.setText(nameField);
        visionField.setHint(nameField);
        inputField.setVisibility(View.INVISIBLE);
        visionField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visionField.setVisibility(View.INVISIBLE);
                titleField.setVisibility(View.INVISIBLE);
                inputField.setVisibility(View.VISIBLE);
                inputField.setInputType(inputType);
                showKeyboard(context,inputField);
                inputField.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                            visionField.setText(inputField.getText());
                            visionField.setVisibility(View.VISIBLE);
                            titleField.setVisibility(View.VISIBLE);
                            inputField.setVisibility(View.INVISIBLE);
                            hideKeyboard(context, inputField);
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
    }

    private boolean reviseInputField(ArrayList<TextView> visionTextViews, ArrayList<TextView> inscriptionTextViews, int fields){
        for (int  i = 0; i < fields; i++){
            if (visionTextViews.get(i).getText().equals("")){
                new AlertDialog.Builder(InsertEntityInBAseActivity.this)
                        .setTitle("заполните поле:")
                        .setMessage(inscriptionTextViews.get(i).getText())
                        .show();
                return false;
            }
        }
        return true;
    }


    private void showKeyboard(Context context, EditText input){
        input.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
    }

    private void hideKeyboard(Context context, EditText input){
        input.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }
}