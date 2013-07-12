package View;

import Model.Animado;

public class Animacao extends Animado implements Cloneable {
	
	public Animacao() {
		
	}
	
	public void anima() {
		if(++speed_atual == speed) {
			if(++col == num_col)
				col = 0;
			
			speed_atual = 0;
		}
	}
	
	@Override
	public Animacao clone() throws CloneNotSupportedException {
		Animacao clone = (Animacao) super.clone();
		
		return clone;
	}
	
}
