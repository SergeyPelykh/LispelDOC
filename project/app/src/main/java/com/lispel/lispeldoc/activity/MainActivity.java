package com.lispel.lispeldoc.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.service.carrier.CarrierMessagingService;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.abstracts.Client;
import com.lispel.lispeldoc.model.abstracts.Order;
import com.lispel.lispeldoc.model.abstracts.OrderItem;
import com.lispel.lispeldoc.model.lispel.Cartridge;
import com.lispel.lispeldoc.model.lispel.ClientLispel;
import com.lispel.lispeldoc.model.lispel.OrderItemLispel;
import com.lispel.lispeldoc.model.lispel.OrderLispel;
import com.lispel.lispeldoc.model.lispel.PrintUnit;
import com.lispel.lispeldoc.model.lispel.ServiceOnPrintUnit;
import com.lispel.lispeldoc.model.lispel.StickerNumber;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String CLIENT = "client";
    static final String MODEL_CARTRIDGE = "modelCartridge";
    static final String STICKER_NUMBER = "stickerNumber";
    static final String SERVICE = "service";
    private Button submitButton;
    private Button saveButton;
    private List<Order> orders;
    private String str;
    private TextView text;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityResultLauncher<Intent> startNewActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            if (data != null) {
                                str = data.getStringExtra(STICKER_NUMBER) + ", " +
                                        data.getStringExtra(MODEL_CARTRIDGE) + ", " +
                                        data.getStringExtra(CLIENT) + ", " +
                                        data.getStringExtra(SERVICE);
                                updateText(str);
                            }
                        }
                    }
                });

        text = findViewById(R.id.text2);


        submitButton = findViewById(R.id.button);
        saveButton = findViewById(R.id.saveButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewOrderActivity.class);
                startNewActivityForResult.launch(intent);
            }

        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File path = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS);
                    String name = new SimpleDateFormat("dd_MM_yyyy_HH_mm").format(new Date()) +".txt";
                    File file = new File(path, name);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                    bufferedWriter.write(text.getText().toString());
                    bufferedWriter.close();
                    text.setText("data was save in file " + name);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    private void updateText(String str) {
        String oldText = text.getText().toString();
        text.setText(oldText + "\n" + str + ", " + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
    }

    private void saveFile(File file){
        
    }








}