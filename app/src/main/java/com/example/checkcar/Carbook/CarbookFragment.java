package com.example.checkcar.Carbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.checkcar.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class CarbookFragment extends Fragment {

    TabLayout tabLayout_carbookMain;
    ViewPager2 viewPager2_carbookMain;

    public CarbookFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carbookmain, container, false);
        setView(view);
        setTabLayout();
        return view;
    }

    public void setView(View view){
        tabLayout_carbookMain = view.findViewById(R.id.tabLayout_carbookMain);
        viewPager2_carbookMain = view.findViewById(R.id.viewPager2_carbookMain);
    }

    public void setTabLayout(){
        ArrayList<String> tabLayout_carbookMain_tabNameList = new ArrayList<>();
        tabLayout_carbookMain_tabNameList.add(requireContext().getResources().getString(R.string.tabItem_carbookMain_total));
        tabLayout_carbookMain_tabNameList.add(requireContext().getResources().getString(R.string.tabItem_carbookMain_maintenanceOthers));
        tabLayout_carbookMain_tabNameList.add(requireContext().getResources().getString(R.string.tabItem_carbookMain_fueling));
        viewPager2_carbookMain.setAdapter(new CarbookMainViewPagerAdapter(requireActivity(), tabLayout_carbookMain_tabNameList.size()));
        new TabLayoutMediator(tabLayout_carbookMain, viewPager2_carbookMain, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabLayout_carbookMain_tabNameList.get(position));
            }
        }).attach();
    }
}