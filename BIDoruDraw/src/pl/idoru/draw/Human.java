package pl.idoru.draw;

import pl.idoru.draw.HumanInterface;
import java.util.Random;
public abstract class Human implements HumanInterface {
	private Random randomGenerator = new Random();
	public String name = "";
	
	
	/*
	 Genders
	 0 - male
	 1 - female
	 */
	public int gender = 0;
	
	/*
	 Orientation
	 0 - hetero sexual
	 1 - homo sexual
	 */
	
	public int sex = 0;
	
	
	// Location of the character
	
	public int locX = 0;
	public int locY = 0;
	
	// Shows trend when character is going
	
	public int directionX = 0;
	public int directionY = 0;
	
	public int stepper = 0;
	
	private int maxStepper = 3;
	
	private int getNegate(int value) {
		if (value > 2)
			return (3-value)*2*randomGenerator.nextInt(5);
		return value*2*randomGenerator.nextInt(5);
		
	}
	
	
	public void regenerate() {
	      this.setNewTrend();
	      this.setNewDirection();
		
	}

	 public Human(String name) {
	      this.name = name;
	      this.sex = randomGenerator.nextInt(1);
	      this.locX  = randomGenerator.nextInt(400);
	      this.locY  = randomGenerator.nextInt(400);
	      
	      
	      
	      
	      this.regenerate();
	   }

	 
	 public void update() {
		 this.stepper--;
		 
		 if (this.stepper==0) {
		      this.regenerate();		 
		 }		 
		 this.locX = this.directionX + this.locX;
		 this.locY = this.directionY + this.locY;
	 }
	 
	 
	 public String getGender() {
		if (this.gender == 1) 
			return "female";
		return "male";
	 }
	 
	
	public void setNewDirection(){
		System.out.println("dirX" + this.directionX +  "dirY" + this.directionY);
	      this.directionX = this.getNegate(randomGenerator.nextInt(5));
	      this.directionY = this.getNegate(randomGenerator.nextInt(5));
		
	};
	public void setNewTrend(){
		  this.stepper = this.maxStepper;
	};
	
}
