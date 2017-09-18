package a13solutions.myEco.comparator;

import java.util.Comparator;

import a13solutions.myEco.model.ExpIncItem;

/**
 * Created by 13120dde on 2017-09-18.
 */

public class ComparatorCategory implements Comparator<ExpIncItem> {
    @Override
    public int compare(ExpIncItem expIncItem, ExpIncItem t1) {
        return expIncItem.getCategory().compareTo(t1.getCategory());
    }
}
