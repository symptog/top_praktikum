import processing.core.*;
import vialab.SMT.*;

public class UserArea extends Zone {
    private Integer col;
    PShape text = null, s = null;

    public UserArea(int i, int i1, int i2, int i3, Integer col) {
        super(i, i1, i2, i3);
        this.col = col;
    }

    @Override
    public void draw() {
        /* Wenn null, dann Rendern
         * Verhindert, dass die Fläche mit jedem Frame neu erstellt wird
         */
        if (s == null) {
            System.out.println("s ist null");
            s = createShape();
            s.beginShape();
            s.stroke(this.col);
            s.strokeWeight(2);
            s.fill(100);
            s.vertex(0, 0, 0, 0);
            s.vertex(this.getWidth(), 0, 1, 0);
            s.vertex(this.getWidth(), this.getHeight(), 1, 1);
            s.vertex(0, this.getHeight(), 0, 1);
            s.endShape(CLOSE);
        }
        if (text == null) {
            System.out.println("text ist null");
            text = loadShape("resources/svg/test.svg");
            text.setFill(this.col);
            text.scale(0.7f);
        }

        shape(s);
        beginShape(POLYGON);
        shapeMode(CENTER);
        shape(text, this.getWidth()/2, this.getHeight()/2);
    }

    //touch method
    @Override
    public void touch() {} //touch down method
    @Override
    public void touchDown(Touch touch){} //touch up method
    @Override
    public void touchUp(Touch touch){} //touch moved method
    @Override
    public void touchMoved(Touch touch){}

}