package com.lispel.lispeldoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.utility.RepositoryService;
import com.lispel.lispeldoc.secondVersion.inteface.GetListOfFields;
import com.lispel.lispeldoc.secondVersion.model.Client;
import com.lispel.lispeldoc.secondVersion.repositoriy.CartridgeRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ClientRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ComponentRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.StickerRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.TonerRepository;

import java.util.ArrayList;

public class AddOrderActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    ConstraintLayout constraintLayout2;
    EditText optionFirstEditText;
    EditText optionFirstEditText2;
    TextView inscriptionFirstTextView;
    TextView optionFirstTextView;
    TextView optionFirstTextView2;
    TextView secondTitleTextView;
    EditText optionSecondEditText;
    TextView inscriptionSecondTextView;
    TextView optionSecondTextView;
    AppCompatImageButton optionFirstButton;
    AppCompatImageButton optionFirstButton2;
    TextView mainTitleTextView;
    ListView optionFirstListView;
    ListView optionFirstListView2;
    ListView optionSecondListView;
    AppCompatImageButton optionSecondButton;
    Context context = AddOrderActivity.this;
    TextWatcher textWatcherOptionFirstEditText;
    TextWatcher textWatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        //keyboard not slide up all views
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        StickerRepository stickerRepository = new StickerRepository(getApplication());
        ClientRepository clientRepository = new ClientRepository(getApplication());
        TonerRepository tonerRepository = new TonerRepository(getApplication());
        ComponentRepository componentRepository = new ComponentRepository(getApplication());
        CartridgeRepository cartridgeRepository = new CartridgeRepository(getApplication());

        //optionFirstEditText = findViewById(R.id.option_first_editText);
        optionFirstEditText2 = findViewById(R.id.option_first_editText2);
//        inscriptionFirstTextView = findViewById(R.id.inscription_first_TextView);
//        inscriptionSecondTextView = findViewById(R.id.inscription_second_TextView);
        //optionFirstListView = findViewById(R.id.option_first_listView);
        optionFirstListView2 = findViewById(R.id.option_first_listView2);
        //optionFirstButton = findViewById(R.id.option_first_button);
        optionFirstButton2 = findViewById(R.id.option_first_button2);
//        optionFirstTextView = findViewById(R.id.option_first_textView);
//        mainTitleTextView = findViewById(R.id.main_title_textView);
        secondTitleTextView = findViewById(R.id.second_title_textView2);
//        constraintLayout = findViewById(R.id.linearLayout2);
        constraintLayout2 = findViewById(R.id.linearLayout4);

//        optionSecondButton = findViewById(R.id.option_second_button);
//        optionSecondTextView = findViewById(R.id.option_second_textView);

        toFillOptionFromBase(optionSecondTextView,
                inscriptionSecondTextView,
                "Картридж",
                "HP85a",
                clientRepository);

        toFillOptionFromBase(
                optionFirstTextView,
                inscriptionFirstTextView,
                "Клиент",
                "Водонасосная 1",
                clientRepository);




