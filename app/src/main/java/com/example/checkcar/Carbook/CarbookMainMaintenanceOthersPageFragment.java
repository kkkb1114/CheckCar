package com.example.checkcar.Carbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.checkcar.R;

public class CarbookMainMaintenanceOthersPageFragment extends Fragment implements View.OnClickListener{

    TextView tv_test;

    public CarbookMainMaintenanceOthersPageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carbookmain_maintenanceothers, container, false);
        setView(view);
        return view;
    }

    public void setView(View view){
        tv_test = view.findViewById(R.id.tv_test);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_test:

                break;

        }
    }
}
