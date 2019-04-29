package inf112.skeleton.app.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.card.ICard;
import inf112.skeleton.app.card.ICardMovement;
import inf112.skeleton.app.card.ICardRotation;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.game.GameRuleConstants;
import inf112.skeleton.app.game.GameState;
import inf112.skeleton.app.robot.IRobot;

import java.util.ArrayList;

import static java.lang.Math.abs;

@SuppressWarnings("Since15")
public class GameGFX extends Stage {
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private ProgramRegisterGFX programRegisterGFX;

    private SpriteBatch batch;
    private Texture texture;
    private Texture textureP;
    private Texture cardBack;
    private Texture cardFront;

    private Sprite sprite;
    private Sprite spriteCardBack;
    private Sprite spriteCardFront;
    private Sprite[] cards;

    private int tilePixelWidth;
    private int tilePixelHeight;

    //Stores all of the robots values, TODO: initialise in create based on number of players
    private int[][] robotPositions = new int[1][3];

    //Used for testing, should not be pushed
    private boolean showCards = false;
    private BitmapFont font;
    private int cardId = 0;
    private Game game;
    private Menu menu;

    private int numberOfRealPlayers;
    private int numberOfAI;


    public void create (int numPlayersIn, int numAIIn, TiledMap tiledMapIn) {
        numberOfRealPlayers = numPlayersIn;
        numberOfAI = numAIIn;
        tiledMap = tiledMapIn;
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        menu = new Menu();
        //opens a Menu and gets the tiledmap from the menu class.
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(menu);

        MapProperties properties = tiledMap.getProperties();
        tilePixelWidth = properties.get("tilewidth", Integer.class);
        tilePixelHeight = properties.get("tileheight", Integer.class);

        createGame();
        initialiseSprites();
    }

