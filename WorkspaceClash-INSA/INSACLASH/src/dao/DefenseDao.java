package dao;

import java.util.Vector;

import Model.Canon;
import Model.Defense;
import Model.Mortier;


public interface DefenseDao {
	Vector<Canon> trouverCanon(String login);
	Vector<Mortier> trouverMortier(String login);
	Defense trouverDefense(String login, int id);
	void upgradeDefense(String login, int id);
	void deplacerDefense(String login, int id, int x, int y);
	void ajouterDefense(String login, Defense defense);
}
