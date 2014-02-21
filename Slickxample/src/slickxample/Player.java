/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slickxample;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Patrick
 */
public class Player extends Entity {

    private PlayerProjectileManager projectiles;
    private Input input;
    private float reloadTime;

    public Player(float xPos, float yPos, Image texture, PlayerProjectileManager projectiles) {
        super(xPos, yPos, texture);
        this.projectiles = projectiles;
        super.speed = 0.1f;
        super.pathingX = 2;
        super.pathingY = 10;
        super.pathing = new Rectangle(xPos + pathingX, yPos + pathingY, 8, 10);
    }

//    public void render(GameContainer container, StateBasedGame game, Graphics g) {
//        super.render(container, game, g);
//    }
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        input = container.getInput();
        reloadTime -= 1 * delta;
        if (input.isKeyDown(Input.KEY_S)) {
            yPos += speed * delta;
        }
        if (input.isKeyDown(Input.KEY_W)) {
            yPos -= speed * delta;
        }
        if (input.isKeyDown(Input.KEY_D)) {
            xPos += speed * delta;
        }
        if (input.isKeyDown(Input.KEY_A)) {
            xPos -= speed * delta;
        }
        if (input.isKeyDown(Input.KEY_SPACE)) {
            if (reloadTime <= 0) {
                projectiles.SpawnProjectile(xPos, yPos, AngleCalculator.getAngle(input, PlayerProjectileManager.ICEBALL_MIDDLEX), 0);
                reloadTime = PlayerProjectileManager.ICEBALL_RELOAD;
            }
        } else {
            if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                if (reloadTime <= 0) {
                    projectiles.SpawnProjectile(xPos, yPos, AngleCalculator.getAngle(input, PlayerProjectileManager.LAVASPRAY_MIDDLEX) + (RandomTool.getRandom().nextFloat() / 3 - 0.1666f), 1);
                    reloadTime = PlayerProjectileManager.LAVASPRAY_RELOAD;
                }
            }
        }
        super.update(container, game, delta);

    }

//    public float getAngle(float projectileid) {
//        float deltax = input.getMouseX() - projectileid - xPos;
//        float deltay = input.getMouseY() - projectileid - yPos;
//        float angle = (float) Math.atan2(deltax, deltay);
//        return angle;
//    }
}
