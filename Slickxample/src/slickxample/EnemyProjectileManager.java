/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slickxample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import static slickxample.PlayerProjectileManager.iceParticleCreationCount;

/**
 *
 * @author PK
 */
public class EnemyProjectileManager {

    private Player player;
    private ArrayList<Projectile> projectiles;
    private Iterator<Projectile> projectileIterator;

    public EnemyProjectileManager(Player player) {
        this.player = player;
    }

    public void SpawnProjectile(float xPos, float yPos, float angle, int id) {
        try {
            switch (id) {
                case 0:
                    throw new SlickException("h");

            }
            MainMenu.count++;
        } catch (SlickException ex) {
            Logger.getLogger(PlayerProjectileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile p = projectileIterator.next();
            p.render(container, game, g);
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
        projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile p = projectileIterator.next();
            p.setYPos((p.getYPos() + TileManager.getScrollspeed() * delta));
            p.update(container, game, delta);
            checkCollision(p);
            if (p.getYPos() < -100 || p.getYPos() > container.getHeight() + 100 || p.getXPos() < -100 || p.getXPos() > container.getWidth() + 100 || p.getLifeTime() <= 0) {
                projectileIterator.remove();
                MainMenu.count--;
            }
        }
    }

    public void checkCollision(Projectile p) {
        if (p.checkCollision(player.getBounds())) {
            p.collision(player);
        }
    }
}
