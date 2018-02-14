package kz.novatron.cryptodesk.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by inetlabs on 2/14/18.
 */

public class DateUtil {
    public static String getFormattedDate(Date updated, Locale locale) {
        return new SimpleDateFormat("dd.MM.yyyy \nHH:mm:ss z", locale).format(updated);
    }
}
