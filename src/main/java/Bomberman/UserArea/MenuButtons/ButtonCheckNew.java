package Bomberman.UserArea.MenuButtons;

import Bomberman.UserArea.UserArea;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class ButtonCheckNew extends Zone {

    private ButtonYes button_yes;
    private ButtonNo button_no;

    public ButtonCheckNew(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);

        button_yes = new ButtonYes(parent, 0, this.getHeight()-this.getHeight()/4, this.getWidth()/2, this.getHeight()/4,2);        // button id: 1= close 2=new 3= surrender
        button_no = new ButtonNo(parent, this.getWidth()/2, this.getHeight()-this.getHeight()/4, this.getWidth()/2, this.getHeight()/4);

        this.add(button_yes);
        this.add(button_no);
    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 30, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Willst du wirklich \n neustarten?", this.getWidth() / 2, this.getHeight() / 3);

    }
    @Override
    public void touchDown(Touch touch){} //nur der Moment des Touches
}
