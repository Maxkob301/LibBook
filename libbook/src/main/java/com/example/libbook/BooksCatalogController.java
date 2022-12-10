package com.example.libbook;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class BooksCatalogController implements com.example.libBook.ImageController {
    com.example.libBook.DataBaseHandler db = new com.example.libBook.DataBaseHandler();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoadButton;

    @FXML
    private Button AddId;

    @FXML
    private Button SearchId;

    @FXML
    private TextField Textid;
    @FXML
    private ImageView ImageId;


    @FXML
    void initialize() {
       AddId.setOnAction(event -> {
           String name = Textid.getText();
           db.CreateImageName(name);
       });
       SearchId.setOnAction(event ->{
           String searchText = Textid.getText();
           String resultImage = null;
           try {
               resultImage = db.getImageFromTable(searchText);
           } catch (SQLException e) {
               throw new RuntimeException(e);
           } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
           }
           ImageId.setImage(getImage(resultImage));
           ImageId.setId(searchText);

       });
    }

}
