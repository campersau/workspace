
import java.util.LinkedList;

import core.Position;
import core.ai.AiMapInfo;
import core.map.FieldType;
import core.map.SNDMapDebugDrawInfo;
import core.map.SNDMapGeneratorBuilder;


public class AStar {

	public AStar(){

	}

	LinkedList<Field> openList = new LinkedList<Field>();
	LinkedList<Field> closedList = new LinkedList<Field>();

	//giebt die n�chste zu gehende Position zur�ck
	public Position getnextPosition(AiMapInfo map, Position startposition, Position targetposition) {
		Field playerposition = new Field( startposition.x, startposition.y, null , 0, calcH(startposition,targetposition));

		//cancel if already on targetpoint
		if(samePosition(startposition, targetposition))return startposition;

		openList.add(playerposition);

		while (!isinclosedList(targetposition)||openList.isEmpty()) {
			findLowF();
			movableToOpen(new Position(closedList.getLast().getX(), closedList.getLast().getY()), targetposition, playerposition, map);
		}

		return new Position(trakeBacktoPosition());

	}

	private Position trakeBacktoPosition() {
		return fieldtoPosition(closedList.get(1));
	}

	//findet kleinste f in openlist und f�gt es in die colosedlist
	private void findLowF() {
		Field temp = openList.get(0);
		int pos = 0;

		for (int i = 1; i < openList.size(); i++) {
			if (openList.get(i).getF() < temp.getF()) {
				temp = openList.get(i);
				pos = i;
			}
		}
		closedList.add(temp);
		openList.remove(pos);
	}

	//�berpr�ft, ob die felder begehbar sind
	private void movableToOpen(Position actualposition,Position targetposition, Field start, AiMapInfo map) {
		//Lindkedlist of up, down, left, right Positions
		LinkedList<Position> movable = new LinkedList<Position>();
		movable.addAll(actualposition.getD4Neighbors());

		for (int i = 0; i < movable.size(); i++) {				//itterate thrue movable
			if (getFieldType(movable.get(i), map)== FieldType.STABLE_FIELD||			//check if field is movable
					getFieldType(movable.get(i), map)==FieldType.UNSTABLE_FIELD) {
				//check if g is lower if prev = movable i
				if (isinopenList(movable.get(i))) {
					changeprev(movable.get(i));
				} else if (!isinclosedList(movable.get(i))) {				//check if is in closedlist
					addPositionToOpenList(targetposition, actualposition, movable.get(i));
				}
			}
		}
	}

	private void changeprev(Position position) {
		if (openList.get(findPointInOpenList(position)).getG() < closedList.getLast().getG()+1) {
			openList.get(findPointInOpenList(position)).setPrev(closedList.getLast());
		}
	}

	private FieldType getFieldType(Position position, AiMapInfo map) {
		FieldType field = map.getField(position);
		return field;
	}

	//f�gt begehbare felder in die openlist ein
	private void addPositionToOpenList(Position targetposition, Position prev, Position movable) {
		openList.add(new Field(movable.x, movable.y, closedList.get(findPointInClosedList(prev)),closedList.get(findPointInClosedList(prev)).getG(),calcH(movable, targetposition) ));
	}

	private int findPointInClosedList(Position position) {
		for (int i = 0; i < closedList.size(); i++) {
			if (position.x == closedList.get(i).getX()&& position.y == closedList.get(i).getY()) {
				return i;
			}
		}
		return -1;
	}

	private int findPointInOpenList(Position position) {
		for (int i = 0; i < openList.size(); i++) {
			if (position.x == openList.get(i).getX()&& position.y == openList.get(i).getY()) {
				return i;
			}
		}
		return -1;
	}

	//�berpr�ft ob feld bereits in closedlist ist
	private boolean isinclosedList(Position position) {
		for (int i = 0; i < closedList.size(); i++) {
			if (position.x == closedList.get(i).getX() && position.y == closedList.get(i).getY()) {
				return true;
			}
		}
		return false;
	}

	//�berpr�ft ob feld bereits in opnelist ist
	private boolean isinopenList(Position position) {
		for (int i = 0; i < openList.size(); i++) {
			if (position == new Position(openList.get(i).getX(), openList.get(i).getY())) {
				return true;
			}
		}
		return false;
	}

	public Position fieldtoPosition(Field field) {
		Position erg = new Position(field.getX(), field.getY());
		return erg;
	}

	public int calcH(Position actual, Position target) {
		int x = Math.abs(actual.x - target.x);
		int y = Math.abs(actual.y - target.y);
		int erg = x+y;
		return erg;
	}


	public boolean samePosition(Position positiona, Position positionb) {
		if (positiona.x == positionb.x & positiona.y == positionb.y) {
			return true;
		}
		return false;
	}

    // my test function
	public static void main(String[] args) {
		SNDMapGeneratorBuilder builder = new SNDMapGeneratorBuilder();
		builder.setSizeX(100);
		builder.setSizeY(100);
		builder.setNumberOfHoles(50);
		builder.setCreateRandomHoles(true);
		builder.setNumberOfObstacles(20);

		AiMapInfo map = new AiMapInfo(builder.buildMapGenerator().generateMap(), new SNDMapDebugDrawInfo(100, 100), "EasyGame");
		Position next = new Position(5, 5);
		Position end = new Position(50, 50);

		while ((next = new AStar().getnextPosition(map, next, end)) != null) {
			System.out.println(next.toString());
		}

	}

}
