package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import Model.Joueur;
import dao.DAOException;
import dao.JoueurDao;


public final class InscriptionForm {
    private static final String CHAMP_LOGIN        = "login";
    private static final String CHAMP_PASS       = "motDePasse";
    private static final String CHAMP_CONF       = "confirmation";
    private static final String ALGO_CHIFFREMENT = "SHA-256";
    private String              resultat;
    private Map<String, String> erreurs;
    private JoueurDao      joueurDao;


    public InscriptionForm( JoueurDao joueurDao ) {
        erreurs=new HashMap<String, String>();
        this.joueurDao = joueurDao;
    }
    public Map<String, String> getErreurs() {
        return erreurs;
    }
    public String getResultat() {
        return resultat;
    }
    public Joueur inscrireJoueur( HttpServletRequest request ) {
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );

        Joueur joueur = new Joueur();
        try {
            traiterLogin( login, joueur );
            traiterMotsDePasse( motDePasse, confirmation, joueur );
            if ( erreurs.isEmpty() ) {
                joueurDao.creer( joueur );
                resultat = "Succès de l'inscription.";
            } else {
                resultat = "Échec de l'inscription.";
            }
        } catch ( DAOException e ) {
            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
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
        joueur.setLogin(login );
    }
    private void validationLogin( String login ) throws FormValidationException {
        if ( login != null && login.length() > 3 ) {
            if(joueurDao.trouver(login)!=null){
                throw new FormValidationException("Ce login est deja utilise");
            }
        }else
            throw new FormValidationException( "Le login doit contenir au moins 3 caractères." );
    }
    private void traiterMotsDePasse( String motDePasse, String confirmation, Joueur joueur ) {
        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }
    }    
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws FormValidationException {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new FormValidationException( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( motDePasse.length() < 3 ) {
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
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
