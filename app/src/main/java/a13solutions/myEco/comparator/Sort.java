package a13solutions.myEco.comparator;

import java.util.ArrayList;
import java.util.Collections;

import a13solutions.myEco.model.ExpIncItem;

import static java.util.Collections.*;

/**
 * Created by 13120dde on 2017-09-19.
 */

public class Sort {


    public static ArrayList<ExpIncItem> amountAscending(ArrayList<ExpIncItem> dataToSort){
        Collections.sort(dataToSort, new ComparatorAmountAscending());
        return dataToSort;
    }

    public static ArrayList<ExpIncItem> amountDescending(ArrayList<ExpIncItem> dataToSort){
        Collections.sort(dataToSort, new ComparatorAmountDescending());
        return dataToSort;
    }

    public static ArrayList<ExpIncItem> dateAscending(ArrayList<ExpIncItem> dataToSort){
        Collections.sort(dataToSort, new ComparatorDateAscending());
        return dataToSort;
    }

    public static ArrayList<ExpIncItem> dateDescending(ArrayList<ExpIncItem> dataToSort){
        Collections.sort(dataToSort, new ComparatorDateDescending());
        return dataToSort;
    }

    public static ArrayList<ExpIncItem> category(ArrayList<ExpIncItem> dataToSort){
        Collections.sort(dataToSort, new ComparatorCategory());
        return dataToSort;
    }

}
