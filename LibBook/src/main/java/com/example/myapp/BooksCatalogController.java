package com.example.myapp;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import Interfaces.ImageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class BooksCatalogController implements ImageController {
    private final DataBaseHandler db = new DataBaseHandler();
    @FXML
    private Button AddId;

    @FXML
    private Button SearchId;

    @FXML
    private TextField TextId;
    @FXML
    private ImageView ImageId;
    protected static String res;



    @FXML
    void initialize() {
         AddId.setOnAction(event -> createDialogWindow());

        SearchId.setOnAction(event ->{
            String searchText = TextId.getText();
            BooksCatalogController.res = searchText;
            String resultImage;
            try {
                resultImage = db.getImageFromTable(searchText);
                ImageId.setImage(getImage(resultImage));
                ImageId.setId(searchText);
           } catch (SQLException | ClassNotFoundException e ) {
               throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Поиск значения в таблице");
                alert.setHeaderText("Результат:");
                alert.setContentText("Нет такой книги");
                alert.showAndWait();
            }
        });
        ImageId.setOnMouseClicked(event -> openNewScene("ViewBook.fxml"));
    }
    public void createDialogWindow() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("SelectNameBook");
        dialog.setHeaderText("Введите название книги");
        dialog.setContentText("Book:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(db::CreateImageName);

    }
    public void openNewScene(String window) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloController.class.getResource(window));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


}