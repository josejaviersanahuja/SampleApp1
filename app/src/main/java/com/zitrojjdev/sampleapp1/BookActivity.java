package com.zitrojjdev.sampleapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private TextView title, author, description;
    private ImageView imageBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initWidgets();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        Util util = new Util();
        ArrayList<Book> allBooks = util.getAllBooks();
        Book currentBook = allBooks.get(position);

        populateCurrentBook(currentBook);

    }

    private void initWidgets(){
        title = findViewById(R.id.titleBook);
        author = findViewById(R.id.detailAuthor);
        description = findViewById(R.id.detailDescription);
        imageBook = findViewById(R.id.detailImgBook);
    }

    private void populateCurrentBook(Book book){
        title.setText(book.getName());
        author.setText(book.getAuthor());
        String finalDescription = book.getDescription() + "\nNumber of Pages: " +book.getPages();
        description.setText(finalDescription);
        Glide.with(this)
                .asBitmap()
                .load(book.getImageURL())
                .into(imageBook);
    }
}