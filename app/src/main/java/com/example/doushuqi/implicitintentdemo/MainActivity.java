package com.example.doushuqi.implicitintentdemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button alarmClock, camera, calendar, contracts, email, fileStorage, maps, musicOrVideo,
            phone, settings, textMessaging, webBrowser;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private static final int REQUEST_SELECT_CONTACT = 2;
    private static final Uri mLocationForPhotos = Uri.parse("file:///sdcard/");
    private Uri contactUri;

    private HandleContacts mHandleContacts = new HandleContacts(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmClock = (Button) findViewById(R.id.action_alarm_clock);
        alarmClock.setOnClickListener(this);
        calendar = (Button) findViewById(R.id.action_calendar);
        calendar.setOnClickListener(this);
        camera = (Button) findViewById(R.id.action_camera);
        camera.setOnClickListener(this);
        contracts = (Button) findViewById(R.id.action_contacts);
        contracts.setOnClickListener(this);
        email = (Button) findViewById(R.id.action_email);
        email.setOnClickListener(this);
        fileStorage = (Button) findViewById(R.id.action_file_storage);
        fileStorage.setOnClickListener(this);
        maps = (Button) findViewById(R.id.action_maps);
        maps.setOnClickListener(this);
        musicOrVideo = (Button) findViewById(R.id.action_music_or_video);
        musicOrVideo.setOnClickListener(this);
        phone = (Button) findViewById(R.id.action_phone);
        phone.setOnClickListener(this);
        settings = (Button) findViewById(R.id.action_setting);
        settings.setOnClickListener(this);
        textMessaging = (Button) findViewById(R.id.action_text_messaging);
        textMessaging.setOnClickListener(this);
        webBrowser = (Button) findViewById(R.id.action_web_browser);
        webBrowser.setOnClickListener(this);
    }


    private boolean hasActivityToStart(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            return true;
        } else return false;
    }

    private void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        intent.putExtra(AlarmClock.EXTRA_VIBRATE, false);
        hasActivityToStart(intent);
    }

    private void startTimer(String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        hasActivityToStart(intent);
    }

    private void showAllAlarm() {
        Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        hasActivityToStart(intent);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void addEvent(String title, String location, Calendar begin, Calendar end) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        hasActivityToStart(intent);

    }

    private void capturePhoto(String targetFilename) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.withAppendedPath(mLocationForPhotos, targetFilename));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bitmap thumbnail = data.getParcelableExtra("data");
//            int height = thumbnail.getHeight();
//            int width= thumbnail.getWidth();
//            Log.e("ddd", "height = " + height + "  width = " + width);
        }
        if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {
            contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                Log.e("ddd", "number  " + number);
            }
        }
//
//        if(requestCode == HandleFile.REQUEST_IMAGE_GET) {
//            Bitmap thumbail = data.getParcela
//
//        }
    }

    public void  composeEmail(String[] address, String subject, Uri attachment) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
//        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        hasActivityToStart(intent);
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        hasActivityToStart(intent);
    }

    public void playMedia(Uri file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(file);
//        intent.setType("audio/*");
//        intent.setType("application/ogg");
        hasActivityToStart(intent);
    }

    public void dialPhoenNumber (String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        hasActivityToStart(intent);
    }

    public void openWifiSettings() {
        Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        hasActivityToStart(intent);
    }

    public void composeMessage(String message, Uri attachment) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"));// This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
//        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        hasActivityToStart(intent);
    }

    public void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        hasActivityToStart(intent);
    }

    public void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        hasActivityToStart(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_alarm_clock:
                createAlarm("create a alarm", 8, 50);
                startTimer("start a timer", 60);
                showAllAlarm();
                break;
            case R.id.action_calendar:
                addEvent("test", "beijing", Calendar.getInstance(), Calendar.getInstance());
                break;
            case R.id.action_camera:
                capturePhoto("111.jpg");
                break;
            case R.id.action_contacts:
                mHandleContacts.selectContact();

                break;
            case R.id.action_email:
                composeEmail(new String[]{"ss@ff.com", "dd@ff.com"}, "test");
                break;
            case R.id.action_file_storage:

                break;
            case R.id.action_maps:
                Uri geoLocation = Uri.parse("geo:39.940409,116.355257");
                showMap(geoLocation);
                break;
            case R.id.action_music_or_video:
                Uri file = Uri.parse("file:///storage/sdcard0/audio.mp3");
                playMedia(file);
                break;
            case R.id.action_phone:
                dialPhoenNumber("1123455332");
                break;
            case R.id.action_setting:
                openWifiSettings();
                break;
            case R.id.action_text_messaging:
                composeMessage("send a sms test", null);
                break;
            case R.id.action_web_browser:
//                openWebPage("http://www.baidu.com");
                searchWeb("北京邮电大学");
                break;
        }
    }
}
