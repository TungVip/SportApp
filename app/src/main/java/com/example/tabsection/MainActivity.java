package com.example.tabsection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    fragment1 fragment1Action;
    fragment2 fragment2Action;
    fragment3 fragment3Action;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Spinner spinner;
    ArrayList<String> names;
    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager= findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        vpAdapter.addFragment(new fragment1(), "LEAGUE");
        vpAdapter.addFragment(new fragment2(), "MATCH");
        vpAdapter.addFragment(new fragment3(), "FAVORITE");
        viewPager.setAdapter(vpAdapter);

        spinner = findViewById(R.id.spinner);
        fragment1Action = new fragment1();
        fragment2Action = new fragment2();
        fragment3Action = new fragment3();
        names = new ArrayList<>();
        names.add("Premier League");
        names.add("Bundesliga");
        names.add("Serie A");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.item,names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        selectFragment(fragment1Action);
                        break;
                    case 1:
                        selectFragment(fragment2Action);
                        break;
                    case 2:
                        selectFragment(fragment3Action);
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

}