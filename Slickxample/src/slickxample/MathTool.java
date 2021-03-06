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
public class MathTool {
    
    private static Player player;
    private static LavaFlow lavaFlow;

    public MathTool(Player player, LavaFlow lavaFlow) {
        this.player = player;
        this.lavaFlow = lavaFlow;
    }
    
    public static float getAngle(Input input, float projectileid) {
        float deltax = input.getMouseX() - projectileid - (player.getxPos());
        float deltay = input.getMouseY() - projectileid - (player.getyPos());
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
    public static float getDistanceToPlayer(float xPos, float yPos) {
        float deltax = xPos - (player.getxPos() + player.texture.getWidth()/2);
        float deltay = yPos - (player.getyPos() + player.texture.getHeight()/2);
        float distance = deltax*deltax + deltay*deltay;
        distance = (float) Math.sqrt(distance);
        return distance;
    }
    
    public static float getDistanceBetweenEntities(Entity e1, Entity e2) {
        float deltax = e1.getxPos() + e1.texture.getWidth()/2 - (e2.getxPos() + e2.texture.getWidth()/2);
        float deltay = e1.getyPos() + e1.texture.getHeight()/2 - (e2.getyPos() + e2.texture.getHeight()/2);
        float distance = deltax*deltax + deltay*deltay;
        distance = (float) Math.sqrt(distance);
        return distance;
    }
    
    public static float getDistanceToLava(Entity e) {
        float distance = lavaFlow.getYPos() - (e.getyPos() + e.getTexture().getHeight()/2);
        return distance;
    }
    
    public static float getDistanceBetweenPoints(float x1, float y1, float x2, float y2) {
        float deltax = x1 - x2;
        float deltay = y1 - y2;
        float distance = deltax*deltax + deltay*deltay;
        distance = (float) Math.sqrt(distance);
        return distance;
    }
    
    public static float getAngleInvY(Input input, float projectileid) {
        float deltax = input.getMouseX() - projectileid - (player.getxPos());
        float deltay = -input.getMouseY() - projectileid + (player.getyPos());
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
    public static float getAngleInvX(Input input, float projectileid) {
        float deltax = -input.getMouseX() + projectileid + (player.getxPos());
        float deltay = input.getMouseY() + projectileid - (player.getyPos());
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
    public static float getAngleToPlayer(float xPos, float yPos){
        float deltax = (player.getxPos() + player.texture.getWidth()/2) - xPos;
        float deltay = (player.getyPos() + player.texture.getHeight()/2) - yPos;
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
    public static float getAngleToPlayerInvY(float xPos, float yPos){
        float deltax = (player.getxPos() + player.texture.getWidth()/2) - xPos;
        float deltay = (-player.getyPos() + player.texture.getHeight()/2) + yPos;
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
    public static float getAngleToPlayerInvX(float xPos, float yPos){
        float deltax = (player.getxPos() + player.texture.getWidth()/2) + xPos;
        float deltay = (player.getyPos() + player.texture.getHeight()/2) - yPos;
        float angle = (float) Math.atan2(deltax, deltay);
        return angle;
    }
    
}
