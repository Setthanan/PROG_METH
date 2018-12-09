package logic;

import java.util.ArrayList;

public class RandomSpawn {
	private ArrayList<Plant> plantList;
	private ArrayList<Plant> rand;
	private Player player;
	public RandomSpawn(Player com){
		this.player = com;
		this.rand = new ArrayList<Plant>();
		this.plantList = new ArrayList<Plant>();
		plantList.add(new GreenBean());
		plantList.add(new StonePlant());
		
		
	}
	public void addRand(int num) {
		for(int i = 0; i < num; i++) {
			rand.add(plantList.get((int) (Math.random()*plantList.size()+0)));
		}
	}
	public boolean checkNotExceed(int num) {
		return true;
	}
	public void updateToTable() {
		this.addRand(1);
		for(int i = 0; i < rand.size();i++) {
			player.spawnPlant(rand.get(i), (int)(Math.random()*Player.row+0), (int)(Math.random()*Player.collumn+0));
			rand.remove(i);
		}
	}
}
