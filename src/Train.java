import java.util.Arrays;
import java.util.Comparator;

import org.apache.log4j.Logger;

public class Train {
	public static final String SECRET = "3456";
	public static final int BULL_WEIGHT = 2;
	public static final int COW_WEIGHT = 1;
	
	Guess[] generation;
	int num_generation;
	boolean win;
	int times;
	
	private static final Logger log = Logger.getLogger(Train.class);
	
	/*
	 * constructor of Train class
	 * upperBound: the maximum evolving time 
	 * count: the number of guess of every generation
	 */
	public Train(int upperBound, int count){
		log.info(" Constructor Called!");
		log.info(" SECRET=" + SECRET + ", BULL_WEIGHT=" + BULL_WEIGHT + ", COW_WEIGHT="
				+ COW_WEIGHT + ", maximum evolving times = " + times );
		this.times = upperBound;
		initialize(count);
	}
	
	/*
	 * create the initial generation
	 */
	void initialize(int count){
		log.info("-------- Initialize a new Experiment! -----");		
		generation = new Guess[count];
		for(int i = 0; i < count; i++){
			generation[i] = new Guess();//create generation 0
		}
		num_generation = 0;
		win = false;
		log.info(count + " Guess are created as the Generation 0");
	}
	
    /*
     * evolving method, a new generation is evolved based on current parent generation
     * when time of evolving reaches the maximum, stop evolving. 
     * when a match is found win == true, stop evolving.
     */
	void evoleToNext(){
		while(times > 0 && !win){
			int half = generation.length/2;
			for(int i = 0; i < half; i++){
				Guess new_guess =  generation[i].evolve();
				generation[i+half] = new_guess;
			}
			num_generation++;//new generation created
			times--;
			
			log.info(" A new generation is created: Generation: " + num_generation);
			checkCurrentGeneration();
		}
		
		if(times == 0){
			log.info(" No perfect candidate found within " + times + "generations.");
		}
	}
	
	/*
	 * calculate fitness for every individual of the generation
	 * sort in descending order by fitness
	 * 
	 */
	void checkCurrentGeneration(){
		for(Guess g: generation){
			g.calFitness(SECRET);
		}
		Arrays.sort(generation, new Comparator<Guess>(){
			public int compare(Guess o1, Guess o2) {
			 return o2.fitness - o1.fitness;
			}
		});
		if(generation[0].bulls == 4) {
			win = true;
		  log.info(" A perfect candidate Found: " + generation[0].sequence + " in generation " + num_generation);
		}
	}
	
	public static void main(String[] args) {
		//maximum number of generation is 100, every generation has 16 individual
		Train test = new Train(100,16);
	    test.checkCurrentGeneration();
	    test.evoleToNext();  
	}
}