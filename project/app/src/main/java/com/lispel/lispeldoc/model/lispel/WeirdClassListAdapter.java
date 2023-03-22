package com.lispel.lispeldoc.model.lispel;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class WeirdClassListAdapter extends ListAdapter<WeirdClass, WeirdClassViewHolder> {
    public WeirdClassListAdapter(@NonNull DiffUtil.ItemCallback<WeirdClass> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public WeirdClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return WeirdClassViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WeirdClassViewHolder holder, int position) {
        WeirdClass weirdClass = getItem(position);
        holder.bind(weirdClass);
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
