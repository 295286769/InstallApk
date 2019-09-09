package com.sharkgulf.checkandinstallapk.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sharkgulf.checkandinstallapk.R;
import com.sharkgulf.checkandinstallapk.aplication.UpdateAplication;


public class ToasterManager {

    private static Toast mToast;

    public static void showToast(String message) {
        TextView title = intToast();
        if (null != mToast && title != null) {
            title.setText(message);
            mToast.show();
        }
    }

    public static void showShortToast(String message) {
        TextView title = intToast();
        if (null != mToast && title != null) {
            mToast.setDuration(Toast.LENGTH_SHORT);
            title.setText(message);
            mToast.show();
        }
    }


    public static void showLongToast(String message) {
        TextView title = intToast();
        if (null != mToast && title != null) {
            mToast.setDuration(Toast.LENGTH_LONG);
            title.setText(message);
            mToast.show();
        }
    }






    private static TextView intToast() {
        if (mToast == null) {
            mToast = new Toast(UpdateAplication.Companion.getContext());
        }
        LayoutInflater inflate = (LayoutInflater)
                UpdateAplication.Companion.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflate.inflate(R.layout.checkapk_toast, null);
        TextView title = (TextView) layout.findViewById(R.id.message_textview);
        mToast.setDuration(Toast.LENGTH_SHORT);
//        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setGravity(Gravity.BOTTOM, 0, ScreenUtil.Companion.dpToPx( UpdateAplication.Companion.getContext(),50));
        mToast.setView(layout);
        return title;
    }

    public void destroy() {
        mToast = null;
        System.gc();
    }
}
