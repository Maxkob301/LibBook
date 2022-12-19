package Interfaces;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface ImageController {
    default Image getImage(String resultImageName){
        Image v;
        try {
            v = new Image(new FileInputStream( "C:\\LibBook\\src\\main\\java\\ImagesBooks" + "\\" + resultImageName + ".jpeg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return v;
    }
}
