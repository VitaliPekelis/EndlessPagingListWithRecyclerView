package com.vit.pek;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends PagedListAdapter<DataItem, MyAdapter.DataModelViewHolder> {


    protected MyAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public DataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        view.setBackgroundResource(R.color.colorAccent);
        return new DataModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelViewHolder holder, int position) {
        DataItem model = getItem(position);


    }

    static class DataModelViewHolder extends RecyclerView.ViewHolder {

        public DataModelViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private static final DiffUtil.ItemCallback<DataItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<DataItem>() {
        @Override
        public boolean areItemsTheSame(
                @NonNull DataItem oldData, @NonNull DataItem newData) {
            return oldData.getIndex() == newData.getIndex();
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataItem oldData, @NonNull DataItem newData) {
            return oldData.equals(newData);
        }
    };
}
