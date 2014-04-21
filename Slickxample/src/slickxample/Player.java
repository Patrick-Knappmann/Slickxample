/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slickxample;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Patrick
 */
public class Player extends Entity {

    private PlayerProjectileManager projectiles;
    private Input input;
    private float reloadTime;
    private final float doubleDirectionMultiplier = 0.707114f;
    private float originalSpeed;

    public Player(float xPos, float yPos, Image texture, PlayerProjectileManager projectiles) {
        super(xPos, yPos);
        try {
            super.texture = new Image("res/Player.png");
        } catch (SlickException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.bounds = new Rectangle(xPos + 3, yPos + 3, texture.getWidth() - 6, texture.getHeight() - 6);
        this.projectiles = projectiles;
        super.speed = 0.1f;
        super.pathingX = 2;
        super.pathingY = 10;
        super.pathing = new Rectangle(xPos + pathingX, yPos + pathingY, 8, 10);
        originalSpeed = speed;
        setMaxLife(100);
    }

//    public void render(GameContainer container, StateBasedGame game, Graphics g) {
//        super.render(container, game, g);
//    }
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        reloadTime -= 0.5 * delta;
        TileManager.setScrollspeed(0);
        reactToInput(container, delta);
        super.update(container, game, delta);
    }

    private void reactToInput(GameContainer container, int delta) {
        input = container.getInput();
        if ((input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_A)) && (!(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_W)) && (!(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_D))))) {
            speed = doubleDirectionMultiplier * originalSpeed;
        } else {
            speed = originalSpeed;

        }
        if (input.isKeyDown(Input.KEY_S)) {
//            yPos += speed * delta;
            TileManager.setScrollspeed(speed * -1);
        }
        if (input.isKeyDown(Input.KEY_W)) {
//            yPos -= speed * delta;
            TileManager.setScrollspeed(speed);
        }
        if (input.isKeyDown(Input.KEY_D)) {
            xPos += speed * delta;
        }
        if (input.isKeyDown(Input.KEY_A)) {
            xPos -= speed * delta;
        }
        if (input.isKeyDown(Input.KEY_SPACE)) {
            if (reloadTime <= 0) {
                projectiles.SpawnProjectile(xPos, yPos, MathTool.getAngle(input, PlayerProjectileManager.ICEBALL_MIDDLEX), 0);
                reloadTime = PlayerProjectileManager.ICEBALL_RELOAD;
            }
        } else {
            if (input.isKeyDown(Input.KEY_E)) {
                if (reloadTime <= 0) {
                    projectiles.SpawnProjectile(xPos, yPos, MathTool.getAngle(input, PlayerProjectileManager.LIGHTNING_MIDDLEX), 2);
                    reloadTime = PlayerProjectileManager.LIGHTNING_RELOAD;
                }
            } else {
                if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                    if (reloadTime <= 0) {
                        projectiles.SpawnProjectile(xPos, yPos, MathTool.getAngle(input, PlayerProjectileManager.LAVASPRAY_MIDDLEX), 1);
                        reloadTime = PlayerProjectileManager.LAVASPRAY_RELOAD;
                    }
                }
            }

        }
    }

    @Override
    public void updateBounds() {
        bounds.setLocation(xPos + 3, yPos + 3);
    }
    
    public void reset(){
        setxPos(400);
        setyPos(300);
        setLife(100);
        reloadTime = 400;
    }
    
    

}
