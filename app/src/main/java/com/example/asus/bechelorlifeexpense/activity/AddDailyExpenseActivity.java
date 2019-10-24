package com.example.asus.bechelorlifeexpense.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus.bechelorlifeexpense.R;
import com.example.asus.bechelorlifeexpense.database.MyDBHelper;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddDailyExpenseActivity extends AppCompatActivity {

    private MyDBHelper myDBHelper;

    private Spinner spinner;
    private String[] spinnerList;
    private ArrayAdapter<String> arrayAdapter;

    private EditText amountET, dateET, timeET;
    private RadioButton shuvoRB,jewelRB,debeshRB;
    private CheckBox shuvoCB,jewelCB,debeshCB;
    private Button addExpenseBtn;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    private String expenseType, expenseAmount, expenseDate, expenseTime, spenderName, consumerName,shuvoCons,jewelCons,debeshCons;
    private String idIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_daily_expense);
        setTitle("Add Your Expense");

        //code for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();

        getDate();

        getTime();

        getUpdateIntent();

        //add Expense button action
        addExpenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getDataFromUser();
                getDataFromUser();

                if (idIntent != null) {

                    if (!validate()) {
                        return;
                    } else {
                        //update data to database
                        long resultId = myDBHelper.updateDataToDatabase(idIntent, expenseType, expenseAmount, expenseDate, expenseTime, spenderName, consumerName);

                        if (resultId > 0) {
                            finish();
                            Toast.makeText(AddDailyExpenseActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddDailyExpenseActivity.this, "Data are not Updated", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    if (!validate()) {
                        return;
                    } else {
                        //insert data to database
                        long resultId = myDBHelper.insertDataToDatabase(expenseType, expenseAmount, expenseDate, expenseTime, spenderName, consumerName);

                        if (resultId > 0) {
                            setResult(RESULT_OK);
                            Toast.makeText(AddDailyExpenseActivity.this, "Row " + resultId + " inserted Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AddDailyExpenseActivity.this, "Data are not inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }

    //back button action
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getUpdateIntent() {

        idIntent = getIntent().getStringExtra("EXPENSE_ID");

        if (idIntent != null) {
            int spinnerItemPosition = arrayAdapter.getPosition(getIntent().getStringExtra("EXPENSE_TYPE"));
            spinner.setSelection(spinnerItemPosition);
            amountET.setText(getIntent().getStringExtra("EXPENSE_AMOUNT"));
            dateET.setText(getIntent().getStringExtra("EXPENSE_DATE"));
            timeET.setText(getIntent().getStringExtra("EXPENSE_TIME"));
            setTitle("Update " + getIntent().getStringExtra("EXPENSE_TYPE"));
            addExpenseBtn.setText("Update Expense");
        }
    }

    //check all data empty validation
    private boolean validate() {

        boolean valid = true;

        if (expenseType.equals("Select Expense Type")) {
            Toast.makeText(this, "Please Select Expense Type !", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            if (expenseAmount.isEmpty()) {
                amountET.setError("Please Enter Amount");
                valid = false;
            } else if (expenseAmount.equals(".")) {
                amountET.setError("Please Enter Valid Amount");
                valid = false;
            } else if (expenseDate.isEmpty()) {
                Toast.makeText(this, "Please Select Expense Date !", Toast.LENGTH_SHORT).show();
                valid = false;
            }
        }

        return valid;
    }

    //set time to editTextView by clicking date icon
    private void getTime() {

        timeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddDailyExpenseActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener,
                        hour, minute, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Time time = new Time(hourOfDay, minute, 00);
                SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss aa");
                timeET.setText(timeFormatter.format(time).toString());
            }
        };
    }

    //set date to editTextView by clicking date icon
    private void getDate() {

        dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddDailyExpenseActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                dateET.setText(date);
            }
        };
    }

    //get all data from user input
    private void getDataFromUser() {
        expenseType = spinner.getSelectedItem().toString();
        expenseAmount = amountET.getText().toString().trim();

        if(shuvoCB.isChecked()){
            spenderName = shuvoRB.getText().toString();
        }else if(shuvoCB.isChecked()){
            spenderName = jewelRB.getText().toString();
        }else if(shuvoCB.isChecked()){
            spenderName = debeshRB.getText().toString();
        }

        if(shuvoCB.isChecked()){
            shuvoCons = shuvoCB.getText().toString();
        }
        if(jewelCB.isChecked()){
            jewelCons = jewelCB.getText().toString();
        }
        if(debeshCB.isChecked()){
            debeshCons = debeshCB.getText().toString();
        }

        consumerName = ""+shuvoCons+"\n"+jewelCons+"\n"+debeshCons;
        expenseDate = dateET.getText().toString().trim();
        expenseTime = timeET.getText().toString().trim();

    }

    //initialize all components
    private void init() {

        myDBHelper = new MyDBHelper(AddDailyExpenseActivity.this);

        spinner = findViewById(R.id.selectExpenseTypeSpinnerId);
        spinnerList = getResources().getStringArray(R.array.spinner_list);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerList);
        spinner.setAdapter(arrayAdapter);

        amountET = findViewById(R.id.expenseAmountETId);
        dateET = findViewById(R.id.expenseDateETId);
        timeET = findViewById(R.id.expenseTimeETId);

        shuvoRB = findViewById(R.id.shuvoRBId);
        jewelRB = findViewById(R.id.jewelRBId);
        debeshRB = findViewById(R.id.debeshRBId);

        shuvoCB = findViewById(R.id.shuvoCBId);
        jewelCB = findViewById(R.id.jewelCBId);
        debeshCB = findViewById(R.id.debeshCBId);

        addExpenseBtn = findViewById(R.id.addExpenseBtnId);
    }
}
