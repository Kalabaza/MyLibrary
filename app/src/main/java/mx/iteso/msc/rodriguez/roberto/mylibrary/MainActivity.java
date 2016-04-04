package mx.iteso.msc.rodriguez.roberto.mylibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button authors;
    private Button books;
    private Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the buttons
        authors = (Button)findViewById(R.id.mainAuthors);
        books = (Button)findViewById(R.id.mainBooks);
        admin = (Button)findViewById(R.id.mainAdmin);

        // Set the click listeners
        authors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the Admin activity
                Intent admin = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(admin);
            }
        });
    }
}
