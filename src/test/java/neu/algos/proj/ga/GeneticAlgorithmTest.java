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
    }

    @Test
    public void mutateSchedule() {
    }

    @Test
    public void evolvePopulation() {
        Population p1 = new Population(200, data);
        Population p2 = geneticAlgorithm.evolvePopulation(p1);
        assertNotSame(p1,p2);
    }
}