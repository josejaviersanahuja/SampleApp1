package com.zitrojjdev.sampleapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class SeeAllBooks extends AppCompatActivity {

    private RecyclerView booksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_books);

        // initializing
        booksRecView = findViewById(R.id.allBooks);

        // data array

        // adapter
    }
}