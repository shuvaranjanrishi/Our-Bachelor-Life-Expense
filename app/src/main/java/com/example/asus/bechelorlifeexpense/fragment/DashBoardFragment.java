package com.example.asus.bechelorlifeexpense.fragment;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asus.bechelorlifeexpense.R;
import com.example.asus.bechelorlifeexpense.database.MyDBHelper;

import java.util.Calendar;

public class DashBoardFragment extends Fragment {

    private MyDBHelper myDBHelper;

    private Spinner spinner;
    private String[] spinnerList;
    private ArrayAdapter<String> arrayAdapter;

    private TextView fromDateTV,toDateTV,totalExpenseTV,totalExpenseOfShuvoTV,totalExpenseOfJewelTV,totalExpenseOfDebeshTV;
    private ImageView fromDateIV,toDateIV;

    private DatePickerDialog.OnDateSetListener mFromDateSetListener;
    private DatePickerDialog.OnDateSetListener mToDateSetListener;

    private String fromDate;

    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);

        init(view);

        getFromDate();

        getToDate();

        //show total expense based on spinner selected item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Cursor cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl");
                    if (cursor.moveToFirst()) {
                        int total = cursor.getInt(cursor.getColumnIndex("total"));
                        totalExpenseTV.setText(String.valueOf(total));
                    }

                    Cursor shuvoCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo'");
                    if(shuvoCursor.moveToFirst()){
                        int total = shuvoCursor.getInt(shuvoCursor.getColumnIndex("total"));
                        totalExpenseOfShuvoTV.setText(String.valueOf(total));
                    }

                    Cursor jewelCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel'");
                    if(jewelCursor.moveToFirst()){
                        int total = jewelCursor.getInt(jewelCursor.getColumnIndex("total"));
                        totalExpenseOfJewelTV.setText(String.valueOf(total));
                    }

                    Cursor debeshCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh'");
                    if(debeshCursor.moveToFirst()){
                        int total = debeshCursor.getInt(debeshCursor.getColumnIndex("total"));
                        totalExpenseOfDebeshTV.setText(String.valueOf(total));
                    }

                }else if(position == 1){

                    totalExpenseTV.setText(getExpenseAccordingToType("Electricity Bill"));
                    totalExpenseOfShuvoTV.setText(getExpenseAccordingToSpenderAndType("Shuvo","Electricity Bill"));
                    totalExpenseOfJewelTV.setText(getExpenseAccordingToSpenderAndType("Jewel","Electricity Bill"));
                    totalExpenseOfDebeshTV.setText(getExpenseAccordingToSpenderAndType("Debesh","Electricity Bill"));

                }
                else if(position == 2){

                    totalExpenseTV.setText(getExpenseAccordingToType("Transport Cost"));
                    totalExpenseOfShuvoTV.setText(getExpenseAccordingToSpenderAndType("Shuvo","Transport Cost"));
                    totalExpenseOfJewelTV.setText(getExpenseAccordingToSpenderAndType("Jewel","Transport Cost"));
                    totalExpenseOfDebeshTV.setText(getExpenseAccordingToSpenderAndType("Debesh","Transport Cost"));

                }
                else if(position == 3){

                    totalExpenseTV.setText(getExpenseAccordingToType("Medical Cost"));
                    totalExpenseOfShuvoTV.setText(getExpenseAccordingToSpenderAndType("Shuvo","Medical Cost"));
                    totalExpenseOfJewelTV.setText(getExpenseAccordingToSpenderAndType("Jewel","Medical Cost"));
                    totalExpenseOfDebeshTV.setText(getExpenseAccordingToSpenderAndType("Debesh","Medical Cost"));

                }
                else if(position == 4){

                    totalExpenseTV.setText(getExpenseAccordingToType("Breakfast"));
                    totalExpenseOfShuvoTV.setText(getExpenseAccordingToSpenderAndType("Shuvo","Breakfast"));
                    totalExpenseOfJewelTV.setText(getExpenseAccordingToSpenderAndType("Jewel","Breakfast"));
                    totalExpenseOfDebeshTV.setText(getExpenseAccordingToSpenderAndType("Debesh","Breakfast"));

                }
                else if(position == 5){

                    totalExpenseTV.setText(getExpenseAccordingToType("Lunch"));
                    totalExpenseOfShuvoTV.setText(getExpenseAccordingToSpenderAndType("Shuvo","Lunch"));
                    totalExpenseOfJewelTV.setText(getExpenseAccordingToSpenderAndType("Jewel","Lunch"));
                    totalExpenseOfDebeshTV.setText(getExpenseAccordingToSpenderAndType("Debesh","Lunch"));

                }
                else if(position == 6){

                    totalExpenseTV.setText(getExpenseAccordingToType("Dinner"));
                    totalExpenseOfShuvoTV.setText(getExpenseAccordingToSpenderAndType("Shuvo","Dinner"));
                    totalExpenseOfJewelTV.setText(getExpenseAccordingToSpenderAndType("Jewel","Dinner"));
                    totalExpenseOfDebeshTV.setText(getExpenseAccordingToSpenderAndType("Debesh","Dinner"));

                }
                else if(position == 7){

                    totalExpenseTV.setText(getExpenseAccordingToType("Others"));
                    totalExpenseOfShuvoTV.setText(getExpenseAccordingToSpenderAndType("Shuvo","Others"));
                    totalExpenseOfJewelTV.setText(getExpenseAccordingToSpenderAndType("Jewel","Others"));
                    totalExpenseOfDebeshTV.setText(getExpenseAccordingToSpenderAndType("Debesh","Others"));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private String getExpenseAccordingToSpenderAndType(String spenderName, String expenseType) {

        String totalString = null;
        Cursor cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = '"+spenderName+"' AND expense_type = '"+expenseType+"'");
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndex("total"));
            totalString = String.valueOf(total);
        }
        return totalString;
    }

    private String getExpenseAccordingToType(String expenseType) {
        String totalString = null;
        Cursor cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE expense_type = '"+expenseType+"'");
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndex("total"));
            totalString = String.valueOf(total);
        }
        return totalString;
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

    //show data according to date
    private void setDataAccordingToDate(String toDate) {
        String selectedItem = spinner.getSelectedItem().toString();
        Cursor cursor;

        switch (selectedItem) {
            case "Select Expense Type":
                cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                if (cursor.moveToFirst()) {
                    int total = cursor.getInt(cursor.getColumnIndex("total"));
                    totalExpenseTV.setText(String.valueOf(total));
                }
                break;

            case "Electricity Bill":
                cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE expense_type = 'Electricity Bill' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                if (cursor.moveToFirst()) {
                    int total = cursor.getInt(cursor.getColumnIndex("total"));
                    totalExpenseTV.setText(String.valueOf(total));
                }
                break;

            case "Transport Cost":
                cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE expense_type = 'Transport Cost' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                if (cursor.moveToFirst()) {
                    int total = cursor.getInt(cursor.getColumnIndex("total"));
                    totalExpenseTV.setText(String.valueOf(total));
                }
                break;

            case "Medical Cost":
                cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE expense_type = 'Medical Cost' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                if (cursor.moveToFirst()) {
                    int total = cursor.getInt(cursor.getColumnIndex("total"));
                    totalExpenseTV.setText(String.valueOf(total));
                }
                break;

            case "Breakfast":
                cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE expense_type = 'Breakfast' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                if (cursor.moveToFirst()) {
                    int total = cursor.getInt(cursor.getColumnIndex("total"));
                    totalExpenseTV.setText(String.valueOf(total));
                }
                break;

            case "Lunch":
                cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE expense_type = 'Lunch' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                if (cursor.moveToFirst()) {
                    int total = cursor.getInt(cursor.getColumnIndex("total"));
                    totalExpenseTV.setText(String.valueOf(total));
                }
                break;

            case "Dinner":
                cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE expense_type = 'Dinner' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                if (cursor.moveToFirst()) {
                    int total = cursor.getInt(cursor.getColumnIndex("total"));
                    totalExpenseTV.setText(String.valueOf(total));
                }
                break;

            case "Others":
                cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE expense_type = 'Others' AND expense_date BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
                if (cursor.moveToFirst()) {
                    int total = cursor.getInt(cursor.getColumnIndex("total"));
                    totalExpenseTV.setText(String.valueOf(total));
                }
                break;
        }
    }

    //initialize all components
    private void init(View view) {

        myDBHelper = new MyDBHelper(getActivity());

        spinner = view.findViewById(R.id.selectExpenseTypeSpinnerId);
        spinnerList = getResources().getStringArray(R.array.expense_type_spinner_list);
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,spinnerList);
        spinner.setAdapter(arrayAdapter);

        fromDateTV = view.findViewById(R.id.fromDateTVId);
        toDateTV = view.findViewById(R.id.toDateTVId);
        fromDateIV = view.findViewById(R.id.fromDateIVId);
        toDateIV = view.findViewById(R.id.toDateIVId);

        totalExpenseTV = view.findViewById(R.id.totalExpenseTVId);
        totalExpenseOfShuvoTV = view.findViewById(R.id.totalExpenseOfShuvoTVId);
        totalExpenseOfJewelTV = view.findViewById(R.id.totalExpenseOfJewelTVId);
        totalExpenseOfDebeshTV = view.findViewById(R.id.totalExpenseOfDebeshTVId);

    }

}
