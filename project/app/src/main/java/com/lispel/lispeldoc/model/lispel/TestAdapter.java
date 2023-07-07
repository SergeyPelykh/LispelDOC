package com.lispel.lispeldoc.model.lispel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.lispel.lispeldoc.model.abstracts.ListViewDisplaible;
import com.lispel.lispeldoc.model.models.Toner;

public class TestAdapter extends ListAdapter<ListViewDisplaible, TestViewHolder> {
    Context context;
    private final ClickListener clickListener;

    public TestAdapter(@NonNull DiffUtil.ItemCallback<ListViewDisplaible> diffCallback, Context context, ClickListener clickListener) {
        super(diffCallback);
        this.context = context;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TestViewHolder testViewHolder = TestViewHolder.create(parent);
        testViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = testViewHolder.getAdapterPosition();
                if (clickListener != null) {
                    clickListener.onItemClick(position);
                }
            }
        });
        return testViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        ListViewDisplaible listViewDisplaible = getItem(position);
        holder.bind(listViewDisplaible, context);
    }
    public static class StickerNumberDiff extends DiffUtil.ItemCallback<ListViewDisplaible>{

        @Override
        public boolean areItemsTheSame(@NonNull ListViewDisplaible oldItem, @NonNull ListViewDisplaible newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListViewDisplaible oldItem, @NonNull ListViewDisplaible newItem) {
            return oldItem.getDescription().equals(newItem.getDescription());
        }

    }
    public interface ClickListener{
        void onItemClick(int position);
    }
}

