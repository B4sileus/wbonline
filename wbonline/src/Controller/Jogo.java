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
	
	private int x, dx;
	private Map<String, Animado> hash;
	private ArrayList<Animacao> animacoes;
	private ArrayList<Heroi> herois;
	
	public void onLoad() {
    	x = 150;
    	dx = 2;
    	
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
		
		for(int i=0; i<animacoes.size(); i++) {
			animacoes.get(i).anima();
		}
		
		for(int i=0; i<herois.size(); i++) {
			herois.get(i).anda();
		}
	}

	public void onRender(Graphics2D g) {
		
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.BLACK);
        g.fillOval(x, 10, 10, 10);
        
        // animacoes
        for(int i=0; i<animacoes.size(); i++) {
        	Animacao aux;
        	aux = animacoes.get(i);
        	
        	aux.drawYourself(g);
        }
        
        // herois
        for(int i=0; i<herois.size(); i++) {
        	Heroi aux;
        	aux = herois.get(i);
        	
        	aux.drawYourself(g);
        	aux.drawYourIcon(g);
        }
        
        // textos
        g.fillRect(270, 170, 280, 50);
        FonteUtil.escreva("Cristhian", g, 275, 175, FonteUtil.WHITE);
        FonteUtil.escreva("Ola aventureiro, esta preparado?", g, 275, 190, FonteUtil.WHITE);
        FonteUtil.escreva("             ^", g, 350, 205, FonteUtil.WHITE);
	}
}
