package mx.iteso.msc.rodriguez.roberto.mylibrary;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Roberto Rodriguez on 4/3/2016.
 */
public class AdminEdit extends Fragment implements View.OnClickListener {
    public static Fragment newInstance() {
        AdminEdit fragment = new AdminEdit();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }
}
