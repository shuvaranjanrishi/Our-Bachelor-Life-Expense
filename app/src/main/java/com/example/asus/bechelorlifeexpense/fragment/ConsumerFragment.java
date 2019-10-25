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
                if(position == 0){
                    Cursor shuvoCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo'");
                    if (shuvoCursor.moveToFirst()) {
                        int total = shuvoCursor.getInt(shuvoCursor.getColumnIndex("total"));
                        expenseSpenderShuvoTV.setText(String.valueOf(total));
                    }

                    Cursor jewelCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel'");
                    if(jewelCursor.moveToFirst()){
                        int total = jewelCursor.getInt(jewelCursor.getColumnIndex("total"));
                        expenseSpenderJewelTV.setText(String.valueOf(total));
                    }

                    Cursor debeshCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh'");
                    if(debeshCursor.moveToFirst()){
                        int total = debeshCursor.getInt(debeshCursor.getColumnIndex("total"));
                        expenseSpenderDebeshTV.setText(String.valueOf(total));
                    }

                }else if(position == 1){

                    Cursor shuvoCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Electricity Bill'");
                    if(shuvoCursor.moveToFirst()){
                        int total = shuvoCursor.getInt(shuvoCursor.getColumnIndex("total"));
                        expenseSpenderShuvoTV.setText(String.valueOf(total));
                    }

                    Cursor jewelCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Electricity Bill'");
                    if(jewelCursor.moveToFirst()){
                        int total = jewelCursor.getInt(jewelCursor.getColumnIndex("total"));
                        expenseSpenderJewelTV.setText(String.valueOf(total));
                    }

                    Cursor debeshCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Electricity Bill'");
                    if(debeshCursor.moveToFirst()){
                        int total = debeshCursor.getInt(debeshCursor.getColumnIndex("total"));
                        expenseSpenderDebeshTV.setText(String.valueOf(total));
                    }
                }
                else if(position == 2){

                    Cursor shuvoCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Transport Cost'");
                    if(shuvoCursor.moveToFirst()){
                        int total = shuvoCursor.getInt(shuvoCursor.getColumnIndex("total"));
                        expenseSpenderShuvoTV.setText(String.valueOf(total));
                    }

                    Cursor jewelCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Transport Cost'");
                    if(jewelCursor.moveToFirst()){
                        int total = jewelCursor.getInt(jewelCursor.getColumnIndex("total"));
                        expenseSpenderJewelTV.setText(String.valueOf(total));
                    }

                    Cursor debeshCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Transport Cost'");
                    if(debeshCursor.moveToFirst()){
                        int total = debeshCursor.getInt(debeshCursor.getColumnIndex("total"));
                        expenseSpenderDebeshTV.setText(String.valueOf(total));
                    }
                }
                else if(position == 3){

                    Cursor shuvoCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Medical Cost'");
                    if(shuvoCursor.moveToFirst()){
                        int total = shuvoCursor.getInt(shuvoCursor.getColumnIndex("total"));
                        expenseSpenderShuvoTV.setText(String.valueOf(total));
                    }

                    Cursor jewelCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Medical Cost'");
                    if(jewelCursor.moveToFirst()){
                        int total = jewelCursor.getInt(jewelCursor.getColumnIndex("total"));
                        expenseSpenderJewelTV.setText(String.valueOf(total));
                    }

                    Cursor debeshCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Medical Cost'");
                    if(debeshCursor.moveToFirst()){
                        int total = debeshCursor.getInt(debeshCursor.getColumnIndex("total"));
                        expenseSpenderDebeshTV.setText(String.valueOf(total));
                    }
                }
                else if(position == 4){

                    Cursor shuvoCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Breakfast'");
                    if(shuvoCursor.moveToFirst()){
                        int total = shuvoCursor.getInt(shuvoCursor.getColumnIndex("total"));
                        expenseSpenderShuvoTV.setText(String.valueOf(total));
                    }

                    Cursor jewelCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Breakfast'");
                    if(jewelCursor.moveToFirst()){
                        int total = jewelCursor.getInt(jewelCursor.getColumnIndex("total"));
                        expenseSpenderJewelTV.setText(String.valueOf(total));
                    }

                    Cursor debeshCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Breakfast'");
                    if(debeshCursor.moveToFirst()){
                        int total = debeshCursor.getInt(debeshCursor.getColumnIndex("total"));
                        expenseSpenderDebeshTV.setText(String.valueOf(total));
                    }
                }
                else if(position == 5){

                    Cursor shuvoCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Lunch'");
                    if(shuvoCursor.moveToFirst()){
                        int total = shuvoCursor.getInt(shuvoCursor.getColumnIndex("total"));
                        expenseSpenderShuvoTV.setText(String.valueOf(total));
                    }

                    Cursor jewelCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Lunch'");
                    if(jewelCursor.moveToFirst()){
                        int total = jewelCursor.getInt(jewelCursor.getColumnIndex("total"));
                        expenseSpenderJewelTV.setText(String.valueOf(total));
                    }

                    Cursor debeshCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Lunch'");
                    if(debeshCursor.moveToFirst()){
                        int total = debeshCursor.getInt(debeshCursor.getColumnIndex("total"));
                        expenseSpenderDebeshTV.setText(String.valueOf(total));
                    }
                }
                else if(position == 6){

                    Cursor shuvoCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Dinner'");
                    if(shuvoCursor.moveToFirst()){
                        int total = shuvoCursor.getInt(shuvoCursor.getColumnIndex("total"));
                        expenseSpenderShuvoTV.setText(String.valueOf(total));
                    }

                    Cursor jewelCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Dinner'");
                    if(jewelCursor.moveToFirst()){
                        int total = jewelCursor.getInt(jewelCursor.getColumnIndex("total"));
                        expenseSpenderJewelTV.setText(String.valueOf(total));
                    }

                    Cursor debeshCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Dinner'");
                    if(debeshCursor.moveToFirst()){
                        int total = debeshCursor.getInt(debeshCursor.getColumnIndex("total"));
                        expenseSpenderDebeshTV.setText(String.valueOf(total));
                    }
                }
                else if(position == 7){

                    Cursor shuvoCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Shuvo' AND expense_type = 'Others'");
                    if(shuvoCursor.moveToFirst()){
                        int total = shuvoCursor.getInt(shuvoCursor.getColumnIndex("total"));
                        expenseSpenderShuvoTV.setText(String.valueOf(total));
                    }

                    Cursor jewelCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Jewel' AND expense_type = 'Others'");
                    if(jewelCursor.moveToFirst()){
                        int total = jewelCursor.getInt(jewelCursor.getColumnIndex("total"));
                        expenseSpenderJewelTV.setText(String.valueOf(total));
                    }

                    Cursor debeshCursor = myDBHelper.getData("SELECT SUM (expense_amount) AS total FROM expense_tbl WHERE spender_name = 'Debesh' AND expense_type = 'Others'");
                    if(debeshCursor.moveToFirst()){
                        int total = debeshCursor.getInt(debeshCursor.getColumnIndex("total"));
                        expenseSpenderDebeshTV.setText(String.valueOf(total));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
