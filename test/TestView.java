import java.util.HashMap;
import java.util.ArrayList;

/**
 * Different test to check the View Class
 */
public class TestView {

    /**
     *Test if the initialisation is correct
     */
    public void testInitView() throws Exception {
        int[] coordinates = {0, 0};
        int[] dimensions = {50, 50};
        int[] size = {2, 4};
        Fish clownFish = new Fish("Clown Fish", coordinates, size, "RandomPathWay");
        Fish chouchouFish = new Fish("Chouchou Fish", coordinates, size, "RandomPathWay");
        Fish flowerFish = new Fish("Flower Fish", coordinates, size, "RandomPathWay");
        HashMap<String, Fish> fishes = new HashMap<String, Fish>();
        fishes.put("Clown Fish", clownFish);
        fishes.put("chouchou Fish", chouchouFish);
        fishes.put("flower Fish", flowerFish);
        try {
            View view = new View("N1", coordinates, dimensions);
        } catch (ViewException e) {
            assert false : "Exception Launched";
        }
    }

    /**
     *Test if the coordinates is negative
     */
    public void testInitViewNegativeCoordinates() throws Exception {
        int[] coordinates = {-10, 0};
        int[] fishCoordinates = {0, 0};
        int[] dimensions = {50, 50};
        int[] size = {2, 4};
        Fish clownFish = new Fish("Clown Fish", fishCoordinates, size, "RandomPathWay");
        Fish chouchouFish = new Fish("Chouchou Fish", fishCoordinates, size, "RandomPathWay");
        Fish flowerFish = new Fish("Flower Fish", fishCoordinates, size, "RandomPathWay");
        try {
            View view = new View("N1", coordinates, dimensions);
            view.addFish(flowerFish);
            view.addFish(clownFish);
            view.addFish(chouchouFish);
        } catch (ViewException e) {
            return;
        }
        assert false : "Exception not launched";
    }

    /**
     *Test if the coordinates is over big
     */
    public void testInitViewOverBigCoordinates() throws Exception {
        int[] coordinates = {120, 0};
        int[] fishCoordinates = {0, 0};
        int[] dimensions = {50, 50};
        int[] size = {2, 4};
        Fish clownFish = new Fish("Clown Fish", fishCoordinates, size, "RandomPathWay");
        Fish chouchouFish = new Fish("Chouchou Fish", fishCoordinates, size, "RandomPathWay");
        Fish flowerFish = new Fish("Flower Fish", fishCoordinates, size, "RandomPathWay");
        try {
            View view = new View("N1", coordinates, dimensions);
            view.addFish(clownFish);
            view.addFish(chouchouFish);
            view.addFish(flowerFish);
        } catch (ViewException e) {
            return;
        }
        assert false : "Exception not launched";
    }

    /**
     *Test if the coordinates with the dimension is over big
     */
    public void testInitViewOverBigCoordinatesPlusDimension() throws Exception {
        int[] coordinates = {70, 0};
        int[] fishCoordinates = {0, 0};
        int[] dimensions = {50, 50};
        int[] size = {2, 4};
        Fish clownFish = new Fish("Clown Fish", fishCoordinates, size, "RandomPathWay");
        Fish chouchouFish = new Fish("Chouchou Fish", fishCoordinates, size, "RandomPathWay");
        Fish flowerFish = new Fish("Flower Fish", fishCoordinates, size, "RandomPathWay");
    
        try {
            View view = new View("N1", coordinates, dimensions);
            view.addFish(clownFish);
            view.addFish(chouchouFish);
            view.addFish(flowerFish);
        } catch (ViewException e) {
            return;
        }
        assert false : "Exception not launched";
    }

    /**
    *Test the getId method 
    */
    public void testGetId() {
        try {
            View view = new View("N1", new int[]{0, 0}, new int[]{100, 100});
            assert "N1" == view.getId() : "Different id";
        } catch (ViewException e) {
            assert false : "Exception Launched";
        }
    }

    /**
    *Test the getDimensions method
    */
    public void testGetDimensions() {
        try {
            View view = new View("N1", new int[]{0, 0}, new int[]{100, 90});
            int[] dimensions = view.getDimensions();
            assert 100 == dimensions[0] : "Bad dimension[0]";
            assert 90 == dimensions[1] : "Bad dimension[1]";
        } catch (ViewException e) {
            assert false : "Exception Launched";
        }
    }

    /**
    *Test the getPosition method
    */
    public void testGetPosition() {
        try {
            View view = new View("N1", new int[]{10, 20}, new int[]{90, 80});
            int[] position = view.getPosition();
            assert 10 == position[0] : "Bad position[0]";
            assert 20 == position[1] : "Bad position[0]";
        } catch (ViewException e) {
            assert false : "Exception Launched";
        }
    }

