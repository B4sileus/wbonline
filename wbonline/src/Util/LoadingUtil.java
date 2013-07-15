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
	
	// reads the file about animations and add them on the map
	public static void loadAnimado(Map<String, Animado> mapa, String end) {
		try {
			// finding
			FileReader ent = new FileReader(end);
			BufferedReader buffered = new BufferedReader(ent);
			
			// reading
			String in = "";
			String a[];
			Animado aux = null;
			
			while( (in = buffered.readLine()) != null ) {
				if(in.contentEquals("-")) { // separates one from another
					mapa.put(aux.getNome(), aux); // puts in the map
					continue; // start the other
				} else if(in.contentEquals("EOF")) { // end of file
					break;
				}
				
				// splitting
				a = in.split("=");
				
				if(a[1].contentEquals("new_animacao")) { // new one
					aux = new Animacao();
					aux.setNome(a[0]); // sets its name, so I can find it later on the map
				} else if(a[1].contentEquals("new_heroi")) { // new one
					aux = new Heroi();
					aux.setNome(a[0]); // also sets its name
				} else {
					// set the attributes
					LoadingUtil.leiaAnimado(a[0], a[1], (Animado)aux);
				}
			}
			
			// end the job
			buffered.close();
			ent.close();
		} catch (Exception e) {
			// ops...
			System.out.println("Arquivo não encontrado em " + end);
		}
	}
	// this read and set the attributes
	private static void leiaAnimado(String atr, String val, Animado aux) {		
		switch(atr) {
		case "end": // sprite address
			aux.setImagem( (new ImageIcon(val)).getImage() );
			break;
		case "icon": // icon address
			((Heroi)aux).setIcon( (new ImageIcon(val)).getImage() );
			break;
		case "width": // width
			aux.setWidth( Integer.parseInt(val) );
			break;
		case "height": // height
			aux.setHeight( Integer.parseInt(val) );
			break;
		case "num_lin": // number of rows
			aux.setNumLin(Integer.parseInt(val) );
			break;
		case "num_col": // number of columns
			aux.setNumCol( Integer.parseInt(val) );
			break;
		case "speed": // speed of the frames
			aux.setSpeed( Integer.parseInt(val) );
			break;
		case "esp": // space between the draws
			aux.setEsp( Integer.parseInt(val) );
			break;
		// in the case it is an attribute of a child class, do a cast
		}
	}
	
	// loads the file containing the map info, and instanciate them on the scenario
	public static void addAnimacoes(Map<String, Animado> mapa, ArrayList<Animacao> lista, String end) {
		try {
			// finding
			FileReader ent = new FileReader(end);
			BufferedReader buffered = new BufferedReader(ent);
			
			// reading
			String in = "";
			String a[];
			Animacao aux = null;
			
			while( (in = buffered.readLine()) != null ) {
				if(in.contentEquals("-")) { // end this one
					lista.add( aux ); // add this one
					aux = null; // new one
					continue;
				} else if(in.contentEquals("EOF")) { // end of file
					break;
				}
				
				// splitting
				a = in.split("=");
				
				if(a[1].contentEquals("new_animacao")) { // new one
					// clones the existing object from the map, so it can set
					// some attributes, and add to the game.
					// important: it doesn't clone the reference to the image,
					// so be careful when modifying it.
					aux = ((Animacao)mapa.get( a[0] )).clone();
				} else {
					// set some attributes
					LoadingUtil.atribuaAnimado(a[0], a[1], aux);
				}
			}
			
			// end of the job
			buffered.close();
			ent.close();
		} catch (Exception e) {
			// ops...
			System.out.println("Arquivo não encontrado em " + end);
		}
	}
	public static void addHerois(Map<String, Animado> mapa, ArrayList<Heroi> lista, String end) {
		try {
			// finding
			FileReader ent = new FileReader(end);
			BufferedReader buffered = new BufferedReader(ent);
			
			// reading
			String in = "";
			String a[];
			Heroi aux = null;
			
			while( (in = buffered.readLine()) != null ) {
				if(in.contentEquals("-")) { // end of this one
					lista.add( aux ); // add this one
					aux = null; // new one
					continue;
				} else if(in.contentEquals("EOF")) { // end of file
					break;
				}
				
				// splitting
				a = in.split("=");
				
				if(a[1].contentEquals("new_heroi")) { // new one
					aux = ((Heroi)mapa.get( a[0] )).clone();
				} else {
					// setting attributes
					LoadingUtil.atribuaAnimado(a[0], a[1], aux);
				}
			}
			
			// end of job
			buffered.close();
			ent.close();
		} catch (Exception e) {
			// ops...
			System.out.println("Arquivo não encontrado em " + end);
		}
	}
	private static void atribuaAnimado(String atr, String val, Animado aux) {
		Random random; // random value
		
		switch(atr) {
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
		// in the case it is an attribute from a child class, do a cast
		}
	}
	
	
}
