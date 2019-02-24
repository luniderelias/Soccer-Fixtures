package teste.com.fixtures.Util;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static String getModifiedDate(long modified) {
        return getModifiedDate(Locale.getDefault(), modified);
    }

    public static String getModifiedDate(Locale locale, long modified) {
        SimpleDateFormat dateFormat = null;

        dateFormat = new SimpleDateFormat(getDateFormat(locale));

        return dateFormat.format(new Date(modified));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static String getDateFormat(Locale locale) {
        return DateFormat.getBestDateTimePattern(locale, "MM/dd/yyyy hh:mm:ss aa");
    }
}
