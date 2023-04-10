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
     *Test if the getNewPosition return the good new position of a fish
     */
    public void testGetNewPostionFish() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        assert testFish.getNewPosition()[0] == coordinates[0] : "x : coordinate isn't correct";
        assert testFish.getNewPosition()[1] == coordinates[1] : "y : coordinate isn't correct";
    } 

    /**
     *Test if the getNewPosition return the good new position of a fish
     */
    public void testSetNewPostionFish() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
        testFish.setNewPosition(new int[]{5, 5});
        assert testFish.getNewPosition()[0] == 5 : "x : coordinate isn't correct";
        assert testFish.getNewPosition()[1] == 5 : "y : coordinate isn't correct";
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
     *Test if negative new position generates an error
     */
    public void testSetNegativeNewPositionFish() {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        try {
            Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
            int[] newDimensions = {-5, 2};
            testFish.setNewPosition(newDimensions);
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
     *Test if too big position generates an error
     */
    public void testSetOverBigNewPositionFish() {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        try {
            Fish testFish = new Fish(name, coordinates, dimensions, "RandomPathWay");
            int[] newDimensions = {105, 2};
            testFish.setNewPosition(newDimensions);
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
}
