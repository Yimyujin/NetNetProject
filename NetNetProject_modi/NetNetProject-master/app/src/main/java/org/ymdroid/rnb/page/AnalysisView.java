package org.ymdroid.rnb.page;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import org.ymdroid.rnb.R;
import org.ymdroid.rnb.event.ListViewAnalysisAdapter;
import org.ymdroid.rnb.key.CosInfo;
import org.ymdroid.rnb.key.Key;

public class AnalysisView extends ActionBarActivity {
    private ListView mListView = null;
    private ListViewAnalysisAdapter mAdapter = null;
    private CosInfo A ;
    private Resources res;

    private int[] dangerImg = {
            R.drawable.ing_null,R.drawable.ing_one, R.drawable.ing_two, R.drawable.ing_three, R.drawable.ing_four,
            R.drawable.ing_five, R.drawable.ing_six, R.drawable.ing_seven, R.drawable.ing_eight, R.drawable.ing_nine
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_view);

        getSupportActionBar().setTitle("화장품 성분분석");
        res = getResources();
        mListView = (ListView) findViewById(R.id.listView_analysis);
        mAdapter = new ListViewAnalysisAdapter(this);
        A = Key.cosInfo;
        int size = A.enIng.length;

        for(int i=0; i<size; i++) {
            mAdapter.addItem(BitmapFactory.decodeResource(res, dangerImg[A.danger[i]]),A.krIng[i], A.enIng[i]);
        }
        mListView.setAdapter(mAdapter);
    }
}
