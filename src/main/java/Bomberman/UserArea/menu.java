package Bomberman.UserArea;

import vialab.SMT.Touch;
import vialab.SMT.Zone;
import vialab.SMT.*;

/**
 * Created by tobias on 15.07.15.
 */


public class menu extends Zone {

    private UserArea parent;

    Zone options;

    public menu(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        this.parent = parent;

        options = new ContainerZone("Options",getWidth(),getHeight(),getWidth(),getHeight());
        options.add(new ButtonZone("PlayButton", getWidth(), getHeight(), 100, 50, "mitspielen", 10));
        options.add(new ButtonZone("OptionsButton", getWidth(), getHeight()+50, 100, 50, "Aufgeben", 10));
        options.add(new ButtonZone("ExitButton",getWidth(),getHeight()+100,100,50,"Spiel beenden",10));
    }
    @Override
    public void draw() {
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 0, 0);//legt Füllfarbe nachfolgender Formen fest
        rectMode(CENTER);
        rect(0, 0, this.getHeight(), this.getHeight());
        textAlign(CENTER);  textSize(10);   fill(0);
        text("menu", 0, 0);
    }

    //touch method
    @Override
    public void touchDown(Touch touch){} //nur der Moment des Touches
    @Override
    public void touchUp(Touch touch){} //touch moved method

}