package data;

import books.Book;
import com.main.LibrarySystem;
import exception.custom.IllegalAdminAccess;
import javafx.geometry.Insets;
import util.iMenu;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Student extends User implements iMenu {
    public static ArrayList<UserStudent> arr_userStudent = new ArrayList<>();

    public static class UserStudent {
        String nama, nim, fakultas, prodi;

        public UserStudent(String nama, String nim, String fakultas, String prodi) {
            this.nama = nama;
            this.nim = nim;
            this.fakultas = fakultas;
            this.prodi = prodi;
        }
    }

    @Override
    public void menu() {
        Stage studentMenuStage = new Stage();
        studentMenuStage.setTitle("Lib UMM - Menu Student");

        Label sceneTitle = new Label("Menu Student");

        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        Button listBookButton = new Button("Daftar Buku yang Tersedia");
        Button borrowedBookButton = new Button("Buku Terpinjam");
        Button borrowBookButton = new Button("Pinjam Buku");
        Button returnBookButton = new Button("Kembalikan Buku");
        Button logoutButton = new Button("Log Out");

        listBookButton.setPrefWidth(200);
        borrowedBookButton.setPrefWidth(200);
        borrowBookButton.setPrefWidth(200);
        returnBookButton.setPrefWidth(200);
        logoutButton.setPrefWidth(100);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle, 1, 0);
        grid.add(listBookButton, 1, 1);
        grid.add(borrowedBookButton, 1, 2);
        grid.add(borrowBookButton, 1, 3);
        grid.add(returnBookButton, 1, 4);
        grid.add(logoutButton, 1, 5);

        grid.setVgap(10);
        grid.setHgap(5);

        GridPane.setHalignment(logoutButton, HPos.CENTER);

        Scene studentMenuScene = new Scene(grid, 1360, 720);
        studentMenuStage.setScene(studentMenuScene);
        studentMenuStage.show();

        listBookButton.setOnAction(event -> {
            displayBook();
            studentMenuStage.close();
        });

        borrowedBookButton.setOnAction(event -> {
            showBorrowedBooks();
            studentMenuStage.close();
        });

        borrowBookButton.setOnAction(event -> {
            choiceBooks();
            studentMenuStage.close();
        });

        returnBookButton.setOnAction(event -> {
            returnBooks();
            studentMenuStage.close();
        });

        logoutButton.setOnAction(event -> {
            try {
                LibrarySystem librarySystemObj = new LibrarySystem();
                Stage primaryStage = new Stage();
                librarySystemObj.start(primaryStage);
                studentMenuStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void choiceBooks() {
        super.choiceBooks();
    }

    public void showBorrowedBooks() {
        Stage showBorrowedBooksStage = new Stage();
        showBorrowedBooksStage.setTitle("Informasi Buku Yang Dipinjam");

        TableView<Book> table = new TableView<>();
        table.setPrefSize(1360, 720);

        TableColumn<Book, String> idColumn = new TableColumn<>("ID Buku");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Nama Buku");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Penulis");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> categoryColumn = new TableColumn<>("Kategori");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, Integer> durationColumn = new TableColumn<>("Durasi");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        table.getColumns().add(idColumn);
        table.getColumns().add(titleColumn);
        table.getColumns().add(authorColumn);
        table.getColumns().add(categoryColumn);
        table.getColumns().add(durationColumn);

        for (Book a : Book.arr_borrowedBook) {
            for (Book i : Book.arr_bookList) {
                if (i.getBookId().equals(a.getBookId())) {
                    table.getItems().add(i);
                }
            }
        }

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(table, 0, 0);

        Button backButton = new Button("Kembali");
        backButton.setPrefSize(150, 40);
        gridPane.add(backButton, 0, 1);

        backButton.setOnAction(event -> {
            menu();
            showBorrowedBooksStage.close();
        });

        gridPane.setVgap(10);
        gridPane.setHgap(5);

        Scene scene = new Scene(gridPane, 840, 700);
        showBorrowedBooksStage.setScene(scene);
        showBorrowedBooksStage.show();
    }

    public void returnBooks() {
        Stage returnBooksStage = new Stage();
        returnBooksStage.setTitle("UMM Library - Pengembalian buku");

        Label headerTitle = new Label("Pengembalian buku");
        Label bookIdLabel = new Label("Inputkan ID buku yang ingin dikembalikan");

        Label submitSuccessLabel = new Label("Pengembalian berhasil");
        Label submitFailedLabel = new Label("Pengembalian gagal");

        headerTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        bookIdLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        headerTitle.setStyle("-fx-text-fill: #A91D3A;");
        submitSuccessLabel.setStyle("-fx-text-fill: #16FF00;");
        submitFailedLabel.setStyle("-fx-text-fill: #FF1E1E;");

        submitSuccessLabel.setVisible(false);
        submitFailedLabel.setVisible(false);

        TextField bookIdField = new TextField();

        Button submitButton = new Button("Kembalikan");
        Button returnButton = new Button("Kembali");

        TableView<Book> tableView = new TableView<>();

        TableColumn<Book, String> idBookColumn = new TableColumn<>("ID Buku");
        TableColumn<Book, String> titleBookColumn = new TableColumn<>("Nama Buku");

        idBookColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleBookColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        tableView.getColumns().addAll(idBookColumn, titleBookColumn);

        // Tambahkan buku yang dipinjam ke tabel
        tableView.getItems().addAll(Book.arr_borrowedBook);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(headerTitle, 0, 0, 2, 1); // Pindah header ke sebelah kiri atas
        grid.add(tableView, 0, 1, 2, 1); // Table spans two columns
        grid.add(bookIdLabel, 0, 2);
        grid.add(bookIdField, 0, 3);
        grid.add(submitSuccessLabel, 1, 2);
        grid.add(submitFailedLabel, 1, 2);
        grid.add(submitButton, 1, 5);
        grid.add(returnButton, 0, 5);

        Scene returnBookScene = new Scene(grid, 840, 700);
        returnBooksStage.setScene(returnBookScene);
        returnBooksStage.show();

        submitButton.setOnAction(event -> {
            boolean validasiReturnBooks = false;

            for (int i = 0; i < Book.arr_borrowedBook.size(); i++) {
                if (Book.arr_borrowedBook.get(i).getBookId().equals(bookIdField.getText())) {
                    for (Book book : Book.arr_bookList) {
                        if (book.getBookId().equals(bookIdField.getText())) {
                            int returnBook = book.getStock();
                            returnBook++;
                            book.setStock(returnBook);
                            Book.arr_borrowedBook.remove(i);
                            validasiReturnBooks = true;
                        }
                    }
                }
            }
            tableView.refresh();
            if (validasiReturnBooks) {
                submitSuccessLabel.setVisible(true);
                submitFailedLabel.setVisible(false);
            } else {
                submitFailedLabel.setVisible(true);
                submitSuccessLabel.setVisible(false);
            }
        });

        returnButton.setOnAction(event -> {
            menu();
            returnBooksStage.close();
        });
    }




    public void displayBook() {
        Stage displayBookStage = new Stage();
        displayBookStage.setTitle("Lib UMM - Daftar Buku");

        Label sceneTitle = new Label("Daftar Buku");
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

        double buttonWidth = 150;
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

    public boolean isStudents(TextField username) throws IllegalAdminAccess {
        if (username.getText().length() != 15) {
            throw new IllegalAdminAccess("NIM harus 15 digit!");
        }
        for (UserStudent i : arr_userStudent) {
            if (i.nim.equals(username.getText())) {
                return true;
            }
        }
        throw new IllegalAdminAccess("NIM tidak ditemukan!");
    }

    public void showLoginMenu() {
        // Implementasi menu login di sini
    }
}
