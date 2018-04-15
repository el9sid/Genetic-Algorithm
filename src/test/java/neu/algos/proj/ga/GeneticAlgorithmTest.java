package neu.algos.proj.ga;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneticAlgorithmTest {

    private Data data = new Data();
    private GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(data);


    @Test
    public void crossoverPopulation() {
        Population p1 = new Population(100, data);
        Population p2 = geneticAlgorithm.crossoverPopulation(p1);
        assertNotSame(p2,p1);
    }

    @Test
    public void selectTournamentPopulation() {
        Population p1 = new Population(100, data);
        Population p2 = geneticAlgorithm.selectTournamentPopulation(p1);
        assertNotSame(p2,p1);
    }

    @Test
    public void mutatePopulation() {
        Population p1 = new Population(200, data);
        Population p2 = geneticAlgorithm.mutatePopulation(p1);

        assertNotNull(p2);
        assertNotNull(p1);
        assertNotSame(p2,p1);
    }

    @Test
    public void crossoverSchedule() {
        Population p1 = new Population(200, data);
        Schedule s1 = p1.sortByFitness().getSchedules().get(0);
        Schedule s2 = p1.sortByFitness().getSchedules().get(1);
        Schedule s3 = geneticAlgorithm.crossoverSchedule(s1, s2);

        assertNotNull(s1);
        assertNotNull(s2);
        assertNotSame(s1, s3);
        assertNotSame(s2, s3);
    }

    @Test
    public void evolvePopulation() {
        Population p1 = new Population(200, data);
        Population p2 = geneticAlgorithm.evolvePopulation(p1);
        assertNotSame(p1,p2);
    }
}