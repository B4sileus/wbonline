package Util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public abstract class FontUtil {
	
	// space In Sprite is the space between two words in the font sprite
	private static int SPACE_IS = 2, SPACE = 1;
	// the colors that can be written
	public static int WHITE = 1, YELLOW = 2;
	// the font image
	private static Image image;
	// the map with the letter images
	private static Map<Integer, Letter> map_w; //, mapa_y;
	
	// a class so I can search some information about the letter
	private static class Letter {
		private int x, y;
		private int height, width;
		
		private Letter(int height, int width, int x, int y) {
			this.width = height;
			this.height = width;
			this.x = x;
			this.y = y;
		}
		
		public int getX() { return x; }
		public int getY() { return y; }
		public int getWidth() { return width; }
		public int getHeight() { return height; }
	}
	
	// loads the font file
	public static void loadFont(String address) {
		int wid_default = 9, hei_default = 10;
		int sum_x = 0, y = 0;
		int width;
		
		try {
			// finding
			FileReader ent = new FileReader(address);
			BufferedReader buffered = new BufferedReader(ent);
			
			// reading
			String in = "";
			String a[];
			
			while( (in = buffered.readLine()) != null ) {
				
				a = in.split("=");
				
				// all letters have the same height, but have different width
				if(a[1].contentEquals("new")) { // new letter
					
					if(a[2].contentEquals("default")) { // default width
						addLetter(a[0], wid_default, hei_default, sum_x, y);
						
						sum_x += wid_default + SPACE_IS;
					} else { // custom width
						width = Integer.parseInt( a[2] );
						addLetter(a[0], width, hei_default, sum_x, y);
						
						sum_x += width + SPACE_IS;
					}
					
				} else if(a[0].contentEquals("x")) { // come back
					sum_x = Integer.parseInt( a[1] );
				} else if(a[0].contentEquals("y")) { // new row
					y = Integer.parseInt( a[1] );
				} else if(a[0].contentEquals("width")) { // new default height
					wid_default = Integer.parseInt( a[1] );
				} else if(a[0].contentEquals("height")) { // new default width
					hei_default = Integer.parseInt( a[1] );
				} else if(a[0].contentEquals("address")) { // the address of the image
					image = new ImageIcon(a[1]).getImage();
				}
			}
			
			buffered.close();
			ent.close();
		} catch (Exception e) {
			// ops...
			System.out.println("File not found at " + address);
		}
	}
	// this adds the letter in the map, so I can easily search it later
	public static void addLetter(String letter, int width, int height, int x, int y) {
		if(map_w == null)
			map_w = new HashMap<Integer, Letter>();

		map_w.put( (int)letter.charAt(0), new Letter(width, height, x, y));
	}
	
	// this will write something on the video
	// for now, it is just a message, with no effects
	// remember that the letters have the black background
	public static void write(String text, Graphics2D g, int dx, int dy, int color) {
		Letter letter;
		int sum_x = 0;
		
		// choosing the color
		HashMap<Integer, Letter> aux = null;
		if(color == FontUtil.WHITE)
			aux = (HashMap<Integer, Letter>) map_w;
		else if(color == FontUtil.YELLOW)
			aux = (HashMap<Integer, Letter>) map_w; // to change
		
		// for each letter of the phrase
		for(int i=0; i<text.length(); i++) {
			letter = aux.get( (int)text.charAt(i) );
			
			// draw the letter
			g.drawImage(image, dx+sum_x, dy, dx+letter.getWidth()+sum_x, dy+letter.getHeight(), 
					letter.getX(), letter.getY(), letter.getX()+letter.getWidth(), 
					letter.getY()+letter.getHeight(), null);
			
			sum_x += letter.getWidth() + SPACE;
		}
	}
	
}
