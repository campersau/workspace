

import core.Position;
import core.ai.AiMapInfo;
import core.ai.AiPlayerInfo;
import core.map.FieldType;
import core.player.Player.Action;
import core.player.PlayerController;

public class zweiterVersuch extends PlayerController{

	@Override
	public String getName() {
		return "dummer Bot";
	}

	@Override
	public String getAuthor() {
		return "Fabi";
	}

	@Override
	public Action think(AiMapInfo map, AiPlayerInfo[] enemies,
			AiPlayerInfo ownPlayer) {

		if (map.isParkourMap()) {

			AStar sternchen = new AStar();

			Position next = sternchen.getnextPosition(map, ownPlayer, ownPlayer.getPosition(), getFlag(map));;

			if (next == (ownPlayer.getPosition().right())) return Action.MOVE_RIGHT;
			if (next == (ownPlayer.getPosition().left())) return Action.MOVE_LEFT;
			if (next ==(ownPlayer.getPosition().down())) return Action.MOVE_DOWN;
			if (next ==(ownPlayer.getPosition().up())) return Action.MOVE_UP;

		}

		return null;

	}

	private Position getFlag(AiMapInfo map) {
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				if (map.getField(new Position(j,i)).equals(FieldType.ACTION_FIELD_FLAG)) {
					Position flag = new Position(j,i);
					return flag;
				}
			}
		}
		return null;
	}

}
