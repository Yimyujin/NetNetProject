package org.ymdroid.rnb.page;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.ymdroid.rnb.R;
import org.ymdroid.rnb.event.ListData;
import org.ymdroid.rnb.event.ListViewAdapter;
import org.ymdroid.rnb.key.CosInfo;
import org.ymdroid.rnb.key.CosInfoDAO;
import org.ymdroid.rnb.key.Key;

/**
 * Created by kimminyoung on 2016-05-05.
 */
public class Cosmetic_category extends ActionBarActivity
{
    private ArrayAdapter<String> mSpinnerAdapter = null;
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;
    private CosInfoDAO cosInfoDAO;
    private Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_category);
        getSupportActionBar().setTitle("화장품 카테고리");
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00000000));
        res = getResources();
        cosInfoDAO = CosInfoDAO.getInstance();
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        setItmeList();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ListData mData = mAdapter.mListData.get(position);
                Log.e("COSCATA", "cosmeticIdx : "+String.valueOf(position));
                Key.cosInfo = cosInfoDAO.cosInfos.get(position);
                Toast.makeText(Cosmetic_category.this, mData.mTitle, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Cosmetic_category.this, Cosmetic_info.class);
                startActivity(i);
            }
        });
    }
   public void setItmeList(){
        int size = cosInfoDAO.cosInfos.size();
        for(int i=0; i<size; i++){
            CosInfo cosInfo = cosInfoDAO.cosInfos.get(i);
            mAdapter.addItem(BitmapFactory.decodeResource(res, cosInfo.img),
                    cosInfo.name, cosInfo.type);
        }
    }

}

