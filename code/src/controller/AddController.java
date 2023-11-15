package controller;

import entity.Book;
import entity.Bookshelf;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddController extends Controller<Bookshelf>{

    public static int indexCount = 3;

    private final String currentWorkingDirectory = System.getProperty("user.dir");
    @FXML
    private TextField textField1 ;

    @FXML
    private TextField textField2 ;

    public void addBook() {

        String text1 = textField1.getText();
        String text2 = textField2.getText();
        int total = getPageNum(currentWorkingDirectory + File.separator + indexCount + ".txt");
        this.model.getBooks().add(new Book(indexCount, text1, text2, "0/" + total));
        indexCount ++;

        this.stage.close();
    }

    private int getPageNum(String filePath) {
        Integer num = 0;
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;

            while ((line = reader.readLine()) != null) {
                num += 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }
}
