package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import Controller.Game;
import Model.Animated;
import Model.Animation;
import View.AnimationMediumCoin;
import View.AnimationSmallCoin;

public class LoadingUtil {
	
	// adds the animations to the map, so i can easily clone them
	public static void addToTheAnimationMap(Map<String, Animation> map) {
		map.put("coin01", new AnimationSmallCoin());
		map.put("coin02", new AnimationMediumCoin());
	}
	
	// loads the file containing the map info, and instantiate them on the scenario
	public static void addAnimations(Map<String, Animation> map, ArrayList<Animation> list, String address) {
		try {
			// finding
			FileReader ent = new FileReader(address);
			BufferedReader buffered = new BufferedReader(ent);
			
			// reading
			String in = "";
			String a[];
			Animation aux = null;
			
			while( (in = buffered.readLine()) != null ) {
				if(in.contentEquals("-")) { // end this one
					list.add( aux ); // add this one
					aux = null; // new one
					continue;
				} else if(in.contentEquals("EOF")) { // end of file
					break;
				}
				
				// splitting
				a = in.split("=");
				
				// note: change to new_animation
				if(a[1].contentEquals("new_animacao")) { // new one
					// clones the existing object from the map, so it can set
					// some attributes, and add to the game.
					// important: it doesn't clone the reference to the image,
					// so be careful when modifying it.
					aux = ((Animation)map.get( a[0] )).clone();
				} else {
					// set some attributes
					LoadingUtil.setAnimated(a[0], a[1], aux);
				}
			}
			
			// end of the job
			buffered.close();
			ent.close();
		} catch (Exception e) {
			// ops...
			System.out.println("File not found at " + address);
		}
	}
	// it sets the attributes
	private static void setAnimated(String attr, String val, Animated aux) {
		Random random; // random value
		
		switch(attr) {
		case "x": // x axis
			aux.setX( Integer.parseInt(val) );
			break;
		case "y": // y axis
			aux.setY( Integer.parseInt(val) );
			break;
		case "rand_x": // random x axis
			random = new Random();
			aux.setX( random.nextInt( Game.WINDOW_WIDTH ) );
			break;
		case "rand_y": // random y axis
			random = new Random();
			aux.setY( random.nextInt( Game.WINDOW_HEIGHT ) );
			break;
		// case it is an attribute from a child class, do a cast
		}
	}
	
	
}
