package a13solutions.myEco.comparator;

import java.util.Comparator;

import a13solutions.myEco.model.ExpIncItem;
import a13solutions.myEco.model.UtlilityMethods;

/**
 * Created by 13120dde on 2017-09-18.
 */

public class ComparatorDateAscending implements Comparator<ExpIncItem> {
    @Override
    public int compare(ExpIncItem item, ExpIncItem item2) {
        int[] thisDate = UtlilityMethods.parseDateFromString(item.getDate());
        int[] otherDate = UtlilityMethods.parseDateFromString(item2.getDate());

        if(thisDate[0]<otherDate[0]){
            return -1;
        }else if(thisDate[0]>otherDate[0]){
            return 1;
        }else{
            if(thisDate[1]<otherDate[1]){
                return -1;
            }else if(thisDate[1]>otherDate[1]){
                return 1;
            }else{
                if(thisDate[2]<otherDate[2]){
                    return -1;
                }else if(thisDate[2]>otherDate[2]){
                    return 1;
                }else{
                    return 0;
                }
            }
        }
    }
}
