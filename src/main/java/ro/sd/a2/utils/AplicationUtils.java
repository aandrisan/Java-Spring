package ro.sd.a2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AplicationUtils {
    public static String generatePrettyDateFromSQLDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }

    public static Date generateDateFromString(String stringDate) throws ParseException {
        Date date1=new SimpleDateFormat("dd MM yyyy").parse(stringDate);
        return date1;
    }
}
