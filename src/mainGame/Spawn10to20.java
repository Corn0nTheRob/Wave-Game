package mainGame;

import java.util.ArrayList;
import java.util.Random;

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
	private int spawnTimer;
	private int levelTimer;
	private String[] side = { "left", "right", "top", "bottom" };
	ArrayList<Integer> levels = new ArrayList<Integer>();
	private int index;
	private int randomMax;
	private int levelNumber = 0;
	private int tempCounter = 0;
	public static int LEVEL_SET_2_RESET = 0;

	private double playerX;
	private double playerY;
	private double hGenTemp;
	private double wGenTemp;

	public Spawn10to20(Handler handler, HUD hud, Spawn1to10 spawner, Game game) {
		restart();
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		hud.restoreHealth();
		spawnTimer = 10;
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

		case 1:// this is level 1
			spawnTimer--;// keep decrementing the spawning spawnTimer 60 times a second
			levelTimer--;// keep decrementing the level spawnTimer 60 times a second
			if (tempCounter < 1) {// called only once, but sets the levelTimer to how long we want this level to
									// run for
				levelTimer = 2000;// 2000 / 60 method calls a second = 33.33 seconds long
				tempCounter++;// ensures the method is only called once
			}
			if (spawnTimer == 0) {// time to spawn another enemy
				
				findX();
				findY();
				//temporary enemy height generation variable -EH
				hGenTemp = r.nextInt(Game.HEIGHT);
				//temporary enemy width generation variable -EH
				wGenTemp = r.nextInt(Game.WIDTH);
				//this checks to see if the enemy width and height are both within 
				//75 pixels of the player's respective x and y coordinates.
				//if that is the case, it rerolls the enemy width and height. -EH
				while (Math.abs(wGenTemp - playerX) < 100 && Math.abs(hGenTemp - playerY) < 100) {
					wGenTemp = r.nextInt(Game.WIDTH);
					hGenTemp = r.nextInt(Game.HEIGHT);
				}
				
				//
				handler.addObject(
						new EnemyBasic(wGenTemp, hGenTemp, 9, 9, ID.EnemyBasic, handler));
				// add them to the handler, which handles all game objects
				spawnTimer = 100;// reset the spawn timer
			}
			if (levelTimer == 0) {// level is over
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				if (levelsRemaining == 1) {// time for the boss!
					levelNumber = 101;// arbitrary number for the boss level
				} else {// not time for the boss, just go to the next level
					levels.remove(index);// remove the current level from being selected
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);// pick another level at random
					levelNumber = levels.get(index);// set levelNumber to whatever index was randomly selected
				}
			} 
			break;
		case 2:
			spawnTimer--;
			levelTimer--;
			
			findX();
			findY();
			hGenTemp = r.nextInt(Game.HEIGHT);
			wGenTemp = r.nextInt(Game.WIDTH);
			while (Math.abs(wGenTemp - playerX) < 100 && Math.abs(hGenTemp - playerY) < 100) {
				wGenTemp = r.nextInt(Game.WIDTH);
				hGenTemp = r.nextInt(Game.HEIGHT);
			}
			
			if (tempCounter < 1) {
				levelTimer = 2000;
				tempCounter++;
			}
			if (spawnTimer == 30) {
				handler.addObject(
						new EnemySweep(wGenTemp, hGenTemp, 20, 2, ID.EnemySweep, handler));
			} else if (spawnTimer == 20) {
				handler.addObject(
						new EnemySweep(wGenTemp, hGenTemp, 20, -2, ID.EnemySweep, handler));
			} else if (spawnTimer == 10) {
				handler.addObject(
						new EnemySweep(wGenTemp, hGenTemp, 20, 4, ID.EnemySweep, handler));
			} else if (spawnTimer == 0) {
				handler.addObject(
						new EnemySweep(wGenTemp, hGenTemp, 20, -4, ID.EnemySweep, handler));
				spawnTimer = 80;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);
					levelNumber = levels.get(index);
				}
			}
			break;
		case 3:
			spawnTimer--;
			levelTimer--;
			
			findX();
			findY();
			hGenTemp = r.nextInt(Game.HEIGHT);
			wGenTemp = r.nextInt(Game.WIDTH);
			while (Math.abs(wGenTemp - playerX) < 100 && Math.abs(hGenTemp - playerY) < 100) {
				wGenTemp = r.nextInt(Game.WIDTH);
				hGenTemp = r.nextInt(Game.HEIGHT);
			}
			
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmart(wGenTemp, hGenTemp, -5, ID.EnemySmart, handler));
				spawnTimer = 100;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);
					levelNumber = levels.get(index);
				}
			} 
			break;
		case 4:
			levelTimer--;
			
			findX();
			findY();
			hGenTemp = r.nextInt(Game.HEIGHT) - 75;
			wGenTemp = r.nextInt(Game.WIDTH) - 35;
			while (Math.abs(wGenTemp - playerX) < 100 && Math.abs(hGenTemp - playerY) < 100) {
				
				//these two lines have constants subtracted from them
				//because thats how they were originally made when instantiated -EH
				wGenTemp = r.nextInt(Game.WIDTH) - 35;
				hGenTemp = r.nextInt(Game.HEIGHT) - 75;
			}
			
			if (tempCounter < 1) {
				handler.addObject(new EnemyShooter(wGenTemp, hGenTemp, 100, 100,
						-20, ID.EnemyShooter, this.handler));
				levelTimer = 1300;
				tempCounter++;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);
					levelNumber = levels.get(index);
				}
			} 
			break;
		case 5:
			spawnTimer--;
			levelTimer--;
			
			//this case statement doesn't generate enemies like the rest,
			//so it doesn't need my special generation parameters. -EH
			
			if (tempCounter < 1) {
				levelTimer = 1400;
				tempCounter++;
			}
			if (spawnTimer <= 0) {
				handler.addObject(new EnemyBurst(-200, 200, 50, 50, 200, side[r.nextInt(4)], ID.EnemyBurst, handler));
				spawnTimer = 180;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);
					levelNumber = levels.get(index);
				}
			} 
			break;
		case 6:
			spawnTimer--;
			levelTimer--;
			
			findX();
			findY();
			hGenTemp = r.nextInt(Game.HEIGHT);
			wGenTemp = r.nextInt(Game.WIDTH);
			while (Math.abs(wGenTemp - playerX) < 100 && Math.abs(hGenTemp - playerY) < 100) {
				wGenTemp = r.nextInt(Game.WIDTH);
				hGenTemp = r.nextInt(Game.HEIGHT);
			}
			
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemyBasic(wGenTemp, hGenTemp, 7, 7, ID.EnemyBasic, handler));
				spawnTimer = 50;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 40;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);
					levelNumber = levels.get(index);
				}
			} break;
		case 7:
			spawnTimer--;
			levelTimer--;
			
			findX();
			findY();
			hGenTemp = r.nextInt(Game.HEIGHT);
			wGenTemp = r.nextInt(Game.WIDTH);
			while (Math.abs(wGenTemp - playerX) < 100 && Math.abs(hGenTemp - playerY) < 100) {
				wGenTemp = r.nextInt(Game.WIDTH);
				hGenTemp = r.nextInt(Game.HEIGHT);
			}
			
			if (tempCounter < 1) {
				levelTimer = 1200;
				tempCounter++;
			}
			if (spawnTimer == 35) {
				handler.addObject(
						new EnemySweep(wGenTemp, hGenTemp, 25, 2, ID.EnemySweep, handler));
			} else if (spawnTimer == 25) {
				handler.addObject(
						new EnemySweep(wGenTemp, hGenTemp, 25, -2, ID.EnemySweep, handler));
			} else if (spawnTimer == 15) {
				handler.addObject(
						new EnemySweep(wGenTemp, hGenTemp, 25, 4, ID.EnemySweep, handler));
			} else if (spawnTimer == 0) {
				handler.addObject(
						new EnemySweep(wGenTemp, hGenTemp, 25, -4, ID.EnemySweep, handler));
				spawnTimer = 100;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);
					levelNumber = levels.get(index);
				}
			} break;
		case 8:
			spawnTimer--;
			levelTimer--;
			
			findX();
			findY();
			hGenTemp = r.nextInt(Game.HEIGHT);
			wGenTemp = r.nextInt(Game.WIDTH);
			while (Math.abs(wGenTemp - playerX) < 100 && Math.abs(hGenTemp - playerY) < 100) {
				wGenTemp = r.nextInt(Game.WIDTH);
				hGenTemp = r.nextInt(Game.HEIGHT);
			}
			
			if (tempCounter < 1) {
				levelTimer = 1000;
				tempCounter++;
			}
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmart(wGenTemp, hGenTemp, -3, ID.EnemySmart, handler));
				spawnTimer = 50;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);
					levelNumber = levels.get(index);
				}
			} break;
		case 9:
			levelTimer--;
			
			findX();
			findY();
			hGenTemp = r.nextInt(Game.HEIGHT) - 75;
			wGenTemp = r.nextInt(Game.WIDTH) - 35;
			while (Math.abs(wGenTemp - playerX) < 100 && Math.abs(hGenTemp - playerY) < 100) {
				wGenTemp = r.nextInt(Game.WIDTH) - 35;
				hGenTemp = r.nextInt(Game.HEIGHT) - 75;
			}
			
			if (tempCounter < 1) {
				handler.addObject(new EnemyShooter(wGenTemp, hGenTemp, 200, 200,
						-15, ID.EnemyShooter, this.handler));
				levelTimer = 2500;
				tempCounter++;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;
				if (levelsRemaining == 1) {
					levelNumber = 101;
				} else {
					levels.remove(index);
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);
					levelNumber = levels.get(index);
				}
			} break;
		
		case 10:
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1400;
				tempCounter++;
			}
			if (spawnTimer <= 0) {
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
				handler.addObject(new BossEye(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 - 150, ID.BossEye, handler, 1));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 150, ID.BossEye, handler, 2));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 50, Game.HEIGHT / 2 - 150, ID.BossEye, handler, 3));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 - 50, ID.BossEye, handler, 4));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 50, ID.BossEye, handler, 5));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 50, Game.HEIGHT / 2 - 50, ID.BossEye, handler, 6));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 + 50, ID.BossEye, handler, 7));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 + 50, ID.BossEye, handler, 8));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 50, Game.HEIGHT / 2 + 50, ID.BossEye, handler, 9));
				tempCounter++;
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

	public void findX() {
		playerX = Game.getPlayerX();
	}

	public void findY() {
		playerY = Game.getPlayerY();
	}

}
