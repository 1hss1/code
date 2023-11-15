import entity.Bookshelf;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.ViewLoader;

import static javafx.application.Application.launch;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/storage/book_icon.png"));
        ViewLoader.showStage(new Bookshelf(), "/view/Book.fxml", "Your Bookshelf", stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}