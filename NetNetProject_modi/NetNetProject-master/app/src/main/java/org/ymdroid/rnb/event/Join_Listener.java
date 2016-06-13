package org.ymdroid.rnb.event;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by yj on 16. 5. 22..
 */

public class Join_Listener implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView parent, View view, int pos, long id) {
        // TODO Auto-generated method stub
        Toast.makeText(parent.getContext(),
                "The planet is " + parent.getItemIdAtPosition(pos),
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView parent) {
        // TODO Auto-generated method stub
        // Do nothing
    }

}
