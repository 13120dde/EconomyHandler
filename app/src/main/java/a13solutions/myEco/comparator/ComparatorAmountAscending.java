package a13solutions.myEco.comparator;

import java.util.Comparator;

import a13solutions.myEco.model.ExpIncItem;

/**
 * Created by 13120dde on 2017-09-19.
 */

public class ComparatorAmountAscending implements Comparator<ExpIncItem>{
    @Override
    public int compare(ExpIncItem item, ExpIncItem item2) {
        return (int) (item.getAmount()-item2.getAmount());
    }
}
