package inf112.skeleton.app.board;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.board.ConveyorBelts.*;

import java.util.ArrayList;

public class Board implements IBoard {
	private int width;
	private int height;
	private TiledMap map;

	/**
	 * board constructor, with automatic width and height
	 *
	 * @param map - Map to create the board out of
	 */
	public Board(TiledMap map) {
		MapProperties properties = map.getProperties();
		this.width = properties.get("width", Integer.class);
		this.height = properties.get("height", Integer.class);
		this.map = map;
	}
	
	/**
	 * board constructor, manual width and height
	 *
	 * @param width - width
	 * @param height - height
	 * @param mapIn - Input map
	 */
	public Board(int width, int height, TiledMap mapIn) {
		this.width = width;
		this.height = height;
		map = mapIn;
	}
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public ArrayList<BoardElement> getWalls(int x, int y) {
		ArrayList<BoardElement> walls = new ArrayList<>();

		Direction dir = Direction.NORTH;
		for(int i = 0; i < 4; i++) {
			TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("walls"+dir.toString());
			TiledMapTileLayer.Cell cell = layer.getCell(x, y);
			if(cell != null) {
				String type = cell.getTile().getProperties().get("type").toString();
				if (type.equals("wall"))
					walls.add(BoardElement.WALLS.get(dir.getDirectionValue()));
			}
			dir = dir.next();
		}
		if(walls.isEmpty())
			return null;
		else
			return walls;
	}

	@Override
	public TiledMap getMap() {
		return map;
	}


	@Override
	public IConveyorBelt getConveyorBelt(int x, int y) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("boardElements");
        MapProperties properties = layer.getCell(x, y).getTile().getProperties();
        if(properties.get("conveyorType") == null)
        	throw new IllegalArgumentException("The given coordinates do not point to a conveyor belt");

		String conveyorType = properties.get("conveyorType").toString();
		Direction moveDirection = Direction.valueOf(properties.get("moveDirection").toString());
		int[] position = {x,y};
		int moveValue;
		if((boolean) properties.get("isExpress"))
			moveValue = 2;
		else
			moveValue = 1;


		IConveyorBelt conveyorBelt;
		switch(conveyorType) {
			case "straight": conveyorBelt = new ConveyorStraight(moveDirection, moveValue, position); break;
			case "turn":
				boolean turnDirection = (boolean) properties.get("rotationDirection");
				conveyorBelt = new ConveyorTurn(moveDirection, moveValue, position, turnDirection);
				break;
			case "into":
				turnDirection = (boolean) properties.get("rotationDirection");
				conveyorBelt = new ConveyorInto(moveDirection, moveValue, position, turnDirection);
				break;
			case "combine": conveyorBelt = new ConveyorCombine(moveDirection, moveValue, position); break;
			default: conveyorBelt = null;
		}
		return conveyorBelt;
	}

	@Override
	public ILaser getLaser(int x, int y) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("lasers");
        TiledMapTileLayer.Cell cell = layer.getCell(x,y);
        if (cell == null){
        	return null;
		}
        MapProperties properties = cell.getTile().getProperties();
        String type = properties.get("type").toString();
        String direction = properties.get("direction").toString();
        int value = (int) properties.get("value");
        return new Laser(value, direction, x, y);
	}

	@Override
	public BoardElement getBoardElement(int x, int y) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("boardElements");
		MapProperties properties = layer.getCell(x, y).getTile().getProperties();
		String type = properties.get("type").toString();

		BoardElement elementType = null;
		switch (type) {
			case "conveyorBelt": elementType = BoardElement.CONVEYORBELT; break;
			case "hole": elementType = BoardElement.HOLE; break;
			case "wrench":
				String name = properties.get("name").toString();
                	switch (name) {
                  		case "normal": elementType = BoardElement.WRENCH; break;
                   		case "hammer": elementType = BoardElement.SPECIAL_WRENCH; break;
                   		case "flag1": elementType = BoardElement.FLAG1; break;
                   		case "flag2": elementType = BoardElement.FLAG2; break;
                   		case "flag3": elementType = BoardElement.FLAG3; break;
                   		case "flag4": elementType = BoardElement.FLAG4; break;
                	}
				break;
			case "gear":
			    boolean rotationDirection = (boolean) properties.get("rotationDirection");
			    if(rotationDirection)
			        elementType = BoardElement.GEAR_RIGHT;
			    else
			        elementType = BoardElement.GEAR_LEFT;
			    break;
            case "pusher":
                Direction dir = Direction.valueOf(properties.get("direction").toString());
                boolean activatesOnEvenTurns = (boolean) properties.get("activatesOnEvenTurns");
                if(activatesOnEvenTurns)
                    elementType = BoardElement.PUSHERS_EVEN.get(dir.getDirectionValue());
                else
                    elementType = BoardElement.PUSHERS_ODD.get(dir.getDirectionValue());
                break;
			case "startingPoint":
				String startPoint = "STARTING_POSITION_" + properties.get("value").toString();
				elementType = BoardElement.valueOf(startPoint);
				break;
		}
		return elementType;
	}
}



