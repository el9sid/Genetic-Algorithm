package neu.algos.proj.ga;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class GeneticAlgorithm {

    //Evolves the population from one generation to other using crossover.

    private Data data;

    public GeneticAlgorithm(Data data) {
        this.data = data;
    }

    public Population crossoverPopulation(Population population) {

        Population crossoverPopulation = new Population(population.getSchedules().size(), data);

        //performing elitism
        IntStream.range(0, GenerateSchedule.ELITE_SCHEDULES_COUNT)
                .forEach(x -> crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)));

        //for remaining schedules
        IntStream.range(GenerateSchedule.ELITE_SCHEDULES_COUNT, population.getSchedules().size()).forEach(x -> {
            if (GenerateSchedule.CROSSOVER_RATE > Math.random()) {
                //get the fittest schedules to crossover and create new population
                Schedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                Schedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);

                crossoverPopulation.getSchedules().set(x, crossoverSchedule(schedule1, schedule2));
            } else {
                crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x));
            }
        });

        return crossoverPopulation;
    }

    public Population selectTournamentPopulation(Population population) {

        Population tournamentPopulation = new Population(GenerateSchedule.SELECTION_SIZE, data);
        IntStream.range(0, GenerateSchedule.SELECTION_SIZE).forEach(x -> {
            //get random populations based on the tournament size
            tournamentPopulation.getSchedules()
                    .set(x, population.getSchedules()
                            .get((int) (Math.random() * population.getSchedules().size())));
        });
        return tournamentPopulation;
    }

    public Population mutatePopulation(Population population) {

        Population mutatePopulation = new Population(population.getSchedules().size(), data);
        ArrayList<Schedule> schedules = mutatePopulation.getSchedules();
        IntStream.range(0, GenerateSchedule.ELITE_SCHEDULES_COUNT)
                .forEach(x -> schedules.set(x, population.getSchedules().get(x)));

        // performing mutation on schedules other than elite schedules
        IntStream.range(GenerateSchedule.ELITE_SCHEDULES_COUNT, population.getSchedules().size())
                .forEach(x -> schedules.set(x, mutatePopulation.getSchedules().get(x)));
        return mutatePopulation;
    }

    public Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2) {
        Schedule crossoverSchedule = new Schedule(data).initialize();
        IntStream.range(0, crossoverSchedule.getLectures().size()).forEach(x -> {
            if (Math.random() > 0.5) crossoverSchedule.getLectures().set(x, schedule1.getLectures().get(x));
            else crossoverSchedule.getLectures().set(x, schedule2.getLectures().get(x));
        });
        return crossoverSchedule;
    }

    public Schedule mutateSchedule(Schedule scheduleMutation) {
        Schedule schedule = new Schedule(data).initialize();
        IntStream.range(0, scheduleMutation.getLectures().size()).forEach(x -> {
            if(GenerateSchedule.MUTATION_RATE > Math.random()) scheduleMutation.getLectures().set(x, schedule.getLectures().get(x));
            else scheduleMutation.getLectures().set(x-1, scheduleMutation.getLectures().get(x));
        });

        return scheduleMutation;
    }

    public Population evolvePopulation(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }
}
