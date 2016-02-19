package dao;

import Model.Village;

public interface VillageDao {
	void creerVillage(Village village, String login);
	Village chargerVillage(String login);

}
