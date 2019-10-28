package com.example.asus.bechelorlifeexpense.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.bechelorlifeexpense.R;
import com.example.asus.bechelorlifeexpense.activity.AddDailyExpenseActivity;
import com.example.asus.bechelorlifeexpense.adapter.ExpenseAdapter;
import com.example.asus.bechelorlifeexpense.database.MyDBHelper;
import com.example.asus.bechelorlifeexpense.model_class.Expense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpensesFragment extends Fragment {

    public static MyDBHelper myDBHelper;
    private ExpenseAdapter expenseAdapter;

    private Spinner expenseTypeSpinner,spenderSpinner;
    private String[] expenseTypeSpinnerList,spenderSpinnerList;
    private ArrayAdapter<String> expenseTypeSpinnerAdapter,spenderSpinnerAdapter;

    private TextView fromDateTV,toDateTV;
    private ImageView fromDateIV,toDateIV;

    private DatePickerDialog.OnDateSetListener mFromDateSetListener;
    private DatePickerDialog.OnDateSetListener mToDateSetListener;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private List<Expense> expenseList;

    private String fromDate;

    public ExpensesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_expenses, container, false);

        init(view);

        getFromDate();

        getToDate();

        getData();

        populateDataToRecyclerView();

        //show expenses based on expense type spinner selected item
        expenseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedSpender = spenderSpinner.getSelectedItem().toString();

                switch (selectedSpender){
                    case "Select Spender":
                        switch (position){
                            case 0:
                                getData();
                                populateDataToRecyclerView();
                                break;
                            case 1:
                                setDataAccordingToExpenseType("Electricity Bill");
                                break;
                            case 2:
                                setDataAccordingToExpenseType("Transport Cost");
                                break;
                            case 3:
                                setDataAccordingToExpenseType("Medical Cost");
                                break;
                            case 4:
                                setDataAccordingToExpenseType("Breakfast");
                                break;
                            case 5:
                                setDataAccordingToExpenseType("Lunch");
                                break;
                            case 6:
                                setDataAccordingToExpenseType("Dinner");
                                break;
                            case 7:
                                setDataAccordingToExpenseType("Others");
                                break;
                        }
                        break;
                    case "Shuvo":
                        switch (position){
                            case 0:
                                setDataAccordingToSpender("Shuvo");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Electricity Bill","Shuvo");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Transport Cost","Shuvo");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Medical Cost","Shuvo");
                                break;
                            case 4:
                                setDataAccordingToTypeAndSpender("Breakfast","Shuvo");
                                break;
                            case 5:
                                setDataAccordingToTypeAndSpender("Lunch","Shuvo");
                                break;
                            case 6:
                                setDataAccordingToTypeAndSpender("Dinner","Shuvo");
                                break;
                            case 7:
                                setDataAccordingToTypeAndSpender("Others","Shuvo");
                                break;
                        }
                        break;
                    case "Jewel":
                        switch (position){
                            case 0:
                                setDataAccordingToSpender("Jewel");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Electricity Bill","Jewel");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Transport Cost","Jewel");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Medical Cost","Jewel");
                                break;
                            case 4:
                                setDataAccordingToTypeAndSpender("Breakfast","Jewel");
                                break;
                            case 5:
                                setDataAccordingToTypeAndSpender("Lunch","Jewel");
                                break;
                            case 6:
                                setDataAccordingToTypeAndSpender("Dinner","Jewel");
                                break;
                            case 7:
                                setDataAccordingToTypeAndSpender("Others","Jewel");
                                break;
                        }
                        break;
                    case "Debesh":
                        switch (position){
                            case 0:
                                setDataAccordingToSpender("Debesh");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Electricity Bill","Debesh");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Transport Cost","Debesh");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Medical Cost","Debesh");
                                break;
                            case 4:
                                setDataAccordingToTypeAndSpender("Breakfast","Debesh");
                                break;
                            case 5:
                                setDataAccordingToTypeAndSpender("Lunch","Debesh");
                                break;
                            case 6:
                                setDataAccordingToTypeAndSpender("Dinner","Debesh");
                                break;
                            case 7:
                                setDataAccordingToTypeAndSpender("Others","Debesh");
                                break;
                        }
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //show expenses based on spender spinner selected item
        spenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedType = expenseTypeSpinner.getSelectedItem().toString();

                switch (selectedType){
                    case "Select Expense Type":
                        switch (position){
                            case 0:
                                getData();
                                populateDataToRecyclerView();
                                break;
                            case 1:
                                setDataAccordingToSpender("Shuvo");
                                break;
                            case 2:
                                setDataAccordingToSpender("Jewel");
                                break;
                            case 3:
                                setDataAccordingToSpender("Debesh");
                                break;
                        }
                        break;
                    case "Electricity Bill":
                        switch (position){
                            case 0:
                                setDataAccordingToExpenseType("Electricity Bill");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Electricity Bill","Shuvo");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Electricity Bill","Jewel");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Electricity Bill","Debesh");
                                break;
                        }
                        break;
                    case "Transport Cost":
                        switch (position){
                            case 0:
                                setDataAccordingToExpenseType("Transport Cost");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Transport Cost","Shuvo");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Transport Cost","Jewel");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Transport Cost","Debesh");
                                break;
                        }
                        break;
                    case "Medical Cost":
                        switch (position){
                            case 0:
                                setDataAccordingToExpenseType("Medical Cost");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Medical Cost","Shuvo");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Medical Cost","Jewel");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Medical Cost","Debesh");
                                break;
                        }
                        break;
                    case "Breakfast":
                        switch (position){
                            case 0:
                                setDataAccordingToExpenseType("Breakfast");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Breakfast","Shuvo");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Breakfast","Jewel");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Breakfast","Debesh");
                                break;
                        }
                        break;
                    case "Lunch":
                        switch (position){
                            case 0:
                                setDataAccordingToExpenseType("Lunch");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Lunch","Shuvo");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Lunch","Jewel");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Lunch","Debesh");
                                break;
                        }
                        break;
                    case "Dinner":
                        switch (position){
                            case 0:
                                setDataAccordingToExpenseType("Dinner");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Dinner","Shuvo");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Dinner","Jewel");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Dinner","Debesh");
                                break;
                        }
                        break;
                    case "Others":
                        switch (position){
                            case 0:
                                setDataAccordingToExpenseType("Others");
                                break;
                            case 1:
                                setDataAccordingToTypeAndSpender("Others","Shuvo");
                                break;
                            case 2:
                                setDataAccordingToTypeAndSpender("Others","Jewel");
                                break;
                            case 3:
                                setDataAccordingToTypeAndSpender("Others","Debesh");
                                break;
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //floating action button actions here to add new expense
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddDailyExpenseActivity.class);
                startActivityForResult(intent,100);
            }
        });

        //hide fab icon on scroll up
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    fab.hide();
                else if (dy < 0)
                    fab.show();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        return view;
    }

    private void setDataAccordingToTypeAndSpender(String expType, String spnName) {
        expenseList.clear();
        Cursor cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_type = '"+expType+"' AND spender_name = '"+spnName+"' ");
        while (cursor.moveToNext()) {
            String expenseId = cursor.getString(0);
            String expenseType = cursor.getString(1);
            String expenseAmount = cursor.getString(2);
            String expenseDate = cursor.getString(3);
            String expenseTime = cursor.getString(4);
            String spenderName = cursor.getString(5);
            String consumerName = cursor.getString(6);
            expenseList.add(new Expense(expenseId,expenseType,expenseAmount,expenseDate,expenseTime,spenderName,consumerName));
        }
        populateDataToRecyclerView();
    }

    private void setDataAccordingToExpenseType(String expType) {
        expenseList.clear();
        Cursor cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_type = '"+expType+"'");
        while (cursor.moveToNext()) {
            String expenseId = cursor.getString(0);
            String expenseType = cursor.getString(1);
            String expenseAmount = cursor.getString(2);
            String expenseDate = cursor.getString(3);
            String expenseTime = cursor.getString(4);
            String spenderName = cursor.getString(5);
            String consumerName = cursor.getString(6);
            expenseList.add(new Expense(expenseId,expenseType,expenseAmount,expenseDate,expenseTime,spenderName,consumerName));
        }
        populateDataToRecyclerView();
    }

    private void setDataAccordingToSpender(String spnName) {
        expenseList.clear();
        Cursor cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE spender_name = '"+spnName+"'");
        while (cursor.moveToNext()) {
            String expenseId = cursor.getString(0);
            String expenseType = cursor.getString(1);
            String expenseAmount = cursor.getString(2);
            String expenseDate = cursor.getString(3);
            String expenseTime = cursor.getString(4);
            String spenderName = cursor.getString(5);
            String consumerName = cursor.getString(6);
            expenseList.add(new Expense(expenseId,expenseType,expenseAmount,expenseDate,expenseTime,spenderName,consumerName));
        }
        populateDataToRecyclerView();
    }

    private void setData(Cursor cursor) {
        expenseList.clear();
        while (cursor.moveToNext()) {
            String expenseId = cursor.getString(0);
            String expenseType = cursor.getString(1);
            String expenseAmount = cursor.getString(2);
            String expenseDate = cursor.getString(3);
            String expenseTime = cursor.getString(4);
            String spenderName = cursor.getString(5);
            String consumerName = cursor.getString(6);
            expenseList.add(new Expense(expenseId,expenseType,expenseAmount,expenseDate,expenseTime,spenderName,consumerName));
        }
        populateDataToRecyclerView();
    }

    //refresh data after adding
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 100 && resultCode == getActivity().RESULT_OK){
            getData();
            populateDataToRecyclerView();
            Toast.makeText(getActivity(), "Data saved Successfully", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //set all data to recycler view
    public void populateDataToRecyclerView() {
        expenseAdapter = new ExpenseAdapter(expenseList,getActivity());
        expenseAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(expenseAdapter);
    }

    //getDataFromDatabase
    public void getData() {
        expenseList.clear();
        Cursor cursor = myDBHelper.getDataFromDatabase();

        while (cursor.moveToNext()) {

            String expenseId = cursor.getString(0);
            String expenseType = cursor.getString(1);
            String expenseAmount = cursor.getString(2);
            String expenseDate = cursor.getString(3);
            String expenseTime = cursor.getString(4);
            String spenderName = cursor.getString(5);
            String consumerName = cursor.getString(6);

            expenseList.add(new Expense(expenseId,expenseType,expenseAmount,expenseDate,expenseTime,spenderName,consumerName));
        }
    }

    //set date to fromDate TextView by clicking from date icon
    private void getFromDate() {

        fromDateIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mFromDateSetListener,
                        year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.setTitle("Please select date");
                datePickerDialog.show();
            }
        });

        mFromDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                fromDate = dayOfMonth + "/" + month + "/" + year;
                fromDateTV.setText(fromDate);
            }
        };
    }

    //set date to toDate TextView by clicking to date icon
    private void getToDate() {

            toDateIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                getActivity(),
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                mToDateSetListener,
                                year, month, day
                        );

                        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        datePickerDialog.setTitle("Please select date");
                        datePickerDialog.show();

                }
            });

            mToDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    String toDate = dayOfMonth + "/" + month + "/" + year;
                    toDateTV.setText(toDate);
                    setDataAccordingToDate(toDate);
                }
            };
    }

    private void setDataAccordingToDate(String toDate) {

        String selectedItem = expenseTypeSpinner.getSelectedItem().toString();
        Cursor cursor;

        switch (selectedItem){
            case "Select Expense Type":
                cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                setData(cursor);
                break;

            case "Electricity Bill":
                cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_type = 'Electricity Bill' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                setData(cursor);
                break;

            case "Transport Cost":
                cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_type = 'Transport Cost' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                setData(cursor);
                break;

            case "Medical Cost":
                cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_type = 'Medical Cost' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                setData(cursor);
                break;

            case "Breakfast":
                cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_type = 'Breakfast' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                setData(cursor);
                break;

            case "Lunch":
                cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_type = 'Lunch' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                setData(cursor);
                break;

            case "Dinner":
                cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_type = 'Dinner' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                setData(cursor);
                break;

            case "Others":
                cursor = myDBHelper.getData("SELECT * FROM expense_tbl WHERE expense_type = 'Others' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                setData(cursor);
                break;
        }
    }

    //initialize all components
    private void init(View view) {

        myDBHelper = new MyDBHelper(getActivity());

        expenseTypeSpinner = view.findViewById(R.id.selectExpenseTypeSpinnerId);
        expenseTypeSpinnerList = getResources().getStringArray(R.array.expense_type_spinner_list);
        expenseTypeSpinnerAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,expenseTypeSpinnerList);
        expenseTypeSpinner.setAdapter(expenseTypeSpinnerAdapter);

        spenderSpinner = view.findViewById(R.id.selectSpenderSpinnerId);
        spenderSpinnerList = getResources().getStringArray(R.array.spender_spinner_list);
        spenderSpinnerAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,spenderSpinnerList);
        spenderSpinner.setAdapter(spenderSpinnerAdapter);

        fromDateTV = view.findViewById(R.id.fromDateTVId);
        toDateTV = view.findViewById(R.id.toDateTVId);
        fromDateIV = view.findViewById(R.id.fromDateIVId);
        toDateIV = view.findViewById(R.id.toDateIVId);

        recyclerView = view.findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = view.findViewById(R.id.fabId);
        expenseList = new ArrayList<Expense>();
    }

}
