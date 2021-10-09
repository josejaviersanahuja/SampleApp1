package com.zitrojjdev.sampleapp1;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String name, author, imageURL, description;
    private int pages;

    public Book(String name, String author, String imageURL, String description, int pages) {
        this.name = name;
        this.author = author;
        this.imageURL = imageURL;
        this.description = description;
        this.pages = pages;
    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        imageURL = in.readString();
        description = in.readString();
        pages = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", description='" + description + '\'' +
                ", pages=" + pages +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(author);
        parcel.writeString(imageURL);
        parcel.writeString(description);
        parcel.writeInt(pages);
    }
}
