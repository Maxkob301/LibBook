package com.example.myapp;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import Interfaces.ImageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class BooksCatalogController implements ImageController {
    DataBaseHandler db = new DataBaseHandler();
    @FXML
    private Button AddId;

    @FXML
    private Button SearchId;

    @FXML
    protected TextField TextId;
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
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            ImageId.setImage(getImage(resultImage));
            ImageId.setId(searchText);

        });
        ImageId.setOnMouseClicked(event -> openNewScene("ViewBook.fxml"));
    }
    public void createDialogWindow() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("SelectNameBook");
        dialog.setHeaderText("Введите название книги");
        dialog.setContentText("Book:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> db.CreateImageName(name));

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