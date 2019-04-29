package inf112.skeleton.app.graphics;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Menu extends Stage {
    private Sprite[] increase;
    private Sprite[] decrease;
    private Sprite start;
    private Texture textureIncrease;
    private Texture textureDecrease;
    private Texture textureStart;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private Texture menuBackground;
    private Sprite spriteMenuBackground;
    private BitmapFont font;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private SpriteBatch batch;
    private int numberOfRealPlayers;
    private int numberOfAI;
    private int mapNumber;
    private boolean menuActive;
    private int currentPosition;


    public Menu(){
        numberOfRealPlayers = 0;
        numberOfAI = 0;
        mapNumber = 0;
        font = new BitmapFont();
        currentPosition = 0;
        menuActive = true;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        tiledMap = new TmxMapLoader().load("assets/map1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        batch = new SpriteBatch();
        initialiseSprites();
    }
    private void initialiseSprites(){
        menuActive = true;
        menuBackground = new Texture(Gdx.files.internal("assets/menuBack.png"));
        spriteMenuBackground = new Sprite(menuBackground);
        spriteMenuBackground.setPosition(65,100);
        increase = new Sprite[3];
        decrease = new Sprite[3];
        textureIncrease = new Texture(Gdx.files.internal("assets/increase.png"));
        textureDecrease = new Texture(Gdx.files.internal("assets/decrease.png"));
        resetButtons();
        renderButtons();
    }
    private void resetButtons(){
        for(int i = 0; i < 3; i++){
            increase[i] = new Sprite(textureIncrease);
            decrease[i] = new Sprite(textureDecrease);
            increase[i].setPosition(700,700-i*170);
            decrease[i].setPosition(700,620-i*170);
        }
        textureStart = new Texture(Gdx.files.internal("assets/start.png"));
        start = new Sprite(textureStart);
        start.setPosition(700,170);
    }
    private void renderButtons(){
        switch (currentPosition) {
            case (0):
                increase[0].setPosition(increase[0].getX()+20, increase[0].getY());
                break;
            case (1):
                System.out.println("test");
                decrease[0].setPosition(decrease[0].getX()+20, decrease[0].getY());
                break;
            case (2):
                increase[1].setPosition(increase[1].getX()+20, increase[1].getY());
                break;
            case (3):
                decrease[1].setPosition(decrease[1].getX()+20, decrease[1].getY());
                break;
            case (4):
                increase[2].setPosition(increase[2].getX()+20, increase[2].getY());
                break;
            case (5):
                decrease[2].setPosition(decrease[2].getX()+20, decrease[2].getY());
                break;
            case (6):
                start.setPosition(start.getX()+20, start.getY());
                break;
        }
    }
    private void initialiseText(){
        font.draw(batch, ("Number of Real Players:".concat(Integer.toString(getNumberOfRealPlayers()))), 900, 700);
        font.draw(batch, ("Number of AI:".concat(Integer.toString(getNumbersOfAI()))),900, 550);
        font.draw(batch, ("Map number:".concat((Integer.toString(mapNumber)))), 900, 370);
    }
    private void renderText(){
        initialiseText();
    }
    public void render(){
        camera.update();
        tiledMapRenderer.setView(camera);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        spriteMenuBackground.draw(batch);
        for(int i = 0; i < 3; i++){
            decrease[i].draw(batch);
            increase[i].draw(batch);
        }
        start.draw(batch);
        initialiseText();
        batch.end();
    }
    public int getNumberOfRealPlayers(){
        return numberOfRealPlayers;
    }
    public int getNumbersOfAI(){
        return numberOfAI;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public Boolean isMenuActive(){
        return menuActive;
    }
    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            if(menuActive) {
                if (currentPosition > 0) {
                    currentPosition--;
                }
                else currentPosition = 5;
            }
            resetButtons();
            renderButtons();
        }
        if(keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            if(menuActive) {
                if (currentPosition < 6) {
                    currentPosition++;
                }
                else
                    currentPosition = 0;
            }
            resetButtons();
            renderButtons();
        }
        if(keycode == Input.Keys.ENTER) {
            if (menuActive){
                switch (currentPosition) {
                    case (0):
                        numberOfRealPlayers++;
                        break;
                    case (1):
                        numberOfRealPlayers--;
                        break;
                    case (2):
                        numberOfAI++;
                        break;
                    case (3):
                        numberOfAI--;
                        break;
                    case (4):
                        mapNumber++;
                        break;
                    case (5):
                        mapNumber--;
                        break;
                    case (6):
                        menuActive = false;
                        break;
                }
            }
        }
        return false;
    }
}
