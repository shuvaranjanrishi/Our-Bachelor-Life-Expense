package com.example.asus.bechelorlifeexpense.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asus.bechelorlifeexpense.R;
import com.example.asus.bechelorlifeexpense.database.MyDBHelper;
import com.example.asus.bechelorlifeexpense.fragment.ConsumerFragment;
import com.example.asus.bechelorlifeexpense.fragment.DashBoardFragment;
import com.example.asus.bechelorlifeexpense.fragment.ExpensesFragment;
import com.example.asus.bechelorlifeexpense.local_backup.LocalBackup;

import java.io.File;

import static com.example.asus.bechelorlifeexpense.fragment.ExpensesFragment.myDBHelper;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private LocalBackup localBackup;

    public static final int REQUEST_CODE_PERMISSIONS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setLogo(R.mipmap.bachelor_life_expense_logo);
        setTitle("Dash Board");

        replaceFragment(new DashBoardFragment());//set default fragment in dash board

        init();

        //navigation item selected action
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.dashBoardNavItemId:

                        replaceFragment(new DashBoardFragment());
                        setTitle("Dash Board");
                        return true;

                    case R.id.consumerNavItemId:

                        replaceFragment(new ConsumerFragment());
                        setTitle("Consumers");
                        return true;

                    case R.id.expensesNavItemId:

                        replaceFragment(new ExpensesFragment());
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

    //menu item action


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext());

        switch (item.getItemId()) {
            case R.id.createBackupItemId:
                String outFileName = Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name) + File.separator;
                localBackup.performBackup(myDBHelper, outFileName);
                break;

            case R.id.importDataItemId:
                localBackup.performRestore(myDBHelper);
                break;

            case R.id.clearAllDataItemId:

                showAlertDialog();

                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Are you sure to clear all data !");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqLiteDatabase,MyDBHelper.getDatabaseVersion(),MyDBHelper.getDatabaseVersion());
                Toast.makeText(MainActivity.this, "All data cleared\nplease restart your app", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();

    }

    //initialize all component
    private void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigationViewId);
        localBackup = new LocalBackup(this);
    }
}
