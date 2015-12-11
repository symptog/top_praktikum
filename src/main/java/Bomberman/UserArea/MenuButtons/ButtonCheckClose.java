package Bomberman.UserArea.MenuButtons;

import Bomberman.UserArea.UserArea;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class ButtonCheckClose extends Zone {

    public ButtonCheckClose(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 30, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Willst du wirklich \n beenden?", this.getWidth() / 2, this.getHeight() / 3);

    }
    @Override
    public void touchDown(Touch touch){
       /* this.pop=!this.pop;
        if (this.pop) {

        this.remove(button_new);
        this.remove(button_surr);

        }*/
    } //nur der Moment des Touches
}

