

import core.Position;

public class Field {
	
	private int x,y;
	/*
	 * G = Die Bewegungskosten, um vom Startpunkt A zu einem gegebenen Quadrat des Gitters unter Verwendung des dafür 
	 * ermittelten Pfades zu gelangen.
	 * 
	 * H = Die geschätzten Kosten, um von dem gegebenen Quadrat zum Zielpunkt B zu gelangen.
	 */
	private int G,H;
	//bewegungskosten um über ein Feld zu laufen
	private int movment;
	//Vorgänger des Feldes
	private Field prev = null;
	
	public Field(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Field(int x, int y, Field prev, int g, int h) {
		this.x = x;
		this.y = y;
		this.prev = prev;
		this.G = g;
		this.H = h;
	}


	public Position fieldtoPosition(Field field) {
		Position erg = new Position(field.x, field.y);
		return erg;
	}
	
	public String toString() {
		return "[" +this.x +", " +this.y+"]";
	}
	
	public int getF() {
		return getG()+ getH();
	}
	
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getG() {
		if (this.prev == null) {
			return 1;
		}
		return this.prev.G +1;
	}
	public void setG(int g) {
		G = g;
	}
	public int getH() {
		return this.H;
	}
	public void setH(int h) {
		H = h;
	}
	public int getMovment() {
		return this.movment;
	}
	public void setMovment(int movment) {
		this.movment = movment;
	}
	public Field getPrev() {
		return this.prev;
	}
	public void setPrev(Field prev) {
		this.prev = prev;
	}

}
