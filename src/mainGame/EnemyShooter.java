package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class EnemyShooter extends GameObject {

	private Handler handler;
	private int sizeX;
	private int sizeY;
	private int timer;
	private GameObject player;
	private double bulletVelX;
	private double bulletVelY;
	private int bulletSpeed;
	private Image img;

	public EnemyShooter(double x, double y, int sizeX, int sizeY, int bulletSpeed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.velX = 0;
		this.velY = 0;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.timer = 60;
		this.bulletSpeed = bulletSpeed;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}
		
		img = null;
		try {
			img = ImageIO.read(new File("images/OctoBoi.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void tick() {
		this.x += velX;
		this.y += velY;

		if (this.y <= 0 || this.y >= Game.HEIGHT - 40)
			velY *= -1;
		if (this.x <= 0 || this.x >= Game.WIDTH - 16)
			velX *= -1;



		timer--;
		if (timer <= 0) {
			shoot();
			updateEnemy();
			timer = 10;
		}
		collision();
	}
		
	public void collision() {

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.PlayerBullet) {// tempObject is an enemy

				// collision code
				if (getBounds().intersects(tempObject.getBounds())) {// player hit an enemy
					this.sizeX--;
					this.sizeY--;
				}
			}
		}
	}

	public void shoot() {
		double diffX = this.x - player.getX() - 16;
		double diffY = this.y - player.getY() - 16;
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		////////////////////////////// pythagorean theorem
		////////////////////////////// above//////////////////////////////////////////////////
		bulletVelX = ((this.bulletSpeed / distance) * diffX); // numerator affects speed of enemy
		bulletVelY = ((this.bulletSpeed / distance) * diffY);// numerator affects speed of enemy

		handler.addObject(
				new EnemyShooterBullet(x + this.sizeX / 2, y + this.sizeY /2, bulletVelX, bulletVelY, ID.EnemyShooterBullet, this.handler));
	}

	public void updateEnemy() {
		this.sizeX--;
		this.sizeY--;

		if (sizeX <= 0) {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {

		g.drawImage(img, (int) x, (int) y, this.sizeX, this.sizeY, null);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, this.sizeX, this.sizeY);
	}

}
