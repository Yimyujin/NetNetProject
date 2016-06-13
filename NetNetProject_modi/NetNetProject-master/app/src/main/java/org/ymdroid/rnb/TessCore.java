package org.ymdroid.rnb;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

/**
 * Created by gec on 2016-01-22.
 */
public class TessCore {
    static final String TAG = "DBG_" + TessCore.class.getName();

    private Context mCtx;

    public TessCore(Context context) {
        this.mCtx = context;
    }

    public String detectText(Bitmap bitmap) {
        Log.d(TAG, "Initialization of TessBaseApi");
        TessDataManager.initTessTrainedData(mCtx);
        TessBaseAPI tessBaseAPI = new TessBaseAPI();
        String path = TessDataManager.getTesseractFolder();
        Log.d(TAG, "Tess folder: " + path);
        tessBaseAPI.setDebug(true);
        tessBaseAPI.init(path, "eng");
        // 추천 글자들
        tessBaseAPI.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        // 비추천 글자들
        tessBaseAPI.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "!@#$%^&*()_+=-[]}{;:'\"\\|~`,./<>?");
        tessBaseAPI.setPageSegMode(TessBaseAPI.OEM_TESSERACT_CUBE_COMBINED);
        Log.d(TAG, "Ended initialization of TessEngine");
        Log.d(TAG, "Running inspection on bitmap");
        tessBaseAPI.setImage(bitmap);
        String inspection = tessBaseAPI.getUTF8Text();
        Log.d(TAG, "=====Got data=====" + inspection);
        tessBaseAPI.end();
        System.gc();
        return inspection;
    }
}
