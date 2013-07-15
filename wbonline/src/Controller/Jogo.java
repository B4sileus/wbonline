package Controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Animado;
import Util.FonteUtil;
import Util.LoadingUtil;
import View.Animacao;
import View.Heroi;

public class Jogo extends Game {
	
	// not important
	private int x, dx;
	
	// --- important ---
	// the map where the animations are goona be stored
	private Map<String, Animado> hash;
	// the list with the animated objects
	private ArrayList<Animacao> animacoes;
	// the list with the heroes (one is yours, the others are for future online)
	private ArrayList<Heroi> herois;
	
	public void onLoad() {
    	x = 150;
    	dx = 2;
    	
    	// --- loading ---
    	hash = new HashMap<String, Animado>();
    	LoadingUtil.loadAnimado(hash, "res/metadata/ani_info");
    	LoadingUtil.loadAnimado(hash, "res/metadata/her_info");
    	
    	animacoes = new ArrayList<Animacao>();
    	LoadingUtil.addAnimacoes(hash, animacoes, "res/metadata/ani_opt");
    	
    	herois = new ArrayList<Heroi>();
    	LoadingUtil.addHerois(hash, herois, "res/metadata/her_opt");
    	
    	FonteUtil.loadFonte("res/metadata/fonte");
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
		for(int i=0; i<animacoes.size(); i++) {
			animacoes.get(i).anima();
		}
		
		// the heroes acting
		for(int i=0; i<herois.size(); i++) {
			herois.get(i).acao();
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
        for(int i=0; i<animacoes.size(); i++) {
        	Animacao aux;
        	aux = animacoes.get(i);
        	
        	// they can draw theirselves
        	aux.drawYourself(g);
        }
        
        // drawing the heroes
        for(int i=0; i<herois.size(); i++) {
        	Heroi aux;
        	aux = herois.get(i);
        	
        	// they also can draw theirselves
        	aux.drawYourself(g);
        	// and their icons
        	aux.drawYourIcon(g);
        }
        
        // testing the fonts
        g.fillRect(270, 170, 280, 50);
        FonteUtil.escreva("Cristhian", g, 275, 175, FonteUtil.WHITE);
        FonteUtil.escreva("Ola aventureiro, esta preparado?", g, 275, 190, FonteUtil.WHITE);
        FonteUtil.escreva("             ^", g, 350, 205, FonteUtil.WHITE);
	}
}
