
import java.io.ObjectInputStream.GetField;
import java.util.LinkedList;

import client.gui.elements.AiSelector;


import core.Position;
import core.ai.AiMapInfo;
import core.ai.AiPlayerInfo;
import core.map.FieldType;

public class AStar {

	public AStar(){

	}

	LinkedList<Field> openList = new LinkedList<Field>();
	LinkedList<Field> closedList = new LinkedList<Field>();

	//giebt die n�chste zu gehende Position zur�ck
	public Position getnextPosition(AiMapInfo map, AiPlayerInfo player, Position startposition, Position targetposition) {
		Field start = new Field( startposition.x, startposition.y);
		Field target = new Field(targetposition.x, targetposition.y);

		//cancel if already on targetpoint
		if(target.equals(start))return startposition;

		isfirst(start);

		while (!isinclosedList(targetposition)) {
			findLowF();
			Position actualposition = new Position(closedList.getLast().x, closedList.getLast().y);
			movabletoopen(player, actualposition, targetposition, start, map);
		}

		Position erg = new Position(closedList.get(1).x,closedList.get(1).y);
		return erg;

	}

	//findet kleinste f in openlist und f�gt es in die colosedlist
	private void findLowF() {
		Field temp = openList.get(0);
		int pos = 0;

		for (int i = 1; i < openList.size(); i++) {
			if (openList.get(i).getF()<temp.getF()) {
				temp = openList.get(i);
				pos = i;
			}	
		}
		closedList.add(temp);
		openList.remove(pos);
	}

	//wenn openlist lehr ist, f�ge ein
	private void isfirst(Field start) {
		if (openList.isEmpty()) {
			start.setG(0);
			openList.add(start);
		}
	}

	//�berpr�ft, ob die felder begehbar sind
	private void movabletoopen(AiPlayerInfo player, Position startposition,
			Position targetposition, Field start, AiMapInfo map) {
		//Lindkedlist of up, down, left, right Positions
		LinkedList<Position> movable = new LinkedList<Position>();
		movable.addAll(startposition.getD4Neighbors());

		for (int i = 0; i < movable.size(); i++) {				//itterate thrue movable
			if (getFieldType(movable.get(i), map)== FieldType.STABLE_FIELD||			//check if field is movable
					getFieldType(movable.get(i), map)==FieldType.UNSTABLE_FIELD) {
				if (!isinclosedList(movable.get(i))) {				//check if is in closedlist
					addPositiontoopenList(player, targetposition, start, movable, i);
				}
			}
		}
	}

	private FieldType getFieldType(Position position, AiMapInfo map) {
		FieldType field = map.getField(position);
		return field;
	}

	//f�gt begehbare felder in die openlist ein
	private void addPositiontoopenList(AiPlayerInfo player,Position targetposition, Field start, LinkedList<Position> movable,int i) {
		openList.add(new Field(movable.get(i).x, movable.get(i).y,start,
				calcG(),calcH(movable.get(i),targetposition)));
	}

	//�berpr�ft ob feld bereits in opnelist ist
	private boolean isinopenList(Position position) {
		for (int i = 0; i < openList.size(); i++) {
			if (position == new Position(openList.get(i).x, openList.get(i).y)) {
				return true;
			}
		}
		return false;
	}

	//�berpr�ft ob feld bereits in closedlist ist
	private boolean isinclosedList(Position position) {

		for (int i = 0; i < closedList.size(); i++) {

			if (position.x == closedList.get(i).x && position.y == closedList.get(i).y) {
				return true;

			}

		}	
		return false;
	}

	public Position fieldtoPosition(Field field) {
		Position erg = new Position(field.x, field.y);
		return erg;
	}

	public int calcH(Position actual, Position target) {
		int x = Math.abs(actual.x - target.x);
		int y = Math.abs(actual.y - target.y);
		int erg = x+y;
		return erg;
	}

	public int calcG() {
		int closemin = closedList.size()-1;
		return closedList.get(closemin).G +1;
	}

}
