import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RentalFormView {
    public static void showRentarView(VBox contentArea) {
        contentArea.getChildren().clear();

        Label tittleLabel = new Label("Rentar");
        tittleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Region spacer = new Region();
        spacer.setPrefHeight(20);

        // Datos del cliente
        Label datosClienteLabel = new Label("Datos del Cliente:");
        datosClienteLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Label nombreClienteLabel = new Label("Nombre:");
        nombreClienteLabel.setFont(Font.font("Arial", 16));
        TextField nombreClienteField = new TextField();
        nombreClienteField.setPrefWidth(260);
        nombreClienteField.setStyle("-fx-padding: 5; -fx-pref-height: 30;");
        nombreClienteField.setPromptText("Ingrese el nombre del cliente");

        HBox nombreBox = new HBox(10);
        nombreBox.setAlignment(Pos.CENTER);
        nombreBox.getChildren().addAll(nombreClienteLabel, nombreClienteField);

        Label telefonoClienteLabel = new Label("Telefono:");
        telefonoClienteLabel.setFont(Font.font("Arial", 16));
        TextField telefonoClienteField = new TextField();
        telefonoClienteField.setPrefWidth(260);
        telefonoClienteField.setStyle("-fx-padding: 5; -fx-pref-height: 30;");
        telefonoClienteField.setPromptText("Ingrese el numero de telefono");

        telefonoClienteField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                telefonoClienteField.setText(newValue.replaceAll("[^\\\\d]", ""));
            }

            if (newValue.length() > 10) {
                telefonoClienteField.setText(oldValue);
            }
        });

        HBox telefonoClienteBox = new HBox(10);
        telefonoClienteBox.setAlignment(Pos.CENTER);
        telefonoClienteBox.getChildren().addAll(telefonoClienteLabel, telefonoClienteField);

        // Datos del evento
        Label datosEventoLabel = new Label("Datos del Evento:");
        datosEventoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Label mesasLabel = new Label("Mesas:");
        mesasLabel.setFont(Font.font("Arial", 16));
        Spinner<Integer> mesasSpinner = new Spinner<>();
        mesasSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        mesasSpinner.setStyle("-fx-pref-height: 30; -fx-pref-width: 70;");

        HBox mesasBox = new HBox(10);
        mesasBox.setAlignment(Pos.CENTER);
        mesasBox.getChildren().addAll(mesasLabel, mesasSpinner);

        Label sillasLabel = new Label("Sillas:");
        sillasLabel.setFont(Font.font("Arial", 16));
        Spinner<Integer> sillasSpinner = new Spinner<>();
        sillasSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        sillasSpinner.setStyle("-fx-pref-height: 30; -fx-pref-width: 70;");

        HBox sillasBox = new HBox(10);
        sillasBox.setAlignment(Pos.CENTER);
        sillasBox.getChildren().addAll(sillasLabel, sillasSpinner);

        Label direccionClienteLabel = new Label("Dirección:");
        direccionClienteLabel.setFont(Font.font("Arial", 16));
        TextField direccionClienteField = new TextField();
        direccionClienteField.setPrefWidth(260);
        direccionClienteField.setStyle("-fx-padding: 5; -fx-pref-height: 30;");
        direccionClienteField.setPromptText("Ingrese la direccion del evento");

        HBox direccionClienteBox = new HBox(10);
        direccionClienteBox.setAlignment(Pos.CENTER);
        direccionClienteBox.getChildren().addAll(direccionClienteLabel, direccionClienteField);

        Label fechaLabel = new Label("Fecha: ");
        fechaLabel.setFont(Font.font("Arial", 16));
        DatePicker fechaPicker = new DatePicker();
        fechaPicker.setStyle("-fx-padding: 0; -fx-pref-height: 30;");

        HBox fechaBox = new HBox(10);
        fechaBox.setAlignment(Pos.CENTER);
        fechaBox.getChildren().addAll(fechaLabel, fechaPicker);

        Label horaEntregaLabel = new Label("Hora de entrega: ");
        horaEntregaLabel.setFont(Font.font("Arial", 16));
        Spinner<Integer> horaSpinner = new Spinner<>();
        horaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        horaSpinner.setStyle("-fx-pref-height: 30; -fx-pref-width: 70;");
        Label separadorHoraLabel = new Label(":");
        separadorHoraLabel.setFont(Font.font("Arial", 16));
        Spinner<Integer> minutoSpinner = new Spinner<>();
        minutoSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        minutoSpinner.setStyle("-fx-pref-height: 30; -fx-pref-width: 70;");

        HBox horaEntregaBox = new HBox(10);
        horaEntregaBox.setAlignment(Pos.CENTER);
        horaEntregaBox.getChildren().addAll(horaEntregaLabel, horaSpinner, separadorHoraLabel, minutoSpinner);

        Label costoTrasladoLabel = new Label("Costo del Traslado: $");
        costoTrasladoLabel.setFont(Font.font("Arial", 16));
        TextField costoTrasladoField = new TextField();
        costoTrasladoField.setPromptText("0.00");
        costoTrasladoField.setPrefWidth(100);
        costoTrasladoField.setStyle("-fx-padding: 5; -fx-pref-height: 30;");

        costoTrasladoField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                costoTrasladoField.setText(oldValue);
            }
        });

        HBox costoTrasladoBox = new HBox(10);
        costoTrasladoBox.setAlignment(Pos.CENTER);
        costoTrasladoBox.getChildren().addAll(costoTrasladoLabel, costoTrasladoField);

        Label messageLabel = new Label();

        Button rentarBtn = new Button("Rentar");
        rentarBtn.setPrefWidth(210);
        rentarBtn.setStyle("-fx-background-color: #9DB2BF; -fx-padding: 8; -fx-font-size: 16; -fx-cursor: hand;");

        rentarBtn.setOnAction(e -> {
            String nombreCliente = nombreClienteField.getText();
            String telefonoCliente = telefonoClienteField.getText();
            int mesas = mesasSpinner.getValue();
            int sillas = sillasSpinner.getValue();
            String direccionCliente = direccionClienteField.getText();
            LocalDate fecha = fechaPicker.getValue();
            String fechaRenta = fecha.toString();
            int hora = horaSpinner.getValue();
            String horario = horaSpinner.getValue() + ":" + minutoSpinner.getValue();
            String costo = costoTrasladoField.getText();

            if (nombreCliente.isEmpty() || telefonoCliente.isEmpty() || mesas == 0 || sillas == 0 || direccionCliente.isEmpty() || fechaRenta.isEmpty() || hora == 0 || costo.isEmpty()) {
                messageLabel.setText("Por favor complete todos los campos");
                messageLabel.setStyle("-fx-text-fill: red;");
            } else {
                if (registrarRenta(nombreCliente, telefonoCliente, mesas, sillas, direccionCliente, fechaRenta, horario, costo)) {
                    System.out.println("Renta registrada correctamente");

                    messageLabel.setText("Renta registrada correctamente");
                    messageLabel.setStyle("-fx-text-fill: #67e600");

                    nombreClienteField.clear();
                    telefonoClienteField.clear();
                    mesasSpinner.getValueFactory().setValue(1);
                    sillasSpinner.getValueFactory().setValue(1);
                    direccionClienteField.clear();
                    fechaPicker.setValue(null);
                    horaSpinner.getValueFactory().setValue(0);
                    minutoSpinner.getValueFactory().setValue(0);
                    costoTrasladoField.clear();
                } else {
                    messageLabel.setText("No se pudo registrar la renta");
                    mesasSpinner.setStyle("-fx-text-fill: red;");
                }
            }
        });

        contentArea.getChildren().addAll(
                tittleLabel,
                spacer,
                datosClienteLabel,
                nombreBox,
                telefonoClienteBox,
                datosEventoLabel,
                mesasBox,
                sillasBox,
                direccionClienteBox,
                fechaBox,
                horaEntregaBox,
                costoTrasladoBox,
                messageLabel,
                rentarBtn
        );
    }

    private static boolean registrarRenta(String nombreCliente, String telefonoCliente, int mesas, int sillas, String direccionCliente, String fechaRenta, String horario, String costo) {
        String sql = "INSERT INTO rentas (nombreCliente, telefonoCliente, numeroMesas, numeroSillas, direccionCliente, fechaRenta, horarioRenta, costoRenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DatabaseConnection.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, nombreCliente);
            pstmt.setString(2, telefonoCliente);
            pstmt.setInt(3, mesas);
            pstmt.setInt(4, sillas);
            pstmt.setString(5, direccionCliente);
            pstmt.setString(6, fechaRenta);
            pstmt.setString(7, horario);
            pstmt.setString(8, costo);

            int rowInsert = pstmt.executeUpdate();

            if (rowInsert > 0) {
                System.out.println("Rentar registrada correctamente");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar la renta");
            e.printStackTrace();
            return false;
        } finally {
            if (pstmt == null) {
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