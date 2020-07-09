package com.example.photocollectionapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photocollectionapp.R;
import com.example.photocollectionapp.fragment.ImageData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    private Context context;
    private List<ImageData> imageDataList;

    public ImageAdapter(Context context , List<ImageData> imageDataList) {
        this.context = context;
        this.imageDataList = imageDataList;
    }

    @NonNull
    @Override
    public ImageAdapter.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imgdataview , parent , false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageHolder holder, int position) {
        String imgDes = imageDataList.get(position).getImgDes();
        String imgUrl = imageDataList.get(position).getImgUrl();

        holder.textView.setText(imgDes);

        Glide.with(context).load(imgUrl).into(holder.imageView);
        //Picasso.get().load(imgUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageDataList.size();
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            textView = itemView.findViewById(R.id.txtView);

        }
    }
}
