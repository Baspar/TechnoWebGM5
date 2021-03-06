package Controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JoueurDao;
import dao.VillageDao;
import form.ConnexionForm;
import dao.DAOFactory;
import Model.Joueur;

public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_JOUEUR = "joueur";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur"; 
	public static final String ATT_SESSION_MANQUE_RESSOURCE="manqueRessource";
	public static final String VUE = "/WEB-INF/connexion.jsp";
	public static final String VUECONNECTE="/WEB-INF/joueurConnecte/vueRessource.jsp";

	private JoueurDao joueurDao;
	private VillageDao villageDao;


	
	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.joueurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getJoueurDao();
		this.villageDao =( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVillageDao();
	}
	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
	
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ConnexionForm form = new ConnexionForm(joueurDao, villageDao);
		/* Traitement de la requête et récupération du bean en résultant */
		Joueur joueur = form.connecterJoueur( request );
		//System.out.println(joueur.getVillage().getBatiments().getBatiments(TypeBatiment.HDV));
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();
		if ( form.getErreurs().isEmpty() ) {
			session.setAttribute( ATT_SESSION_JOUEUR, joueur );
		}  else {
			session.setAttribute( ATT_SESSION_JOUEUR, null );
		}
		//System.out.println(joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR));
		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute( ATT_FORM, form );
		request.setAttribute( ATT_JOUEUR, joueur );
		session.setAttribute(ATT_SESSION_MANQUE_RESSOURCE, null);
		if(session.getAttribute(ATT_SESSION_JOUEUR)==null)
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		else
			this.getServletContext().getRequestDispatcher( VUECONNECTE ).forward( request, response );

	}

}