    /**
    *Test addFish method correct case
    */
    public void testAddFishCorrect() {
        int[] fishCoordinates = {15, 25};
        int[] size = {2, 4};
        try {
            Fish clownFish = new Fish("clownFish", fishCoordinates, size, "RandomPathWay");
            Fish chouchouFish = new Fish("chouchouFish", fishCoordinates, size, "RandomPathWay");
            Fish flowerFish = new Fish("flowerFish", fishCoordinates, size, "RandomPathWay");
            View view = new View("N1", new int[]{10, 20}, new int[]{90, 80});
            view.addFish(flowerFish);
            view.addFish(chouchouFish);
            view.addFish(clownFish);
            assert view.getFishes().size() == 3 : "Bad numbers of fishes";
            assert view.getFishes().get("flowerFish") == flowerFish : "flowerFish isn't added";
            assert view.getFishes().get("chouchouFish") == chouchouFish : "chouchouFish isn't added";
            assert view.getFishes().get("clownFish") == clownFish : "clownFish isn't added";
        } catch (Exception e) {
            assert false : e.getMessage();
        }
    }

    /**
    *Test addFish but with a fish wich isn't in the view
    */
    public void testAddFishNotInTheView() {
        int[] fishCoordinates = {50, 50};
        int[] size = {2, 4};
        try {
            Fish flowerFish = new Fish("flowerFish", fishCoordinates, size, "RandomPathWay");
            View view = new View("N1", new int[]{10, 20}, new int[]{5, 5});
            view.addFish(flowerFish);
         } catch (FishViewException e) {
            return;
         } catch (Exception e2) {
            assert false : "Exception Launched";
        }
        assert false : "FishViewException not launched";
    }

    /**
    *Test addFish but with a fish wich is in the view
    */
    public void testAddFishAlreadyInTheView() {
        int[] fishCoordinates = {50, 50};
        int[] size = {2, 4};
        try {
            Fish flowerFish = new Fish("flowerFish", fishCoordinates, size, "RandomPathWay");
            HashMap<String, Fish> fishes = new HashMap<String, Fish>();
            View view = new View("N1", new int[]{10, 20}, new int[]{90, 80});
            view.addFish(flowerFish);
            view.addFish(flowerFish);
         } catch (FishViewException e) {
            return;
         } catch (Exception e2) {
            assert false : "Exception Launched";
        }
        assert false : "FishViewException not launched";
    }

    /**
    *Test removeFish a fish from the view
    */
    public void testRemoveFishInTheView() {
        int[] fishCoordinates = {50, 50};
        int[] size = {2, 4};
        try {
            Fish flowerFish = new Fish("flowerFish", fishCoordinates, size, "RandomPathWay");
            View view = new View("N1", new int[]{10, 20}, new int[]{90, 80});
            view.addFish(flowerFish);
            view.removeFish(flowerFish);
         } catch (Exception e2) {
            assert false : "Exception Launched";
        }
    }

    /**
    *Test removeFish but with a fish which isn't in the view
    */
    public void testRemoveFishNotInTheView() {
        int[] fishCoordinates = {50, 50};
        int[] size = {2, 4};
        try {
            Fish clownFish = new Fish("clownFish", fishCoordinates, size, "RandomPathWay");
            Fish flowerFish = new Fish("flowerFish", fishCoordinates, size, "RandomPathWay");
            View view = new View("N1", new int[]{10, 20}, new int[]{90, 80});
            view.addFish(flowerFish);
            view.removeFish(clownFish);
         } catch (FishViewException e) {
            return;
         } catch (Exception e2) {
            assert false : "Exception Launched";
        }
        assert false : "FishViewException not launched";
    }

    /**
    *Test updateCoordinates with a fish in the view
    */
    public void testupdateCoordinatesFish() {
        int[] fishCoordinates = {50, 50};
        int[] size = {2, 4};
        try {
            Fish clownFish = new Fish("clownFish", fishCoordinates, size, "RandomPathWay");
            Fish flowerFish = new Fish("flowerFish", fishCoordinates, size, "RandomPathWay");
            View view = new View("N1", new int[]{10, 20}, new int[]{90, 80});
            view.addFish(flowerFish);
            view.addFish(clownFish);
            clownFish.setPosition(new int[]{12, 28});
            view.updateCoordinatesFish(clownFish);
         } catch (Exception e2) {
            assert false : "Exception Launched";
        }
    }

    /**
    *Test updateCoordinates with serveral fishes in the view
    */
    public void testupdateCoordinatesFishes() {
        int[] fishCoordinates = {50, 50};
        int[] size = {2, 4};
        try {
            Fish clownFish = new Fish("clownFish", fishCoordinates, size, "RandomPathWay");
            Fish flowerFish = new Fish("flowerFish", fishCoordinates, size, "RandomPathWay");
            View view = new View("N1", new int[]{10, 20}, new int[]{90, 80});
            view.addFish(flowerFish);
            view.addFish(clownFish);
            clownFish.setPosition(new int[]{12, 28});
            flowerFish.setPosition(new int[]{12, 56});
            ArrayList<Fish> fishesArray = new ArrayList<Fish>();
            fishesArray.add(flowerFish);
            fishesArray.add(clownFish);
            view.updateCoordinatesListFishes(fishesArray);
         } catch (Exception e2) {
            assert false : "Exception Launched";
        }
    }
}
