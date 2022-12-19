package Interfaces;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface ImageController {
    default Image getImage(String resultImageName) throws FileNotFoundException {
        Image v;
            v = new Image(new FileInputStream( "C:\\LibBook\\src\\main\\java\\ImagesBooks" + "\\" + resultImageName + ".jpeg"));
        return v;
    }
}
