package Bomberman.UserArea;

import vialab.SMT.Touch;
import vialab.SMT.Zone;

/**
 * Created by acer on 14.12.2015.
 */
public class MenuButton extends Zone {

    private boolean pop = false;
    private String ButtonName;
    private int ColorCode;

    public MenuButton (UserArea parent, int i, int i1, int i2, int i3, String name, int ColorCode){
        super(i, i1, i2, i3);
        this.parent = parent;
        this.ButtonName = name;
        this.ColorCode = ColorCode;


    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, this.ColorCode, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text(this.ButtonName, this.getWidth() / 2, this.getHeight() / 3 * 2);

    }
    @Override
    public void touchDown(Touch touch){






    }//nur der Moment des Touches

}
