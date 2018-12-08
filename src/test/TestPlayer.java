package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.*;
public class TestPlayer {
	//@Test
	/*void TestputPlant() {
		Player p1 = new Player(100);
		Player p2 = new Player(100);
		PlantStorage a = new PlantStorage();
		GreenBean pl1 = new GreenBean();
		SunFlower pl2 = new SunFlower();
		a.addPlant(pl1);
		a.addPlant(pl2);
		a.listPlant();
		a.pickPlant(0);
		p1.spawnPlant(new GreenBean(),1,1);
		p2.spawnPlant(new SunFlower(), 1, 1);
		p1.start(p2);
		assertEquals(9.5,((SunFlower) p2.playerPlant[1][1]).getHp());
		p2.start(p1);
		assertEquals(9.5,((SunFlower) p2.playerPlant[1][1]).getHp());
		assertEquals(100,p2.getPlayerHp());
		System.out.println("---");
		p1.spawnPlant(a.pickPlant(0), 1, 0);
		p1.start(p2);
		System.out.println("---");
		p2.start(p1);
		assertEquals(13.5,((SunFlower) p2.playerPlant[1][1]).getHp());
		System.out.println("----");
		p2.spawnPlant(new GreenBean(), 1,0);
		p1.start(p2);
		System.out.println("---");
		p2.start(p1);
		System.out.println("---");
		assertEquals(8,((GreenBean) p2.playerPlant[1][0]).getHp());
	}*/
	@Test
	void testDeletePlant() {
		Player p1 = new Player(200,10,0,0);
		Player p2 = new Player(200,100,0,0);
		assertEquals(null, p2.getPlant(0, 0));System.out.println(p2.getPlant(7, 0));
		p1.spawnPlant(new GreenBean(), 0, 1);
		p2.spawnPlant(new SunFlower(), 0, 0);
		assertEquals(50, (new GreenBean()).getCost());
		//assertEquals(50,p1.getSunPower());
		assertEquals(75,p2.getSunPower());
		//assertEquals(true, p1.playerPlant[0][1].isAlive());
		
		for(int i = 0 ; i < 20 ; i++ ) {
			p1.start(p2);
			p2.start(p1);
			p1.detectPlant();
			p2.detectPlant();
		}
		//assertEquals(null, p2.playerPlant[0][0]);
		p2.spawnPlant(new GreenBean(), 0, 0);
		assertEquals(75, p2.getSunPower());
		System.out.println(p2.getPlant(7, 0));

		
	}
}
