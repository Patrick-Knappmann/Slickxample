/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slickxample;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Patrick
 */
public class SkeletonMage extends Entity {

    private MoveStrategy moveStrat;
    private int moveCounter;
    private boolean recentlyFled;

    public SkeletonMage(float xPos, float yPos) {
        super(xPos, yPos);
        try {
            super.texture = new Image("res/SkeletonMage.png");
        } catch (SlickException ex) {
            Logger.getLogger(SkeletonMage.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.bounds = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
        setMaxLife(50);
        super.speed = 0.08f;
        super.pathingX = 7;
        super.pathingY = 36;
        super.pathing = new Rectangle(xPos + pathingX, yPos + pathingY, 16, 9);
        moveStrat = MoveRegister.getCaster1();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {

        if (MathTool.getDistance(xPos + texture.getWidth() / 2, yPos + texture.getHeight() / 2) < 100 && !recentlyFled) {
            moveStrat = MoveRegister.getFlee();
            moveCounter = 1;
            recentlyFled = true;
        }
        if (moveCounter >= 4200) {
            moveCounter = 0;

            recentlyFled = false;
        } else {
            if (moveCounter >= 1950) {
                moveStrat = MoveRegister.getIdle();
            } else {
                if (moveCounter == 0) {
                    if (MathTool.getDistance(xPos + texture.getWidth() / 2, yPos + texture.getHeight() / 2) > 300) {
                        moveStrat = MoveRegister.getCasterTowards();
                        moveCounter = 500;
                    } else {
//                        int i = RandomTool.getRandom().nextInt(3);
//                        if (i <= 1) {
//                            moveStrat = MoveRegister.getIdle();
//                        }
//                        if (i == 2) {
                            moveStrat = MoveRegister.getCaster1();

//                        }
                    }
                }
            }
            moveCounter += (RandomTool.getRandom().nextInt(2) + 1) * delta;
        }

        moveStrat.move(this, delta);
        super.update(container, game, delta);
        System.out.println(moveCounter);

    }

}
