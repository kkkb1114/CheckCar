package com.example.checkcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkcar.Carbook.CarbookFragment;
import com.example.checkcar.Monitoring.MonitoringFragment;
import com.example.checkcar.Setting.SettingFragment;
import com.example.checkcar.Tools.CalenderManager;
import com.example.checkcar.Tools.DialogManager;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    NavigationBarView bottomNavigationView_activityMain;
    ImageView imageView_actionbarCarbook_searchButton;
    ConstraintLayout constraintLayout_actionbarCarbook_selectDate;
    TextView textView_actionbarCarbook_date;
    long timeNow;
    Date date;
    CalenderManager calenderManager = new CalenderManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        setView();
        setDateData();
        setBottomNavigationView();
        setActionbarDate();
        dateHandler();
    }

    public void setView() {
        bottomNavigationView_activityMain = findViewById(R.id.bottomNavigationView_activityMain);
        imageView_actionbarCarbook_searchButton = findViewById(R.id.imageView_actionbarCarbook_searchButton);
        constraintLayout_actionbarCarbook_selectDate = findViewById(R.id.constraintLayout_actionbarCarbook_selectDate);
        textView_actionbarCarbook_date = findViewById(R.id.textView_actionbarCarbook_date);

        constraintLayout_actionbarCarbook_selectDate.setOnClickListener(this);
        imageView_actionbarCarbook_searchButton.setOnClickListener(this);
    }

    public void setBottomNavigationView() {
        MonitoringFragment monitoringFragment = new MonitoringFragment();
        CarbookFragment carbookFragment = new CarbookFragment();
        SettingFragment settingFragment = new SettingFragment();

        // 초기화면 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_activityMain, monitoringFragment).commit();

        bottomNavigationView_activityMain.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_activityMain, monitoringFragment).commit();
                        return true;
                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_activityMain, carbookFragment).commit();
                        return true;
                    case R.id.info:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_activityMain, settingFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

        switch (view.getId()) {
            case R.id.imageView_actionbarCarbook_searchButton:
                toast.setText("검색");
                toast.show();
                break;
            case R.id.constraintLayout_actionbarCarbook_selectDate:
                DialogManager.calenderDialog calenderDialog = new DialogManager.calenderDialog(context, date);
                calenderDialog.show();
                break;
        }
    }

    public void setActionbarDate() {
        //todo db 만들면 마지막 데이터를 기준으로 날짜를 지정하고 없을 경우 오늘 날짜로 예외처리 해야한다.
        textView_actionbarCarbook_date.setText(calenderManager.getDateFormat(date, "yyyy년MM월"));
    }


    public void setDateData(){
        // 처음 시간 세팅
        timeNow = System.currentTimeMillis();
        date = new Date(timeNow);
    }

    public static Handler dateHandler = null;

    private void dateHandler() {
        try {
            dateHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message message) {
                    try {
                        switch(message.what){
                            case 1:
                                date = (Date) message.obj;
                                textView_actionbarCarbook_date.setText(calenderManager.getDateFormat(date, "yyyy년MM월"));
                                break;
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}