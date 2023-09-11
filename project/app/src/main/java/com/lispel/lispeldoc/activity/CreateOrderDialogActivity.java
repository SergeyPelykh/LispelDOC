
package com.lispel.lispeldoc.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.secondVersion.inteface.ListedFields;
import com.lispel.lispeldoc.secondVersion.model.Client;
import com.lispel.lispeldoc.secondVersion.repositoriy.CartridgeRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ClientRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.FieldRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.StreetRepository;
import com.lispel.lispeldoc.secondVersion.uiServices.FieldSetViews;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateOrderDialogActivity extends AppCompatActivity {

    FieldRepository fieldRepository = new FieldRepository(getApplication());
    ArrayList<String> fieldsFromIntent = new ArrayList<>();
    ArrayList<FieldSetViews> fields = new ArrayList<>();
    HashMap<String, FieldSetViews> fieldsMap = new HashMap<>();

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order_dialog);
        //keyboard not slide up all views
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        Intent intent = getIntent();
        String titleOfNewEntity = "nothing";
        String nameOfClassNewEntity = intent.getStringExtra(MainActivity.NAME_ENTITY);
        if (nameOfClassNewEntity != null) {
            switch (nameOfClassNewEntity) {
                case "client":
                    titleOfNewEntity = "Клиент";
                    break;
                case "order":
                    titleOfNewEntity = "Заказ";
            }
            ;
        }
        RepositoryService repository = getRepositoryNewEntity(getApplication(), nameOfClassNewEntity);
        ArrayList<String> fieldsOfNewEntity = repository.getListOfFields();
        AppCompatButton addEntityInBase = findViewById(R.id.add_entity_in_base_button);
        addEntityInBase.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                MutableLiveData<Long> idNewEntity = new MutableLiveData<Long>();

                ArrayList<String> properties = new ArrayList<>();
                    for (int i = 0; i < repository.getListOfFields().size(); i++) {
                        properties.add(fieldsMap.get(repository.getListOfFields().get(i)).getName().getText().toString());
                }

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        idNewEntity.postValue(repository.insertNewEntityInBase(properties));

                    }
                });
                thread.start();
                idNewEntity.observe(CreateOrderDialogActivity.this, x -> {
                    Toast.makeText(CreateOrderDialogActivity.this, "insert with id: " + x, Toast.LENGTH_SHORT)
                            .show();
                });
            }
        });
        ConstraintLayout mainLayout = findViewById(R.id.addEntityLinearLayout);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fields.isEmpty()){
                    for (FieldSetViews f : fields) {
                        f.removeFocus(CreateOrderDialogActivity.this);
                    }
                }
            }
        });

        TextView title = findViewById(R.id.addEntityTitleTextView);
        title.setText(titleOfNewEntity);

        ArrayList<ListView> baseOptionListViews = new ArrayList<>();
        baseOptionListViews.add(findViewById(R.id.add_entity_field_ListView));
        baseOptionListViews.add(findViewById(R.id.add_entity_field2_ListView));
        baseOptionListViews.add(findViewById(R.id.add_entity_field3_ListView));
        baseOptionListViews.add(findViewById(R.id.add_entity_field4_ListView));
        baseOptionListViews.add(findViewById(R.id.add_entity_field5_ListView));

        ArrayList<ImageButton> addImageButtons = new ArrayList<>();
        addImageButtons.add(findViewById(R.id.add_entity_field_imageButton));
        addImageButtons.add(findViewById(R.id.add_entity_field2_imageButton));
        addImageButtons.add(findViewById(R.id.add_entity_field3_imageButton));
        addImageButtons.add(findViewById(R.id.add_entity_field4_imageButton));
        addImageButtons.add(findViewById(R.id.add_entity_field5_imageButton));

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

        for (TextView t : visionTextViews) {
            t.setVisibility(View.INVISIBLE);
        }
        for (TextView t : inscriptionTextViews) {
            t.setVisibility(View.INVISIBLE);
        }
        for (EditText t : inputEditTexts) {
            t.setVisibility(View.INVISIBLE);
        }
        for (ImageButton i : addImageButtons) {
            i.setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < fieldsOfNewEntity.size(); i++) {
            inputFieldService(CreateOrderDialogActivity.this,
                    visionTextViews.get(i),
                    inscriptionTextViews.get(i),
                    inputEditTexts.get(i),
                    baseOptionListViews.get(i),
                    addImageButtons.get(i),
                    fieldsOfNewEntity.get(i));
        }

