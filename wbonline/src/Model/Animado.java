package Model;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Animado {
	
	// atributos
	protected String nome;
	protected int x, y;
	protected int width, height, esp;
	protected int num_lin, num_col;
	protected int lin, col;
	protected int speed, speed_atual;
	protected Image imagem;
	
	public Animado() {
		// default
		lin = 0;
		col = 0;
		speed_atual = 0;
		esp = 0;
	}
	
	public void drawYourself(Graphics2D g) {
		g.drawImage(imagem, x, y, x+width, y+height, 
				width*col + esp*(col+1), 
				height*lin + esp*(lin+1), 
				width*col + width + esp*(col+1), 
				height*lin + height + esp*(lin+1), 
				null);
	}
	
	// getters
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getEsp() { return esp; }
	public int getNumLin() { return num_lin; }
	public int getNumCol() { return num_col; }
	public int getLin() { return lin; }
	public int getCol() { return col; }
	public Image getImagem() { return imagem; }
	public String getNome() { return nome; }
		
	// setters
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public void setEsp(int esp) { this.esp = esp; }
	public void setNumLin(int num_lin) { this.num_lin = num_lin; }
	public void setNumCol(int num_col) { this.num_col = num_col; }
	public void setLin(int lin) { this.lin = lin; }
	public void setCol(int col) { this.col = col; }
	public void setSpeed(int speed) { this.speed = speed; }
	public void setImagem(Image imagem) { this.imagem = imagem; }
	public void setNome(String nome) { this.nome = nome; }
	
}
