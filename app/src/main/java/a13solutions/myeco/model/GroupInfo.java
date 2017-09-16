package a13solutions.myeco.model;

import java.util.ArrayList;

/**
 * Created by 13120dde on 2017-09-16.
 */

public class GroupInfo {

    private String name;
    private ArrayList<ChildInfo> list = new ArrayList<ChildInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildInfo> getProductList() {
        return list;
    }

    public void setProductList(ArrayList<ChildInfo> productList) {
        this.list = productList;
    }

}
