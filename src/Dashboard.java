import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Dashboard {
    private Stage stage;
    private String user;
    private BorderPane mainLayout;
    private VBox contentArea;

    public Dashboard(Stage stage, String user) {
        this.stage = stage;
        this.user = user;
    }

    public void show() {
        mainLayout = new BorderPane();

        VBox sidebar = createSidebar();
        mainLayout.setLeft(sidebar);

        contentArea = new VBox(20);
        contentArea.setAlignment(Pos.CENTER);
        contentArea.setPadding(new Insets(40));
        contentArea.setStyle("-fx-background-color: #f5f5f5");

        HomeView.showHomeView(user, contentArea);

        mainLayout.setCenter(contentArea);

        Scene scene = new Scene(mainLayout, 1200, 690);
        stage.setScene(scene);
        stage.setTitle("Renta de sillas y mesas - Dashboard");
        stage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50;");
        sidebar.setPrefWidth(250);

        Label tittleLabel = new Label("Dashboard");
        tittleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        tittleLabel.setStyle("-fx-text-fill: white;");

        Label userLabel = new Label("Usuario: " + user);
        userLabel.setFont(Font.font("Arial", 14));
        userLabel.setStyle("-fx-text-fill: #ecf0f1;");

        Region spacer = new Region();
        spacer.setPrefHeight(20);

        Button homeBtn = createNavButton("Inicio");
        Button rentarBtn = createNavButton("Rentar");
        Button misRentasBtn = createNavButton("Mis Rentas");
        Button inventarioBtn = createNavButton("Inventario");
        Button perfilBtn = createNavButton("Mi Perfil");

        Region spacerButton = new Region();
        VBox.setVgrow(spacerButton, Priority.ALWAYS);

        Button cerrarSesionBtn = createNavButton("Cerrar Sesión");
        cerrarSesionBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " + "-fx-font-size: 14; -fx-padding: 12; -fx-cursor: hand;");

        homeBtn.setOnAction(e -> {
            HomeView.showHomeView(user, contentArea);
        });

        rentarBtn.setOnAction(e -> {
            RentalFormView.showRentarView(contentArea);
        });

        misRentasBtn.setOnAction(e -> {
            RentalsView.showMisRentasView(contentArea);
        });

        inventarioBtn.setOnAction(e -> {
            InventoryView.showInventarioView(contentArea);
        });

        perfilBtn.setOnAction(e -> {
            ProfileView.showPerfilView(contentArea, user);
        });

        cerrarSesionBtn.setOnAction(e -> {
            Login login = new Login(stage);
            login.show();
        });

        sidebar.getChildren().addAll(
                tittleLabel,
                userLabel,
                spacer,
                homeBtn,
                rentarBtn,
                misRentasBtn,
                inventarioBtn,
                perfilBtn,
                spacerButton,
                cerrarSesionBtn
        );

        return sidebar;
    }

    private Button createNavButton(String text) {
        Button btn = new Button(text);
        btn.setPrefWidth(210);
        btn.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; " +
                "-fx-font-size: 14; -fx-padding: 12; -fx-cursor: hand; " +
                "-fx-alignment: CENTER-LEFT;");
        btn.setOnMouseEntered(e -> {
            btn.setStyle("-fx-background-color: #1abc9c; -fx-text-fill: white; " +
                    "-fx-font-size: 14; -fx-padding: 12; -fx-cursor: hand; " +
                    "-fx-alignment: CENTER-LEFT;");
        });
        btn.setOnMouseExited(e -> {
            btn.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; " +
                    "-fx-font-size: 14; -fx-padding: 12; -fx-cursor: hand; " +
                    "-fx-alignment: CENTER-LEFT;");
        });
        return btn;
    }
}
