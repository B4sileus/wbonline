package View;

import javax.swing.ImageIcon;

import Model.Animation;

public class AnimationMediumCoin extends Animation {
	
	public AnimationMediumCoin() {
		super();
		
		// attributes
		width = 30;
		height = 30;
		num_row = 1;
		num_col = 4;
		speed = 2;
		
		// image
		image = new ImageIcon( "res/img/coin02.png" ).getImage();
		
		// hash
		name = "coin02";
	}
	
}
