package com.example.asus.bechelorlifeexpense.fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asus.bechelorlifeexpense.R;
import com.example.asus.bechelorlifeexpense.database.MyDBHelper;

public class ConsumerFragment extends Fragment {

    private MyDBHelper myDBHelper;

    private Spinner spinner;
    private String[] spinnerList;
    private ArrayAdapter<String> arrayAdapter;

    private TextView expenseSpenderShuvoTV,expenseSpenderJewelTV,expenseSpenderDebeshTV;
    private TextView costShuvoConsumerOfShuvo,costJewelConsumerOfShuvo,costDebeshConsumerOfShuvo;
    private TextView costShuvoConsumerOfJewel,costJewelConsumerOfJewel,costDebeshConsumerOfJewel;
    private TextView costShuvoConsumerOfDebesh,costJewelConsumerOfDebesh,costDebeshConsumerOfDebesh;

    public ConsumerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_consumer, container, false);

        init(view);

        setAllData();

        return view;
    }

    private void setAllData() {
        //show total expense based on spinner selected item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor;

                if(position == 0){

                    //spender shuvo
                    expenseSpenderShuvoTV.setText(getDataAccordingToColumnAndSpender("expense_amount","Shuvo"));
                    costShuvoConsumerOfShuvo.setText(getDataAccordingToColumnAndSpender("shuvo_cost","Shuvo"));
                    costJewelConsumerOfShuvo.setText(getDataAccordingToColumnAndSpender("jewel_cost","Shuvo"));
                    costDebeshConsumerOfShuvo.setText(getDataAccordingToColumnAndSpender("debesh_cost","Shuvo"));

                    //spender jewel
                    expenseSpenderJewelTV.setText(getDataAccordingToColumnAndSpender("expense_amount","Jewel"));
                    costShuvoConsumerOfJewel.setText(getDataAccordingToColumnAndSpender("shuvo_cost","Jewel"));
                    costJewelConsumerOfJewel.setText(getDataAccordingToColumnAndSpender("jewel_cost","Jewel"));
                    costDebeshConsumerOfJewel.setText(getDataAccordingToColumnAndSpender("debesh_cost","Jewel"));

                    //spender debesh
                    expenseSpenderDebeshTV.setText(getDataAccordingToColumnAndSpender("expense_amount","Debesh"));
                    costShuvoConsumerOfDebesh.setText(getDataAccordingToColumnAndSpender("shuvo_cost","Debesh"));
                    costJewelConsumerOfDebesh.setText(getDataAccordingToColumnAndSpender("jewel_cost","Debesh"));
                    costDebeshConsumerOfDebesh.setText(getDataAccordingToColumnAndSpender("debesh_cost","Debesh"));

                }else if(position == 1){

                    //spender shuvo
                    expenseSpenderShuvoTV.setText(setDataColumnAndSpenderAndType("expense_amount","Shuvo","Electricity Bill"));
                    costShuvoConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("shuvo_cost","Shuvo","Electricity Bill"));
                    costJewelConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("jewel_cost","Shuvo","Electricity Bill"));
                    costDebeshConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("debesh_cost","Shuvo","Electricity Bill"));

                    //spender jewel
                    expenseSpenderJewelTV.setText(setDataColumnAndSpenderAndType("expense_amount","Jewel","Electricity Bill"));
                    costShuvoConsumerOfJewel.setText(setDataColumnAndSpenderAndType("shuvo_cost","Jewel","Electricity Bill"));
                    costJewelConsumerOfJewel.setText(setDataColumnAndSpenderAndType("jewel_cost","Jewel","Electricity Bill"));
                    costDebeshConsumerOfJewel.setText(setDataColumnAndSpenderAndType("debesh_cost","Jewel","Electricity Bill"));

                    //spender debesh
                    expenseSpenderDebeshTV.setText(setDataColumnAndSpenderAndType("expense_amount","Debesh","Electricity Bill"));
                    costShuvoConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("shuvo_cost","Debesh","Electricity Bill"));
                    costJewelConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("jewel_cost","Debesh","Electricity Bill"));
                    costDebeshConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("debesh_cost","Debesh","Electricity Bill"));

                }
                else if(position == 2){

                    //spender shuvo
                    expenseSpenderShuvoTV.setText(setDataColumnAndSpenderAndType("expense_amount","Shuvo","Transport Cost"));
                    costShuvoConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("shuvo_cost","Shuvo","Transport Cost"));
                    costJewelConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("jewel_cost","Shuvo","Transport Cost"));
                    costDebeshConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("debesh_cost","Shuvo","Transport Cost"));

                    //spender jewel
                    expenseSpenderJewelTV.setText(setDataColumnAndSpenderAndType("expense_amount","Jewel","Transport Cost"));
                    costShuvoConsumerOfJewel.setText(setDataColumnAndSpenderAndType("shuvo_cost","Jewel","Transport Cost"));
                    costJewelConsumerOfJewel.setText(setDataColumnAndSpenderAndType("jewel_cost","Jewel","Transport Cost"));
                    costDebeshConsumerOfJewel.setText(setDataColumnAndSpenderAndType("debesh_cost","Jewel","Transport Cost"));

                    //spender debesh
                    expenseSpenderDebeshTV.setText(setDataColumnAndSpenderAndType("expense_amount","Debesh","Transport Cost"));
                    costShuvoConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("shuvo_cost","Debesh","Transport Cost"));
                    costJewelConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("jewel_cost","Debesh","Transport Cost"));
                    costDebeshConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("debesh_cost","Debesh","Transport Cost"));

                }
                else if(position == 3){

                    //spender shuvo
                    expenseSpenderShuvoTV.setText(setDataColumnAndSpenderAndType("expense_amount","Shuvo","Medical Cost"));
                    costShuvoConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("shuvo_cost","Shuvo","Medical Cost"));
                    costJewelConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("jewel_cost","Shuvo","Medical Cost"));
                    costDebeshConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("debesh_cost","Shuvo","Medical Cost"));

                    //spender jewel
                    expenseSpenderJewelTV.setText(setDataColumnAndSpenderAndType("expense_amount","Jewel","Medical Cost"));
                    costShuvoConsumerOfJewel.setText(setDataColumnAndSpenderAndType("shuvo_cost","Jewel","Medical Cost"));
                    costJewelConsumerOfJewel.setText(setDataColumnAndSpenderAndType("jewel_cost","Jewel","Medical Cost"));
                    costDebeshConsumerOfJewel.setText(setDataColumnAndSpenderAndType("debesh_cost","Jewel","Medical Cost"));

                    //spender debesh
                    expenseSpenderDebeshTV.setText(setDataColumnAndSpenderAndType("expense_amount","Debesh","Medical Cost"));
                    costShuvoConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("shuvo_cost","Debesh","Medical Cost"));
                    costJewelConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("jewel_cost","Debesh","Medical Cost"));
                    costDebeshConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("debesh_cost","Debesh","Medical Cost"));

                }
                else if(position == 4){

                    //spender shuvo
                    expenseSpenderShuvoTV.setText(setDataColumnAndSpenderAndType("expense_amount","Shuvo","Breakfast"));
                    costShuvoConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("shuvo_cost","Shuvo","Breakfast"));
                    costJewelConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("jewel_cost","Shuvo","Breakfast"));
                    costDebeshConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("debesh_cost","Shuvo","Breakfast"));

                    //spender jewel
                    expenseSpenderJewelTV.setText(setDataColumnAndSpenderAndType("expense_amount","Jewel","Breakfast"));
                    costShuvoConsumerOfJewel.setText(setDataColumnAndSpenderAndType("shuvo_cost","Jewel","Breakfast"));
                    costJewelConsumerOfJewel.setText(setDataColumnAndSpenderAndType("jewel_cost","Jewel","Breakfast"));
                    costDebeshConsumerOfJewel.setText(setDataColumnAndSpenderAndType("debesh_cost","Jewel","Breakfast"));

                    //spender debesh
                    expenseSpenderDebeshTV.setText(setDataColumnAndSpenderAndType("expense_amount","Debesh","Breakfast"));
                    costShuvoConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("shuvo_cost","Debesh","Breakfast"));
                    costJewelConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("jewel_cost","Debesh","Breakfast"));
                    costDebeshConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("debesh_cost","Debesh","Breakfast"));

                }
                else if(position == 5){

                    //spender shuvo
                    expenseSpenderShuvoTV.setText(setDataColumnAndSpenderAndType("expense_amount","Shuvo","Lunch"));
                    costShuvoConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("shuvo_cost","Shuvo","Lunch"));
                    costJewelConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("jewel_cost","Shuvo","Lunch"));
                    costDebeshConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("debesh_cost","Shuvo","Lunch"));

                    //spender jewel
                    expenseSpenderJewelTV.setText(setDataColumnAndSpenderAndType("expense_amount","Jewel","Lunch"));
                    costShuvoConsumerOfJewel.setText(setDataColumnAndSpenderAndType("shuvo_cost","Jewel","Lunch"));
                    costJewelConsumerOfJewel.setText(setDataColumnAndSpenderAndType("jewel_cost","Jewel","Lunch"));
                    costDebeshConsumerOfJewel.setText(setDataColumnAndSpenderAndType("debesh_cost","Jewel","Lunch"));

                    //spender debesh
                    expenseSpenderDebeshTV.setText(setDataColumnAndSpenderAndType("expense_amount","Debesh","Lunch"));
                    costShuvoConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("shuvo_cost","Debesh","Lunch"));
                    costJewelConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("jewel_cost","Debesh","Lunch"));
                    costDebeshConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("debesh_cost","Debesh","Lunch"));

                }
                else if(position == 6){

                    //spender shuvo
                    expenseSpenderShuvoTV.setText(setDataColumnAndSpenderAndType("expense_amount","Shuvo","Dinner"));
                    costShuvoConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("shuvo_cost","Shuvo","Dinner"));
                    costJewelConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("jewel_cost","Shuvo","Dinner"));
                    costDebeshConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("debesh_cost","Shuvo","Dinner"));

                    //spender jewel
                    expenseSpenderJewelTV.setText(setDataColumnAndSpenderAndType("expense_amount","Jewel","Dinner"));
                    costShuvoConsumerOfJewel.setText(setDataColumnAndSpenderAndType("shuvo_cost","Jewel","Dinner"));
                    costJewelConsumerOfJewel.setText(setDataColumnAndSpenderAndType("jewel_cost","Jewel","Dinner"));
                    costDebeshConsumerOfJewel.setText(setDataColumnAndSpenderAndType("debesh_cost","Jewel","Dinner"));

                    //spender debesh
                    expenseSpenderDebeshTV.setText(setDataColumnAndSpenderAndType("expense_amount","Debesh","Dinner"));
                    costShuvoConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("shuvo_cost","Debesh","Dinner"));
                    costJewelConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("jewel_cost","Debesh","Dinner"));
                    costDebeshConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("debesh_cost","Debesh","Dinner"));

                }
                else if(position == 7){

                    //spender shuvo
                    expenseSpenderShuvoTV.setText(setDataColumnAndSpenderAndType("expense_amount","Shuvo","Others"));
                    costShuvoConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("shuvo_cost","Shuvo","Others"));
                    costJewelConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("jewel_cost","Shuvo","Others"));
                    costDebeshConsumerOfShuvo.setText(setDataColumnAndSpenderAndType("debesh_cost","Shuvo","Others"));

                    //spender jewel
                    expenseSpenderJewelTV.setText(setDataColumnAndSpenderAndType("expense_amount","Jewel","Others"));
                    costShuvoConsumerOfJewel.setText(setDataColumnAndSpenderAndType("shuvo_cost","Jewel","Others"));
                    costJewelConsumerOfJewel.setText(setDataColumnAndSpenderAndType("jewel_cost","Jewel","Others"));
                    costDebeshConsumerOfJewel.setText(setDataColumnAndSpenderAndType("debesh_cost","Jewel","Others"));

                    //spender debesh
                    expenseSpenderDebeshTV.setText(setDataColumnAndSpenderAndType("expense_amount","Debesh","Others"));
                    costShuvoConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("shuvo_cost","Debesh","Others"));
                    costJewelConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("jewel_cost","Debesh","Others"));
                    costDebeshConsumerOfDebesh.setText(setDataColumnAndSpenderAndType("debesh_cost","Debesh","Others"));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String setDataColumnAndSpenderAndType(String columnName,String spenderName, String expenseType) {

        String totalString = null;
        Cursor cursor = myDBHelper.getData("SELECT SUM ("+columnName+") AS total FROM expense_tbl WHERE spender_name = '"+spenderName+"' AND expense_type = '"+expenseType+"'");
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndex("total"));
            totalString = String.valueOf(total);
        }
        return totalString;
    }

    private String getDataAccordingToColumnAndSpender(String columnName,String spenderName) {
        String totalString = null;
        Cursor cursor = myDBHelper.getData("SELECT SUM ("+columnName+") AS total FROM expense_tbl WHERE spender_name = '"+spenderName+"'");
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndex("total"));
            totalString = String.valueOf(total);
        }
        return totalString;
    }

    private void init(View view) {
        myDBHelper = new MyDBHelper(getActivity());

        spinner = view.findViewById(R.id.selectExpenseTypeSpinnerId);
        spinnerList = getResources().getStringArray(R.array.expense_type_spinner_list);
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,spinnerList);
        spinner.setAdapter(arrayAdapter);

        expenseSpenderShuvoTV = view.findViewById(R.id.expenseOfShuvoSpenderTVId);
        expenseSpenderJewelTV = view.findViewById(R.id.expenseOfJewelSpenderTVId);
        expenseSpenderDebeshTV = view.findViewById(R.id.expenseOfDebeshSpenderTVId);

        costShuvoConsumerOfShuvo = view.findViewById(R.id.shuvoConsCostOfShuvoTVId);
        costJewelConsumerOfShuvo = view.findViewById(R.id.jewelConsCostOfShuvoTVId);
        costDebeshConsumerOfShuvo = view.findViewById(R.id.debeshConsCostOfShuvoTVId);

        costShuvoConsumerOfJewel = view.findViewById(R.id.shuvoConsCostOfJewelTVId);
        costJewelConsumerOfJewel = view.findViewById(R.id.jewelConsCostOfJewelTVId);
        costDebeshConsumerOfJewel = view.findViewById(R.id.debeshConsCostOfJewelTVId);

        costShuvoConsumerOfDebesh = view.findViewById(R.id.shuvoConsCostOfDebeshTVId);
        costJewelConsumerOfDebesh = view.findViewById(R.id.jewelConsCostOfDebeshTVId);
        costDebeshConsumerOfDebesh = view.findViewById(R.id.debeshConsCostOfDebeshTVId);
    }
}