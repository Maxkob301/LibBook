package com.example.myapp;

import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;


public class DataBaseHandler extends Configs {

    File pathImagesDirectory = new File("C:\\LibBook\\src\\main\\java\\ImagesBooks");
    File pathBooksTxtDirectory = new File("C:\\LibBook\\src\\main\\java\\BooksTxt");


    FileChooser chooser = new FileChooser();


    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException,SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return  dbConnection;
    }

    public String getImageFromTable(String nameImage) throws SQLException, ClassNotFoundException {
            ResultSet resultSet;
            String name = null;
            Statement statement = getDbConnection().createStatement();
            String select = "SELECT * FROM booksname WHERE Books " + "= " + "'" + nameImage + "'";
            resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                name = resultSet.getString("Books");
            }
            return name;
    }
    //Добавляем файл и его название в базу данных images
    public void CreateImageName(String nameImage){
        //SQL запрос добавления имени и файла в таблицу
        String insert = "INSERT INTO booksname (Books)" + "VALUES(" + "\"" + nameImage + "\"" + ")";
        File filePath;
        File filePath2;
        try {
            filePath = chooser.showOpenDialog(null);
            if(filePath == null){
                System.out.println();
            }else {
                //Создание соединения
                Statement statement = getDbConnection().createStatement();
                statement.executeUpdate(insert);
                FileUtils.copyFileToDirectory(filePath, pathImagesDirectory);
                File file1 = new File(pathImagesDirectory + "\\" + filePath.getName());
                File file2 = new File(pathImagesDirectory + "\\" + nameImage + ".jpeg");
                FileUtils.moveFile(file1, file2);
                filePath2 = chooser.showOpenDialog(null);
                FileUtils.copyFileToDirectory(filePath2,pathBooksTxtDirectory);
                File file3 = new File(pathBooksTxtDirectory + "\\" + filePath2.getName());
                File file4 = new File(pathBooksTxtDirectory + "\\" + nameImage + ".txt");
                FileUtils.moveFile(file3,file4);
            }} catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void removeNameImage()  {
        Statement statement;
        File fileRemove;
        try {
            statement = getDbConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        fileRemove = chooser.showOpenDialog(null);
        if(fileRemove == null){
            System.out.println();
        }else {
            String remove = "DELETE FROM booksname" + " WHERE " + "(" + "\"Books\"" + " = " + "\"" + fileRemove.getName() + "\"" + ")";
            File deleteFile = new File(pathImagesDirectory + "\\" + fileRemove.getName());
            if (deleteFile.delete()) {
                System.out.println("Удалено успешно");
            } else {
                System.out.println("Не удалено");
            }
            try {
                statement.executeUpdate(remove);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }}

