package logic;

import java.util.ArrayList;
import java.util.Random;

public class RandomSpawn {
	private ArrayList<Plant> plantList;
	private ArrayList<Plant> rand;
	private Player player;

	public RandomSpawn(Player com) {
		this.player = com;
		this.rand = new ArrayList<Plant>();
		this.plantList = new ArrayList<Plant>();
		plantList.add(new StonePlant());
		plantList.add(new GreenBean());

	}

	public void addRand(int num) {
		for (int i = 0; i < num; i++) {
			Random rnd = new Random();
			int randNum;
			if (plantList.size() <= 1) {
				randNum = 0;
			} else {
				randNum = rnd.nextInt(plantList.size());
			}
			rand.add(plantList.get((randNum)));
		}
	}

	public void updateToTable() {
		addRand(1);
		for (int i = 0; i < rand.size(); i++) {
			Random rnd = new Random();
			int randi = rnd.nextInt(Player.row);
			int randj = rnd.nextInt(Player.collumn);
			System.out.println(player.playerPlant[randi][randj]);
			if (rand.get(i) instanceof GreenBean) {
				player.spawnPlant(new GreenBean(), randi, randj);
				rand.remove(i);
				continue;
			}
			if (rand.get(i) instanceof SunFlower) {
				player.spawnPlant(new SunFlower(), randi, randj);
				rand.remove(i);
				continue;
			}
			if (rand.get(i) instanceof StonePlant) {
				player.spawnPlant(new StonePlant(), randi, randj);
				rand.remove(i);
				continue;
			}

		}
	}
}
