package Controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Hashtable;

import Combat.Combat;
import Model.HDV;
import Model.Joueur;
import Model.TypeBatiment;
import Model.TypeRessource;
import dao.DAOFactory;
import dao.JoueurDao;
import dao.VillageDao;


@WebServlet("/GestionGuerre")
public class GestionGuerre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE_FIN="/WEB-INF/joueurConnecte/vueGain.jsp";
	public static final String VUE="/WEB-INF/joueurConnecte/vueGuerre.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur";  
	public static final String ATT_SESSION_ADVERSAIRE = "sessionAdversaire"; 
	public static final String GAIN="gain";
	private VillageDao villageDao;
	
   @Override
	public void init() throws ServletException {
	   this.villageDao =( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVillageDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int i=(Integer) session.getAttribute("tourCourant");
		int nbTour=(Integer) session.getAttribute("nbTour");
		i++;
		if(i<nbTour){
			request.setAttribute("tourCourant", i);
			session.setAttribute("tourCourant",i);
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response); 
		}
		else
			this.getServletContext().getRequestDispatcher( VUE_FIN ).forward( request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int i=(Integer) session.getAttribute("tourCourant");
		int nbTour=(Integer) session.getAttribute("nbTour");
		i++;
		System.out.println(i);
		if(i<nbTour){
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response); 
		}
		else
			this.getServletContext().getRequestDispatcher( VUE_FIN ).forward( request, response); 

			
	}

}
