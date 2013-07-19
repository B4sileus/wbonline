package View;

import javax.swing.ImageIcon;

import Model.Animation;

public class AnimationSmallCoin extends Animation {
	
	public AnimationSmallCoin() {
		super();
		
		// attributes
		width = 22;
		height = 22;
		num_row = 1;
		num_col = 4;
		speed = 2;
		
		// image
		image = new ImageIcon( "res/img/coin01.png" ).getImage();
		
		// hash
		name = "coin01";
	}

}
