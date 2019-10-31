package Models;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class Slider {
    private BorderPane slider1,slider2, mainContainerBorderPane;
    private HBox containerSlide;
    private double mainContainerWidth,mainContainerHeight;
    public static final String previous = "p";
    public static final String next = "n";
    private Timeline timelinePrevious = new Timeline();
    private Timeline timelineNext = new Timeline();


    public Slider(){

    }

    public HBox initSlider(BorderPane mainContainerBorderPane,BorderPane slider1, BorderPane slider2){
        this.mainContainerBorderPane = mainContainerBorderPane;
        mainContainerHeight=mainContainerBorderPane.getHeight();
        mainContainerWidth=mainContainerBorderPane.getWidth();
        this.slider1 = slider1;
        this.slider2 = slider2;

        containerSlide = new HBox();

        slider1.setMinSize(mainContainerWidth,mainContainerHeight);
        slider2.setMinSize(mainContainerWidth,mainContainerHeight);

        containerSlide.getChildren().add(slider1);
        containerSlide.getChildren().add(slider2);

        return containerSlide;
    }

    public void transition(String direction){

        if (direction.equals(next)){
            animationTimelineNext();
        }else{
            animationTimelinePrevious();
        }


    }

    public void animationTimelineNext(){
        timelinePrevious.stop();
        timelineNext.getKeyFrames().addAll(
                new KeyFrame(new Duration(1000),new KeyValue(containerSlide.translateXProperty(),(mainContainerWidth*-1),Interpolator.EASE_IN))
        );
        timelineNext.setCycleCount(1);

        timelineNext.play();
    }


    public void animationTimelinePrevious(){
        timelineNext.stop();
        timelinePrevious.getKeyFrames().addAll(
                new KeyFrame(new Duration(1000),new KeyValue(containerSlide.translateXProperty(),0, Interpolator.EASE_IN))
        );
        timelinePrevious.setCycleCount(1);

        timelinePrevious.play();
    }

}
