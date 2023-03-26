package com.lispel.lispeldoc.model.lispel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.activity.WeirdActivity;

import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class WeirdClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView numberTextView;
    private final TextView dateTextView;
    private final TextView clientTextView;
    private final TextView cartridgeTextView;
    int id;
    Context context;
    public WeirdClassViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        numberTextView = itemView.findViewById(R.id.number_TextView);
        dateTextView = itemView.findViewById(R.id.date_TextView);
        clientTextView = itemView.findViewById(R.id.client_TextView);
        cartridgeTextView = itemView.findViewById(R.id.cartridge_TextView);
    }
    public void bind (WeirdClass weirdClass, Context context){
        this.context = context;
        numberTextView.setText(weirdClass.getNumber());
        dateTextView.setText(new SimpleDateFormat("HH:mm dd.MM.yyyy").format(weirdClass.getDate_of_create()));
        clientTextView.setText(weirdClass.getClient());
        cartridgeTextView.setText(weirdClass.getCartridge());
        id = weirdClass.getId();
    }



    static WeirdClassViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new WeirdClassViewHolder(view);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, WeirdActivity.class);
        intent.putExtra(WeirdActivity.ID, id);
        context.startActivity(intent);
    }
}
