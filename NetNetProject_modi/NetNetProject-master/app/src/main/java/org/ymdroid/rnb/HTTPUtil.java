package org.ymdroid.rnb;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by kimminyoung on 2016-05-13.
 */
public class HTTPUtil extends Thread {
    private static final String TAG = "HTTPUtil";
    OkHttpClient client = new OkHttpClient();
    Request request;
    Response response;

    final String base_url = "http://115.68.182.138";
    public HTTPUtil() {
    }

    public String connection(String url, String jsonString) throws IOException, NetworkErrorException, Exception {
        RequestBody query = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Log.e(TAG, "url : "+base_url+url);
        request = new Request.Builder()
                .url(base_url + url)
                .post(query)
                .build();
        response = client.newCall(request).execute();
        return response.body().string();
    }


}
