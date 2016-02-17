package dao;

import java.util.Vector;

import Model.Canon;
import Model.Mortier;


public interface DefenseDao {
	Vector<Canon> trouverCanon(String login);
	Vector<Mortier> trouverMortier(String login);
	void upgradeDefense(String login, int id);
	void deplacerDefense(String login, int id, int x, int y);
	void ajouterDefense(String login, String type, int id);
}
