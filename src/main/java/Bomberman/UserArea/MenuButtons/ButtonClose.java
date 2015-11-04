package Bomberman.UserArea.MenuButtons;

import Bomberman.UserArea.UserArea;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class ButtonClose extends Zone {
    private boolean pop = false;
    private ButtonCheckClose button_checkclose;
    private ButtonYes button_yes;
    private ButtonNo button_no;

    public ButtonClose(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);

        button_checkclose = new ButtonCheckClose(parent, this.getWidth(),-this.getHeight()*2,this.getWidth()*2,this.getHeight()*4);
        button_yes = new ButtonYes(parent, this.getWidth(),this.getHeight(),this.getWidth(),this.getHeight());
        button_no = new ButtonNo(parent, this.getWidth()*2,this.getHeight(),this.getWidth(),this.getHeight());
    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 0, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Beenden", this.getWidth() / 2, this.getHeight() / 3 * 2);

    }
    @Override
    public void touchDown(Touch touch){
        this.pop=!this.pop;
        if (this.pop) {
            this.add(button_checkclose);
            this.add(button_yes);
            this.add(button_no);

        } else {
            this.remove(button_checkclose);
            this.remove(button_yes);
            this.remove(button_no);
        }
    } //nur der Moment des Touches
}