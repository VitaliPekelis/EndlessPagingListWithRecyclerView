package com.vit.pek;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

class DataItem {
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    static final DiffUtil.ItemCallback<DataItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<DataItem>() {
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
