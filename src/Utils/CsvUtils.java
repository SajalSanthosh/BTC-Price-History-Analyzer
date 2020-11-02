package Utils;

import Models.BtcData;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class CsvUtils
{

    /**
     * This method will parse the given csv file and insert the data into database
     * @param csvFile file name pointing to the csvFile from the data is to be taken
     */
    public static void parseAndInsertFromCSV(String csvFile)
    {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {

            br.readLine(); // Skip the csv header section
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");

            while ((line = br.readLine()) != null)
            {
                String[] csvColumn = line.split(",");
                Date date = simpleDateFormat.parse(csvColumn[0]);
                double price = Double.parseDouble(csvColumn[1]);
                long volume = NumberUtils.formatParser(csvColumn[5]);
                double percentage = NumberUtils.parsePercentage(csvColumn[6]);

                BtcData btcData = new BtcData(date, price, volume, percentage);
                SqlHelper.insertData(btcData);
/*
                System.out.print(csvColumn[0]);
                System.out.print("\t\t");
                System.out.print(csvColumn[1]);
                System.out.print("\t\t");
                System.out.print(csvColumn[2]);
                System.out.print("\t\t");
                System.out.print(csvColumn[3]);
                System.out.print("\t\t");
                System.out.print(csvColumn[4]);
                System.out.print("\t\t");
                System.out.print(csvColumn[5]);
                System.out.print("\t\t");
                System.out.print(csvColumn[6]);
                System.out.println();*/
            }

        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param csvFile file name pointing to the csv File from the data is to be taken
     * @param sqlFile file name pointing to the sql file to save the insert statements to
     */
    public static void parseAndExportAsSQL(String csvFile, String sqlFile)
    {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)); Formatter formatter = new Formatter(sqlFile))
        {
            br.readLine(); // Skip the csv header section
            SimpleDateFormat simpleDateFormatWrite = new SimpleDateFormat("yyyy-mm-dd");
            SimpleDateFormat simpleDateFormatRead = new SimpleDateFormat("dd-mm-yyyy");

            while ((line = br.readLine()) != null)
            {
                String[] csvColumn = line.split(",");
                Date date = simpleDateFormatRead.parse(csvColumn[0]);
                double price = Double.parseDouble(csvColumn[1]);
                long volume = NumberUtils.formatParser(csvColumn[5]);
                double percentage = NumberUtils.parsePercentage(csvColumn[6]);

                formatter.format("INSERT INTO `btc_historical_daily` (`date`, `price`, `volume`, `change_percentage`) VALUES ('%s', %.02f, %d, %.02f);\n", simpleDateFormatWrite.format(date), price, volume, percentage);
            }
        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }

    }
}
