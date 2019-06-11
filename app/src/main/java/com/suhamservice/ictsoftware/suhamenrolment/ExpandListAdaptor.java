package com.suhamservice.ictsoftware.suhamenrolment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandListAdaptor extends BaseExpandableListAdapter {

    private Context context;
    private List ListHeader;
    private HashMap<String,List<String>> ListDetails;

    public ExpandListAdaptor(Context context,List<String> ListHeader,HashMap<String,List<String>> ListDetail)
    {
        this.context=context;
        this.ListDetails=ListDetail;
        this.ListHeader=ListHeader;
    }


    @Override
    public int getGroupCount() {
        return this.ListHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.ListDetails.get(this.ListHeader.get(groupPosition)).size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.ListHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.ListDetails.get(this.ListHeader.get(groupPosition)).get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final String listTitle=(String)getGroup(groupPosition);
        if (convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.listtopic,null);

        }

        View ind = convertView.findViewById( R.id.explist_indicator);
        if( ind != null ) {
            ImageView indicator = (ImageView)ind;
            if( getChildrenCount( groupPosition ) == 0 ) {
                indicator.setVisibility( View.INVISIBLE );
            }
            else {
                indicator.setVisibility(View.VISIBLE);
                indicator.setImageResource(isExpanded ? R.drawable.key_arrow_down : R.drawable.key_arrow_right);
            }
           /* else {
                indicator.setVisibility( View.VISIBLE );
                int stateSetIndex = ( isExpanded ? 1 : 0) ;
                Drawable drawable = indicator.getDrawable();
            }*/
        }


            TextView listTitleTextView=(TextView)convertView.findViewById(R.id.TextViewTopic);
        listTitleTextView.setText(listTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String expandedString=(String) getChild(groupPosition,childPosition);

        if (convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.listsubtopic,null);

        }

        TextView expandedlistTextView=(TextView) convertView.findViewById(R.id.TextViewSubTopic);

        expandedlistTextView.setText(expandedString);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}


