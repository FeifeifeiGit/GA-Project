## Genetic Algorithm of Bulls and Cows Game
#### Team Members: Fei Li, HuiWen Gan
### Introduction
  This project used GA to play the bulls and cows game. Bulls and Cows game : given a serect number - "2345", and we need to guess the secret number. After every guess we can get a feedback telling you how many bulls and how many cows you got.  Bull - same number at the same position, cow - same number at different positions. In this project we get one batch of Guess as one generation, caluculate their fitness respectively based on bulls and cows. We choose the first half Guess after sorting by fitness and evlove to a new generation through mutation. Repeat the evolving utile a corret guess has been made - bulls is length of the secret number.     
### Outline   
* Functionatilty implementation
* Parameter expetriment            

### Implementation

* **Guess Class:**  
  
  * Viriable: number sequence, fitness, bulls and cows, 
  * Methods to munipulate the guess object - mutate the sequence, calculate the fitness
  
* **Train Class:** main class to run the GA process.
  * Initialize generation 0. 
  * Calculate their fitness can sort by it descendingly. 
  * Evolve to a new generation by culling half of guesses from last generation and mutate the other half which has higher fitness to form a new generation.
  * Repeat the evolving process until one Guess is corrent in current generation, GA stops.   
  
### Generic Code//Mutator 
* Generic Code: Using a String to represent the gene, each character is '1' - '9'
  ```java
   public class Guess {
	      int fitness;
	      int bulls;//bulls this.sequence compared with SECRET
	      int cows;//cows this.sequence compared with SECRET
	      String sequence;//Gene code
	}
  ```
  * Mutator: pick a random index and change that character into a random character ( '1' - '9')
  ```java
  void mutate(){
		int idx  = rn.nextInt(Integer.MAX_VALUE)%4;
		int new_number = rn.nextInt(Integer.MAX_VALUE)%10;
		char[] tmp = sequence.toCharArray();
		tmp[idx] = (char) (new_number + '0');
		sequence = new String(tmp);
	}
  ```
### Gene Expression
* Every character in sequence represents the gene corresponding with the gene at the same index in SECRET (the word we need to guess)
### Fitness Function
* Calculate bulls and cows bases on sequence. And add them together with weight to form the fitness value of this Guess
```Java
	private void calBullsCows(String secret){
		int[] numbers = new int[10];
		this.bulls = 0;
		this.cows = 0;
		for(int i = 0; i < secret.length(); i++){
			char s = secret.charAt(i);
			char c = sequence.charAt(i);
			if(s == c) {
				bulls++;
			}else{
				if(numbers[s-'0'] < 0) cows++;
				numbers[s-'0']++;//if one character occurs in secret, increment numbers[s-'0]
				if(numbers[c-'0'] > 0) cows++;
				numbers[c-'0']--;
			}
		}
	}
	public void calFitness(String secret){
		this.calBullsCows(secret);
		this.fitness = this.bulls*Train.BULL_WEIGHT + this.cows*Train.COW_WEIGHT;
	}
```
### Sort Function
* Use system sort in java, sorting by fitness
```java
	Arrays.sort(generation, new Comparator<Guess>(){
			public int compare(Guess o1, Guess o2) {
			 return o2.fitness - o1.fitness;
			}
		});
```
### Evolution mechanism
* pick the top 8 out pof 16 in current generation, mutate each of them and combine with the top 8 parents to form new generation
```java
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
```
### Logging Function
* Adopted log4j to record evolving process
* log when generation 0 is created
* log when new generation is generated
* log when a sucessful guess is made, log the sequence
* log when no sucess got while max evolution times reached


### Unit Test
* Test fitness calculation method using Junit test. 
* Other methods: mutate/initialize all involve random number, did not use unit test.

### Conclusion
 * GA algrithm is an effective solution to solve word guessing games. With this project, we can successfully guess the secret word within 100 generations.


   
   
