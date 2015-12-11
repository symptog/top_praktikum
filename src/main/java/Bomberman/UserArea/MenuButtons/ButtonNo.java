package Bomberman.UserArea.MenuButtons;

import Bomberman.UserArea.UserArea;
import vialab.SMT.Touch;
import vialab.SMT.Zone;


public class ButtonNo extends Zone {

    public ButtonNo(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);


    }


    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(170, 60, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);
        textSize(15);
        fill(0);
        text("Nein", this.getWidth() / 2, this.getHeight() / 3 * 2);

    }
    @Override
    public void touchDown(Touch touch){


    } //nur der Moment des Touches

}