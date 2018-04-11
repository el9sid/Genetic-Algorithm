package neu.algos.proj.ga.timetable;

public class Room {

    private String number;
    private int maxCapacity;

    public Room(String number, int maxCapacity) {
        this.number = number;
        this.maxCapacity = maxCapacity;
    }

    public String getNumber() {
        return number;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
