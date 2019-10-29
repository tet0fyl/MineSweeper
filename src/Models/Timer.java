package Models;

import javafx.animation.AnimationTimer;
import javafx.scene.text.Text;

public class Timer extends AnimationTimer {
    private String result;
    private Text textTimer;
    private int time = 0;
    private byte m,s;
    private long lastUpdate = 0;

    public Timer(Text textTimer){
        s=0;
        this.textTimer=textTimer;
        this.start();
    }

    @Override
    public void handle(long now) {
        if(now - lastUpdate >= 1_000_000_000L){
            time++;
            s=(byte)(time%60);
            m=(byte)((time/60)%60);
            if(m<10){
                result = "0" + m;
            }else{
                result = String.valueOf(m);
            }
            if(s<10){
                result += ":0" + s;
            }else{
                result += ":" + s;
            }

            textTimer.setText(result);
            lastUpdate=now;
        }
    }

    public String getTime() {
        return result;
    }
}
