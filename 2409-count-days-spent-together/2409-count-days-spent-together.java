class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] days = {31, 28, 31, 30, 31, 30,
                      31, 31, 30, 31, 30, 31};

        int start = Math.max(toDay(arriveAlice, days),
                             toDay(arriveBob, days));

        int end = Math.min(toDay(leaveAlice, days),
                           toDay(leaveBob, days));

        return Math.max(0, end - start + 1);
    }

    private int toDay(String date, int[] days) {
        String[] parts = date.split("-");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);

        for (int i = 0; i < month - 1; i++) {
            day += days[i];
        }

        return day;
    }
}