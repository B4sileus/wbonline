package View;

import Model.Animated;

public class Animation extends Animated implements Cloneable {
	
	public Animation() {
		// something
	}
	
	// this animates the animation, making it changes between the sprite's frames
	public void act() {
		if(++speed_actual == speed) {
			if(++col == num_col)
				col = 0;
			
			speed_actual = 0;
		}
	}
	
	// this will clone the animation, when I want severals instances
	@Override
	public Animation clone() throws CloneNotSupportedException {
		Animation clone = (Animation) super.clone();
		
		return clone;
	}
	
}
