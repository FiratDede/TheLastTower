import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {
	BufferedImage image1; // castle1
	BufferedImage image2; // castle2
	BufferedImage image3; // castledoor

	CopyOnWriteArrayList<Monster> monsterslistforblack;
	CopyOnWriteArrayList<Monster> monsterslistforgreen;
	CopyOnWriteArrayList<Monster> monsterslistforblue;
	CopyOnWriteArrayList<Monster> monsterslistforredbig;
	CopyOnWriteArrayList<Monster> monsterslistforredsmall;
	CopyOnWriteArrayList<Bullet> bulletslist;
	int randommonsterx;
	int randommonstery;
	int castledamage;
	int castlehp;
	static int lvl1movement;
	static int lvl2movement;
	static int lvl3movement;
	static int lvl4movement;
	static int lvl5movementpt1;
	static int lvl5movementpt2;
	static int tankmovement;
	static int score;
	static int redbarx;
	static int Time;
	Rectangle rectangle1;
	Rectangle rectangle2;
	Rectangle rectangle3;
	Rectangle rectangle4;
	Rectangle rectangle5;

	Timer gametimer;
	TimerTask level1task;
	TimerTask level2task;
	TimerTask level3task;
	TimerTask level4task;
	TimerTask level5task;
	TimerTask lvl1movementask;
	TimerTask lvl2movementtask;
	TimerTask lvl3movementtask;
	TimerTask lvl4movementtask;
	TimerTask lvl5movementtask;
	TimerTask monsterremover;
	TimerTask bulletremover;
	TimerTask timeshowertask;
	TimerTask gameterminator;
	TimerTask scoreshowertask;
	Timer bullettimer;
	TimerTask bullettask;
	JLabel hpshower;
	JLabel scoreshower;

	Game() {
		JLabel hpshower = new JLabel("Health Points");
		setLayout(null);

		hpshower.setBounds(510, 5, 150, 50);
		add(hpshower);
		JLabel timeshower = new JLabel("Time: " + Time);

		timeshower.setBounds(10, 5, 150, 50);
		add(timeshower);
		score=0;
		scoreshower=new JLabel("Score: "+score);
		scoreshower.setBounds(1030,5,150,50);
		add(scoreshower);
		int[] monsterspawnpoints = { 0, 90, 180, 270, 360, 450, 540, 630, 720, 810, 900, 990 };
		redbarx = 0;
		castlehp = 700;
		lvl1movement=7;
		lvl2movement=7;
		lvl3movement=12;
		lvl4movement=6;
		lvl5movementpt1=8;
		lvl5movementpt2=5;
		tankmovement=3;

		try {
			image1 = ImageIO.read(new File("castle1.png"));
			image2 = ImageIO.read(new File("realcastle2.png"));
			image3 = ImageIO.read(new File("castledoor.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		castledamage = 40;            //castledamage=bulletdamage
		addMouseListener(new Bulletlistener());
		setBackground(Color.orange);
		setPreferredSize(new Dimension(1102, 680));
		rectangle1 = new Rectangle(0, 110, 8 * image2.getWidth(), image2.getHeight());
		rectangle2 = new Rectangle(8 * image2.getWidth(), 96, image1.getWidth() + 9, image1.getHeight());
		rectangle3 = new Rectangle(8 * image2.getWidth() + image1.getWidth(), 70, image3.getWidth() + 3,
				image3.getHeight());
		rectangle4 = new Rectangle(8 * image2.getWidth() + 2 * image1.getWidth() - 50 + image3.getWidth(), 96,
				image1.getWidth() + 12, image1.getHeight());
		rectangle5 = new Rectangle(8 * image2.getWidth() + 2 * image1.getWidth() + image3.getWidth() + 8, 110,
				8 * image2.getWidth(), image2.getHeight());
		monsterslistforblack = new CopyOnWriteArrayList<>();
		monsterslistforgreen = new CopyOnWriteArrayList<>();
		monsterslistforblue = new CopyOnWriteArrayList<>();
		monsterslistforredbig=new CopyOnWriteArrayList<>();
		monsterslistforredsmall=new CopyOnWriteArrayList<>();
		gametimer = new Timer();
		level1task = new TimerTask() {
			int count = 0;

			@Override
			public void run() {
				int x1 = 0;
				int x2 = 0;
				boolean checker = true;
				while (checker) {
					int xs1 = (int) (Math.random() * 12) * 90;
					int xs2 = (int) (Math.random() * 12) * 90;
					if (xs1 != xs2) {
						checker = false;
						x1 = xs1;
						x2 = xs2;
					}

				}
				Monster monster1 = new Monster(x1, 655, 120, 50, 1, 0);
				Monster monster2 = new Monster(x2, 655, 120, 50, 1, 0);

				monsterslistforblack.add(monster1);
				monsterslistforblack.add(monster2);

				repaint();

				if (Time >24) {
					level1task.cancel();
				}

			}
		};

		 gametimer.schedule(level1task, 4000, 3000);
		
		level2task = new TimerTask() {

			@Override
			public void run() {
				int x1 = 0;
				int x2 = 0;
				int x3 = 0;
				boolean checker = true;
				while (checker) {
					int xs1 = (int) (Math.random() * 12) * 90;
					int xs2 = (int) (Math.random() * 12) * 90;
					int xs3 = (int) (Math.random() * 12) * 90;
					if (xs1 != xs2 && xs1 != xs3 && xs2 != xs3) {
						checker = false;
						x1 = xs1;
						x2 = xs2;
						x3 = xs3;
					}

				}
				Monster monster1 = new Monster(x1, 655, 120, 50, 1, 0);
				Monster monster2 = new Monster(x2, 655, 120, 50, 1, 0);
				Monster monster3 = new Monster(x3, 655, 120, 50, 1, 0);

				monsterslistforblack.add(monster1);
				monsterslistforblack.add(monster2);
				monsterslistforblack.add(monster3);
				repaint();
			//	System.out.println(Time);
				if (Time >=57) {
					level2task.cancel();
				}

			}
		};
		 gametimer.schedule(level2task,36000,3500);
		level3task = new TimerTask() { 

			@Override
			public void run() {
				int x1 = 0;
				int x2 = 0;
				boolean checker = true;
				while (checker) {
					int xs1 = (int) (Math.random() * 12) * 90;
					int xs2 = (int) (Math.random() * 12) * 90;
					if (xs1 != xs2) {
						checker = false;
						x1 = xs1;
						x2 = xs2;
					}

				}
				Monster monster1 = new Monster(x1, 655, 120, 100, 2, 0);
				Monster monster2 = new Monster(x2, 655, 120, 100, 2, 0);
				monsterslistforgreen.add(monster1);
				monsterslistforgreen.add(monster2);
				
				if(Time>102) {level3task.cancel();}
				

			}
		};
		 gametimer.schedule(level3task,70000,4000); 
		level4task = new TimerTask() {

			@Override
			public void run() { if(Time>150) level4task.cancel();
				int xs1 = (int) (Math.random() * 12);
				Monster monster1 = new Monster(monsterspawnpoints[xs1], 655, 320, 200, 3, 0);
				Monster monster2 = new Monster(monsterspawnpoints[(xs1 + 1) % 12], 655, 120, 50, 2, 0);
				Monster monster3 = new Monster(monsterspawnpoints[(xs1 + 2) % 12], 655, 120, 50, 2, 0);
				monsterslistforblue.add(monster1);
				monsterslistforblack.add(monster2);
				monsterslistforblack.add(monster3);

			}
		};
		gametimer.schedule(level4task, 117000, 4000);
		
		level5task=new TimerTask() {
			
			@Override
			public void run() {
				int x1 = 0;
				int x2 = 0;
				boolean checker = true;
				while (checker) {
					int xs1 = (int) (Math.random() * 12) * 90;
					int xs2 = (int) (Math.random() * 12) * 90;
					if (xs1 != xs2) {
						checker = false;
						x1 = xs1;
						x2 = xs2;
					}

				}
				Monster monster1 = new Monster(x1, 655, 200, 125, 5, 0);
				Monster monster2 = new Monster(x2, 655, 200, 125, 5, 0);
				monsterslistforredbig.add(monster1);
				monsterslistforredbig.add(monster2);
			}
		};
		gametimer.schedule(level5task,170000,6500);
		 lvl1movementask = new TimerTask() {

			@Override
			public void run() {
				for (Monster b : monsterslistforblack) {
				
				
					b.monstery -=lvl1movement;
					b.monsterrect.setRect(b.monsterx, b.monstery, 25, 25);
					repaint();
				}
				if(Time>36)  lvl1movementask.cancel();
					
				

			}
		};
		 gametimer.schedule(lvl1movementask,4200,100);
		  lvl2movementtask = new TimerTask() {
			
			@Override
			public void run() {
				for (Monster b : monsterslistforblack) {
					b.monstery -= 7;
					b.monsterrect.setRect(b.monsterx, b.monstery, 25, 25);
					repaint();
				}
				
				if (Time>68)lvl2movementtask.cancel();
			}
		};
		gametimer.schedule(lvl2movementtask, 36100,100);
		
		
		lvl3movementtask = new TimerTask() {

			@Override
			public void run() {
				int speedx = 10;
				int speedy = -12;
				int checker = 0;
                if(Time>115) lvl3movementtask.cancel();
				for (Monster b : monsterslistforgreen) {
					int random = (int) (Math.random() * 2);
					if (random == 0)
						random = -1;
					speedx = speedx * random;
					checker = b.monsterx + speedx;
					if (checker > 1076 || checker <= 0) {
						speedx = speedx * -1;
					}
					b.monsterx += speedx;
					b.monsterrect.setRect(b.monsterx, b.monstery, 25, 25);
					repaint();
					++b.count;
					if (b.count % 2 == 0) {
						b.monstery += speedy;
						b.monsterrect.setRect(b.monsterx, b.monstery, 25, 25);
						repaint();
					}
				}

			}
		};
		
		 gametimer.schedule(lvl3movementtask,70100,100);
		lvl4movementtask = new TimerTask() {

			@Override
			public void run() { if(Time>169) lvl4movementtask.cancel();
				for (Monster b : monsterslistforblack) {
					b.monstery -=lvl4movement;
					b.monsterrect.setRect(b.monsterx, b.monstery, 25, 25);
					repaint();
				}
				for (Monster c : monsterslistforblue) {

					c.monstery -= tankmovement;
					c.monsterrect.setRect(c.monsterx, c.monstery, 50, 50);
					repaint();
				}

			}
		};
		gametimer.schedule(lvl4movementtask, 117005, 100); 
		
		lvl5movementtask=new TimerTask() { 
			
			@Override
			public void run() {  
			if(Time>0&&Time%8==0)++lvl5movementpt2;
				for (Monster b : monsterslistforredsmall) {
					b.monstery -=lvl5movementpt1;
					b.monsterrect.setRect(b.monsterx, b.monstery, 22, 22);
					repaint();
				}
				for (Monster c : monsterslistforredbig) {

					c.monstery -= lvl5movementpt2;
					c.monsterrect.setRect(c.monsterx, c.monstery,40, 40);
					repaint();
				}
				
			}
		};
		gametimer.schedule(lvl5movementtask, 170200,250);
		monsterremover = new TimerTask() {                                 //monster remover

			@Override
			public void run() {
				for (Monster b : monsterslistforblack) {
					if ((rectangle1.intersects(b.monsterrect)) || (rectangle2.intersects(b.monsterrect))
							|| (rectangle3.intersects(b.monsterrect)) || (rectangle4.intersects(b.monsterrect))
							|| (rectangle5.intersects(b.monsterrect))) {
						monsterslistforblack.remove(b);
						
						castlehp = castlehp - b.damage;
						redbarx = redbarx + (b.damage / 5);
						repaint();
					}
					if (b.health <= 0) {
						monsterslistforblack.remove(b);
						score=score+30;
						repaint();
						
					}

				}
				for (Monster c : monsterslistforgreen) {
					if ((rectangle1.intersects(c.monsterrect)) || (rectangle2.intersects(c.monsterrect))
							|| (rectangle3.intersects(c.monsterrect)) || (rectangle4.intersects(c.monsterrect))
							|| (rectangle5.intersects(c.monsterrect))) {
						monsterslistforgreen.remove(c);
						castlehp -= c.damage;
						redbarx = redbarx + (c.damage / 5);
						repaint();
					}
					if (c.health <= 0) {
						monsterslistforgreen.remove(c);
						score=score+50;
						repaint();
					}

				}
				for (Monster d : monsterslistforblue) {
					if ((rectangle1.intersects(d.monsterrect)) || (rectangle2.intersects(d.monsterrect))
							|| (rectangle3.intersects(d.monsterrect)) || (rectangle4.intersects(d.monsterrect))
							|| (rectangle5.intersects(d.monsterrect))) {
						monsterslistforblue.remove(d);
						castlehp -= d.damage;
						redbarx = redbarx + (d.damage / 5);
						repaint();
					}
					if (d.health <= 0) {
						monsterslistforblue.remove(d);
						score=score+70;
						repaint();
					}

				}
				for (Monster e : monsterslistforredbig) {
					if ((rectangle1.intersects(e.monsterrect)) || (rectangle2.intersects(e.monsterrect))
							|| (rectangle3.intersects(e.monsterrect)) || (rectangle4.intersects(e.monsterrect))
							|| (rectangle5.intersects(e.monsterrect))) {
						monsterslistforredbig.remove(e);
						castlehp -= e.damage;
						redbarx = redbarx + (e.damage / 5);
						repaint();
					}
					if (e.health <= 0) {int smallredx=e.monsterx;
					int smallredy=e.monstery;
					Monster monster1=new Monster(smallredx+2,smallredy-15 ,80,70, 4, 0);
					Monster monster2=new Monster(smallredx+35,smallredy-15 ,80,70, 4, 0);
						monsterslistforredbig.remove(e);
						monsterslistforredsmall.add(monster1);
						monsterslistforredsmall.add(monster2);
						score=score+60;
						repaint();
					}

				}
				for (Monster f : monsterslistforredsmall) {
					if ((rectangle1.intersects(f.monsterrect)) || (rectangle2.intersects(f.monsterrect))
							|| (rectangle3.intersects(f.monsterrect)) || (rectangle4.intersects(f.monsterrect))
							|| (rectangle5.intersects(f.monsterrect))) {
						System.out.println(f.monsterrect.x);
						monsterslistforredsmall.remove(f);
						
						castlehp -= f.damage;
						redbarx = redbarx + (f.damage / 5);
						repaint();
					}
					if (f.health <= 0) {
						monsterslistforredsmall.remove(f);
						score=score+50;
						repaint();
					}

				}

			}
		};
		gametimer.schedule(monsterremover, 1, 1);
		bulletslist = new CopyOnWriteArrayList<Bullet>();
		bullettimer = new Timer();
		bullettask = new TimerTask() {                    //for removing bullet

			@Override
			public void run() {                                            
				for (Bullet a : bulletslist) {
					a.bullety += 5;
					repaint();
					int bulletchecker = a.bullety;
					if (bulletchecker > 680) {
						bulletslist.remove(a);
					}
					for (Monster b : monsterslistforblack) {
						if (b.monsterrect.intersects(new Rectangle(a.bulletx - 3, a.bullety, 6, 12))) {
							bulletslist.remove(a);
							b.health = b.health - castledamage;
							repaint();
						}

					}
					for (Monster c : monsterslistforgreen) {
						if (c.monsterrect.intersects(new Rectangle(a.bulletx - 3, a.bullety, 6, 12))) {
							bulletslist.remove(a);
							c.health = c.health - castledamage;
							repaint();
						}

					}
					for (Monster d : monsterslistforblue) {
						if (d.monsterrect.intersects(new Rectangle(a.bulletx - 3, a.bullety, 6, 12))) {
							bulletslist.remove(a);
							d.health = d.health - castledamage;
							repaint();
						}

					}
					for (Monster e : monsterslistforredbig) {
						if (e.monsterrect.intersects(new Rectangle(a.bulletx - 3, a.bullety, 6, 12))) {
							bulletslist.remove(a);
							e.health = e.health - castledamage;
							repaint();
						}

					}
					for (Monster g : monsterslistforredsmall) {
						if (g.monsterrect.intersects(new Rectangle(a.bulletx - 3, a.bullety, 6, 12))) {
							bulletslist.remove(a);
							g.health = g.health - castledamage;
							repaint();
						}

					} 
				}

			}
		};
		
		bullettimer.schedule(bullettask, 1, 10);
		gameterminator=new TimerTask() {
			
			@Override
			public void run() {
				if(castlehp<=0) {
					gametimer.cancel();
					String Screen="Game Over\nYour Score: "+score+"\n Time: "+Time+" seconds";
					JOptionPane.showMessageDialog(null,Screen);
					System.exit(0);
					
				
					
					
				}
				
			}
		};
		gametimer.schedule(gameterminator, 0,1);
		timeshowertask = new TimerTask() {

			@Override
			public void run() {
				++Time;
				timeshower.setText("Time: " + Time);

			}
		};
		gametimer.schedule(timeshowertask, 1000, 1000);
         scoreshowertask=new TimerTask() {
			
			@Override
			public void run() {
				scoreshower.setText("Score: "+score);
				
			}
		};
		gametimer.schedule(scoreshowertask, 0,1);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		g.drawImage(image2, 0, 110, image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 0 + image2.getWidth(), 110, image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 0 + 2 * image2.getWidth(), 110, image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 0 + 3 * image2.getWidth(), 110, image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 0 + 4 * image2.getWidth(), 110, image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 0 + 5 * image2.getWidth(), 110, image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 0 + 6 * image2.getWidth(), 110, image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 0 + 7 * image2.getWidth(), 110, image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image1, 0 + 8 * image2.getWidth(), 96, image1.getWidth() + 9, image1.getHeight(), null);
		g.drawImage(image3, 0 + 8 * image2.getWidth() + image1.getWidth(), 70, image3.getWidth() + 3,
				image3.getHeight(), null); // castledoor
		g.drawImage(image1, 0 + 8 * image2.getWidth() + image1.getWidth() - 5 + image3.getWidth(), 96,
				image1.getWidth() + 12, image1.getHeight(), null);
		g.drawImage(image2, 8 * image2.getWidth() + 2 * image1.getWidth() + image3.getWidth() + 8, 110,
				image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 9 * image2.getWidth() + 2 * image1.getWidth() + image3.getWidth() + 8, 110,
				image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 10 * image2.getWidth() + 2 * image1.getWidth() + image3.getWidth() + 8, 110,
				image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 11 * image2.getWidth() + 2 * image1.getWidth() + image3.getWidth() + 8, 110,
				image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 12 * image2.getWidth() + 2 * image1.getWidth() + image3.getWidth() + 8, 110,
				image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 13 * image2.getWidth() + 2 * image1.getWidth() + image3.getWidth() + 8, 110,
				image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 14 * image2.getWidth() + 2 * image1.getWidth() + image3.getWidth() + 8, 110,
				image2.getWidth(), image2.getHeight(), null);
		g.drawImage(image2, 15 * image2.getWidth() + 2 * image1.getWidth() + image3.getWidth() + 8, 110,
				image2.getWidth(), image2.getHeight(), null);

		g.setColor(Color.black);
		g.drawRect(479, 39, 141, 11);
		
		if(castlehp>=0) {g.setColor(Color.green);
		g.fillRect(480, 40, 140, 10);} // can bar�
		if (castlehp >= 0) {
			g.setColor(Color.red);
			g.fillRect(620 - redbarx, 40, redbarx, 10);
		}
		if(castlehp<=0) {g.setColor(Color.red);
			g.fillRect(480, 40, 140, 10);
		}
		for (Bullet a : bulletslist) {
			g.setColor(Color.red);
			g.fillRect(a.bulletx - 3, a.bullety, 6, 12);
		}
		for (Monster b : monsterslistforblack) {
			g.setColor(Color.black);
			g.fillOval(b.monsterx, b.monstery, 25, 25);
		}
		for (Monster c : monsterslistforgreen) {
			g.setColor(Color.green);
			g.fillOval(c.monsterx, c.monstery, 25, 25);
		}
		for (Monster d : monsterslistforblue) {
			g.setColor(Color.blue);
			g.fillOval(d.monsterx, d.monstery, 50, 50);
		}
		for (Monster e : monsterslistforredbig) {
			g.setColor(Color.red);
			g.fillOval(e.monsterx, e.monstery, 40, 40);
		}
		for (Monster f : monsterslistforredsmall) {
			g.setColor(Color.red);
			g.fillOval(f.monsterx, f.monstery, 22, 22);
		}
		
	}

	class Monster {
		int monsterx;
		int monstery;
		int health;
		int damage;
		int type;
		int count;
		Rectangle monsterrect;

		public Monster(int monsterx, int monstery, int health, int damage, int type, int count) {
			super();
			this.monsterx = monsterx;
			this.monstery = monstery;
			this.health = health;
			this.damage = damage;
			this.type = type;
			this.count = count;
			if (this.type == 1 || this.type == 2)
				monsterrect = new Rectangle(monsterx, monstery, 25, 25);
			if (this.type == 3 )
				monsterrect = new Rectangle(monsterx, monstery, 50, 50);
			if(this.type==4) monsterrect = new Rectangle(monsterx, monstery, 40, 40);
			if(this.type==5) monsterrect = new Rectangle(monsterx, monstery, 22, 22);
		}

	}

	class Bullet {
		int bulletx;
		int bullety;
		Rectangle bulletrect;

		public Bullet(int bulletx, int bullety) {
			super();
			this.bulletx = bulletx;
			this.bullety = bullety;
			this.bulletrect = new Rectangle(bulletx - 3, bullety, 6, 12);
		}

	}

	class Bulletlistener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Bullet bullet = new Bullet(e.getX(), 220);
			bulletslist.add(bullet);
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
     
		}

		@Override
		public void mouseExited(MouseEvent e) {
			

		}

	}

}
