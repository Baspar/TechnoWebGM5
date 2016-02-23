package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import Model.Joueur;
import form.ConnexionForm;

public final class DAOUtilitaire {
    private DAOUtilitaire() {
    }
    
    public static void fermetureSilencieuse( ResultSet resultSet ) {//DONE
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
            }
        }
    }

    public static void fermetureSilencieuse( Statement statement ) {//DONE
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
            }
        }
    }
    
    public static void fermetureSilencieuse( Connection connexion ) {//DONE
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
            }
        }
    }

    
  
    public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {//DONE
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {//DONE
        fermetureSilencieuse( resultSet );
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {//DONE
        PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
}
