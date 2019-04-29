package inf112.skeleton.app.board;

import inf112.skeleton.app.card.*;
import inf112.skeleton.app.game.GameRuleConstants;
import inf112.skeleton.app.game.IGame;
import inf112.skeleton.app.robot.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mari on 24.02.2019.
 */
public class ProgramRegister implements IProgramRegister {
    private final int maxLives = GameRuleConstants.MAX_LIVES.getValue();    //The max amount of lives
    private int maxDamage = GameRuleConstants.MAX_DAMAGE.getValue();    //The max amount of damage the register can register
    private IRobot robot;           //The robot associated with the program register
    private int lives;              //The current amount of lives
    private boolean powerDowned;    //Whether the robot in the register should be powered down or not
    private int damage;
    private int flagCounter;
    private boolean isRobotDestroyed;


    /**
     * All of the variables and lists relating to cards
     * availableCards: All cards in the register (usually 9)
     * activeCards: The cards used in phase execution (usually 5)
     * isCardFlipped: Stores whether an active card is flipped or not
     */
    private ArrayList<ICard> availableCards = new ArrayList<>();
    private ICard[] activeCards = new ICard[GameRuleConstants.ACTIVE_CARDS_IN_REGISTER.getValue()];
    private boolean[] isCardFlipped = new boolean[GameRuleConstants.ACTIVE_CARDS_IN_REGISTER.getValue()];
    private int maxAvailableCardAmount = GameRuleConstants.MAX_CARDS_IN_REGISTER.getValue();
    private int maxActiveCardAmount = GameRuleConstants.ACTIVE_CARDS_IN_REGISTER.getValue();

    public ProgramRegister(IRobot robot){
        this.robot = robot;
        lives = maxLives;
        powerDowned = false;
        damage = 0;
        flagCounter = 0;
        isRobotDestroyed = false;
    }

    @Override
    public boolean isDead() {
        return lives == 0;
    }

    @Override
    public int getFlagCounter() {
        return flagCounter;
    }

    @Override
    public void increaseFlagCounter() {
        flagCounter++;
    }

    @Override
    public IRobot getRobot() {
        return robot;
    }

    @Override
    public void destroyRobot() {
        isRobotDestroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return isRobotDestroyed;
    }

    @Override
    public void restoreRobot() {
        isRobotDestroyed = false;
    }

    @Override
    public void powerDown() {
        setDamage(0);
        powerDowned = true;
    }

    @Override
    public boolean isPoweredDown() {
        return powerDowned;
    }

    /**
     * activates robot from powerDown
     */
    @Override
    public void powerOn() {
        powerDowned = false;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void removeLife() {
        lives--;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void changeDamage(int dam) {
        damage += dam;
        if(damage < 0)
            damage = 0;
        if(damage >= maxDamage)
            isRobotDestroyed = true;
    }

    /**
     * Checks if all five card slots are filled
     *
     * @return true if they are filled
     */
    @Override
    public boolean isCardSlotsFilled() {
        for (int i = 0; i < activeCards.length; i++) {
            if (activeCards[i] == null) {
                return false;
            }
        }
        return true;
    }

    //TODO: uses the exact same code as discardAllCards first part, consider merging methods
    @Override
    public void discardUnusedCards(IGame game) {
        int availableCardsSize = availableCards.size();
        for(int i = 0; i < availableCardsSize; i++) {
            game.addCardToDeck(availableCards.get(0));
            availableCards.remove(0);
        }
    }

    //TODO: should not remove a card if it's "locked in" because of damage
    @Override
    public void discardAllCards(IGame game) {
        int availableCardsSize = availableCards.size();
        for(int i = 0; i < availableCardsSize; i++) {
            game.addCardToDeck(availableCards.get(0));
            availableCards.remove(0);
        }

        for(int i = 0; i < activeCards.length; i++) {
            if(activeCards[i] != null) {
                game.addCardToDeck(activeCards[i]);
                activeCards[i] = null;
            }
        }
    }

    @Override
    public void setAvailableCards(ArrayList<ICard> listOfCards) {
        this.availableCards = listOfCards;
    }

    /**
     * Turns a card during a phase/register
     *
     * @param numCard
     */
    @Override
    public void turnACard(int numCard) {
        isCardFlipped[numCard] = !isCardFlipped[numCard];
    }

    @Override
    public boolean makeCardActive(int numCard) {
        for(int i = 0; i < activeCards.length; i++) {
            if(activeCards[i] == null){
                activeCards[i] = availableCards.get(numCard);
                availableCards.remove(numCard);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all of the available cards
     * @return ArrayList<ICard> of available cards
     */
    @Override
    public ArrayList<ICard> getAvailableCards() {
        return availableCards;
    }

    /**
     * Returns all of the active cards
     * @return ArrayList<ICard> of active cards
     */
    @Override
    public ArrayList<ICard> getActiveCards() {
        return new ArrayList<>(Arrays.asList(activeCards));
    }

    @Override
    public ICard getActiveCardInPosition(int position) {
        return activeCards[position];
    }
}