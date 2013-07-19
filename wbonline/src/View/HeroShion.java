package View;

import javax.swing.ImageIcon;

import Interfaces.HeroInterface;
import Model.Hero;

public class HeroShion extends Hero implements HeroInterface {
	
	public HeroShion() {
		super();
		
		// test
		x = 100;
		y = 100;
		
		// attributes
		width = 50;
		height = 64;
		num_row = 1;
		num_col = 5;
		speed = 3;
		space = 1;
		
		// images
		image = new ImageIcon( "res/img/heroi.png" ).getImage();
		image_icon = new ImageIcon( "res/img/heroi_icon.png" ).getImage();
	}
	
	public void act() {
		// do something
	}
}
