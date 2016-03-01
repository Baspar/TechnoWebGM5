package Controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.HDV;
import Model.Joueur;
import Model.TypeBatiment;
import Model.TypeRessource;
import dao.DAOFactory;
import dao.JoueurDao;
import dao.VillageDao;

/**
 * Servlet implementation class GestionTousJoueurs
 */
@WebServlet("/GestionTousJoueurs")
public class GestionTousJoueurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE="/WEB-INF/joueurConnecte/vueTousJoueurs.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur";  
	public static final String ATT_SESSION_MANQUE_RESSOURCE="manqueRessource";
	public static final String ATT_SESSION_LISTE_JOUEUR="sessionListeJoueur";
	private JoueurDao joueurDao;
	private VillageDao villageDao;
	
   @Override
	public void init() throws ServletException {
	   this.joueurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getJoueurDao();
	   this.villageDao =( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVillageDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Joueur jou=(Joueur) session.getAttribute(ATT_SESSION_JOUEUR);
		session.setAttribute(ATT_SESSION_LISTE_JOUEUR, joueurDao.trouverTousLesJoueurs(jou.getLogin()));
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Joueur joueur = (Joueur) session.getAttribute(ATT_SESSION_JOUEUR);
		String t= (String) session.getAttribute(ATT_SESSION_MANQUE_RESSOURCE);
		t=null;
	//	System.out.println(t);
		//on doit ameliorer l'hdv
		if(request.getParameter("ameliorer")!=null){
			HDV h=(HDV) joueur.getVillage().getHDV();
			if( joueur.getVillage().upgradeBatiment(TypeBatiment.HDV, 0) ){
				villageDao.ameliorerBatiment(joueur.getLogin(), h);
				villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.OR, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.OR));
			}	
			else
				t="true";
		}
		//System.out.println(t);
		session.setAttribute(ATT_SESSION_JOUEUR, joueur);
		session.setAttribute(ATT_SESSION_MANQUE_RESSOURCE, t);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
