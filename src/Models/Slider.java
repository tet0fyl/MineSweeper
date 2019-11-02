package Models;

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
    private double targetPosition;

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
            targetPosition= mainContainerWidth*-1;
            animationTimelineSecondPanel();
        }else if (direction.equals(third)){
            targetPosition= mainContainerWidth*2*-1;
            animationTimelineNextThirdPanel();
        }else{
            targetPosition=0;
            animationTimelineMain();
        }


    }

    public void animationTimelineSecondPanel(){
        timelimeReset.stop();
        timelineThird.stop();
        timelineSecond.getKeyFrames().addAll(
                new KeyFrame(new Duration(1000),new KeyValue(containerSlide.translateXProperty(),targetPosition))
        );
        timelineSecond.setCycleCount(1);

        timelineSecond.play();
    }


    public void animationTimelineMain(){
        timelineSecond.stop();
        timelineThird.stop();
        timelimeReset.getKeyFrames().addAll(
                new KeyFrame(new Duration(1000),new KeyValue(containerSlide.translateXProperty(),0))
        );
        timelimeReset.setCycleCount(1);

        timelimeReset.play();
    }

    public void animationTimelineNextThirdPanel(){
        timelineSecond.stop();
        timelimeReset.stop();
        timelineThird.getKeyFrames().addAll(
                new KeyFrame(new Duration(1000),new KeyValue(containerSlide.translateXProperty(),targetPosition))
        );
        timelineThird.setCycleCount(1);
        timelineThird.play();
    }

}
