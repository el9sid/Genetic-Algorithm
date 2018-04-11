package neu.algos.proj.ga;

public class GeneticAlgorithm {

    //Evoloves the population from one generation to other using crossover.

    private Data data;

    public GeneticAlgorithm(Data data) {
        this.data = data;
    }

    public Population crossoverPopulation(Population population) {

        return null;
    }

    public Population selectTournamentPopulation(Population population) {

        return null;
    }

    public Population mutatePopulation(Population population) {

        return null;
    }

    public Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2) {

        return null;
    }

    public Schedule mutateSchedule(Schedule scheduleMutation){

        return null;
    }

    public Population evolvePopulation(Population population){

        return mutatePopulation(population);
    }
}
