package Utils;

import Models.BtcData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlHelper
{
    //the user name and password used for sql
    private static String user = "root";
    private static String password = "";

    /**
     * This method will create table
     */
    public void createTable()
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/btc_data", user, password); Statement statement = conn.createStatement())
        {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `btc_historical_daily` (`id` SMALLINT UNSIGNED PRIMARY KEY AUTO_INCREMENT, `date` DATE NOT NULL , `price` DECIMAL(10,2) NOT NULL , `volume` INT NOT NULL , `change_percentage` DECIMAL(5,2) NOT NULL )");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * create the database
     *
     * @throws SQLException
     */
    public void createDatabase() throws SQLException
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, password); Statement statement = conn.createStatement())
        {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS btc_data");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method will insert the BtcData to database
     *
     * @param btcData
     * @return the id of inserted data in database
     */
    public int insertData(BtcData btcData)
    {
        int BtcId = -1;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/btc_data", user, password))
        {
            String sql = "INSERT INTO `btc_historical_daily` (`date`, `price`, `volume`, `change_percentage`) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"}))
            {
                ps.setDate(1, new Date(btcData.getDate().getTime()));
                ps.setDouble(2, btcData.getPrice());
                ps.setLong(3, btcData.getVolume());
                ps.setDouble(4, btcData.getChange_percentage());

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys())
                {
                    while (rs.next())
                    {
                        BtcId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return BtcId;
    }


    /**
     * This method will return all rows from database
     *
     * @return A list of BtcDaa containing all rows
     */
    public List<BtcData> getAllRows()
    {
        List<BtcData> btcDataList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/btc_data", user, password))
        {
            String sql = "SELECT * FROM btc_historical_daily";

            try (PreparedStatement ps = conn.prepareStatement(sql))
            {
                try (ResultSet rs = ps.executeQuery())
                {
                    while (rs.next())
                    {
                        int id = rs.getInt(1);
                        Date date = rs.getDate(2);
                        double price = rs.getDouble(3);
                        long volume = rs.getLong(4);
                        double price_change = rs.getDouble(5);

                        BtcData btcData = new BtcData(id, date, price, volume, price_change);
                        btcDataList.add(btcData);
                    }
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return btcDataList;
    }
}
