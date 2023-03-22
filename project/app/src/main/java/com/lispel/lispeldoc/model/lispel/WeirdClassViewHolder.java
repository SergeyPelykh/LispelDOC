package com.lispel.lispeldoc.model.lispel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lispel.lispeldoc.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeirdClassViewHolder extends RecyclerView.ViewHolder {
    private final TextView numberTextView;
    private final TextView dateTextView;
    private final TextView clientTextView;
    private final TextView cartridgeTextView;
    private final TextView serviceTextView;
    private final TextView commentTextView;
    public WeirdClassViewHolder(@NonNull View itemView) {
        super(itemView);
        numberTextView = itemView.findViewById(R.id.number_TextView);
        dateTextView = itemView.findViewById(R.id.date_TextView);
        clientTextView = itemView.findViewById(R.id.client_TextView);
        cartridgeTextView = itemView.findViewById(R.id.cartridge_TextView);
        serviceTextView = itemView.findViewById(R.id.service_TextView);
        commentTextView = itemView.findViewById(R.id.comment_TextView);
    }
    public void bind (WeirdClass weirdClass){
        numberTextView.setText(weirdClass.getNumber());
        dateTextView.setText(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(weirdClass.getDate_of_create()));
        clientTextView.setText(weirdClass.getClient());
        cartridgeTextView.setText(weirdClass.getCartridge());
        serviceTextView.setText(weirdClass.getService());
        commentTextView.setText(weirdClass.getComment());
    }



    static WeirdClassViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new WeirdClassViewHolder(view);
    }
}
