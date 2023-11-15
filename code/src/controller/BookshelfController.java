package controller;

import entity.Book;
import entity.Bookshelf;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.ViewLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookshelfController extends Controller<Bookshelf> implements Initializable {

    @FXML
    private TableView tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public final Bookshelf getBookshelf() {
        return this.model;
    }

    public void delete() {
        Book selectedBook = (Book) tableView.getSelectionModel().getSelectedItem();

        this.model.getBooks().remove(selectedBook);

        tableView.refresh();
    }

    public void add() throws IOException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/storage/book_icon.png"));
        ViewLoader.showStage(this.model,
                "/view/Addiation.fxml",
                "Add a New Book", stage);

        tableView.refresh();
    }

    public void enter() throws IOException {

        Book selectedBook = (Book) tableView.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/storage/book_icon.png"));
            ViewLoader.showStage(selectedBook,
                    "/view/Reading.fxml",
                    "Read Your Selected Book", stage);
        }
        tableView.refresh();
    }

    public void exit() {
        if (stage != null) {
            stage.close();
        }
    }
}
