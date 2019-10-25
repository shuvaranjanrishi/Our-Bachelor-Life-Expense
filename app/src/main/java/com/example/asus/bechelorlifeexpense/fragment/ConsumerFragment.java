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
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo'");
                    expenseSpenderShuvoTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo'");
                    costShuvoConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor= myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo'");
                    costJewelConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo'");
                    costDebeshConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    //spender jewel
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel'");
                    expenseSpenderJewelTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel'");
                    costShuvoConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel'");
                    costJewelConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel'");
                    costDebeshConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    //spender debesh
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh'");
                    expenseSpenderDebeshTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh'");
                    costShuvoConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh'");
                    costJewelConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh'");
                    costDebeshConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                }else if(position == 1){

                    //spender shuvo
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Electricity Bill'");
                    expenseSpenderShuvoTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Electricity Bill'");
                    costShuvoConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor= myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Electricity Bill'");
                    costJewelConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Electricity Bill'");
                    costDebeshConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    //spender jewel
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Electricity Bill'");
                    expenseSpenderJewelTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Electricity Bill'");
                    costShuvoConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Electricity Bill'");
                    costJewelConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Electricity Bill'");
                    costDebeshConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    //spender debesh
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Electricity Bill'");
                    expenseSpenderDebeshTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Electricity Bill'");
                    costShuvoConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Electricity Bill'");
                    costJewelConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Electricity Bill'");
                    costDebeshConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                }
                else if(position == 2){

                    //spender shuvo
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Transport Cost'");
                    expenseSpenderShuvoTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Transport Cost'");
                    costShuvoConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor= myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Transport Cost'");
                    costJewelConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Transport Cost'");
                    costDebeshConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    //spender jewel
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Transport Cost'");
                    expenseSpenderJewelTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Transport Cost'");
                    costShuvoConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Transport Cost'");
                    costJewelConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Transport Cost'");
                    costDebeshConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    //spender debesh
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Transport Cost'");
                    expenseSpenderDebeshTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Transport Cost'");
                    costShuvoConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Transport Cost'");
                    costJewelConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Transport Cost'");
                    costDebeshConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));
                }
                else if(position == 3){

                    //spender shuvo
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Medical Cost'");
                    expenseSpenderShuvoTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Medical Cost'");
                    costShuvoConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor= myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Medical Cost'");
                    costJewelConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Medical Cost'");
                    costDebeshConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    //spender jewel
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Medical Cost'");
                    expenseSpenderJewelTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Medical Cost'");
                    costShuvoConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Medical Cost'");
                    costJewelConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Medical Cost'");
                    costDebeshConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    //spender debesh
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Medical Cost'");
                    expenseSpenderDebeshTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Medical Cost'");
                    costShuvoConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Medical Cost'");
                    costJewelConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Medical Cost'");
                    costDebeshConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));
                }
                else if(position == 4){

                    //spender shuvo
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Breakfast'");
                    expenseSpenderShuvoTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Breakfast'");
                    costShuvoConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor= myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Breakfast'");
                    costJewelConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Breakfast'");
                    costDebeshConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    //spender jewel
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Breakfast'");
                    expenseSpenderJewelTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Breakfast'");
                    costShuvoConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Breakfast'");
                    costJewelConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Breakfast'");
                    costDebeshConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    //spender debesh
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Breakfast'");
                    expenseSpenderDebeshTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Breakfast'");
                    costShuvoConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Breakfast'");
                    costJewelConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Breakfast'");
                    costDebeshConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));
                }
                else if(position == 5){

                    //spender shuvo
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Lunch'");
                    expenseSpenderShuvoTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Lunch'");
                    costShuvoConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor= myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Lunch'");
                    costJewelConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Lunch'");
                    costDebeshConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    //spender jewel
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Lunch'");
                    expenseSpenderJewelTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Lunch'");
                    costShuvoConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Lunch'");
                    costJewelConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Lunch'");
                    costDebeshConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    //spender debesh
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Lunch'");
                    expenseSpenderDebeshTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Lunch'");
                    costShuvoConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Lunch'");
                    costJewelConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Lunch'");
                    costDebeshConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));
                }
                else if(position == 6){

                    //spender shuvo
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Dinner'");
                    expenseSpenderShuvoTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Dinner'");
                    costShuvoConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor= myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Dinner'");
                    costJewelConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Dinner'");
                    costDebeshConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    //spender jewel
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Dinner'");
                    expenseSpenderJewelTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Dinner'");
                    costShuvoConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Dinner'");
                    costJewelConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Dinner'");
                    costDebeshConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    //spender debesh
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Dinner'");
                    expenseSpenderDebeshTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Dinner'");
                    costShuvoConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Dinner'");
                    costJewelConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Dinner'");
                    costDebeshConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));
                }
                else if(position == 7){

                    //spender shuvo
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Others'");
                    expenseSpenderShuvoTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Others'");
                    costShuvoConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor= myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Others'");
                    costJewelConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Others'");
                    costDebeshConsumerOfShuvo.setText(setDataToConsumersFragment(cursor));

                    //spender jewel
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Others'");
                    expenseSpenderJewelTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Others'");
                    costShuvoConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Others'");
                    costJewelConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Others'");
                    costDebeshConsumerOfJewel.setText(setDataToConsumersFragment(cursor));

                    //spender debesh
                    cursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Others'");
                    expenseSpenderDebeshTV.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (shuvo_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Others'");
                    costShuvoConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (jewel_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Others'");
                    costJewelConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));

                    cursor = myDBHelper.getData("SELECT SUM (debesh_cost) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Others'");
                    costDebeshConsumerOfDebesh.setText(setDataToConsumersFragment(cursor));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String setDataToConsumersFragment(Cursor cursor) {
        String totalString = null;
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