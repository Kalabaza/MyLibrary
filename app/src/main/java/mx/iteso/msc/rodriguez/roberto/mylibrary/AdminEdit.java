package mx.iteso.msc.rodriguez.roberto.mylibrary;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Roberto Rodriguez on 4/3/2016.
 */
public class AdminEdit extends Fragment implements View.OnClickListener {
    public static Fragment newInstance() {
        return new AdminEdit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_admin, container, false);
        return view;
    }
}
