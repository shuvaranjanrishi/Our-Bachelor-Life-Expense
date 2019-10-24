package com.example.asus.bechelorlifeexpense.model_class;

public class Expense {

    private String id;
    private String expenseType;
    private String expenseAmount;
    private String expenseDate;
    private String expenseTime;
    private String spenderName;
    private String consumerName;

    public Expense(String id, String expenseType, String expenseAmount, String expenseDate, String expenseTime, String spenderName, String consumerName) {
        this.id = id;
        this.expenseType = expenseType;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.expenseTime = expenseTime;
        this.spenderName = spenderName;
        this.consumerName = consumerName;
    }

    public String getId() {
        return id;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public String getExpenseTime() {
        return expenseTime;
    }

    public String getSpenderName() {
        return spenderName;
    }

    public String getConsumerName() {
        return consumerName;
    }
}
