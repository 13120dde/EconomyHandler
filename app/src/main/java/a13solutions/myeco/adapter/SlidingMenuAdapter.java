package a13solutions.myeco.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a13solutions.myeco.R;
import a13solutions.myeco.model.ItemSlideMenu;

/**
 * Created by 13120dde on 2017-09-12.
 */

public class SlidingMenuAdapter extends BaseAdapter {

    private Context context;
    private List<ItemSlideMenu> lstItem;

    public SlidingMenuAdapter(Context context, List<ItemSlideMenu> lstItem) {
        this.context = context;
        this.lstItem = lstItem;
    }

    @Override
    public int getCount() {
        return lstItem.size();
    }

    @Override
    public Object getItem(int i) {
        return lstItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.item_sliding_menu, null);
        ImageView img = (ImageView) v.findViewById(R.id.item_img);
        TextView tv = (TextView) v.findViewById(R.id.item_title);

        ItemSlideMenu item = lstItem.get(i);
        img.setImageResource(item.getImgId());
        tv.setText(item.getTitle());

        return v;
    }
}