//        optionFirstTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                optionFirstTextView.setVisibility(View.INVISIBLE);
//                optionFirstEditText.setVisibility(View.VISIBLE);
//                optionFirstEditText.requestFocus();
//                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(optionFirstEditText, InputMethodManager.SHOW_FORCED);
//            }
//        });
//        textWatcherOptionFirstEditText = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(AddOrderActivity.this, android.R.layout.simple_list_item_1, new ArrayList<>());
//                example.setAdapter(adapter);
//                if (charSequence.length() != 0) {
//                    addClientButton.setVisibility(View.GONE);
//                    example.setVisibility(View.VISIBLE);
//                    inscriptionFirstTextView.setVisibility(View.GONE);
//                    clientRepository.getAllClientByName("%" + charSequence.toString() + "%").observe(AddOrderActivity.this, x -> {
//                        if (x.size() != 0) {
//                            ArrayList<String> arr = new ArrayList<>();
//                            for (Client client : x) {
//                                arr.add(client.getName());
//                            }
//                            adapter.clear();
//                            adapter.addAll(arr);
//                            example.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    optionFirstEditText.setVisibility(View.INVISIBLE);
//                                    optionFirstTextView.setVisibility(View.VISIBLE);
//                                    inscriptionFirstTextView.setVisibility(View.VISIBLE);
//                                    optionFirstTextView.setText(x.get(i).getName());
//                                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                                    imm.hideSoftInputFromWindow(optionFirstEditText.getWindowToken(), 0);
//                                    example.setVisibility(View.GONE);
//                                }
//                            });
//                        }else{
//                            addClientButton.setVisibility(View.VISIBLE);
//                            inscriptionFirstTextView.setVisibility(View.VISIBLE);
//                            example.setVisibility(View.GONE);
//                        }
//                    });
//
//                }else {
//                    addClientButton.setVisibility(View.GONE);
//                    inscriptionFirstTextView.setVisibility(View.VISIBLE);
//                    example.setVisibility(View.GONE);
//                    adapter.clear();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        };
//
//        optionFirstEditText.addTextChangedListener(textWatcherOptionFirstEditText);
//        optionFirstEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                example.setVisibility(View.VISIBLE);
//                inscriptionFirstTextView.setVisibility(View.GONE);
//            }
//        });
//        addClientButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                optionFirstEditText.setVisibility(View.INVISIBLE);
//                optionFirstTextView.setVisibility(View.VISIBLE);
//                optionFirstTextView.setText(optionFirstEditText.getText());
//                mainTitleTextView.setText("Добавление нового клиента");
//                addClientButton.setVisibility(View.GONE);
//                example.setVisibility(View.GONE);
//                inscriptionFirstTextView.setVisibility(View.VISIBLE);
//                inscriptionFirstTextView.setText("имя клиента");
//                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(optionFirstEditText.getWindowToken(), 0);
//                optionFirstEditText.removeTextChangedListener(textWatcherOptionFirstEditText);
//                optionFirstTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        optionFirstTextView.setVisibility(View.INVISIBLE);
//                        optionFirstEditText.setVisibility(View.VISIBLE);
//                        addClientButton.setVisibility(View.VISIBLE);
//                        optionFirstEditText.requestFocus();
//                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.showSoftInput(optionFirstEditText, InputMethodManager.SHOW_IMPLICIT);
//                        textWatcherOptionFirstEditText = new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                            }
//
//                            @Override
//                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                                addClientButton.setVisibility(View.VISIBLE);
//                                addClientButton.setBackgroundResource(R.drawable.ic_baseline_check_24);
//                                inscriptionFirstTextView.setVisibility(View.VISIBLE);
//                            }
//
//                            @Override
//                            public void afterTextChanged(Editable editable) {
//
//                            }
//                        };
//                        optionFirstEditText.addTextChangedListener(textWatcherOptionFirstEditText);
//
//                    }
//                });
//
//            }
//        });
//    }
    }
    private void toFillOptionFromBase(
            TextView valueVision,
            TextView valueTitle,
            String valueNameTitle,
            String valueNameHint,
            RepositoryService repository){
        toFillOptionFromBaseHelper(context,
                constraintLayout,
                constraintLayout2,
                valueVision,
                optionFirstEditText2,
                valueTitle,
                secondTitleTextView,
                optionFirstButton2,
                optionFirstListView2,
                valueNameTitle,
                valueNameHint,
                repository);
    }

    private void toFillOptionFromBaseHelper(Context context,
                                            ConstraintLayout layoutMain,
                                            ConstraintLayout layoutSecond,
                                            TextView valueVision,
                                            EditText valueEdit,
                                            TextView valueTitle,
                                            TextView titleSecondLayout,
                                            AppCompatImageButton controlImage,
                                            ListView dataFromBase,
                                            String valueNameTitle,
                                            String valueNameHint,
                                            RepositoryService repository){
        layoutMain.setVisibility(View.VISIBLE);
        layoutSecond.setVisibility(View.INVISIBLE);
        valueVision.setVisibility(View.VISIBLE);
        valueTitle.setVisibility(View.VISIBLE);
        valueVision.setHint(valueNameHint);
        valueTitle.setText(valueNameTitle);

        valueVision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlImage.setVisibility(View.VISIBLE);
                controlImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insertOptionInOrder(layoutMain, layoutSecond,valueEdit,controlImage,titleSecondLayout, repository);
                    }
