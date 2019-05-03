
package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.board.IProgramRegister;
import inf112.skeleton.app.board.ProgramRegister;
import inf112.skeleton.app.card.ICard;
import inf112.skeleton.app.card.MovementCard;
import inf112.skeleton.app.card.RotationCard;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.game.IGame;
import inf112.skeleton.app.graphics.GFX;
import inf112.skeleton.app.robot.IRobot;
import inf112.skeleton.app.robot.Robot;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.*;

/**
 * Test of Program registers
 */
public class ProgramRegistersTest {
    IRobot robot;
    IProgramRegister programRegister;
    ArrayList<ICard> availableCards;
    IGame game;

    @Before
    public void initialize(){
        int[] testCoordinates = {1,1};
        robot = new Robot(1,testCoordinates);
        programRegister = new ProgramRegister(robot, true);
        availableCards=new ArrayList<>();
        ICard moveCard1 = new MovementCard(450,3);
        ICard moveCard2 = new MovementCard(120,1);
        ICard moveCard3 = new MovementCard(300,2);
        ICard moveCard4 = new MovementCard(90,1);
        ICard moveCard5 = new MovementCard(270,2);
        ICard moveCard6 = new MovementCard(390,3);
        ICard rotateCard1 = new RotationCard(230,true,1);
        ICard rotateCard2 = new RotationCard(190,false,1);
        ICard rotateCard3 = new RotationCard(60,true,1);
        availableCards.add(moveCard1);
        availableCards.add(moveCard2);
        availableCards.add(moveCard3);
        availableCards.add(rotateCard1);
        availableCards.add(rotateCard3);
        availableCards.add(rotateCard2);
        availableCards.add(moveCard4);
        availableCards.add(moveCard5);
        availableCards.add(moveCard6);



        //TiledMap map = new TmxMapLoader().load("assets/map1.tmx");
        //game=new Game(map,3);
    }

    @Test
    public void isCardSlotsFilledTest(){
        assertEquals(false, programRegister.isCardSlotsFilled());


        assertFalse(true == programRegister.isCardSlotsFilled());

    }


    @Test
    public void powerDownTest() {
        assertEquals(false, programRegister.isPoweredDown());
        programRegister.powerDown();
        assertEquals(true, programRegister.isPoweredDown());
        assertFalse(false == programRegister.isPoweredDown());
    }

    @Test
    public void LivesTests(){
        assertEquals(3, programRegister.getLives());
        programRegister.removeLife();
        programRegister.removeLife();
        assertEquals(1, programRegister.getLives());
    }

    @Test
    public void amountOfDamageTest(){
        assertEquals(0, programRegister.getDamage());
        programRegister.changeDamage(4);
        assertEquals(4, programRegister.getDamage());
        assertFalse(9 == programRegister.getDamage());
    }

    @Test
    public void checkIfCardsAreActive () {

    }

    @Test
    public void HumanTest() {

        programRegister=new ProgramRegister(robot,true);

        assertTrue(programRegister.isPlayerHuman());

        programRegister.turnHumanPlayerIntoAI();

        assertFalse(programRegister.isPlayerHuman());


    }

    @Test
    public void isDeadTest() {

        programRegister.removeLife();
        programRegister.removeLife();
        programRegister.removeLife();

        assertEquals(0,programRegister.getLives());
    }


    @Test
    public void flagCounterTest() {
        int flagCounter = programRegister.getFlagCounter();

        assertEquals(0,flagCounter);

        programRegister.increaseFlagCounter();
        programRegister.increaseFlagCounter();
        programRegister.increaseFlagCounter();

        flagCounter=programRegister.getFlagCounter();

        assertEquals(3,flagCounter);
    }

    @Test
    public void getRobotTest() {
        assertEquals(robot,programRegister.getRobot());
    }

    @Test
    public void restoreRobotTest() {

        programRegister.destroyRobot();

        assertTrue(programRegister.isDestroyed());

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Board";
        cfg.width = 1520;
        cfg.height = 960;


        LwjglApplication helper = new LwjglApplication(new GFX(),cfg);

        TiledMap map = new TmxMapLoader().load("assets/map/test/testMap.tmx");

        helper.exit();

        game = new Game(map,1,1);


        programRegister.restoreRobot(game);

        assertFalse(programRegister.isDestroyed());

    }

