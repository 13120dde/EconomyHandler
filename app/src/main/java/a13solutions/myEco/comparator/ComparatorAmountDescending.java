package a13solutions.myEco.comparator;

import java.util.Comparator;

import a13solutions.myEco.model.ExpIncItem;

/**
 * Created by 13120dde on 2017-09-18.
 */

public class ComparatorAmountDescending implements Comparator<ExpIncItem> {
    @Override
    public int compare(ExpIncItem item, ExpIncItem item2) {
        return (int) (item2.getAmount()-item.getAmount());
    }
}
