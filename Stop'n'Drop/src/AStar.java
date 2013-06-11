import java.util.LinkedList;

import core.Position;
import core.ai.AiMapInfo;
import core.ai.AiPlayerInfo;


public class AStar {

	LinkedList<Field> openList = new LinkedList<Field>();
	LinkedList<Field> closedList = new LinkedList<Field>();

	AiMapInfo map;
	AiPlayerInfo playerinf;
	Position target;

	public AStar (AiMapInfo map, AiPlayerInfo playerinf, Position target) {
		this.map = map;
		this.playerinf = playerinf;
		this.target = target;
	}

	public Position getNextPosition() {

		Field start = new Field(playerinf.getPosition(), target, map.getField(playerinf.getPosition()));
		openList.add(start);

		while (!openList.isEmpty()&& !isInClosedList(target)) {
			Field lowF = findLowestF();
			openList.remove(lowF);
			if(!isInClosedList(lowF)) {closedList.add(lowF);}
			addSurrounding(lowF);

		}
		Field erg = trackBackToPosition();
		return erg.fieldtoPosition();
	}

	private Field trackBackToPosition() {
		Field last = closedList.getLast();
		while (last.getPrev().getPrev() != null){
			last = last.getPrev();
		}
		return last;
	}

	private void addSurrounding(Field lowF) {

		Position current = new Position(lowF.getX(), lowF.getY());

		Position leftOf = new Position(current.x-1, current.y);
		Field leftOfField = new Field(leftOf, target, playerinf.getPosition(), map.getField(leftOf), lowF);
		if(leftOfField.iswalkable() && !isInClosedList(leftOfField) && !isInOpenList(lowF)) openList.add(leftOfField);

		Position rightOf = new Position(current.x+1, current.y);
		Field rightOfField = new Field(rightOf, target, playerinf.getPosition(), map.getField(rightOf), lowF);
		if(rightOfField.iswalkable() && !isInClosedList(rightOfField) && !isInOpenList(lowF)) openList.add(rightOfField);

		Position aboveOf = new Position(current.x, current.y-1);
		Field aboveOfField = new Field(aboveOf, target, playerinf.getPosition(), map.getField(aboveOf), lowF);
		if(aboveOfField.iswalkable() && !isInClosedList(aboveOfField) && !isInOpenList(lowF)) openList.add(aboveOfField);

		Position belowOf = new Position(current.x, current.y+1);
		Field belowOfField = new Field(belowOf, target, playerinf.getPosition(), map.getField(belowOf), lowF);
		if(belowOfField.iswalkable() && !isInClosedList(belowOfField) && !isInOpenList(lowF)) openList.add(belowOfField);
	}
	private boolean isInOpenList(Field current) {
		for (int i = 0; i < openList.size(); i++) {
			if (sameField(current, openList.get(i))) {
				 return true;
			}
		}
		return false;
	}

	private Field findLowestF() {
		Field temp = openList.getFirst();
		for (int i = 1; i < openList.size(); i++) {
			if (temp.getF() > openList.get(i).getF()) {
				temp = openList.get(i);
			}
		}
		return temp;
	}

	private boolean sameField(Field a,Position b) {
		if (a.getX() == b.x && a.getY()== b.y) {
			return true;
		}
		return false;
	}

	private boolean sameField(Field lowF, Field field) {
		if (lowF.getX() == field.getX()&& lowF.getY()== field.getY()) {
			return true;
		}
		return false;
	}

	private boolean isInClosedList(Position target) {
		for (int i = 0; i < closedList.size(); i++) {
			if (sameField(closedList.get(i), target)) {
				return true;
			}
		}
		return false;
	}

	private boolean isInClosedList(Field lowF) {
		for (int i = 0; i < closedList.size(); i++) {
			if (sameField(lowF, closedList.get(i))) {
				 return true;
			}
		}
		return false;
	}

}