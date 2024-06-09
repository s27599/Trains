package test;

import javax.sound.sampled.Line;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class test {
    public static void main(String[] args) {
//        try {
//            Date date = new Date();
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd_hh.mm");
//            FileWriter fos = new FileWriter("Z:\\GUI\\proj1\\test"+sf.format(date)+".txt");
//            fos.write("heka tu lenka");
//            fos.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        Set<Integer> test= new LinkedHashSet<>();
//
//        for (int i = 0; i < 10; i++) {
//            test.add(i);
//        }
//
//
//        for (int i :test) {
//            System.out.println(i);
//
//        }


        Semaphore semaphore = new Semaphore(1);

        // Tworzenie wątków dla pociągów.
        Train train1 = new Train(semaphore, "Pociąg 1");
        Train train2 = new Train(semaphore, "Pociąg 2");
        Train train3 = new Train(semaphore, "Pociąg 3");

        // Uruchamianie wątków.
        train1.start();
        train2.start();
        train3.start();
    }
}

class Train extends Thread {
    private final Semaphore semaphore;
    private final String name;

    public Train(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            // Prośba o uzyskanie dostępu do toru.
            semaphore.acquire();

            System.out.println(name + " wjeżdża na tor.");
            Thread.sleep(2000);
            System.out.println(name + " opuszcza tor.");

            // Zwolnienie toru.
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
