package Bomberman.UserArea.MenuButtons;

import Bomberman.UserArea.UserArea;
import vialab.SMT.Zone;

public class ButtonNew extends Zone {

    public ButtonNew(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
    }

    @Override
    public void draw() {
        //neues Spiel
        stroke(0, 0, 0);
        fill(192, 50, 0);
        rectMode(CORNER);
        rect(0, -this.getHeight()-this.getHeight()/25, this.getWidth()/2 + this.getWidth()/6, this.getHeight()/4);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Neu", this.getWidth()/3, -this.getHeight()/3 -this.getHeight()/2 -this.getHeight()/22);
    }
}