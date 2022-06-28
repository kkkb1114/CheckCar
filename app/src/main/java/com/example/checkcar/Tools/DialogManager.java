package com.example.checkcar.Tools;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.checkcar.MainActivity;
import com.example.checkcar.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.util.Calendar;
import java.util.Date;

public class DialogManager extends AlertDialog.Builder {

    Context context;

    public DialogManager(Context context) {
        super(context);
        this.context = context;
    }

    public static class calenderDialog extends BaseDialog implements OnDateSelectedListener {
        TextView textView_dialogCalendar_year;
        TextView textView_dialogCalendar_date;
        MaterialCalendarView materialCalendarView_dialogCalendar;
        Button button_dialogCalendar_confirm;
        Button button_dialogCalendar_cancel;
        Date dbDate;
        Date selectDate;
        CalenderManager calenderManager = new CalenderManager();

        //todo date는 db 만들면 마지막 데이터를 기준으로 날짜를 지정하고 없을 경우 오늘 날짜로 예외처리 해야한다.
        public calenderDialog(@NonNull Context context, Date dbDate) {
            super(context, R.layout.dialog_calendar);
            this.dbDate = dbDate;
        }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setView();
            setTextDate(getYear(dbDate), getDate(dbDate));
            setMaterialCalendarView();
            onClick_button_dialogCalendar_confirm();
            onClick_button_dialogCalendar_cancel();
        }

        @Override
        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
            selectDate = date.getDate();
            textView_dialogCalendar_year.setText(calenderManager.getDateFormat(selectDate, "yyyy년"));
            textView_dialogCalendar_date.setText(calenderManager.getDateFormat(selectDate, "MM월dd일") + calenderManager.getDayOfTheWeek(selectDate));
        }

        public void setView() {
            textView_dialogCalendar_year = findViewById(R.id.textView_dialogCalendar_year);
            textView_dialogCalendar_date = findViewById(R.id.textView_dialogCalendar_date);
            materialCalendarView_dialogCalendar = findViewById(R.id.materialCalendarView_dialogCalendar);
            button_dialogCalendar_confirm = findViewById(R.id.button_dialogCalendar_confirm);
            button_dialogCalendar_cancel = findViewById(R.id.button_dialogCalendar_cancel);
        }

        public void setTextDate(String year, String date) {
            textView_dialogCalendar_year.setText(year);
            textView_dialogCalendar_date.setText(date);
        }

        public String getYear(Date dbDate) {
            return calenderManager.getDateFormat(dbDate, "yyyy년");
        }

        public String getDate(Date dbDate) {
            return calenderManager.getDateFormat(dbDate, "MM월dd일") + calenderManager.getDayOfTheWeek(dbDate);
        }

        //todo 여기도 db데이터가 있으면 db 데이터 기준으로 date를 넣고 아니면 현재 시간으로 세팅해야한다.
        public void setMaterialCalendarView() {
            // 받아온 dbDate를 기준으로 달력 페이지 세팅
            materialCalendarView_dialogCalendar.setCurrentDate(dbDate);
            // 기본값은 표시됨 을 호출하여 상단 표시줄(화살표 버튼 및 월 레이블)을 숨기거나 표시할 수 있습니다.
            materialCalendarView_dialogCalendar.setTopbarVisible(true);
            // 설명을 보긴했는데 잘 이해가 안가서 다시 검색해서 보는게 좋을 듯 하다. (일단 완성이 급하니 작업 진행하자)
            materialCalendarView_dialogCalendar.setOnDateChangedListener(this);
            materialCalendarView_dialogCalendar.addDecorator(new TodayDecorator());
            materialCalendarView_dialogCalendar.addDecorator(new SaturdayDecorator());
            materialCalendarView_dialogCalendar.addDecorator(new SundayDecorator());
            materialCalendarView_dialogCalendar.setTitleFormatter(new MonthArrayTitleFormatter(context.getResources().getTextArray(R.array.MaterialCalendarView_customMonths)));
            materialCalendarView_dialogCalendar.setWeekDayFormatter(new ArrayWeekDayFormatter(context.getResources().getTextArray(R.array.MaterialCalendarView_customWeekdays)));
        }

        // 오늘 날짜 도트 찍고 텍스트 굵게 설정
        public class TodayDecorator implements DayViewDecorator {
            private final Calendar calendar = Calendar.getInstance();
            private CalendarDay calendarDay;

            public TodayDecorator() {
                calendarDay = CalendarDay.today();
                calendarDay = CalendarDay.from(calendar);
            }

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                day.copyTo(calendar);
                return calendarDay != null && day.equals(calendarDay);
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(Color.CYAN));
                view.addSpan(new StyleSpan(Typeface.BOLD));
                view.addSpan(new RelativeSizeSpan(1.4f));
            }
        }

        public class SaturdayDecorator implements DayViewDecorator {
            private final Calendar calendar = Calendar.getInstance();

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                day.copyTo(calendar);
                int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
                return weekDay == Calendar.SATURDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(Color.BLUE));
            }
        }

        public class SundayDecorator implements DayViewDecorator {
            private final Calendar calendar = Calendar.getInstance();

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                day.copyTo(calendar);
                int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
                return weekDay == Calendar.SUNDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(Color.RED));
            }
        }

        public class SelectDecorator implements DayViewDecorator {
            private final Calendar calendar = Calendar.getInstance();

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                day.copyTo(calendar);
                int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
                return weekDay == Calendar.SATURDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {

            }
        }

        public void onClick_button_dialogCalendar_confirm() {
            button_dialogCalendar_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.dateHandler.obtainMessage(1, selectDate).sendToTarget();
                    dismiss();
                }
            });
        }

        public void onClick_button_dialogCalendar_cancel() {
            button_dialogCalendar_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }
    }
}