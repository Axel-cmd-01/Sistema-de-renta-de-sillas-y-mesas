import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
        contentArea.getStyleClass().add("content-area");

        HomeView.showHomeView(user, contentArea);

        mainLayout.setCenter(contentArea);

        Scene scene = new Scene(mainLayout, 1200, 690);

        String css = this.getClass().getResource("/styles/dashboard.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.setTitle("Renta de sillas y mesas - Dashboard");
        stage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.getStyleClass().add("sidebar-container");

        Label tittleLabel = new Label("Dashboard");
        tittleLabel.getStyleClass().add("title-label");

        Label userLabel = new Label("Usuario: " + user);
        userLabel.getStyleClass().add("userLabel");

        Region spacer = new Region();
        spacer.setPrefHeight(20);

        Button homeBtn = createNavButton("Inicio");
        Button rentarBtn = createNavButton("Rentar");
        Button misRentasBtn = createNavButton("Mis Rentas");
        Button inventarioBtn = createNavButton("Inventario");
        Button perfilBtn = createNavButton("Mi Perfil");
        Button actualizarInventarioBtn = createNavButton("Actualizar inventario");

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

        actualizarInventarioBtn.setOnAction(e -> {
           UpdateInventoryForm.showUpdateInventoryView(contentArea);
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
                actualizarInventarioBtn,
                perfilBtn,
                spacerButton,
                cerrarSesionBtn
        );

        return sidebar;
    }

    private Button createNavButton(String text) {
        Button btn = new Button(text);
        btn.getStyleClass().add("buttons");

        return btn;
    }
}
