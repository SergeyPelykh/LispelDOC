package com.lispel.lispeldoc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.lispel.TestAdapter;
import com.lispel.lispeldoc.model.lispel.WeirdClass;
import com.lispel.lispeldoc.model.lispel.WeirdClassListAdapter;
import com.lispel.lispeldoc.model.lispel.WeirdClassModel;

import java.util.Date;

public class TestNewOrderActivity extends AppCompatActivity {
    TextView stickerNumberTextView;
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
        setContentView(R.layout.activity_test_new_order);

        weirdClassModel = new ViewModelProvider(this).get(WeirdClassModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TestAdapter adapter = new TestAdapter(new WeirdClassListAdapter.StickerNumberDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        weirdClassModel.getAllWeirdClasses().observe(this, weirdClasses -> {
            if (weirdClasses != null) {
                adapter.submitList(weirdClasses);
            }
        });

        weirdClassModel = new ViewModelProvider(this).get(WeirdClassModel.class);
        stickerNumberTextView = findViewById(R.id.sticker_number);
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
                    stickerNumberTextView.setText(x.getNumber());
                    modelCartridgeEditText.setText(x.getCartridge());
                    clientEditText.setText(x.getClient());
                    serviceItemEditText.setText(x.getService());
                    commentEditText.setText(x.getComment());
                }
            });
        }
        stickerNumberTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(stickerNumberTextView.getText().toString().substring(2)) + 1;
                stickerNumberTextView.setText(stickerNumberTextView.getText().subSequence(0, 2) + "" + number);
                stickerNumberTextView.setTextSize(22);
                stickerNumberTextView.setEnabled(false);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String mode;
                if (intent != null) {
                    WeirdClass weirdClass = new WeirdClass();
                    weirdClass.setClient(clientEditText.getText().toString());
                    weirdClass.setNumber(stickerNumberTextView.getText().toString());
                    weirdClass.setCartridge(modelCartridgeEditText.getText().toString());
                    weirdClass.setService(serviceItemEditText.getText().toString());
                    weirdClass.setComment(commentEditText.getText().toString());
                    mode = intent.getStringExtra(MainActivity.MODE);
                    switch (mode) {
                        case "createNew":
                            weirdClassModel.insert(weirdClass);
                            break;
                        case "editExist":
                            weirdClassModel.getById(id).observe(TestNewOrderActivity.this, x -> {
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
