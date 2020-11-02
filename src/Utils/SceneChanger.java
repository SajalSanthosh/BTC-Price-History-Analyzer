package Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger
{
    /**
     * This method will change to the view provided
     */
    public static void changeScene(ActionEvent event, String viewName, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(new Object()
        {
        }.getClass().getResource(viewName));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
