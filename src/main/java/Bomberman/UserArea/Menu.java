package Bomberman.UserArea;

import Bomberman.Bomberman;
import Bomberman.UserArea.MenuButtons.*;
import vialab.SMT.*;

public class Menu extends Zone {


    private UserArea parent;
    private Integer side;
    private boolean pop = false;
    protected Bomberman bomberman;

    private Submenu button_close;
    private Submenu button_new;
    private Submenu button_surr;

    public Menu(UserArea parent, int i, int i1, int i2, int i3, int position, Bomberman b) {
        super(i, i1, i2, i3);
        this.parent = parent;
        this.side = position;
        this.bomberman = b;

        this.button_close = new Submenu(parent, 0,-this.getHeight(),this.getWidth(),this.getHeight(),"Beenden" , this.side, bomberman);
        this.button_surr = new Submenu(parent, 0,-this.getHeight()*3,this.getWidth(),this.getHeight(),"Aufgeben" , this.side, bomberman);
        this.button_new = new Submenu(parent, 0,-this.getHeight()*2,this.getWidth(),this.getHeight(),"Neues Spiel" , this.side, bomberman);

    }

    void showMenu(){
        this.add(button_close);
        this.add(button_new);
        this.add(button_surr);
    }
    void hideMenu(){
        this.remove(button_close);
        this.remove(button_new);
        this.remove(button_surr);
    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 0, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Menu", this.getWidth() / 2, this.getHeight() / 3 * 2);
    }
    //touch method
    @Override
    public void touch() {}
    @Override
    public void touchDown(Touch touch){

        this.pop=!this.pop;

        if (this.pop) {
            showMenu();
        }
        else {
           clearChildren();
        }

    } //nur der Moment des Touches
    @Override
    public void touchUp(Touch touch){} //touch moved method

}

