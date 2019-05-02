package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.IBoard;
import inf112.skeleton.app.graphics.GFX;
import org.junit.Test;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class BoardTest {


	private TiledMap map = new TmxMapLoader().load("assets/risky_exchange.tmx");
	private TiledMapRenderer tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

	public void fixNullPointerException () {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Board";
		cfg.width = 1520;
		cfg.height = 960;

		new LwjglApplication(new GFX(), cfg).exit();
	}
	
	@Test
	public void testCheckSquare () {

		fixNullPointerException();

		IBoard board = new Board(map);

		int xCoordinate = 0;
		int yCoordinate = 0;


	}

	@Test
	public void widthTest (){

		fixNullPointerException();

		IBoard board = new Board(map);

		int width=60;

		int widthFromMethod = board.getWidth();

		assertEquals(width,widthFromMethod);
	}

	@Test
	public void heightTest (){

		fixNullPointerException();

		IBoard board = new Board(map);

		int height = 30;

		int heightFromMethod = board.getHeight();

	}

	@Test
	public void testGetMap () {

		fixNullPointerException();

		IBoard board = new Board(map);

		TiledMap mapFromMethod = board.getMap();

		assertEquals(map,mapFromMethod);

	}


	
}

	