package inf112.skeleton.app.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.board.*;
import inf112.skeleton.app.board.ConveyorBelts.*;
import inf112.skeleton.app.card.*;
import inf112.skeleton.app.graphics.GameGFX;
import inf112.skeleton.app.robot.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game implements IGame {
    private ArrayList<ICard> deck = new ArrayList<>();
    private ArrayList<int[]> boardHoles = new ArrayList<>();
    private ArrayList<IFlag> boardFlags = new ArrayList<>(); //TODO: consider sorting the flags
    private ArrayList<int[]> boardRepairSites = new ArrayList<>();
    private ArrayList<IConveyorTurn> gears = new ArrayList<>(); //Uses the "turn" type conveyor, since it's essentially a 0 move turner
    //Works very similar to straight conveyors, thus uses the same class
    private ArrayList<ConveyorStraight> pushersEven = new ArrayList<>();
    private ArrayList<ConveyorStraight> pushersOdd = new ArrayList<>();
    private int[][] startingPoints = new int[GameRuleConstants.NUMBER_OF_STARTING_POINTS.getValue()][2]; //Stores starting points in order

    private ArrayList<int[]> northWalls = new ArrayList<>();
    private ArrayList<int[]> westWalls = new ArrayList<>();
    private ArrayList<int[]> eastWalls = new ArrayList<>();
    private ArrayList<int[]> southWalls = new ArrayList<>();
    private ArrayList<int[]>[] boardWalls = new ArrayList[4];
    private ArrayList<IConveyorBelt> conveyorBelts = new ArrayList<>();
    private ArrayList<ILaser> laser = new ArrayList<>();
    private ArrayList<IProgramRegister> allProgramRegisters = new ArrayList<>();
    private IProgramRegister currentRegister;
    private Board board;
    private ArrayList<int[]> robotLaserEnd;
    private boolean rLaserIsActive;

    private IAI AI;

    private GameState gameState;
    private PhaseState phaseState;
    private int phaseNumber = 0;
    private ArrayList<IProgramRegister> robotsToMove = new ArrayList<>();
    private boolean gameHasHumanPlayers;

    public Game(TiledMap tiledMap, int numberOfPlayers, int numberOfHumanPlayers) {
        rLaserIsActive = false;
        robotLaserEnd = new ArrayList<>();
        gameState = GameState.SETUP;
        phaseState = PhaseState.REVEAL_CARDS;
        board = new Board(tiledMap);
        boardWalls[0] = northWalls;
        boardWalls[1] = eastWalls;
        boardWalls[2] = southWalls;
        boardWalls[3] = westWalls;

        initialize();
        createDeck();
        shuffleDeck();
        AI = new SimpleBraveAI();
        programRegistersFactory(numberOfPlayers, numberOfHumanPlayers);
        currentRegister = allProgramRegisters.get(0);
        gameHasHumanPlayers = numberOfHumanPlayers != 0;

        /*int[] testPos1 = {0, 1}; //TODO: for tests, remove later
        allProgramRegisters.get(0).getRobot().setPosition(testPos1);
        int[] testPos2 = {9, 11};
        allProgramRegisters.get(1).getRobot().setPosition(testPos2);*/
    }

    @Override
    public boolean checkIfGameHasHumanPlayers() {
        return gameHasHumanPlayers;
    }

    public ArrayList<IProgramRegister> getAllProgramRegisters() {
        return allProgramRegisters;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void progressGameState() {
        System.out.println("Switching to state " + gameState.nextState(false));
        gameState = gameState.nextState(false);
    }

    private void programRegistersFactory(int numberOfPlayers, int numberOfHumanPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            int[] robotPos = startingPoints[i];
            Robot robot = new Robot(i + 1, robotPos);
            boolean isPlayerHuman = i < numberOfHumanPlayers;
            ProgramRegister programRegister = new ProgramRegister(robot, isPlayerHuman);
            allProgramRegisters.add(programRegister);
        }
    }

    @Override
    public IBoard getBoard() {
        return board;
    }

    public IProgramRegister getCurrentRegister() {
        return currentRegister;
    }

    /**
     * @param coordinates
     */
    @Override
    public void absoluteMove(IRobot robot, int[] coordinates) {
        robot.setPosition(coordinates);
    }

    @Override
    public void relativeMove(IRobot robot, ICardMovement card) {
        relativeMoveStraight(robot, robot.getDir(),  card.getMoveValue());
    }

    /**
     * Attempts to move a robot in a given direction
     * @param robot the robot to be moved
     * @param dir the direction to move
     * @param moveValue the amount of movement that should be done
     * @return true if successful, false otherwise
     */
    public boolean relativeMoveStraight(IRobot robot, Direction dir, int moveValue) {
        if(getRegisterFromRobot(robot).isDestroyed())
            return false;

        //get current position of robot
        int[] coordinates = robot.getPosition();

        if(moveValue < 0) { //Makes backward movement work properly
            dir = dir.next().next();
            moveValue = Math.abs(moveValue);
        }

        //Checks if the robot encounters a wall for each movement
        for(int i = 0; i < moveValue; i++) {
            if(!checkForWall(coordinates, dir)) {
                coordinates = getPositionInDirection(coordinates, dir);
                if(checkIfContainsRobot(coordinates) != null) {
                    if(!relativeMoveStraight(checkIfContainsRobot(coordinates).getRobot(), dir, 1))
                        return false;
                }
            } else {
                System.out.println("Robot in {" + coordinates[0] + "," + coordinates[1] + "} hit a wall going " + dir);
            }
        }
        robot.setPosition(coordinates);
        if(checkIfOnHoleOrOutsideBoard(robot)) {
            getRegisterFromRobot(robot).destroyRobot();
        }
        return true;
    }

    /**
     * Small help-method to get an adjacent position in a direction without affecting the input
     */
    public int[] getPositionInDirection(int[] startPos, Direction dir) {
        int[] endPos = startPos.clone();
        endPos[0] += dir.getDeltaX();
        endPos[1] += dir.getDeltaY();
        return  endPos;
    }

    /**
     * Finds a programRegister based on the robot provided
     * @param robot
     * @return
     */
    private IProgramRegister getRegisterFromRobot(IRobot robot) {
        for(IProgramRegister register : allProgramRegisters) {
            if(robot.equals(register.getRobot()))
                return register;
        }

        return null;
    }

    /**
     * Turns the current Robot
     *
     * @param card the rotation card
     */
    @Override
    public void rotationMove(IRobot robot, ICardRotation card) {
        if(getRegisterFromRobot(robot).isDestroyed())
            return; //Rotation is not performed if robot is destroyed

        for (int i = 0; i < card.getRotationValue(); i++)
            robot.rotate(card.getRotationDirection());
    }

    /**
     * Checks if the robot from the currentRegister is on any of the holes in the board
     * TODO: needs to be tested
     *
     * @return true if on any holes, false otherwise
     */
    public boolean checkIfOnHoleOrOutsideBoard(IRobot robot) {
        int[] robotPos = robot.getPosition();
        //Checks if the robot's position overlaps with any holes
        for (int[] holePos : boardHoles) {
            if (Arrays.equals(holePos, robotPos))
                return true;
        }
        return checkIfOutsideBoard(robot.getPosition());
    }

    public boolean checkIfOutsideBoard(int[] position){
        //Checks if the robot is outside of the board
        if (position[0] > board.getWidth() || position[0] < 0)
            return true;
        if (position[1] > board.getHeight() || position[1] < 0)
            return true;

        return false;
    }

    @Override
    public boolean checkForWall(int[] position, Direction dir){
        //Checks if there is a wall on the same square blocking movement
        for(int[] wallPosition : boardWalls[dir.getDirectionValue()]) {
            if(Arrays.equals(position, wallPosition)) {
                return true;
            }
        }

        //Gets the coordinates of the square the robot is moving into
        int[] adjacentPosition = getPositionInDirection(position, dir);
        //Might seem like redundant code, but means you only have to go through two of the wall Lists
        for(int[] wallPosition : boardWalls[dir.next().next().getDirectionValue()]) { //Uses next() twice to get the opposite direction
            if(Arrays.equals(adjacentPosition, wallPosition)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkIfOnFlag(IRobot robot) {
        int[] robotPos = robot.getPosition();
        for (int i = 0; i < boardFlags.size(); i++) {
            int[] flagPos = boardFlags.get(i).getPosition();
            if (Arrays.equals(flagPos, robotPos))
                return true;
        }
        return false;
    }

    @Override
    public void activateLasers() {
        for(IProgramRegister currentRegister : allProgramRegisters){
            for(ILaser currentLaser : laser) {

                if (currentRegister.getRobot().getPosition()[0] == (currentLaser.getPosition())[0] &&
                    currentRegister.getRobot().getPosition()[1] == (currentLaser.getPosition())[1] ) {
                    currentRegister.changeDamage(currentLaser.getDamage());

                    break;
                }
            }
        }
    }

    /**
     * Ikke pent
     */
    public boolean robotLaserIsActive(){
        return rLaserIsActive;
    }
    @Override
    public void activateRobotLasers(){
        int[] position;
        Direction direction;
        boolean hit;

        for(IProgramRegister currentRegister : allProgramRegisters) {
            position = currentRegister.getRobot().getPosition();
            direction = currentRegister.getRobot().getDir();
            for(int i = 0; i < board.getHeight(); i++) {
                if(checkIfOutsideBoard(position));
                if(checkForWall(position, direction)) break;
                if (checkIfContainsRobot(position) != null && !Arrays.equals(position,currentRegister.getRobot().getPosition())) {
                    checkIfContainsRobot(position).changeDamage(1);
                    System.out.println("hit");
                    break;
                } else{
                    position = getPositionInDirection(position, direction);
                }
            }
            robotLaserEnd.add(position);
        }
        rLaserIsActive = true;
    }
    public boolean possibleLaser(int[] position, Direction direction){
        if(checkIfOutsideBoard(position)){
            return false;
        }
        else if(checkForWall(position, direction)){
            return false;
        }
        else if(checkIfContainsRobot(position) != null){
            return false;
        }
        else{
            return true;
        }

    }
    @Override
    public void doRepairs () {
        for (IProgramRegister currentRegister : allProgramRegisters) {
            for (int[] repairSitePos : boardRepairSites) {
                if (Arrays.equals(currentRegister.getRobot().getPosition(), repairSitePos)) {
                    repair(currentRegister);
                }
            }
        }
    }

    private void initialize () {
        int width = board.getWidth();
        int height = board.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                initializeBoardElements(i, j);
                initializeLaser(i, j);
                initializeWalls(i, j);
            }
        }
    }

    //Helper method used by initializeBoardElements
    private void initializeStartingPoint(BoardElement startingPoint, int xCoordinate, int yCoordinate) {
        int[] tempCoordinate = {xCoordinate, yCoordinate};
        startingPoints[startingPoint.getValue() - 1] = tempCoordinate;
    }


    /**
     * Goes through the board and initializes the walls into their perspective ArrayLists
     */
    private void initializeWalls(int x, int y) {
        ArrayList<BoardElement> walls = board.getWalls(x, y);
        if (walls != null) {
            int[] tempPos = {x, y};
            for (BoardElement wall : walls) {
                if (!BoardElement.WALLS.contains(wall))
                    throw new IllegalArgumentException("The given element is not a wall");
                else
                    boardWalls[wall.getDirection().getDirectionValue()].add(tempPos);
            }
        }
    }

    private void initializeLaser(int x, int y) {
        if (board.getLaser(x, y)!= null){
            laser.add(board.getLaser(x,y));
        }
    }

    private void initializeBoardElements(int x, int y) {
        BoardElement elem = board.getBoardElement(x, y);
        int[] tempCoordinates = {x, y}; //Temporarily creates coordinates for the elements that need those
        if (elem == BoardElement.NORMAL_TILE) {
            //If it's a normal tile you might as well do nothing and stop
        } else if(BoardElement.FLAGS.contains(elem)) {
            boardFlags.add(new Flag(elem.getValue() - 1, x, y));
        } else if (elem == BoardElement.CONVEYORBELT) {
            conveyorBelts.add(board.getConveyorBelt(x, y));
        } else if (BoardElement.GEARS.contains(elem)) {
            if (elem == BoardElement.GEAR_RIGHT)
                gears.add(new ConveyorTurn(null, 0, tempCoordinates, true));
            else if (elem == BoardElement.GEAR_LEFT)
                gears.add(new ConveyorTurn(null, 0, tempCoordinates, false));
        } else if (elem == BoardElement.HOLE) {
            boardHoles.add(tempCoordinates);
        } else if (BoardElement.WRENCHES.contains(elem)) {
            boardRepairSites.add(tempCoordinates);
        } else if (BoardElement.PUSHERS.contains(elem)) {
            if (BoardElement.PUSHERS_EVEN.contains(elem))
                pushersEven.add(new ConveyorStraight(elem.getDirection(), 1, tempCoordinates));
            else if (BoardElement.PUSHERS_ODD.contains(elem))
                pushersOdd.add(new ConveyorStraight(elem.getDirection(), 1, tempCoordinates));
        } else if (BoardElement.STARTING_POINTS.contains(elem)) {
            initializeStartingPoint(elem, x, y);
        }
    }

    //TODO: consider renaming methods
    private void doMoveAccordingToCardType(IRobot robot, ICard inputCard) {
        if (inputCard.getType() == 1) { //Movement Cards
            relativeMove(robot, (ICardMovement) inputCard);
        } else if (inputCard.getType() == 2) { // Rotation Cards
            rotationMove(robot, (ICardRotation) inputCard);
        }
    }

    /**
     * Eirik
     */
    @Override
    public void progressPhase() {
        /**
         * Phase order:
         * 1: Reveal Program Cards
         * 2: Move robots according to priority
         * 3: Board Elements Move
         * TODO 4: Lasers Fire
         * TODO 5: Touch checkpoints
         */
        switch (phaseState) {
            case REVEAL_CARDS:
                //Flips a card in each of the registers
                for (IProgramRegister register : allProgramRegisters) {
                    register.turnACard(phaseNumber);
                }
                phaseState = phaseState.nextState();
                break;

            case MAKE_MOVEMENT_PRIORITY_LIST:
            //Makes a new list of all of the registers then in turn does the move of the highest priority then removes that register from the list
            //TODO: needs to be tested, not sure if it works as intended to be honest
            ArrayList<IProgramRegister> programRegistersToSort = new ArrayList<>(allProgramRegisters);
            for (int i = 0; i < allProgramRegisters.size(); i++) {
                IProgramRegister currentHighestPriority = null;
                int highestPriorityIndex = 0;
                for (int j = 1; j < programRegistersToSort.size(); j++) {
                    int highestPrioritySoFar = programRegistersToSort.get(highestPriorityIndex).getActiveCardInPosition(phaseNumber).getPriority();
                    int newPriority = programRegistersToSort.get(j).getActiveCardInPosition(phaseNumber).getPriority();
                    if (newPriority > highestPrioritySoFar)
                        highestPriorityIndex = j;
                    currentHighestPriority = programRegistersToSort.get(highestPriorityIndex);
                }
                robotsToMove.add(currentHighestPriority);
            }
            robotsToMove = programRegistersToSort;
            phaseState = phaseState.nextState();
            break;

            case MOVE_ROBOTS:

                    if (robotsToMove.get(0).isPoweredDown())
                        robotsToMove.remove(0);
                    else {
                        doMoveAccordingToCardType(robotsToMove.get(0).getRobot(), robotsToMove.get(0).getActiveCardInPosition(phaseNumber));
                        robotsToMove.remove(0);
                    }

                if (robotsToMove.isEmpty())
                    phaseState = phaseState.nextState();
                break;

                //TODO: should be expanded to have all boardElements
            case ACTIVATE_BOARD_ELEMENTS:
                activateBoardElements();
                phaseState = phaseState.nextState();
                break;
            case FIRE_LASERS:
                activateLasers();
                activateRobotLasers();
                phaseState = phaseState.nextState();
                break;
            case ACTIVATE_CHECKPOINTS:
                activateFlag();
                phaseState = phaseState.nextState();
                phaseNumber++;
                break;

        }
    }

    //A collection method to simplify activation
    public void activateBoardElements() {
        activateConveyorBelts(true);
        activateConveyorBelts(false);
        //activatePushers(); TODO: Make this method
        activateGears();

    }

    @Override
    public void progressRound(GameGFX graphicsInterface) {
        switch (gameState) {
            case SETUP:
                progressGameState(); //TODO: should be changed later
                break;
            case DEALING_CARDS:
                shuffleDeck(); //Shuffles the deck at the start of each round
                dealCards();
                progressGameState();
                graphicsInterface.flipShowCard();

                for(IProgramRegister register : allProgramRegisters) {
                    if(!register.isPlayerHuman())
                        AI.activateCards(register);
                }
                break;
            case CHOOSING_CARDS:
                int playersNotReady = getNumberOfPlayersNotReady();
                if(playersNotReady == 0) {
                    graphicsInterface.flipShowCard();
                    discardAllUnusedCards();
                    progressGameState();
                    if(checkIfGameHasHumanPlayers())
                        graphicsInterface.printTextToDefaultPosition("Please chose if you want to power down by pressing y/n", 2f, 5);
                } else {
                    graphicsInterface.printTextToDefaultPosition("Everyone is not ready", 3f, 5);
                }

                if(playersNotReady == 1 && allProgramRegisters.size() != 1)
                    startTimer();
                break;
            case ANNOUNCING_POWER_DOWN:
                for(IProgramRegister register : allProgramRegisters) {
                    if(!register.isPlayerHuman())
                        AI.decideIfPowerDown(register);
                }
                if(!checkIfGameHasHumanPlayers())
                    progressGameState();
                break;
            case EXECUTING_PHASES:
                if(phaseNumber == (GameRuleConstants.NUMBER_OF_PHASES_IN_ROUND.getValue())) {
                    phaseNumber = 0;
                    progressGameState();
                } else {
                    System.out.println("Phase state: " + (phaseState)+ ", phaseNumber:" + (phaseNumber+ 1));
                    progressPhase();
                }
                break;
            case END_OF_ROUND_CLEANUP:
                    doRepairs();
                    for(IProgramRegister register:allProgramRegisters) {
                        register.powerOn();
                        if (register.isDestroyed())
                            register.restoreRobot(this);
                    }
                    progressGameState();
                break;
        }
    }

    public void powerDownRobot(IProgramRegister register, boolean powerDown) {
        if(powerDown)register.powerDown();
    }

    /**
     * Checks how many players have readied up
     * @return number of players not ready
     */
    private int getNumberOfPlayersNotReady() {
        int notReadyCounter = 0;
        for(IProgramRegister register : allProgramRegisters) {
            if (!register.isCardSlotsFilled()) {
                notReadyCounter++;
            }
        }
        return notReadyCounter;
    }

    private void startTimer() {
        //TODO: Make this
    }

    /**
     * Marius
     *
     * @return
     */
    public ArrayList<Event> makeEventList() {
        return null;
    }
    //TODO:
    //Roboter beveger seg.
    //MAP...
    //liste over eventer i fasen

    /**
     * Marius
     *
     * @param listOfEvents
     */
    public Event readEvents(ArrayList<Event> listOfEvents) {
        //TODO:
        //
        return null;

    }

    /**
     * Eirik
     *
     * @param programRegister to be repaired
     */
    public void repair(IProgramRegister programRegister) {
        programRegister.changeDamage(-1);
    }

    /**
     * @param robot to update the backup of
     */
    public void updateBackUp(IRobot robot) {
        int[] backUp = new int[2];
        backUp = robot.getPosition();
        robot.setBackup(backUp);
    }

    private void discardAllUnusedCards() {
        for(IProgramRegister register : allProgramRegisters) {
            register.discardUnusedCards(this);
        }
    }

    //TODO: Can this handle locked-in cards?
    @Override
    public void dealCards() {
        rLaserIsActive = false;
        for (IProgramRegister register : allProgramRegisters) {
            register.discardAllCards(this); //Removes any cards, just in case there are some

            final int numberOfCardsToDeal = GameRuleConstants.MAX_CARDS_IN_REGISTER.getValue() - register.getDamage();
            ArrayList<ICard> temp = new ArrayList<>(deck.subList(0, numberOfCardsToDeal));
            deck.removeAll(temp); //Removes the cards from the deck
            register.setAvailableCards(temp);
        }
    }


    /**
     * Activates all of the gears on the board & applies effects to robots on gears
     */
    public void activateGears() {
        for (IConveyorTurn gear : gears) {
            for (IProgramRegister register : allProgramRegisters) {
                if (Arrays.equals(gear.getPosition(), register.getRobot().getPosition())) {
                    register.getRobot().rotate(gear.getTurnDirection());
                }
            }
            //gear.rotate() Would be cool if we actually rotated the gears in GFX to show that they're activated
        }
    }

    /**
     * @param cards
     */
    public void removeCard(boolean[] cards) {

    }

    /**
     * Creates a deck of deck of all of the "simple" types, stored in the ArrayList deck
     * As of right now it's hard-coded, but in the future we should probably make it more dynamic
     */
    private void createDeck() {
        //Creates the cards "Backup", "Move 1", "Move 2", "Move 3"
        createMovementCards(6, 10, 430, -1);
        createMovementCards(18, 10, 490, 1);
        createMovementCards(12, 10, 670, 2);
        createMovementCards(6, 10, 790, 3);

        //Creates the cards "U-Turn", "Rotate Right", "Rotate Left"
        createRotationCards(6, 10, 10, true, 2);
        createRotationCards(18, 20, 80, true, 1);
        createRotationCards(18, 20, 70, false, 1);
    }

    /**
     * Creates a set of movement cards based on the input given
     *
     * @param cardNum       The number of cards to create
     * @param interval      The interval between each card's priority
     * @param intervalStart The starting value of the priority
     * @param moveValue     The amount of movement of the card
     */
    private void createMovementCards(int cardNum, int interval, int intervalStart, int moveValue) {
        for (int i = 0; i < cardNum; i++) {
            deck.add(new MovementCard(intervalStart + i * interval, moveValue));
        }
    }

    /**
     * Creates a set of rotation cards based on the input given
     *
     * @param cardNum           The number of cards to create
     * @param interval          The interval between each card's priority
     * @param intervalStart     The starting value of the priority
     * @param rotationDirection The direction of the rotation (TRUE = right, FALE = left)
     * @param rotationValue     The amount of rotation to be performed
     */
    private void createRotationCards(int cardNum, int interval, int intervalStart, boolean rotationDirection, int rotationValue) {
        for (int i = 0; i < cardNum; i++) {
            deck.add(new RotationCard(intervalStart + i * interval, rotationDirection, rotationValue));

        }
    }

    /**
     * Shuffles the deck of deck
     */
    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    @Override
    public void addCardToDeck(ICard card) {
        deck.add(card);
    }

    @Override
    public void activateFlag() {
            for (IProgramRegister register : allProgramRegisters) {
                if(checkIfOnFlag(register.getRobot())) {
                    for (IFlag flag : boardFlags) {
                        if (register.getFlagCounter() == flag.getFlagId()) { //checks if the robot hits flags in right order.
                            System.out.println();
                            register.increaseFlagCounter(); //updates the robot programming card.
                            updateBackUp(register.getRobot()); //places a new backup
                            break;
                        }
                    }
                }
            }
    }

    public IProgramRegister checkIfContainsRobot(int[] coordinate) {
        for(IProgramRegister register : allProgramRegisters){
            if(register.getRobot().getPosition()[0] == coordinate[0]
                    && register.getRobot().getPosition()[1] == coordinate[1] && !register.isDestroyed()){
                return register;
            }
        }
        return null;
    }

    @Override
    public void activateConveyorBelts(boolean activateOnlyExpressConveyors) {
        int numberOfRobots = allProgramRegisters.size();

        boolean[] isMoved = new boolean[numberOfRobots]; //Tracks if a robot will be moved by a conveyor
        int[][] predictedPositions = new int[numberOfRobots][2]; //Used to store "predicted" positions
        //Stores the conveyorBelts a robot will interact with, first is the one it's on, the second is the one it will move onto
        IConveyorBelt[][] conveyorsWithRobot = new IConveyorBelt[numberOfRobots][2];

        for(int i = 0; i < numberOfRobots; i++) {
            //Initiates array values with the assumption that the robot isn't on a conveyor
            isMoved[i] = false;
            conveyorsWithRobot[i][0] = null;
            conveyorsWithRobot[i][1] = null;
            int[] robotPos = new int[2];
            if(allProgramRegisters.get(i).isDestroyed()) {
                //Sets the position of a destroyed robot to be outside of the board to avoid incorrect
                robotPos[0] = Integer.MIN_VALUE;
                robotPos[1] = Integer.MIN_VALUE;
            } else {
                robotPos = allProgramRegisters.get(i).getRobot().getPosition();
            }
            predictedPositions[i] = robotPos.clone();
            for(IConveyorBelt conveyorBelt : conveyorBelts) {
                if((conveyorBelt.isExpressType() == activateOnlyExpressConveyors) || !activateOnlyExpressConveyors) {
                    //Goes through all of the conveyor belts and calculates the predicted position & stores relevant conveyors
                    if (Arrays.equals(robotPos, conveyorBelt.getPosition())) {
                        predictedPositions[i] = getPositionInDirection(robotPos, conveyorBelt.getDirection());

                        conveyorsWithRobot[i][0] = conveyorBelt;
                        conveyorsWithRobot[i][1] = getConveyorInPosition(predictedPositions[i]);

                        isMoved[i] = true;
                        break; //Breaks to avoid running through the rest of the conveyor list
                    }
                }
            }
        }

        boolean[] canMove = new boolean[numberOfRobots]; //Stores whether a conveyor can move a robot or not
        //Not the most efficient way to compare positions, but considering the small data size it should be fine
        for(int i = 0; i < predictedPositions.length; i++) { //checks if there is any overlap in predicted positions
            for(int j = i + 1; j < predictedPositions.length; j++) {
                if(Arrays.equals(predictedPositions[i], predictedPositions[j])) {
                    canMove[i] = false;
                    canMove[j] = false;
                }
            }
        }

        for(int i = 0; i < numberOfRobots; i++) {
            canMove[i] = true;
            IRobot robot = allProgramRegisters.get(i).getRobot();
            if(isMoved[i] && canMove[i]) {
                //Checks is the conveyor the robot is moved onto is of the turn type (this includes the into types)
                if(conveyorsWithRobot[i][1] instanceof IConveyorTurn) {
                    IConveyorTurn conveyorBelt = (IConveyorTurn) conveyorsWithRobot[i][1];
                    robot.rotate(conveyorBelt.getTurnDirection());
                } else if(conveyorsWithRobot[i][1] instanceof IConveyorCombine) {
                    IConveyorCombine conveyorBelt = (IConveyorCombine) conveyorsWithRobot[i][1];
                    robot.rotate(conveyorBelt.getRotationDirectionFromPreviousPosition(robot.getPosition()));
                }

                if(!Arrays.equals(robot.getPosition(), predictedPositions[i])) {
                    relativeMoveStraight(robot, conveyorsWithRobot[i][0].getDirection(), 1);
                }
                //absoluteMove(robot, predictedPositions[i]); OLD VERSION, KEEP IN CASE THE ABOVE DOESN'T WORK PROPERLY
            }
        }
    }

    /**
     * A simple help-method that returns a conveyor belt in the given position, or null if none is found
     * @param position the position to check
     * @return IConveyorBelt object
     */
    private IConveyorBelt getConveyorInPosition(int[] position) {
        for(IConveyorBelt conveyorBelt : conveyorBelts) {
            if(Arrays.equals(conveyorBelt.getPosition(), position))
                return conveyorBelt;
        }

        return null;
    }

    //TODO: complete or remove
    @Override
    public boolean canMove(int[] startCoordinates, int[] destinationCoordinates) {
        //Checks to see if the positions are adjacent
        int deltaX = destinationCoordinates[0] - startCoordinates[0];
        int deltaY = destinationCoordinates[1] - startCoordinates[1];
        if (deltaX > 1 || deltaX < -1 || deltaY > 1 || deltaY < -1)
            throw new IllegalArgumentException("The positions are not adjacent");


        return true;
    }

    @Override
    public boolean winCheck() {
        for(IProgramRegister register : allProgramRegisters) {
            if (register.getFlagCounter() == boardFlags.size() - 1) {
                System.out.println("Winner! The robot has registered all of its flags.");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean gameOver() {
        if (winCheck()) {
            System.out.println("Game over");
            //System.exit(0);
            return true;
        }
        return false;
    }
    public PhaseState getPhaseState(){
        return phaseState;
    }
}


