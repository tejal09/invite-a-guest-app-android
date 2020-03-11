package com.blaxtation.inviteaguest;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;

import java.util.List;

public class galleryAdapter extends
        RecyclerView.Adapter<galleryAdapter.ViewHolder> {

    private List<String> imageList;
    private Context context;

    public galleryAdapter(List<String> list, Context ctx) {
        imageList = list;
        context = ctx;
    }
    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public galleryAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_image_view, parent, false);

        galleryAdapter.ViewHolder viewHolder =
                new galleryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(galleryAdapter.ViewHolder holder, int position) {
        final int itemPos = position;
        final String fileUrl = imageList.get(position);

        Glide.with(context)
                .load(fileUrl)
                .transition(GenericTransitionOptions
                .with(R.anim.fui_slide_in_right))
                .into(holder.imageView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.iv_photo);
        }
    }
}