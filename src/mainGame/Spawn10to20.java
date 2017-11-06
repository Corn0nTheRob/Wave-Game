package mainGame;

import java.util.ArrayList;
import java.util.Random;

import mainGame.Game.STATE;

/**
 * This class closely resembles Spawn1to10. Please refer to that class for
 * documentation
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class Spawn10to20 {

	private Handler handler;
	private HUD hud;
	private Game game;
	private int scoreKeep = 0;
	private Random r = new Random();
	private int timer;
	private int levelTimer;
	private String[] side = { "left", "right", "top", "bottom" };
	ArrayList<Integer> levels = new ArrayList<Integer>();
	private int index;
	private int randomMax;
	private int levelNumber = 0;
	private int tempCounter = 0;
	public static int LEVEL_SET_2_RESET = 0;
	public int BossEyeTimer = 3600;

	public Spawn10to20(Handler handler, HUD hud, Spawn1to10 spawner, Game game) {
		restart();
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		hud.restoreHealth();
		timer = 10;
		levelTimer = 150;
		randomMax = 10;
		hud.setLevel(1);
		tempCounter = 0;
		addLevels();
		index = r.nextInt(randomMax);
		levelNumber = 0;
	}

	public void addLevels() {
		for (int i = 1; i <= 10; i++) {
			levels.add(i);
		}
	}

	public void tick() {
		// if(LEVEL_SET_2_RESET < 1){
		// restart();
		// LEVEL_SET_2_RESET ++;
		// }
		switch (levelNumber) {
		
		case 0:
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 200, "Same levels...",
						ID.Levels1to10Text));
				handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2, "...but a little harder now",
						ID.Levels1to10Text));
				tempCounter++;
			}
			if (levelTimer <= 0) {
				handler.clearEnemies();
				tempCounter = 0;
				levelNumber = levels.get(index);
			}

		break;

		case 1:
			timer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (timer == 0) {
				handler.addObject(
						new EnemyBasic(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 13, 13, ID.EnemyBasic, handler));
				timer = 80;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				timer = 40;
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
				break;
		case 2:
			timer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (timer == 30) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, 2, ID.EnemySweep, handler));
			} else if (timer == 20) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, -2, ID.EnemySweep, handler));
			} else if (timer == 10) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, 4, ID.EnemySweep, handler));
			} else if (timer == 0) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, -4, ID.EnemySweep, handler));
				timer = 45;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
		case 3:
			timer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (timer == 0) {
				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -7, ID.EnemySmart, handler));
				timer = 60;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				timer = 10;
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
				break;
		case 4:
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 100, 100,
						-30, ID.EnemyShooter, this.handler));
				levelTimer = 1300;
				tempCounter++;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				timer = 10;
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
				break;
		case 5:
			timer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1400;
				tempCounter++;
			}
			if (timer <= 0) {
				handler.addObject(new EnemyBurst(-250, 250, 75, 75, 250, side[r.nextInt(4)], ID.EnemyBurst, handler));
				timer = 120;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				timer = 10;
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
		case 6:
			timer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (timer == 0) {
				handler.addObject(
						new EnemyBasic(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 15, 15, ID.EnemyBasic, handler));
				timer = 50;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				timer = 40;
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
				break;
		case 7:
			timer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (timer == 35) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, 2, ID.EnemySweep, handler));
			} else if (timer == 25) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, -2, ID.EnemySweep, handler));
			} else if (timer == 15) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, 4, ID.EnemySweep, handler));
			} else if (timer == 0) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, -4, ID.EnemySweep, handler));
				timer = 30;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
				break;
		case 8:
			timer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1000;
				tempCounter++;
			}
			if (timer == 0) {
				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -9, ID.EnemySmart, handler));
				timer = 50;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				timer = 10;
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
		case 9:
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 200, 200,
						-40, ID.EnemyShooter, this.handler));
				levelTimer = 2500;
				tempCounter++;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				timer = 10;
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
		case 10:
			timer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1400;
				tempCounter++;
			}
			if (timer <= 0) {
				handler.addObject(new EnemyBurst(-300, 300, 60, 60, 300, side[r.nextInt(4)], ID.EnemyBurst, handler));
				timer = 60;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				timer = 10;
				tempCounter = 0;
				if (randomMax == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					randomMax--;
					index = r.nextInt(randomMax);
					levelNumber = levels.get(index);
				}
			}
			break;

		case 101:
			if (tempCounter < 1) {
				handler.addObject(new BossEye(Game.WIDTH / 2 - 300, Game.HEIGHT / 2 - 300, ID.BossEye, handler, 1));
				handler.addObject(new BossEye(Game.WIDTH / 2   -50, Game.HEIGHT / 2 - 300, ID.BossEye, handler, 2));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 200, Game.HEIGHT / 2 - 300, ID.BossEye, handler, 3));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 300, Game.HEIGHT / 2   -50, ID.BossEye, handler, 4));
				handler.addObject(new BossEye(Game.WIDTH / 2  -50 , Game.HEIGHT / 2   -50, ID.BossEye, handler, 5));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 200, Game.HEIGHT / 2   -50, ID.BossEye, handler, 6));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 300, Game.HEIGHT / 2 + 200, ID.BossEye, handler, 7));
				handler.addObject(new BossEye(Game.WIDTH / 2   -50, Game.HEIGHT / 2 + 200, ID.BossEye, handler, 8));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 200, Game.HEIGHT / 2 + 200, ID.BossEye, handler, 9));
				tempCounter++; 
				} else if (tempCounter == 1) {
//					LEVEL_SET_2_RESET++; //game automatically goes to victory screen when boss appears, CHANGE
//					game.gameState = STATE.Victory;
						for (int i = 0; i < handler.object.size(); i++) {
							GameObject tempObject = handler.object.get(i);
							if (tempObject.getId() == ID.BossEye) {
								if (tempObject.getHealth() <= 0) {
									handler.removeObject(tempObject);
									//handler.clearEnemies();
									LEVEL_SET_2_RESET++;
									game.gameState = STATE.Victory;
								}
							}
						}
					}
				

		}
		// WINNER
		// else if(levelNumber){
		// levelTimer --;
		// if(tempCounter < 1){
		// handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 200,
		// "Same levels...", ID.Levels1to10Text));
		// handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2,
		// "...but a little harder now", ID.Levels1to10Text));
		// tempCounter++;
		// }
		// if(levelTimer <= 0){
		// handler.clearEnemies();
		// tempCounter = 0;
		// levelNumber = levels.get(index);
		// }
		//
		// }

	}

	public void skipLevel() {
		if (randomMax == 1) {
			tempCounter = 0;
			levelNumber = 101;
		} else if (randomMax > 1) {
			levels.remove(index);
			randomMax--;
			tempCounter = 0;
			index = r.nextInt(randomMax);
			levelNumber = levels.get(index);
		}
	}

	public void restart() {
		levelNumber = -10;
		tempCounter = 0;
		levelTimer = 150;
		randomMax = 10;
		index = r.nextInt(randomMax);

	}

}
