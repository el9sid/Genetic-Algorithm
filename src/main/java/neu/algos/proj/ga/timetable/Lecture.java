package neu.algos.proj.ga.timetable;

public class Lecture {

    private int id;
    private Department department;
    private Course course;
    private Professor professor;
    private MeetingTime meetingTime;
    private Room room;

    public Lecture(int id, Department department, Course course) {
        this.id = id;
        this.department = department;
        this.course = course;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public Course getCourse() {
        return course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "{"+department.getName() +
                ", " + course.getNumber() +
                ", " + professor.getId() +
                ", " + meetingTime.getId() +
                ", " + room.getNumber() +
                '}';
    }
}
