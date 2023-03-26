package com.lispel.lispeldoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.abstracts.Client;
import com.lispel.lispeldoc.model.abstracts.Order;
import com.lispel.lispeldoc.model.lispel.Cartridge;
import com.lispel.lispeldoc.model.lispel.ClientLispel;
import com.lispel.lispeldoc.model.lispel.OrderItemLispel;
import com.lispel.lispeldoc.model.lispel.OrderLispel;
import com.lispel.lispeldoc.model.lispel.PrintUnit;
import com.lispel.lispeldoc.model.lispel.ServiceOnPrintUnit;
import com.lispel.lispeldoc.model.lispel.StickerNumber;
import com.lispel.lispeldoc.model.lispel.WeirdClass;
import com.lispel.lispeldoc.model.lispel.WeirdClassModel;

import java.util.Date;

public class NewOrderActivity extends AppCompatActivity {
    EditText stickerNumberEditText;
    EditText modelCartridgeEditText;
    EditText clientEditText;
    EditText serviceItemEditText;
    EditText commentEditText;
    Button submitButton;
    private WeirdClassModel weirdClassModel;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        weirdClassModel = new ViewModelProvider(this).get(WeirdClassModel.class);
        stickerNumberEditText = findViewById(R.id.sticker_number);
        modelCartridgeEditText = findViewById(R.id.model_cartridge);
        clientEditText = findViewById(R.id.client_name);
        serviceItemEditText = findViewById(R.id.service_item);
        commentEditText = findViewById(R.id.comment_edit_text);
        submitButton = findViewById(R.id.button);
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra(MainActivity.ID, 0);
            weirdClassModel.getById(id).observe(this, x -> {
                if (x != null) {
                    System.out.println(x.getCartridge());
                    stickerNumberEditText.setText(x.getNumber());
                    modelCartridgeEditText.setText(x.getCartridge());
                    clientEditText.setText(x.getClient());
                    serviceItemEditText.setText(x.getService());
                    commentEditText.setText(x.getComment());
                }
            });
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String mode;
                if (intent != null) {
                    WeirdClass weirdClass = new WeirdClass();
                    weirdClass.setClient(clientEditText.getText().toString());
                    weirdClass.setNumber(stickerNumberEditText.getText().toString());
                    weirdClass.setCartridge(modelCartridgeEditText.getText().toString());
                    weirdClass.setService(serviceItemEditText.getText().toString());
                    weirdClass.setComment(commentEditText.getText().toString());
                    mode = intent.getStringExtra(MainActivity.MODE);
                    switch (mode) {
                        case "createNew":
                            weirdClassModel.insert(weirdClass);
                            break;
                        case "editExist":
                            weirdClassModel.getById(id).observe(NewOrderActivity.this, x -> {
                                weirdClass.setDate_of_create(x.getDate_of_create());
                                weirdClass.setId(id);
                                weirdClass.setDate_of_last_edit(new Date());
                                weirdClassModel.update(weirdClass);
                                    });
                            break;
                    }
                }
                finish();
            }
        });
    }
}