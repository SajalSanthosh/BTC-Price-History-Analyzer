import Models.BtcData;
import Utils.CsvUtils;
import Utils.SqlHelper;

import java.sql.SQLException;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        SqlHelper sqlHelper = new SqlHelper();
        try
        {
            sqlHelper.createDatabase();
            sqlHelper.createTable();

            System.out.println("[+] Inserting Data to Database From CSV Provided...\n");
            CsvUtils.parseAndInsertFromCSV("src/Bitcoin_Historical_Data_Daily.csv", sqlHelper);

            System.out.println("[+] Exporting Data from CSV as sql insert statements\n");
            CsvUtils.parseAndExportAsSQL("src/Bitcoin_Historical_Data_Daily.csv", "src/Bitcoin_Historical_Data_Daily.sql");
            List<BtcData> btcDataList = sqlHelper.getAllRows();

            System.out.println("[+] Printing all data on database:\n");
            printBtcDataList(btcDataList);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method will print the BtcDataList as a nice table
     * @param btcDataList List of BtcData from database
     */
    private static void printBtcDataList(List<BtcData> btcDataList)
    {
        System.out.print("Id");
        System.out.print("\t\t\t");
        System.out.print("Date");
        System.out.print("\t\t");
        System.out.print("Price");
        System.out.print("\t\t");
        System.out.print("Volume");
        System.out.print("\t\t");
        System.out.print("Change Percentage");
        System.out.println();

        for (BtcData btcData : btcDataList)
        {
            System.out.print(btcData.getId());
            System.out.print("\t\t");
            System.out.print(btcData.getDate());
            System.out.print("\t\t");
            System.out.print(btcData.getPrice());
            System.out.print("\t\t");
            System.out.print(btcData.getVolume());
            System.out.print("\t\t");
            System.out.print(btcData.getChange_percentage());
            System.out.println();
        }
    }
}
