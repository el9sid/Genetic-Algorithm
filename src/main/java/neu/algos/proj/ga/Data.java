package neu.algos.proj.ga;

import neu.algos.proj.ga.timetable.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Data {

    private ArrayList<Room> rooms;
    private ArrayList<Professor> professors;
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
//        rooms = new ArrayList<Room>((Collection<? extends Room>) readJSONData().get("Room List"));
//        rooms.addAll((Collection<? extends Room>) readJSONData().get("Room List"));
//        System.out.println(rooms);

        MeetingTime meetingTime1 = new MeetingTime("MT1", "MWF 09:00 - 10:00");
        MeetingTime meetingTime2 = new MeetingTime("MT2", "MWF 10:00 - 11:00");
        MeetingTime meetingTime3 = new MeetingTime("MT3", "TTH 09:00 - 10:30");
        MeetingTime meetingTime4 = new MeetingTime("MT4", "TTH 10:30 - 12:00");
        meetingTimes = new ArrayList<MeetingTime>(Arrays.asList(meetingTime1, meetingTime2, meetingTime3, meetingTime4));

        Professor professor1 = new Professor("P1", "Dr. Hillyard");
        Professor professor2 = new Professor("P2", "Dr. Bugrara");
        Professor professor3 = new Professor("P3", "Dr. Parikh");
        Professor professor4 = new Professor("P4", "Dr. Ozbek");
        professors = new ArrayList<Professor>(Arrays.asList(professor1, professor2, professor3, professor4));
//        professors = new ArrayList<Professor>((Collection<? extends Professor>) readJSONData().get("Professor List"));

        Course course1 = new Course("C1", "Physics101", 25, new ArrayList<Professor>(Arrays.asList(professor1, professor2)));
        Course course2 = new Course("C2", "Mathematics101", 30, new ArrayList<Professor>(Arrays.asList(professor2, professor3)));
        Course course3 = new Course("C3", "Chemistry301", 45, new ArrayList<Professor>(Arrays.asList(professor4)));
        Course course4 = new Course("C4", "Algorithms301", 35, new ArrayList<Professor>(Arrays.asList(professor1, professor4)));
        Course course5 = new Course("C5", "CryptoCurrency401", 35, new ArrayList<Professor>(Arrays.asList(professor2, professor3)));
        Course course6 = new Course("C6", "SmartContracts201", 25, new ArrayList<Professor>(Arrays.asList(professor2, professor4)));
        Course course7 = new Course("C7", "ApplicationEngineering301", 45, new ArrayList<Professor>(Arrays.asList(professor1, professor2, professor3, professor4)));
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

    public ArrayList<Professor> getProfessors() {
        return professors;
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

    //read data from a json file
    private JSONObject readJSONData(){

        JSONParser parser = new JSONParser();

        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader("C:\\Users\\siddh\\OneDrive\\Documents\\GitHub\\Genetic-Algorithm\\src\\main\\resources\\data.json"));

            jsonObject = (JSONObject) obj;

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    //for unit testing the Data class
//    public static void main(String[] args) {
//        Data data = new Data();
//        data.initialize();
//    }
}
