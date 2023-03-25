import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

/**
 * View of the aquarium
 */
public class View {

    private int id; 
    private HashMap<String, Fish> fishes = new HashMap<>(); 
    private int[] dimensions = new int[2];
    private int[] coordinates = new int[2];

    public View(int id, HashMap<String, Fish> fishes, int[] coordinates) {
        this.id = id;
        this.fishes = fishes; 
        this.dimensions[0] = dimensions[0];
        this.dimensions[1] = dimensions[1];
        this.coordinates[0] = coordinates[0];
        this.coordinates[1] = coordinates[1];
    }

    /**
     * Return the identity of the view
     * @return id int
     */
    private int getId() {
        return id;
    }

    /**
     * Return the size of the view
     * @return dimensions int[2]
     */
    private int[] getDimensions() {
        return dimensions;
    }

    /**
     * Return the position of the view
     * @return position int[2]
     */
    private int[] getPosition() {
        return coordinates;
    }

    /**
     * Add a fish to the ArrayList fishes
     * @param fish Fish
     * @throws FishViewException
     */
    public void addFish(Fish fish) throws FishViewException{
        if (isInView(fish)) {
            if (fishes.containsKey(fish.getName())) {
                fishes.put(fish.getName(), fish);
            } else {
                throw new FishViewException("The fish is already in the view");
            }
        } else {
            throw new FishViewException("The position of the fish isn't in the view");
        }
    }

    /**
     * Remove a fish to the ArrayList fishes
     * @param fish Fish
     * @throws FishViewException
     */
    public void removeFish(Fish fish) throws FishViewException{
        if (fishes.containsKey(fish.getName())) {
            fishes.remove(fish.getName());
        } else {
            throw new FishViewException("The fish isn't in the view");
        }
    }

    /**
     * Update the coordinates of the fish in the view associated to @fish 
     * @param fish Fish
     * @throws FishViewException
     */
    public void updateCoordinatesFish(Fish fish) throws FishViewException {
        if (isInView(fish)) {
            if (fishes.containsKey(fish.getName())) {
                fishes.get(fish.getName()).setPosition(fish.getPosition());
            } else {
                throw new FishViewException("The fish isn't in the view");
            }
        } else {
            throw new FishViewException("The position of the fish isn't in the view");
        }
    }
    
    /**
     * Update the coordinates of the fishes in the view associated to @fishesMoved 
     * @param fish Fish
     * @throws FishViewException
     */
    public void updateCoordinatesListFishes(ArrayList<Fish> fishesMoved) throws FishViewException {
        ListIterator<Fish> li = fishesMoved.listIterator();
        while (li.hasNext()) {
            Fish currentFish = li.next();
            updateCoordinatesFish(currentFish);
        }
    }

    /**
     * Check if the position of a fish is in the view
     */
    private boolean isInView(Fish fish) {
        int[] position = fish.getPosition();
        for (int i = 0; i < 2; ++i) {
            if (position[i] < coordinates[i] || position[i] > coordinates[i] + dimensions[i]) {
                return false;
            }
        }
        return true;
    }
}
