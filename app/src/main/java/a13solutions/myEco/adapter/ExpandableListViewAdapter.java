package a13solutions.myEco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import a13solutions.myEco.R;
import a13solutions.myEco.model.ChildInfo;
import a13solutions.myEco.model.ListItemInfo;

/**
 * Created by 13120dde on 2017-09-16.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter{

    private Context context;
    private ArrayList<ListItemInfo> listItemInfo;

    public ExpandableListViewAdapter(Context context, ArrayList<ListItemInfo> listItemInfo) {
        this.context = context;
        this.listItemInfo = listItemInfo;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItemInfo.get(groupPosition).getChild(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        ChildInfo detailInfo = (ChildInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.child_items, null);
        }

        TextView childItem = (TextView) view.findViewById(R.id.tvCategoryCreated);
        childItem.setText(detailInfo.getName().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listItemInfo.get(groupPosition).getChildren().size();
    }

    
    @Override
    public Object getGroup(int groupPosition) {
        return listItemInfo.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return listItemInfo.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        ListItemInfo headerInfo = (ListItemInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.group_items, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.tvListItemHeader);
        heading.setText(headerInfo.getTitle().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void changeIcon(int i) {
    }
}
