package com.example.doushuqi.implicitintentdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by administrator on 14-9-26.
 */
public class HandleContacts {
    private Context mContext;
    private Activity mActivity;
    static final int REQUEST_SELECT_CONTACT = 2;

    public HandleContacts(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
    }

    public void selectContact() {
        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        //如果只是检索一种类型的contact用ContactsContract.CommonDataKinds 会更效率
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mActivity.startActivityForResult(intent, REQUEST_SELECT_CONTACT);
        }
    }

    public void viewContact(Uri contactUri) {
        Log.e("ddd", "*****************");
        Intent intent = new Intent(Intent.ACTION_VIEW, contactUri);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mActivity.startActivity(intent);
            Log.e("ddd", "-------------------");
        }

    }

    public void editContact(Uri contactUri, String email) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(contactUri);
        intent.putExtra(Contacts.Intents.Insert.EMAIL, email);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mActivity.startActivity(intent);
        }
    }

    public void insertContact(String name, String email) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mActivity.startActivity(intent);
        }
    }

}
