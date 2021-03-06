package com.zitrojjdev.sampleapp1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AllBooksRecyclerViewAdapter extends RecyclerView.Adapter<AllBooksRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "AllBooksRecViewAdapter";

    // fields of this class
    private ArrayList<Book> listOfBooks = new ArrayList<>();
    private Context context;
    private Util util = new Util();


    public AllBooksRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    //this method will inflate or populate the data array with the layout we prepared before.
    // It won´t provide anything else
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // 99% of the times, this will be the code needed.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_books_recycler_view,null);
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_books_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // this method will provide the extra logic to our layout to get live interactions
    // or extra logic to add data after the first inflation
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.bookName.setText(listOfBooks.get(position).getName());
        holder.bookName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int index = holder.getAdapterPosition();
                Toast.makeText(context, listOfBooks.get(index).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // nuevo onclick listener
        holder.bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int index = holder.getAdapterPosition();
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("name", listOfBooks.get(index).getName());
                context.startActivity(intent);
            }
        });

        // onLongClickListener
        holder.bookCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final int index = holder.getAdapterPosition();
                Book book = listOfBooks.get(index);
                // alert modal screen
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Options");
                builder.setMessage("Choose an action");
                builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listOfBooks.remove(book);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setCancelable(false);
                switch (util.getType()){
                    case "WantToReadBooks":
                        builder.setNeutralButton("Move to current reading books", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                util.addCurrentlyReadingBook(book);
                                listOfBooks.remove(book);
                                notifyDataSetChanged();
                            }
                        });
                        builder.create().show();
                        break;
                    case "AlreadyReadBooks":
                        builder.setNeutralButton("Move to want to read books", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                util.addWantToReadBook(book);
                                listOfBooks.remove(book);
                                notifyDataSetChanged();
                            }
                        });
                        builder.create().show();
                        break;
                    case "CurrentlyReadingBooks":
                        builder.setNeutralButton("Move to already read books", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                util.addAlreadyReadBook(book);
                                listOfBooks.remove(book);
                                notifyDataSetChanged();
                            }
                        });
                        builder.create().show();
                        break;
                    default: break;
                }

                return false;
            }
        });
        // Aquí añadimos la url Image usando una librería externa con Glide
        Glide.with(context)
                .asBitmap()
                .load(listOfBooks.get(position).getImageURL())
                .into(holder.bookImage);
    }

    // just passing the size od the data array
    @Override
    public int getItemCount() {
        return listOfBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView bookImage;
        private TextView bookName;
        private CardView bookCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookImage = itemView.findViewById(R.id.imgBook);
            bookName = itemView.findViewById(R.id.titleBook);
            bookCard = itemView.findViewById(R.id.bookCard);

        }
    }

    // setter of the list of books
    public void setListOfBooks(ArrayList<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
        // next line is like a listener that will trigger the update the view of the RecyclerView
        notifyDataSetChanged();
    }
}
