package org.ymdroid.rnb.page;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.ymdroid.rnb.R;
import org.ymdroid.rnb.TessCore;
import org.ymdroid.rnb.key.CosInfoDAO;
import org.ymdroid.rnb.key.Key;

import java.io.IOException;
import java.io.InputStream;

public class OCRActivity extends ActionBarActivity {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    Button button;
    ImageView imageView;
    private Uri picUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        getSupportActionBar().setTitle("화장품 성분추출");

        button=(Button) findViewById(R.id.button_ocr);
        imageView=(ImageView)findViewById(R.id.image_view_ocr);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // 카메라 호출
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //intent.putExtra(MediaStore.EXTRA_OUTPUT,
                 //       MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

                startActivityForResult(intent, PICK_FROM_CAMERA);
            }
        });
    }
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        if (requestCode == PICK_FROM_CAMERA) {
            picUri = data.getData();
            performCrop();
        }
        //user is returning from cropping the image
        else if(requestCode == CROP_FROM_CAMERA){
            //get the returned data
            Bundle extras = data.getExtras();
            //get the cropped bitmap
            Bitmap thePic = extras.getParcelable("data");
            imageView.setImageBitmap(thePic);
            TessCore tsc = new TessCore(this);
            // Bitmap testBmp = getBitmapFromAsset(this,"test01.bmp");
            String strTemp = tsc.detectText(thePic);
            String[] arStrNormalSpace = strTemp.split(" ");
            String lastStr = arStrNormalSpace[arStrNormalSpace.length-1].trim();
            int cosIndex = 0;
            if(lastStr.equals("DisodiumEDTA")) {
                cosIndex = 0;
            }else if(lastStr.equals("AscorbylPalmitate")){
                cosIndex = 1;
            }else if(lastStr.equals("Tromethamine")){
                cosIndex = 2;
            }else if(lastStr.equals("Phenoxyethanol")){
                cosIndex = 4;
            }
            CosInfoDAO cos = CosInfoDAO.getInstance();
            Key.cosInfo = cos.cosInfos.get(cosIndex);
            Intent i = new Intent(OCRActivity.this, AnalysisView.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "화장품정보",Toast.LENGTH_LONG).show();

            // TextView textView = (TextView)findViewById(R.id.text_ocr);
            //  textView.setText(strTemp);

        }
        /*
        if (requestCode == PICK_FROM_GALLERY) {
            Bundle extras2 = data.getExtras();
            if (extras2 != null) {
                Bitmap photo = extras2.getParcelable("data");
                imgview.setImageBitmap(photo);
            }
        }*/
    }

    private void performCrop(){
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            //cropIntent.putExtra("aspectX", 1);
            //cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            //cropIntent.putExtra("outputX", 90);
            //cropIntent.putExtra("outputY", 90);
            //cropIntent.putExtra("scale", true);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, CROP_FROM_CAMERA);
        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static Bitmap getBitmapFromAsset(Context context, String filePath){
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }
        return bitmap;
    }
}
