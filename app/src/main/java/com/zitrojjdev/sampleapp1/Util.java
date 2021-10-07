package com.zitrojjdev.sampleapp1;

import java.util.ArrayList;
import java.util.Random;

public class Util {
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> alreadyReadBooks;

    public Util() {
        if (allBooks == null){
            allBooks = new ArrayList<>();
            initAllBooks();
        }
        if (currentlyReadingBooks == null){
            currentlyReadingBooks = new ArrayList<>();
        }
        if (wantToReadBooks == null){
            wantToReadBooks = new ArrayList<>();
        }
        if (alreadyReadBooks == null){
            alreadyReadBooks = new ArrayList<>();
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public boolean addCurrentlyReadingBook(Book book){
        return currentlyReadingBooks.add(book);
    }
    public boolean addAlreadyReadBook(Book book){
        return alreadyReadBooks.add(book);
    }
    public boolean addWantToReadBook(Book book){
        return wantToReadBooks.add(book);
    }

    public boolean removeCurrentlyReadingBook(Book book){
        return currentlyReadingBooks.remove(book);
    }
    public boolean removeAlreadyReadBook(Book book){
        return alreadyReadBooks.remove(book);
    }
    public boolean removeWantToReadBook(Book book){
        return wantToReadBooks.remove(book);
    }

    private static void initAllBooks(){
        String name, author, imageURL, description;
        Random r = new Random();
        int pages;
        for (int i = 1; i < 100 ; i++) {
            name = "book number " + i;
            author = "Author number " + i;
            description = "The description is number " + i;
            pages = r.nextInt(500) +100;
            if (i<10){
                imageURL = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/00" + i +".png";
            } else {
                imageURL = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/0" + i +".png";
            }
            Book newBook = new Book(name, author, imageURL, description, pages);
            allBooks.add(newBook);
        }
    }
}
