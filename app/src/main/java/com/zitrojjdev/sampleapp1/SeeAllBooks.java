package com.zitrojjdev.sampleapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class SeeAllBooks extends AppCompatActivity {

    private RecyclerView booksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_books);

        // back button?
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // initializing
        booksRecView = findViewById(R.id.allBooks);

        // data array
        ArrayList<Book> books = new ArrayList<>();

        Util util = new Util();
        books = util.getAllBooks();
        // adapter
        AllBooksRecyclerViewAdapter adapter = new AllBooksRecyclerViewAdapter(this);
        booksRecView.setAdapter(adapter);

        // we are setting something important here regarding the layout. could be linear or grid
        booksRecView.setLayoutManager(new GridLayoutManager(this, 2));

        // we set the array of books
        adapter.setListOfBooks(books);
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
}