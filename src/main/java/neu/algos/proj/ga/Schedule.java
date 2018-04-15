package neu.algos.proj.ga;

import neu.algos.proj.ga.timetable.Department;
import neu.algos.proj.ga.timetable.Lecture;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Lecture> lectures;
    private Data data;
    private int lectureNumber = 0;
    private int numberOfConflicts = 0;
    private boolean isFitnessChanged = true;
    private double fitness = -1;

    public Schedule(Data data) {
        this.data = data;
        lectures = new ArrayList<>(data.getNumberOfLectures());
    }

    public Schedule initialize() {

        new ArrayList<Department>(data.getDepartments()).forEach(department -> {
                    department.getCourses().forEach(course -> {
                        Lecture newLecture = new Lecture(lectureNumber++, department, course);
                        newLecture.setLectureTime(data.getLectureTimes().get((int) (data.getLectureTimes().size() * Math.random())));
                        newLecture.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
                        newLecture.setProfessor(data.getProfessors().get((int) (data.getProfessors().size() * Math.random())));
                        lectures.add(newLecture);
                    });
                }
        );
        return this;
    }

    public Data getData() {
        return data;
    }

    public int getNumberOfConflicts() {
        return numberOfConflicts;
    }

    public ArrayList<Lecture> getLectures() {
        isFitnessChanged = true;
        return lectures;
    }

    public double getFitness() {
        if(isFitnessChanged){
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    private double calculateFitness(){
        numberOfConflicts = 0;
        lectures.forEach(x -> {
            //check if room capacity is less than the number of students
            if(x.getRoom().getMaxCapacity() < x.getCourse().getMaxStudents()) numberOfConflicts++;
            lectures.stream().filter(y -> lectures.indexOf(y) >= lectures.indexOf(x)).forEach(y -> {
                //check if the lecture times are same for different lectures
                if(x.getLectureTime() == y.getLectureTime() && x.getId() != y.getId()){
                    //check if same room is assigned to multiple lectures
                    if(x.getRoom() == y.getRoom()) numberOfConflicts++;
                    //check if an instructor has multiple engagements at the same time
                    if (x.getProfessor() == y.getProfessor()) numberOfConflicts++;
                }

            });
        });
        return 1/(double)(numberOfConflicts + 1); //adding 1 takes care of fitness if number of conflicts is 0
    }

    @Override
    public String toString() {
        String value = "";
        for (int i = 0; i < lectures.size()-1; i++) {
            value = value+lectures.get(i) + ",";
        }
        value += lectures.get(lectures.size()-1);
        return value;
    }
}
