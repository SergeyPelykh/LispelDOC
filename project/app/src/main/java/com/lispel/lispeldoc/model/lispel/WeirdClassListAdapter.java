package com.lispel.lispeldoc.model.lispel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.lispel.lispeldoc.activity.WeirdActivity;

public class WeirdClassListAdapter extends ListAdapter<WeirdClass, WeirdClassViewHolder> {
    Context context;
    public WeirdClassListAdapter(@NonNull DiffUtil.ItemCallback<WeirdClass> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }


    @NonNull
    @Override
    public WeirdClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return WeirdClassViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WeirdClassViewHolder holder, int position) {
        WeirdClass weirdClass = getItem(position);
        holder.bind(weirdClass, context);
    }

    public static class StickerNumberDiff extends DiffUtil.ItemCallback<WeirdClass>{

        @Override
        public boolean areItemsTheSame(@NonNull WeirdClass oldItem, @NonNull WeirdClass newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull WeirdClass oldItem, @NonNull WeirdClass newItem) {
            return oldItem.getNumber().equals(newItem.getNumber());
        }

    }



}
