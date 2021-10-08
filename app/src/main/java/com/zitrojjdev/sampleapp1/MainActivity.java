package com.zitrojjdev.sampleapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnSeeAllBooks, btnCurrentlyReading, btnWantToRead, btnAlreadyRead, btnAbout;
    private TextView mainActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.in, R.anim.out);

        initialWidgets();
    }

    private void initialWidgets(){
        //All buttons initialization
        btnAbout = findViewById(R.id.aboutBtn);
        btnAbout.setOnClickListener(this);
        btnAlreadyRead = findViewById(R.id.alreadyReadBtn);
        btnAlreadyRead.setOnClickListener(this);
        btnCurrentlyReading = findViewById(R.id.currentlyReadingBtn);
        btnCurrentlyReading.setOnClickListener(this);
        btnSeeAllBooks = findViewById(R.id.seeAllBooksBtn);
        btnSeeAllBooks.setOnClickListener(this);
        btnWantToRead = findViewById(R.id.wantToReadBtn);
        btnWantToRead.setOnClickListener(this);

        //textview
        mainActivityTitle = findViewById(R.id.titleMainActivity);
    }

    @Override
    public void onClick(@NonNull View view) {
        switch(view.getId()){
            case R.id.aboutBtn:
                Intent intentAbout = new Intent(MainActivity.this, About.class);
                startActivity(intentAbout);
                break;
            case R.id.seeAllBooksBtn:
                Intent intentSeeAllBooks = new Intent(MainActivity.this, SeeAllBooks.class);
                startActivity(intentSeeAllBooks);
                break;
            case R.id.currentlyReadingBtn:
                Intent intentCurrentlyReading = new Intent(MainActivity.this, CurrentlyReadingBooks.class);
                startActivity(intentCurrentlyReading);
                break;
            case R.id.wantToReadBtn:
                Intent intentWantToRead = new Intent(MainActivity.this, WantToReadBooks.class);
                startActivity(intentWantToRead);
                break;
            case R.id.alreadyReadBtn:
                Intent intentAlreadyRead = new Intent(MainActivity.this, AlreadyReadBooks.class);
                startActivity(intentAlreadyRead);
                break;
            default:
                System.out.println("NO ENTRO EN NINGUN LUGAR" + view.getId());
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
    }
}

