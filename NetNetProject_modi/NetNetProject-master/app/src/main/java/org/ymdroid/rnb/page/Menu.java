package org.ymdroid.rnb.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.ymdroid.rnb.R;

public class Menu extends FragmentActivity {
    private ArrayAdapter<String> mSpinnerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void capture_barcode_click(View v) {
        Intent i = new Intent(Menu.this, BarcodePageActivity.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "바코드찍기",Toast.LENGTH_LONG).show();
    }

    public void capture_cosmetic_click(View v) {
        Intent i = new Intent(Menu.this, OCRActivity.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "화장품성분표찍기",Toast.LENGTH_LONG).show();
    }

    public void cosmetic_info_click(View v) {
        Intent i = new Intent(Menu.this, Cosmetic_category.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "화장품정보",Toast.LENGTH_LONG).show();
    }

    public void cosmetic_ingredient_click(View v) {
        Intent i = new Intent(Menu.this, Cosmetic_ingredient.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "화장품성분표소개",Toast.LENGTH_LONG).show();
    }

    public void mypage_click(View v) {
        Intent i = new Intent(Menu.this, Mypage.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "마이페이지",Toast.LENGTH_LONG).show();
    }


}