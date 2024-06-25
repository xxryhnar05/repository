package data;

import books.Book;
import com.main.LibrarySystem;
import util.iMenu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random;

public class Admin extends User implements iMenu {

    public static String adminusername = "adm1n";
    public static String adminpassword = "adm1n";

    @Override
    public void menu() {
        Stage adminMenuStage = new Stage();
        adminMenuStage.setTitle("Lib UMM - Admin Menu");

        Label sceneTitle = new Label("Menu Admin :");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        Button addStudentButton     = new Button("Tambah Mahasiswa");
        Button displayStudentButton = new Button("Daftar Mahasiswa");
        Button addBookButton        = new Button("Tambah Buku");
        Button listBookButton       = new Button("Daftar Buku yang Tersedia");
        Button logoutButton         = new Button("Logout");

        double buttonWidth = 200;
        double buttonHeight = 40;

        addStudentButton.setPrefSize(buttonWidth, buttonHeight);
        displayStudentButton.setPrefSize(buttonWidth, buttonHeight);
        addBookButton.setPrefSize(buttonWidth, buttonHeight);
        listBookButton.setPrefSize(buttonWidth, buttonHeight);
        logoutButton.setPrefSize(buttonWidth, buttonHeight);  // Adjusted width to match others

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle, 0, 0, 2, 1);
        grid.add(addStudentButton, 0, 1);
        grid.add(displayStudentButton, 0, 2);
        grid.add(addBookButton, 0, 3);
        grid.add(listBookButton, 0, 4);
        grid.add(logoutButton, 0, 5);  // Placed logout button directly below the listBookButton

        grid.setVgap(10);
        grid.setHgap(6);

        Scene scene = new Scene(grid, 1360, 720);
        adminMenuStage.setScene(scene);
        adminMenuStage.show();

        addStudentButton.setOnAction(event -> {
            addstudent();
            adminMenuStage.close();
        });

        displayStudentButton.setOnAction(event -> {
            displaystudent();
            adminMenuStage.close();
        });

        addBookButton.setOnAction(event -> {
            inputBook();
            adminMenuStage.close();
        });

        listBookButton.setOnAction(event -> {
            displayBook();
            adminMenuStage.close();
        });

