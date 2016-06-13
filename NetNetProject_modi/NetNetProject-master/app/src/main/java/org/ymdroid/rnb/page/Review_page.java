package org.ymdroid.rnb.page;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.ymdroid.rnb.R;
import org.ymdroid.rnb.event.ListReviewAdapter;
import org.ymdroid.rnb.key.CosInfo;
import org.ymdroid.rnb.key.Key;
import org.ymdroid.rnb.key.Review;

/**
 * Created by yj on 16. 6. 12..
 */
public class Review_page extends ActionBarActivity {

    private ListView mListView = null;
    private ListReviewAdapter mAdapter = null;
    private CosInfo cosInfo;
    private Resources res;

    private int[] score = {R.drawable.score_zero,R.drawable.score_one,R.drawable.score_two,R.drawable.score_three,R.drawable.score_four,R.drawable.score_five};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        getSupportActionBar().setTitle("리뷰");
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00000000));
        mListView = (ListView) findViewById(R.id.ls_review);
        mAdapter = new ListReviewAdapter(this);
        mListView.setAdapter(mAdapter);
        cosInfo = Key.cosInfo;
        res = getResources();
        insertReview();
    }

    public void insertReview()
    {
        int size = cosInfo.rv.size();

        for(Review rv :  cosInfo.rv) {
            Log.e("REVIEW","score : "+String.valueOf(rv.score));
            mAdapter.addItem(rv.name,BitmapFactory.decodeResource(res, score[rv.score]),
                    rv.text);
        }
    }

    public void writeReviewButtonClicked(View v)  throws Exception {

        Intent i = new Intent(Review_page.this, Review_write.class);
        startActivity(i);
        finish();

    }

}
