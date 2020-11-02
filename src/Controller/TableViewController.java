package Controller;

import Models.BtcData;
import Utils.SceneChanger;
import Utils.SqlHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TableViewController implements Initializable
{
    @FXML
    private TableView<BtcData> tableView;

    @FXML
    private TableColumn<BtcData, Integer> idCol;

    @FXML
    private TableColumn<BtcData, Date> dateCol;

    @FXML
    private TableColumn<BtcData, Double> priceCol;

    @FXML
    private TableColumn<BtcData, Long> volumeCol;

    @FXML
    private TableColumn<BtcData, Double> changeCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        idCol.setCellValueFactory(new PropertyValueFactory<BtcData, Integer>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<BtcData, Date>("date"));
        priceCol.setCellValueFactory(new PropertyValueFactory<BtcData, Double>("price"));
        volumeCol.setCellValueFactory(new PropertyValueFactory<BtcData, Long>("volume"));
        changeCol.setCellValueFactory(new PropertyValueFactory<BtcData, Double>("change_percentage"));
        List<BtcData> btcDataList = SqlHelper.getAllRows();
        tableView.getItems().addAll(btcDataList);
    }


    @FXML
    void graphViewOnClick(ActionEvent event) throws IOException
    {
        SceneChanger.changeScene(event,"/View/chartView.fxml","BTC Price History Chart");
    }
}
