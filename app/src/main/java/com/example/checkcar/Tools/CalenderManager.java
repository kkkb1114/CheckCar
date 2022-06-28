package com.example.checkcar.Tools;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalenderManager {

    // 요일 구하기
    public String getDayOfTheWeek(Date mDate) {
        String DayOfTheWeek = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int DayOfTheWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
        switch (DayOfTheWeekNum) {
            case 1:
                DayOfTheWeek = " (일)";
                break;
            case 2:
                DayOfTheWeek = " (월)";
                break;
            case 3:
                DayOfTheWeek = " (화)";
                break;
            case 4:
                DayOfTheWeek = " (수)";
                break;
            case 5:
                DayOfTheWeek = " (목)";
                break;
            case 6:
                DayOfTheWeek = " (금)";
                break;
            case 7:
                DayOfTheWeek = " (토)";
                break;
        }
        return DayOfTheWeek;
    }

    // date가 null이면 현재 시간으로 date를 생성해 세팅하며 null이 아니면 들어온 date값으로 세팅한다.
    public String getDateFormat(Date date, String type) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(type);
        return mSimpleDateFormat.format(date);
    }
}
