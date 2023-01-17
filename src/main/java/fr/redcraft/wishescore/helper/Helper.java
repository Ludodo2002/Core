package fr.redcraft.wishescore.helper;

import java.util.*;

public class Helper {
    private static final int maxYears = 100000;

    public static List<String> getStartingWith(Collection<String> choices, String prefix) {
        List<String> list = new ArrayList<>();
        for (String string : choices) {
            if (string.toLowerCase().startsWith(prefix)) {
                list.add(string);
            }
        }
        return list;
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static String formatDateDiff(final long date) {
        final Calendar c = new GregorianCalendar();
        c.setTimeInMillis(date);
        final Calendar now = new GregorianCalendar();
        return formatDateDiff(now, c);
    }

    public static String formatDateDiff(final Calendar fromDate, final Calendar toDate) {
        boolean future = false;
        if (toDate.equals(fromDate)) {
            return "now";
        }
        if (toDate.after(fromDate)) {
            future = true;
        }
        // Temporary 50ms time buffer added to avoid display truncation due to code execution delays
        toDate.add(Calendar.MILLISECOND, future ? 50 : -50);
        final StringBuilder sb = new StringBuilder();
        final int[] types = new int[] {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND};
        final String[] names = new String[] {"year", "years", "month", "months", "day", "days", "hour", "hours", "minute", "minutes", "second", "seconds"};
        int accuracy = 0;
        for (int i = 0; i < types.length; i++) {
            if (accuracy > 2) {
                break;
            }
            final int diff = dateDiff(types[i], fromDate, toDate, future);
            if (diff > 0) {
                accuracy++;
                sb.append(" ").append(diff).append(" ").append(names[i * 2 + (diff > 1 ? 1 : 0)]);
            }
        }
        // Preserve correctness in the original date object by removing the extra buffer time
        toDate.add(Calendar.MILLISECOND, future ? -50 : 50);
        if (sb.length() == 0) {
            return "now";
        }
        return sb.toString().trim();
    }

    static int dateDiff(final int type, final Calendar fromDate, final Calendar toDate, final boolean future) {
        final int year = Calendar.YEAR;

        final int fromYear = fromDate.get(year);
        final int toYear = toDate.get(year);
        if (Math.abs(fromYear - toYear) > maxYears) {
            toDate.set(year, fromYear +
                    (future ? maxYears : -maxYears));
        }

        int diff = 0;
        long savedDate = fromDate.getTimeInMillis();
        while ((future && !fromDate.after(toDate)) || (!future && !fromDate.before(toDate))) {
            savedDate = fromDate.getTimeInMillis();
            fromDate.add(type, future ? 1 : -1);
            diff++;
        }
        diff--;
        fromDate.setTimeInMillis(savedDate);
        return diff;
    }
    public static boolean isUUID(String str) {
        try {
            UUID.fromString(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
