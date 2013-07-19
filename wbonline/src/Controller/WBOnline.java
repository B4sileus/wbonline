package Controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Animation;
import Model.Hero;
import Util.FontUtil;
import Util.LoadingUtil;
import View.HeroShion;

public class WBOnline extends Game {
	
	// not important
	private int x, dx;
	
	// --- important ---
	// this map will contain the animations of the game, so I can access by the name
	private Map<String, Animation> hash;
	// the list with the animated objects
	private ArrayList<Animation> animations;
	// the list with the heroes (one is yours, the others are for future online)
	private ArrayList<Hero> heroes;
	
	public void onLoad() {
    	x = 150;
    	dx = 2;
    	
    	// --- loading ---
    	hash = new HashMap<String, Animation>();
    	LoadingUtil.addToTheAnimationMap(hash);
    	
    	animations = new ArrayList<Animation>();
    	LoadingUtil.addAnimations(hash, animations, "res/metadata/stage01");
    	
    	heroes = new ArrayList<Hero>();
    	heroes.add( new HeroShion() );
    	
    	FontUtil.loadFont("res/metadata/font");
	}

	public void onUnload() {
		
	}

	public void onUpdate() {
		x += dx;
		
		if(x > 400)
			dx = -2;
		if(x < 100)
			dx = 2;
		
		// the animated objects doing what they do
		for(int i=0; i<animations.size(); i++) {
			animations.get(i).act();
		}
		
		// the heroes acting
		for(int i=0; i<heroes.size(); i++) {
			heroes.get(i).act();
		}
	}

	public void onRender(Graphics2D g) {
		// background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // non sense ball
        g.setColor(Color.BLACK);
        g.fillOval(x, 10, 10, 10);
        
        // drawing the animated objects
        for(int i=0; i<animations.size(); i++) {
        	Animation aux;
        	aux = animations.get(i);
        	
        	// they can draw themselves
        	aux.drawYourself(g);
        }
        
        // drawing the heroes
        for(int i=0; i<heroes.size(); i++) {
        	Hero aux;
        	aux = heroes.get(i);
        	
        	// they also can draw themselves
        	aux.drawYourself(g);
        	// and their icons
        	aux.drawYourIcon(g);
        }
        
        // testing the fonts
        g.fillRect(270, 170, 280, 50);
        FontUtil.write("Cristhian", g, 275, 175, FontUtil.WHITE);
        FontUtil.write("Ola aventureiro, esta preparado?", g, 275, 190, FontUtil.WHITE);
        FontUtil.write("             ^", g, 350, 205, FontUtil.WHITE);
	}
}
