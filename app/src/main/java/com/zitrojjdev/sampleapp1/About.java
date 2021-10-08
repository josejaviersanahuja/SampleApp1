package com.zitrojjdev.sampleapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        contentAbout.setText("Ahora puedes navegar a mi página web de tutoriales que he escrito a lo largo de 1 año de estudios. " +
                "\n Quiero que revises el último menú sobre android apps con java. Chao");
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