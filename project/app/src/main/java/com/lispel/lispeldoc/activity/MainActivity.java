package com.lispel.lispeldoc.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String ID = "id";
    static final String MODE = "mode";
    private ImageButton submitButton;
    private WeirdClassModel weirdClassModel;

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