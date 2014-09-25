package com.example.doushuqi.implicitintentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{
    private Button alarmClock, camera, calendar, contracts, email, fileStorage, maps, musicOrVideo,
    phone, settings, textMessaging, webBrowser;
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
        fileStorage = (Button)findViewById(R.id.action_file_storage);
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

    @Override
    public void onClick(View v) {

    }
}
