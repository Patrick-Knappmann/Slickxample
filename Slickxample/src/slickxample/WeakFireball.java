/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slickxample;

import org.newdawn.slick.Image;

/**
 *
 * @author PK
 */
public class WeakFireball extends Projectile{

    public WeakFireball(float xPos, float yPos, float angle, float angleInvX, Image texture) {
        super(xPos, yPos, angle, texture);
        super.damage = 10;
        super.texture.setCenterOfRotation(7, 7);
        super.speed = 0.2f;
        super.texture.setRotation((float) Math.toDegrees(angleInvX));
    }
    
}
