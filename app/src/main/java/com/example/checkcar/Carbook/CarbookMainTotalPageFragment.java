package com.example.checkcar.Carbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkcar.R;

public class CarbookMainTotalPageFragment extends Fragment {
    RecyclerView recyclerView_carbookMain_totalRecordList;
    CarbookMainTotalPageRecyclerViewAdapter carbookMainTotalPageRecyclerViewAdapter;

    public CarbookMainTotalPageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carbookmain_totalrecord, container, false);
        setView(view);
        setRecyclerView();
        return view;
    }

    public void setView(View view){
        recyclerView_carbookMain_totalRecordList = view.findViewById(R.id.recyclerView_carbookMain_totalRecordList);
    }

    public void setRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView_carbookMain_totalRecordList.setLayoutManager(linearLayoutManager);
        carbookMainTotalPageRecyclerViewAdapter = new CarbookMainTotalPageRecyclerViewAdapter();
        recyclerView_carbookMain_totalRecordList.setAdapter(carbookMainTotalPageRecyclerViewAdapter);
        carbookMainTotalPageRecyclerViewAdapter.notifyDataSetChanged();
    }
}
