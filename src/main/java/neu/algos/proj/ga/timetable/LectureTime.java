package neu.algos.proj.ga.timetable;

public class LectureTime {

    private String id;
    private String time;

    public LectureTime(String id, String time) {
        this.id = id;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}
