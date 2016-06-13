package org.ymdroid.rnb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;
import org.ymdroid.rnb.key.Key;
import org.ymdroid.rnb.page.Join_nextpage;

public class SignUp extends FragmentActivity {

    private static final String TAG = "DEBUG";
    // HTTPUtil httpUtil = new HTTPUtil();
    HTTPUtil httpUtil = new HTTPUtil();
    JsonParse json= new JsonParse();
    JSONObject obj;
    String res;
    private ProgressBar spinner;

    EditText email;
    EditText name;
    EditText password;
    EditText confirmPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);

        email = (EditText) findViewById(R.id.et_email);
        name = (EditText) findViewById(R.id.et_name);
        //EditText birth = (EditText) findViewById(R.id.et_birth);
        password = (EditText) findViewById(R.id.et_password);
        confirmPasswd = (EditText) findViewById(R.id.et_confirmPasswd);

    }

    public void NextButtonClicked(View v)throws Exception
    {

        String passwd = password.getText().toString();
        String confirmPW = confirmPasswd.getText().toString();
        String uemail = email.getText().toString();
        String uname = name.getText().toString();

        Key.user_email = uemail;
        if(passwd.getBytes().length <= 0 || confirmPW.getBytes().length <= 0 || uemail.getBytes().length <= 0 || uname.getBytes().length <= 0){
            //빈값이 넘어올때의 처리
            Toast.makeText(SignUp.this, "값을 입력하세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (passwd.length() > 7 && passwd.length() < 15) {
                password.setTextColor(Color.BLACK);
                confirmPasswd.setTextColor(Color.BLACK);
                if (passwd.equals(confirmPW)) {
                    //비밀번호 확인 하기
                    confirmPasswd.setTextColor(Color.BLACK);
                    obj = new JSONObject();
                    obj.put("uemail", uemail);
                    obj.put("upasswd", passwd);
                    obj.put("uname", uname);
                    Log.e(TAG, "json : " + obj.toString());//json 객체 확인

                    //서버로 보냄 파라미터 : "url"동적으로 변화되는 경로, "jsonObject"서버로 보내질 객체
                    HttpTask task = new HttpTask("/cosmetics/join2.php", obj.toString());
                    res = task.execute().get(); //결과값을 받음
                    Log.e(TAG, "result : " + res);//결과 객체 확인
                    // spinner.setVisibility(View.VISIBLE);

                    if (json.StatusJsonParse(res)) {

                        Intent i = new Intent(SignUp.this, Join_nextpage.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(getApplicationContext(), "다음으로 넘어갑니다",Toast.LENGTH_LONG).show();
                        //다음으로 넘어가기 전에 일단 아이디랑 비밀번호, 이메일은 확인하기!
                    } else {
                        Toast.makeText(getApplicationContext(), "회원가입실패", Toast.LENGTH_LONG).show();
                    }
                    //spinner.setVisibility(View.GONE);

                } else {
                    confirmPasswd.setTextColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "비밀번호가 다릅니다. 다시 입력해주세요.", Toast.LENGTH_LONG).show();
                }
            } else {
                password.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "비밀번호를 다시 입력해주세요.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void CancelButtonClicked(View v){
        finish();
    }
}
