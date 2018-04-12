package neu.algos.proj.ga.timetable;

import java.util.ArrayList;

public class Course {

    private String number;
    private String name;
    private int maxStudents;
    private ArrayList<Instructor> instructors;

    public Course(String number, String name, int maxStudents, ArrayList<Instructor> instructors) {
        this.number = number;
        this.name = name;
        this.maxStudents = maxStudents;
        this.instructors = instructors;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    @Override
    public String toString() {
        return name;
    }
}
