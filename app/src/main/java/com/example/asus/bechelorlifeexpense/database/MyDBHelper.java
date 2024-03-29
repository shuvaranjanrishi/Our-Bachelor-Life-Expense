package com.example.asus.bechelorlifeexpense.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bachelor_life_expense_db";
    private static final int VERSION = 2;

    private static final String TABLE_NAME = "expense_tbl";
    private static final String ID = "id";
    private static final String EXPENSE_TYPE = "expense_type";
    private static final String EXPENSE_AMOUNT = "expense_amount";
    private static final String EXPENSE_DATE = "expense_date";
    private static final String EXPENSE_TIME = "expense_time";
    private static final String SPENDER_NAME = "spender_name";
    private static final String CONSUMER_NAME = "consumer_name";
    private static final String SHUVO_COST = "shuvo_cost";
    private static final String JEWEL_COST = "jewel_cost";
    private static final String DEBESH_COST = "debesh_cost";

    private static final String CREATE_TABLE = " CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EXPENSE_TYPE+" TEXT, "+EXPENSE_AMOUNT+" TEXT, "+EXPENSE_DATE+" TEXT, "+EXPENSE_TIME+" TEXT,"+SPENDER_NAME+" TEXT, "+CONSUMER_NAME+" TEXT , "+SHUVO_COST+" TEXT, "+JEWEL_COST+" TEXT, "+DEBESH_COST+" TEXT)";
    private static final String DROP_TABLE = " DROP TABLE IF EXISTS "+TABLE_NAME;

    private Context context;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate is Called", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            Toast.makeText(context, "onUpgrade is Called", Toast.LENGTH_SHORT).show();
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public static int getDatabaseVersion() {
        return VERSION;
    }

    //insert data to database
    public long insertDataToDatabase(String expenseType,String expenseAmount,String expenseDate,String expenseTime,String spenderName,String consumerName, String shuvoCost, String jewelCost, String debeshCost){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EXPENSE_TYPE,expenseType);
        contentValues.put(EXPENSE_AMOUNT,expenseAmount);
        contentValues.put(EXPENSE_DATE,expenseDate);
        contentValues.put(EXPENSE_TIME,expenseTime);
        contentValues.put(SPENDER_NAME,spenderName);
        contentValues.put(CONSUMER_NAME,consumerName);
        contentValues.put(SHUVO_COST,shuvoCost);
        contentValues.put(JEWEL_COST,jewelCost);
        contentValues.put(DEBESH_COST,debeshCost);

        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return rowId;
    }

    //get all data form database
    public Cursor getDataFromDatabase(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        return cursor;
    }

    //get specific data according to sql
    public Cursor getData(String sql) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }

    public int deleteDataFromDatabase(int rowId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int deleteId = sqLiteDatabase.delete(TABLE_NAME, ID + "=" + rowId, null);
        return deleteId;
    }

    //update data to database
    public long updateDataToDatabase(String id,String expenseType, String expenseAmount,String expenseDate,String expenseTime,String spenderName,String consumerName, String shuvoCost, String jewelCost, String debeshCost){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EXPENSE_TYPE,expenseType);
        contentValues.put(EXPENSE_AMOUNT,expenseAmount);
        contentValues.put(EXPENSE_DATE,expenseDate);
        contentValues.put(EXPENSE_TIME,expenseTime);
        contentValues.put(SPENDER_NAME,spenderName);
        contentValues.put(CONSUMER_NAME,consumerName);
        contentValues.put(SHUVO_COST,shuvoCost);
        contentValues.put(JEWEL_COST,jewelCost);
        contentValues.put(DEBESH_COST,debeshCost);

        long rowId = sqLiteDatabase.update(TABLE_NAME,contentValues,"id = ?",new String[]{id});

        return rowId;
    }

    //create a backup for database
    public void backup(String outFileName) {

        //database path
        final String inFileName = context.getDatabasePath(DATABASE_NAME).toString();

        try {

            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            // Transfer bytes from the input file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Close the streams
            output.flush();
            output.close();
            fis.close();

            Toast.makeText(context, "Backup Completed", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Unable to backup database. Retry", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //import data from database
    public void importDB(String inFileName) {

        final String outFileName = context.getDatabasePath(DATABASE_NAME).toString();

        try {

            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            // Transfer bytes from the input file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Close the streams
            output.flush();
            output.close();
            fis.close();

            Toast.makeText(context, "Import Completed\nRestart your app...", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Unable to import database. Retry...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
