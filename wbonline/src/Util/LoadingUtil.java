package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import Controller.Game;
import Model.Animated;
import View.Animation;
import View.Hero;

public class LoadingUtil {
	
	// reads the file about animations and add them on the map
	public static void loadAnimated(Map<String, Animated> map, String address) {
		try {
			// finding
			FileReader ent = new FileReader(address);
			BufferedReader buffered = new BufferedReader(ent);
			
			// reading
			String in = "";
			String a[];
			Animated aux = null;
			
			while( (in = buffered.readLine()) != null ) {
				if(in.contentEquals("-")) { // separates one from another
					map.put(aux.getName(), aux); // puts in the map
					continue; // start the other
				} else if(in.contentEquals("EOF")) { // end of file
					break;
				}
				
				// splitting
				a = in.split("=");
				
				if(a[1].contentEquals("new_animation")) { // new one
					aux = new Animation();
					aux.setName(a[0]); // sets its name, so I can find it later on the map
				} else if(a[1].contentEquals("new_hero")) { // new one
					aux = new Hero();
					aux.setName(a[0]); // also sets its name
				} else {
					// set the attributes
					LoadingUtil.readsAnimated(a[0], a[1], (Animated)aux);
				}
			}
			
			// end the job
			buffered.close();
			ent.close();
		} catch (Exception e) {
			// ops...
			System.out.println("File not found at " + address);
		}
	}
	// this read and set the attributes
	private static void readsAnimated(String attr, String val, Animated aux) {		
		switch(attr) {
		case "address": // sprite address
			aux.setImage( (new ImageIcon(val)).getImage() );
			break;
		case "icon": // icon address
			((Hero)aux).setIcon( (new ImageIcon(val)).getImage() );
			break;
		case "width": // width
			aux.setWidth( Integer.parseInt(val) );
			break;
		case "height": // height
			aux.setHeight( Integer.parseInt(val) );
			break;
		case "num_row": // number of rows
			aux.setNumRow(Integer.parseInt(val) );
			break;
		case "num_col": // number of columns
			aux.setNumCol( Integer.parseInt(val) );
			break;
		case "speed": // speed of the frames
			aux.setSpeed( Integer.parseInt(val) );
			break;
		case "space": // space between the draws
			aux.setSpace( Integer.parseInt(val) );
			break;
		// in the case it is an attribute of a child class, do a cast
		}
	}
	
	// loads the file containing the map info, and instanciate them on the scenario
	public static void addAnimations(Map<String, Animated> map, ArrayList<Animation> list, String address) {
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
	public static void addHeroes(Map<String, Animated> map, ArrayList<Hero> list, String address) {
		try {
			// finding
			FileReader ent = new FileReader(address);
			BufferedReader buffered = new BufferedReader(ent);
			
			// reading
			String in = "";
			String a[];
			Hero aux = null;
			
			while( (in = buffered.readLine()) != null ) {
				if(in.contentEquals("-")) { // end of this one
					list.add( aux ); // add this one
					aux = null; // new one
					continue;
				} else if(in.contentEquals("EOF")) { // end of file
					break;
				}
				
				// splitting
				a = in.split("=");
				
				if(a[1].contentEquals("new_hero")) { // new one
					aux = ((Hero)map.get( a[0] )).clone();
				} else {
					// setting attributes
					LoadingUtil.setAnimated(a[0], a[1], aux);
				}
			}
			
			// end of job
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
