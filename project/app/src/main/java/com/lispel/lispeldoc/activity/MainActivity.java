package com.lispel.lispeldoc.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.lispel.WeirdClass;
import com.lispel.lispeldoc.model.lispel.WeirdClassListAdapter;
import com.lispel.lispeldoc.model.lispel.WeirdClassModel;
import com.lispel.lispeldoc.secondVersion.model.Cartridge;
import com.lispel.lispeldoc.secondVersion.model.Client;
import com.lispel.lispeldoc.secondVersion.model.Street;
import com.lispel.lispeldoc.secondVersion.repositoriy.CartridgeRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ClientRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.FieldRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.StreetRepository;
import com.lispel.lispeldoc.secondVersion.uiServices.Field;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String FIELD_VALUE = "valueField";
    static final ArrayList<String> FIELDS = new ArrayList<>(Arrays.asList(
            "field1",
            "field2",
            "field3",
            "field4",
            "field5",
            "field6"
    ));
    static final String FIELD_1 = "field1";
    static final String FIELD_2 = "field2";
    static final String FIELD_3 = "field3";
    static final String FIELD_4 = "field4";
    static final String FIELD_5 = "field5";
    static final String NAME_ENTITY = "nameEntity";
    static final String ID = "id";
    static final String MODE = "mode";
    private ImageButton submitButton;
    private Button newButton;
    private Button addButton;
    private Button fillBaseButton;
    private WeirdClassModel weirdClassModel;

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        String valueField = intent.getStringExtra(FIELD_VALUE);
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> startForNewOrder = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        String valueField = intent.getStringExtra(FIELD_VALUE);
                    }
                }
            }
    );

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemMenuId = item.getItemId();
        switch (itemMenuId) {
            case R.id.menu_upload_in_file:
                try {
                    File path = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS);
                    String name = new SimpleDateFormat("HH_mm_dd_MM_yyyy").format(new Date()) + ".txt";
                    File file = new File(path, name);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                    getAllRecords().forEach(x -> {
                        String dateOfEdit = "not edited";
                        if (x.getDate_of_last_edit() != null) {
                            dateOfEdit = "edited " + new SimpleDateFormat("HH:mm dd.MM.yyyy").format(x.getDate_of_last_edit());
                        }
                        try {
                            bufferedWriter.write(x.getNumber() + " | " + new SimpleDateFormat("HH:mm dd.MM.yyyy").format(x.getDate_of_create())
                                    + " | " + dateOfEdit + " | " + x.getClient() + " | " + x.getCartridge()
                                    + " | " + x.getService() + " | " + x.getComment() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    bufferedWriter.close();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    Toast.makeText(this, "save in file " + name, Toast.LENGTH_SHORT).show();
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

        StreetRepository streetRepository = new StreetRepository(getApplication(), "street");
        FieldRepository fieldRepository = new FieldRepository(getApplication());
        CartridgeRepository cartridgeRepository = new CartridgeRepository(getApplication());
        ClientRepository clientRepository = new ClientRepository(getApplication());


        weirdClassModel = new ViewModelProvider(this).get(WeirdClassModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WeirdClassListAdapter adapter = new WeirdClassListAdapter(new WeirdClassListAdapter.StickerNumberDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        weirdClassModel.getAllWeirdClasses().observe(this, weirdClasses -> {
            if (weirdClasses != null) {
                adapter.submitList(weirdClasses);
            }
        });
        newButton = findViewById(R.id.new_29_07_button);
        cartridgeRepository.getAllCartridges().observe(MainActivity.this, x -> {
            if (x == null){
                newButton.setText("nothing cartridges");
            }
            else {
                newButton.setText(x.size() + " cartridges");
            }
        });
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientRepository.getAllClients().observe(MainActivity.this, x -> {
                    fillBaseButton.setText(fillBaseButton.getText() + " " + x.size() + "cl");
                });
                Intent intent = new Intent(MainActivity.this, CreateOrderDialogActivity.class);
                intent.putExtra(FIELD_1, "address");
                intent.putExtra(FIELD_2, "clientType");
                intent.putExtra(FIELD_3, "cartridge");
                intent.putExtra(NAME_ENTITY, "new entity");
                startForResult.launch(intent);

            }
        });



        fillBaseButton = findViewById(R.id.fill_base_button);

        streetRepository.getAllStreets().observe(MainActivity.this, x -> {
            if (x.size() == 0){
                fillBaseButton.setText("base empty");
            }
        });

        fillBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateOrderDialogActivity.class);
                //intent.putExtra(FIELD_1, "address");
                startForResult.launch(intent);
                streetRepository.getAllStreets().observe(MainActivity.this, x -> {
                    if (x.size() == 0){
                        String line = "";
                        try(
                                BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(getAssets().open("streets.txt"))))
                        {
                            while ((line = bufferedReader.readLine()) != null){
                                streetRepository.insert(new Street("street", line));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        fillBaseButton.setText(x.size() + " str");
                    }
                });


                cartridgeRepository.getAllCartridges().observe(MainActivity.this, x -> {
                    if (x.size() == 0){

                        Client client = new Client();
                        client.setName("Акватория");
                        client.setAddress("Ленина 26");
                        client.setFullName("ООО Акватория");
                        client.setPhone("888888888");
                        client.setType("Организация");
                        Thread thread1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                clientRepository.insert(client);
                            }
                        });
                        thread1.start();

                        for (int i = 0; i < 20; i++){
                            Cartridge cartridge2 = new Cartridge();
                            cartridge2.setModel("HP " + i);
                            cartridge2.setVendor("HP");
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    cartridgeRepository.insert(cartridge2);
                                }
                            });
                            thread.start();
                        }



                        StreetRepository streetRepository1 = new StreetRepository(
                                getApplication(), "clientType");
                        streetRepository1.insert(new Street(
                                "clientType", "организация"));
                        streetRepository1.insert(new Street(
                                "clientType", "физическое лицо"));

                        Field address = new Field();
                        address.setName("address");
                        address.setHint("адрес");
                        address.setInscription("адрес клиента");
                        address.setDataSource("street");
                        address.setInputType(1);

                        Field clientType = new Field();
                        clientType.setName("clientType");
                        clientType.setHint("организация/физик");
                        clientType.setInscription("тип клиента");
                        clientType.setDataSource("clientType");
                        clientType.setInputType(1);


                        Field cartridge = new Field();
                        cartridge.setName("cartridge");
                        cartridge.setHint("модель картриджа");
                        cartridge.setInscription("картридж");
                        cartridge.setDataSource("cartridge");
                        cartridge.setInputType(1);

                        Field clientField = new Field();
                        clientField.setName("client");
                        clientField.setHint("имя клиента");
                        clientField.setInscription("клиент");
                        clientField.setDataSource("client");
                        clientField.setInputType(8192);

                        Field name = new Field();
                        name.setName("name");
                        name.setHint("имя");
                        name.setInscription("имя");
                        name.setDataSource("none");
                        name.setInputType(8192);

                        Field fullName = new Field();
                        fullName.setName("fullName");
                        fullName.setHint("полное имя");
                        fullName.setInscription("полное имя");
                        fullName.setDataSource("none");
                        fullName.setInputType(8192);

                        Field phone = new Field();
                        phone.setName("phone");
                        phone.setHint("номер телефона");
                        phone.setInscription("номер телефона");
                        phone.setDataSource("none");
                        phone.setInputType(3);

                        fieldRepository.insert(address);
                        fieldRepository.insert(clientType);
                        fieldRepository.insert(cartridge);
                        fieldRepository.insert(clientField);
                        fieldRepository.insert(name);
                        fieldRepository.insert(fullName);
                        fieldRepository.insert(phone);
                    }
                });







            }
        });


        submitButton = findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewOrderActivity.class);
                if (getAllRecords().size() > 0) {
                    int id = Collections.max(getAllRecords(), MainActivity::compareWeirdClass).getId();
                    intent.putExtra(MainActivity.ID, id);
                }
                intent.putExtra(MainActivity.MODE, "createNew");
                startActivity(intent);
            }
        });

        addButton = findViewById(R.id.add_order_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateOrderDialogActivity.class);
                intent.putExtra(NAME_ENTITY, "cartridge");
                intent.putExtra(FIELD_1, "client");
                startForNewOrder.launch(intent);
            }
        });
    }



    static int compareWeirdClass(WeirdClass a, WeirdClass b) {
        return a.getId() - b.getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<WeirdClass> getAllRecords() {
        List<WeirdClass> allRecords = weirdClassModel.getAllWeirdClasses().getValue();
        return allRecords;
    }
}