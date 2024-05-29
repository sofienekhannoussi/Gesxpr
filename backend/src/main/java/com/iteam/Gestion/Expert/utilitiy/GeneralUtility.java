package com.iteam.Gestion.Expert.utilitiy;

import java.util.Calendar;
import java.util.Date;

public class GeneralUtility {

    public static Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}