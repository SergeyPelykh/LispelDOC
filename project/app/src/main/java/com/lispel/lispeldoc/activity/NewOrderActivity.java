package com.lispel.lispeldoc.activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class NewOrderActivity extends AppCompatActivity {
    EditText stickerNumberEditText;
    EditText modelCartridgeEditText;
    EditText clientEditText;
    EditText serviceItemEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        stickerNumberEditText = findViewById(R.id.sticker_number);
        modelCartridgeEditText = findViewById(R.id.model_cartridge);
        clientEditText = findViewById(R.id.client_name);
        serviceItemEditText = findViewById(R.id.service_item);
        submitButton = findViewById(R.id.button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.CLIENT, clientEditText.getText().toString().trim());
                intent.putExtra(MainActivity.MODEL_CARTRIDGE, modelCartridgeEditText.getText().toString().trim());
                intent.putExtra(MainActivity.STICKER_NUMBER, stickerNumberEditText.getText().toString().trim());
                intent.putExtra(MainActivity.SERVICE, serviceItemEditText.getText().toString().trim());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}