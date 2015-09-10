package Bomberman;

import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

import java.util.concurrent.ConcurrentHashMap;

public class Bomberman {
    private String id;
    protected Integer blockX, blockY;
    private Float x, y;
    private Field p;
    private Boolean inverted = false;
    public Float speed = 1.0f;
    PImage img;
    public Float count = 60.0f;

    private Float maxLeft, maxRight, maxUp, maxDown;

    private static final int ECKE = 15; // Tolleranz beim um die Ecke gehen
    public static final int STOP = 0;
    public static final int UP = 10;
    public static final int UP1 = 11;
    public static final int UP2 = 12;
    public static final int DOWN = 20;
    public static final int DOWN1 = 21;
    public static final int DOWN2 = 22;
    public static final int LEFT = 30;
    public static final int LEFT1 = 31;
    public static final int LEFT2 = 32;
    public static final int RIGHT = 40;
    public static final int RIGHT1 = 41;
    public static final int RIGHT2 = 42;
    private int direction = STOP;
    private ConcurrentHashMap<Integer, PShape> bman = new ConcurrentHashMap<Integer, PShape>();

    public Bomberman(Field p, String id, Integer blockX, Integer blockY) {
        this.p = p;
        this.id = id;
        this.blockX = blockX;
        this.blockY = blockY;
    }

