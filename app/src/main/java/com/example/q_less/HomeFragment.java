package com.example.q_less;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvStalls;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvStalls = view.findViewById(R.id.rvStalls);
        rvStalls.setLayoutManager(new GridLayoutManager(getContext(), 2));
        
        List<Stall> stallList = new ArrayList<>();
        stallList.add(new Stall(getString(R.string.stall_jaiswal), R.drawable.jaiswal_canteen));
        stallList.add(new Stall(getString(R.string.stall_tea_post), R.drawable.tea_post));
        stallList.add(new Stall(getString(R.string.stall_shiv_snacks), R.drawable.shiv_snacks));
        stallList.add(new Stall(getString(R.string.stall_indu_cafe), R.drawable.indu_cafe));
        stallList.add(new Stall(getString(R.string.stall_nestle), R.drawable.nestle));

        StallAdapter adapter = new StallAdapter(stallList, stall -> {
            Intent intent = new Intent(getActivity(), MenuActivity.class);
            intent.putExtra("stall", stall);
            startActivity(intent);
        });
        rvStalls.setAdapter(adapter);

        return view;
    }
}