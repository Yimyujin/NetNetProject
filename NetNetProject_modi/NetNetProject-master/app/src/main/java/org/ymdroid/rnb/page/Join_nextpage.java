package org.ymdroid.rnb.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;
import org.ymdroid.rnb.HTTPUtil;
import org.ymdroid.rnb.HttpTask;
import org.ymdroid.rnb.JsonParse;
import org.ymdroid.rnb.Login;
import org.ymdroid.rnb.R;
import org.ymdroid.rnb.event.Join_Listener;
import org.ymdroid.rnb.event.ListAllergyAdapter;
import org.ymdroid.rnb.event.SwipeDismissListViewTouchListener;
import org.ymdroid.rnb.key.Key;
import org.ymdroid.rnb.key.user_info;

/**
 * 만약 중간에 취소된다면, User Table에 가장 나중에 저장된 아이디 지우기
 */
public class Join_nextpage extends FragmentActivity {

    // HTTPUtil httpUtil = new HTTPUtil();
    HTTPUtil httpUtil = new HTTPUtil();
    JsonParse json= new JsonParse();
    JSONObject obj;
    String res;

    private static final String TAG = "Join_nextpage";
    CheckBox cb_oily_skin;
    CheckBox cb_combination_skin;
    CheckBox cb_dry_skin;
    CheckBox cb_normal_skin;
    CheckBox cb_sensitive_skin;
    Spinner spinner;
    ArrayAdapter adapter;
  //  private ArrayAdapter<String> mSpinnerAdapter = null;
    private ListView mListView = null;
    private ListAllergyAdapter mAdapter = null;

    Boolean check = false;
    Boolean[] all_old = null;
    int[] listArr = {0,0,0,0,0,0,0,0,0,0,0};
    //Queue<Integer> listCheck;
    //private ArrayAdapter<String> mSpinnerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_nextpage);

        for(int i=0; i<listArr.length; i++)
        {
            listArr[i] = 0;
        }
        cb_oily_skin = (CheckBox)findViewById(R.id.cb_oily_skin);
        cb_combination_skin = (CheckBox)findViewById(R.id.cb_combination_skin);
        cb_dry_skin = (CheckBox)findViewById(R.id.cb_dry_skin);
        cb_normal_skin = (CheckBox)findViewById(R.id.cb_normal_skin);
        cb_sensitive_skin = (CheckBox)findViewById(R.id.cb_sensitive_skin);

        try {
            if(user_info.allergy == null)
            {
                for (int i = 0; i < user_info.allergy.length; i++) {
                    user_info.allergy[i] = false;
                }
            }

            for (int i = 0; i < user_info.allergy.length; i++) {

                all_old[i] = user_info.allergy[i];
                user_info.allergy[i] = false;
            }
        }
        catch(Exception ex)
        {
            Log.e(TAG,"allergy = " + ex.toString());
        }

        spinner = (Spinner) findViewById(R.id.spinner_allergy);
        spinner.setOnItemSelectedListener(new Join_Listener());

        spinner.setPrompt(getResources().getString(R.string.allergy_prompt));
        adapter = ArrayAdapter.createFromResource(this, R.array.array_allergy,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new Join_Listener());

        mListView = (ListView) findViewById(R.id.listView2);

        mAdapter = new ListAllergyAdapter(this);
        mListView.setAdapter(mAdapter);

