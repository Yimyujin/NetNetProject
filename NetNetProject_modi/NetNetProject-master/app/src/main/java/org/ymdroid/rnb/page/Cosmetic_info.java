package org.ymdroid.rnb.page;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.ymdroid.rnb.R;
import org.ymdroid.rnb.key.CosInfo;
import org.ymdroid.rnb.key.CosInfoDAO;
import org.ymdroid.rnb.key.Key;

/**
 * Created by yj on 16. 5. 17..
 */
public class Cosmetic_info extends ActionBarActivity {
    public ImageView cosimg;
    public ImageView scoreimg;
    public TextView cosName;
    public TextView cosBrand;
    public TextView cosType;
    public TextView cosPrice;
    public TextView cosSafety;

    int[] rcimg = {R.id.btn_cos_sample, R.id.btn_cos_sample2, R.id.btn_cos_sample3,  R.id.btn_cos_sample3,  R.id.btn_cos_sample4,  R.id.btn_cos_sample5} ;
    int[] rccost = {R.id.tv_cos_text, R.id.tv_cos_text2,R.id.tv_cos_text3, R.id.tv_cos_text4,  R.id.tv_cos_text5} ;
    private int[] score = {R.drawable.score_zero,R.drawable.score_one,R.drawable.score_two,R.drawable.score_three,R.drawable.score_four,R.drawable.score_five};

    Resources res;
    CosInfo cosInfo = Key.cosInfo;
    CosInfoDAO cosInfoDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_info);
        cosimg = (ImageView)findViewById(R.id.img_cosmetic);
        cosName = (TextView) findViewById(R.id.tv_cosname);
        cosBrand = (TextView) findViewById(R.id.tv_brand);
        cosType= (TextView) findViewById(R.id.tv_type);
        cosPrice = (TextView) findViewById(R.id.tv_cost);
        scoreimg  = (ImageView)findViewById(R.id.tv_review_score);
        cosSafety = (TextView) findViewById(R.id.tv_safety);
        res = getResources();
        cosInfoDAO = CosInfoDAO.getInstance();

        // cosHighPrice = (TextView) findViewById(R.id.tv_cosname);
        getSupportActionBar().setTitle("화장품 정보");
        setCosmeticInfo();
        getIngredient();
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00000000));
    }

    public void getIngredient()
    {
        int size = cosInfo.danger.length;
        //int avscore = 0;
        int check2to6 = 0;
        int check7to9 = 0;
        for(int i=0; i<size; i++) {
            if(cosInfo.danger[i] > 6)
                check7to9++;
            else if(cosInfo.danger[i] > 1 && cosInfo.danger[i] < 7)
                check2to6++;
         //   avscore+=cosInfo.danger[i];
        }
       // avscore/=size;
        cosSafety.setText((100-(check7to9*4)-(check2to6*2)) + " %");
    }



    public void setCosmeticInfo(){
        cosimg.setImageBitmap(BitmapFactory.decodeResource(res, cosInfo.img));
        cosName.setText(cosInfo.name);
        cosBrand.setText(cosInfo.brand);
        cosType.setText(cosInfo.type);
        cosPrice.setText(String.valueOf(cosInfo.lowPrice + " ~ " + cosInfo.highPrice));
        int size = cosInfo.rc.length;
        for(int i=0; i<size; i++) {
            CosInfo tmp = cosInfoDAO.cosInfos.get(cosInfo.rc[i]);
            Log.e("TEST", "id : "+String.valueOf(cosInfo.rc[i]));
            ImageView img = (ImageView) findViewById(rcimg[i]);
            img.setImageBitmap(BitmapFactory.decodeResource(res, tmp.img));
            TextView cost = (TextView) findViewById(rccost[i]);  //문자가 너무 길어서 잘랐어
            //if(String.valueOf(tmp.name).length() > 10) {
                cost.setText(String.valueOf(tmp.name.substring(0,9)+"..."));
            //}
            //else
           //     cost.setText(String.valueOf(tmp.name));
        }
        averageScore();
    }
    public void averageScore(){

        int size = cosInfo.rv.size();
        int avscore  =0;
        for(int i=0; i<size; i++) {
            avscore+=cosInfo.rv.get(i).score;
        }
        avscore/=size;
        scoreimg.setImageBitmap(BitmapFactory.decodeResource(res,score[avscore]));
    }

    public void CosAnlaysisButtonClicked(View v){
        Intent i = new Intent(Cosmetic_info.this, AnalysisView.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "성분확인페이지",Toast.LENGTH_LONG).show();
    }

    public void CosSampleButtonClicked(View v)throws Exception
    {
        int getidx =0;
        for(int i=0; i<5; i++) {
            if (v.getId() != rcimg[i]) continue;
            getidx =i; break;
        }

        int size = cosInfo.rc.length;
        if(size-1<getidx) return;

        Key.cosInfo = cosInfoDAO.cosInfos.get(cosInfo.rc[getidx]);
        Intent i = new Intent(Cosmetic_info.this, Cosmetic_info.class);
        startActivity(i);
        finish();
    }
    public void reviewPageOnClicked(View v) throws Exception
    {
        Intent i = new Intent(Cosmetic_info.this, Review_page.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Review",Toast.LENGTH_LONG).show();
    }
}
