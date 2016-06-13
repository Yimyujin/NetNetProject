package org.ymdroid.rnb.page;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.ymdroid.rnb.HttpTask;
import org.ymdroid.rnb.JsonParse;
import org.ymdroid.rnb.Login;
import org.ymdroid.rnb.R;
import org.ymdroid.rnb.key.Key;

import java.util.concurrent.ExecutionException;

/**
 * Created by yj on 16. 5. 24..
 */

//데이터베이스와 연동해서 유저 정보 비밀번호 파트 업데이트 시켜주기.
//다시 로그인 화면으로 돌아가기(?)

public class MyPage_ChangePW extends ActionBarActivity {

    private static final String TAG = "DEBUG";
    private String res = "test";
    //HTTPUtil http = new HTTPUtil();
    JsonParse Json = new JsonParse();
    EditText et_currpasswd, et_newpasswd, et_confpasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_changepw);
        getSupportActionBar().setTitle("MyPage");

        et_currpasswd = (EditText) findViewById(R.id.et_currpasswd_changepw);
        et_newpasswd = (EditText) findViewById(R.id.et_newpasswd_changepw);
        et_confpasswd = (EditText) findViewById(R.id.et_confpasswd_changepw);
    }

    public void ChangePW_OK_ButtonClick(View v) throws ExecutionException, InterruptedException, JSONException {

        String currpasswd = et_currpasswd.getText().toString();
        String newpasswd = et_newpasswd.getText().toString();
        String confpasswd = et_confpasswd.getText().toString();

        et_currpasswd.setTextColor(Color.BLACK);
        et_confpasswd.setTextColor(Color.BLACK);
        et_newpasswd.setTextColor(Color.BLACK);

        //예외처리
        if(currpasswd.getBytes().length <= 0 || newpasswd.getBytes().length <= 0 || confpasswd.getBytes().length <= 0){
            Toast.makeText(MyPage_ChangePW.this, "값을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!currpasswd.equals(Key.user_passwd)) {
            et_currpasswd.setTextColor(Color.RED);
            Log.e("CHANGEPW","password : "+Key.user_passwd+"  user password : "+currpasswd);
            Toast.makeText(getApplicationContext(), "기존 비밀번호와 일치 하지 않습니다!.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newpasswd.equals(confpasswd)) {
            et_newpasswd.setTextColor(Color.RED);
            et_confpasswd.setTextColor(Color.RED);
            Log.e("CHANGEPW","new passowrd : "+newpasswd+" confpasswd : "+confpasswd);
            Toast.makeText(getApplicationContext(), "새로운 비밀번호와 확인이 일치 하지 않습니다!.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (newpasswd.length() <= 7 || newpasswd.length() > 15){
            et_newpasswd.setTextColor(Color.RED);
            Log.e("CHANGEPW","new passowrd length: "+newpasswd.length());
            Toast.makeText(getApplicationContext(), "비밀번호는 8자이상 15자 이하로 작성해야합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject obj = new JSONObject();
        obj.put("uemail", Key.user_email);
        obj.put("newpasswd",newpasswd);
        Log.e(TAG, "json : " + obj.toString());//json 객체 확인

        HttpTask task = new HttpTask("/cosmetics/changePW.php", obj.toString());
        String res = task.execute().get(); //결과값을 받음
        Log.e(TAG, "result : " + res);//결과 객체 확인

        //Json 결과 파서
        if (Json.StatusJsonParse(res)) {
            // Json.getUserInfo(res);
            Toast.makeText(getApplicationContext(), "비밀번호가 변경되었습니다. 다시 로그인해주세요.", Toast.LENGTH_LONG).show();
            //spinner.setVisibility(View.INVISIBLE);
            Key.user_passwd = newpasswd;
            //newpasswd를 디비로 넘겨주세요  changePW.php
            Intent i = new Intent(MyPage_ChangePW.this, Login.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            //spinner.setVisibility(View.INVISIBLE);
        }

    }
    public void ChangePW_Cancel_ButtonClick(View v){
        Intent i = new Intent(MyPage_ChangePW.this, Mypage.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MyPage_ChangePW.this, Mypage.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
