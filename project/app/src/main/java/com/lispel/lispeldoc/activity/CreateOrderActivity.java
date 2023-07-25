package com.lispel.lispeldoc.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.secondVersion.model.Cartridge;
import com.lispel.lispeldoc.secondVersion.model.Client;
import com.lispel.lispeldoc.secondVersion.repositoriy.CartridgeRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ClientRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ComponentRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.StickerRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.TonerRepository;

import java.util.ArrayList;

public class CreateOrderActivity extends AppCompatActivity {

    static final String FIELD_VALUE = "valueField";
    static final String TYPE_ENTITY = "valueField";
    static final String CLIENT_ID = "clientId";
    static final String CARTRIDGE_ID = "cartridgeId";
    TextView temp;

    MutableLiveData<Long> idClient = new MutableLiveData<>();
    MutableLiveData<Long> idCartridge = new MutableLiveData<>();

    TextView optionFirstTextView;
    TextView optionSecondTextView;
    TextView optionThirdTextView;

    EditText optionFirstEditText;
    EditText optionSecondEditText;
    EditText optionThirdEditText;

    AppCompatImageButton optionFirstButton;
    AppCompatImageButton optionSecondButton;
    AppCompatImageButton optionThirdButton;

    ListView optionFirstListView;
    ListView optionSecondListView;
    ListView optionThirdListView;


        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent intent = result.getData();
                            String valueField = intent.getStringExtra(FIELD_VALUE);
                            temp.setText(valueField);
                            if (intent.getStringExtra(TYPE_ENTITY).equals("1")){
                                idClient.postValue(intent.getLongExtra(CLIENT_ID, 0));
                            }
                        } else {
                            temp.setText("");
                        }
                    }
                });





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );




        StickerRepository stickerRepository = new StickerRepository(getApplication());
        ClientRepository clientRepository = new ClientRepository(getApplication());
        TonerRepository tonerRepository = new TonerRepository(getApplication());
        ComponentRepository componentRepository = new ComponentRepository(getApplication());
        CartridgeRepository cartridgeRepository = new CartridgeRepository(getApplication());

        optionFirstTextView = findViewById(R.id.optionFirstTextView);
        optionSecondTextView = findViewById(R.id.optionSecondTextView);
        optionThirdTextView = findViewById(R.id.optionThirdTextView);

        optionFirstEditText = findViewById(R.id.optionFirstEditText);
        optionSecondEditText = findViewById(R.id.optionSecondEditText);
        optionThirdEditText = findViewById(R.id.optionThirdEditText);

        optionFirstButton = findViewById(R.id.optionFirstButton);
        optionSecondButton = findViewById(R.id.optionSecondButton);
        optionThirdButton = findViewById(R.id.optionThirdButton);

        optionFirstListView = findViewById(R.id.optionFirstListView);
        optionSecondListView = findViewById(R.id.optionSecondListView);
        optionThirdListView = findViewById(R.id.optionThirdListView);

        idClient.observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                //optionSecondTextView.setText(aLong.toString());
                if (aLong == -1) {
                    optionSecondTextView.setText("");
                } else {
                    cartridgeRepository.getCartridgeByOwner(aLong).observe(CreateOrderActivity.this, x -> {
                        if (x != null) {
                            optionSecondTextView.setText(x.getStickers() + " (" + x.getModel() + ")");
                            optionFirstButton.setVisibility(View.INVISIBLE);
                        } else {
                            optionSecondTextView.setText("У клиента еще нет картриджа");
                        }
                    });
                }
            }
        });

        inputValueInField(CreateOrderActivity.this, optionFirstEditText, optionFirstTextView, optionFirstButton, optionFirstListView, clientRepository);
        inputValueInField2(CreateOrderActivity.this, optionSecondEditText, optionSecondTextView, optionSecondButton, optionSecondListView, cartridgeRepository);
    }

    private void inputValueInField(Context context,
                                   EditText editText,
                                   TextView textView,
                                   AppCompatImageButton imageButton,
                                   ListView listView,
                                   RepositoryService repository)
    {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKeyboard(context, editText);
                textView.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.VISIBLE);
                editText.setText(textView.getText());
                editText.setInputType(TextView.AUTOFILL_TYPE_TEXT);
                editText.setHint("");
                idClient.postValue(-1L);
                showKeyboard(context, editText);
                if (editText.getText().equals("")) {
                    editText.setSelection(0);
                } else {
                    editText.setSelection(editText.getText().length());
                }
                imageButton.setVisibility(View.VISIBLE);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateOrderActivity.this, android.R.layout.simple_list_item_1, new ArrayList<>());
                listView.setAdapter(adapter);
                if (charSequence.length() != 0) {
                    listView.setVisibility(View.VISIBLE);
                    repository.findAllByStringField(charSequence.toString()).observe(CreateOrderActivity.this, x -> {
                        if (x.size() != 0) {
                            imageButton.setVisibility(View.VISIBLE);
                            imageButton.setBackgroundResource(R.drawable.ic_outline_cancel_24);
                            imageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    editText.setText("");
                                }
                            });
                            adapter.clear();
                            adapter.addAll(x);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    repository.findByStringField(x.get(i)).observe(CreateOrderActivity.this, y -> {
                                        idClient.postValue(y.getId());
                                        textView.setText(x.get(i));
                                        editText.setVisibility(View.INVISIBLE);
                                        listView.setVisibility(View.GONE);
                                        textView.setVisibility(View.VISIBLE);
                                        imageButton.setVisibility(View.INVISIBLE);
                                        hideKeyboard(context, editText);
                                    });

                                }
                            });
                        }else{
                            imageButton.setVisibility(View.VISIBLE);
                            imageButton.setBackgroundResource(R.drawable.ic_baseline_add_24);
                            listView.setVisibility(View.GONE);
                            idClient.postValue(-1l);
                            imageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(CreateOrderActivity.this, InsertEntityInBAseActivity.class);
                                    intent.putExtra(MainActivity.MODE, "createNew");
                                    intent.putStringArrayListExtra("listEntityFields", (new Client().getNameAllFields()));
                                    intent.putExtra("titleActivity", "Добавление нового клиента");
                                    intent.putExtra("repository", "client");
                                    imageButton.setVisibility(View.INVISIBLE);
                                    editText.setVisibility(View.INVISIBLE);
                                    textView.setVisibility(View.VISIBLE);
                                    temp = textView;
                                    startForResult.launch(intent);
                                }
                            });

                        }
                    });

                }else {
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(CreateOrderActivity.this, InsertEntityInBAseActivity.class);
                            intent.putExtra(MainActivity.MODE, "createNew");
                            intent.putStringArrayListExtra("listEntityFields", (new Client().getNameAllFields()));
                            intent.putExtra("titleActivity", "Добавление нового клиента");
                            intent.putExtra("repository", "client");
                            imageButton.setVisibility(View.INVISIBLE);
                            editText.setVisibility(View.INVISIBLE);
                            textView.setVisibility(View.VISIBLE);
                            temp = textView;
                            startForResult.launch(intent);
                        }
                    });
                    imageButton.setBackgroundResource(R.drawable.ic_baseline_add_24);
                    imageButton.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    adapter.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void inputValueInField2(Context context,
                                   EditText editText,
                                   TextView textView,
                                   AppCompatImageButton imageButton,
                                   ListView listView,
                                   RepositoryService repository)
    {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKeyboard(context, editText);
                textView.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.VISIBLE);
                editText.setText(textView.getText());
                editText.setInputType(TextView.AUTOFILL_TYPE_TEXT);
                editText.setHint("");
                idClient.postValue(-1L);
                showKeyboard(context, editText);
                if (editText.getText().equals("")) {
                    editText.setSelection(0);
                } else {
                    editText.setSelection(editText.getText().length());
                }
                imageButton.setVisibility(View.VISIBLE);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateOrderActivity.this, android.R.layout.simple_list_item_1, new ArrayList<>());
                listView.setAdapter(adapter);
                if (charSequence.length() != 0) {
                    listView.setVisibility(View.VISIBLE);
                    repository.findAllByStringField(charSequence.toString()).observe(CreateOrderActivity.this, x -> {
                        if (x.size() != 0) {
                            imageButton.setVisibility(View.VISIBLE);
                            imageButton.setBackgroundResource(R.drawable.ic_outline_cancel_24);
                            imageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    editText.setText("");
                                }
                            });
                            adapter.clear();
                            adapter.addAll(x);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    repository.findByStringField(x.get(i)).observe(CreateOrderActivity.this, y -> {
                                        //idClient.postValue(y.getId());
                                        textView.setText(x.get(i));
                                        editText.setVisibility(View.INVISIBLE);
                                        listView.setVisibility(View.GONE);
                                        textView.setVisibility(View.VISIBLE);
                                        imageButton.setVisibility(View.INVISIBLE);
                                        hideKeyboard(context, editText);
                                    });

                                }
                            });
                        }else{
                            imageButton.setVisibility(View.VISIBLE);
                            imageButton.setBackgroundResource(R.drawable.ic_baseline_add_24);
                            listView.setVisibility(View.GONE);
                            idClient.postValue(-1l);
                            imageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(CreateOrderActivity.this, InsertEntityInBAseActivity.class);
                                    intent.putExtra(MainActivity.MODE, "createNew");
                                    intent.putStringArrayListExtra("listEntityFields", (new Cartridge().getNameAllFields()));
                                    intent.putExtra("titleActivity", "Добавление нового картриджа");
                                    intent.putExtra("repository", "cartridge");
                                    imageButton.setVisibility(View.INVISIBLE);
                                    editText.setVisibility(View.INVISIBLE);
                                    textView.setVisibility(View.VISIBLE);
                                    temp = textView;
                                    startForResult.launch(intent);
                                }
                            });

                        }
                    });

                }else {
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(CreateOrderActivity.this, InsertEntityInBAseActivity.class);
                            intent.putExtra(MainActivity.MODE, "createNew");
                            intent.putStringArrayListExtra("listEntityFields", (new Cartridge().getNameAllFields()));
                            intent.putExtra("titleActivity", "Добавление нового картриджа");
                            intent.putExtra("repository", "cartridge");
                            imageButton.setVisibility(View.INVISIBLE);
                            editText.setVisibility(View.INVISIBLE);
                            textView.setVisibility(View.VISIBLE);
                            temp = textView;
                            startForResult.launch(intent);
                        }
                    });
                    imageButton.setBackgroundResource(R.drawable.ic_baseline_add_24);
                    imageButton.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    adapter.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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