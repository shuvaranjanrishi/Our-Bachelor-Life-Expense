package com.example.asus.bechelorlifeexpense.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.bechelorlifeexpense.R;
import com.example.asus.bechelorlifeexpense.activity.AddDailyExpenseActivity;
import com.example.asus.bechelorlifeexpense.fragment.ExpensesFragment;
import com.example.asus.bechelorlifeexpense.model_class.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private List<Expense> expenseList;
    private Context context;
    private TextView spenderName,expenseType,expenseAmount,expenseDate,expenseTime,consumerName;

    public ExpenseAdapter() {

    }

    public ExpenseAdapter(List<Expense> expenseList, Context context) {
        this.expenseList = expenseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_design,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        final Expense expense = expenseList.get(position);

        viewHolder.spenderNameTV.setText(expense.getSpenderName());
        viewHolder.expenseTypeTV.setText(expense.getExpenseType());
        viewHolder.expenseDateTV.setText(expense.getExpenseDate());
        viewHolder.expenseAmountTV.setText(expense.getExpenseAmount());

        //recycler view item click event to show details
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet, null);
                spenderName = view.findViewById(R.id.spenderNameTVId);
                expenseType = view.findViewById(R.id.expenseTypeTVId);
                expenseAmount = view.findViewById(R.id.expenseAmountTVId);
                expenseDate = view.findViewById(R.id.expenseDateTVId);
                expenseTime = view.findViewById(R.id.expenseTimeTVId);
                consumerName = view.findViewById(R.id.consumerNameTVId);

                spenderName.setText(expense.getSpenderName());
                expenseType.setText(expense.getExpenseType());
                expenseAmount.setText(expense.getExpenseAmount()+" BDT");
                expenseDate.setText(expense.getExpenseDate());
                consumerName.setText(expense.getConsumerName());

                //time empty checking
                if(expense.getExpenseTime() == null || expense.getExpenseTime().isEmpty()){
                    expenseTime.setText("No Time Added");
                }else {
                    expenseTime.setText(expense.getExpenseTime());
                }

                BottomSheetDialog dialog = new BottomSheetDialog(context);
                dialog.setContentView(view);
                dialog.show();
            }
        });

        //recycler view more button click action
        viewHolder.moreIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context,viewHolder.moreIV);
                popupMenu.inflate(R.menu.edit_menu_item);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.updateOptionId:
                                //update option click action
                                update(expense);

                                return true;

                            case R.id.deleteOptionId:
                                //delete option click action
                                Cursor cursor = ExpensesFragment.myDBHelper.getData("SELECT id FROM expense_tbl");
                                List<Integer> id = new ArrayList<>();

                                while (cursor.moveToNext()){
                                    id.add(cursor.getInt(0));
                                }

                                showDeleteDialog(id.get(position),position);

                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void update(Expense expense) {

        Intent intent = new Intent(context,AddDailyExpenseActivity.class);

        intent.putExtra("EXPENSE_ID",expense.getId());
        intent.putExtra("EXPENSE_TYPE",expense.getExpenseType());
        intent.putExtra("EXPENSE_AMOUNT",expense.getExpenseAmount());
        intent.putExtra("EXPENSE_DATE",expense.getExpenseDate());
        intent.putExtra("EXPENSE_TIME",expense.getExpenseTime());
        intent.putExtra("SPENDER_NAME",expense.getSpenderName());
        intent.putExtra("CONSUMER_NAME",expense.getConsumerName());

        context.startActivity(intent);
    }

    //show delete dialog to delete
    private void showDeleteDialog(final int rowId, final int position) {

        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
        deleteDialog.setTitle("Warning!");
        deleteDialog.setMessage("Are you sure to delete?");

        deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    ExpensesFragment.myDBHelper.deleteDataFromDatabase(rowId);
                    Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                    expenseList.remove(position);
                    notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        deleteDialog.show();
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView spenderNameTV,expenseTypeTV,expenseDateTV,expenseAmountTV;
        private ImageView moreIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            spenderNameTV = itemView.findViewById(R.id.spenderNameTVId);
            expenseTypeTV = itemView.findViewById(R.id.expenseTypeTVId);
            expenseDateTV = itemView.findViewById(R.id.expenseDateTVId);
            expenseAmountTV = itemView.findViewById(R.id.expenseAmountTVId);
            moreIV = itemView.findViewById(R.id.moreIVId);
        }
    }
}
