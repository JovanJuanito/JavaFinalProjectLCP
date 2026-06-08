package LCP.Loaders;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationHelper {

    public static void fadeIn(Node node) {
        try {
            if (node == null) return;
            node.setOpacity(0);
            FadeTransition fade = new FadeTransition(Duration.millis(450), node);
            fade.setToValue(1);
            fade.setInterpolator(Interpolator.EASE_BOTH);
            fade.play();
        } catch (Exception e) {
            System.err.println("Error in fadeIn animation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void slideInFromTop(Node node, int i) {
        try {
            if (node == null) return;
            node.setTranslateY(-50);
            TranslateTransition slide = new TranslateTransition(Duration.millis(i), node);
            slide.setToY(0);
            // slide.setInterpolator(Interpolator.EASE_OUT);
            slide.play();
        } catch (Exception e) {
            System.err.println("Error in slideInFromTop animation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void slideInFromLeft(Node node, int i) {
        try {
            if (node == null) return;
            node.setTranslateX(-50);
            TranslateTransition slide = new TranslateTransition(Duration.millis(i), node);
            slide.setToX(0);
            // slide.setInterpolator(Interpolator.EASE_OUT);
            slide.play();
        } catch (Exception e) {
            System.err.println("Error in slideInFromLeft animation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void slideOutFromBottom(Node node, int i) {
        try {
            if (node == null) return;
            node.setTranslateY(0);
            TranslateTransition slide = new TranslateTransition(Duration.millis(i), node);
            slide.setToY(-50);
            // slide.setInterpolator(Interpolator.EASE_OUT);
            slide.play();
        } catch (Exception e) {
            System.err.println("Error in slideOutFromBottom animation: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}