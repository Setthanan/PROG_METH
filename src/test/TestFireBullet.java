package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.*;
 
public class TestFireBullet {
	@Test
	void testGreenBean() {
		GreenBean a = new GreenBean();
		assertEquals(10, a.getHp());
		assertEquals(1, a.getBullet().getPower());
	}
	@Test
	void testFireSunFlower() {
		SunFlower a = new SunFlower();
		GreenBean b = new GreenBean();
		assertEquals(10, b.getHp());
		assertEquals(1, b.getBullet().getPower());
		assertEquals(10, b.getMaxHp());
		assertEquals(10, a.getHp());
		assertEquals(0.5, b.calDamage(a));
		b.fireAt(a);
		assertEquals(9.5, a.getHp());
		for(int i = 0;i<30; i++) {
			b.fireAt(a);
		}
		assertEquals(0, a.getHp());
	}
	@Test
	void testFireStonePlant() {
		StonePlant pl1 = new StonePlant();
		GreenBean pl2 = new GreenBean();
		assertEquals(0, pl1.getHp());
		for(int i = 0;i<5; i++) {
			pl2.fireAt(pl1);
		}
		assertFalse(pl1.isAlive());
	}
}
