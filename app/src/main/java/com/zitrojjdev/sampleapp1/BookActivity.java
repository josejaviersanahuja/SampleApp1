package com.zitrojjdev.sampleapp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private TextView title, author, description;
    private ImageView imageBook;
    private Button btnAddWantToRead, btnAddAlreadyRead, btnAddCurrentlyReading;
    private Book currentBook;
    private Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // initializing UI
        initWidgets();

        // Getting all arrays
        ArrayList<Book> allBooks = util.getAllBooks();

        // Retrieving current book
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        // Init current Book
        for (Book b: allBooks){
            if (b.getName().equals(name)){
                // populating current book in UI
                currentBook = b;
                populateCurrentBook(currentBook);
                break;
            }
        }

        // setting the listeners
        settingOnClickListeners(util, currentBook);

    }

    private void initWidgets(){
        title = findViewById(R.id.titleBook);
        author = findViewById(R.id.detailAuthor);
        description = findViewById(R.id.detailDescription);
        imageBook = findViewById(R.id.detailImgBook);
        btnAddAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnAddWantToRead = findViewById(R.id.btnWantToRead);
        btnAddCurrentlyReading = findViewById(R.id.btnCurrentlyReading);

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

    private void settingOnClickListeners (Util util, Book book){
        // alert modal screen
        AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);
        builder.setMessage("You already added this book on this list");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setCancelable(false);

        // all listeners
        btnAddWantToRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isInList(util.getWantToReadBooks(), book)){
                    // alert modal screen
                    builder.create().show();
                } else {
                    util.addWantToReadBook(book);
                    Toast.makeText(BookActivity.this, "Book added to the want to read list", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAddAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInList(util.getAlreadyReadBooks(), book)){
                    // alert modal screen
                    builder.create().show();
                } else {
                    util.addAlreadyReadBook(book);
                    Toast.makeText(BookActivity.this, "Book added to the Already read list", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAddCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInList(util.getCurrentlyReadingBooks(), book)){
                    builder.create().show();
                } else {
                    util.addCurrentlyReadingBook(book);
                    Toast.makeText(BookActivity.this, "Book added to the currently reading list", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isInList(ArrayList<Book> list, Book book){
        boolean output = false;
        for (Book b: list){
            if (b.getName().equals(book.getName())){
                output = true;
            }
        }
        return output;
    }

}