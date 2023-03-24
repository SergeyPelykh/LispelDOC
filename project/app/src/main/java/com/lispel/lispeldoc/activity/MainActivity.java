package com.lispel.lispeldoc.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.lispel.WeirdClass;
import com.lispel.lispeldoc.model.lispel.WeirdClassListAdapter;
import com.lispel.lispeldoc.model.lispel.WeirdClassModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String CLIENT = "client";
    static final String MODEL_CARTRIDGE = "modelCartridge";
    static final String STICKER_NUMBER = "stickerNumber";
    static final String SERVICE = "service";
    static final String COMMENT = "comment";
    private Button submitButton;
    private Button saveButton;
    private String str;
    private TextView text;
    private ActionMenuItemView menuItemUpload;

    private WeirdClassModel weirdClassModel;
    private WeirdClass weirdClass;
    static int icon = R.drawable.ic_baseline_insert_drive_file_light_24;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "deleteAll", Toast.LENGTH_SHORT).show();
        int itemMenuId = item.getItemId();
        switch (itemMenuId){
            case R.id.menu_upload_in_file:
                try {
                    File path = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS);
                    String name = new SimpleDateFormat("dd_MM_yyyy_HH_mm").format(new Date()) +".txt";
                    File file = new File(path, name);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                    getAllRecords().forEach(x -> {
                        try {
                            bufferedWriter.write(x.getNumber() + " | "+ new SimpleDateFormat("dd.MM.yyyy HH:mm").format(x.getDate_of_create())
                                    + " | " + x.getClient() + " | " + x.getCartridge()
                                    + " | " + x.getService() + " | " + x.getComment() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weirdClassModel = new ViewModelProvider(this).get(WeirdClassModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WeirdClassListAdapter adapter = new WeirdClassListAdapter(new WeirdClassListAdapter.StickerNumberDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        weirdClassModel.getAllWeirdClasses().observe(this, weirdClasses -> {
            adapter.submitList(weirdClasses);
        });


        ActivityResultLauncher<Intent> startNewActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent dataFromIntent = result.getData();
                            if (dataFromIntent != null) {
                                str = dataFromIntent.getStringExtra(STICKER_NUMBER) + ", " +
                                        dataFromIntent.getStringExtra(MODEL_CARTRIDGE) + ", " +
                                        dataFromIntent.getStringExtra(CLIENT) + ", " +
                                        dataFromIntent.getStringExtra(SERVICE) + ", " +
                                        dataFromIntent.getStringExtra(COMMENT);
                                updateText(str);
                                createNewInstanceAndInsertInDatabase(dataFromIntent);
                            }
                        }
                    }
                });

        text = findViewById(R.id.text2);
        menuItemUpload = findViewById(R.id.menu_upload_in_file);
        submitButton = findViewById(R.id.button);
        saveButton = findViewById(R.id.saveButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewOrderActivity.class);
                startNewActivityForResult.launch(intent);
            }
        });
    }

    private void createNewInstanceAndInsertInDatabase(Intent data) {
        weirdClass = new WeirdClass();
        weirdClass.setClient(data.getStringExtra(CLIENT));
        weirdClass.setNumber(data.getStringExtra(STICKER_NUMBER));
        weirdClass.setCartridge(data.getStringExtra(MODEL_CARTRIDGE));
        weirdClass.setService(data.getStringExtra(SERVICE));
        weirdClass.setComment(data.getStringExtra(COMMENT));
        weirdClassModel.insert(weirdClass);
    }


    private void updateText(String str) {
        String oldText = text.getText().toString();
        text.setText(oldText + "\n" + str + ", " + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        //weirdClassModel.insert(new WeirdClass(str.substring(0, 8)));
        //stickerNumberModel.insert(new StickerNumber(str.substring(0, 8)));
        //stickerNumberModel.deleteAll();
        //weirdClassModel.deleteAll();

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<WeirdClass> getAllRecords(){
        List<WeirdClass> allRecords = weirdClassModel.getAllWeirdClasses().getValue();
        allRecords.forEach(x -> System.out.println(x));

        return allRecords;

    }
}