package mx.iteso.msc.rodriguez.roberto.mylibrary;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Roberto Rodriguez on 4/3/2016.
 */
public class AdminAction extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_admin);
        // En landscape el cambio de fragment sera hecho en la clase AdminOptions
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        // En portrait se cambia el fragment que se despliega en pantalla
        Fragment fragment;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String option = extras.getString("action");
            assert option != null;
            switch (option) {
                case "add":
                    fragment = AdminAdd.newInstance();
                    break;
                case "edit":
                    fragment = AdminEdit.newInstance();
                    break;
                default: // "delete"
                    fragment = AdminDelete.newInstance();
                    break;
            }
            getFragmentManager().beginTransaction().replace(R.id.actionAdmin, fragment).commit();
        }
    }
}
