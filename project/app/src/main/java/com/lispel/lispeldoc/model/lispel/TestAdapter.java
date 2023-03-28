package com.lispel.lispeldoc.model.lispel;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class TestAdapter extends ListAdapter<WeirdClass, TestViewHolder> {
    Context context;
    public TestAdapter(@NonNull DiffUtil.ItemCallback<WeirdClass> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TestViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
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

