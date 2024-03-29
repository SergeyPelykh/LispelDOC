package com.lispel.lispeldoc.activity;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.models.PartOfCartridge;
import com.lispel.lispeldoc.model.models.Toner;
import com.lispel.lispeldoc.model.repositories.TonerRepository;
import com.lispel.lispeldoc.model.lispel.WeirdClassModel;
import com.lispel.lispeldoc.model.utility.ServiceForEntity;
import com.lispel.lispeldoc.secondVersion.model.Client;

import java.text.SimpleDateFormat;


public class WeirdActivity extends AppCompatActivity {
    public static final String CLIENT = "client";
    public static final String MODEL_CARTRIDGE = "modelCartridge";
    public static final String STICKER_NUMBER = "stickerNumber";
    public static final String SERVICE = "service";
    public static final String COMMENT = "comment";
    public static final String DATE = "date";
    public static final String ID = "id";
    private final int ADMIN_PASSWORD = 12;
    private TextView numberTextView;
    private TextView cartridgeTextView;
    private TextView clientTextView;
    private TextView dateTextView;
    private TextView serviceTextView;
    private TextView commentTextView;
    private TextView tonerTextView;
    private EditText tonerNameEditText;
    private EditText tonerFullNameEditText;
    private Button tonerButton;
    private Button tonerSelectButton;
    private Button deleteButton;
    private Button editButton;
    private WeirdClassModel weirdClassModel;
    private int id = 0;
    Context context;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weird);
        context = this;

        weirdClassModel = new ViewModelProvider(this).get(WeirdClassModel.class);
        numberTextView = findViewById(R.id.number_TextView);
        cartridgeTextView = findViewById(R.id.cartridge_TextView);
        clientTextView = findViewById(R.id.client_TextView);
        dateTextView = findViewById(R.id.date_TextView);
        serviceTextView = findViewById(R.id.service_TextView);
        commentTextView = findViewById(R.id.comment_TextView);
        deleteButton = findViewById(R.id.delete_button);
        editButton = findViewById(R.id.edit_button);

        tonerNameEditText = findViewById(R.id.toner_name_EditText);
        tonerFullNameEditText = findViewById(R.id.toner_full_name_EditText);
        tonerTextView = findViewById(R.id.toner_TextView);
        tonerButton = findViewById(R.id.toner_add_button);
        tonerSelectButton = findViewById(R.id.toner_select_button);
        TonerRepository tonerRepository = new TonerRepository(getApplication());


        tonerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeirdActivity.this, AddOrderActivity.class);
                intent.putExtra(ID, id);
                intent.putExtra(MainActivity.MODE, "createNew");
                context.startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra(MainActivity.ID, 0);
            weirdClassModel.getById(id).observe(this, x -> {
                if (x != null) {
                    System.out.println(x.getCartridge());
                    numberTextView.setText("id = " + x.getId() + " " + x.getNumber());
                    cartridgeTextView.setText(x.getCartridge());
                    clientTextView.setText(x.getClient());
                    serviceTextView.setText(x.getService());
                    commentTextView.setText(x.getComment());
                    String dateOfEdit = "not edited";
                    if(x.getDate_of_last_edit() != null){
                        dateOfEdit = "edited " + new SimpleDateFormat("HH:mm dd.MM.yyyy").format(x.getDate_of_last_edit());
                    }
                    dateTextView.setText(new SimpleDateFormat("HH:mm dd.MM.yyyy").format(x.getDate_of_create()) + " " + dateOfEdit);
                }
            });
        }

        tonerSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeirdActivity.this, CreateOrderActivity.class);
                intent.putExtra(ID, id);
                intent.putExtra(MainActivity.MODE, "createNew");
                intent.putStringArrayListExtra("listEntityFields", (new Client().getNameAllFields()));
                intent.putExtra("titleActivity", "Добавление нового клиента");
                intent.putExtra("repository", "client");
                context.startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = new EditText(WeirdActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                new AlertDialog.Builder(WeirdActivity.this).setTitle("Вы собираетесь удалить запись из базы данных")
                        .setMessage("для продолжения введите пароль администратора").setView(input)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Editable value = input.getText();
                                try {
                                    if (Integer.parseInt(value.toString()) == ADMIN_PASSWORD) {
                                        weirdClassModel.deleteById(id);
                                        Toast.makeText(WeirdActivity.this, "Запись успешно удалена", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(WeirdActivity.this, "неверный пароль", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = new EditText(WeirdActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                new AlertDialog.Builder(WeirdActivity.this).setTitle("Вы собираетесь изменить запись в базе данных")
                        .setMessage("для продолжения введите пароль администратора").setView(input)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Editable value = input.getText();
                                try {
                                    if (Integer.parseInt(value.toString()) == ADMIN_PASSWORD) {
                                        Intent intent = new Intent(context, NewOrderActivity.class);
                                        intent.putExtra(MainActivity.MODE, "editExist");
                                        intent.putExtra(WeirdActivity.ID, id);
                                        context.startActivity(intent);
                                    } else {
                                        Toast.makeText(WeirdActivity.this, "неверный пароль", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
            }
        });
    }
}