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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.board.Direction;
import inf112.skeleton.app.board.IProgramRegister;
import inf112.skeleton.app.card.ICard;
import inf112.skeleton.app.card.ICardMovement;
import inf112.skeleton.app.card.ICardRotation;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.game.GameRuleConstants;
import inf112.skeleton.app.game.GameState;
import inf112.skeleton.app.game.PhaseState;
import inf112.skeleton.app.robot.IRobot;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

@SuppressWarnings("Since15")
public class GameGFX extends Stage {
    private final boolean ANARCHY_MODE = false; //Makes the game run 3x faster than usual, used only for testing (and fun)

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private ProgramRegisterGFX programRegisterGFX;

    private SpriteBatch batch;
    private SpriteBatch absoluteBatch;
    private Texture cardBack;
    private Texture cardFront;

    private Sprite thisPlayerSprite;
    private Sprite[] otherPlayerSprites;
    private Sprite spriteCardBack;
    private Sprite spriteCardFront;
    private Sprite[] cards;

    private Texture laserVertical;
    private Texture laserHorizontal;

    private ArrayList<Sprite> spriteLaserVerticalList;
    private ArrayList<Sprite> spriteLaserHorizontalList;


    private int tilePixelWidth;
    private int tilePixelHeight;

    private int[][] robotPositions;

    //Used for testing, should not be pushed
    private boolean showCards = false;
    private BitmapFont font;
    private int cardId = 0;
    private Game game;
    private Menu menu;

    private int numberOfRealPlayers;
    private int numberOfAI;
    private ArrayList<int[]> startRobotLaser;
    private ArrayList<int[]> endRobotLaser;

    private int[] programRegisterPosition = {960, 1080};
    private ArrayList<MessageGFX> messages = new ArrayList<>();
    private Timer timer = new Timer();

    public void create (int numPlayersIn, int numAIIn, TiledMap tiledMapIn) {
        spriteLaserVerticalList = new ArrayList<>();
        spriteLaserHorizontalList = new ArrayList<>();
        startRobotLaser = new ArrayList<>();
        endRobotLaser = new ArrayList<>();
        numberOfRealPlayers = numPlayersIn;
        numberOfAI = numAIIn;
        tiledMap = tiledMapIn;
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
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
        createGame(numberOfRealPlayers + numAIIn, numberOfRealPlayers);
        initialiseSprites(numberOfRealPlayers + numAIIn);

        Timer.Task updateMessageDurations = new Timer.Task() {
            @Override
            public void run() {
                decreaseMessageTimer();
            }
        };
        timer.scheduleTask(updateMessageDurations, 0f, 1f, Integer.MAX_VALUE);
        timer.start();
        int x = -850;
        int y = 1210;
        for(int i = 0; i < otherPlayerSprites.length; i++) {
            int[] tempPos = {x, y};
            MessageGFX tempMessage = new MessageGFX("Robot " + (i + 1) + " active cards:", tempPos, false, 2);
            messages.add(tempMessage);
            y -= tilePixelHeight * 2;
        }
    }

    private void initialiseSprites(int numberOfSprites) {
        laserHorizontal = new Texture(Gdx.files.internal("assets/laserHorizontal.png"));
        laserVertical = new Texture(Gdx.files.internal("assets/laserVertical.png"));
        batch = new SpriteBatch();
        absoluteBatch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("assets/bot-g.gif"));
        thisPlayerSprite = new Sprite(texture);
        texture = new Texture(Gdx.files.internal("assets/bot-r.gif"));
        otherPlayerSprites = new Sprite[numberOfSprites];
        for(int i = 0; i < otherPlayerSprites.length; i++)
            otherPlayerSprites[i] = new Sprite(texture);

        programRegisterGFX = new ProgramRegisterGFX(programRegisterPosition[0], programRegisterPosition[1]);

