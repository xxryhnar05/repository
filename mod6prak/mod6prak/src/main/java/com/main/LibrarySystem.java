package com.main;

import data.Admin;
import data.Student;
import exception.custom.IllegalAdminAccess;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LibrarySystem extends Application {

    public static String NIM;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Admin adminObj = new Admin();
        Student studentObj = new Student();

        primaryStage.setTitle("Lib UMM");

        String imagePath = "file:///C:/Users/ADMIN/Documents/kuliah/pramuka/UMM.png";

        Image appIcon = new Image(imagePath);
        primaryStage.getIcons().add(appIcon);

        ImageView imageView = new ImageView(appIcon);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        Label sceneTitle = new Label("Perpustakaan");
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");

        Label errorLoginMessage = new Label("Pengguna tidak ditemukan");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        usernameLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        passwordLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        errorLoginMessage.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 12));

        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");
        errorLoginMessage.setStyle("-fx-text-fill: #FF1E1E;");

        errorLoginMessage.setVisible(false);

        Button loginButton = new Button("Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(imageView, 0, 0, 2, 1);
        grid.add(sceneTitle, 0, 1, 2, 1);

        grid.add(usernameLabel, 0, 2);
        grid.add(passwordLabel, 0, 3);
        grid.add(errorLoginMessage, 0, 4);

        grid.add(usernameField, 1, 2);
        grid.add(passwordField, 1, 3);

        grid.add(loginButton, 1, 4);

        grid.setVgap(10);
        grid.setHgap(5);

        Scene scene = new Scene(grid, 820, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        loginButton.setOnAction(event -> handleLogin(usernameField, passwordField, errorLoginMessage, adminObj, studentObj, primaryStage));

        usernameField.setOnAction(event -> passwordField.requestFocus());

        passwordField.setOnAction(event -> handleLogin(usernameField, passwordField, errorLoginMessage, adminObj, studentObj, primaryStage));
    }

    private void handleLogin(TextField usernameField, PasswordField passwordField, Label errorLoginMessage, Admin adminObj, Student studentObj, Stage primaryStage) {
        if (usernameField.getText().equals(Admin.adminusername) && passwordField.getText().equals(Admin.adminpassword)) {
            adminObj.menu();
            primaryStage.close();
        } else if (usernameField.getText().length() == 15 && passwordField.getText().length() < 15) {
            try {
                if (studentObj.isStudents(usernameField)) {
                    errorLoginMessage.setVisible(false);
                    studentObj.menu();
                    primaryStage.close();
                } else {
                    errorLoginMessage.setVisible(true);
                }
            } catch (IllegalAdminAccess pesanError) {
                errorLoginMessage.setText(pesanError.getMessage());
                errorLoginMessage.setVisible(true);
            }
        } else {
            errorLoginMessage.setVisible(true);
        }
    }
}
