package View;

import java.awt.Graphics2D;
import java.awt.Image;

import Model.Animado;

public class Heroi extends Animado implements Cloneable {
	
	//private int slot;
	private int coins;
	private Image image_icon;
	
	public Heroi() {
		// inicializações
		col = 1;
		coins = 0;
		//slot = 0;
	}
	
	public void anda() {
		if(++speed_atual == speed) {
			if(++col == num_col)
				col = 1;
			
			speed_atual = 0;
		}
	}
	
	@Override
	public Heroi clone() throws CloneNotSupportedException {
		Heroi clone = (Heroi) super.clone();
		
		// nota: a imagem não está sendo clonada..
		// a mesma referência da imagem está sendo passada
		// para todas as instâncias, portanto cuidado ao 
		// manipular essa imagem
		
		return clone;
	}
	
	public void drawYourIcon(Graphics2D g) {
		if(image_icon != null)
			g.drawImage(image_icon, 20, 20, 72, 84, 0, 0, 52, 64, null);
		else
			System.out.println("missing image_icon");
	}
	
	// getters
	public int getCoins() { return coins; }
	
	// setters
	public void setCoins(int coins) { this.coins = coins; }
	public void addCoins(int coins) { this.coins += coins; }
	//public void setSlot(int slot) { this.slot = slot; }
	public void setIcon(Image image_icon) { this.image_icon = image_icon; }
	
}
