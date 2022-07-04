package com.example.checkcar.Carbook;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.checkcar.CarbookMainRecyclerViewAdapter;

public class CarbookMainViewPagerAdapter extends FragmentStateAdapter {
    int pageNum;

    public CarbookMainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int pageNum){
        super(fragmentActivity);
        this.pageNum = pageNum;
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new CarbookMainTotalPageFragment();
            case 1:
                return new CarbookMainMaintenanceOthersPageFragment();
            case 2:
                return new CarbookMainFuelingPageFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return pageNum;
    }
}
