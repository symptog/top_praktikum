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

    private Integer flameleft=1, flameright=1, flameup=1, flamedown=1, stopleft2=0, stopright2=0, stopup2=0, stopdown2=0;
    private Boolean stopleft=true, stopright=true, stopup=true, stopdown=true;

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
    public Integer getBlockX() {
        return blockX;
    }public Integer getBlockY() {
        return blockY;
    }
    public void setRange(Integer range) {
        this.range = range;
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
        this.time=180;
        this.blockX=blockX;
        this.blockY=blockY;
        this.x = p.getBlock(blockX, blockY).getPosX();
        this.y = p.getBlock(blockX, blockY).getPosY();

    }

    public void bombexplode(Integer blockX, Integer blockY)
    {
        for(int i=0;i<p.bombfield.length;i++)
        {
            if(p.bombfield[i].isUsed()) {
                if (p.bombfield[i].getBlockX() == blockX && p.bombfield[i].getBlockY() == blockY)
                {
                    if(p.bombfield[i].getTime()>3&&p.bombfield[i].getTime()<181)
                        p.bombfield[i].setTime(1);
                }
            }
        }
    }

    public void draw(Integer xred, Integer yred, Integer xorange, Integer yorange, Integer xblue, Integer yblue,   Integer xviolett, Integer yviolett)
    {

        if(!used)
            return;

        if((time<241&&time>180) || time==0) {
            p.shape(renderBomb(FLAMME), this.x, this.y);
            if(time==0) {
                time = 241;
            }

            if(p.getBlock(blockX, blockY).getType()==1)
            {
                bombexplode(blockX, blockY);
            }
            if(time>182) {

                if (blockX == xred && yred == blockY) {
                    p.b1.die();
                }
                if (blockX == xorange && yorange == blockY) {
                    p.b2.die();
                }
                if (blockX == xblue && yblue == blockY) {
                    p.b3.die();
                }
                if (blockX == xviolett && yviolett == blockY) {
                    p.b4.die();
                }
            }

            for(int i=1;i<=range;i++)
            {
                if (blockX-i >= 0 && flameleft==i) //p.getBlock(blockX-i, blockY).isWalkable() && x >= p.getBlock(blockX-i, blockY).getPosX()
                {

                    if(p.getBlock(blockX-i, blockY).getType()==1)
                    {
                        bombexplode(blockX - i, blockY);
                    }
                    if(time>182) {
                        if (blockX - i == xred && yred == blockY) {
                            p.b1.die();
                        }
                        if (blockX - i == xorange && yorange == blockY) {
                            p.b2.die();
                        }
                        if (blockX - i == xblue && yblue == blockY) {
                            p.b3.die();
                        }
                        if (blockX - i == xviolett && yviolett == blockY) {
                            p.b4.die();
                        }
                    }



                    if(p.getBlock(blockX-i, blockY).getCovered())
                    {
                        if(time>181&&time<183&&stopleft) {
                            stopleft=false;
                            stopleft2=i;
                            flameleft--;
                        }
                        flameleft--;
                    }
                    if(p.getBlock(blockX-i, blockY).getType()==2)
                    {
                        if(time>180&&time<183&&stopleft) {
                            stopleft=false;
                            stopleft2=i;
                            flameleft--;
                        }
                        flameleft--;
                    }
                    if(p.getBlock(blockX-i, blockY).getType()==3)
                    {
                        flameleft=flameleft-2;
                    }


                    if((i==flameleft||i-1==flameleft)&&stopleft)
                        p.shape(renderBomb(FLAMME), p.getBlock(blockX - i, blockY).getPosX(), p.getBlock(blockX - i, blockY).getPosY());
                    flameleft++;
                }
                if (blockX+i < p.getHorizontal_blocks() &&  flameright ==i)   //p.getBlock(blockX+1+i, blockY).isWalkable() && x <= p.getBlock(blockX+i, blockY).getPosX()+p.getBlock_size()){
                {
                    if(p.getBlock(blockX+i, blockY).getType()==1)
                    {
                        bombexplode(blockX+i, blockY);
                    }
                    if(time>182) {
                        if (blockX + i == xred && yred == blockY) {
                            p.b1.die();
                        }
                        if (blockX + i == xorange && yorange == blockY) {
                            p.b2.die();
                        }
                        if (blockX + i == xblue && yblue == blockY) {
                            p.b3.die();
                        }
                        if (blockX + i == xviolett && yviolett == blockY) {
                            p.b4.die();
                        }
                    }


                    if(p.getBlock(blockX+i, blockY).getCovered())
                    {
                        if(time>181&&time<183&&stopright) {
                            stopright=false;
                            stopright2=i;
                            flameright--;
                        }
                        flameright--;
                    }
                    if(p.getBlock(blockX+i, blockY).getType()==2)
                    {
                        if(time>180&&time<183&&stopright) {
                            stopright=false;
                            stopright2=i;
                            flameright--;
                        }
                        flameright--;
                    }
                    if(p.getBlock(blockX+i, blockY).getType()==3)
                    {
                        flameright=flameright-2;
                    }


                    if((i==flameright||i-1==flameright)&&stopright)
                        p.shape(renderBomb(FLAMME), p.getBlock(blockX + i, blockY).getPosX(), p.getBlock(blockX + i, blockY).getPosY());
                    flameright++;
                }
                if (blockY-i >= 0 && flamedown==i )//p.getBlock(blockX, blockY-i).isWalkable() && y >= p.getBlock(blockX, blockY-i).getPosY())
                {
                    if(p.getBlock(blockX, blockY-i).getType()==1)
                    {
                        bombexplode(blockX, blockY-i);
                    }
                    if(time>182) {
                        if (blockX == xred && yred == blockY - i) {
                            p.b1.die();
                        }
                        if (blockX == xorange && yorange == blockY - i) {
                            p.b2.die();
                        }
                        if (blockX == xblue && yblue == blockY - i) {
                            p.b3.die();
                        }
                        if (blockX == xviolett && yviolett == blockY - i) {
                            p.b4.die();
                        }
                    }


                    if(p.getBlock(blockX, blockY-i).getCovered())
                    {
                        if(time>181&&time<183&&stopdown) {
                            stopdown=false;
                            stopdown2=i;
                            flamedown--;
                        }
                        flamedown--;
                    }
                    if(p.getBlock(blockX, blockY-i).getType()==2)
                    {
                        if(time>180&&time<183&&stopdown) {
                            stopdown=false;
                            stopdown2=i;
                            flamedown--;
                        }
                        flamedown--;
                    }
                    if(p.getBlock(blockX, blockY-i).getType()==3)
                    {
                        flamedown=flamedown-2;
                    }


                    if((i==flamedown||i-1==flamedown)&&stopdown)
                        p.shape(renderBomb(FLAMME), p.getBlock(blockX, blockY-i).getPosX(), p.getBlock(blockX, blockY-i).getPosY());
                    flamedown++;

                }
                if (blockY+i < p.getVertical_blocks()-1 && flameup==i)//p.getBlock(blockX, blockY + 1 + i).isWalkable() && y <= p.getBlock(blockX, blockY + i).getPosY() + p.getBlock_size()) {
                {
                    if(p.getBlock(blockX, blockY+i).getType()==1)
                    {
                        bombexplode(blockX, blockY+i);
                    }
                    if(time>182) {
                        if (blockX == xred && yred == blockY + i) {
                            p.b1.die();
                        }
                        if (blockX == xorange && yorange == blockY + i) {
                            p.b2.die();
                        }
                        if (blockX == xblue && yblue == blockY + i) {
                            p.b3.die();
                        }
                        if (blockX == xviolett && yviolett == blockY + i) {
                            p.b4.die();
                        }
                    }

                    if(p.getBlock(blockX, blockY+i).getCovered())
                    {
                        if(time>181&&time<183&&stopup) {
                            stopup=false;
                            stopup2=i;
                            flameup--;
                        }
                        flameup--;
                    }
                    if(p.getBlock(blockX, blockY+i).getType()==2)
                    {
                        if(time>180&&time<183&&stopup) {
                            stopup=false;
                            stopup2=i;
                            flameup--;
                        }
                        flameup--;
                    }
                    if(p.getBlock(blockX, blockY+i).getType()==3)
                    {
                        flameup=flameup-2;
                    }


                    if((i==flameup||i-1==flameup)&&stopup)
                        p.shape(renderBomb(FLAMME), p.getBlock(blockX, blockY + i).getPosX(), p.getBlock(blockX, blockY+i).getPosY());
                    flameup++;

                }
            }
            flameleft=1;
            flameright=1;
            flameup=1;
            flamedown=1;

            if(time==181) {
                if(p.getBlock(blockX-stopleft2, blockY).getCovered())
                    p.getBlock(blockX - stopleft2, blockY).setCovered(false);
                if(p.getBlock(blockX-stopleft2, blockY).getType()==2)
                    p.getBlock(blockX - stopleft2, blockY).setType(0);

                if(p.getBlock(blockX+stopright2, blockY).getCovered())
                    p.getBlock(blockX + stopright2, blockY).setCovered(false);
                if(p.getBlock(blockX+stopright2, blockY).getType()==2)
                    p.getBlock(blockX + stopright2, blockY).setType(0);

                if(p.getBlock(blockX, blockY+stopup2).getCovered())
                    p.getBlock(blockX, blockY+stopup2).setCovered(false);
                if(p.getBlock(blockX, blockY+stopup2).getType()==2)
                    p.getBlock(blockX, blockY+stopup2).setType(0);

                if(p.getBlock(blockX, blockY-stopdown2).getCovered())
                    p.getBlock(blockX, blockY-stopdown2).setCovered(false);
                if(p.getBlock(blockX, blockY-stopdown2).getType()==2)
                    p.getBlock(blockX, blockY-stopdown2).setType(0);
                stopleft2=0;
                stopright2=0;
                stopup2=0;
                stopdown2=0;
                this.used = false;
                stopleft=true;
                stopright=true;
                stopup=true;
                stopdown=true;
                p.getBlock(blockX,blockY).setType(Items.EMPTY);
            }
        } else if(time>170&&time<181)
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
