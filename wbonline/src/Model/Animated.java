package Model;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Animated {
	
	// attributes
	protected String name;
	protected int x, y;
	protected int width, height, space;
	protected int num_row, num_col;
	protected int row, col;
	protected int speed, speed_actual;
	protected Image image;
	
	public Animated() {
		// default
		row = 0;
		col = 0;
		speed_actual = 0;
		space = 0;
	}
	
	// it draws itself
	public void drawYourself(Graphics2D g) {
		g.drawImage(image, x, y, x+width, y+height, 
				width*col + space*(col+1), 
				height*row + space*(row+1), 
				width*col + width + space*(col+1), 
				height*row + height + space*(row+1), 
				null);
	}
	
	// getters
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getSpace() { return space; }
	public int getNumRow() { return num_row; }
	public int getNumCol() { return num_col; }
	public int getRow() { return row; }
	public int getCol() { return col; }
	public Image getImage() { return image; }
	public String getName() { return name; }
		
	// setters
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public void setSpace(int space) { this.space = space; }
	public void setNumRow(int num_row) { this.num_row = num_row; }
	public void setNumCol(int num_col) { this.num_col = num_col; }
	public void setRow(int row) { this.row = row; }
	public void setCol(int col) { this.col = col; }
	public void setSpeed(int speed) { this.speed = speed; }
	public void setImage(Image image) { this.image = image; }
	public void setName(String name) { this.name = name; }
	
}
