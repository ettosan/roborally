package inf112.skeleton.app.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ProgramRegisterGFX {
    private Texture texture;
    private Texture textureP;
    private Texture damage;
    private Texture damageRed;
    private Texture damageBack;
    private Texture lives;
    private Texture livesBack;
    private Texture powerDownBack;
    private Texture powerDown;
    private Sprite spritePowerDown;
    private Sprite spritePowerDownBack;
    private Sprite[] livesArrBack;
    private Sprite[] livesArr;
    private Sprite[] damageArr;
    private Sprite spriteDamageRed;
    private Sprite[] damageArrback;
    private Sprite spriteP;
    private BitmapFont font;
    private int xPosition;
    private int yPosition;


    public ProgramRegisterGFX(int xPos, int yPos){
        xPosition = xPos;
        yPosition = yPos;
        textureP = new Texture(Gdx.files.internal("assets/programRegister.png"));
        lives = new Texture(Gdx.files.internal("assets/lives.png"));
        livesBack = new Texture(Gdx.files.internal("assets/lives_background.png"));
        powerDownBack = new Texture(Gdx.files.internal("assets/powerDown_background.png"));
        powerDown = new Texture(Gdx.files.internal("assets/powerDown.png"));
        spritePowerDownBack = new Sprite(powerDownBack);
        spritePowerDown = new Sprite(powerDown);
        spritePowerDownBack.setPosition(xPos + 10,yPos + 125);
        spritePowerDown.setPosition(xPos + 10,yPos + 125);
        font = new BitmapFont();


        spriteP = new Sprite(textureP);
        spriteP.setPosition(xPos, yPos);
        damage = new Texture(Gdx.files.internal("assets/damage.png"));
        damageArr = new Sprite[9];
        damageRed = new Texture(Gdx.files.internal("assets/damage_red.png"));

        spriteDamageRed = new Sprite(damageRed);
        spriteDamageRed.setPosition(xPos + 40, yPos + 87);
        damageBack = new Texture(Gdx.files.internal("assets/damage_background.png"));
        damageArrback = new Sprite[9];
        for(int i = 0; i < damageArrback.length; i++){
            damageArrback[i] = new Sprite(damageBack);
            damageArrback[i].setPosition((xPos + 87 + (i*50)), yPos + 87);
        }
        for(int i = 0; i < damageArr.length; i++){
            damageArr[i] = new Sprite(damage);
            damageArr[i].setPosition((xPos + 90 + (i*50)), yPos + 90);
        }
        livesArr = new Sprite[3];
        for(int i = 0; i < livesArr.length; i++){
            livesArr[i] = new Sprite(lives);
            livesArr[i].setPosition((xPos + 241 + (i*50)), yPos + 141);
        }
        livesArrBack = new Sprite[3];
        for(int i = 0; i < livesArrBack.length; i++){
            livesArrBack[i] = new Sprite(livesBack);
            livesArrBack[i].setPosition((xPos + 240 + (i*50)), yPos + 140);
        }



    }
    public void render(SpriteBatch batch, int damage, int lives, boolean isPowerDown, int flagCounter){
        spriteP.draw(batch);
        spriteDamageRed.draw(batch);
        if(isPowerDown){
            spritePowerDown.draw(batch);
        } else {
            spritePowerDownBack.draw(batch);
        } for (int i = 0; i < damageArrback.length; i++){
            damageArrback[i].draw(batch);
        } for (int i = 0; damage > i; i++){
            damageArr[8-i].draw(batch);
        } for (int i = 0; i < livesArrBack.length; i++){
            livesArrBack[i].draw(batch);
        } for (int i = 0; i < lives; i++){
            livesArr[i].draw(batch);
        }
        font.draw(batch,"Flag Counter:  ".concat(Integer.toString(flagCounter)), xPosition+440, yPosition+160);
    }
}
