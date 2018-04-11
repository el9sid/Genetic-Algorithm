package neu.algos.proj.ga;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {

    private ArrayList<Schedule> schedules;

    public Population(int size, Data data) {
        schedules = new ArrayList<>(size);
        IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(data).initialize()));
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public Population sortByFitness(){
        schedules.sort((schedule1, schedule2)->{
            int value = 0;
            if(schedule1.getFitness() > schedule2.getFitness()) value = -1;
            else if(schedule1.getFitness() < schedule2.getFitness()) value = 1;
            return value;
        });
        return this;
    }
}
