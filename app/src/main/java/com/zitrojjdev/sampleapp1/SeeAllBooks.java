package com.zitrojjdev.sampleapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class SeeAllBooks extends AppCompatActivity {

    private RecyclerView booksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_books);

        // initializing
        booksRecView = findViewById(R.id.allBooks);

        // data array
        ArrayList<Book> books = new ArrayList<>();

        //adding books
        books.add(new Book("1Q84","Haruki Murakami", "https://images.gr-assets.com/bokks/14831033311/10357575.jpg","A work of maddeling brilliance",1350 ));
        books.add(new Book("Iliad","Homer", "https://images-na.ssl-images-amazon.com/images/I/AlsXo113HML.jpg","Greek heroes and tragedies",1000 ));
        books.add(new Book("Beyond Good and Evil","Nietzsche", "https://pressbooks.com/app/uploads/sites/27444/2014/03/beyondgoogandevill.jpg","The philosofo",350 ));

        // adapter
        AllBooksRecyclerViewAdapter adapter = new AllBooksRecyclerViewAdapter(this);
        booksRecView.setAdapter(adapter);

        // we are setting something important here regarding the layout. could be linear or grid
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        // we set the array of books
        adapter.setListOfBooks(books);
    }
}