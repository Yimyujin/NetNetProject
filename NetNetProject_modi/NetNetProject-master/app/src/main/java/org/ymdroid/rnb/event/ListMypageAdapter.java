package org.ymdroid.rnb.event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.ymdroid.rnb.R;

import java.util.ArrayList;

/**
 * Created by yj on 16. 5. 22..
 */
public class ListMypageAdapter extends BaseAdapter {
    private Context mContext = null;
    public ArrayList<ListData2> mListData = new ArrayList<ListData2>();

    public ListMypageAdapter(Context mContext) {
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

    public void addItem(String mTitle, String mDate){
        ListData2 addInfo = null;
        addInfo = new ListData2();
        addInfo.mTitle = mTitle;
        addInfo.mDate = mDate;
        mListData.add(addInfo);
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder2 holder;

        if (convertView == null) {
            holder = new ViewHolder2();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_userinfo, null);

            holder.mText = (TextView) convertView.findViewById(R.id.mText_userinfo);
            holder.mDate = (TextView) convertView.findViewById(R.id.mDate_userinfo);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder2) convertView.getTag();
        }

        ListData2 mData = mListData.get(position);

        holder.mText.setText(mData.mTitle);
        holder.mDate.setText(mData.mDate);

        return convertView;
    }
}
