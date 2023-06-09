import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Listener extends Thread {
    private String contentOneTime;
    private String getFishes;
    private BufferedReader input;
    private Object mutex = new Object();
    private Object mutexFishes = new Object();
    private boolean running = true;
    private Prompt prompt;
    private View view;
    private Logging logging;

    public Listener(BufferedReader in, Prompt prompt, View view, Logging logging) {
        this.input = in;
        this.prompt = prompt;
        this.view = view;
        this.logging = logging;
    }

    public void run() {
        try {
            String result;
            while (running) {
                result = input.readLine();
                if (result.split(" ")[0].equals("list") == false){
                    synchronized (mutex) {
                        contentOneTime = result;
                        mutex.notifyAll();
                    }
                } else {
                    synchronized (mutexFishes) {
                        getFishes = result;
                        mutexFishes.notifyAll();
                    }
                    updateView(result.replaceAll("\\[", "").replaceAll("x", " ").replaceAll("\\]", "").replaceAll("x", " ").replaceAll(",", " "));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return a string from the server for commands which doesn't wait a list of Fish
     * @return String 
     * @throws InterruptedException
     */
    public String readLine() throws InterruptedException {
        synchronized (mutex) {
            while (contentOneTime == null) {
                mutex.wait();
            }
            String result = contentOneTime;
            contentOneTime = null;
            return result;
        }
    }

    /**
     * Return the string with a list of Fishes
     * @return
     * @throws InterruptedException
     */
    public String getFishes() throws InterruptedException {
        synchronized (mutexFishes) {
            while (getFishes == null) {
                mutexFishes.wait();
            }
            String result = getFishes;
            getFishes = null;
            return result;
        }
    }


    private void updateView(String fishes) {
        ArrayList<String> dataFishes = prompt.parse(fishes, " ");
        int countData = dataFishes.size();
        dataFishes = new ArrayList<String>(dataFishes.subList(1, countData));
        --countData;
        for (int i = 0; i < countData/6; ++i) {
            ArrayList<String> fish = new ArrayList<String>(dataFishes.subList(i*6, (i+1)*6));
            String fishName = fish.get(0);
            int time = Integer.parseInt(fish.get(5));
            int[] positionAndTime = new int[]{Integer.parseInt(fish.get(1)), Integer.parseInt(fish.get(2)), time};
            int[] size = new int[]{Integer.parseInt(fish.get(3)), Integer.parseInt(fish.get(4))};
            try {
                Fish fishFromView = view.getFish(fishName);
                fishFromView.addPositionAndTime(positionAndTime);
            } catch (Exception e) {
                try {
                    Fish newFish = new Fish(
                        fishName,
                        new int[]{Integer.parseInt(fish.get(1)), Integer.parseInt(fish.get(2))},
                        size,
                        ""
                    );
                    view.addFish(newFish);
                    newFish.addPositionAndTime(positionAndTime);
                } catch (Exception e2) {
                    logging.warning(e2.getMessage());
                }
            }
        }
        view.updateInterface();
    }

    public void stopRunning(){
        running = false;
    }
}
