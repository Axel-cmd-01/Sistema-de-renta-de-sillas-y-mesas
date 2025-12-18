import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Stack;

public class Register {
    private Stage stage;
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    public Register(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        VBox main = new VBox(5);
        main.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(50));
        main.setMaxWidth(900);
        main.getStyleClass().add("main-container");

        Label titleLabel = new Label("Registrarse");
        titleLabel.getStyleClass().add("title-label");

        Label userLabel = new Label("Nombre:");
        userLabel.getStyleClass().add("field-label");
        TextField userField = new TextField();
        userField.getStyleClass().add("custom-text-field");
        userField.setPromptText("Ingrese un nombre de usuario:");

        Label emailLabel = new Label("Correo electrónico:");
        emailLabel.getStyleClass().add("field-label");
        TextField emailField = new TextField();
        emailField.getStyleClass().add("custom-text-field");
        emailField.setPromptText("Ingrese un correo electronico");

        Label passwordLabel = new Label("Contraseña:");
        passwordLabel.getStyleClass().add("field-label");
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("custom-text-field");
        passwordField.setPromptText("Ingrese una contraseña");

        TextField passwordTextField = new TextField();
        passwordTextField.getStyleClass().add("custom-text-field");
        passwordTextField.setPromptText("Ingrese una contraseña");
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

        Button toggleButtonOne = new Button("👁");
        toggleButtonOne.getStyleClass().add("toggle-button");

        toggleButtonOne.setOnAction(e -> {
            isPasswordVisible = !isPasswordVisible;

            if (isPasswordVisible) {
                passwordTextField.setText(passwordField.getText());
                passwordField.setVisible(false);
                passwordField.setManaged(false);
                passwordTextField.setVisible(true);
                passwordTextField.setManaged(true);
                toggleButtonOne.setText("👁");
            } else {
                passwordField.setText(passwordTextField.getText());
                passwordTextField.setVisible(false);
                passwordTextField.setManaged(false);
                passwordField.setVisible(true);
                passwordField.setManaged(true);
                toggleButtonOne.setText("👁");
            }
        });

        StackPane passwordContainer = new StackPane();
        passwordContainer.getChildren().addAll(passwordField, passwordTextField, toggleButtonOne);
        StackPane.setAlignment(passwordField, Pos.CENTER_LEFT);
        StackPane.setAlignment(passwordTextField, Pos.CENTER_LEFT);
        StackPane.setAlignment(toggleButtonOne, Pos.CENTER_RIGHT);
        toggleButtonOne.setTranslateX(-10);
        passwordContainer.setMaxWidth(300);
        passwordContainer.setPrefWidth(300);

        Label confirmPasswordLabel = new Label("Confirmar contraseña");
        confirmPasswordLabel.getStyleClass().add("field-label");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.getStyleClass().add("custom-text-field");
        confirmPasswordField.setPromptText("Confirme su contraseña");

        TextField confirmPasswordTextField = new TextField();
        confirmPasswordTextField.getStyleClass().add("custom-text-field");
        confirmPasswordTextField.setPromptText("Confirme su contraseña");
        confirmPasswordTextField.setVisible(false);
        confirmPasswordTextField.setManaged(false);

        confirmPasswordField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!isConfirmPasswordVisible) {
                confirmPasswordTextField.setText(newVal);
            }
        });

        confirmPasswordTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (isConfirmPasswordVisible) {
                confirmPasswordField.setText(newVal);
            }
        });

        Button toggleButtonTwo = new Button("👁");
        toggleButtonTwo.getStyleClass().add("toggle-button");

        toggleButtonTwo.setOnAction(e -> {
            isConfirmPasswordVisible = !isConfirmPasswordVisible;

            if (isConfirmPasswordVisible) {
                confirmPasswordTextField.setText(confirmPasswordField.getText());
                confirmPasswordField.setVisible(false);
                confirmPasswordField.setManaged(false);
                confirmPasswordTextField.setVisible(true);
                confirmPasswordTextField.setManaged(true);
                toggleButtonTwo.setText("👁");
            } else {
                confirmPasswordField.setText(confirmPasswordTextField.getText());
                confirmPasswordTextField.setVisible(false);
                confirmPasswordTextField.setManaged(false);
                confirmPasswordField.setVisible(true);
                confirmPasswordField.setManaged(true);
                toggleButtonTwo.setText("👁");
            }
        });

        StackPane confirmPasswordContainer = new StackPane();
        confirmPasswordContainer.getChildren().addAll(confirmPasswordField, confirmPasswordTextField, toggleButtonTwo);
        StackPane.setAlignment(confirmPasswordField, Pos.CENTER_LEFT);
        StackPane.setAlignment(confirmPasswordTextField, Pos.CENTER_LEFT);
        StackPane.setAlignment(toggleButtonTwo, Pos.CENTER_RIGHT);
        toggleButtonTwo.setTranslateX(-10);
        confirmPasswordContainer.setMaxWidth(300);
        confirmPasswordContainer.setPrefWidth(300);

        Label errorLabel = new Label();
        errorLabel.getStyleClass().add("error-label");

        Button registerButton = new Button("Registrarse");
        registerButton.getStyleClass().add("register-button");
        registerButton.setDefaultButton(true);

        registerButton.setOnAction(e -> {
            String user = userField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordTextField.getText();

            if (user.isEmpty() ||  email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                errorLabel.setText("Por favor complete todos los campos");
                errorLabel.setStyle("-fx-text-fill: red");
                return;
            } else if (!email.contains("@")) {
                errorLabel.setText("El correo debe contener: @");
                errorLabel.setStyle("-fx-text-fill: red");
            } else if (password.length() < 6) {
                errorLabel.setText("La contraseña debe tener al menos 6 caracteres");
                errorLabel.setStyle("-fx-text-fill: red");
            } else if (!password.equals(confirmPassword)) {
                errorLabel.setText("Las contraseñas no coinciden");
                errorLabel.setStyle("-fx-text-fill: red");
            } else {
                if (registerUser(user, email, password)) {
                    errorLabel.setText("Registro exitoso");
                    errorLabel.setStyle("-fx-text-fill: #67e600;");

                    userField.clear();
                    emailField.clear();
                    passwordField.clear();
                    passwordTextField.clear();
                    confirmPasswordTextField.clear();

                } else {
                    errorLabel.setText("Error al registrarse");
                    errorLabel.setStyle("-fx-text-fill: red;");
                }
            }
        });

        Button iniciarSesionButton = new Button("¿Ya tienes cuenta? - Iniciar Sesión");
        iniciarSesionButton.getStyleClass().add("login-button");

        iniciarSesionButton.setOnAction(e -> {
            Login login = new Login(stage);
            login.show();
        });

        main.getChildren().addAll(
                titleLabel,
                userLabel,
                userField,
                emailLabel,
                emailField,
                passwordLabel,
                passwordContainer,
                confirmPasswordLabel,
                confirmPasswordContainer,
                errorLabel,
                registerButton,
                iniciarSesionButton
        );

        root.getChildren().add(main);

        Scene scene = new Scene(root, 1200, 690);

        String css = this.getClass().getResource("/styles/register.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.setTitle("Renta de sillas y mesas - Registrarse");
        stage.show();
    }

    private boolean registerUser(String user, String email, String password) {
        String sql = "INSERT INTO users (user, email, password) VALUES (?, ?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DatabaseConnection.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, user);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            int rowsInsert = pstmt.executeUpdate();

            if (rowsInsert > 0) {
                System.out.println("Usuario registrado correctamente");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al registrarse");
            e.printStackTrace();
            return false;
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
