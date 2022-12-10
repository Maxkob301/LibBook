package com.example.libBook;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface ImageController {
    default Image getImage(String resultImageName){
        Image v = null;
        try {
            v = new Image(new FileInputStream("C:\\Users\\Маksim\\IdeaProjects\\LibBook\\src\\main\\java\\com\\example\\libBook\\ImagesBooks" + resultImageName + ".jpeg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return v;
    }
}