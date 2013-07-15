package View;

import java.awt.Graphics2D;
import java.awt.Image;

import Model.Animated;
import Model.State;
import Model.StateWaiting;

public class Hero extends Animated implements Cloneable {
	
	private int coins; // how many coins I have
	private Image image_icon; // my icon
	
	private State state; // my state
	
	public Hero() {
		// startup
		col = 1;
		coins = 0;
		//slot = 0;
		
		// state
		state = new StateWaiting();
	}
	
	// this is called every frame, and the hero do something (walk, attack)
	public int act() {
		state.act();
		
		return 0;
	}
	
	// just for testing
	public void walk() {
		if(++speed_actual == speed) {
			if(++col == num_col)
				col = 1;
			
			speed_actual = 0;
		}
	}
	
	// this clones the hero
	@Override
	public Hero clone() throws CloneNotSupportedException {
		Hero clone = (Hero) super.clone();
		
		// note: the image is not being clonned
		// the same instance is being passed ahed,
		// so be careful when manipulating it
		
		return clone;
	}
	
	// the name says everything
	public void drawYourIcon(Graphics2D g) {
		if(image_icon != null)
			g.drawImage(image_icon, 20, 20, 72, 84, 0, 0, 52, 64, null);
		else
			System.out.println("missing image_icon");
	}
	
	// getters
	public int getCoins() { return coins; }
	
	// setters
	public void setCoins(int coins) { this.coins = coins; }
	public void addCoins(int coins) { this.coins += coins; }
	public void setIcon(Image image_icon) { this.image_icon = image_icon; }
	
}
