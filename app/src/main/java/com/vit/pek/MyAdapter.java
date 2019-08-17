package com.vit.pek;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.BitmapCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyAdapter extends PagedListAdapter<DataItem, MyAdapter.DataModelViewHolder> {

    private Handler mainThredHandler;
    private Executor executer;

    MyAdapter() {
        super(DataItem.DIFF_CALLBACK);
        mainThredHandler= new Handler(Looper.getMainLooper());
        executer = Executors.newFixedThreadPool(3);
    }

    @NonNull
    @Override
    public DataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new DataModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelViewHolder holder, int position) {
        Context context = holder.textView.getContext();
        DataItem model = getItem(position);
        String positionStr = String.format(Locale.getDefault(),"%06d", position);

        holder.textView.setText(positionStr);
        if(position % 2 == 0){
            holder.textView.setTextColor(context.getResources().getColor(android.R.color.black));
        } else {
            holder.textView.setTextColor(context.getResources().getColor(R.color.highlighted));
        }

        executer.execute(new DrawTextOnBitmapRunnable(holder.imageView, String.valueOf(position)));
    }

    static class DataModelViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public DataModelViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
            imageView = itemView.findViewById(R.id.iv);
        }
    }

    class DrawTextOnBitmapRunnable implements Runnable {

        private WeakReference<ImageView> imageViewWeakReference;
        private String text;

        public DrawTextOnBitmapRunnable(ImageView imageView, String text) {
            if(imageView == null) throw new IllegalArgumentException("imageView cant be NULL");

            imageViewWeakReference = new WeakReference<>(imageView);
            this.text = text;
        }

        @Override
        public void run() {
            Bitmap bitmap = drawOnBitmap();
            if(imageViewWeakReference.get() == null) {
                bitmap.recycle();
            } else {
                try{
                    mainThredHandler.post(() ->
                        imageViewWeakReference.get().setImageBitmap(bitmap));
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        Bitmap drawOnBitmap(){
            Context context = imageViewWeakReference.get().getContext();
            float scale = context.getResources().getDisplayMetrics().density;

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;

            Bitmap originBitmap = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.background, options);

            Bitmap workingBitmap = Bitmap.createBitmap(originBitmap);
            Bitmap result = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

            Canvas canvas = new Canvas(result);

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(context.getResources().getColor(android.R.color.holo_blue_dark));
            paint.setTextSize((int) (12 * scale));

            Rect textBounds = new Rect();
            paint.getTextBounds(text,0, text.length(), textBounds);

            int beginX = originBitmap.getWidth()/2 - (textBounds.width()/2);
            int beginY = originBitmap.getHeight()/2 + (textBounds.height()/2);

            canvas.drawText(text, beginX , beginY , paint);
            return result;
        }

    }

}
