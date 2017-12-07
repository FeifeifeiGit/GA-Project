## Genetic Algorithm of Bulls and Cows Game
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

### Experiment


   
   
