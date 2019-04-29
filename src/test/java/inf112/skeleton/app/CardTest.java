package inf112.skeleton.app;

import inf112.skeleton.app.card.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Simple test class that tests the MovementCard & RotationCard classes
 */
public class CardTest {
    @Test
    public void movementTests(){
        //These values correspond to a "Move 1 forward" card
        final int INTERVAL_START = 490;
        final int INTERVAL = 10;
        final int TYPE = 1;
        final int MOVE_VALUE = 1;
        final int NUM_OF_CARDS = 18;

        MovementCard[] movementCardArray = new MovementCard[NUM_OF_CARDS];

        for(int i = 0; i < movementCardArray.length; i++){
            movementCardArray[i] = new MovementCard(INTERVAL_START + i * INTERVAL, MOVE_VALUE);
        }

        priorityTests(movementCardArray,INTERVAL_START,INTERVAL);
        typeTest(movementCardArray, TYPE);
        moveValueTest(movementCardArray, MOVE_VALUE);
    }

    @Test
    public void rotationTests(){
        //These values correspond to a "rotate left" card
        final int INTERVAL_START = 70;
        final int INTERVAL = 20;
        final int TYPE = 2;
        final int ROTATION_VALUE = 1;
        final boolean ROTATION_DIRECTION = false;
        final int NUM_OF_CARDS = 18;

        RotationCard[] rotationCardArray = new RotationCard[NUM_OF_CARDS];

        for(int i = 0; i < rotationCardArray.length; i++){
            rotationCardArray[i] = new RotationCard(INTERVAL_START + i * INTERVAL, ROTATION_DIRECTION, ROTATION_VALUE);
        }

        priorityTests(rotationCardArray,INTERVAL_START,INTERVAL);
        typeTest(rotationCardArray, TYPE);
        rotationDirectionTest(rotationCardArray, ROTATION_DIRECTION);
        rotationValueTest(rotationCardArray, ROTATION_VALUE);
    }

    private void priorityTests(ICard[] cardArray, int intervalStart, int interval){
        for(int i = 0; i < cardArray.length; i++){
            assertEquals(intervalStart + i * interval, cardArray[i].getPriority());
        }
    }

    private void typeTest(ICard[] cardArray, int type){
        for(ICard card : cardArray){
            assertEquals(type, card.getType());
        }
    }

    private void moveValueTest(ICardMovement[] movementCardArray, int moveValue){
        for(ICardMovement card : movementCardArray){
            assertEquals(moveValue, card.getMoveValue());
        }
    }

    private void rotationDirectionTest(ICardRotation[] rotationCardArray, boolean rotationDirection){
        for(ICardRotation card : rotationCardArray){
            assertEquals(rotationDirection, card.getRotationDirection());
        }
    }

    private void rotationValueTest(ICardRotation[] rotationCardArray, int rotationValue){
        for(ICardRotation card : rotationCardArray){
            assertEquals(rotationValue, card.getRotationValue());
        }
    }
}