        cardBack = new Texture(Gdx.files.internal("assets/card_back.png"));
        cardFront = new Texture(Gdx.files.internal("assets/card_front.png"));
        spriteCardBack = new Sprite(cardBack);
        spriteCardFront = new Sprite(cardFront);
        cards = new Sprite[5];
        for(int i = 0; i < 5; i++){
            cards[i] = new Sprite(cardBack);
            cards[i].setPosition(programRegisterPosition[0] + 10 + (i*110), programRegisterPosition[1] - 80);
        }
        spriteCardBack = new Sprite(cardBack);
        spriteCardFront = new Sprite(cardFront);
    }
    private void initialiseRobotLasers() {
        ArrayList<IProgramRegister> robotRegister = game.getAllProgramRegisters();
        spriteLaserVerticalList = new ArrayList<>();
        for (int i = 0; i < robotRegister.size(); i++) {
            if (robotRegister.get(i).isDestroyed()) break;
            Direction tempDir = robotRegister.get(i).getRobot().getDir();
            int[] tempPos;
            int j = robotRegister.get(i).getRobot().getDir().getDirectionValue();
            tempPos = robotRegister.get(i).getRobot().getPosition().clone();
            for (int k = 0; k < 20; k++) {
                if (game.checkForWall(tempPos, tempDir)) break;
                if (j % 2 == 0) {
                    tempPos = game.getPositionInDirection(tempPos, tempDir);
                    spriteLaserVerticalList.add(new Sprite(laserVertical));
                    spriteLaserVerticalList.get(k).setPosition((tilePixelWidth) * tempPos[0] + 40, (tilePixelHeight) * tempPos[1] + 3);
                    spriteLaserVerticalList.get(k).draw(batch);
                    if (!game.possibleLaser(tempPos, tempDir)) break;
                } else if (j % 2 != 0) {
                    tempPos = game.getPositionInDirection(tempPos, tempDir);
                    spriteLaserHorizontalList.add(new Sprite(laserHorizontal));
                    spriteLaserHorizontalList.get(k).setPosition(tilePixelWidth *   tempPos[0] + 3, tilePixelHeight * tempPos[1] + 40);
                    spriteLaserHorizontalList.get(k).draw(batch);
                    if (!game.possibleLaser(tempPos, tempDir)) break;
                }
            }
        }
    }

    private void createGame(int numberOfPlayers, int numberOfRealPlayers) {
        game = new Game(tiledMap, numberOfPlayers, numberOfRealPlayers);
        robotPositions = new int[numberOfPlayers][3];
        for(int i = 0; i < numberOfPlayers; i++) {
            robotPositions[i][0] = game.getAllProgramRegisters().get(i).getRobot().getPosition()[0] * tilePixelWidth;
            robotPositions[i][1] = game.getAllProgramRegisters().get(i).getRobot().getPosition()[1] * tilePixelHeight;
            robotPositions[i][2] = 180; //TODO: change if the sprite for the robot is changed
        }

        Timer.Task progressGame = new Timer.Task() {
            @Override
            public void run() {
                progressGame();
            }
        };
        float updateInterval;
        if(game.checkIfGameHasHumanPlayers()) {
            updateInterval = 0.5f;
        } else if(ANARCHY_MODE) {
            updateInterval = 0.1f;
        } else {
            updateInterval = 0.3f;
        }
        timer.scheduleTask(progressGame, 3f, updateInterval, Integer.MAX_VALUE);
    }

    private void progressGame() {
        game.progressRound(this);
    }

    //Used to render the robot
    private void calculateRobotPosition(int robotId) {
        IRobot robot = game.getAllProgramRegisters().get(robotId).getRobot();

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
    }

    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        if(game.checkIfGameHasHumanPlayers()) {
            programRegisterGFX.render(batch, game.getCurrentRegister().getDamage(), game.getCurrentRegister().getLives(), game.getCurrentRegister().isPoweredDown(), game.getCurrentRegister().getFlagCounter());
            renderActiveCards(programRegisterPosition[0]+10, programRegisterPosition[1] -80, game.getCurrentRegister(), true); //Renders the cards on the program register
        }
        renderRobots();
        if(game.getPhaseState().equals(PhaseState.FIRE_LASERS)){
            initialiseRobotLasers();
        }
        if(showCards && game.checkIfGameHasHumanPlayers())
            renderAvailableCards(game.getCurrentRegister().getAvailableCards());

        renderText();
        if(game.getGameState() == GameState.EXECUTING_PHASES) {
            changeOtherActiveCardsVisibility(true);
            int x = 1125;
            for(int i = 0; i < otherPlayerSprites.length; i++) { //Renders the cards of all of the robots as they are flipped
                renderActiveCards(-575, x, game.getAllProgramRegisters().get(i), false);
                x -= tilePixelHeight * 2;
            }
        } else
            changeOtherActiveCardsVisibility(false);
        batch.end();
    }

    private void renderActiveCards(int xPos, int yPos, IProgramRegister register, boolean ignoreFlipped) {
        ArrayList<ICard> activeCards = register.getActiveCards();
        Sprite[] activeCardArray = new Sprite[activeCards.size()];

        for(int i = 0; i < activeCardArray.length; i++) {
            if(activeCards.get(i) == null)
                break;

            boolean drawText = false;
            if(register.getIsCardFlipped()[i] || ignoreFlipped) {
                activeCardArray[i] = new Sprite(cardFront);
                drawText = true;
            } else
                activeCardArray[i] = new Sprite(cardBack);

            activeCardArray[i].setPosition(i * 110 + xPos, yPos);
            activeCardArray[i].draw(batch);

            if(drawText) {
                font.draw(batch, Integer.toString(activeCards.get(i).getPriority()), i * 110 + 56 + xPos, yPos + 136);
                int type = activeCards.get(i).getType();
                font.draw(batch, createCardTypeString(type, activeCards, i), i * 110 + 25 + xPos, yPos + 100);
            }
        }
    }

    private void changeOtherActiveCardsVisibility(boolean shouldBeVisible) {
        for(MessageGFX message : messages) {
            if(message.getMessage().contains("Robot")) {
                message.setVisible(shouldBeVisible);
            }
        }
    }

    private void renderRobots() {
        calculateRobotPosition(0);
        thisPlayerSprite.setPosition(robotPositions[0][0], robotPositions[0][1]);
        thisPlayerSprite.setRotation(robotPositions[0][2]);
        if(!game.getCurrentRegister().isDestroyed())
            thisPlayerSprite.draw(batch);
        for(int i = 1; i < game.getAllProgramRegisters().size(); i++) {
            calculateRobotPosition(i);
            //Subtracts 1 in the otherPlayerSprites array since it's 1 shorter in length
            otherPlayerSprites[i - 1].setPosition(robotPositions[i][0], robotPositions[i][1]);
            otherPlayerSprites[i - 1].setRotation(robotPositions[i][2]);
            if(!game.getAllProgramRegisters().get(i).isDestroyed())
                otherPlayerSprites[i - 1].draw(batch);
        }
    }

    private void renderAvailableCards(ArrayList<ICard> availableCards) {
        Sprite[] cardSpriteTest = new Sprite[GameRuleConstants.MAX_CARDS_IN_REGISTER.getValue()];

        absoluteBatch.begin();
        for(int i = 0; i < cardSpriteTest.length; i++) {
            if(i < availableCards.size()) {
                cardSpriteTest[i] = new Sprite(cardFront);

                int y = 10;
                if (i == cardId)
                    y += 20;

                cardSpriteTest[i].setPosition(i * 105f + 15f, y);
                cardSpriteTest[i].setSize(90f, 140f);
                cardSpriteTest[i].draw(absoluteBatch);

                font.draw(absoluteBatch, Integer.toString(availableCards.get(i).getPriority()), i * 105 + 66, y + 128);
                int type = availableCards.get(i).getType();

                font.draw(absoluteBatch, createCardTypeString(type, availableCards, i), i * 105 + 35, y + 100);
            } else {
                cardSpriteTest[i] = new Sprite(cardBack);
                int y = 10;
                if (i == cardId)
                    y += 20;

                cardSpriteTest[i].setPosition(i * 105 + 15, y);
                cardSpriteTest[i].setSize(90f,140f);
                cardSpriteTest[i].draw(absoluteBatch);
            }
        }
        absoluteBatch.end();
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

    private void choseCard() {
        game.getCurrentRegister().makeCardActive(cardId);
    }

    private void decreaseMessageTimer() {
        for(int i = 0; i < messages.size(); i++) {
            MessageGFX message = messages.get(i);
            if(message.hasDuration()) {
                if(message.decreaseDuration()) {
                    messages.remove(message);
                    i--;
                }
            }
        }
    }

    public void printTextToDefaultPosition(String input, float scale, int duration) {
        int[] defaultPos = {1000, 800};
        print(input, defaultPos, scale, duration);
    }

    public void print(String input, int[] position, float scale, int duration) {
        MessageGFX tempMessage = new MessageGFX(input, position, true, scale, duration);
        messages.add(tempMessage);
    }

    private void renderText() {
        float oldScaleX = font.getData().scaleX;
        float oldScaleY = font.getData().scaleY;
        for(MessageGFX message : messages) {
            if(message.isVisible()) {
                font.getData().setScale(message.getScale(), message.getScale());
                font.draw(batch, message.getMessage(), message.getPosition()[0], message.getPosition()[1]);
            }
        }
        font.getData().setScale(oldScaleX, oldScaleY);
    }

    public void flipShowCard() {
        showCards = !showCards;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public boolean keyUp(int keycode) {
        if(game.getGameState() == GameState.ANNOUNCING_POWER_DOWN) { //TODO: move these to game
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
                    cardId = game.getCurrentRegister().getAvailableCards().size() - 1;
                else
                    cardId--;
            }
        }
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            if(showCards) {
                if(cardId == game.getCurrentRegister().getAvailableCards().size() - 1)
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
            if(showCards) {
                choseCard();
                if(cardId > 0)
                    cardId--;
            }
        }
        if(keycode == Input.Keys.SPACE)
            game.progressRound(this);

        if(keycode == Input.Keys.E) //TODO: used for testing, remove before hand-in
            game.activateBoardElements();

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
    public boolean gameOver(){
        return game.gameOver();
    }

}
