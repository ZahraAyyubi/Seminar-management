import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;

public class Time extends Thread {

    JLabel timeLabel;//for display current time on it
    public Time(JLabel label) {
        timeLabel=label;
    }
    @Override
    public void run() {
        while(true) {
            step();
            pause();
        }
    }
    private void step() {
        timeLabel.setText(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Calendar.getInstance().getTime()));
    }
    private void pause() {

        try {
            Thread.sleep(500);//pause for 300 milliseconds
        } catch (InterruptedException e) {
            System.out.println("خطایی رخ داده است");
        }

    }

}
