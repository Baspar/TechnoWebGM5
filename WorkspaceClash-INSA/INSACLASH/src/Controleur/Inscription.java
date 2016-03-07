package Controleur;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Joueur;
import dao.DAOFactory;
import dao.JoueurDao;
import dao.VillageDao;
import form.InscriptionForm;


public class Inscription extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "joueur";
	public static final String ATT_FORM = "form"; 
	public static final String VUE = "/WEB-INF/inscription.jsp"; 
	private JoueurDao joueurDao;
	private VillageDao villageDao;
	
	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.joueurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getJoueurDao();
		this.villageDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVillageDao();
	}
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		InscriptionForm form = new InscriptionForm( joueurDao, villageDao );
		/* Traitement de la requête et récupération du bean en résultant */
		Joueur joueur = form.inscrireJoueur( request );
		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute( ATT_FORM, form );
		request.setAttribute( ATT_USER, joueur );
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
} 