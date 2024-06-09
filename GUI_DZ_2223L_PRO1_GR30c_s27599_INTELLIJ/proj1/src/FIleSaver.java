import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeSet;

public class FIleSaver implements Runnable {


    @Override
    public void run() {

        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");

        try {
            FileWriter fos = new FileWriter("Z:\\GUI\\proj1\\AppState.txt");

            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(5000); // zamienić na 5 sekund
                } catch (InterruptedException e) {
                    System.out.println("zapis do ppliku zakonczony");
                    fos.close();
                    return;
                }
                date = new Date();
                fos.write("*****************" + sf.format(date) + "\n");
                ArrayList<Locomotive> trainslist = new ArrayList<>(Trains.getTrains());
                trainslist.sort(null);

                for (Locomotive loc : trainslist) {
                    if (loc.velocityChanger.isAlive())
                        fos.write(loc.giveInfo() + "\n");
                }
                fos.write("-------------------------------------------------------------\n");
            }
            fos.close();
        } catch (IOException e) {
            System.out.println("Blad pliku");
        }
    }
}
