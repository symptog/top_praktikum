package Bomberman;

import processing.core.PImage;
import processing.core.PShape;

public class Bomberman {
    private String id;
    private Integer blockX, blockY;
    private Float x, y;
    private Field p;
    private Boolean inverted = false;
    private Float speed = 1.0f;
    PImage img;
    private Float count_left = 60.0f, count_right = 60.0f, count_up = 60.0f, count_down = 60.0f;

    public static final int UP1 = 11;
    public static final int UP2 = 12;
    public static final int DOWN1 = 21;
    public static final int DOWN2 = 22;
    public static final int LEFT1 = 31;
    public static final int LEFT2 = 32;
    public static final int RIGHT1 = 41;
    public static final int RIGHT2 = 42;

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
        switch (direction) {
            case UP1:
                img = p.getImage(id + "_oben_2.png");
                break;
            case UP2:
                img = p.getImage(id + "_oben_3.png");
                break;
            case DOWN1:
                img = p.getImage(id + "_unten_3.png");
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
                img = p.getImage(id + ".png");
                break;
        }
        PShape shape = p.createShape();
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
            shape.rotateX(p.PI);
            shape.translate(0, -p.getBlock_size());
            p.popMatrix();
        }

        this.x = p.getBlock(blockX, blockY).getPosX();
        this.y = p.getBlock(blockX, blockY).getPosY();

        return shape;

    }

    public void display() {
        updateBlock();
        p.shape(this.render(), this.x, this.y );
    }

    public void displaydirection(int direction) {
        updateBlock();
        p.shape(this.render(direction), this.x, this.y);
    }

    public void moveLeft() {
        if (blockX > 0 && p.getBlock(blockX, blockY).isWalkable() && x >= p.getBlock(blockX, blockY).getPosX()) {
            if (this.count_left < 25) {
                this.displaydirection(LEFT1);
                this.count_left += this.speed;
            }
            if (this.count_left >= 25) {
                displaydirection(LEFT2);
                this.count_left += this.speed;
                if (this.count_left >= 50)
                    this.count_left = 0.0f;
            }
            this.x -= this.speed;
        }
    }
    public void moveRight() {
        if (blockX < p.getHorizontal_blocks()-1 && p.getBlock(blockX+1, blockY).isWalkable() && x <= p.getBlock(blockX, blockY).getPosX()+p.getBlock_size()) {
            if (this.count_right < 25) {
                this.displaydirection(LEFT1);
                this.count_right += this.speed;
            }
            if (this.count_right >= 25) {
                displaydirection(LEFT2);
                this.count_right += this.speed;
                if (this.count_right >= 50)
                    this.count_right = 0.0f;
            }
            this.x += this.speed;
        }
    }
    public void moveUp() {
        if (blockY > 0 && p.getBlock(blockX, blockY).isWalkable() && y >= p.getBlock(blockX, blockY).getPosY()) {
            if (this.count_up < 25) {
                this.displaydirection(LEFT1);
                this.count_up += this.speed;
            }
            if (this.count_up >= 25) {
                displaydirection(LEFT2);
                this.count_up += this.speed;
                if (this.count_up >= 50)
                    this.count_up = 0.0f;
            }            
            this.y -= speed;
        }
    }
    public void moveDown() {
        if (blockY < p.getVertical_blocks()-1 && p.getBlock(blockX, blockY+1).isWalkable() && y <= p.getBlock(blockX, blockY).getPosY()+p.getBlock_size()) {
            if (this.count_down < 25) {
                this.displaydirection(LEFT1);
                this.count_down += this.speed;
            }
            if (this.count_down >= 25) {
                displaydirection(LEFT2);
                this.count_down += this.speed;
                if (this.count_down >= 50)
                    this.count_down = 0.0f;
            }   
            this.y += 1;
        }
    }
    public void dropBomb() {
        Block bomb_block = p.getBlock(blockX, blockY);
        bomb_block.setType(Items.BOMBE);
    }
    public void die() {}

    private void updateBlock() {
        blockX = (int) (x-p.getX_offset())/p.getBlock_size();
        blockY = (int) (y-p.getY_offset())/p.getBlock_size();
    }

}
