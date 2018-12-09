package logic;

import java.util.ArrayList;
import java.util.Random;

public class RandomSpawn {
	private ArrayList<Plant> plantList;
	private ArrayList<Plant> rand;
	private Player player;
	public RandomSpawn(Player com){
		this.player = com;
		this.rand = new ArrayList<Plant>();
		this.plantList = new ArrayList<Plant>();
		plantList.add(new StonePlant());
		plantList.add(new GreenBean());
		plantList.add(new SunFlower());
		
	}
	public void addRand(int num) {
		for(int i = 0; i < num; i++) {
			Random rnd = new Random();
			int randNum;
			if(plantList.size() <=1) {
				randNum = 0;
			}
			else {
				randNum = rnd.nextInt(plantList.size());
				System.out.println(randNum);
			}
			rand.add(plantList.get((randNum)));
		}
	}
	public boolean checkNotExceed(int num) {
		return true;
	}
	public void updateToTable() {
		addRand(3);
		System.out.println();
		for(int i = 0; i < rand.size();i++) {
			Random rnd = new Random();
			int randi = rnd.nextInt(Player.row);
			int randj = rnd.nextInt(Player.collumn);
			player.spawnPlant( rand.get(rand.size()-1), randi, randj);
			rand.remove(i);
		}
	}
}