//                        ConstraintLayout addEntityLinearLayout = findViewById(R.id.addEntityLinearLayout);
//                        addEntityLinearLayout.setVisibility(View.VISIBLE);
//                        layoutMain.setVisibility(View.INVISIBLE);
//                        layoutSecond.setVisibility(View.INVISIBLE);
//                        hideKeyboard(context, valueEdit);
//                        TextView visionField = findViewById(R.id.add_entity_field_textView);
//                        TextView titleField = findViewById(R.id.inscription_add_entity_field_TextView);
//                        EditText inputField = findViewById(R.id.add_entity_field_editText);
//                        addInputField(context,visionField, inputField, titleField, InputType.TYPE_CLASS_TEXT);
//                        TextView visionField2 = findViewById(R.id.add_entity_field2_textView);
//                        TextView titleField2 = findViewById(R.id.inscription_add_entity_field2_TextView);
//                        EditText inputField2 = findViewById(R.id.add_entity_field2_editText);
//                        addInputField(context,visionField2, inputField2, titleField2, InputType.TYPE_CLASS_TEXT);
//                        TextView visionField3 = findViewById(R.id.add_entity_field3_textView);
//                        TextView titleField3 = findViewById(R.id.inscription_add_entity_field3_TextView);
//                        EditText inputField3 = findViewById(R.id.add_entity_field3_editText);
//                        addInputField(context,visionField3, inputField3, titleField3, InputType.TYPE_CLASS_TEXT);
//                        TextView visionField4 = findViewById(R.id.add_entity_field4_textView);
//                        TextView titleField4 = findViewById(R.id.inscription_add_entity_field4_TextView);
//                        EditText inputField4 = findViewById(R.id.add_entity_field4_editText);
//                        addInputField(context,visionField4, inputField4, titleField4, InputType.TYPE_CLASS_PHONE);
//                        TextView visionField5 = findViewById(R.id.add_entity_field5_textView);
//                        TextView titleField5 = findViewById(R.id.inscription_add_entity_field5_TextView);
//                        EditText inputField5 = findViewById(R.id.add_entity_field5_editText);
//                        visionField5.setText("физическое лицо");
//                        addInputField(context,visionField5, inputField5, titleField5, InputType.TYPE_CLASS_PHONE);
//
//                        AppCompatButton cancelButton = findViewById(R.id.cancel_add_entity_in_base_button);
//                        cancelButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                addEntityLinearLayout.setVisibility(View.INVISIBLE);
//                                layoutMain.setVisibility(View.VISIBLE);
//                                layoutSecond.setVisibility(View.VISIBLE);
//                                controlImage.setVisibility(View.INVISIBLE);
//                                valueEdit.setVisibility(View.INVISIBLE);
//                                //visionField.setVisibility(View.INVISIBLE);
//                                //titleField.setVisibility(View.INVISIBLE);
//                                titleSecondLayout.setVisibility(View.INVISIBLE);
//                            }
//                        });
//
//                        AppCompatButton button = findViewById(R.id.add_entity_in_base_button);
//                        button.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                if (reviseInputField(visionField.getText().toString(), titleField.getText().toString()) &&
//                                        reviseInputField(visionField2.getText().toString(), titleField2.getText().toString()) &&
//                                        reviseInputField(visionField3.getText().toString(), titleField3.getText().toString()) &&
//                                        reviseInputField(visionField4.getText().toString(), titleField4.getText().toString()) &&
//                                        reviseInputField(visionField5.getText().toString(), titleField5.getText().toString())) {
//                                    Client client = new Client();
//                                    client.setName(visionField.getText().toString());
//                                    client.setFullName(visionField2.getText().toString());
//                                    client.setAddress(visionField3.getText().toString());
//                                    client.setPhone(visionField4.getText().toString());
//                                    client.setType(visionField5.getText().toString());
//                                    repository.insert(client);
//                                }
//                            }
//                        });
//                    }
                });


                layoutMain.setVisibility(View.INVISIBLE);
                layoutSecond.setVisibility(View.VISIBLE);
                valueEdit.setVisibility(View.VISIBLE);
                titleSecondLayout.setText(valueNameTitle);
                valueEdit.setText(valueVision.getText());
                if (valueEdit.getText().equals("")) {
                    valueEdit.setSelection(0);
                } else {
                    valueEdit.setSelection(valueEdit.getText().length());
                }

                constraintLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        constraintLayout.setVisibility(View.VISIBLE);
                        constraintLayout2.setVisibility(View.INVISIBLE);
                        hideKeyboard(context, valueEdit);
                    }
                });

                valueEdit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddOrderActivity.this, android.R.layout.simple_list_item_1, new ArrayList<>());
                        dataFromBase.setAdapter(adapter);
                        if (charSequence.length() != 0) {
                            controlImage.setVisibility(View.GONE);
                            dataFromBase.setVisibility(View.VISIBLE);
                            repository.findAllByStringField(charSequence.toString()).observe(AddOrderActivity.this, x -> {
                                if (x.size() != 0) {
                                    adapter.clear();
                                    adapter.addAll(x);
                                    dataFromBase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            layoutMain.setVisibility(View.VISIBLE);
                                            layoutSecond.setVisibility(View.INVISIBLE);
                                            valueVision.setText(x.get(i));
                                            hideKeyboard(context, valueEdit);
                                        }
                                    });
                                }else{
                                    controlImage.setVisibility(View.VISIBLE);
                                    valueTitle.setVisibility(View.VISIBLE);
                                    dataFromBase.setVisibility(View.INVISIBLE);
                                }
                            });

                        }else {
                            controlImage.setVisibility(View.VISIBLE);
                            adapter.clear();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                showKeyboard(context, valueEdit);
            }
        });
        valueEdit.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             dataFromBase.setVisibility(View.VISIBLE);
                                             valueTitle.setVisibility(View.GONE);
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

    private boolean reviseInputField(ArrayList<TextView> visionTextViews, ArrayList<TextView> inscriptionTextViews){
        for (int  i = 0; i < visionTextViews.size(); i++){
            if (visionTextViews.get(i).getText().equals("")){
                Toast.makeText(context, "заполните поле: \" " + inscriptionTextViews.get(i).getText() + "\"", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void addInputField(Context context, TextView visionField, EditText inputField, TextView titleField, int inputType){
        visionField.setVisibility(View.VISIBLE);
        titleField.setVisibility(View.VISIBLE);
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

    private void insertOptionInOrder(ConstraintLayout layoutMain,
                                     ConstraintLayout layoutSecond,
                                     EditText valueEdit,
                                     AppCompatImageButton controlImage,
                                     TextView titleSecondLayout,
                                     RepositoryService repository){

        ConstraintLayout addEntityLinearLayout = findViewById(R.id.addEntityLinearLayout);
        addEntityLinearLayout.setVisibility(View.VISIBLE);
        layoutMain.setVisibility(View.INVISIBLE);
        layoutSecond.setVisibility(View.INVISIBLE);
        hideKeyboard(context, valueEdit);
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
        for (int i = 0; i < visionTextViews.size(); i++){
            addInputField(context, visionTextViews.get(i), inputEditTexts.get(i), inscriptionTextViews.get(i),InputType.TYPE_CLASS_TEXT);
        }
//
//
//        TextView visionField = findViewById(R.id.add_entity_field_textView);
//        TextView titleField = findViewById(R.id.inscription_add_entity_field_TextView);
//        EditText inputField = findViewById(R.id.add_entity_field_editText);
//        addInputField(context,visionField, inputField, titleField, InputType.TYPE_CLASS_TEXT);
//        TextView visionField2 = findViewById(R.id.add_entity_field2_textView);
//        TextView titleField2 = findViewById(R.id.inscription_add_entity_field2_TextView);
//        EditText inputField2 = findViewById(R.id.add_entity_field2_editText);
//        addInputField(context,visionField2, inputField2, titleField2, InputType.TYPE_CLASS_TEXT);
//        TextView visionField3 = findViewById(R.id.add_entity_field3_textView);
//        TextView titleField3 = findViewById(R.id.inscription_add_entity_field3_TextView);
//        EditText inputField3 = findViewById(R.id.add_entity_field3_editText);
//        addInputField(context,visionField3, inputField3, titleField3, InputType.TYPE_CLASS_TEXT);
//        TextView visionField4 = findViewById(R.id.add_entity_field4_textView);
//        TextView titleField4 = findViewById(R.id.inscription_add_entity_field4_TextView);
//        EditText inputField4 = findViewById(R.id.add_entity_field4_editText);
//        addInputField(context,visionField4, inputField4, titleField4, InputType.TYPE_CLASS_PHONE);
//        TextView visionField5 = findViewById(R.id.add_entity_field5_textView);
//        TextView titleField5 = findViewById(R.id.inscription_add_entity_field5_TextView);
//        EditText inputField5 = findViewById(R.id.add_entity_field5_editText);
//        visionField5.setText("физическое лицо");
//        addInputField(context,visionField5, inputField5, titleField5, InputType.TYPE_CLASS_PHONE);

        AppCompatButton cancelButton = findViewById(R.id.cancel_add_entity_in_base_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (TextView t : visionTextViews) {
                    t.setText("");
                }
                addEntityLinearLayout.setVisibility(View.INVISIBLE);
                layoutMain.setVisibility(View.VISIBLE);
                layoutSecond.setVisibility(View.VISIBLE);
                controlImage.setVisibility(View.INVISIBLE);
                valueEdit.setVisibility(View.INVISIBLE);
                titleSecondLayout.setVisibility(View.INVISIBLE);
            }
        });

        AppCompatButton button = findViewById(R.id.add_entity_in_base_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reviseInputField(visionTextViews, inscriptionTextViews)) {
                    Client client = new Client();
                    client.setName(visionTextViews.get(0).getText().toString());
                    client.setFullName(visionTextViews.get(1).getText().toString());
                    client.setAddress(visionTextViews.get(2).getText().toString());
                    client.setPhone(visionTextViews.get(3).getText().toString());
                    client.setType(visionTextViews.get(4).getText().toString());
                    repository.insert(client);
                }
            }
        });
    }




}
