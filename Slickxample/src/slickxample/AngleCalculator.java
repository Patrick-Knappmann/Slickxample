/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slickxample;

import org.newdawn.slick.Input;

/**
 *
 * @author PK
 */
public class AngleCalculator {
    
    private static Player player;

    public AngleCalculator(Player player) {
        this.player = player;
    }
    
    public static float getAngle(Input input, float projectileid) {
        float deltax = input.getMouseX() - projectileid - player.getxPos();
        float deltay = input.getMouseY() - projectileid - player.getyPos();
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
    public static float getDistance(Input input, float projectileid) {
        float deltax = input.getMouseX() - projectileid - player.getxPos();
        float deltay = input.getMouseY() - projectileid - player.getyPos();
        float distance = deltax*deltax + deltay*deltay;
        distance = (float) Math.sqrt(distance);
        return distance;
    }
    
    public static float getAngleInvY(Input input, float projectileid) {
        float deltax = input.getMouseX() - projectileid - player.getxPos();
        float deltay = -input.getMouseY() - projectileid + player.getyPos();
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
    public static float getAngleInvX(Input input, float projectileid) {
        float deltax = -input.getMouseX() + projectileid + player.getxPos();
        float deltay = input.getMouseY() + projectileid - player.getyPos();
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
    public static float getAngleToPlayer(float xPos, float yPos){
        float deltax = player.getxPos()  + player.texture.getWidth()/2 - xPos;
        float deltay = player.getyPos() - PlayerProjectileManager.ICEBALL_MIDDLEX - yPos;
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
}
