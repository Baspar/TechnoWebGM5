package dao;

import java.util.Properties;
import java.util.Vector;

import Model.Joueur;


public interface JoueurDao {
	void creer( Joueur joueur ) throws DAOException;
	Joueur trouver( String login ) throws DAOException;
	Vector<String> trouverTousLesJoueurs() throws DAOException;
} 