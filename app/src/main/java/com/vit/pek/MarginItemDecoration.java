package com.vit.pek;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecoration extends RecyclerView.ItemDecoration {

    private int spaceInt;

    public MarginItemDecoration(int spaceInt){
        this.spaceInt = spaceInt;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.top = spaceInt;
        }
        outRect.left = spaceInt;
        outRect.right = spaceInt;
        outRect.bottom = spaceInt;
    }

}
