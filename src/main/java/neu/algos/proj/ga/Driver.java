package neu.algos.proj.ga;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import neu.algos.proj.ga.timetable.Lecture;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Driver extends Application {

    public static final int POPULATION_SIZE = (int)((long)readJSONData().get("population_size"));
    public static final double MUTATION_RATE = (double) readJSONData().get("mutation_rate");
    public static final double CROSSOVER_RATE = (double) readJSONData().get("crossover_rate");
    public static final int TOURNAMENT_SELECTION_SIZE = (int)((long)readJSONData().get("tournament_selection_size"));
    public static final int ELITE_SCHEDULES_COUNT = (int)((long)readJSONData().get("elite_schedule_count"));

    private Data data;
    private int scheduleNumber = 0;
    private int lectureNumber = 1;

    private static ArrayList<Double> fitnessList = new ArrayList<>();
    private static int totalGenerations;

    public static void main(String[] args){
        int generationNumber = 0;
//        ArrayList<Double> fitnessList = new ArrayList<>();

        Driver driver = new Driver();
        driver.data = new Data();

        driver.printAvailableData();

        System.out.println(">>> Generation #: "+generationNumber);
        System.out.print("Schedule #|");
        System.out.print("Lectures [Department, Lecture, Room, Professor, Meeting-time]");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t | Fitness | Conflicts");
        System.out.println("---------------------------------------------------------");

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
        Population population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
        population.getSchedules().forEach(schedule -> {
            System.out.println("\t"+driver.scheduleNumber++
                    +"\t| "+schedule+" | "+ String.format("%.5f", schedule.getFitness())
                    +" | "+schedule.getNumberOfConflicts());
        });

        driver.printSchedule(population.getSchedules().get(0), generationNumber);

        driver.lectureNumber = 1;
        while (population.getSchedules().get(0).getFitness()!=1.0) {
            System.out.println(">>> Generation #: "+ ++generationNumber);
            System.out.print("Schedule #|");
            System.out.print("Lectures [Department, Lecture, Room, Professor, Meeting-time]");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t | Fitness | Conflicts");
            System.out.println("---------------------------------------------------------");

            population = geneticAlgorithm.evolvePopulation(population).sortByFitness();
            driver.scheduleNumber = 0;
            population.getSchedules().forEach(schedule -> System.out.println("\t"+driver.scheduleNumber++
                    +"\t| "+schedule+" | "+ String.format("%.5f", schedule.getFitness())
                    +" | "+schedule.getNumberOfConflicts()));

            driver.printSchedule(population.getSchedules().get(0), generationNumber);
            fitnessList.add(population.getSchedules().get(0).getFitness());
        }

        totalGenerations = generationNumber;
        launch();
    }

    private void printSchedule(Schedule schedule, int generation){

        ArrayList<Lecture> lectures = schedule.getLectures();
        System.out.print("\n \t \t");
        System.out.println("Lecture #  | \tDepartment \t\t|\t\t Course (number, max students) \t | Room (Capacity) | " +
                "Professor (ID) \t\t| Meeting-time (ID)");
        System.out.print("\t\t");
        System.out.print("----------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------");
        lectures.forEach(x -> {
            int deptIndex = data.getDepartments().indexOf(x.getDepartment());
            int coursesIndex = data.getCourses().indexOf(x.getCourse());
            int roomsIndex = data.getRooms().indexOf(x.getRoom());
            int instructorsIndex = data.getProfessors().indexOf(x.getProfessor());
            int meetingTimeIndex = data.getMeetingTimes().indexOf(x.getMeetingTime());

            System.out.print("\t\t\t");
            System.out.print(String.format("  %1$02d  ", lectureNumber)+" |\t");
            System.out.print(String.format("%1$15s", data.getDepartments().get(deptIndex).getName())+" | ");
            System.out.print(String.format("%1$35s", data.getCourses().get(coursesIndex).getName()+
            " ("+data.getCourses().get(coursesIndex).getNumber()+", "
                    +data.getCourses().get(coursesIndex).getMaxStudents())+")\t |");
            System.out.print(String.format("%1$10s", data.getRooms().get(roomsIndex).getNumber()+" ("
                    +data.getRooms().get(roomsIndex).getMaxCapacity())+")\t   |");
            System.out.print(String.format("%1$20s", data.getProfessors().get(instructorsIndex).getName() +" ("
                    +data.getProfessors().get(instructorsIndex).getId())+")\t|");
            System.out.println(data.getMeetingTimes().get(meetingTimeIndex).getTime()
                    +" ("+data.getMeetingTimes().get(meetingTimeIndex).getId()+")");

            lectureNumber++;
        });

        if(schedule.getFitness() == 1) System.out.println(">>> Solution generated in "+ (generation+1) +" generations");
        System.out.print("\t\t----------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------");
    }

    public void printAvailableData(){

        System.out.println("Departments -------->");
        data.getDepartments().forEach(x -> System.out.println("name: "+x.getName() +", courses: "+x.getCourses()));

        System.out.println("");
        System.out.println("Courses -------->");
        data.getCourses().forEach(x -> System.out.println("course number: "+x.getNumber()
                +", name: "+x.getName() +", instructors: "+x.getProfessors()+", max students: "+x.getMaxStudents()));
        System.out.println("Rooms -------->");
        data.getRooms().forEach(x -> System.out.println("room number: "+x.getNumber()
                +", room capacity: "+x.getMaxCapacity()));
        System.out.println("Instructors -------->");
        data.getProfessors().forEach(x -> System.out.println("instructor id: "+x.getId()
                +", instructor name: "+x.getName()));
        System.out.println("Meeting Times -------->");
        data.getMeetingTimes().forEach(x -> System.out.println("meeting id: "+x.getId()+", meeting time: "+x.getTime()));
        System.out.println("---------------------------------------------------------");
    }

    private static JSONObject readJSONData() {

        JSONParser parser = new JSONParser();

        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/constants.json"));
//            Object obj = parser.parse(new FileReader("C:\\Users\\siddh\\OneDrive\\Documents\\GitHub\\Genetic-Algorithm\\src\\main\\resources\\constants.json"));

            jsonObject = (JSONObject) obj;

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Generation-Fitness Chart");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Generation Number");
        yAxis.setLabel("Fitness Score");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Timetable fitness chart");

        Scene scene = new Scene(lineChart, 800, 600);

        XYChart.Series series = plotData();
        lineChart.getData().add(series);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public XYChart.Series plotData() {
        XYChart.Series series = new XYChart.Series();
        System.out.println("generations: "+totalGenerations);
        System.out.println("fitness list: "+fitnessList.size());

        for (int i = 0; i<totalGenerations && fitnessList.size()!=0; i++) {
            series.getData().add(new XYChart.Data<>(i, fitnessList.get(i)));
        }

        return series;
    }
}
