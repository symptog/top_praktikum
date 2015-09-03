package Bomberman;
/**
 * Created by Relic on 28.08.2015.
 */
import processing.core.PImage;
import processing.core.PShape;

import java.util.concurrent.ConcurrentHashMap;

public class Plantedbomb {
    private Integer blockX, blockY;
    private Float x, y;
    private Integer time;
    private Integer range;
    private Field p;
    private static ConcurrentHashMap<Integer, PShape> pbomb = new ConcurrentHashMap<Integer, PShape>();
    private boolean used;
    PImage img;
    public static final int BOMBE_1_GROSS =8;
    public static final int BOMBE_1_KLEIN_1 =6;
    public static final int BOMBE_1_KLEIN_2 =7;
    public static final int BOMBE_2_GROSS =5;
    public static final int BOMBE_2_KLEIN_1 =3;
    public static final int BOMBE_2_KLEIN_2 =4;
    public static final int BOMBE_3_GROSS =2;
    public static final int BOMBE_3_KLEIN_1=0;
    public static final int BOMBE_3_KLEIN_2 =1;
    public static final int FLAMME =9;

    public Plantedbomb(Field p){
        this.p = p;
        this.blockX = 0;
        this.blockY = 0;
        this.range=3;
        used=false;
        this.time=180;
        this.x = p.getBlock(blockX, blockY).getPosX();
        this.y = p.getBlock(blockX, blockY).getPosY();
    }

