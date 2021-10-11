package com.zitrojjdev.sampleapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnSeeAllBooks, btnCurrentlyReading, btnWantToRead, btnAlreadyRead, btnAbout, btnTest;
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
        btnTest = findViewById(R.id.testBtn);
        btnTest.setOnClickListener(this);
        //textview
        mainActivityTitle = findViewById(R.id.titleMainActivity);
    }

    @Override
    public void onClick(@NonNull View view) {
        switch(view.getId()){
            case R.id.aboutBtn:
                // creating an arrayList to pass in the intent
                ArrayList<String> array = new ArrayList<>();
                array.add("jose javier");
                array.add("ariannys");

                // creating a book to pass
                Book newBook = new Book(
                        "passing objects",
                        "zitrojj",
                        "https://media2.giphy.com/media/3kQqYPyzGSQ6I/giphy.gif?cid=7028f486h2d362eiipaxprq2nam6suzselgmzr6365m1x8q7&rid=giphy.gif&ct=s",
                        "It is is like this",
                        100
                );

                //create the intent
                Intent intentAbout = new Intent(MainActivity.this, About.class);

                // passing the array list
                intentAbout.putExtra(getString(R.string.names), array);

                // passing the book to a bundle
                Bundle bundle = new Bundle();
                bundle.putParcelable(getString(R.string.book), newBook);

                // passing the bundle to the intent
                intentAbout.putExtra("bundle", bundle);

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
            case R.id.testBtn:
                Intent intentTest = new Intent(MainActivity.this, TestActivity.class);
                //intentTest.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intentTest.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity( intentTest);
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

