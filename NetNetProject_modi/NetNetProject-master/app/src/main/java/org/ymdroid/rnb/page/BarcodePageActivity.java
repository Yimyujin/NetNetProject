package org.ymdroid.rnb.page;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ymdroid.rnb.R;
import org.ymdroid.rnb.google.zxing.integration.android.IntentIntegrator;
import org.ymdroid.rnb.google.zxing.integration.android.IntentResult;
import org.ymdroid.rnb.key.CosInfo;
import org.ymdroid.rnb.key.CosInfoDAO;
import org.ymdroid.rnb.key.Key;

public class BarcodePageActivity extends AppCompatActivity implements OnClickListener{

    private Button scanBtn;
    private Button goCosInfoBtn;
    private TextView formatTxt, contentTxt;
    private boolean checkFirst = true;
    private CosInfoDAO cosInfoDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode_page);
        scanBtn=(Button)findViewById(R.id.bt_scan);
        goCosInfoBtn=(Button)findViewById(R.id.bt_goCosinfo);

        formatTxt=(TextView)findViewById(R.id.scan_format);
        contentTxt=(TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);

        cosInfoDAO  = CosInfoDAO.getInstance();

        if(checkFirst) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
            checkFirst = false;
        }
    }
    public void bt_goCosmeticInfo_Click(View v){

        if(v.getId()==R.id.bt_goCosinfo){
            Intent i = new Intent(BarcodePageActivity.this, Cosmetic_info.class);
            startActivity(i);
        }
    }

    public void onClick(View v){
        if(v.getId()==R.id.bt_scan){
            IntentIntegrator scanIntegrator=new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public boolean searchCosIngoCode(String Content){
        int size = cosInfoDAO.cosInfos.size();

        for(int i=0; i<size; i++){
            CosInfo cosInfo = cosInfoDAO.cosInfos.get(i);
            Log.e("BARCODE", "code Content : "+cosInfo.code);
            if(cosInfo.code.equals(Content)){
                Key.cosInfo = cosInfo;
                return true;
            }
        }

        return false;
    }

    public void onActivityResult(int requestCode,int resultCode, Intent intent)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);

        if(scanningResult != null)
        {
            String scanContent = scanningResult.getContents();
            String scanFormat=scanningResult.getFormatName();
            formatTxt.setText("FORMAT: "+scanFormat);
            contentTxt.setText("CONTENT: "+scanContent);
            boolean res = searchCosIngoCode(scanContent);

            if(res) {
                goCosInfoBtn.setEnabled(true);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "화장품 정보를 찾았습니다", Toast.LENGTH_SHORT);
                toast.show();

            }else{
                goCosInfoBtn.setEnabled(false);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "화장품 정보를 찾지못하였습니다", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }



}


