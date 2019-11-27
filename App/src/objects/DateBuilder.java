package objects;

public class DateBuilder{
    public String[] getYears(int yr) {
        String[] years = new String[yr];
        for (int i = 0; i < 117; i++) {
            years[i] = 1900 + i + "";
        }
        return years;
    }

    public String[] getDays(int day) {
        String[] nichi = new String[day];
        for (int i = 0; i < day; i++) {
            nichi[i] = 1 + i + "";
        }
        return nichi;
    }
}
