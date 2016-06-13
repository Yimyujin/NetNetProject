package org.ymdroid.rnb.event;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.ymdroid.rnb.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kimminyoung on 2016-05-05.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context mContext = null;
    public ArrayList<ListData> mListData = new ArrayList<ListData>();

    public ListViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(Bitmap icon, String mTitle, String mDate){
        ListData addInfo = null;
        addInfo = new ListData();
        addInfo.mIcon = icon;
        addInfo.mTitle = mTitle;
        addInfo.mDate = mDate;

        mListData.add(addInfo);
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void sort(){
        Collections.sort(mListData, ListData.ALPHA_COMPARATOR);
        dataChange();
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview, null);

            holder.mIcon = (ImageView) convertView.findViewById(R.id.mImage);
            holder.mText = (TextView) convertView.findViewById(R.id.mText);
            holder.mDate = (TextView) convertView.findViewById(R.id.mDate);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ListData mData = mListData.get(position);

        if (mData.mIcon != null) {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageBitmap(mData.mIcon);
        }else{
            holder.mIcon.setVisibility(View.GONE);
        }

        holder.mText.setText(mData.mTitle);
        holder.mDate.setText(mData.mDate);

        return convertView;
    }
}

