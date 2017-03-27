package com.yztc.damaiproject.utils;

import android.widget.Toast;

import com.yztc.damaiproject.application.App;

/**
 * Created by wanggang on 2017/3/17.
 */

public class ToastUtils {


    private static Toast toast;

    public static void toast(String massage){

        if(toast==null){
            toast=Toast.makeText(App.getContext(),"",Toast.LENGTH_SHORT);
        }
        toast.setText(massage);
        toast.show();
    }

}
