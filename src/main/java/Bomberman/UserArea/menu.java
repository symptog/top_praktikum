package Bomberman.UserArea;

import vialab.SMT.Touch;
import vialab.SMT.Zone;
import vialab.SMT.*;

/**
 * Created by tobias on 15.07.15.
 */

public class menu extends Zone {

    private UserArea parent;
    private boolean pop = false;

    public menu(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        this.parent = parent;


        //SMT.add(new LeftPopUpMenuZone(300, 100, 50, 50, 200, 100, "Aufgeben", "Beenden", "zur�ck"));
        // http://vialab.science.uoit.ca/smt/reference/LeftPopUpMenuZone/LeftPopUpMenuZone.php
        // http://vialab.science.uoit.ca/smt/examples/Zones/LeftPopUpMenu.php
    }
    @Override
    public void draw() {
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 0, 0);//legt F�llfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(-16, -20, this.getHeight(), this.getHeight());
        textAlign(CENTER);  textSize(10);   fill(0);
        text("menu", 0, 0);
        if (pop) {
            drawMenu();
        }
    }
    public void drawMenu() {
        // Menu
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 50, 0);//legt F�llfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(-16, -55, this.getHeight(), this.getHeight());
        textAlign(CENTER);  textSize(10);   fill(0);
        text("Neu", 0, -35);

        //Beenden
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 50, 0);//legt F�llfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(-16, -90, this.getHeight(), this.getHeight());
        textAlign(CENTER);  textSize(10);   fill(0);
        text("close", 0, -70);

        // Aufgeben
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 50, 0);//legt F�llfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(-16, -125, this.getHeight(), this.getHeight());
        textAlign(CENTER);  textSize(10);   fill(0);
        text("aufgeben", 0, -105);
    }


    //touch method
    @Override
    public void touch() {}
    @Override
    public void touchDown(Touch touch){
        pop=!pop;
    } //nur der Moment des Touches
    @Override
    public void touchUp(Touch touch){} //touch moved method

}