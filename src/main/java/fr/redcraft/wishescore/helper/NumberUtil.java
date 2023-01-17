package fr.redcraft.wishescore.helper;

import java.text.DecimalFormat;

public class NumberUtil {
    private static final DecimalFormat twoDPlaces = new DecimalFormat("#,###.##");


    public static String formatDouble(final double value) {
        return twoDPlaces.format(value);
    }
}
