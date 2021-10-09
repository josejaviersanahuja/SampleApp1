package com.zitrojjdev.sampleapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class About extends AppCompatActivity {

    private TextView titleAbout, contentAbout;
    private Button btnToWebSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        overridePendingTransition(R.anim.in, R.anim.out);

        initWidgets();
        String content = "Names inside the array list. \n ";
        // retrieve the intent
        Intent prevIntent = getIntent();

        // retriving the arrayList and the Book
        ArrayList<String> names = new ArrayList<>();
        Bundle bundle;
        Book newBook;
        try {
            names = prevIntent.getStringArrayListExtra(getString(R.string.names));
            bundle = prevIntent.getBundleExtra("bundle");
            newBook = bundle.getParcelable(getString(R.string.book));

            for (String s:
                    names ) {
                content += s +"\n";
            }
            
            content += newBook.getName() + " " + newBook.getAuthor() + " " + newBook.getPages();
        } catch (NullPointerException e){
            e.getStackTrace();
        }
        
        contentAbout.setText(content);
        // set on click al btn
        btnToWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(About.this, WebViewActvity.class);
                intent.putExtra("url", "https://tutorials-vert.vercel.app/");
                startActivity(intent);
            }
        });
    }

    private void initWidgets(){
        titleAbout = findViewById(R.id.titleAbout);
        contentAbout = findViewById(R.id.contentAbout);
        btnToWebSite = findViewById(R.id.btnToWebSite);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
    }
}