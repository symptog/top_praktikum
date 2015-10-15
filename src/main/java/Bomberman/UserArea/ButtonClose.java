package Bomberman.UserArea;

import vialab.SMT.Zone;

/**
 * Created by tobias on 15.10.15.
 */
public class ButtonClose extends Zone {

    public ButtonClose(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
    }

    @Override
    public void draw() {
        //Beenden
        stroke(0, 0, 0);
        fill(192, 50, 0);
        rectMode(CORNER);
        rect(0, 0, this.getHeight(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Beenden", 25, -61);
    }
}
