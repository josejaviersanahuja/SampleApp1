package com.zitrojjdev.sampleapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class WantToReadBooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read_books);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        overridePendingTransition(R.anim.in, R.anim.out);

        RecyclerView booksRecView = findViewById(R.id.wantToReadBooksRecView);
        // data array
        ArrayList<Book> books = new ArrayList<>();

        Util util = new Util();
        util.setType("WantToReadBooks");
        books = util.getWantToReadBooks();
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
                // overridePendingTransition(R.anim.close_in, R.anim.close_out);
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