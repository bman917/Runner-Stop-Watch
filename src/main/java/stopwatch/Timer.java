/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stopwatch;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Mr Jacky
 */
public class Timer extends Thread {

    long start = 0;
    JLabel label = null;
    DecimalFormat df = new DecimalFormat("00");
    DecimalFormat df2 = new DecimalFormat("000");
    boolean started = false;
    public boolean run = false;
    long pauseTime = 0;
    long elapsedSincePaused = 0;

    public Timer(JLabel label) {
        this.label = label;

    }

    public void pause() {
        run = false;
        pauseTime = System.currentTimeMillis();
    }

    @Override
    public synchronized void start() {

        if (!started) {
            super.start();
            started = true;
            start = System.currentTimeMillis();
        }

        if (run == false) {
            run = true;

            if (pauseTime != 0) {
                elapsedSincePaused += System.currentTimeMillis() - pauseTime;
                pauseTime = 0;
            }
        }

    }

    @Override
    public void run() {

        try {

            while (true) {

                if (run) {
                    long elapsedTime = System.currentTimeMillis() - start - elapsedSincePaused;
                    



                    long hour = elapsedTime / (60 * 1000 * 60);
                    long mins = elapsedTime / (60 * 1000);
                    long secs = elapsedTime / 1000;
                    long mili = elapsedTime - secs * 1000;

                    if (mins >= 60) {
                        mins %= 60;
                    }

                    if (secs >= 60) {
                        secs %= 60;
                    }



                    label.setText(df.format(hour) + ":" + df.format(mins) + ":" + df.format(secs) + ":" + df2.format(mili));
                }
                this.sleep(123);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
