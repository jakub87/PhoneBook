package phonebook;

public class Time {
    public static String calculateTime(long time) {
        int minutes = (int) (time / 60_000);
        int seconds = (int) ((time % 60_000) * 0.001);
        int miliSeconds = (int) ((time % 60_000) % 1000);
        return "Time taken: "+minutes+" min. "+seconds+" sec. "+miliSeconds+" ms.";
    }
}
