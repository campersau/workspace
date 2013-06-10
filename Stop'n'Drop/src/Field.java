

import core.Position;

public class Field {
	
	int x,y;
	/*
	 * G = Die Bewegungskosten, um vom Startpunkt A zu einem gegebenen Quadrat des Gitters unter Verwendung des daf�r 
	 * ermittelten Pfades zu gelangen.
	 * 
	 * H = Die gesch�tzten Kosten, um von dem gegebenen Quadrat zum Zielpunkt B zu gelangen.
	 */
	int G,H;
	//F = Gesamtkosten f�r den gefundenen Weg
	int F ;
	//bewegungskosten um �ber ein Feld zu laufen
	int movment;
	//Vorg�nger des Feldes
	Field prev = null;
	
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
		F = G + H;
		return this.F;
	}
	
	public void setF(int f) {
		this.F = f;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getG() {
		return G;
	}
	public void setG(int g) {
		G = g;
	}
	public int getH() {
		return H;
	}
	public void setH(int h) {
		H = h;
	}
	public int getMovment() {
		return movment;
	}
	public void setMovment(int movment) {
		this.movment = movment;
	}
	public Field getPrev() {
		return prev;
	}
	public void setPrev(Field prev) {
		this.prev = prev;
	}

}
