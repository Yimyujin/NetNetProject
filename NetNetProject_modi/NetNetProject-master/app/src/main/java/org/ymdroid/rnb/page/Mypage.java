package org.ymdroid.rnb.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.ymdroid.rnb.R;
import org.ymdroid.rnb.event.ListMypageAdapter;
import org.ymdroid.rnb.key.user_info;

/**
 * Created by yj on 16. 5. 22..
 */
public class Mypage extends ActionBarActivity {

    private static final String TAG = "DEBUG";
    private ArrayAdapter<String> mSpinnerAdapter = null;
    private ListView mListView = null;
    public static ListMypageAdapter mAdapter = null;
    private TextView tv_empty_myallergy;
    TextView tv_skintype_mypage = null;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mypage);
        getSupportActionBar().setTitle("마이페이지");

        tv_empty_myallergy = (TextView) findViewById(R.id.tv_empty_mypage);
        mListView = (ListView) findViewById(R.id.ls_allergy_mypage);
        tv_skintype_mypage = (TextView)findViewById(R.id.tv_skintype_mypage);
        mAdapter = new ListMypageAdapter(this);
        mListView.setAdapter(mAdapter);


        MySkintype();
        MyAllergyList();

        mAdapter.notifyDataSetChanged();

        if(mListView!=null) {
            tv_empty_myallergy.setVisibility(View.INVISIBLE);
        }
        else
            tv_empty_myallergy.setVisibility(View.VISIBLE);

    }

    public void MySkintype()
    {
        String skintype = "";
        for(int i=0; i<user_info.skin.length; i++)
        {
            if(user_info.skin[i] == true)
                skintype += "  " + user_info.skinType[i];
        }

        tv_skintype_mypage.setText("피부타입 : " + skintype);
    }

    public void MyAllergyList()
    {
        for(int i=0; i< user_info.allergy.length; i++)
        {
            if(user_info.allergy[i] == true)
                mAdapter.addItem(user_info.allergyType[i], null);
        }
    }



    public void UserinfoButtonClicked(View v) throws Exception {
        Toast.makeText(getApplicationContext(), "사용자 정보 변경", Toast.LENGTH_LONG).show();
        Intent i = new Intent(Mypage.this, Mypage_Userinfo.class);
        startActivity(i);
    }


    public void ChangePWButtonClicked(View v) throws Exception {
        Toast.makeText(getApplicationContext(), "비밀번호 변경", Toast.LENGTH_LONG).show();
        Intent i = new Intent(Mypage.this, MyPage_ChangePW.class);
        startActivity(i);
    }

}