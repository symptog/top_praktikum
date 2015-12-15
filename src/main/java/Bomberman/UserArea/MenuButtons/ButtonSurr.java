package Bomberman.UserArea.MenuButtons;

import Bomberman.UserArea.UserArea;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class ButtonSurr extends Zone {
    private boolean pop = false;
    private ButtonCheckSurr button_check;
    private ButtonYes button_yes;
    private ButtonNo button_no;

    private boolean close;
    public void setButtonSurr(boolean action){this.close=action;}
    public boolean getButtonSurr(){return this.close;}



    public ButtonSurr(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);

        button_check = new ButtonCheckSurr(parent, this.getWidth(),0,this.getWidth()*2,this.getHeight()*4);
        button_yes = new ButtonYes(parent, this.getWidth(),this.getHeight()*3,this.getWidth(),this.getHeight());
        button_no = new ButtonNo(parent, this.getWidth()*2,this.getHeight()*3,this.getWidth(),this.getHeight());



    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 0, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Aufgeben", this.getWidth() / 2, this.getHeight() / 3 * 2);

    }
    @Override
    public void touchDown(Touch touch){

        this.pop=!this.pop;
        if (this.pop) {
            this.add(button_check);
            this.add(button_yes);
            this.add(button_no);
        }
        else{
            clearChildren();
        }

    } //nur der Moment des Touches
}