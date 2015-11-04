package Bomberman.UserArea;

import Bomberman.UserArea.MenuButtons.*;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class Menu extends Zone {

    private UserArea parent;
    private boolean pop = false;
    private ButtonClose button_close;
    private ButtonNew button_new;
    private ButtonSurr button_surr;
    private ButtonCheckSurr button_check;
    private ButtonYes button_yes;
    private ButtonNo button_no;


    public Menu(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        this.parent = parent;
        button_close = new ButtonClose(parent, 0,-this.getHeight(),this.getWidth(),this.getHeight());
        button_new = new ButtonNew(parent, 0,-this.getHeight()*2,this.getWidth(),this.getHeight());
        button_surr = new ButtonSurr(parent, 0,-this.getHeight()*3,this.getWidth(),this.getHeight());
    }
    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 0, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Menu", this.getWidth()/2, this.getHeight()/3*2);

    }

    //touch method
    @Override
    public void touch() {}
    @Override
    public void touchDown(Touch touch){
        this.pop=!this.pop;
        if (this.pop) {
            this.add(button_close);
            this.add(button_new);
            this.add(button_surr);

        } else {
            this.remove(button_close);
            this.remove(button_new);
            this.remove(button_surr);
        }

    } //nur der Moment des Touches
    @Override
    public void touchUp(Touch touch){} //touch moved method

}