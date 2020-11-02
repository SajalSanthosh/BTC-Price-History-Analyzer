package Controller;

import Models.BtcData;
import Utils.SceneChanger;
import Utils.SqlHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChartViewController implements Initializable
{
    @FXML
    private AreaChart<?, ?> areaChart;

    @FXML
    private CategoryAxis dateAxis;

    @FXML
    private NumberAxis priceAxis;

    @FXML
    private RadioButton monthlyViewRButton;

    @FXML
    private RadioButton yearlyViewRButton;

    @FXML
    private RadioButton allViewRButton;

    private XYChart.Series pricehistory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        monthlyViewRButton.setSelected(true);

        dateAxis.setLabel("Date");
        priceAxis.setLabel("Price ($)");
        pricehistory = new XYChart.Series();
        pricehistory.setName("Bitcoin Price");

        List<BtcData> btcDataList = SqlHelper.getAllRowsMonthly();
        for (BtcData btcData : btcDataList)
        {
            pricehistory.getData().add(new XYChart.Data(btcData.getDate().toString(), btcData.getPrice()));
        }
        areaChart.getData().addAll(pricehistory);
    }

    @FXML
    void AllViewOnClick(ActionEvent event)
    {
        yearlyViewRButton.setSelected(false);
        monthlyViewRButton.setSelected(false);

        pricehistory.getData().clear();
        areaChart.getData().clear();
        areaChart.setAnimated(true);
        dateAxis.setLabel("Date");
        priceAxis.setLabel("Price ($)");
        pricehistory = new XYChart.Series();
        pricehistory.setName("Bitcoin Price");

        List<BtcData> btcDataList = SqlHelper.getAllRows();
        for (BtcData btcData : btcDataList)
        {
            pricehistory.getData().add(new XYChart.Data(btcData.getDate().toString(), btcData.getPrice()));
        }
        areaChart.getData().addAll(pricehistory);
        areaChart.setAnimated(false);
    }

    @FXML
    void MontlyViewOnClick(ActionEvent event)
    {
        yearlyViewRButton.setSelected(false);
        allViewRButton.setSelected(false);

        pricehistory.getData().clear();
        areaChart.getData().clear();
        areaChart.setAnimated(true);
        dateAxis.setLabel("Date");
        priceAxis.setLabel("Price ($)");
        pricehistory = new XYChart.Series();
        pricehistory.setName("Bitcoin Price");
        List<BtcData> btcDataList = SqlHelper.getAllRowsMonthly();
        for (BtcData btcData : btcDataList)
        {
            pricehistory.getData().add(new XYChart.Data(btcData.getDate().toString(), btcData.getPrice()));
        }
        areaChart.getData().addAll(pricehistory);
        areaChart.setAnimated(false);

    }

    @FXML
    void YearlyViewOnClick(ActionEvent event)
    {
        monthlyViewRButton.setSelected(false);
        allViewRButton.setSelected(false);

        pricehistory.getData().clear();
        areaChart.getData().clear();
        areaChart.setAnimated(true);
        dateAxis.setLabel("Date");
        priceAxis.setLabel("Price ($)");
        pricehistory = new XYChart.Series();
        pricehistory.setName("Bitcoin Price");
        List<BtcData> btcDataList = SqlHelper.getAllRowsYearly();
        for (BtcData btcData : btcDataList)
        {
            pricehistory.getData().add(new XYChart.Data(btcData.getDate().toString(), btcData.getPrice()));
        }
        areaChart.getData().addAll(pricehistory);
        areaChart.setAnimated(false);

    }


    @FXML
    void viewTableOnClick(ActionEvent event) throws IOException
    {
        SceneChanger.changeScene(event,"/View/tableView.fxml","BTC Price History Table");
    }
}
