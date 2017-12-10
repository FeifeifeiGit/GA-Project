import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GuessTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalFitness1() {
		Guess test1 = new Guess("1111");
	    test1.calFitness("1111");
		assertEquals(test1.fitness, 8);
	}
	
	@Test
	public void testCalFitness2() {
		Guess test1 = new Guess("1234");
	    test1.calFitness("2567");
		assertEquals(test1.fitness, 1);
	}
    
	@Test
	public void testCalFitness3() {
		Guess test1 = new Guess("1234");
	    test1.calFitness("7567");
		assertEquals(test1.fitness, 0);
	}
}
