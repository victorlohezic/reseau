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
        Fish testFish = new Fish(name, coordinates, dimensions);
        assert testFish.getName() == name : "The name of the fish isn't correct during the initialisation.";
    } 

    /**
     *Test if the getPosition return the good position of a fish
     */
    public void testGetPostionFish() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions);
        assert testFish.getPosition()[0] == coordinates[0] : "x : coordinate isn't correct";
        assert testFish.getPosition()[1] == coordinates[1] : "y : coordinate isn't correct";
    } 


    /**
     *Test if negative position generates an error
     */
    public void testInitNegativePositionFish() {
        int[] coordinates = {-10, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        try {
            Fish testFish = new Fish(name, coordinates, dimensions);
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
            Fish testFish = new Fish(name, coordinates, dimensions);
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
            Fish testFish = new Fish(name, coordinates, dimensions);
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
            Fish testFish = new Fish(name, coordinates, dimensions);
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
            Fish testFish = new Fish(name, coordinates, dimensions);
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
        Fish testFish = new Fish(name, coordinates, dimensions);
        assert testFish.getSize()[0] == dimensions[0] : "x : coordinate isn't correct";
        assert testFish.getSize()[1] == dimensions[1] : "y : coordinate isn't correct";
    } 
}