package Controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Joueur;
import Model.MineOr;
import Model.TypeBatiment;
import dao.DAOFactory;
import dao.JoueurDao;
import dao.VillageDao;
import form.ConnexionForm;

/**
 * Servlet implementation class ajoutMine
 */
@WebServlet("/ajoutMine")
public class ajoutMine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE="/WEB-INF/joueurConnecte/vueRessource.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur"; 
       
	private JoueurDao joueurDao;
	private VillageDao villageDao;
	
   @Override
	public void init() throws ServletException {
	   this.joueurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getJoueurDao();
	   this.villageDao =( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVillageDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response); 

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Joueur joueur = (Joueur) session.getAttribute(ATT_SESSION_JOUEUR);
		MineOr m= new MineOr();
		m.upgrade();
		villageDao.ajouterBatiment(joueur.getLogin(), m);
		joueur.getVillage().ajouterBatiment(TypeBatiment.MINEOR);
		session.setAttribute(ATT_SESSION_JOUEUR, joueur);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		
	}

}
