package mainGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import mainGame.Game.STATE;

/**
 * Handles all mouse input
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class MouseListener extends MouseAdapter {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Spawn1to10 spawner;
	private Spawn10to20 spawner2;
	private UpgradeScreen upgradeScreen;
	private Upgrades upgrades;
	private Player player;
	private String upgradeText;
	private Victory victory;
	private int buttonwidth = Game.WIDTH/4;
	private int buttonheight = Game.HEIGHT/5;

	public MouseListener(Game game, Handler handler, HUD hud, Spawn1to10 spawner, Spawn10to20 spawner2,
			UpgradeScreen upgradeScreen, Player player, Upgrades upgrades, Victory victory) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;
		this.spawner2 = spawner2;
		this.upgradeScreen = upgradeScreen;
		this.player = player;
		this.upgrades = upgrades;
		this.victory = victory;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.GameOver) {
			handler.object.clear();
			upgrades.resetUpgrades();
			hud.health = 100;
			hud.setScore(0);
			hud.setLevel(1);
			spawner.restart();
			spawner.addLevels();
			spawner2.restart();
			spawner2.addLevels();
			Spawn1to10.LEVEL_SET = 1;
			game.gameState = STATE.Menu;
		}
		
		else if (game.gameState == STATE.Victory) {
			handler.object.clear();
			upgrades.resetUpgrades();
			hud.health = 100;
			hud.setScore(0);
			hud.setLevel(1);
			spawner.restart();
			spawner.addLevels();
			spawner2.restart();
			spawner2.addLevels();
			Spawn1to10.LEVEL_SET = 1;
			game.gameState = STATE.Menu;
		}

		else if (game.gameState == STATE.Game) {

		}

		else if (game.gameState == STATE.Upgrade) {
			if (mouseOver(mx, my, 100, 300, 1721, 174)) {
				upgradeText = upgradeScreen.getPath(1);

				upgrades.activateUpgrade(upgradeText);

				upgradeScreen.removeUpgradeOption(1);

				game.gameState = STATE.Game;
			} else if (mouseOver(mx, my, 100, 300 + (60 + Game.HEIGHT / 6), 1721, 174)) {
				upgradeText = upgradeScreen.getPath(2);

				upgrades.activateUpgrade(upgradeText);

				upgradeScreen.removeUpgradeOption(2);

				game.gameState = STATE.Game;
			} else if (mouseOver(mx, my, 100, 300 + 2 * (60 + Game.HEIGHT / 6), 1721, 174)) {
				upgradeText = upgradeScreen.getPath(3);

				upgrades.activateUpgrade(upgradeText);

				upgradeScreen.removeUpgradeOption(3);

				game.gameState = STATE.Game;
			}

		}

		else if (game.gameState == STATE.Menu) {
			// Waves Button
			if (mouseOver(mx, my, ((Game.WIDTH - buttonwidth)/2), ((Game.HEIGHT - buttonheight)/2), buttonwidth, buttonheight)) {
				handler.object.clear();
				game.gameState = STATE.Game;
				handler.addObject(player);
				// handler.addPickup(new PickupHealth(100, 100, ID.PickupHealth,
				// "images/PickupHealth.png", handler));
			}

			// Help Button
			else if (mouseOver(mx, my, ((Game.WIDTH - buttonwidth)/2), ((Game.HEIGHT - buttonheight)*5/6), buttonwidth, buttonheight)) {
				JOptionPane.showMessageDialog(game,
						"Controls:"
								+ " Use either WASD or the Arrow Keys to move. \nYour character shoots in the direction you're moving in. \nSurvive and Fight as long as you can!");
			}

			// Credits
			else if (mouseOver(mx, my, ((Game.WIDTH - buttonwidth)*15/16), ((Game.HEIGHT - buttonheight)*5/6), buttonwidth, buttonheight)) {
				JOptionPane.showMessageDialog(game,
						"Made by the Brogrammers 2.0 for CSC 225. \n In Loving Memory of Christopher Cherry.");
			}

			// Quit Button
			else if (mouseOver(mx, my, ((Game.WIDTH - buttonwidth)/16), ((Game.HEIGHT - buttonheight)*5/6), buttonwidth, buttonheight)) {
				System.exit(1);
			}
		}

		// Back Button for Help screen
		else if (game.gameState == STATE.Help) {
			if (mouseOver (mx, my, ((Game.WIDTH - buttonwidth)/2), ((Game.HEIGHT - buttonheight)*5/6), buttonwidth, buttonheight)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * Helper method to detect is the mouse is over a "button" drawn via Graphics
	 * 
	 * @param mx
	 *            mouse x position
	 * @param my
	 *            mouse y position
	 * @param x
	 *            button x position
	 * @param y
	 *            button y position
	 * @param width
	 *            button width
	 * @param height
	 *            button height
	 * @return boolean, true if the mouse is contained within the button
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}
