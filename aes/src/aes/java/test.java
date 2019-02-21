package aes.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.sun.xml.internal.ws.api.Cancelable;

public class test {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        getDate2();
    }

    public static Date getDate() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        Date date = new Date();
        String dateStr = format.format(date);
        Date date2 = format.parse(dateStr);
        date2.setMonth(date2.getMonth() + 1);
        return date2;
    }

    public static Date getDate2() throws ParseException {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        System.out.println(cal.getTime());
        return cal.getTime();
    }
}
