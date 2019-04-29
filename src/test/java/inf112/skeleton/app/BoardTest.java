package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;

		import com.badlogic.gdx.maps.tiled.TiledMap;
		import inf112.skeleton.app.board.Board;
		import inf112.skeleton.app.board.IBoard;
		import org.junit.Test;

public class BoardTest {
	
	@Test
	public void testCheckSquare () {
		TiledMap map = new TiledMap();
		IBoard board = new Board(map);

		int xCoordinate = 0;
		int yCoordinate = 0;


	}

	@Test
	public void widthTest (){
		TiledMap map = new TiledMap();
		IBoard board = new Board(map);

		int width=60;

		int widthFromMethod = board.getWidth();

		assertEquals(width,widthFromMethod);
	}

	@Test
	public void heightTest (){

		TiledMap map = new TiledMap();
		IBoard board = new Board(map);

		int height = 30;

		int heightFromMethod = board.getHeight();

	}

	@Test
	public void testGetMap () {
		TiledMap map = new TiledMap();
		IBoard board = new Board(map);

		TiledMap mapFromMethod = board.getMap();

		assertEquals(map,mapFromMethod);

	}


	
}

	