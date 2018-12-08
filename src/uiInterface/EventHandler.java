package uiInterface;

import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import logic.Player;
import logic.SolarPower;

public class EventHandler {
	private Counter c1 = new Counter(0,500);
	private Counter c2 = new Counter(600,1100);
	/*public void setOnMoveCounterPressed(KeyEvent event,int player) {
		if(player == 1) {
			switch(event.getCode()) {
			case UP: c2.moveCounter(0, -1);; break;
			case DOWN: c2.moveCounter(0, 1);; break;
			case LEFT: c2.moveCounter(-1, 0);; break;
			case RIGHT: c2.moveCounter(1, 0);; break;
			case W: c1.moveCounter(0, -1);; break;
			case S: c1.moveCounter(0, 1);; break;
			case A: c1.moveCounter(-1, 0);; break;
			case D: c1.moveCounter(1, 0);; break;
			case DIGIT1 : digit1 = true; break;
			case NUMPAD1 : num1 = true; break;
			}
		}
	}*/
	public void setUpSolarPower(SolarPower solarPower,Player p1) {
		solarPower.setOnMouseClicked(new addSolarPower(solarPower,p1));
	}
	private class addSolarPower implements javafx.event.EventHandler<MouseEvent>{
		private SolarPower solarpower;
		private Player p1;
		public addSolarPower(SolarPower solarpower,Player p1) {
			this.solarpower = solarpower;
			this.p1 = p1;
		}
		@Override
		public void handle(MouseEvent event) {
				p1.setSolarPower(p1.getSunPower()+25);
				this.solarpower.setIsClicked(false);
			
		}
	
	}
	
}
