package Controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Caserne;
import Model.Joueur;
import Model.TypeBatiment;
import Model.TypeRessource;
import Model.TypeSoldat;
import dao.DAOFactory;
import dao.VillageDao;

/**
 * Servlet implementation class GestionCaserne
 */
@WebServlet("/GestionCaserne")
public class GestionCaserne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE="/WEB-INF/joueurConnecte/vueCaserne.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur";  
	public static final String ATT_SESSION_MANQUE_RESSOURCE="manqueRessource";
	private VillageDao villageDao;
	
   @Override
	public void init() throws ServletException {
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
		String t= (String) session.getAttribute(ATT_SESSION_MANQUE_RESSOURCE);
		t=null;
		//on doit ameliorer la caserne
		if(request.getParameter("ameliorercaserne")!=null){
			Caserne c=(Caserne) joueur.getVillage().getCaserne();
			if( joueur.getVillage().upgradeBatiment(TypeBatiment.CASERNE, 0) ){
				villageDao.ameliorerBatiment(joueur.getLogin(), c);
				villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.CHARBON, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON));
			}	
			else
				t="true";
		} else
			//on doit ameliorer les archers
			if(request.getParameter("ameliorerarcher")!=null){
				if(joueur.getVillage().upgradeSoldat(TypeSoldat.ARCHER)){
					villageDao.ameliorerSoldat(joueur.getLogin(), TypeSoldat.ARCHER);
					villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.CHARBON, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON));
				}
				else
					t="true";
			} else 
				//on doit ameliorer les trebuchets
				if(request.getParameter("ameliorertrebuchet")!=null){
					if(joueur.getVillage().upgradeSoldat(TypeSoldat.TREBUCHET)){
						villageDao.ameliorerSoldat(joueur.getLogin(), TypeSoldat.TREBUCHET);
						villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.CHARBON, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON));
					}
					else
						t="true";
				} else
					//on doit ajouter un archer
					if(request.getParameter("ajouterarcher")!=null){
						if(joueur.getVillage().ajouterSoldat(TypeSoldat.ARCHER)){
							villageDao.modifNbSoldat(joueur.getLogin(), TypeSoldat.ARCHER, 1);
							villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.CHARBON, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON));
						}
						else
							t="true";
					}else
						//on doit ajouter un trebuchet
						if(request.getParameter("ajoutertrebuchet")!=null){
							if(joueur.getVillage().ajouterSoldat(TypeSoldat.TREBUCHET)){
								villageDao.modifNbSoldat(joueur.getLogin(), TypeSoldat.TREBUCHET, 1);
								villageDao.miseAJourRessource(joueur.getLogin(), TypeRessource.CHARBON, joueur.getVillage().getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON));
							}
							else
								t="true";
						}else
							//on doit supprimer un trebuchet
							if(request.getParameter("supprimertrebuchet")!=null){
								if(joueur.getVillage().supprimerSoldat(TypeSoldat.TREBUCHET)){
									villageDao.modifNbSoldat(joueur.getLogin(), TypeSoldat.TREBUCHET, -1);
								}
								else
									t="true";
							}
							else
								//on doit supprimer un archer
								if(request.getParameter("supprimerarcher")!=null){
									if(joueur.getVillage().supprimerSoldat(TypeSoldat.ARCHER)){
										villageDao.modifNbSoldat(joueur.getLogin(), TypeSoldat.ARCHER, -1);
									}
									else
										t="true";
								}
		session.setAttribute(ATT_SESSION_JOUEUR, joueur);
		session.setAttribute(ATT_SESSION_MANQUE_RESSOURCE, t);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
   
   

}
