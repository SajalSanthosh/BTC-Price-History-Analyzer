import Models.BtcData;
import Utils.CsvUtils;
import Utils.SqlHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class Main extends Application
{
    public static void main(String[] args)
    {
        try
        {
            SqlHelper.createDatabase();
            SqlHelper.createTable();

            if (SqlHelper.countRows() == 0)
            {
                System.out.println("[+] Inserting Data to Database From CSV Provided...\n");
                CsvUtils.parseAndInsertFromCSV("src/Bitcoin_Historical_Data_Daily.csv");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("View/chartView.fxml"));
        primaryStage.setTitle("BTC Price History Chart");
        primaryStage.getIcons().add(new Image("bitcoin.png"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
