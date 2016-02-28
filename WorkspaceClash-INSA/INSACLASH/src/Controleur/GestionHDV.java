package Controleur;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Joueur;
import Model.MineCharbon;
import Model.MineOr;
import Model.TypeBatiment;
import Model.TypeRessource;
import dao.DAOFactory;
import dao.JoueurDao;
import dao.VillageDao;

/**
 * Servlet implementation class GestionHDV
 */
@WebServlet("/GestionHDV")
public class GestionHDV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE="/WEB-INF/joueurConnecte/vueHDV.jsp";
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
		//On doit ajouter une mine or
		if (request.getParameter("ajouteror")!=null){
			MineOr m= new MineOr();
			m.upgrade();
			if (joueur.getVillage().ajouterBatiment(TypeBatiment.MINEOR))
				villageDao.ajouterBatiment(joueur.getLogin(), m);
			//on doit ajouter une mine de charbon
		}else {if(request.getParameter("ajoutercharbon")!=null){
			MineCharbon m= new MineCharbon();
			m.upgrade();
			if (joueur.getVillage().ajouterBatiment(TypeBatiment.MINECHARBON))
				villageDao.ajouterBatiment(joueur.getLogin(), m);
			}else {
				boolean trouve=false;
				//on reg si on améliorer une mine or
				if(!trouve){
					for(int i=0; i<joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).size(); i++)
						if(request.getParameter("ameliorer"+joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).get(i).getId())!=null){
							MineOr m= (MineOr) joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).get(i);
							if( joueur.getVillage().upgradeBatiment(TypeBatiment.MINEOR, i) )
								villageDao.ameliorerBatiment(joueur.getLogin(), m);
							trouve=true;
						}	
				}
				// on reg si on vide une mine or
				if(!trouve){
				for(int i=0; i<joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).size(); i++)
					if(request.getParameter("vider"+joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).get(i).getId())!=null){
						MineOr m=( MineOr) joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).get(i);
						int qte= m.prelever(); 
						villageDao.viderRessource(joueur.getLogin(), m.getId(), new Date());
						qte=joueur.getVillage().getHDV().crediter(TypeRessource.OR, qte);
						villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.OR, qte);
					}	
				}
				//on reg si on ameliore une mine de charbon
				if(!trouve){
					for(int i=0; i<joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON).size(); i++)
						if(request.getParameter("ameliorer"+joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON).get(i).getId())!=null){
							MineCharbon m= (MineCharbon) joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON).get(i);
							if( joueur.getVillage().upgradeBatiment(TypeBatiment.MINECHARBON, i) )
								villageDao.ameliorerBatiment(joueur.getLogin(), m);
							trouve=true;
						}	
				}
				//on reg si on vide une mine de charbon
				if(!trouve){
					for(int i=0; i<joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON).size(); i++)
						if(request.getParameter("vider"+joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON).get(i).getId())!=null){
							MineCharbon m=( MineCharbon) joueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON).get(i);
							int qte= m.prelever(); 
							villageDao.viderRessource(joueur.getLogin(), m.getId(), new Date());
							qte=joueur.getVillage().getHDV().crediter(TypeRessource.CHARBON, qte);
							villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.CHARBON, qte);
						}	
				}
			}
			}
		session.setAttribute(ATT_SESSION_JOUEUR, joueur);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		
	}

}
