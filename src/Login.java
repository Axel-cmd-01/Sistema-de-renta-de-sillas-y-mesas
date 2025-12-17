import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private Stage stage;
    private boolean isPasswordVisible = false;

    public Login(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        /* Este es el contenido que se establece en el scene */
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        /* Este es el contenidor principal*/
        HBox main = new HBox(40);
        main.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(50));
        main.setMaxWidth(900);
        main.getStyleClass().add("login-container");

        /* Este es lado izquierdo, es decir el formulario */
        VBox leftSide = new VBox(15);
        leftSide.setAlignment(Pos.CENTER);
        leftSide.setPadding(new Insets(30));
        leftSide.setPrefWidth(400);

        /* Elementos dentro del leftSide */
        Label titleLabel = new Label("Iniciar sesión");
        titleLabel.getStyleClass().add("title-label");

        Label userLabel = new Label("Usuario:");
        userLabel.getStyleClass().add("field-label");
        TextField userField = new TextField();
        userField.setPromptText("Ingrese su nombre de usuario");
        userField.getStyleClass().add("custom-text-field");

        Label passwordLabel = new Label("Contraseña:");
        passwordLabel.getStyleClass().add("field-label");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Ingrese su contraseña");
        passwordField.getStyleClass().add("custom-text-field");

        TextField passwordTextField = new TextField();
        passwordTextField.setPromptText("Ingrese su contraseña");
        passwordTextField.getStyleClass().add("custom-text-field");
        passwordTextField.setVisible(false);
        passwordTextField.setManaged(false);

        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!isPasswordVisible) {
                passwordTextField.setText(newVal);
            }
        });

        passwordTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (isPasswordVisible) {
                passwordField.setText(newVal);
            }
        });

        Button toggleButton = new Button("👁");
        toggleButton.getStyleClass().add("toggle-button");

        toggleButton.setOnAction(e -> {
            isPasswordVisible = !isPasswordVisible;

            if (isPasswordVisible) {
                passwordTextField.setText(passwordField.getText());
                passwordField.setVisible(false);
                passwordField.setManaged(false);
                passwordTextField.setVisible(true);
                passwordTextField.setManaged(true);
                toggleButton.setText("👁");
            } else {
                passwordField.setText(passwordTextField.getText());
                passwordTextField.setVisible(false);
                passwordTextField.setManaged(false);
                passwordField.setVisible(true);
                passwordField.setManaged(true);
                toggleButton.setText("👁");
            }
        });

        StackPane passwordContainer = new StackPane();
        passwordContainer.getChildren().addAll(passwordField, passwordTextField, toggleButton);
        StackPane.setAlignment(passwordField, Pos.CENTER_LEFT);
        StackPane.setAlignment(passwordTextField, Pos.CENTER_LEFT);
        StackPane.setAlignment(toggleButton, Pos.CENTER_RIGHT);
        toggleButton.setTranslateX(-10);
        toggleButton.setText("👁");

        Label errorLabel = new Label();
        errorLabel.getStyleClass().add("error-label");

        Button loginButton = new Button("Iniciar Sesión");
        loginButton.getStyleClass().add("login-button");
        loginButton.setDefaultButton(true);

        loginButton.setOnAction(e -> {
            String user = userField.getText().trim();
            String password = passwordField.getText();

            if (user.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Por favor complete todos los campos");
                errorLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            if (verifyUser(user, password)) {
                userField.clear();
                passwordField.clear();

                Dashboard dashboard = new Dashboard(stage, user);
                dashboard.show();
            } else {
                System.out.println("Contraseña o usuario incorrecto");
                errorLabel.setText("Contraseña o usuario incorrecto");
            }

        });

        Button registerButton = new Button("¿No tienes cuenta? - Regístrate");
        registerButton.getStyleClass().add("register-button");

        registerButton.setOnAction(e -> {
            Register register = new Register(stage);
            register.show();
        });


        leftSide.getChildren().addAll(
                titleLabel,
                userLabel,
                userField,
                passwordLabel,
                passwordContainer,
                errorLabel,
                loginButton,
                registerButton
        );

        /* Este es el lado derecho, es decir la imagen */
        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setPrefWidth(400);

        try {
            Image image = new Image(getClass().getResourceAsStream("/images/chair.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(350);
            imageView.setFitWidth(350);
            imageView.setPreserveRatio(true);
            rightSide.getChildren().add(imageView);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la silla");
        };

        main.getChildren().addAll(
                leftSide,
                rightSide
        );

        root.getChildren().add(main);

        Scene scene = new Scene(root, 1200, 690);

        String css = this.getClass().getResource("/styles/login.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.setTitle("Renta de sillas y mesas - Iniciar Sesión");
        stage.show();
    }

    public boolean verifyUser(String user, String password) {
        String sql = "SELECT password FROM users WHERE user = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();

            if (connection == null) {
                System.out.println("No se pudo establecer la conexion");
                return false;
            }

            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String passwordSaved = rs.getString("password");

                if (passwordSaved.equals(password)) {
                    System.out.println("Contraseña encontrada");
                    return true;
                } else {
                    System.out.println("Contraseña incorrecta");
                    return false;
                }
            } else {
                System.out.println("Usuario no encontrado");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el login");
            e.printStackTrace();
            return false;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
