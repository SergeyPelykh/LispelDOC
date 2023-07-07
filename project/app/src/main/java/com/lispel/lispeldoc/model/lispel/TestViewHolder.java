package com.lispel.lispeldoc.model.lispel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lispel.lispeldoc.R;
import com.lispel.lispeldoc.activity.TestNewOrderActivity;
import com.lispel.lispeldoc.model.abstracts.ListViewDisplaible;
import com.lispel.lispeldoc.model.models.Toner;

public class TestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView text;
    private View.OnClickListener onClickListener;

    public TestViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        text = itemView.findViewById(R.id.number_TextView);
    }
    static TestViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_view_item, parent, false);
        return new TestViewHolder(view);
    }
    public void bind(ListViewDisplaible listViewDisplaible, Context context){
        text.setText(listViewDisplaible.getDescription());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(text.getContext(), "listViewDisplaible.getDescription()", Toast.LENGTH_SHORT).show();
    }
}