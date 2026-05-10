package LCP.Loaders;

import javafx.scene.layout.Pane;

public class ViewData<T> {
    private final Pane view;
    private final T controller;

    public ViewData(Pane view, T controller) {
        this.view = view;
        this.controller = controller;
    }

    public Pane getPane(){
        return this.view;
    }
    public T getController(){
        return this.controller;
    }
}