    private void initialiseSprites() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("assets/bot-g.gif"));
        sprite = new Sprite(texture);
        sprite.setSize(tilePixelWidth - 10, tilePixelHeight - 10);
        sprite.setOrigin(35,35);

        programRegisterGFX = new ProgramRegisterGFX();

        cardBack = new Texture(Gdx.files.internal("assets/card_back.png"));
        cardFront = new Texture(Gdx.files.internal("assets/card_front.png"));
        spriteCardBack = new Sprite(cardBack);
        spriteCardFront = new Sprite(cardFront);
        cards = new Sprite[5];
        for(int i = 0; i < 5; i++){
            cards[i] = new Sprite(cardBack);
            cards[i].setPosition(970+(i*110), 680);
        }
        spriteCardBack = new Sprite(cardBack);
        spriteCardFront = new Sprite(cardFront);
    }

    private void createGame() {
        game = new Game(tiledMap, numberOfRealPlayers);
        //TODO: should be dynamically assigned
        robotPositions[0][0] = game.getCurrentRegister().getRobot().getPosition()[0] * tilePixelWidth;
        robotPositions[0][1] = game.getCurrentRegister().getRobot().getPosition()[1] * tilePixelHeight;
        robotPositions[0][2] = 0; //Rotation value
    }

    //Used to render the robot
    private void calculateRobotPosition(int robotId) {
        IRobot robot = game.getCurrentRegister().getRobot();

        int xPos = robot.getPosition()[0];
        int yPos = robot.getPosition()[1];
        int desiredAngle = robot.getDir().getDirectionInDegrees();
        //Had to use this "hack" since the "default" rotation in libGDX is South, while in Direction it starts at North
        if(desiredAngle == 180)
            desiredAngle = 0;
        else if (desiredAngle == 0)
            desiredAngle = 180;

        int currentAngle = robotPositions[robotId][2];
        if(currentAngle < desiredAngle) {
            if(abs(desiredAngle - currentAngle) > 180)
                currentAngle -= 10;
            else
                currentAngle += 10;
        } else if (currentAngle > desiredAngle){
            if(abs(desiredAngle - currentAngle) > 180)
                currentAngle += 10;
            else
                currentAngle -= 10;
        }

        currentAngle = Math.floorMod(currentAngle, 360);

        robotPositions[robotId][2] = currentAngle;


        if(robotPositions[robotId][0] > xPos * tilePixelWidth + 5)
            robotPositions[robotId][0] -= 5;
        else if(robotPositions[robotId][0] < xPos * tilePixelWidth + 5)
            robotPositions[robotId][0] += 5;
        if(robotPositions[robotId][1] > yPos * tilePixelHeight + 5)
            robotPositions[robotId][1] -= 5;
        else if(robotPositions[robotId][1] < yPos * tilePixelHeight + 5)
            robotPositions[robotId][1] += 5;


        sprite.setPosition(robotPositions[robotId][0], robotPositions[robotId][1]);
        sprite.setRotation(currentAngle);
    }

    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        programRegisterGFX.render(batch, game.getCurrentRegister().getDamage(), game.getCurrentRegister().getLives(), game.getCurrentRegister().isPoweredDown());


        calculateRobotPosition(0);
        sprite.draw(batch);
        for (int i = 0; i < 5; i++){
            cards[i].draw(batch);
        }

        batch.end();
        if(showCards)
            renderAvailableCards(game.getCurrentRegister().getAvailableCards());
        renderActiveCards(game.getCurrentRegister().getActiveCards());
    }

    private void renderAvailableCards(ArrayList<ICard> availableCards) {
        Sprite[] cardSpriteTest = new Sprite[GameRuleConstants.MAX_CARDS_IN_REGISTER.getValue()];

        batch.begin();
        for(int i = 0; i < cardSpriteTest.length; i++) {
            if(i < availableCards.size()) {
                cardSpriteTest[i] = new Sprite(cardFront);

                int y = 10;
                if (i == cardId)
                    y += 20;

                cardSpriteTest[i].setPosition(i * 105f + 15f, y);
                cardSpriteTest[i].setSize(90f, 140f);
                cardSpriteTest[i].draw(batch);

                font.draw(batch, Integer.toString(availableCards.get(i).getPriority()), i * 105 + 66, y + 128);
                int type = availableCards.get(i).getType();

                font.draw(batch, createCardTypeString(type, availableCards, i), i * 105 + 35, y + 100);
            } else {
                cardSpriteTest[i] = new Sprite(cardBack);
                int y = 10;
                if (i == cardId)
                    y += 20;

                cardSpriteTest[i].setPosition(i * 105 + 15, y);
                cardSpriteTest[i].setSize(90f,140f);
                cardSpriteTest[i].draw(batch);
            }
        }
        batch.end();
    }

    private String createCardTypeString(int type, ArrayList<ICard> cards, int i) {
        String strType = "";
        String strValue = "";
        String strDir = "";
        if (type == 1) {
            ICardMovement tempCard = (ICardMovement) cards.get(i);
            strType = "Move";
            strValue = Integer.toString(tempCard.getMoveValue());
        } else if (type == 2) {
            ICardRotation tempCard = (ICardRotation) cards.get(i);
            strType = "Rotate";
            strValue = Integer.toString(tempCard.getRotationValue());
            if (tempCard.getRotationDirection())
                strDir = "RIGHT";
            else
                strDir = "LEFT";
        }
        return strType + " " + strValue + "\n" + strDir;
    }

    private void renderActiveCards(ArrayList<ICard> activeCards) {
        Sprite[] activeCardArray = new Sprite[activeCards.size()];

        batch.begin();
        for(int i = 0; i < activeCardArray.length; i++) {
            if(activeCards.get(i) == null)
                break;

            activeCardArray[i] = new Sprite(cardFront);

            int y = 680;
            int x = 970;
            activeCardArray[i].setPosition(i * 110 + x, y);
            activeCardArray[i].draw(batch);

            font.draw(batch, Integer.toString(activeCards.get(i).getPriority()), i * 110 + 56 + x, y + 136);
            int type = activeCards.get(i).getType();
            font.draw(batch, createCardTypeString(type, activeCards, i),i * 110 + 25 + x, y + 100);
        }
        batch.end();
    }

    private void choseCard() {
        game.getCurrentRegister().makeCardActive(cardId);
    }

    //TODO: should print to the screen
    public void printText(String input) {
        System.out.println(input);
    }

    public void flipShowCard() {
        showCards = !showCards;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public boolean keyUp(int keycode) {
        if(game.getGameState() == GameState.ANNOUNCING_POWER_DOWN) {
            if(keycode == Input.Keys.Y) {
                game.powerDownRobot(game.getCurrentRegister(), true);
                game.progressGameState();
            }
            if(keycode == Input.Keys.N) {
                game.powerDownRobot(game.getCurrentRegister(), false);
                game.progressGameState();
            }

        }

        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            if(showCards) {
                if(cardId == 0)
                    cardId = 9 - 1;
                else
                    cardId--;
            }
        }
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            if(showCards) {
                if(cardId == 9 - 1)
                    cardId = 0;
                else
                    cardId++;
            }
        }
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        if(keycode == Input.Keys.NUM_3)
            tiledMap.getLayers().get(2).setVisible(!tiledMap.getLayers().get(2).isVisible());
        if(keycode == Input.Keys.NUM_4)
            tiledMap.getLayers().get(3).setVisible(!tiledMap.getLayers().get(3).isVisible());
        if(keycode == Input.Keys.NUM_5)
            tiledMap.getLayers().get(4).setVisible(!tiledMap.getLayers().get(4).isVisible());
        if(keycode == Input.Keys.ENTER) {
            if(showCards)
                choseCard();
        }
        if(keycode == Input.Keys.SPACE)
            game.progressRound(this);
        if(keycode == Input.Keys.E) //TODO: used for testing, remove before hand-in
            game.activateConveyorBelts();

        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float x = Gdx.input.getDeltaX() * camera.zoom;
        float y = Gdx.input.getDeltaY() * camera.zoom;

        camera.translate(-x,y);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        float zoomAmount = amount;
        camera.zoom += zoomAmount / 10;
        return true;
    }

}
