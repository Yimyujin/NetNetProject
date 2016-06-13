package org.ymdroid.rnb.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.ymdroid.rnb.R;
import org.ymdroid.rnb.key.CosInfo;
import org.ymdroid.rnb.key.CosInfoDAO;
import org.ymdroid.rnb.key.Key;
import org.ymdroid.rnb.key.Review;

/**
 * Created by yj on 16. 6. 13..
 */
public class Review_write extends ActionBarActivity {


    EditText et_text_write;
    ImageButton btn_rv_score_one;
    ImageButton btn_rv_score_two;
    ImageButton btn_rv_score_three;
    ImageButton btn_rv_score_four;
    ImageButton btn_rv_score_five;

    int i = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("리뷰 작성하기");
        setContentView(R.layout.activity_review_write);

        et_text_write = (EditText)findViewById(R.id.et_text_review_write);
        btn_rv_score_one = (ImageButton)findViewById(R.id.btn_rv_score_one);
        btn_rv_score_two = (ImageButton)findViewById(R.id.btn_rv_score_two);
        btn_rv_score_three = (ImageButton)findViewById(R.id.btn_rv_score_three);
        btn_rv_score_four = (ImageButton)findViewById(R.id.btn_rv_score_four);
        btn_rv_score_five = (ImageButton)findViewById(R.id.btn_rv_score_five);

        //스피너 (콤보박스)

    }

    public void scoreOneClicked(View v) throws Exception
    {
        i = 1;
        btn_rv_score_one.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_two.setBackgroundResource(R.drawable.heart_nopush);
        btn_rv_score_three.setBackgroundResource(R.drawable.heart_nopush);
        btn_rv_score_four.setBackgroundResource(R.drawable.heart_nopush);
        btn_rv_score_five.setBackgroundResource(R.drawable.heart_nopush);
    }
    public void scoreTwoClicked(View v) throws Exception
    {
        i = 2;
        btn_rv_score_one.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_two.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_three.setBackgroundResource(R.drawable.heart_nopush);
        btn_rv_score_four.setBackgroundResource(R.drawable.heart_nopush);
        btn_rv_score_five.setBackgroundResource(R.drawable.heart_nopush);
    }
    public void scoreThreeClicked(View v) throws Exception
    {
        i = 3;
        btn_rv_score_one.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_two.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_three.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_four.setBackgroundResource(R.drawable.heart_nopush);
        btn_rv_score_five.setBackgroundResource(R.drawable.heart_nopush);
    }
    public void scoreFourClicked(View v) throws Exception
    {
        i = 4;
        btn_rv_score_one.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_two.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_three.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_four.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_five.setBackgroundResource(R.drawable.heart_nopush);
    }
    public void scoreFiveClicked(View v) throws Exception
    {
        i = 5;
        btn_rv_score_one.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_two.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_three.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_four.setBackgroundResource(R.drawable.heart_push);
        btn_rv_score_five.setBackgroundResource(R.drawable.heart_push);
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(Review_write.this, Review_page.class);
        startActivity(i);
        finish();
    }


    public void Write_ReviewButtonClicked(View v) throws Exception {

        String text = et_text_write.getText().toString();
        Log.d("TAG_text", text);

        CosInfo tmp = Key.cosInfo;
        CosInfoDAO data  = CosInfoDAO.getInstance();

        data.cosInfos.get(tmp.idx).rv.add(new Review(Key.user_email,i,text));

        Toast.makeText(getApplicationContext(), "작성된 리뷰를 저장합니다.", Toast.LENGTH_LONG).show();

        Intent i = new Intent(Review_write.this, Review_page.class);
        startActivity(i);
        finish();

    }

    public void Write_CancelButtonClicked(View v) throws Exception {

        Toast.makeText(getApplicationContext(), "리뷰 작성을 중단합니다.", Toast.LENGTH_LONG).show();
        Intent i = new Intent(Review_write.this, Review_page.class);
        startActivity(i);
        finish();
    }


}
