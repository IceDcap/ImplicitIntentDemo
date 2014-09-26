package com.example.doushuqi.implicitintentdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by administrator on 14-9-26.
 */
public class HandleFile {
    private Context mContext;
    private Activity mActivity;
    static final int REQUEST_IMAGE_GET = 3;

    public HandleFile(Context context, Activity activity) {
        mActivity = activity;
        mContext = context;
    }

    private boolean hasActivityToStart(Intent intent) {
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mActivity.startActivityForResult(intent, REQUEST_IMAGE_GET);
            return true;
        } else return false;
    }

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        hasActivityToStart(intent);
    }

}
