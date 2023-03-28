package com.lispel.lispeldoc.model.lispel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lispel.lispeldoc.R;

public class TestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView text;

    public TestViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        text = itemView.findViewById(R.id.number_TextView);
    }
    static TestViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_view_item, parent, false);
        return new TestViewHolder(view);
    }
    public void bind(WeirdClass weirdClass, Context context){
        text.setText(weirdClass.getNumber());
    }

    @Override
    public void onClick(View view) {

    }
}