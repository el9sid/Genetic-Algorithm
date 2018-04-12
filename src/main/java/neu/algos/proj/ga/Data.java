package neu.algos.proj.ga;

import neu.algos.proj.ga.timetable.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {

    private ArrayList<Room> rooms;
    private ArrayList<Instructor> instructors;
    private ArrayList<Department> departments;
    private ArrayList<Course> courses;
    private ArrayList<MeetingTime> meetingTimes;

    private int numberOfLectures = 0;

    public Data() {
        initialize();
    }

    private Data initialize(){

        Room room1 = new Room("R1", 25);
        Room room2 = new Room("R2", 35);
        Room room3 = new Room("R3", 45);
        rooms = new ArrayList<Room>(Arrays.asList(room1,room2,room3));

        MeetingTime meetingTime1 = new MeetingTime("MT1", "MWF 09:00 - 10:00");
        MeetingTime meetingTime2 = new MeetingTime("MT2", "MWF 10:00 - 11:00");
        MeetingTime meetingTime3 = new MeetingTime("MT3", "TTH 09:00 - 10:30");
        MeetingTime meetingTime4 = new MeetingTime("MT4", "TTH 10:30 - 12:00");
        meetingTimes = new ArrayList<MeetingTime>(Arrays.asList(meetingTime1, meetingTime2, meetingTime3, meetingTime4));

        Instructor instructor1 = new Instructor("I1", "Dr. Hillyard");
        Instructor instructor2 = new Instructor("I2", "Dr. Bugrara");
        Instructor instructor3 = new Instructor("I3", "Dr. Parikh");
        Instructor instructor4 = new Instructor("I4", "Dr. Ozbek");
        instructors = new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2, instructor3, instructor4));

        Course course1 = new Course("C1", "Physics101", 25, new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2)));
        Course course2 = new Course("C2", "Mathematics101", 30, new ArrayList<Instructor>(Arrays.asList(instructor2,instructor3)));
        Course course3 = new Course("C3", "Chemistry301", 45, new ArrayList<Instructor>(Arrays.asList(instructor4)));
        Course course4 = new Course("C4", "Algorithms301", 35, new ArrayList<Instructor>(Arrays.asList(instructor1, instructor4)));
        Course course5 = new Course("C5", "CryptoCurrency401", 35, new ArrayList<Instructor>(Arrays.asList(instructor2, instructor3)));
        Course course6 = new Course("C6", "SmartContracts201", 25, new ArrayList<Instructor>(Arrays.asList(instructor2, instructor4)));
        Course course7 = new Course("C7", "ApplicationEngineering301", 45, new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2, instructor3, instructor4)));
        courses = new ArrayList<Course>(Arrays.asList(course1, course2, course3, course4, course5, course6, course7));

        Department department1 = new Department("Engineering", new ArrayList<Course>(Arrays.asList(course1, course2, course3)));
        Department department2 = new Department("Computers", new ArrayList<Course>(Arrays.asList(course4, course7)));
        Department department3 = new Department("BlockChain", new ArrayList<Course>(Arrays.asList(course5, course6)));
        departments = new ArrayList<Department>(Arrays.asList(department1, department2, department3));
        departments.forEach(x -> numberOfLectures += x.getCourses().size());


        return this;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public int getNumberOfLectures() {
        return numberOfLectures;
    }
}
