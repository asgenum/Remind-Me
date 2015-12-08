package application.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * Created by Destrouer on 11.12.15.
 */
public class DBWorker {
    private final static DBWorker INSTANCE  = new DBWorker();

    private DBWorker() {

    }

    public static DBWorker getInstance() {
        return INSTANCE;
    }

    public int writeEventListToFile(String pathToFile, Vector<Event> vectorOfEvents) {
        if (pathToFile != null && pathToFile.length() > 0 && vectorOfEvents.size() > 0) {
            try {
                FileOutputStream fos = new FileOutputStream(pathToFile);
                ObjectOutputStream outStream = new ObjectOutputStream(fos);
                for (int i = 0; i < vectorOfEvents.size(); i++) {
                    outStream.writeObject(vectorOfEvents.elementAt(i));
                    outStream.flush();
                }
                outStream.flush();
                outStream.close();
            } catch (Exception e) {
                System.out.println("Error+e.getMessage()");
            }
        }
        return 0;
    }

    public Vector<Event> readEventListFromFile(String pathToFile) {
        Vector<Event> vectorOfEvents = new Vector<Event>();
        FileInputStream fis = null;
        ObjectInputStream inputStream = null;
        try {
            vectorOfEvents.clear();
            fis = new FileInputStream(pathToFile);
            inputStream = new ObjectInputStream(fis);

            while (true) {
                Event e = (Event) inputStream.readObject();
                System.out.println(e.getYearOfEvent());
                vectorOfEvents.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error+e.getMessage()");
        }
        try {
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Error+e.getMessage()");
        }

        return vectorOfEvents;
    }
}
