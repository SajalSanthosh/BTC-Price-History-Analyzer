package Utils;

public class NumberUtils
{

    /**
     * Converts formatted numbers with K, M notation to normal number
     * @param formattedNumber Formatted number with K, M notation
     * @return parsed number
     */
    public static Long formatParser(String formattedNumber)
    {
        if (formattedNumber.toLowerCase().endsWith("m"))
        {
            return Long.parseLong(formattedNumber.replaceAll("[\\D]", "")) * 1000000;
        }

        if (formattedNumber.toLowerCase().endsWith("k"))
        {
            return Long.parseLong(formattedNumber.replaceAll("[\\D]", "")) * 1000;
        }

        return 0L;
    }


    /**
     * Parse number with a percentage sign %
     * @param percentage
     * @return parsed Double
     */
    public static Double parsePercentage(String percentage)
    {
        return Double.parseDouble(percentage.replaceAll("[^-(\\d+(\\.\\d+)?)|(\\.\\d+)]", ""));
    }
}
