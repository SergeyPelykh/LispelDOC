package com.lispel.lispeldoc.model.lispel;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class StickerNumberListAdapter extends ListAdapter<StickerNumber, StickerNumberViewHolder> {

    public StickerNumberListAdapter(@NonNull DiffUtil.ItemCallback<StickerNumber> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public StickerNumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return StickerNumberViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull StickerNumberViewHolder holder, int position) {
        StickerNumber stickerNumber = getItem(position);
        holder.bind(stickerNumber);
    }
    public static class StickerNumberDiff extends DiffUtil.ItemCallback<StickerNumber>{

        @Override
        public boolean areItemsTheSame(@NonNull StickerNumber oldItem, @NonNull StickerNumber newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull StickerNumber oldItem, @NonNull StickerNumber newItem) {
            return oldItem.getNumber().equals(newItem.getNumber());
        }
    }
}
