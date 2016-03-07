package Controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Hashtable;

import Combat.Combat;
import Model.Joueur;
import Model.TypeRessource;
import Model.TypeSoldat;
import dao.DAOFactory;
import dao.VillageDao;


@WebServlet("/GestionCombat")
public class GestionCombat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE_RETOUR="/WEB-INF/joueurConnecte/vueTousJoueurs.jsp";
	public static final String VUE="/WEB-INF/joueurConnecte/vueAdversaire.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur";  
	public static final String ATT_SESSION_ADVERSAIRE = "sessionAdversaire"; 
	public static final String GAIN="gain";
	public static final String VUE_GAIN="/WEB-INF/joueurConnecte/vueGain.jsp";
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
		} else{
			Joueur joueur2=(Joueur) session.getAttribute(ATT_SESSION_ADVERSAIRE);
			if (request.getParameter("attaquer")!=null){
				boolean b1=false, b2=false, b3=false, b4=false;
				if(request.getParameter("choix1")!=null){
					b1=true;
				}	
				if(request.getParameter("choix2")!=null){
					b2=true;
				}
				if(request.getParameter("choix3")!=null){
					b3=true;
				}
				if(request.getParameter("choix4")!=null){
					b4=true;
				}
				Combat c= new Combat(joueur2.getVillage(),joueur.getVillage().getArmee());
				if(!b1 && !b2 && !b3 && !b4)
					c.placerSoldats(true, true, true, true);
				else
					c.placerSoldats(b3, b4, b1, b2);
				Hashtable<TypeRessource, Integer> h=new Hashtable<TypeRessource, Integer>();
				h=c.combattre();
				session.setAttribute(GAIN, h);
				int qte=h.get(TypeRessource.OR);
				qte=joueur.getVillage().getHDV().crediter(TypeRessource.OR, qte);
				villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.OR, qte);
				int qte2=h.get(TypeRessource.CHARBON);
				qte2=joueur.getVillage().getHDV().crediter(TypeRessource.CHARBON, qte2);
				villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.CHARBON, qte2);
				while(joueur.getVillage().supprimerSoldat(TypeSoldat.TREBUCHET))
					villageDao.modifNbSoldat(joueur.getLogin(), TypeSoldat.TREBUCHET, -1);
				while(joueur.getVillage().supprimerSoldat(TypeSoldat.ARCHER))
					villageDao.modifNbSoldat(joueur.getLogin(), TypeSoldat.ARCHER, -1);	
				session.setAttribute(ATT_SESSION_JOUEUR, joueur);
				this.getServletContext().getRequestDispatcher( VUE_GAIN ).forward( request, response); 
			}
		}

	}

}
