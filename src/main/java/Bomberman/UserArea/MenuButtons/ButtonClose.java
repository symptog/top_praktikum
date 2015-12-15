package Bomberman.UserArea.MenuButtons;

import Bomberman.UserArea.UserArea;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class ButtonClose extends Zone {
    private boolean pop = false;
    public ButtonCheckClose button_check_close;
    private ButtonYes button_yes;
    private ButtonNo button_no;

    private boolean status = false;
    public void setButtonClose(boolean zustand) {this.status=zustand; }
    public boolean getButtonClose(){ return status; }


    public ButtonClose(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);

        button_check_close = new ButtonCheckClose(parent, this.getWidth(), -this.getHeight() * 2, this.getWidth() * 2, this.getHeight() * 4);
        button_yes = new ButtonYes(parent, this.getWidth(), this.getHeight(), this.getWidth(), this.getHeight());
        button_no = new ButtonNo(parent, this.getWidth() * 2, this.getHeight(), this.getWidth(), this.getHeight());

        dragWithinParent( );

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

        this.pop = !this.pop;
        if (this.pop){
            this.add(button_check_close);
            this.add(button_no);
            this.add(button_yes);

        }
        else{
            clearChildren();
        }
    }
}//nur der Moment des Touches
