package com.lispel.lispeldoc.model.lispel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lispel.lispeldoc.R;

public class StickerNumberViewHolder extends RecyclerView.ViewHolder {
    private final TextView stickerNumberItemView;
    private final TextView stickerNumberDataView;
    private StickerNumberViewHolder(@NonNull View itemView) {
        super(itemView);
        stickerNumberItemView = itemView.findViewById(R.id.number_TextView);
        stickerNumberDataView = itemView.findViewById(R.id.date_TextView);
    }
    public void bind (StickerNumber stickerNumber){

        stickerNumberItemView.setText(stickerNumber.getNumber());
        stickerNumberDataView.setText(stickerNumber.getDateOfCreate().toString());
    }


    static StickerNumberViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new StickerNumberViewHolder(view);
    }
}
