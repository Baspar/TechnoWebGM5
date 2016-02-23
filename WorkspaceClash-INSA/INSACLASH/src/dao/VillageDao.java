package dao;

import Model.Batiment;
import Model.TypeBatiment;
import Model.Village;

public interface VillageDao {
	void creerVillage(Village village, String login);
	Village chargerVillage(String login);
	void deplacerBatiment(String login, Batiment b);
	void ameliorerBatiment(String login, Batiment b);
	
}
