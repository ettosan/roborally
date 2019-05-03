package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.IBoard;
import inf112.skeleton.app.board.ILaser;
import inf112.skeleton.app.board.Laser;
import inf112.skeleton.app.graphics.GFX;
import inf112.skeleton.app.graphics.Menu;
import org.junit.Before;
import org.junit.Test;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.io.File;
import java.util.ArrayList;

public class BoardTest {




	@Test
	public void widthTest (){

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Board";
		cfg.width = 1520;
		cfg.height = 960;


		LwjglApplication helper = new LwjglApplication(new GFX(),cfg);

		TiledMap map = new TmxMapLoader().load("assets/map/test/testMap.tmx");

		helper.exit();

		IBoard board = new Board(map);

		int width=100;

		int widthFromMethod = board.getWidth();

		assertEquals(width,widthFromMethod);
	}

	@Test
	public void heightTest (){

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Board";
		cfg.width = 1520;
		cfg.height = 960;


		LwjglApplication helper = new LwjglApplication(new GFX(),cfg);

		TiledMap map = new TmxMapLoader().load("assets/map/test/testMap.tmx");

		helper.exit();

		IBoard board = new Board(map);

		int height = 80;

		int heightFromMethod = board.getHeight();

		assertEquals(heightFromMethod,height);

	}

	@Test
	public void testGetMap () {


		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Board";
		cfg.width = 1520;
		cfg.height = 960;


		LwjglApplication helper = new LwjglApplication(new GFX(),cfg);

		TiledMap map = new TmxMapLoader().load("assets/map/test/testMap.tmx");

		helper.exit();

		IBoard board = new Board(map);


		assertEquals(board.getMap(),map);


	}

}

	