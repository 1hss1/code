package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import storage.FileInitialization;

public class Bookshelf {

    private ObservableList<Book> books = FXCollections.observableArrayList();

    public Bookshelf() {
        ObservableList<Book> books1 = FileInitialization.readObjets();
        this.books.addAll(books1);
//        this.;
    }

    public ObservableList<Book> getBooks() {
        return books;
    }
}
