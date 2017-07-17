package com.example.ravi.insertsqlite;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Ravi on 16-07-2017.
 */

public class Message {

    public static void message(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