    private PShape renderBomb (int bomb) {
        PShape shape = null;
        shape = pbomb.get(bomb);
        if (shape == null) {
            switch (bomb) {
                case BOMBE_3_KLEIN_1:
                    img = p.getImage("Bombe3_klein_1.png");
                    break;
                case BOMBE_3_KLEIN_2:
                    img = p.getImage("Bombe3_klein_2.png");
                    break;
                case BOMBE_3_GROSS:
                    img = p.getImage("Bombe3_gross.png");
                    break;
                case BOMBE_2_KLEIN_1:
                    img = p.getImage("Bombe2_klein_1.png");
                    break;
                case BOMBE_2_KLEIN_2:
                    img = p.getImage("Bombe2_klein_2.png");
                    break;
                case BOMBE_2_GROSS:
                    img = p.getImage("Bombe2_gross.png");
                    break;
                case BOMBE_1_KLEIN_1:
                    img = p.getImage("Bombe1_klein_1.png");
                    break;
                case BOMBE_1_KLEIN_2:
                    img = p.getImage("Bombe1_klein_2.png");
                    break;
                case BOMBE_1_GROSS:
                    img = p.getImage("Bombe1_gross.png");
                    break;
                case FLAMME:
                    img = p.getImage("Flamme.png");
                    break;
                default:
                    return null;
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
            pbomb.put(bomb, shape);
        }
        return shape;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setBomb(Integer blockX, Integer blockY ){
        this.used=true;
        this.blockX=blockX;
        this.blockY=blockY;
        this.x = p.getBlock(blockX, blockY).getPosX();
        this.y = p.getBlock(blockX, blockY).getPosY();

    }

    public void draw()
    {

        if(!used)
            return;

        if(time==0) {
            p.shape(renderBomb(FLAMME), this.x, this.y);
            time=241;
            for(int i=1;i<=range;i++)
            {
                if (blockX-i >= 0 && p.getBlock(blockX-i, blockY).isWalkable() && x >= p.getBlock(blockX-i, blockY).getPosX())
                    p.shape(renderBomb(FLAMME), p.getBlock(blockX-i, blockY).getPosX(), p.getBlock(blockX-i, blockY).getPosY());
                if (blockX+1 < p.getHorizontal_blocks()-1 && p.getBlock(blockX+1+i, blockY).isWalkable() && x <= p.getBlock(blockX+i, blockY).getPosX()+p.getBlock_size())
                    p.shape(renderBomb(FLAMME), p.getBlock(blockX+i, blockY).getPosX(), p.getBlock(blockX+i, blockY).getPosY());
                if (blockY-i >= 0 && p.getBlock(blockX, blockY-i).isWalkable() && y >= p.getBlock(blockX, blockY-i).getPosY())
                    p.shape(renderBomb(FLAMME), p.getBlock(blockX, blockY-i).getPosX(), p.getBlock(blockX, blockY-i).getPosY());
                if (blockY+i < p.getVertical_blocks()-1 && p.getBlock(blockX, blockY+1+i).isWalkable() && y <= p.getBlock(blockX, blockY+i).getPosY()+p.getBlock_size())
                    p.shape(renderBomb(FLAMME), p.getBlock(blockX, blockY+i).getPosX(), p.getBlock(blockX, blockY+i).getPosY());
            }
        }
        else if(time<241&&time>180) {
            p.shape(renderBomb(FLAMME), this.x, this.y);
            for(int i=1;i<=range;i++)
            {
                if (blockX-i >= 0 && p.getBlock(blockX-i, blockY).isWalkable() && x >= p.getBlock(blockX-i, blockY).getPosX())
                    p.shape(renderBomb(FLAMME), p.getBlock(blockX-i, blockY).getPosX(), p.getBlock(blockX-i, blockY).getPosY());
                if (blockX+1 < p.getHorizontal_blocks()-1 && p.getBlock(blockX+1+i, blockY).isWalkable() && x <= p.getBlock(blockX+i, blockY).getPosX()+p.getBlock_size())
                    p.shape(renderBomb(FLAMME), p.getBlock(blockX+i, blockY).getPosX(), p.getBlock(blockX+i, blockY).getPosY());
                if (blockY-i >= 0 && p.getBlock(blockX, blockY-i).isWalkable() && y >= p.getBlock(blockX, blockY-i).getPosY())
                    p.shape(renderBomb(FLAMME), p.getBlock(blockX, blockY-i).getPosX(), p.getBlock(blockX, blockY-i).getPosY());
                if (blockY+i < p.getVertical_blocks()-1 && p.getBlock(blockX, blockY+1+i).isWalkable() && y <= p.getBlock(blockX, blockY+i).getPosY()+p.getBlock_size())
                    p.shape(renderBomb(FLAMME), p.getBlock(blockX, blockY+i).getPosX(), p.getBlock(blockX, blockY+i).getPosY());
            }
            if(time==181) {
                this.used = false;
                p.getBlock(blockX,blockY).setType(Items.EMPTY);
            }
        }
        else if(time>170&&time<181)
            p.shape(renderBomb(BOMBE_3_KLEIN_1), this.x, this.y);
        else if(time>160)
            p.shape(renderBomb(BOMBE_3_KLEIN_2), this.x, this.y);
        else if(time>150)
            p.shape(renderBomb(BOMBE_3_KLEIN_1), this.x, this.y);
        else if(time>140)
            p.shape(renderBomb(BOMBE_3_KLEIN_2), this.x, this.y);
        else if(time>130)
            p.shape(renderBomb(BOMBE_3_KLEIN_1), this.x, this.y);
        else if(time>120)
            p.shape(renderBomb(BOMBE_3_KLEIN_2), this.x, this.y);
        else if(time>110)
            p.shape(renderBomb(BOMBE_2_KLEIN_1), this.x, this.y);
        else if(time>100)
            p.shape(renderBomb(BOMBE_2_KLEIN_2), this.x, this.y);
        else if(time>90)
            p.shape(renderBomb(BOMBE_2_GROSS), this.x, this.y);
        else if(time>80)
            p.shape(renderBomb(BOMBE_2_KLEIN_1), this.x, this.y);
        else if(time>70)
            p.shape(renderBomb(BOMBE_2_KLEIN_2), this.x, this.y);
        else if(time>60)
            p.shape(renderBomb(BOMBE_2_GROSS), this.x, this.y);
        else if(time>50)
            p.shape(renderBomb(BOMBE_1_KLEIN_1), this.x, this.y);
        else if(time>40)
            p.shape(renderBomb(BOMBE_1_GROSS), this.x, this.y);
        else if(time>30)
            p.shape(renderBomb(BOMBE_1_KLEIN_1), this.x, this.y);
        else if(time>20)
            p.shape(renderBomb(BOMBE_1_GROSS), this.x, this.y);
        else if(time>10)
            p.shape(renderBomb(BOMBE_1_KLEIN_1), this.x, this.y);
        else if(time>0)
            p.shape(renderBomb(BOMBE_1_GROSS), this.x, this.y);

        time--;

    }
}

