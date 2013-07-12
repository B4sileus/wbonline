package Util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public abstract class FonteUtil {
	
	private static int ESP = 2, ESPACO = 1;
	public static int WHITE = 1, YELLOW = 2;
	private static Image imagem;
	private static Map<Integer, Letra> mapa_w; //, mapa_y;
	
	private static class Letra {
		private int x, y;
		private int alt, lar;
		
		private Letra(int lar, int alt, int x, int y) {
			this.lar = lar;
			this.alt = alt;
			this.x = x;
			this.y = y;
		}
		
		public int getX() { return x; }
		public int getY() { return y; }
		public int getLar() { return lar; }
		public int getAlt() { return alt; }
	}
	
	public static void loadFonte(String end) {
		int lar_default = 9, alt_default = 10;
		int acumulo_x = 0, y = 0;
		int lar;
		
		try {
			FileReader ent = new FileReader(end);
			BufferedReader buffered = new BufferedReader(ent);
			
			String in = "";
			String a[];
			
			
			
			while( (in = buffered.readLine()) != null ) {
				
				a = in.split("=");
				
				if(a[1].contentEquals("new")) {
					
					if(a[2].contentEquals("default")) {
						addLetra(a[0], lar_default, alt_default, acumulo_x, y);
						
						acumulo_x += lar_default + ESP;
					} else {
						lar = Integer.parseInt( a[2] );
						addLetra(a[0], lar, alt_default, acumulo_x, y);
						
						acumulo_x += lar + ESP;
					}
					
				} else if(a[0].contentEquals("x")) {
					acumulo_x = Integer.parseInt( a[1] );
				} else if(a[0].contentEquals("y")) {
					y = Integer.parseInt( a[1] );
				} else if(a[0].contentEquals("lar")) {
					lar_default = Integer.parseInt( a[1] );
				} else if(a[0].contentEquals("alt")) {
					alt_default = Integer.parseInt( a[1] );
				} else if(a[0].contentEquals("end")) {
					imagem = new ImageIcon(a[1]).getImage();
				}
			}
			
			buffered.close();
			ent.close();
		} catch (Exception e) {
			System.out.println("Arquivo não encontrado em " + end);
		}
	}
	public static void addLetra(String letra, int lar, int alt, int x, int y) {
		if(mapa_w == null)
			mapa_w = new HashMap<Integer, Letra>();

		mapa_w.put( (int)letra.charAt(0), new Letra(lar, alt, x, y));
	}
	
	public static void escreva(String texto, Graphics2D g, int dx, int dy, int cor) {
		Letra letra;
		int acumulo_x = 0;
		
		HashMap<Integer, Letra> aux = null;
		if(cor == FonteUtil.WHITE)
			aux = (HashMap<Integer, Letra>) mapa_w;
		else if(cor == FonteUtil.YELLOW)
			aux = (HashMap<Integer, Letra>) mapa_w; // alterar
		
		for(int i=0; i<texto.length(); i++) {
			letra = aux.get( (int)texto.charAt(i) );
			
			g.drawImage(imagem, dx+acumulo_x, dy, dx+letra.getLar()+acumulo_x, dy+letra.getAlt(), 
					letra.getX(), letra.getY(), letra.getX()+letra.getLar(), 
					letra.getY()+letra.getAlt(), null);
			
			acumulo_x += letra.getLar() + ESPACO;
		}
	}
	
}
