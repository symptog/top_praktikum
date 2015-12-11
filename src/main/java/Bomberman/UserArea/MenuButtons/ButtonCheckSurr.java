package Bomberman.UserArea.MenuButtons;

import Bomberman.UserArea.UserArea;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class ButtonCheckSurr extends Zone {

    public ButtonCheckSurr(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 30, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Willst du wirklich \n Aufgeben?", this.getWidth() / 2, this.getHeight() / 3);

    }
    @Override
    public void touchDown(Touch touch){
        /*
        this.remove(button_close);
        this.remove(button_new);
        */
    } //nur der Moment des Touches

}