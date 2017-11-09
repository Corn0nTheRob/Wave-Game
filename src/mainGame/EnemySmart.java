package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class EnemySmart extends GameObject {

	private Handler handler;
	private GameObject player;
	private int speed;
	private Image img;

	public EnemySmart(double x, double y, int speed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speed = speed;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		

			try {
				img = Toolkit.getDefaultToolkit().getImage("images/Leech.gif");
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Image getImage(String path) {
		Image image = null;
		try {
			URL imageURL = Game.class.getResource(path);
			image = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return image;
	}
	


	public void tick() {
		this.x += velX;
		this.y += velY;
		////////////////////////////// pythagorean theorem
		////////////////////////////// below//////////////////////////////////////////////////
		double diffX = this.x - player.getX() - 16;
		double diffY = this.y - player.getY() - 16;
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		////////////////////////////// pythagorean theorem
		////////////////////////////// above//////////////////////////////////////////////////
		velX = ((this.speed / distance) * diffX); // numerator affects speed of enemy
		velY = ((this.speed / distance) * diffY);// numerator affects speed of enemy

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 40) velY *= -1;
		// if (this.x <= 0 || this.x >= Game.WIDTH - 16) velX *= -1;

		if (this.y >= Game.HEIGHT * 2 || this.y <= Game.HEIGHT *-2 || this.x >= Game.WIDTH * 2 || this.x <= Game.WIDTH * -2) {
			handler.removeObject(this);
			System.out.print("Smart is removed");
		}

	}

	public void render(Graphics g) {
		g.drawImage(img, (int) this.x, (int) this.y, 48, 100, null);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}
