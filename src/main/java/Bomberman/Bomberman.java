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
    private Boolean invulnerable;
    private Boolean alive, dying,playing;
    private Integer bombcount, maxbombcount, range, life;
    public Float speed;
    PImage img;
    public Float count = 60.0f;

    private Float maxLeft, maxRight, maxUp, maxDown;
    private Integer invultime;

    private int CORNER; // Toleranz beim um die Ecke gehen, ToDo: Toleranz besser in %
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
    public static final int DYING1 = 51;
    public static final int DYING2 = 52;
    private int direction = STOP;
    private ConcurrentHashMap<Integer, PShape> bman = new ConcurrentHashMap<Integer, PShape>();


    public Bomberman(Field p, String id, Integer blockX, Integer blockY, Boolean inverted) {
        this.p = p;
        this.id = id;
        this.blockX = blockX;
        this.blockY = blockY;
        this.inverted = inverted;
        this.x = p.getBlock(blockX, blockY).getPosX();
        this.y = p.getBlock(blockX, blockY).getPosY();
        bombcount=1;
        maxbombcount=1;
        range=1;
        life=3;
        speed=2.0f;
        invulnerable=false;
        alive=false;
        dying=false;
        playing=false;
        invultime=0;
        this.CORNER = (int)(p.getBlock_size() * 0.75f);

    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Integer getBombcount() {
        return bombcount;
    }

    public Integer getMaxbombcount() {
        return maxbombcount;
    }

    public Integer getRange() {
        return range;
    }

    public Integer getLife() {
        return life;
    }


    public boolean isAlive() {
        return alive;
    }
    public void setAlive() {
        alive = !alive;
    }
    public boolean isDying() {
        return dying;
    }

    public void setPlaying(Boolean playing) {
        this.playing = playing;
    }

    public Boolean isPlaying() {
        return playing;
    }

    public Boolean isInvulnerable() {
        return invulnerable;
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

    public void increaseSpeed() {
        if(speed < 4.0f)
            speed = speed+0.2f;
    }

    public void setInverted(Boolean inverted) {
        this.inverted = inverted;
    }

    public Boolean isInverted() {
        return inverted;
    }

    public String getId() {
        return id;
    }
    
    public void increaseBombcount()
    {
        if(bombcount < maxbombcount)
            bombcount++;
    }
    public void increasemaxBombCount()
    {
        if(maxbombcount<4)
        {
            maxbombcount++;
            increaseBombcount();
        }
    }
    public void increaseLife()
    {
        life++;

    }
    public void increaseRange()
    {
        if(range <9) {
            range++;
            if (id.equals("red")) {
                for (int i = 0; i < 5; i++) {
                    p.bombfield[i].setRange(this.range);
                }

            }
            if (id.equals("orange")) {
                for (int i = 5; i < 10; i++) {
                    p.bombfield[i].setRange(this.range);
                }
            }
            if (id.equals("blue")) {
                for (int i = 10; i < 15; i++) {
                    p.bombfield[i].setRange(this.range);
                }
            }
            if (id.equals("violett")) {
                for (int i = 15; i < 20; i++) {
                    p.bombfield[i].setRange(this.range);
                }
            }
        }
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
                case DYING1:
                    img = p.getImage(id + "_dying_1.png");
                    break;
                case DYING2:
                    img = p.getImage(id + "_dying_2.png");
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
        if (x >= maxLeft && y >= p.getBlock(blockX, blockY).getPosY()-(CORNER + speed) && y <= p.getBlock(blockX, blockY).getPosY()+(CORNER + speed) &&!dying) {
            if(this.invulnerable&&this.invultime==0)
                this.invulnerable = false;
            this.y = p.getBlock(blockX, blockY).getPosY();
            this.x -= speed;
        }
    }
    public void moveRight() {
        if (x <= maxRight && y >= p.getBlock(blockX, blockY).getPosY()-(CORNER + speed) && y <= p.getBlock(blockX, blockY).getPosY()+(CORNER + speed)&&!dying) {
            if(this.invulnerable&&this.invultime==0)
                this.invulnerable = false;
            this.y = p.getBlock(blockX, blockY).getPosY();
            this.x += speed;
        }
    }
    public void moveUp() {
        if (y >= maxUp && x >= p.getBlock(blockX, blockY).getPosX()-(CORNER + speed) && x <= p.getBlock(blockX, blockY).getPosX()+(CORNER + speed)&&!dying) {
            if(this.invulnerable&&this.invultime==0)
                this.invulnerable = false;
            this.y -= speed;
            this.x = p.getBlock(blockX, blockY).getPosX();
        }
    }
    public void moveDown() {
        if (y <= maxDown && x >= p.getBlock(blockX, blockY).getPosX()-(CORNER + speed) && x <= p.getBlock(blockX, blockY).getPosX()+(CORNER + speed)&&!dying) {
            if(this.invulnerable&&this.invultime==0)
                this.invulnerable = false;
            this.y += speed;
            this.x = p.getBlock(blockX, blockY).getPosX();
        }
    }
    
    public void draw()
    {
        if(invultime>0) {
            invultime--;
        }
        else if(invultime==0&&dying) {
            die();

        }
        if(dying) {
            if (count < 25) {
                displayDirection(Bomberman.DYING1);
                count++;
            }
            if (count >= 25) {
                displayDirection(Bomberman.DYING2);
                count++;
                if (count >= 50)
                    count = 0.0f;
            }
        }
        else {
            if (direction == 0) {
                displayDirection(Bomberman.STOP);
            }
            if (direction == 10) {
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
            if (direction == 20) {
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
            if (direction == 30) {
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
            if (direction == 40) {
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


    }
    
    public void dropBomb() {
        /*
        Block bomb_block = p.getBlock(blockX, blockY);
        bomb_block.setType(Items.BOMBE); */

        if(id.equals("red")&&bombcount > 0&&alive&&!dying&&!invulnerable&&p.getBlock(blockX,blockY).getType()==Items.EMPTY)
        {
            for(int i=0;i<5;i++)
            {
                if(p.bombfield[i].isUsed()==false)
                {
                    p.bombfield[i].setBomb(this.blockX, this.blockY);
                    p.getBlock(blockX,blockY).setType(Items.BOMBE);
                    bombcount--;
                    i=5;
                }

            }

        }
        if(id.equals("orange")&&bombcount > 0&&alive&&!dying&&!invulnerable&&p.getBlock(blockX,blockY).getType()==Items.EMPTY)
        {
            for(int i=5;i<10;i++)
            {
                if(p.bombfield[i].isUsed()==false)
                {
                    p.bombfield[i].setBomb(this.blockX, this.blockY);
                    p.getBlock(blockX,blockY).setType(Items.BOMBE);
                    bombcount--;
                    i=10;
                }

            }

        }
        if(id.equals("blue")&&bombcount > 0&&alive&&!dying&&!invulnerable&&p.getBlock(blockX,blockY).getType()==Items.EMPTY)
        {
            for(int i=10;i<15;i++)
            {
                if(p.bombfield[i].isUsed()==false)
                {
                    p.bombfield[i].setBomb(this.blockX, this.blockY);
                    p.getBlock(blockX,blockY).setType(Items.BOMBE);
                    bombcount--;
                    i=15;
                }

            }

        }
        if(id.equals("violett")&&bombcount > 0&&alive&&!dying&&!invulnerable&&p.getBlock(blockX,blockY).getType()==Items.EMPTY)
        {
            for(int i=15;i<20;i++)
            {
                if(p.bombfield[i].isUsed()==false)
                {
                    p.bombfield[i].setBomb(this.blockX, this.blockY);
                    p.getBlock(blockX,blockY).setType(Items.BOMBE);
                    bombcount--;
                    i=20;
                }

            }

        }



    }
    public void lostgame()
    {
        alive=false;
    }

    public void startDie() {
        if(invulnerable==false)
        {
            dying=true;
            invultime=300;
            invulnerable = true;
        }


    }
    public void die() {


                    if (id == "red") {
                        blockX = 0;
                        blockY = 0;
                    }
                    if (id == "orange") {
                        blockX = p.getHorizontal_blocks() - 1;
                        blockY = 0;
                    }
                    if (id == "blue") {
                        blockX = 0;
                        blockY = p.getVertical_blocks() - 1;
                    }
                    if (id == "violett") {
                        blockX = p.getHorizontal_blocks() - 1;
                        blockY = p.getVertical_blocks() - 1;

                    }
                    this.x = p.getBlock(blockX, blockY).getPosX();
                    this.y = p.getBlock(blockX, blockY).getPosY();


                dying=false;
                if (life > 1)
                    life--;
                else
                    lostgame();


    }

    private void checkItem(int x, int y) {
        Block b = p.getBlock(x,y);
        switch (b.getType()) {
            case Items.LIFEUPGRADE:
                this.increaseLife();
                b.setType(Items.EMPTY);
                p.setFloatingUpgrade(1,  id);
                break;
            case Items.RANGEUPGRADE:
                this.increaseRange();
                b.setType(Items.EMPTY);
                p.setFloatingUpgrade(2, id);
                break;
            case Items.SPEEDUPGRADE:
                this.increaseSpeed();
                b.setType(Items.EMPTY);
                p.setFloatingUpgrade(3, id);
                break;
            case Items.COUNTUPGRADE:
                this.increasemaxBombCount();
                b.setType(Items.EMPTY);
                p.setFloatingUpgrade(4,id);
                break;
            /*
            case Items.BOMBE:
                this.increaseBombcount();
                b.setType(Items.EMPTY);
                break;
                */
            default:
                break;
        }
    }

    private void updateBlock() {

        this.blockX = (int) ((x+p.getBlock_size()/2)-p.getX_offset())/p.getBlock_size();
        this.blockY = (int) ((y+p.getBlock_size()/2)-p.getY_offset())/p.getBlock_size();

        this.checkItem(this.blockX, this.blockY);

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
