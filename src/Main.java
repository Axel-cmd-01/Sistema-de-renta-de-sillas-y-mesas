import javafx.application.Application;
import javafx.stage.Stage;

public class Main  extends Application{
    public void start(Stage primayStage) {
        Login login = new Login(primayStage);
        login.show();
    }

    public void stop() {
        DatabaseConnection.closeConnection();
        System.out.println("Aplicacion cerrada");
    }

    public static void main(String[] args) {
        launch(args);
    }
}