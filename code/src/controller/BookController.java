package controller;

import entity.Book;
import entity.Bookshelf;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;

import storage.FileInitialization;

public class BookController extends Controller<Book> implements Initializable {

    @FXML
    private TextArea novelTextArea;

    private int page;
    private int total;

    private static final String UTF8_PATTERN = "[^\\x00-\\x7F]";

    private final String currentWorkingDirectory = System.getProperty("user.dir");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        novelTextArea.setWrapText(true);

        this.page = Integer.parseInt(this.model.getRead().split("/")[0]);
        this.total = Integer.parseInt(this.model.getRead().split("/")[1]);

        loadPage(6, page, currentWorkingDirectory +
                File.separator +
                String.valueOf(this.model.getIndex()) + ".txt");
    }

    public void previousPage() {

        page += 1;
        this.model.setRead(page + "/" + total);

        loadPage(6, page, currentWorkingDirectory +
                File.separator +
                String.valueOf(this.model.getIndex()) + ".txt");
    }

    public void nextPage() {

        page -= 1;
        this.model.setRead(page + "/" + total);

        loadPage(6, page, currentWorkingDirectory +
                File.separator +
                String.valueOf(this.model.getIndex()) + ".txt");
    }

    public void exit() {

        ObservableList<Book> books1 = FileInitialization.readObjets();
        for (Book book:books1) {
            if (Objects.equals(book.getIndex(), this.model.getIndex())) {
                book.setRead(this.model.getRead());
            }
        }

        FileInitialization.writeObject(books1);

        if (stage != null) {
            stage.close();
        }
    }

    private void loadPage(int linesPerPage, int currentPage, String filePath) {
        // get input stream of the target file
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath));
             // given the encoding type of file: UTF_8
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            StringBuilder pageContent = new StringBuilder();
            String line;
            // skip the pages which have been read.
            for (int i = 0; i < linesPerPage * (currentPage - 1); i++) {
                if ((line = reader.readLine()) == null) {
                    break;
                }
            }

            // get the content in the current pages. for lines per page.
            for (int i = 0; i < linesPerPage; i++) {
                if ((line = reader.readLine()) != null) {
                    // moreover, in the file UTF_8 reading, there still some error coding, replace them all.
                    line = line.replaceAll(UTF8_PATTERN, "");
                    pageContent.append(line).append("\n");
                } else {
                    break;
                }
            }
            // set the content in the front.
            novelTextArea.setText(pageContent.toString());
        // catch the exception in case.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
