package com.example.checkcar.Tools;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public abstract class BaseDialog extends Dialog {

    Context context;

    public BaseDialog(@NonNull Context context, int layoutId) {
        super(context);
        this.context = context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layoutId);

        // 뒤로가기나 키 배경 터치시 종료 설정
        setCancelable(true);
        // 화면 밖 터치시 종료 설정
        setCanceledOnTouchOutside(false);

        Window window = getWindow();
        if(window != null){
            // 백그라운드 투명
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams layoutParams = window.getAttributes();
            // 화면에 가득 차도록
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }

        try{
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            this.getWindow().setNavigationBarColor(Color.TRANSPARENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isShowing = false;
    @Override
    public void show() {
        try{
            if(!isShowing){
                isShowing = true;
                super.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        isShowing = false;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isShowing = false;
    }
}
