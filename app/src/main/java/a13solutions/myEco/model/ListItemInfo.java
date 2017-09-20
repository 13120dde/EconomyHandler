package a13solutions.myEco.model;

import java.util.ArrayList;

/**
 * Created by 13120dde on 2017-09-16.
 */

public class ListItemInfo {

    private String title;

    ArrayList<ChildInfo> children = new ArrayList<ChildInfo>();

    private double amount;
    private int itemId;
    public ListItemInfo(String title, ChildInfo category, ChildInfo date, double amount, int itemId) {
        this.title = title;
        this.amount = amount;
        this.itemId = itemId;
        children.add(date);
        children.add(category);
    }

    public ArrayList<ChildInfo> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ChildInfo> children) {
        this.children = children;
    }

    public ChildInfo getChild(int index){
        return children.get(index);
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getAmount() {
        return UtilityMethods.roundTwoDecimals(amount);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
