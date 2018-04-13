package neu.algos.proj.ga.timetable;

import java.util.ArrayList;

public class Course {

    private String number;
    private String name;
    private int maxStudents;
    private ArrayList<Professor> professors;

    public Course(String number, String name, int maxStudents, ArrayList<Professor> professors) {
        this.number = number;
        this.name = name;
        this.maxStudents = maxStudents;
        this.professors = professors;
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

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    @Override
    public String toString() {
        return name;
    }
}
