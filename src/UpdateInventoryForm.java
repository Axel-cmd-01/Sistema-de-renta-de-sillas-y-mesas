import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UpdateInventoryForm {
    public static void showUpdateInventoryView (VBox contentArea) {
        contentArea.getChildren().clear();
        contentArea.getStylesheets().clear();

        String css = "file:src/styles/updateInventoryForm.css";
        contentArea.getStylesheets().add(css);

        Label titleLabel = new Label("Actualizar inventario");
        titleLabel.getStyleClass().add("titleLabel");

        Region spacer = new Region();
        spacer.setPrefHeight(20);

        /* Sillas  */

        Label labelActualizarSillas = new Label("Actualizar cantidad de sillas");
        labelActualizarSillas.getStyleClass().add("labelActualizar");

        ImageView imageSillaView = null;
        try {
            Image imageSilla = new Image("file:src/images/chair.png");
            imageSillaView = new ImageView(imageSilla);
            imageSillaView.setFitWidth(200);
            imageSillaView.setFitHeight(200);
            imageSillaView.setPreserveRatio(true);
            imageSillaView.setSmooth(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no se pudo cargar la imagen");
        }

        Label labelCantSillas = new Label("Cantidad: " + String.valueOf(getNumberChairs()));
        labelCantSillas.getStyleClass().add("labelCantidad");

        TextField agregarSillasField = new TextField();
        agregarSillasField.setPromptText("00");
        agregarSillasField.getStyleClass().add("fields");

        agregarSillasField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                agregarSillasField.setText(newValue.replaceAll("\\D", ""));
            }
        });

        TextField quitarSillasField = new TextField();
        quitarSillasField.setPromptText("00");
        quitarSillasField.getStyleClass().add("fields");

        quitarSillasField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
                quitarSillasField.setText(newValue.replaceAll("\\D", ""));
            }
        });

        HBox cantidadSillasFieldBox = new HBox(30);
        cantidadSillasFieldBox.setAlignment(Pos.CENTER);
        cantidadSillasFieldBox.getChildren().addAll(agregarSillasField, quitarSillasField);

        // Agregarle el evento al los botones
        Button agregarSillasBtn = new Button("Agregar");
        agregarSillasBtn.getStyleClass().add("buttons");
        agregarSillasBtn.setOnAction(e -> {
            int sumarCantidad = Integer.parseInt(agregarSillasField.getText().trim());
            if (!agregarSillasField.getText().isEmpty()) {
                sumarInventario(sumarCantidad, "SILLA");
                agregarSillasField.clear();
            }
        });

        // Agregarle el evento al los botones
        Button quitarSillasBtn = new Button("Quitar");
        quitarSillasBtn.getStyleClass().add("buttons");
        quitarSillasBtn.setOnAction(e -> {
            int restarCantidad = Integer.parseInt(quitarSillasField.getText().trim());
            if (!quitarSillasField.getText().isEmpty()) {
                restarInventario(restarCantidad, "SILLA");
                quitarSillasField.clear();
            }
        });

        HBox buttonsSillasBox = new HBox(80);
        buttonsSillasBox.setAlignment(Pos.CENTER);
        buttonsSillasBox.getChildren().addAll(agregarSillasBtn, quitarSillasBtn);

        VBox updateSillasBox = new VBox(5);
        updateSillasBox.setAlignment(Pos.CENTER);
        updateSillasBox.getChildren().addAll(labelActualizarSillas, imageSillaView, labelCantSillas, cantidadSillasFieldBox, buttonsSillasBox);

        /* Mesas */

        Label labelActualizarMesas = new Label("Actualizar cantidad de mesas");
        labelActualizarMesas.getStyleClass().add("labelActualizar");

        ImageView imageMesaView = null;
        try {
            Image imageMesa = new Image("file:src/images/table.png");
            imageMesaView = new ImageView(imageMesa);
            imageMesaView.setFitWidth(200);
            imageMesaView.setFitHeight(200);
            imageMesaView.setPreserveRatio(true);
            imageMesaView.setSmooth(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no se pudo cargar la imagen");
        }

        Label labelCantidadMesas = new Label("Cantidad: " + String.valueOf(getNumberTables()));
        labelCantidadMesas.getStyleClass().add("labelCantidad");

        TextField agregarMesasField = new TextField();
        agregarMesasField.setPromptText("00");
        agregarMesasField.getStyleClass().add("fields");

        agregarMesasField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                agregarMesasField.setText(newValue.replaceAll("\\D", ""));
            }
        });

        TextField quitarMesasField = new TextField();
        quitarMesasField.setPromptText("00");
        quitarMesasField.getStyleClass().add("fields");

        quitarMesasField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                quitarMesasField.setText(newValue.replaceAll("\\D", ""));
            }
        });

        HBox cantidadMesasFieldBox = new HBox(30);
        cantidadMesasFieldBox.setAlignment(Pos.CENTER);
        cantidadMesasFieldBox.getChildren().addAll(agregarMesasField, quitarMesasField);

        // Agregarle el evento al los botones
        Button agregarMesasBtn = new Button("Agregar");
        agregarMesasBtn.getStyleClass().add("buttons");

        agregarMesasBtn.setOnAction(e -> {
            int sumarCantidad = Integer.parseInt(agregarMesasField.getText().trim());
            if (!agregarMesasField.getText().isEmpty()) {
                sumarInventario(sumarCantidad, "MESA");
                agregarMesasField.clear();
            }
        });

        // Agregarle el evento al los botones
        Button quitarMesasBtn = new Button("Quitar");
        quitarMesasBtn.getStyleClass().add("buttons");
        quitarMesasBtn.setOnAction(e -> {
            int restarCantidad = Integer.parseInt(quitarMesasField.getText().trim());
            if (!quitarMesasField.getText().isEmpty()) {
                restarInventario(restarCantidad, "MESA");
                quitarMesasField.clear();
            }
        });

        HBox buttonsMesasBox = new HBox(80);
        buttonsMesasBox.setAlignment(Pos.CENTER);
        buttonsMesasBox.getChildren().addAll(agregarMesasBtn, quitarMesasBtn);

        VBox updateMesasBox = new VBox(5);
        updateMesasBox.setAlignment(Pos.CENTER);
        updateMesasBox.getChildren().addAll(labelActualizarMesas, imageMesaView, labelCantidadMesas, cantidadMesasFieldBox, buttonsMesasBox);

        HBox formBox = new HBox(20);
        formBox.setAlignment(Pos.CENTER);
        formBox.getChildren().addAll(updateSillasBox, updateMesasBox);

        contentArea.getChildren().addAll(
                titleLabel,
                spacer,
                formBox
        );
    }

    public static int getNumberChairs() {
        String sql = "SELECT cantidad FROM inventario WHERE tipo = 'SILLA'";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cantidadSillas = 0;

        try {
            connection = DatabaseConnection.getConnection();
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                cantidadSillas = rs.getInt("cantidad");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidadSillas;
    }

    public static int getNumberTables() {
        String sql = "SELECT cantidad FROM inventario WHERE tipo = 'MESA'";

        Connection connection;
        PreparedStatement pstmt;
        ResultSet rs = null;
        int cantidadMesas = 0;

        try {
            connection = DatabaseConnection.getConnection();
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                cantidadMesas = rs.getInt("cantidad");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidadMesas;
    }

    public static void sumarInventario(int sumarCantidad, String tipo) {
        String sql = "UPDATE inventario SET cantidad = cantidad + ? WHERE tipo = ?";

        Connection connection;
        PreparedStatement pstmt;

        try {
            connection = DatabaseConnection.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sumarCantidad);
            pstmt.setString(2, tipo);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void restarInventario(int restarCantidad, String tipo) {

        String sql = "UPDATE inventario SET cantidad = cantidad - ? WHERE tipo = ?";

        Connection connection;
        PreparedStatement pstmt;

        try {
            connection = DatabaseConnection.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, restarCantidad);
            pstmt.setString(2, tipo);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}