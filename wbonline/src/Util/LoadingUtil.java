package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import Controller.Game;
import Model.Animado;
import View.Animacao;
import View.Heroi;

public class LoadingUtil {
	
	// le o arquivo de animações e adiciona os objetos no hash
	public static void loadAnimado(Map<String, Animado> mapa, String end) {
		try {
			FileReader ent = new FileReader(end);
			BufferedReader buffered = new BufferedReader(ent);
			
			String in = "";
			String a[];
			Animado aux = null;
			
			while( (in = buffered.readLine()) != null ) {
				if(in.contentEquals("-")) {
					mapa.put(aux.getNome(), aux);
					continue;
				}
				
				a = in.split("=");
				
				if(a[1].contentEquals("new_animacao")) {
					aux = new Animacao();
					aux.setNome(a[0]);
				} else if(a[1].contentEquals("new_heroi")) {
					aux = new Heroi();
					aux.setNome(a[0]);
				} else {
					LoadingUtil.leiaAnimado(a[0], a[1], (Animado)aux);
				}
			}
			
			buffered.close();
			ent.close();
		} catch (Exception e) {
			System.out.println("Arquivo não encontrado em " + end);
		}
	}
	private static void leiaAnimado(String atr, String val, Animado aux) {		
		switch(atr) {
		case "end":
			aux.setImagem( (new ImageIcon(val)).getImage() );
			break;
		case "icon":
			((Heroi)aux).setIcon( (new ImageIcon(val)).getImage() );
			break;
		case "width":
			aux.setWidth( Integer.parseInt(val) );
			break;
		case "height":
			aux.setHeight( Integer.parseInt(val) );
			break;
		case "num_lin":
			aux.setNumLin(Integer.parseInt(val) );
			break;
		case "num_col":
			aux.setNumCol( Integer.parseInt(val) );
			break;
		case "speed":
			aux.setSpeed( Integer.parseInt(val) );
			break;
		case "esp":
			aux.setEsp( Integer.parseInt(val) );
			break;
		// caso seja um atributo de uma classe herdada (heroi ou animacao), fazer cast
		}
	}
	
	// le o arquivo de animações e instancia todos os objetos no cenário
	public static void addAnimacoes(Map<String, Animado> mapa, ArrayList<Animacao> lista, String end) {
		try {
			FileReader ent = new FileReader(end);
			BufferedReader buffered = new BufferedReader(ent);
			
			String in = "";
			String a[];
			Animacao aux = null;
			
			while( (in = buffered.readLine()) != null ) {
				if(in.contentEquals("-")) {
					lista.add( aux );
					aux = null;
					continue;
				}
				
				a = in.split("=");
				
				if(a[1].contentEquals("new_animacao")) {
					aux = ((Animacao)mapa.get( a[0] )).clone();
				} else {
					LoadingUtil.atribuaAnimado(a[0], a[1], aux);
				}
			}
			
			buffered.close();
			ent.close();
		} catch (Exception e) {
			System.out.println("Arquivo não encontrado em " + end);
		}
	}
	public static void addHerois(Map<String, Animado> mapa, ArrayList<Heroi> lista, String end) {
		try {
			FileReader ent = new FileReader(end);
			BufferedReader buffered = new BufferedReader(ent);
			
			String in = "";
			String a[];
			Heroi aux = null;
			
			while( (in = buffered.readLine()) != null ) {
				if(in.contentEquals("-")) {
					lista.add( aux );
					aux = null;
					continue;
				}
				
				a = in.split("=");
				
				if(a[1].contentEquals("new_heroi")) {
					aux = ((Heroi)mapa.get( a[0] )).clone();
				} else {
					LoadingUtil.atribuaAnimado(a[0], a[1], aux);
				}
			}
			
			buffered.close();
			ent.close();
		} catch (Exception e) {
			System.out.println("Arquivo não encontrado em " + end);
		}
	}
	private static void atribuaAnimado(String atr, String val, Animado aux) {
		Random random;
		
		switch(atr) {
		case "x":
			aux.setX( Integer.parseInt(val) );
			break;
		case "y":
			aux.setY( Integer.parseInt(val) );
			break;
		case "rand_x":
			random = new Random();
			aux.setX( random.nextInt( Game.WINDOW_WIDTH ) );
			break;
		case "rand_y":
			random = new Random();
			aux.setY( random.nextInt( Game.WINDOW_HEIGHT ) );
			break;
		// caso seja um atributo de uma classe herdada (heroi ou animacao), fazer cast
		}
	}
	
	
}
