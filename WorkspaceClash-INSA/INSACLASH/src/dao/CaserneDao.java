package dao;

import Model.Caserne;
import Model.TypeSoldat;

public interface CaserneDao {
	void creerCaserne(Caserne caserne, String login);
	Caserne trouverCaserne(String login);
	void upgrade(String login);
	void deplacer(String login, int x, int y);
	void modifNombreSoldat(String login, int nombre, TypeSoldat type);
	void ameliorerSoldat(String login, TypeSoldat type);
}