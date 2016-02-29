package Controleur;

import java.awt.Canvas;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Canon;
import Model.Joueur;
import Model.MineCharbon;
import Model.MineOr;
import Model.Mortier;
import Model.TypeBatiment;
import Model.TypeRessource;
import dao.DAOFactory;
import dao.JoueurDao;
import dao.VillageDao;

/**
 * Servlet implementation class GestionDefense
 */
@WebServlet("/GestionDefense")
public class GestionDefense extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	   
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE="/WEB-INF/joueurConnecte/vueDefense.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur";   
	public static final String ATT_SESSION_MANQUE_RESSOURCE="manqueRessource";

	private JoueurDao joueurDao;
	private VillageDao villageDao;
	
   @Override
	public void init() throws ServletException {
	   this.joueurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getJoueurDao();
	   this.villageDao =( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVillageDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(ATT_SESSION_MANQUE_RESSOURCE, null);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response); 

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Joueur joueur = (Joueur) session.getAttribute(ATT_SESSION_JOUEUR);
		String t=null;
		//On doit ajouter un canon
		if (request.getParameter("ajoutercanon")!=null){
			Canon c= new Canon();
			c.upgrade();
		//	System.out.println("ok");
			if (joueur.getVillage().ajouterBatiment(TypeBatiment.CANON)){
			//	System.out.println("ok");
				villageDao.ajouterBatiment(joueur.getLogin(), c);
				joueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON).get(joueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON).size()-1).setId(c.getId());
				villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.OR, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.OR));
			}
			else 
				t="true";
			//on doit ajouter un mortier
		}else {if(request.getParameter("ajoutermortier")!=null){
				Mortier m= new Mortier();
				m.upgrade();
				if (joueur.getVillage().ajouterBatiment(TypeBatiment.MORTIER)){
					villageDao.ajouterBatiment(joueur.getLogin(), m);
					joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER).get(joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER).size()-1).setId(m.getId());
					villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.OR, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.OR));	
				}	
				else 
					t="true";
			}else {
				boolean trouve=false;
				//on reg si on améliorer un canon
				if(!trouve){
					for(int i=0; i<joueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON).size(); i++)
						if(request.getParameter("ameliorer"+joueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON).get(i).getId())!=null){
							Canon c= (Canon) joueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON).get(i);
							//System.out.println(m.getId()); 
							if( joueur.getVillage().upgradeBatiment(TypeBatiment.CANON, i) ){
								villageDao.ameliorerBatiment(joueur.getLogin(), c);
								villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.OR, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.OR));
							}	
							else 
								t="true";
							trouve=true;
						}	
				}
				//on reg si on ameliore un mortier
				if(!trouve){
					for(int i=0; i<joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER).size(); i++)
						if(request.getParameter("ameliorer"+joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER).get(i).getId())!=null){
							Mortier m= (Mortier) joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER).get(i);
							if( joueur.getVillage().upgradeBatiment(TypeBatiment.MORTIER, i) ){ 
								villageDao.ameliorerBatiment(joueur.getLogin(), m);
								villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.OR, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.OR));
							}	
							else 
								t="true";
							trouve=true;
						}	
				}
			}
			}
		session.setAttribute(ATT_SESSION_JOUEUR, joueur);
		session.setAttribute(ATT_SESSION_MANQUE_RESSOURCE, t);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		
	}

}
