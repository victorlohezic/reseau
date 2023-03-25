/**
 * Different test to check the Fish Class
 */
public class TestFish {

    /**
     *Test if the getName return the good name of a fish
     */
    public void testGetNameFish() {
        int[] coordinates = {0, 0};
        int[] dimensions = {5, 2};
        String name = "ChouchouALaCreme";
        Fish testFish = new Fish(name, coordinates, dimensions);
        assert testFish.getName() == name : "The name of the fish isn't correct during the initialisation.";
    } 
}
