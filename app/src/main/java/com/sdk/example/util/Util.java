package com.sdk.example.util;

import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class Util {

    public static void dialogResult(Activity context, String msg){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("OTC-DEMO")
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, (dialog1, which) -> dialog1.dismiss())
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
        TextView textView = dialog.findViewById(android.R.id.message);
        textView.setScroller(new Scroller(context));
        textView.setVerticalScrollBarEnabled(true);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

}
