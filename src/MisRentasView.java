import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.*;


public class MisRentasView {
    public static void showMisRentasView(VBox contentArea) {
        contentArea.getChildren().clear();

        Label titleLabel = new Label("Mis rentas");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Region spacer = new Region();
        spacer.setPrefHeight(10);

        TableView<Rentas> table = new TableView<>();
        table.setPrefHeight(400);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Rentas, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setPrefWidth(150);
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));

        TableColumn<Rentas, String> columnaTelefono = new TableColumn<>("Telefono");
        columnaTelefono.setPrefWidth(150);
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoCliente"));

        TableColumn<Rentas, Integer> columnaMesas = new TableColumn<>("Mesas");
        columnaMesas.setPrefWidth(100);
        columnaMesas.setCellValueFactory(new PropertyValueFactory<>("numeroMesas"));

        TableColumn<Rentas, Integer> columnaSillas = new TableColumn<>("Sillas");
        columnaSillas.setPrefWidth(100);
        columnaSillas.setCellValueFactory(new PropertyValueFactory<>("numeroSillas"));

        TableColumn<Rentas, String> columnaDireccion = new TableColumn<>("Dirección");
        columnaDireccion.setPrefWidth(200);
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionCliente"));

        TableColumn<Rentas, String> columnaFecha = new TableColumn<>("Fecha");
        columnaFecha.setPrefWidth(100);
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRenta"));

        TableColumn<Rentas, String> columnaHorario = new TableColumn<>("Horario");
        columnaHorario.setPrefWidth(100);
        columnaHorario.setCellValueFactory(new PropertyValueFactory<>("horarioRenta"));

        TableColumn<Rentas, String> columnaCosto = new TableColumn<>("Costo");
        columnaCosto.setPrefWidth(100);
        columnaCosto.setCellValueFactory(new PropertyValueFactory<>("costoRenta"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(columnaNombre, columnaTelefono, columnaMesas, columnaSillas, columnaDireccion, columnaFecha, columnaHorario, columnaCosto);

        ObservableList<Rentas> rentas = cargarRentas();
        table.setItems(rentas);

        Button actualizarButton = new Button("Actualizar Lista");
        actualizarButton.setPrefWidth(210);
        actualizarButton.setStyle("-fx-background-color: #9DB2BF; -fx-padding: 8; -fx-font-size: 16; -fx-cursor: hand;");

        actualizarButton.setOnAction(e -> {
            ObservableList<Rentas> nuevasRentas = cargarRentas();
            table.setItems(nuevasRentas);
        });

        contentArea.getChildren().addAll(
                titleLabel,
                table,
                actualizarButton
        );
    }

    private static ObservableList<Rentas> cargarRentas() {
        ObservableList<Rentas> listaRentas = FXCollections.observableArrayList();

        String sql = "SELECT * FROM rentas ORDER BY renta_id DESC";

        Connection connection = null;
        Statement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            pstmt = connection.createStatement();
            rs = pstmt.executeQuery(sql);

            while (rs.next()) {
                Rentas rentas = new Rentas(
                        rs.getInt("renta_id"),
                        rs.getString("nombreCliente"),
                        rs.getString("telefonoCliente"),
                        rs.getInt("numeroMesas"),
                        rs.getInt("numeroSillas"),
                        rs.getString("direccionCliente"),
                        rs.getString("fechaRenta"),
                        rs.getString("horarioRenta"),
                        rs.getString("costoRenta")
                );
                listaRentas.add(rentas);
            }
            System.out.println("Renta guardada: " + listaRentas.size());
        } catch (SQLException e) {
            System.err.println("Error al cargar rentas");
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar rentas");
            alert.setContentText("No se pudo conectar a la base de datos");
            alert.show();
        }
        return listaRentas;
    }
}
