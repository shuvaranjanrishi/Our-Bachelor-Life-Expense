package com.example.asus.bechelorlifeexpense.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.asus.bechelorlifeexpense.R;
import com.example.asus.bechelorlifeexpense.fragment.DashBoardFragment;
import com.example.asus.bechelorlifeexpense.fragment.ExpensesFragment;

public class MainActivity extends AppCompatActivity {

    private int mark = 0;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Dash Board");

        replaceFragment(new DashBoardFragment());//set default fragment in dash board

        init();

        //navigation item selected action
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.dashBoardNavItemId:

                        if(mark == 1){
                            replaceFragment(new DashBoardFragment());
                            mark--;
                        }
                        setTitle("Dash Board");
                        return true;

                    case R.id.expensesNavItemId:

                        if(mark == 0){
                            replaceFragment(new ExpensesFragment());
                            mark++;
                        }
                        setTitle("Expenses");
                        return true;
                }
                return false;
            }
        });

    }

    //change fragment when nav item selected
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId,fragment);
        fragmentTransaction.commit();
    }

    //initialize all component
    private void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigationViewId);
    }
}