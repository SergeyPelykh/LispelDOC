package com.lispel.lispeldoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.model.abstracts.ListViewDisplaible;
import com.lispel.lispeldoc.model.lispel.TestAdapter;

import com.lispel.lispeldoc.secondVersion.model.Cartridge;
import com.lispel.lispeldoc.secondVersion.model.Client;
import com.lispel.lispeldoc.secondVersion.model.Sticker;
import com.lispel.lispeldoc.secondVersion.repositoriy.CartridgeRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ClientRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.ComponentRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.StickerRepository;
import com.lispel.lispeldoc.secondVersion.repositoriy.TonerRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestNewOrderActivity extends AppCompatActivity {
    private TextView stickerNumber;
    private EditText stickerNumberEdit;
    private TextView titleStickerNumber;
    private TextView modelCartridge;
    private TextView addNewItemTextView;
    private TextView clientNameTextView;
    private TextView service;
    private TextView commentTextView;
    private Button submitButton;
    private Button submitButton2;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_new_order);


        //StickerLispelRepository stickerLispelRepository = new StickerLispelRepository(getApplication());
        StickerRepository stickerRepository = new StickerRepository(getApplication());
        ClientRepository clientRepository = new ClientRepository(getApplication());
        TonerRepository tonerRepository = new TonerRepository(getApplication());
        ComponentRepository componentRepository = new ComponentRepository(getApplication());
        CartridgeRepository cartridgeRepository = new CartridgeRepository(getApplication());
        service = findViewById(R.id.serviceTextView);
        clientNameTextView = findViewById(R.id.clientNameTextView);
        stickerNumberEdit = findViewById(R.id.stickerNumberEditText);
        titleStickerNumber = findViewById(R.id.titleStickerNumberTextView);
        recyclerView = findViewById(R.id.recyclerview);
        stickerNumber = findViewById(R.id.stickerNumberTextView);
        modelCartridge = findViewById(R.id.cartridgeTextView);
        submitButton = findViewById(R.id.submitButton);
        submitButton2 = findViewById(R.id.submitButton2);
//        for (int i = 0; i < 10; i++ ){
//            Client client = new Client();
//            client.setName("Client " + i);
//            client.setFullName("Fullname " + i);
//            client.setAddress("Address " + i);
//            client.setPhone("22-33-1" + i);
//            client.setType("Person");
//            clientRepository.insert(client);
//        }

        Client client = new Client();
        client.setName("Акватория");
        //clientRepository.insert(client);

        Cartridge cartridge = new Cartridge();
        cartridge.setModel("1a");
        cartridge.addSticker(5l);
        System.out.println("save cartridge");
        //cartridgeRepository.insert(cartridge);









        Sticker sticker = new Sticker();
        sticker.setNumber("LS741913");
        //stickerRepository.insert(sticker);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("onClick()");
                LiveData<Cartridge> liveData = cartridgeRepository.getCartridgeByModel("1a");
                liveData.observe(TestNewOrderActivity.this, x -> {
                    stickerRepository.getStickerByNumber("LS741913").observe(TestNewOrderActivity.this, y -> {
                        System.out.println("get cartridge");
                        //x.addSticker(y.getId());

                        clientRepository.getClientByName("Акватория").observe(TestNewOrderActivity.this, r ->{
                            x.addOwner(r.getId());
                            modelCartridge.setText(x.getOwner() + "" + x.getModel());
                        });
                        x.setModel("85a");
                    cartridgeRepository.update(x);
                    liveData.removeObservers(TestNewOrderActivity.this);
                });
                });
            }


        });
        submitButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientRepository.getClientByName("Client 0").observe(TestNewOrderActivity.this, x -> {
                    clientNameTextView.setText(x.getName() + " | " + x.getFullName() + " | " + x.getPhone()  + " | " + x.getId());
                });
//                cartridgeRepository.getCartridgeByModel("85a").observe(TestNewOrderActivity.this, x -> {
//                    clientRepository.getClientById(x.getOwner()).observe(TestNewOrderActivity.this, y -> {
//                        stickerRepository.getStickerById(x.getStickers().get(1)).observe(TestNewOrderActivity.this, d ->{
//                            clientNameTextView.setText(x.getModel() + " | " + d.getNumber() + " | "+ y.getName());
//                        });
//                    });
//                });

            }
        });




    }

    void displayRecyclerList(RecyclerView recyclerView, ArrayList<ListViewDisplaible> arr, TextView textView) {
        recyclerView.setVisibility(View.VISIBLE);
        final TestAdapter.ClickListener clickListener = new TestAdapter.ClickListener() {
            @Override
            public void onItemClick(int position) {
                textView.setText(arr.get(position).getDescription());
                recyclerView.setVisibility(View.INVISIBLE);
            }
        };
        TestAdapter adapter = new TestAdapter(new TestAdapter.StickerNumberDiff(), TestNewOrderActivity.this, clickListener);
        recyclerView.setAdapter(adapter);
        adapter.submitList(arr);
    }
}
