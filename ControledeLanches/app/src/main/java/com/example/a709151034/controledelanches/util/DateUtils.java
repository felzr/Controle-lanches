package com.example.a709151034.controledelanches.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by MM on 15/11/2015.
 */
public abstract class DateUtils {

    /**
     * Data Atual
     *
     * @return
     */
    public static Date dataAtual() {
        return GregorianCalendar.getInstance().getTime();
    }

    /**
     * Formata Data
     *
     * @param data
     * @param parent
     * @return
     */
    public static String format(Date data, String parent) {
        return new SimpleDateFormat(parent).format(data);
    }

    /**
     * Formata Data
     *
     * @param data
     * @param parent
     * @return
     */
    public static String format(Date data, String parent, Locale locale) {
        return new SimpleDateFormat(parent, locale).format(data);
    }

    /**
     * Transforma String em Data
     *
     * @param data
     * @param parent
     * @return
     */
    public static Date parse(String data, String parent) {
        try {
            return new SimpleDateFormat(parent).parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retorna Numero Corresponde ao Mes
     *
     * @param mes
     * @return
     */
    public static int numeroMes(String mes) {
        switch (mes) {
            case "JAN":
                return 0;
            case "FEV":
                return 1;
            case "MAR":
                return 2;
            case "ABR":
                return 3;
            case "MAI":
                return 4;
            case "JUN":
                return 5;
            case "JUL":
                return 6;
            case "AGO":
                return 7;
            case "SET":
                return 8;
            case "OUT":
                return 9;
            case "NOV":
                return 10;
            case "DEZ":
                return 11;
        }
        return 0;
    }
}
