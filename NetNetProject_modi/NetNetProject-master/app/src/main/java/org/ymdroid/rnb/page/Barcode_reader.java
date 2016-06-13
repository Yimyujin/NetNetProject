package org.ymdroid.rnb.page;

import android.IntentIntegrator;
import android.IntentResult;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ymdroid.rnb.R;

public class Barcode_reader  extends AppCompatActivity implements View.OnClickListener {

    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    String scanContent = null;
    String scanFormat = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);

        scanBtn=(Button)findViewById(R.id.scan_button);
        formatTxt=(TextView)findViewById(R.id.scan_format);
        contentTxt=(TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);

            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();

    }

    private void findCosmeticInfo(){
        if(scanFormat!="cosmetic"){
            Toast.makeText(getApplicationContext(), "화장품코드가 아닙니다.",Toast.LENGTH_LONG).show();
            return;
        }

    }

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator=new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode,int resultCode, Intent intent)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if(scanningResult != null)
        {
            scanContent = scanningResult.getContents();
            scanFormat=scanningResult.getFormatName();

            formatTxt.setText("FORMAT: "+scanFormat);
            contentTxt.setText("CONTENT: "+scanContent);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