    public Bomberman(Field p, String id, Integer blockX, Integer blockY, Boolean inverted) {
        this.p = p;
        this.id = id;
        this.blockX = blockX;
        this.blockY = blockY;
        this.inverted = inverted;
        this.x = p.getBlock(blockX, blockY).getPosX();
        this.y = p.getBlock(blockX, blockY).getPosY();
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public void setInverted(Boolean inverted) {
        this.inverted = inverted;
    }



    public PShape render() {
        return render(0);
    }

    public PShape render(int direction) {
        PShape shape = null;
        shape = bman.get(direction);
        if(shape==null) {
            switch (direction) {
                case UP1:
                    img = p.getImage(id + "_oben_2.png");
                    break;
                case UP2:
                    img = p.getImage(id + "_oben_3.png");
                    break;
                case DOWN1:
                    img = p.getImage(id + "_unten_2.png");
                    break;
                case DOWN2:
                    img = p.getImage(id + "_unten_3.png");
                    break;
                case LEFT1:
                    img = p.getImage(id + "_links_1.png");
                    break;
                case LEFT2:
                    img = p.getImage(id + "_links_2.png");
                    break;
                case RIGHT1:
                    img = p.getImage(id + "_rechts_1.png");
                    break;
                case RIGHT2:
                    img = p.getImage(id + "_rechts_2.png");
                    break;
                default:
                    img = p.getImage(id + "_unten_1.png");
                    break;
            }
            shape = p.createShape();
            shape.beginShape();
            shape.noStroke();
            shape.fill(255);
            shape.texture(img);
            shape.textureMode(PShape.NORMAL);
            shape.vertex(0, 0, 0, 0);
            shape.vertex(p.getBlock_size(), 0, 1, 0);
            shape.vertex(p.getBlock_size(), p.getBlock_size(), 1, 1);
            shape.vertex(0, p.getBlock_size(), 0, 1);
            shape.endShape(PShape.CLOSE);
            if (inverted) {
                p.pushMatrix();
                shape.rotateX(PConstants.PI);
                shape.translate(0, -p.getBlock_size());
                p.popMatrix();
            }
            bman.put(direction, shape);
            /* was hab ich mir dabei gedacht */
        }

        return shape;

    }

    public void display() {
        updateBlock();
        p.shape(this.render(), this.x, this.y );
    }

    public void displayDirection(int direction) {
        updateBlock();
        p.shape(this.render(direction), this.x, this.y);
    }
    /* ToDo: Abfrage in welchem Block sich der Bomberman befindet
     * ToDo: Prüfen ob der Bomberman sich auf umliegende Felder bewegen darf */
    public void moveLeft() {
        if (x >= maxLeft && y >= p.getBlock(blockX, blockY).getPosY()-ECKE && y <= p.getBlock(blockX, blockY).getPosY()+ECKE ) {
            this.y = p.getBlock(blockX, blockY).getPosY();
            this.x -= speed;
        }
    }
    public void moveRight() {
        if (x <= maxRight && y >= p.getBlock(blockX, blockY).getPosY()-ECKE && y <= p.getBlock(blockX, blockY).getPosY()+ECKE) {
            this.y = p.getBlock(blockX, blockY).getPosY();
            this.x += speed;
        }
    }
    public void moveUp() {
        if (y >= maxUp && x >= p.getBlock(blockX, blockY).getPosX()-ECKE && x <= p.getBlock(blockX, blockY).getPosX()+ECKE) {
            this.y -= speed;
            this.x = p.getBlock(blockX, blockY).getPosX();
        }
    }
    public void moveDown() {
        if (y <= maxDown && x >= p.getBlock(blockX, blockY).getPosX()-ECKE && x <= p.getBlock(blockX, blockY).getPosX()+ECKE) {
            this.y += speed;
            this.x = p.getBlock(blockX, blockY).getPosX();
        }
    }
    
    public void draw()
    {
        if(direction==0) {
            displayDirection(Bomberman.STOP);
        }
        if(direction==10) {
            if (count < 25) {
                displayDirection(Bomberman.UP1);
                moveUp();
                count = count + this.speed;
            }
            if (count >= 25) {
                displayDirection(Bomberman.UP2);
                moveUp();
                count = count + this.speed;
                if (count >= 50)
                    count = 0.0f;
            }
        }
        if(direction==20) {
            if (count < 25) {
                displayDirection(Bomberman.DOWN1);
                moveDown();
                count = count + this.speed;
            }
            if (count >= 25) {
                displayDirection(Bomberman.DOWN2);
                moveDown();
                count = count + this.speed;
                if (count >= 50)
                    count = 0.0f;
            }
        }
        if(direction==30) {
            if (count < 25) {
                displayDirection(Bomberman.LEFT1);
                moveLeft();
                count = count + this.speed;
            }
            if (count >= 25) {
                displayDirection(Bomberman.LEFT2);
                moveLeft();
                count = count + this.speed;
                if (count >= 50)
                    count = 0.0f;
            }
        }
        if(direction==40) {
            if (count < 25) {
                displayDirection(Bomberman.RIGHT1);
                moveRight();
                count = count + this.speed;
            }
            if (count >= 25) {
                displayDirection(Bomberman.RIGHT2);
                moveRight();
                count = count + this.speed;
                if (count >= 50)
                    count = 0.0f;
            }
        }


    }
    
    public void dropBomb() {
        /*
        Block bomb_block = p.getBlock(blockX, blockY);
        bomb_block.setType(Items.BOMBE); */

        if(id.equals("violett"))
        {
            for(int i=0;i<5;i++)
            {
                if(p.bombfield[i].isUsed()==false)
                {
                    p.bombfield[i].setBomb(this.blockX, this.blockY);
                }

            }

        }
        if(id.equals("blue"))
        {
            for(int i=5;i<10;i++)
            {
                if(p.bombfield[i].isUsed()==false)
                {
                    p.bombfield[i].setBomb(this.blockX, this.blockY);
                }

            }

        }
        if(id.equals("orange"))
        {
            for(int i=10;i<15;i++)
            {
                if(p.bombfield[i].isUsed()==false)
                {
                    p.bombfield[i].setBomb(this.blockX, this.blockY);
                }

            }

        }
        if(id.equals("red"))
        {
            for(int i=15;i<20;i++)
            {
                if(p.bombfield[i].isUsed()==false)
                {
                    p.bombfield[i].setBomb(this.blockX, this.blockY);
                }

            }

        }



    }

    public void die() {}

    private void updateBlock() {

        this.blockX = (int) ((x+p.getBlock_size()/2)-p.getX_offset())/p.getBlock_size();
        this.blockY = (int) ((y+p.getBlock_size()/2)-p.getY_offset())/p.getBlock_size();

        try { // Left
            if (p.getBlock(this.blockX-1, this.blockY).isWalkable()) {
                this.maxLeft = p.getBlock(this.blockX-1, this.blockY).getPosX();
            } else {
                this.maxLeft = p.getBlock(this.blockX, this.blockY).getPosX();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            this.maxLeft = p.getBlock(0, blockY).getPosX();
        }

        try { // Right
            if (p.getBlock(this.blockX+1, this.blockY).isWalkable()) {
                this.maxRight = p.getBlock(this.blockX+1, this.blockY).getPosX();
            } else {
                this.maxRight = p.getBlock(this.blockX, this.blockY).getPosX();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            this.maxRight = p.getBlock(p.getHorizontal_blocks()-1, blockY).getPosX();
        }

        try { // Down
            if (p.getBlock(this.blockX, this.blockY+1).isWalkable()) {
                this.maxDown = p.getBlock(this.blockX, this.blockY+1).getPosY();
            } else {
                this.maxDown = p.getBlock(this.blockX, this.blockY).getPosY();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            this.maxDown = p.getBlock(blockX, p.getVertical_blocks()-1).getPosY();
        }

        try { // Up
            if (p.getBlock(this.blockX, this.blockY-1).isWalkable()) {
                this.maxUp = p.getBlock(this.blockX, this.blockY-1).getPosY();
            } else {
                this.maxUp = p.getBlock(this.blockX, this.blockY).getPosY();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            this.maxUp = p.getBlock(blockX, 0).getPosY();
        }
    }

}
