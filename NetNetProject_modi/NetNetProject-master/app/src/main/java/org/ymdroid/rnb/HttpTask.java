package org.ymdroid.rnb;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by user on 2016-05-16.
 */
public class HttpTask extends AsyncTask<Void, Void, String> {

    HTTPUtil http = new HTTPUtil();
    private String uri;
    private String msg;

    public HttpTask(String uri, String msg) {
        this.uri = uri;
        this.msg = msg;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(Void... arg0) { //서버에서 보내주는 걸 받음
        String res = null;
        try {
            res = http.connection(uri, msg);
        }catch (Exception e){e.printStackTrace();}
        return  res;
    }
    protected void onPostExecute(String result) {
       Log.e("TASK", "res : " +result);
    }

}
