package com.example.a709151034.controledelanches.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by MM on 19/11/2015.
 */
public abstract class DecimalUtils {

    /**
     * Formata Valor para Formato Brasileiro
     * @param valor
     * @return
     */
    public static String format(double valor){
        return new DecimalFormat("R$ #,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR"))).format(valor);
    }

    /**
     * Formata Valor
     * @param parent
     * @param locale
     * @param valor
     * @return
     */
    public static String format(String parent, Locale locale, double valor){
        return new DecimalFormat(parent, new DecimalFormatSymbols(locale)).format(valor);
    }
}
