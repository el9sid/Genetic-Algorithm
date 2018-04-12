package neu.algos.proj.ga;

public class Driver {

    public static final int POPULATION_SIZE = 9;
    public static final double MUTATION_RATE = 0.01;
    public static final double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int ELITE_SCHEDULES_COUNT = 2;

    private Data data;
    public static void main(String[] args){
        int generationNumber = 0;
        Driver driver = new Driver();
        driver.data = new Data();

        driver.printAvailableData();

        System.out.println("Generation: "+generationNumber);
        System.out.print("Schedule ");
        System.out.print("Lectures [department, lecture, room, instructor, meeting time]");
        System.out.println("Fitness | Conflicts");
        System.out.println("----------------------------------------------------------");
    }

    public void printAvailableData(){

        System.out.println("Departments -------->");
        data.getDepartments().forEach(x -> System.out.println("name: "+x.getName() +", courses: "+x.getCourses()));

        System.out.println("");
        System.out.println("Courses -------->");
        data.getCourses().forEach(x -> System.out.println("course number: "+x.getNumber()
                +", name: "+x.getName() +", instructors: "+x.getInstructors()+", max students: "+x.getMaxStudents()));
        System.out.println("Rooms -------->");
        data.getRooms().forEach(x -> System.out.println("room number: "+x.getNumber()
                +", room capacity: "+x.getMaxCapacity()));
        System.out.println("Instructors -------->");
        data.getInstructors().forEach(x -> System.out.println("instructor id: "+x.getId()
                +", instructor name: "+x.getName()));
        System.out.println("Meeting Times -------->");
        data.getMeetingTimes().forEach(x -> System.out.println("meeting id: "+x.getId()+", meeting time: "+x.getTime()));
        System.out.println("---------------------------------------------------------");
    }
}
