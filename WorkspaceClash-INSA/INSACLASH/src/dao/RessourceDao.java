package dao;

import java.util.GregorianCalendar;
import java.util.Vector;

import Model.MineCharbon;
import Model.MineOr;

public interface RessourceDao {
	Vector<MineOr> trouverMineOr(String login);
	Vector<MineCharbon> trouverMineCharbon(String login);
	void upgradeRessource(String login, int id);
	void viderRessource(String login, int id, GregorianCalendar date);
	void deplacerRessource(String login, int id, int x, int y);
	void ajouterRessource(String login, String type, int id);
}
