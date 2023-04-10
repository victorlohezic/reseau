import java.util.ArrayList;

/**
 * Different test to check the Fish Class
 */
public class TestFish {

    /**
     *Test if the getName return the good name of a fish
     */
    public void testGetNameFish() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        assert testFish.getName() == name : "The name of the fish isn't correct during the initialisation.";
    } 

    /**
     *Test if the getPosition return the good position of a fish
     */
    public void testGetPostionFish() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        assert testFish.getPosition()[0] == coordinates[0] : "x : coordinate isn't correct";
        assert testFish.getPosition()[1] == coordinates[1] : "y : coordinate isn't correct";
    } 

    /**
     *Test if the getMobility works
     */
    public void testGetMobility() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        assert testFish.getMobility() == "RandomPathWay" : "mobility must be RandomPathWay";
    } 

    /**
     *Test if negative position generates an error
     */
    public void testInitNegativePositionFish() {
        int[] coordinates = {-10, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        try {
            Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        } catch(FishException e) {
            return;
        }
        assert false: "Exception not lauched";
    } 

        /**
     *Test if too big position generates an error
     */
    public void testInitOverBigPositionFish() {
        int[] coordinates = {0, 120};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        try {
            Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        } catch(FishException e) {
            return;
        }
        assert false: "Exception not lauched";
    } 


    /**
     *Test if negative position generates an error
     */
    public void testSetNegativePositionFish() {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        try {
            Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
            int[] newDimensions = {-5, 2};
            testFish.setPosition(newDimensions);
        } catch(FishException e) {
            return;
        }
        assert false: "Exception not lauched";
    } 

        /**
     *Test if negative size generates an error
     */
    public void testInitNegativeSizeFish() {
        int[] coordinates = {0, 0};
        int[] dimensions = {-5, 2};
        String name = "ChouchouALaCreme";
        try {
            Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        } catch(FishException e) {
            return;
        }
        assert false: "Exception not lauched";
    }

    /**
     *Test if too big position generates an error
     */
    public void testSetOverBigPositionFish() {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        try {
            Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
            int[] newDimensions = {105, 2};
            testFish.setPosition(newDimensions);
        } catch(FishException e) {
            return;
        }
        assert false: "Exception not lauched";
    }

    /**
     *Test if the getSize return the good dimensions of a fish
     */
    public void testGetSizeFish() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        assert testFish.getSize()[0] == dimensions[0] : "x : coordinate isn't correct";
        assert testFish.getSize()[1] == dimensions[1] : "y : coordinate isn't correct";
    } 

    /**
     *Test if the getState return the good state STOPPED
     */
    public void testGetState() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        assert testFish.getState() == State.STOPPED : "Initial state is wrong must be STOPPED";
    } 

    /**
     *Test if the setState change in the good state 
     */
    public void testSetState() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        testFish.setState(State.STARTED);
        assert testFish.getState() == State.STARTED : "The new state is wrong, must be STARTED";
    } 

    /**
     *Test setPositionsAndTimes and getPositionsAndTimes
     */
    public void testSetPositionsAndTimes() throws Exception {
        int[] coordinates = {0, 0};
        int[] coordinatesTime2 = {10, 0, 2};
        int[] coordinatesTime3 = {20, 0, 3};
        int[] coordinatesTime4 = {20, 30, 5};
        int[] dimensions = {5, 2};
        ArrayList<int[]> newCoordinatesAndTimes= new ArrayList<int[]>();
        newCoordinatesAndTimes.add(coordinatesTime2);
        newCoordinatesAndTimes.add(coordinatesTime3);
        newCoordinatesAndTimes.add(coordinatesTime4);
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        testFish.setPositionsAndTimes(newCoordinatesAndTimes);
        assert testFish.getPositionsAndTimes() ==  newCoordinatesAndTimes : "The attribute has a different adress";
    } 

    /**
     *Test setPositionsAndTimes and getPositionsAndTimes
     */
    public void testDecrementTime() throws Exception {
        int[] coordinates = {0, 0};
        int[] coordinatesTime2 = {10, 0, 0};
        int[] coordinatesTime3 = {20, 0, 3};
        int[] coordinatesTime4 = {20, 30, 5};
        int[] dimensions = {5, 2};
        ArrayList<int[]> newCoordinatesAndTimes= new ArrayList<int[]>();
        newCoordinatesAndTimes.add(coordinatesTime2);
        newCoordinatesAndTimes.add(coordinatesTime3);
        newCoordinatesAndTimes.add(coordinatesTime4);
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        testFish.setPositionsAndTimes(newCoordinatesAndTimes);
        testFish.decrementTime();
        assert testFish.getPosition()[0] == coordinatesTime2[0] : "x : coordinate isn't correct";
        assert testFish.getPosition()[1] == coordinatesTime2[1] : "y : coordinate isn't correct";
        System.out.println(testFish.getPositionsAndTimes().get(0)[1]);
        assert testFish.getPositionsAndTimes().get(0) == coordinatesTime3 : "First element of PositionsAndTimes is wrong";
        assert testFish.getPositionsAndTimes().get(1) == coordinatesTime4 : "First element of PositionsAndTimes is wrong";
        assert testFish.getPositionsAndTimes().size() == 2 : "Size of PositionsAndTimes is wrong";
        testFish.decrementTime();
        assert testFish.getPosition()[0] == coordinatesTime2[0] : "x : coordinate isn't correct";
        assert testFish.getPosition()[1] == coordinatesTime2[1] : "y : coordinate isn't correct";
    } 

     /**
     *Test setPositionsAndTimes and getPositionsAndTimes
     */
    public void testAddPositionAndTime() throws Exception {
        int[] coordinates = {0, 0};
        int[] coordinatesTime2 = {10, 0, 1};
        int[] coordinatesTime3 = {20, 0, 3};
        int[] coordinatesTime4 = {20, 30, 5};
        int[] dimensions = {5, 2};
        ArrayList<int[]> newCoordinatesAndTimes= new ArrayList<int[]>();
        newCoordinatesAndTimes.add(coordinatesTime2);
        newCoordinatesAndTimes.add(coordinatesTime3);
        newCoordinatesAndTimes.add(coordinatesTime4);
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        testFish.setPositionsAndTimes(newCoordinatesAndTimes);
        testFish.addPositionAndTime(new int[]{15, 15, 0});
        assert testFish.getPosition()[0] == 15 : "x : coordinate isn't correct";
        assert testFish.getPosition()[1] == 15 : "y : coordinate isn't correct";
        int[] newCoordinatesTime = {30, 32, 2};
        testFish.addPositionAndTime(newCoordinatesTime);
        assert testFish.getPositionsAndTimes().size() == 4 : "The size isn't good";
        assert testFish.getPositionsAndTimes().get(1) == newCoordinatesTime : "Coordinates isn't added in the good position in the ArrayList";
        int[] newCoordinatesTime2 = {67, 48, 3};
        testFish.addPositionAndTime(newCoordinatesTime2);
        assert testFish.getPositionsAndTimes().get(2)[0] == 67 : "x : coordinate isn't correct";
        assert testFish.getPositionsAndTimes().get(2)[1] == 48 : "y : coordinate isn't correct";
        testFish.addPositionAndTime(newCoordinatesTime);
    } 
}
