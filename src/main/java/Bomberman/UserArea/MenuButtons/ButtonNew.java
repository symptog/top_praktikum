package Bomberman.UserArea.MenuButtons;

import Bomberman.UserArea.UserArea;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class ButtonNew extends Zone {
    private boolean pop = false;
    private ButtonCheckNew button_checknew;
    private ButtonYes button_yes;
    private ButtonNo button_no;



    public ButtonNew(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);

        button_checknew = new ButtonCheckNew(parent, this.getWidth(),- this.getHeight(),this.getWidth()*2,this.getHeight()*4);
        button_yes = new ButtonYes(parent, this.getWidth(),this.getHeight()*2,this.getWidth(),this.getHeight());
        button_no = new ButtonNo(parent, this.getWidth()*2,this.getHeight()*2,this.getWidth(),this.getHeight());


    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 0, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Neues Spiel", this.getWidth() / 2, this.getHeight() / 3 * 2);

    }
    @Override
    public void touchDown(Touch touch){
        this.pop=!this.pop;
        if (this.pop) {
            this.add(button_checknew);
            this.add(button_yes);
            this.add(button_no);
        }
        else{
            this.remove(button_checknew);
            this.remove(button_yes);
            this.remove(button_no);
        }

    } //nur der Moment des Touches
}