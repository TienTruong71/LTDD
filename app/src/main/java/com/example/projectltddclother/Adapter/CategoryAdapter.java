package com.example.projectltddclother.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectltddclother.Domain.CategoryModel;
import com.example.projectltddclother.R;
import com.example.projectltddclother.databinding.ViewholderCategoryBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryModel> items;
    private Context context;
    private int selectedPosition = 1;
    private int lastSelectedPostion = -1;

    public CategoryAdapter(ArrayList<CategoryModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        ViewholderCategoryBinding binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context)
        ,parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
            holder.binding.titleTxt.setText(items.get(position).getTitle());
            holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPostion= selectedPosition;
                    selectedPosition = position;
                    notifyItemChanged(lastSelectedPostion);
                    notifyItemChanged(selectedPosition);

                }
            });
            if(selectedPosition==position){
                holder.binding.titleTxt.setBackgroundResource(R.drawable.orange_bg);
                holder.binding.titleTxt.setTextColor(context.getResources().getColor(R.color.white));
            }else{
                holder.binding.titleTxt.setBackgroundResource(R.drawable.stroke_bg);
                holder.binding.titleTxt.setTextColor(context.getResources().getColor(R.color.black));
            }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderCategoryBinding binding;
        public ViewHolder(ViewholderCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