        logoutButton.setOnAction(event -> {
            try {
                LibrarySystem librarySystemObj = new LibrarySystem();
                Stage primaryStage = new Stage();
                librarySystemObj.start(primaryStage);
                adminMenuStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addstudent() {
        Stage addStudentStage = new Stage();
        addStudentStage.setTitle("Tambah Mahasiswa");

        Label sceneTitle    = new Label("Tambah Mahasiswa");
        Label nameLabel     = new Label("Nama");
        Label nimLabel      = new Label("NIM");
        Label fakultasLabel = new Label("Fakultas");
        Label jurusanLabel  = new Label("Jurusan");

        Label sumbitFailed = new Label("NIM harus 15 digit!");
        sumbitFailed.setVisible(false);

        TextField nameField     = new TextField();
        TextField nimField      = new TextField();
        TextField fakultasField = new TextField();
        TextField jurusanField  = new TextField();

        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        nameLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        nimLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        fakultasLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        jurusanLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");
        sumbitFailed.setStyle("-fx-text-fill: #FF1E1E;");

        Button submitButton = new Button("Submit");
        Button backButton = new Button("Kembali");

        double buttonWidth = 200;
        double buttonHeight = 40;

        submitButton.setPrefSize(buttonWidth, buttonHeight);
        backButton.setPrefSize(buttonWidth, buttonHeight);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(sceneTitle, 0, 0, 2, 1);

        grid.add(nameLabel, 0, 1);
        grid.add(nimLabel, 0, 2);
        grid.add(fakultasLabel, 0, 3);
        grid.add(jurusanLabel, 0, 4);

        grid.add(nameField, 1, 1);
        grid.add(nimField, 1, 2);
        grid.add(fakultasField, 1, 3);
        grid.add(jurusanField, 1, 4);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.add(backButton, 0, 0);
        buttonGrid.add(submitButton, 1, 0);

        grid.add(buttonGrid, 1, 5);
        grid.add(sumbitFailed, 0, 6, 2, 1);

        grid.setVgap(10);
        grid.setHgap(5);

        Scene scene = new Scene(grid, 840, 700);
        addStudentStage.setScene(scene);
        addStudentStage.show();

        submitButton.setOnAction(event -> handleSubmit(nameField, nimField, fakultasField, jurusanField, sumbitFailed, addStudentStage));
        backButton.setOnAction(event -> {
            menu();
            addStudentStage.close();
        });

        nameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSubmit(nameField, nimField, fakultasField, jurusanField, sumbitFailed, addStudentStage);
            }
        });
        nimField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSubmit(nameField, nimField, fakultasField, jurusanField, sumbitFailed, addStudentStage);
            }
        });
        fakultasField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSubmit(nameField, nimField, fakultasField, jurusanField, sumbitFailed, addStudentStage);
            }
        });
        jurusanField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSubmit(nameField, nimField, fakultasField, jurusanField, sumbitFailed, addStudentStage);
            }
        });
    }

    private void handleSubmit(TextField nameField, TextField nimField, TextField fakultasField, TextField jurusanField, Label sumbitFailed, Stage stage) {
        if (nimField.getText().length() == 15) {
            Student.arr_userStudent.add(new Student.UserStudent(nameField.getText(), nimField.getText(), fakultasField.getText(), jurusanField.getText()));
            menu();
            stage.close();
        } else {
            sumbitFailed.setVisible(true);
        }
    }

    public void displaystudent() {
        Stage displayStudentStage = new Stage();
        displayStudentStage.setTitle("Lib UMM - Daftar Mahasiswa");

        Label sceneTitle    = new Label("Daftar Mahasiswa");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        ListView<String> listView = new ListView<>();

        for (Student.UserStudent i : Student.arr_userStudent) {
            String studentInfo = "Nama     : " + i.nama +"\n" +
                    "NIM      : " + i.nim + "\n" +
                    "Fakultas : " + i.fakultas + "\n" +
                    "Prodi    : " + i.prodi + "\n" +
                    "===========================";
            listView.getItems().add(studentInfo);
        }

        Button backButton = new Button("Kembali");
        backButton.setStyle("-fx-background-color: grey; -fx-text-fill: white;");

        double buttonWidth = 200;
        double buttonHeight = 40;

        backButton.setPrefSize(buttonWidth, buttonHeight);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: white;");

        grid.add(sceneTitle, 0, 0);
        grid.add(listView, 0, 1);
        grid.add(backButton, 0, 2);

        grid.setVgap(5);

        Scene scene = new Scene(grid, 840, 700);
        displayStudentStage.setScene(scene);
        displayStudentStage.show();

        backButton.setOnAction(event -> {
            menu();
            displayStudentStage.close();
        });
    }

    public void inputBook() {
        super.inputBook();
    }

    public void displayBook() {
        Stage displayBookStage = new Stage();
        displayBookStage.setTitle("Lib UMM - Daftar Buku yang Tersedia");

        Label sceneTitle = new Label("Daftar Buku yang Tersedia");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        ListView<String> listView = new ListView<>();

        for (Book book : Book.arr_bookList) {
            String bookInfo = "Judul     : " + book.getTitle() + "\n" +
                    "Penulis   : " + book.getAuthor() + "\n" +
                    "Kategori  : " + book.getCategory() + "\n" +
                    "Stok      : " + book.getStock() + "\n" +
                    "===========================";
            listView.getItems().add(bookInfo);
        }

        Button backButton = new Button("Kembali");
        backButton.setStyle("-fx-background-color: grey; -fx-text-fill: white;");

        double buttonWidth = 200;
        double buttonHeight = 40;

        backButton.setPrefSize(buttonWidth, buttonHeight);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: white;");

        grid.add(sceneTitle, 0, 0);
        grid.add(listView, 0, 1);
        grid.add(backButton, 0, 2);

        grid.setVgap(5);

        Scene scene = new Scene(grid, 840, 700);
        displayBookStage.setScene(scene);
        displayBookStage.show();

        backButton.setOnAction(event -> {
            menu();
            displayBookStage.close();
        });
    }

    public String generateId() {
        Random random = new Random();

        StringBuilder idTengah = new StringBuilder();
        StringBuilder idAkhir = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            idTengah.append(random.nextInt(10));
            idAkhir.append(random.nextInt(10));
        }
        return ("UMM-" + idTengah + "-" + idAkhir);
    }
}
