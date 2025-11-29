import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private Stage stage;

    public Login(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Label titleLabel = new Label("Iniciar sesión");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Label userLabel = new Label("Usuario:");
        TextField userField = new TextField();
        userField.setMaxWidth(250);
        userField.setPromptText("Ingrese su nombre de usuario");

        Label passwordLabel = new Label("Contraseña:");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(250);
        passwordField.setPromptText("Ingrese su contraseña");

        Label errorLabel = new Label();

        Button loginButton = new Button("Iniciar Sesión");
        loginButton.setPrefWidth(200);
        loginButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        loginButton.setDefaultButton(true);

        loginButton.setOnAction(e -> {
            String user = userField.getText();
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

        Button registerButton = new Button("¿No tienes cuenta? - Registrate");
        registerButton.setStyle("-fx-background-color: transparent; -fx-text-fill: blue; -fx-underline: true;");

        registerButton.setOnAction(e -> {
            Register register = new Register(stage);
            register.show();
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.getChildren().addAll(
                titleLabel,
                userLabel,
                userField,
                passwordLabel,
                passwordField,
                errorLabel,
                loginButton,
                registerButton
        );

        Scene scene = new Scene(layout, 840, 700);
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
