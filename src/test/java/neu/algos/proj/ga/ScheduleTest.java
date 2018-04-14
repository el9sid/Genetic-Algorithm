package neu.algos.proj.ga;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScheduleTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Data data = new Data();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(data);
        Schedule schedule = new Schedule(data);
    }

    @Test
    public void getNumberOfConflicts() {
        int conflicts = 5;


    }

    @Test
    public void getFitness() {

    }
}