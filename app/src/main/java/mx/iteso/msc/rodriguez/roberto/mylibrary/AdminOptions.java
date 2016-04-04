package mx.iteso.msc.rodriguez.roberto.mylibrary;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Roberto Rodriguez on 4/3/2016.
 */
public class AdminOptions extends Fragment implements View.OnClickListener {
    private Button add;
    private Button edit;
    private Button delete;

    @Override
    public void onClick(View view) {
        Intent admin = new Intent(getActivity().getApplicationContext(), AdminAction.class);
        switch (view.getId()) {
            case R.id.optionsAdd:
                admin.putExtra("action", "add");
                break;
            case R.id.optionsEdit:
                admin.putExtra("action", "edit");
                break;
            case R.id.optionsDelete:
                admin.putExtra("action", "delete");
                break;
        }
        startActivity(admin);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.options_admin, container, false);
        add = (Button)view.findViewById(R.id.optionsAdd);
        add.setOnClickListener(this);
        edit = (Button)view.findViewById(R.id.optionsEdit);
        edit.setOnClickListener(this);
        delete = (Button)view.findViewById(R.id.optionsDelete);
        delete.setOnClickListener(this);
        return view;
    }
}
