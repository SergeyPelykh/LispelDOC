package com.lispel.lispeldoc.model.lispel;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.activity.MainActivity;
import com.lispel.lispeldoc.activity.WeirdActivity;

import java.text.SimpleDateFormat;

public class WeirdClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView numberTextView;
    private final TextView dateTextView;
    private final TextView clientTextView;
    private final TextView cartridgeTextView;
    private final TextView serviceTextView;
    private final TextView commentTextView;
    Context context;
    public WeirdClassViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        numberTextView = itemView.findViewById(R.id.number_TextView);
        dateTextView = itemView.findViewById(R.id.date_TextView);
        clientTextView = itemView.findViewById(R.id.client_TextView);
        cartridgeTextView = itemView.findViewById(R.id.cartridge_TextView);
        serviceTextView = itemView.findViewById(R.id.service_TextView);
        commentTextView = itemView.findViewById(R.id.comment_TextView);
    }
    public void bind (WeirdClass weirdClass, Context context){
        this.context = context;
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


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, WeirdActivity.class);
        intent.putExtra(WeirdActivity.CLIENT, clientTextView.getText());
        intent.putExtra(WeirdActivity.MODEL_CARTRIDGE, cartridgeTextView.getText());
        intent.putExtra(WeirdActivity.STICKER_NUMBER, numberTextView.getText());
        intent.putExtra(WeirdActivity.COMMENT, commentTextView.getText());
        intent.putExtra(WeirdActivity.SERVICE, serviceTextView.getText());
        intent.putExtra(WeirdActivity.DATE, dateTextView.getText());
        context.startActivity(intent);
    }
}
