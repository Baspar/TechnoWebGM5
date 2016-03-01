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


@WebServlet("/GestionCombat")
public class GestionCombat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE_RETOUR="/WEB-INF/joueurConnecte/vueTousJoueurs.jsp";
	public static final String VUE="/WEB-INF/joueurConnecte/vueAdversaire.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur";  
	private VillageDao villageDao;
	
   @Override
	public void init() throws ServletException {
	   this.villageDao =( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVillageDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Joueur joueur = (Joueur) session.getAttribute(ATT_SESSION_JOUEUR);
		if(request.getParameter("retour")!=null){
			this.getServletContext().getRequestDispatcher( VUE_RETOUR ).forward( request, response );
			return;
		}
		//System.out.println(t);
		session.setAttribute(ATT_SESSION_JOUEUR, joueur);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response); 

	}

}
