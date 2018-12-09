package uiInterface;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.AttackPlant;
import logic.Bullet;
import logic.Player;
import logic.SolarPower;
import logic.SunFlower;

public class Container {
	private ArrayList<Bullet> bullets1;
	private ArrayList<Bullet> bullets2;
	private ArrayList<SolarPower> solars;
	private Player p1;
	private Player p2;
	private EventHandler eventHandler;
	Container(Player p1,Player p2){
		this.p1 = p1;
		this.p2 = p2;
		this.bullets1 = new ArrayList<Bullet>();
		this.bullets2 = new ArrayList<Bullet>();
		this.solars = new ArrayList<SolarPower>();
	}
	//addBullet
	//updateCollision
	//updateOutofRangeBullet
	//updateBulletMovement
	//drawBullet
	public void addBulletContainer() {
		for(int i = 0 ; i < Player.row ; i++) {
			 for(int j = 0 ; j <Player.collumn ; j++) {
				 if(p1.playerPlant[i][j] != null && p1.playerPlant[i][j] instanceof AttackPlant) {
					 ((AttackPlant) p1.playerPlant[i][j]).setBullet(new Bullet(p1.playerPlant[i][j].getX()+80
							 , p1.playerPlant[i][j].getY(), true));
					 if(((AttackPlant) p1.playerPlant[i][j]).getBullet() != null) {
						 Bullet temp = ((AttackPlant) p1.playerPlant[i][j]).getBullet();
						 if(!bullets1.contains(temp)) {
							 bullets1.add(((AttackPlant) p1.playerPlant[i][j]).getBullet());
						}	
					 }
				 }
				 
			 }
		 }
	
		 for(int i = 0 ; i < Player.row ; i++) {
			 for(int j = 0 ; j <Player.collumn ; j++) {
				 if(p2.playerPlant[i][j] != null && p2.playerPlant[i][j] instanceof AttackPlant) {
					 ((AttackPlant) p2.playerPlant[i][j]).setBullet(new Bullet(p2.playerPlant[i][j].getX()
							 , p2.playerPlant[i][j].getY(), true));
					 if(((AttackPlant) p2.playerPlant[i][j]).getBullet() != null) {
						 Bullet temp = ((AttackPlant) p2.playerPlant[i][j]).getBullet();
						 if(!bullets2.contains(temp)) {
							bullets2.add(((AttackPlant) p2.playerPlant[i][j]).getBullet());
							System.out.println("check2");
						 }
					 }
				 }
				 
			 }
		 }
	}
	public void updateCollision() {
		for(int k = 0 ; k < bullets1.size() ; k++) {
			 Bullet tempBullet = bullets1.get(k);
			 for(int i = 0 ; i < Player.row ; i++) {
				 for(int j = 0 ; j <Player.collumn ; j++) {
					 if(p2.playerPlant[i][j] != null) {
						 if(tempBullet.getBounds().intersects(p2.playerPlant[i][j].getBounds())) {
							 p2.playerPlant[i][j].takeDamage(1);
							 bullets1.remove(tempBullet);
						 
						 }
					}
				 }
			 }
			 
		 }
		 for(int k = 0 ; k < bullets2.size() ; k++) {
			 Bullet tempBullet = bullets2.get(k);
			 for(int i = 0 ; i < Player.row ; i++) {
				 for(int j = Player.collumn -1 ;  j >= 0 ; j--) {
					 if(p1.playerPlant[i][j] != null) {
						 if(tempBullet.getBounds().intersects(p1.playerPlant[i][j].getBounds())) {
							 p1.playerPlant[i][j].takeDamage(1);
							 bullets2.remove(tempBullet);
						 
						 }
					}
				 }
			 }
			 
		 }
	}
	public void updateOutOfRangeBullet(Player p1) {
		for(int i = 0; i <bullets1.size(); i++) {
			if(bullets1.get(i).getX() > 1000) bullets1.remove(bullets1.get(i));
		 }
		 for(int i = 0; i <bullets2.size(); i++) {
			if(bullets2.get(i).getX() < -1000) {
				p1.directDamage(1);
				bullets2.remove(bullets2.get(i));	
			}
		}
	}
	public void drawBullet(GraphicsContext gc) {
		for(int i = 0; i <bullets1.size(); i++) {
			bullets1.get(i).render(gc);
			
		 }
		 for(int i = 0; i <bullets2.size(); i++) {
			
			bullets2.get(i).render(gc);
				
		}
	}
	public void updateBulletMovement(double time) {
		for(int i = 0; i <bullets1.size(); i++) {
			bullets1.get(i).update(time);
			
		 }
		 for(int i = 0; i <bullets2.size(); i++) {
			if(bullets2.get(i).getVelX() != -1) bullets2.get(i).setVelX(-1);
			bullets2.get(i).update(time);;
				
		}
	}
	public void addSolarPower(Table table) {
		for(int i = 0 ; i < Player.row ; i++) {
			 for(int j = 0 ; j <Player.collumn ; j++) {
				 if(p1.playerPlant[i][j] != null && p1.playerPlant[i][j] instanceof SunFlower) {
					 
					 
					 
						 //((SunFlower)p1.playerPlant[i][j]).setSolarPower(new SolarPower(0, 0));
					 if(((SunFlower)p1.playerPlant[i][j]).getState()) {
						 ((SunFlower)p1.playerPlant[i][j]).load();
						 ((SunFlower)p1.playerPlant[i][j]).getSolarPower().setI(i);
						 ((SunFlower)p1.playerPlant[i][j]).getSolarPower().setJ(j);
					 	solars.add(((SunFlower)p1.playerPlant[i][j]).getSolarPower());
					 	table.addToTile(i, j, ((SunFlower)p1.playerPlant[i][j]).getSolarPower());
					 	System.out.println("Done");
					 }
				 }
				 
			 }
		 }
	}
	public void updateSolarPower(Table table) {
		
		for(int i = 0; i < solars.size();i++) {	
			
			solars.get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println(event.getSource());
					System.out.println(((SolarPower) event.getSource()).getIsClicked());
					if( event.getSource() instanceof SolarPower) {
						((SolarPower) event.getSource()).setIsClicked(true);
						System.out.println(((SolarPower) event.getSource()).getIsClicked());
					}
					
					
					
				}
			});
			if(solars.get(i).getIsClicked()) {
				p1.setSolarPower(25+p1.getSunPower());
				if(solars.get(i).getI() >=0 && solars.get(i).getJ() >=0 && solars.get(i).getI() <Player.row && solars.get(i).getJ() < Player.collumn)
				table.removeFromTile(solars.get(i).getI(), solars.get(i).getJ(), solars.get(i));
				//solars.get(i).setFill(Color.TRANSPARENT);
				
				System.out.println("removed");
				System.out.println(solars.get(i).getI());
				int m = solars.get(i).getI();
				int n = solars.get(i).getJ();
				System.out.println("this"+m);
				if( m>=0 && n>= 0 && m < Player.row && n<Player.collumn && p1.playerPlant[m][n] instanceof SunFlower) {
					((SunFlower) p1.playerPlant[m][n]).setState(true);
				}
				solars.remove(solars.get(i));
			}
			
		}
			
	}
	public void drawSolars(GraphicsContext gc) {
		for(int i = 0; i < solars.size();i++) {
			solars.get(i).drawSolarPower(gc);
		}
	}
	
	public void deleteBullet() {
		for(int i = 0; i<bullets1.size(); i++) {
			bullets1.remove(i);
		}
	}
	
}
