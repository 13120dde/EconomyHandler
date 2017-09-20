package a13solutions.myEco.model;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by 13120dde on 2017-09-18.
 */

public class ExpIncItem {

    private final int keyInDb;
    private String title, category, date;
    private double amount;
    private int index;

    public ExpIncItem(String title, String category, String date, double amount, int key, int index){
        this.title=title;
        this.category=category;
        this.date=date;
        this.amount=amount;
        keyInDb = key;
        this.index=index;
    }

    public int getKeyInDb() {
        return keyInDb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }


    public String getDate() {
        return date;
    }


    public double getAmount() {
        return amount;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String toString(){
        return date+" "+category+" "+title+" "+amount+" "+keyInDb+"\n";
    }


}
