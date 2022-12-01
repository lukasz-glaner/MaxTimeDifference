import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solve(new String[]{"21:14"}));
        System.out.println(solve(new String[]{"21:14", "15:34", "14:51", "06:25", "15:30"}));
        System.out.println(solve(new String[]{"23:00", "04:22", "18:05", "06:24"}));
    }

    public static String solve(String[] timeArray) {
        if (timeArray.length == 1) {
            return "23:59";
        } else {
            Set<String> timesSet = new TreeSet<>(Arrays.stream(timeArray).toList());
            List<Long> minutesList = new ArrayList<>();
            for (String s : timesSet) {
                minutesList.add(changeStringToMinutes(s));
            }
            long maxDiff = (24 * 60) - minutesList.get(minutesList.size() - 1) + minutesList.get(0);
            for (int i = minutesList.size() - 1; i > 0; i--) {
                if (minutesList.get(i) - minutesList.get(i - 1) > maxDiff) {
                    maxDiff = minutesList.get(i) - minutesList.get(i - 1);
                }
            }
            Long[] hoursMinutes = changeDiffToLongTable(maxDiff - 1);
            return String.format("%d:%d", hoursMinutes[0], hoursMinutes[1]);
        }
    }

    private static long changeStringToMinutes(String stringToChange) {
        String[] hoursMinutes = stringToChange.split(":");
        return Long.parseLong(hoursMinutes[0]) * 60 + Long.parseLong(hoursMinutes[1]);
    }

    private static Long[] changeDiffToLongTable(Long diff) {
        long hours = diff / 60;
        long minutes = diff % 60;
        return new Long[]{hours, minutes};
    }
}
