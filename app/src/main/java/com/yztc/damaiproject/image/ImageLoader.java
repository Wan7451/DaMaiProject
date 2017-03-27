package com.yztc.damaiproject.image;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yztc.damaiproject.R;

/**
 * Created by wanggang on 2017/3/17.
 */

public class ImageLoader {


    public static void load(Context context, String path, ImageView view) {
        Glide.with(context)
                .load(path)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(view);
    }


    public static void load(Fragment context, String path, ImageView view) {
        Glide.with(context)
                .load(path)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(view);
    }

    public static void load(Activity context, String path, ImageView view) {
        Glide.with(context)
                .load(path)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(view);
    }


}
