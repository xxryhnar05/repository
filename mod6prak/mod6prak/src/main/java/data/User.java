package data;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class User {

    public void choiceBooks() {
        Stage choiceBooksStage = new Stage();
        choiceBooksStage.setTitle("Lib UMM - Pilih Buku");

        TableView<Book> tableView = new TableView<>();

        TableColumn<Book, String> idColumn = new TableColumn<>("ID Buku");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Nama Buku");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Penulis");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> categoryColumn = new TableColumn<>("Kategori");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stok");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, categoryColumn, stockColumn);

        // Pastikan buku-buku ditambahkan ke TableView
        tableView.getItems().setAll(Book.arr_bookList);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(tableView, 0, 0);

        Label bookIdLabel = new Label("Input ID buku yang ingin dipinjam:");
        grid.add(bookIdLabel, 0, 1);

        TextField bookIdField = new TextField();
        grid.add(bookIdField, 0, 2);

        Label durationLabel = new Label("Berapa hari ingin meminjam buku? (Max 14 hari)");
        TextField durationField = new TextField();
        durationField.setPromptText("Berapa hari ?");
        grid.add(durationLabel, 0, 3);
        grid.add(durationField, 0, 4);

        Button submitButton = new Button("Submit");
        grid.add(submitButton, 0, 5);

        Label messageLabel = new Label();
        grid.add(messageLabel, 0, 6);

        Button backButton = new Button("Kembali");
        backButton.setPrefSize(150, 40);
        grid.add(backButton, 0, 7);

        Scene scene = new Scene(grid, 600, 400);  // Sesuaikan ukuran window jika perlu
        choiceBooksStage.setScene(scene);
        choiceBooksStage.show();

        // Buat instance dari Student
        Student studentObj = new Student();

        submitButton.setOnAction(e -> {
            boolean validasi = false;
            String idBukuYangDipinjam = bookIdField.getText();

            for (Book i : Book.arr_bookList) {
                if (i.getBookId().equals(idBukuYangDipinjam)) {
                    if (i.getStock() > 0) {
                        int a = i.getStock();
                        a--;
                        i.setStock(a);

                        int inputwaktuPinjaman = Integer.parseInt(durationField.getText());

                        if (inputwaktuPinjaman < 15) {
                            i.setDuration(inputwaktuPinjaman);
                            Book.arr_borrowedBook.add(new Book(i.getBookId(), i.getStock(), i.getDuration()));
                            validasi = true;
                            break;
                        } else {
                            messageLabel.setText("Max 14 hari");
                        }
                    } else if (i.getStock() == 0) {
                        messageLabel.setText("== Stok buku habis! ==");
                        studentObj.menu(); // Panggil menu dari instance Student
                        choiceBooksStage.close();
                    }
                }
            }
            if (validasi) {
                messageLabel.setText("==== Buku berhasil dipinjam! ====");
            } else {
                messageLabel.setText("== ID tidak ditemukan! ==");
            }
        });

        backButton.setOnAction(event -> {
            studentObj.menu();
            choiceBooksStage.close();
        });
    }


    public void inputBook() {
        Book textBookObj = new TextBook();
        Book storyBookObj = new StoryBook();
        Book historyBookObj = new HistoryBook();

        Stage inputBookStage = new Stage();
        inputBookStage.setTitle("Lib UMM - Input Book");

        Label sceneTitle = new Label("Tambah Buku");
        Label chooseBook = new Label("Pilih kategori buku :");

        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        chooseBook.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        Button historyBookButton = new Button("History Book");
        Button storyBookButton = new Button("Story Book");
        Button textBookButton = new Button("Text Book");
        Button backButton = new Button("Kembali");

        historyBookButton.setPrefWidth(200);
        storyBookButton.setPrefWidth(200);
        textBookButton.setPrefWidth(200);
        backButton.setPrefWidth(100);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle, 1, 0);
        grid.add(chooseBook, 1, 1);
        grid.add(historyBookButton, 1, 2);
        grid.add(storyBookButton, 1, 3);
        grid.add(textBookButton, 1, 4);
        grid.add(backButton, 1, 5);

        grid.setVgap(10);
        grid.setHgap(5);

        GridPane.setHalignment(backButton, HPos.CENTER);

        Scene scene = new Scene(grid, 1360, 720);
        inputBookStage.setScene(scene);
        inputBookStage.show();

        historyBookButton.setOnAction(event -> {
            inputBookStage.close();
            addBook(historyBookObj, "Lib UMM - Add History Book ", "History Book", inputBookStage);
        });

        storyBookButton.setOnAction(event -> {
            inputBookStage.close();
            addBook(storyBookObj, "Lib UMM - Add Story Book ", "Story Book", inputBookStage);
        });

        textBookButton.setOnAction(event -> {
            inputBookStage.close();
            addBook(textBookObj, "Lib UMM - Add Text Book", "Text Book", inputBookStage);
        });

        backButton.setOnAction(event -> {
            inputBookStage.close();
            Admin adminObj = new Admin();
            adminObj.menu();
        });
    }

    public void addBook(Book genreBook, String addBookStageTitle, String addBookSceneTitle, Stage previousStage) {
        Admin adminObj = new Admin();
        Book bookObj = new Book();

        Stage addbookStage = new Stage();
        addbookStage.setTitle(addBookStageTitle);

        Label sceneTitleLabel = new Label(addBookSceneTitle);
        Label bookIdLabel = new Label("ID Buku    :");
        Label bookTitleLabel = new Label("Judul Buku :");
        Label authorLabel = new Label("Penulis    :");
        Label stockLabel = new Label("Stok       :");
        Label errorMessageLabel = new Label("Stok harus berupa angka");

        TextField bookIdField = new TextField(adminObj.generateId());
        TextField bookTitleField = new TextField();
        TextField authorField = new TextField();
        TextField stockField = new TextField();

        sceneTitleLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        bookIdLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        bookTitleLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        authorLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        stockLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        sceneTitleLabel.setStyle("-fx-text-fill: #A91D3A;");
        errorMessageLabel.setStyle("-fx-text-fill: #FF1E1E;");
        errorMessageLabel.setVisible(false);

        Button submitButton = new Button("Submit");
        Button backButton = new Button("Kembali");

        GridPane gridAddBook = new GridPane();
        gridAddBook.setAlignment(Pos.CENTER);

        gridAddBook.add(sceneTitleLabel, 1, 0);
        gridAddBook.add(bookIdLabel, 0, 1);
        gridAddBook.add(bookTitleLabel, 0, 2);
        gridAddBook.add(authorLabel, 0, 3);
        gridAddBook.add(stockLabel, 0, 4);
        gridAddBook.add(errorMessageLabel, 0, 5);

        gridAddBook.add(bookIdField, 2, 1);
        gridAddBook.add(bookTitleField, 2, 2);
        gridAddBook.add(authorField, 2, 3);
        gridAddBook.add(stockField, 2, 4);

        gridAddBook.add(submitButton, 2, 5);
        gridAddBook.add(backButton, 2, 6);

        Scene addbookScene = new Scene(gridAddBook, 840, 700);
        addbookStage.setScene(addbookScene);
        addbookStage.show();

        submitButton.setOnAction(event -> {
            try {
                errorMessageLabel.setVisible(false);

                bookObj.setBookId(bookIdField.getText());
                bookObj.setTitle(bookTitleField.getText());
                genreBook.setCategory(sceneTitleLabel.getText());
                bookObj.setAuthor(authorField.getText());
                bookObj.setStock(Integer.parseInt(stockField.getText()));

                Book.arr_bookList.add(new Book(bookObj.getBookId(), bookObj.getTitle(), bookObj.getAuthor(), genreBook.getCategory(), bookObj.getStock()));

                adminObj.menu();
                addbookStage.close();
            } catch (NumberFormatException message) {
                errorMessageLabel.setVisible(true);
                addbookStage.show();
            }
        });

        backButton.setOnAction(event -> {
            addbookStage.close();
            adminObj.menu();
        });
    }
}
