package Model;

import java.awt.Graphics2D;
import java.awt.Image;

import Interfaces.HeroInterface;

public abstract class Hero extends Animated implements Cloneable, HeroInterface {
	
	protected int coins; // how many coins I have
	protected Image image_icon; // my icon
	
	protected State state; // my state
	
	public Hero() {
		super();
		
		// startup
		col = 1;
		coins = 0;
		//slot = 0;
		
		// state
		state = new StateWaiting();
	}
	
	// draws its icon
	public void drawYourIcon(Graphics2D g) {
		if(image_icon != null)
			g.drawImage(image_icon, 20, 20, 72, 84, 0, 0, 52, 64, null);
		else
			System.out.println("missing image_icon");
	}
	
	// to re-write
	public void act() {
		
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
	
	// getters
	public int getCoins() { return coins; }
	
	// setters
	public void setCoins(int coins) { this.coins = coins; }
	public void addCoins(int coins) { this.coins += coins; }
	public void setIcon(Image image_icon) { this.image_icon = image_icon; }
	
}
