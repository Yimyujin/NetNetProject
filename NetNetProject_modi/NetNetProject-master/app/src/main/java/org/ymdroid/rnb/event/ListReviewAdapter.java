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
public class ListReviewAdapter extends BaseAdapter {
    private Context mContext = null;
    public ArrayList<ListData> mListData = new ArrayList<ListData>();

    public ListReviewAdapter(Context mContext) {
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

    public void addItem(String mName, Bitmap mScore, String mText){
        ListData addInfo = null;
        addInfo = new ListData();
        addInfo.mName = mName;
        addInfo.mScore = mScore;
        addInfo.mText = mText;
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
        ViewHolder3 holder;
        if (convertView == null) {
            holder = new ViewHolder3();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_review, null);

            holder.mName = (TextView) convertView.findViewById(R.id.review_mName);
            holder.mScore = (ImageView) convertView.findViewById(R.id.review_mScore);
            holder.mText = (TextView) convertView.findViewById(R.id.review_mText);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder3) convertView.getTag();
        }

        ListData mData = mListData.get(position);

        if (mData.mScore != null) {
            holder.mScore.setVisibility(View.VISIBLE);
            holder.mScore.setImageBitmap(mData.mScore);
        }else{
            holder.mScore.setVisibility(View.GONE);
        }

        holder.mText.setText(mData.mText);
        holder.mName.setText(mData.mName);

        return convertView;
    }
}