    @Test
    public void powerDownRobotTest() {

        programRegister.powerDown();

        assertTrue(programRegister.isPoweredDown());
    }

    @Test
    public void powerRobotOnTest() {
        programRegister.powerDown();
        programRegister.powerOn();

        assertFalse(programRegister.isPoweredDown());
    }

    @Test
    public void getLivesOfRobotTest () {
        assertEquals(3, programRegister.getLives());

        programRegister.removeLife();

        assertEquals(2,programRegister.getLives());
    }

    @Test
    public void getDamageOfRobotTest() {

        assertEquals(0, programRegister.getDamage());

        programRegister.setDamage(5);

        assertEquals(5,programRegister.getDamage());

    }

    @Test
    public void changeDamageOfRobotTest() {

        programRegister.changeDamage(8);

        assertEquals(8, programRegister.getDamage());

    }

    @Test
    public void cardSlotFilledTest() {

        programRegister.setAvailableCards(availableCards);

        programRegister.makeCardActive(0);
        programRegister.makeCardActive(4);
        programRegister.makeCardActive(1);
        programRegister.makeCardActive(0);
        programRegister.makeCardActive(0);


        assertTrue(programRegister.isCardSlotsFilled());
    }

    @Test
    public void discardOfUnusedCardTest() {

        programRegister.setAvailableCards(availableCards);

        programRegister.makeCardActive(0);
        programRegister.makeCardActive(4);
        programRegister.makeCardActive(1);
        programRegister.makeCardActive(0);
        programRegister.makeCardActive(0);

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Board";
        cfg.width = 1520;
        cfg.height = 960;


        LwjglApplication helper = new LwjglApplication(new GFX(),cfg);

        TiledMap map = new TmxMapLoader().load("assets/map/test/testMap.tmx");

        helper.exit();

        game = new Game(map,4,1);

        programRegister.discardUnusedCards(game);

        assertEquals(programRegister.getAvailableCards().size(),0);

    }

    @Test
    public void discardOfAllCardsTest() {


        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Board";
        cfg.width = 1520;
        cfg.height = 960;


        LwjglApplication helper = new LwjglApplication(new GFX(),cfg);

        TiledMap map = new TmxMapLoader().load("assets/map/test/testMap.tmx");

        helper.exit();

        game = new Game(map,4,1);

        programRegister.discardAllCards(game);

        assertEquals(0,programRegister.getAvailableCards());

    }

    @Test
    public void setAvailableCardsTest() {

        programRegister.setAvailableCards(availableCards);

        assertEquals(availableCards,programRegister.getAvailableCards());

    }

    @Test
    public void turnACardTest() {

        programRegister.turnACard(3);

        boolean flipped = programRegister.isCardFlipped(3);

        assertTrue(flipped);
    }

    @Test
    public void makeACardActiveTest() {

        programRegister.setAvailableCards(availableCards);


        boolean success = programRegister.makeCardActive(0);

        assertTrue(success);


    }

    @Test
    public void getAvailableCardsTest() {
        programRegister.setAvailableCards(availableCards);

        assertEquals(availableCards,programRegister.getAvailableCards());
    }

    @Test
    public void getActiveCardsTest() {

        programRegister.setAvailableCards(availableCards);

        ICard card1 = programRegister.getAvailableCards().get(0);
        ICard card2 = programRegister.getAvailableCards().get(1);
        ICard card3 = programRegister.getAvailableCards().get(2);
        ICard card4 = programRegister.getAvailableCards().get(3);
        ICard card5 = programRegister.getAvailableCards().get(4);

        programRegister.makeCardActive(0);
        programRegister.makeCardActive(0);
        programRegister.makeCardActive(0);
        programRegister.makeCardActive(0);
        programRegister.makeCardActive(0);


        ArrayList<ICard> listOfCards = programRegister.getActiveCards();

        assertTrue(listOfCards.contains(card1));

    }

    @Test
    public void getActiveCardsInPosition() {

    }
}
