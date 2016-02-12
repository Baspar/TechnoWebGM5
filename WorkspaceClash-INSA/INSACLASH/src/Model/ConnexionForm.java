package Model;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import dao.DAOException;
import dao.JoueurDao;

public final class ConnexionForm {
    private static final String CHAMP_LOGIN = "login";


    private static final String CHAMP_PASS = "motDePasse";


    private static final String ALGO_CHIFFREMENT ="SHA-256";


    private String resultat;


    private Map<String, String> erreurs = new HashMap<String, String>();


    private JoueurDao joueurDao;


    public String getResultat() {
        return resultat;
    }


    public Map<String, String> getErreurs() {
        return erreurs;
    }


    public ConnexionForm( JoueurDao joueur){
        this.joueurDao=joueur;
    }


    public Joueur connecterJoueur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        Joueur joueur = new Joueur();

        try {
            traiterLogin( login, joueur );
            traiterMotDePasse(login,  motDePasse, joueur );

            /* Initialisation du résultat global de la validation. */
            if ( erreurs.isEmpty() ) {
                resultat = "Succès de la connexion.";
            } else {
                resultat = "Échec de la connexion.";
            }
        } catch ( DAOException e ) {
            resultat = "Échec de la connexion : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return joueur;
    }


    private void traiterLogin( String login, Joueur joueur ) {
        try {
            validationLogin( login );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_LOGIN, e.getMessage() );
        }
        joueur.setLogin( login );
    }


    private void validationLogin( String login ) throws FormValidationException {
        if ( login != null && login.length() > 3 ) {
            if (joueurDao.trouver(login)==null){
                throw new FormValidationException( "Le login n'est pas enregistré." );
            }

        }else
            throw new FormValidationException( "Le login doit contenir au moins 3 caractères." );
    }


    private void traiterMotDePasse( String login, String motDePasse, Joueur joueur) {
        try {
            validationMotDePasse(login, motDePasse );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
    }


    private void validationMotDePasse( String login, String motDePasse ) throws FormValidationException {
        if ( motDePasse != null ) {
            if ( motDePasse.length() > 3 ) {
                Joueur base=joueurDao.trouver(login);
                ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
                passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
                passwordEncryptor.setPlainDigest( false );
                if( (base!=null) && !passwordEncryptor.checkPassword(motDePasse, base.getMotDePasse()) )
                    throw new FormValidationException( "Le mot de passe saisi n'est pas correct." );
            }else
                throw new FormValidationException( "Le mot de passe doit contenir au moins 3 caractères." );
        } else {
            throw new FormValidationException( "Merci de saisir votre mot de passe." );
        }
    }


    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }


    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