//
//        String firstField = intent.getStringExtra(MainActivity.FIELDS.get(0));
//        if (firstField != null) {
//            inputFieldService(CreateOrderDialogActivity.this,
//                    visionTextViews.get(0),
//                    inscriptionTextViews.get(0),
//                    inputEditTexts.get(0),
//                    baseOptionListViews.get(0),
//                    addImageButtons.get(0),
//                    firstField
//            );
//        }
//
//        String secondField = intent.getStringExtra(MainActivity.FIELDS.get(1));
//        if (secondField != null) {
//            inputFieldService(CreateOrderDialogActivity.this,
//                    visionTextViews.get(1),
//                    inscriptionTextViews.get(1),
//                    inputEditTexts.get(1),
//                    baseOptionListViews.get(1),
//                    addImageButtons.get(1),
//                    secondField
//            );
//        }
//        String thirdField = intent.getStringExtra(MainActivity.FIELDS.get(2));
//        if (thirdField != null) {
//            inputFieldService(CreateOrderDialogActivity.this,
//                    visionTextViews.get(2),
//                    inscriptionTextViews.get(2),
//                    inputEditTexts.get(2),
//                    baseOptionListViews.get(2),
//                    addImageButtons.get(2),
//                    thirdField
//            );
//        }
//
//        String fourField = intent.getStringExtra(MainActivity.FIELDS.get(3));
//        if (thirdField != null) {
//            inputFieldService(CreateOrderDialogActivity.this,
//                    visionTextViews.get(3),
//                    inscriptionTextViews.get(3),
//                    inputEditTexts.get(3),
//                    baseOptionListViews.get(3),
//                    addImageButtons.get(3),
//                    fourField
//            );
//        }
//        String fifthField = intent.getStringExtra(MainActivity.FIELDS.get(4));
//        if (thirdField != null) {
//            inputFieldService(CreateOrderDialogActivity.this,
//                    visionTextViews.get(4),
//                    inscriptionTextViews.get(4),
//                    inputEditTexts.get(4),
//                    baseOptionListViews.get(4),
//                    addImageButtons.get(4),
//                    fifthField
//            );
//        }

    }

    @SuppressLint("WrongConstant")
    private void inputFieldService(Context context,
                                   TextView field,
                                   TextView title,
                                   EditText input,
                                   ListView listView,
                                   ImageButton imageButton,
                                   String fieldName) {
        fieldRepository.getFieldByName(fieldName).observe(CreateOrderDialogActivity.this, fieldObject -> {
            if (fieldObject != null) {
                fieldsFromIntent.add(fieldName);
                FieldSetViews fieldSetViews = new FieldSetViews(fieldObject.getInscription(), fieldName, field, input, title, listView, imageButton);
                fields.add(fieldSetViews);
                fieldsMap.put(fieldName, fieldSetViews);
                RepositoryService repositoryService = getRepositoryNewEntity(getApplication(), fieldObject.getDataSource());
                field.setVisibility(fieldObject.getNameTextViewVisibility());
                field.setHint(fieldObject.getHint());
                title.setVisibility(fieldObject.getInscriptionTextViewVisibility());
                title.setText(fieldObject.getInscription());
                input.setInputType(fieldObject.getInputType());
                field.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        for (FieldSetViews f : fields) {
                            if (f != fieldSetViews){
                                f.removeFocus(CreateOrderDialogActivity.this);
                            }
                        }
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                fieldRepository.getAllFields().observe(CreateOrderDialogActivity.this, x -> {
//                                    for (Field f : x) {
//                                        System.out.println(f.getName() + ":" + f.getInscription());
//                                    }
//                                });
                                Intent intent = new Intent(CreateOrderDialogActivity.this, CreateOrderDialogActivity.class);
                                ArrayList<String> fields = getObjectForFields(fieldName).getListOfField();
                                for (int i = 0; i < fields.size(); i++) {
                                    intent.putExtra(MainActivity.FIELDS.get(i), fields.get(i));
                                }
                                intent.putExtra(MainActivity.NAME_ENTITY, "client");
                                startActivity(intent);
                            }
                        });
                        field.setVisibility(View.INVISIBLE);
                        title.setVisibility(View.GONE);
                        input.setVisibility(View.VISIBLE);
                        if (fieldName.equals("address")){
                            String text = field.getText().toString();
                            input.setText(text);
                            input.setSelection(text.length());
                        }
                        showKeyboard(context, input);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateOrderDialogActivity.this, android.R.layout.simple_list_item_1, new ArrayList<>());
                        listView.setAdapter(adapter);
                        if (repositoryService != null) {
                            imageButton.setVisibility(View.VISIBLE);
                            repositoryService.findAllByStringField(input.getText().toString()).observe(CreateOrderDialogActivity.this, x -> {
                                if (x.size() != 0) {
                                    adapter.clear();
                                    adapter.addAll(x);
                                    listView.setVisibility(View.VISIBLE);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            if (!fieldName.equals("address")) {
                                                field.setText(x.get(position));
                                                input.setText("");
                                                hideKeyboard(context, input);
                                                listView.setVisibility(View.GONE);
                                                input.setVisibility(View.INVISIBLE);
                                                imageButton.setVisibility(View.INVISIBLE);
                                                title.setVisibility(View.VISIBLE);
                                                field.setVisibility(View.VISIBLE);
                                            } else {
                                                input.setText(x.get(position) + " ");
                                                listView.setVisibility(View.GONE);
                                                input.setSelection(input.getText().length());
                                            }
                                        }
                                    });
                                }
                            });
                        }else if(field.getText() != ""){
                            input.setText(field.getText());
                            input.setSelection(input.getText().length());
                        }
                        input.setOnKeyListener(new View.OnKeyListener() {
                            @Override
                            public boolean onKey(View v, int keyCode, KeyEvent event) {
                                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                                        keyCode == KeyEvent.KEYCODE_ENTER) {
                                    if (repositoryService == null || fieldName.equals("address")) {
                                        field.setText(input.getText());
                                    }
                                    imageButton.setVisibility(View.INVISIBLE);
                                    input.setVisibility(View.INVISIBLE);
                                    input.setText("");
                                    listView.setVisibility(View.GONE);
                                    field.setVisibility(View.VISIBLE);
                                    title.setVisibility(View.VISIBLE);
                                    hideKeyboard(context, input);
                                    return true;
                                }
                                return false;
                            }
                        });
                        input.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (repositoryService != null) {
                                    title.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);
                                    repositoryService.findAllByStringField(s.toString()).observe(CreateOrderDialogActivity.this, x -> {
                                        if (x.size() != 0) {
                                            adapter.clear();
                                            adapter.addAll(x);
                                        } else {
                                            listView.setVisibility(View.GONE);
                                            adapter.clear();
                                        }
                                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                if (!fieldName.equals("address")) {
                                                    field.setText(x.get(position));
                                                    input.setText("");
                                                    hideKeyboard(context, input);
                                                    listView.setVisibility(View.GONE);
                                                    input.setVisibility(View.INVISIBLE);
                                                    imageButton.setVisibility(View.INVISIBLE);
                                                    title.setVisibility(View.VISIBLE);
                                                    field.setVisibility(View.VISIBLE);
                                                } else {
                                                    input.setText(x.get(position) + " ");
                                                    listView.setVisibility(View.GONE);
                                                    input.setSelection(input.getText().length());
                                                }
                                            }
                                        });
                                    });
                                }
                            }
                            @Override
                            public void afterTextChanged(Editable s) {
                            }
                        });
                    }
                });
            } else {
                field.setVisibility(View.VISIBLE);
                field.setText("объект поля не найден в базе");
            }
        });
    }

    private RepositoryService getRepositoryNewEntity(Application application, String dataSource) {
        switch (dataSource) {
            case "cartridge":
                return new CartridgeRepository(application);
            case "client":
                return new ClientRepository(application);
            case "none":
                return null;
            default:
                return new StreetRepository(application, dataSource);
        }

    }

    private ListedFields getObjectForFields(String str) {
        switch (str) {
            case "client":
                return new Client();
            default:
                return null;
        }
    }

    private void showKeyboard(Context context, EditText input) {
        input.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
    }

    private void hideKeyboard(Context context, EditText input) {
        input.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

//    private class FieldSetViews{
//        String inscriptionText;
//        String fieldName;
//        TextView name;
//        EditText editText;
//        TextView inscription;
//        ListView listView;
//        ImageButton imageButton;
//
//        public String getFieldName() {
//            return fieldName;
//        }
//
//        public FieldSetViews(String inscriptionText, String fieldName, TextView name, EditText editText, TextView inscription, ListView listView, ImageButton imageButton) {
//            this.inscriptionText = inscriptionText;
//            this.fieldName = fieldName;
//            this.name = name;
//            this.editText = editText;
//            this.inscription = inscription;
//            this.listView = listView;
//            this.imageButton = imageButton;
//        }
//        void removeFocus(){
//            if (name.getVisibility() == View.INVISIBLE) {
//                name.setVisibility(View.VISIBLE);
//                name.setText(editText.getText());
//                editText.setVisibility(View.INVISIBLE);
//                inscription.setVisibility(View.VISIBLE);
//                listView.setVisibility(View.GONE);
//                imageButton.setVisibility(View.INVISIBLE);
//                hideKeyboard(CreateOrderDialogActivity.this, editText);
//            }
//        }
//    }
}