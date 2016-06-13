package org.ymdroid.rnb;

import android.content.Intent;
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
import org.ymdroid.rnb.page.Menu;

public class Login extends FragmentActivity {
    private static final String TAG = "DEBUG";
    String res = "test";
    private ProgressBar spinner;
    HTTPUtil http = new HTTPUtil();
    JsonParse Json = new JsonParse();
    EditText user_email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        //startActivity(new Intent(this, Splash.class));

        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        user_email = (EditText)findViewById(R.id.user_Email);
        password = (EditText)findViewById(R.id.password);
    }

    public void LoginButtonClicked(View v) throws Exception {

        String email_id = user_email.getText().toString();
        String passwd = password.getText().toString();

        email_id = email_id.trim();
        passwd = passwd.trim();

        if(email_id.getBytes().length <= 0 || passwd.getBytes().length <= 0){//빈값이 넘어올때의 처리
            Toast.makeText(Login.this, "값을 입력하세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            JSONObject obj = new JSONObject();
            obj.put("uemail", email_id);
            obj.put("upasswd", passwd);
            Log.e(TAG, "json : " + obj.toString());//json 객체 확인

            HttpTask task = new HttpTask("/cosmetics/login.php", obj.toString());
            String res = task.execute().get(); //결과값을 받음
            Log.e(TAG, "result : " + res);//결과 객체 확인


            //Json 결과 파서
            if (Json.StatusJsonParse(res)) {
                // Json.getUserInfo(res);
                Key.user_email = email_id;
                Key.user_passwd = passwd;
                Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_LONG).show();
                //spinner.setVisibility(View.INVISIBLE);
                Intent i = new Intent(Login.this, Menu.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "ID 또는 비밀번호를 다시 입력해주세요.", Toast.LENGTH_LONG).show();
                //spinner.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void SignUpButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), " 회원 가입", Toast.LENGTH_LONG).show();
    }
}
