package com.zitrojjdev.sampleapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
    private Button btnAddWantToRead, btnAddAlreadyRead, btnAddCurrentlyReading, btnRemoveWantToRead, btnRemoveAlreadyRead, btnRemoveCurrentlyReading;
    private Book currentBook;
    private Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // back button?
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        overridePendingTransition(R.anim.in, R.anim.out);

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
        btnRemoveAlreadyRead = findViewById(R.id.btnRemoveAlreadyRead);
        btnRemoveWantToRead = findViewById(R.id.btnRemoveWantToRead);
        btnRemoveCurrentlyReading = findViewById(R.id.btnRemoveCurrentlyReading);
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

        // which button to show
        if (util.getAlreadyReadBooks().contains(book)){
            btnAddAlreadyRead.setVisibility(View.INVISIBLE);
            btnRemoveAlreadyRead.setVisibility(View.VISIBLE);
        } else {
            btnAddAlreadyRead.setVisibility(View.VISIBLE);
            btnRemoveAlreadyRead.setVisibility(View.INVISIBLE);
        }
        if (util.getCurrentlyReadingBooks().contains(book)){
            btnAddCurrentlyReading.setVisibility(View.INVISIBLE);
            btnRemoveCurrentlyReading.setVisibility(View.VISIBLE);
        } else {
            btnAddCurrentlyReading.setVisibility(View.VISIBLE);
            btnRemoveCurrentlyReading.setVisibility(View.INVISIBLE);
        }
        if (util.getWantToReadBooks().contains(book)){
            btnAddWantToRead.setVisibility(View.INVISIBLE);
            btnRemoveWantToRead.setVisibility(View.VISIBLE);
        } else {
            btnAddWantToRead.setVisibility(View.VISIBLE);
            btnRemoveWantToRead.setVisibility(View.INVISIBLE);
        }

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
                if (util.getWantToReadBooks().contains( book)){
                    // alert modal screen
                    builder.create().show();
                } else {
                    boolean isAdded = util.addWantToReadBook(book);
                    Toast.makeText(BookActivity.this, "Book added to the want to read list", Toast.LENGTH_LONG).show();
                    if (isAdded){
                        // switch visibility
                        btnRemoveWantToRead.setVisibility(View.VISIBLE);
                        btnAddWantToRead.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        btnAddAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (util.getAlreadyReadBooks().contains(book)){
                    // alert modal screen
                    builder.create().show();
                } else {
                    boolean isAdded = util.addAlreadyReadBook(book);
                    Toast.makeText(BookActivity.this, "Book added to the Already read list", Toast.LENGTH_LONG).show();
                    if (isAdded){
                        // switch visibility
                        btnRemoveAlreadyRead.setVisibility(View.VISIBLE);
                        btnAddAlreadyRead.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        btnAddCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (util.getCurrentlyReadingBooks().contains(book)){
                    builder.create().show();
                } else {
                    boolean isAdded = util.addCurrentlyReadingBook(book);
                    Toast.makeText(BookActivity.this, "Book added to the currently reading list", Toast.LENGTH_LONG).show();
                    if (isAdded){
                        // switch visibility
                        btnRemoveCurrentlyReading.setVisibility(View.VISIBLE);
                        btnAddCurrentlyReading.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        btnRemoveWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (util.getWantToReadBooks().contains(book)){
                    boolean isRemoved = util.removeWantToReadBook(book);
                    Toast.makeText(BookActivity.this, "Book removed from the currently reading list", Toast.LENGTH_LONG).show();
                    if (isRemoved){
                        // switch visibility
                        btnRemoveWantToRead.setVisibility(View.INVISIBLE);
                        btnAddWantToRead.setVisibility(View.VISIBLE);
                    }
                } else {
                    builder.create().show();
                }
            }
        });

        btnRemoveAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (util.getAlreadyReadBooks().contains(book)){
                    boolean isRemoved = util.removeAlreadyReadBook(book);
                    Toast.makeText(BookActivity.this, "Book removed from the currently reading list", Toast.LENGTH_LONG).show();
                    if (isRemoved){
                        // switch visibility
                        btnRemoveAlreadyRead.setVisibility(View.INVISIBLE);
                        btnAddAlreadyRead.setVisibility(View.VISIBLE);
                    }
                } else {
                    builder.create().show();
                }
            }
        });

        btnRemoveCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (util.getCurrentlyReadingBooks().contains(book)){
                    boolean isRemoved = util.removeCurrentlyReadingBook(book);
                    Toast.makeText(BookActivity.this, "Book removed from the currently reading list", Toast.LENGTH_LONG).show();
                    if (isRemoved){
                        // switch visibility
                        btnRemoveCurrentlyReading.setVisibility(View.INVISIBLE);
                        btnAddCurrentlyReading.setVisibility(View.VISIBLE);
                    }
                } else {
                    builder.create().show();
                }
            }
        });
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