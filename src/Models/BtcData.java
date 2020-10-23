package Models;

import java.util.Date;

public class BtcData
{
    private int id;
    private Date date;
    private double price;
    private long volume;
    private double change_percentage;


    public BtcData(int id, Date date, double price, long volume, double change_percentage)
    {
        setId(id);
        setDate(date);
        setPrice(price);
        setVolume(volume);
        setChange_percentage(change_percentage);
    }

    public BtcData(Date date, double price, long volume, double change_percentage)
    {
        setDate(date);
        setPrice(price);
        setVolume(volume);
        setChange_percentage(change_percentage);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        if (id < 0)
        {
            throw new IllegalArgumentException("Invalid Id");
        }
        this.id = id;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        if (price < 0)
        {
            throw new IllegalArgumentException("Invalid Price");
        }
        this.price = price;
    }

    public long getVolume()
    {
        return volume;
    }

    public void setVolume(long volume)
    {
        if (volume < 0)
        {
            throw new IllegalArgumentException("Invalid volume");
        }
        this.volume = volume;
    }

    public double getChange_percentage()
    {
        return change_percentage;
    }

    public void setChange_percentage(double change_percentage)
    {
        this.change_percentage = change_percentage;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
