package Models;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class Slider {
    private BorderPane slider1,slider2,slider3, mainContainerBorderPane;
    private HBox containerSlide;
    private double mainContainerWidth,mainContainerHeight;
    public static final String main = "m";
    public static final String seconde = "s";
    public static final String third = "t";

    private Timeline timelimeReset = new Timeline();
    private Timeline timelineSecond = new Timeline();
    private Timeline timelineThird = new Timeline();



    public Slider(){

    }

    public HBox initSlider(BorderPane mainContainerBorderPane,BorderPane slider1, BorderPane slider2, BorderPane slider3){
        this.mainContainerBorderPane = mainContainerBorderPane;
        mainContainerHeight=mainContainerBorderPane.getHeight();
        mainContainerWidth=mainContainerBorderPane.getWidth();
        this.slider1 = slider1;
        this.slider2 = slider2;
        this.slider3 = slider3;

        containerSlide = new HBox();

        slider1.setMinSize(mainContainerWidth,mainContainerHeight);
        slider2.setMinSize(mainContainerWidth,mainContainerHeight);
        slider3.setMinSize(mainContainerWidth,mainContainerHeight);


        containerSlide.getChildren().add(slider1);
        containerSlide.getChildren().add(slider2);
        containerSlide.getChildren().add(slider3);


        return containerSlide;
    }

    public void transition(String direction){

        if (direction.equals(seconde)){
            animationTimelineSecondPanel();
        }else if (direction.equals(third)){
            animationTimelineNextThirdPanel();
        }else{
            animationTimelineMain();
        }


    }

    public void animationTimelineSecondPanel(){
        timelimeReset.stop();
        timelineThird.stop();
        timelineSecond.getKeyFrames().addAll(
                new KeyFrame(new Duration(1000),new KeyValue(containerSlide.translateXProperty(),(mainContainerWidth*-1),Interpolator.EASE_IN))
        );
        timelineSecond.setCycleCount(1);

        timelineSecond.play();
    }


    public void animationTimelineMain(){
        timelineSecond.stop();
        timelineThird.stop();
        timelimeReset.getKeyFrames().addAll(
                new KeyFrame(new Duration(1000),new KeyValue(containerSlide.translateXProperty(),0, Interpolator.EASE_IN))
        );
        timelimeReset.setCycleCount(1);

        timelimeReset.play();
    }

    public void animationTimelineNextThirdPanel(){
        timelineSecond.stop();
        timelimeReset.stop();
        timelineThird.getKeyFrames().addAll(
                new KeyFrame(new Duration(1000),new KeyValue(containerSlide.translateXProperty(),-1*2*mainContainerWidth, Interpolator.EASE_IN))
        );
        timelineThird.setCycleCount(1);

        timelineThird.play();
    }

}