        if(mListView!=null) {
            SwipeDismissListViewTouchListener touchListener =
                    new SwipeDismissListViewTouchListener(mListView,
                            new SwipeDismissListViewTouchListener.DismissCallbacks() {
                                @Override
                                public boolean canDismiss(int position) {
                                    return true;
                                }

                                @Override
                                public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                    for (int position : reverseSortedPositions) {
                                        mAdapter.remove(position);
                                        Log.d(TAG,"position :" + position);
                                       // listArr[] = 0; //position이 아니라.. 지웠던 걸 찾아가야댐
                                    }
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
            mListView.setOnTouchListener(touchListener);
            mListView.setOnScrollListener(touchListener.makeScrollListener());
        }
    }

    public void CancelButtonClicked2(View v)throws Exception
    {
        obj = new JSONObject();
        obj.put("uemail", Key.user_email);
        Log.e(TAG, "json : " + obj.toString());//json 객체 확인

        //서버로 보냄 파라미터 : "url"동적으로 변화되는 경로, "jsonObject"서버로 보내질 객체
        HttpTask task = new HttpTask("/cosmetics/join_cancel.php", obj.toString());
        res = task.execute().get(); //결과값을 받음
        Log.e(TAG, "result : " + res);//결과 객체 확인
        // spinner.setVisibility(View.VISIBLE);

        if (json.StatusJsonParse(res)) {
            Intent i = new Intent(Join_nextpage.this, Login.class);
            startActivity(i);
            finish();
            Toast.makeText(getApplicationContext(), "회원 가입을 취소하고 로그인 화면으로 이동합니다.",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "회원 취소 실패", Toast.LENGTH_LONG).show();
        }

    }

    public void PlusButtonClicked(View v)throws Exception
    {
        //spinner 값 누를 때 마다 리스트에 넣어주기
        int index = spinner.getSelectedItemPosition();
        Log.d(TAG,"index = " + index);
        String toastMessage = insertItem(index+1);
        //Toast.makeText(Join_nextpage.this, toastMessage, Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged() ;
    }
    public String insertItem(int index)
    {
        String toastMessage = null;

        if(listArr[index-1] == 1)
        {
            toastMessage = "이미 선택한 알레르기 입니다.";
        }
        else {
            listArr[index - 1] = 1;
            switch (index) {
                case 1: {
                    toastMessage = "옻과 같은 면역계가 이물질로 인식하는 것에 피부가 닿기 때문에 피부가 빨갛고 가렵고 진물 발생";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[0] = true;
                    check = true;
                    break;
                }
                case 2: {
                    toastMessage = "피부의 한 곳이 빨갛고, 가려움 발생";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[1] = true;
                    check = true;
                    break;
                }
                case 3: {
                    toastMessage = "손바닥이나 발바닥의 피부에 자극을 받으며 가렵고 타는 느낌을 주는 맑고 깊은 수포 발생";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[2] = true;
                    check = true;
                    break;
                }
                case 4: {
                    toastMessage = "국소적 가려움증(벌레 물림과 같은)에 의해 머리나 다리 아래, 손목 또는 팔 위 부분에 비늘같은 딱지 생김";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[3] = true;
                    check = true;
                    break;
                }
                case 5: {
                    toastMessage = "피부에 동전 모양의 자극 부위가 생김. 이 부위에 딱지나 비늘이 생기며 매우 가려움";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[4] = true;
                    check = true;
                    break;
                }
                case 6: {
                    toastMessage = "앞머리나 머리 혹은 신체의 다른 부분에 있는 피부에 노란 지성 비늘 자반이 생김";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[5] = true;
                    check = true;
                    break;
                }
                case 7: {
                    toastMessage = "흔히 혈류 문제로 인하여 다리 아래 피부가 자극을 받음";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[6] = true; check = true;
                    break;
                }
                case 8: {
                    toastMessage = "피부를 가볍게 긁거나 스치는 물리적 자극에 의해서 긁은 부분에 두드러기가 발생";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[7] = true; check = true;
                    break;
                }
                case 9: {
                    toastMessage = "피부를 긇은 후 수 시간 후에 긁은 부위에 통증이 있는 두드러기";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[8] = true; check = true;
                    break;
                }
                case 10: {
                    toastMessage = "찬 공기나 찬물에 피부가 노출 시 노출된 부위에 두드러기가 발생";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[9] = true; check = true;
                    break;
                }
                case 11: {
                    toastMessage = "국소적으로 열이 가해진 부위에 두드러기가 발생";
                    mAdapter.addItem(spinner.getSelectedItem().toString(), toastMessage);
                    user_info.allergy[10] = true; check = true;
                    break;
                }
            }
        }
        return toastMessage;
    }

    public void OkButtonClicked(View v)throws Exception
    {
        //콤보박스 내용 삽입
        //리스트에 삽입
        try {

            if(cb_oily_skin.isChecked())
                user_info.skin[0] = true;
            else
                user_info.skin[0] = false;
            if(cb_combination_skin.isChecked())
                user_info.skin[1] = true;
            else
                user_info.skin[1] = false;
            if(cb_dry_skin.isChecked())
                user_info.skin[2] = true;
            else
                user_info.skin[2] = false;
            if(cb_normal_skin.isChecked())
                user_info.skin[3] = true;
            else
                user_info.skin[3] = false;
            if(cb_sensitive_skin.isChecked())
                user_info.skin[4] = true;
            else
                user_info.skin[4] = false;

            if(check == false)
            {
                for(int i=0; i<user_info.allergy.length; i++)
                {
                    user_info.allergy[i] = all_old[i];
                }
            }

            Intent i = new Intent(Join_nextpage.this, Menu.class);
                startActivity(i);
                finish();
                Toast.makeText(getApplicationContext(), "회원 가입을 완료합니다.", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex)
        {
            Log.d(TAG,ex.toString());
        }
    }


}