package Controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Batiment;
import Model.Joueur;
import Model.TypeBatiment;
import dao.DAOFactory;
import dao.VillageDao;

/**
 * Servlet implementation class GestionVillage
 */
@WebServlet("/GestionVillage")
public class GestionVillage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE="/WEB-INF/joueurConnecte/vueVillage.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur";  
	public static final String ATT_SESSION_BATIMENT_A_DEPLACER="sessionbatiment";
	
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
		Batiment b= (Batiment) session.getAttribute(ATT_SESSION_BATIMENT_A_DEPLACER);
		if(b==null){
			//System.out.println("ok");
			boolean bol=false;
			if (request.getParameter("supprimer")!=null){
				for(int i=0;i<14; i++){
					for(int j=0; j<14;j++){
							if(joueur.getVillage().getCarte().get(i).get(j)!=null){
									Batiment b2=joueur.getVillage().getBatiment(i, j);
									joueur.getVillage().deplacerBatiment(b2, -1, -1);
									villageDao.deplacerBatiment(joueur.getLogin(), b2, -1, -1);
							}
						}	
					}
				bol=true;	
			}
			if(!bol)
			for(int i=0;i<14; i++){
				for(int j=0; j<14;j++){
					if (request.getParameter("case"+String.valueOf(14*j+i))!=null){
						if(joueur.getVillage().getCarte().get(i).get(j)!=null){
								b=joueur.getVillage().getCarte().get(i).get(j);
								bol=true;
						}
					}	
				}
			}
			if(!bol){
				if(request.getParameter("hdv")!=null){
					b=joueur.getVillage().getBatiment(TypeBatiment.HDV, 0);
					bol=true;
				} else {
					if (request.getParameter("caserne")!=null){
						b=joueur.getVillage().getBatiment(TypeBatiment.CASERNE, 0);
						bol=true;
					} else{
						for(int i=0; i<joueur.getVillage().getBatiment(TypeBatiment.CANON).size();i++){
							if(request.getParameter("canon"+String.valueOf(joueur.getVillage().getBatiment(TypeBatiment.CANON, i).getId()))!=null){
								b=joueur.getVillage().getBatiment(TypeBatiment.CANON, i);
								bol=true;
							}
						}
						if(!bol)
						for(int i=0; i<joueur.getVillage().getBatiment(TypeBatiment.MORTIER).size();i++){
							if(request.getParameter("mortier"+String.valueOf(joueur.getVillage().getBatiment(TypeBatiment.MORTIER, i).getId()))!=null){
								b=joueur.getVillage().getBatiment(TypeBatiment.MORTIER, i);
								bol=true;
							}
						}
						if(!bol)
						for(int i=0; i<joueur.getVillage().getBatiment(TypeBatiment.MINEOR).size();i++){
							if(request.getParameter("mineor"+String.valueOf(joueur.getVillage().getBatiment(TypeBatiment.MINEOR, i).getId()))!=null){
								b=joueur.getVillage().getBatiment(TypeBatiment.MINEOR, i);
								bol=true;
							}
						}
						if(!bol)
						for(int i=0; i<joueur.getVillage().getBatiment(TypeBatiment.MINECHARBON).size();i++){
							if(request.getParameter("minecharbon"+String.valueOf(joueur.getVillage().getBatiment(TypeBatiment.MINECHARBON, i).getId()))!=null){
								b=joueur.getVillage().getBatiment(TypeBatiment.MINECHARBON, i);
								bol=true;
							}
						}
					}
				}
			}
		}else{
			if (request.getParameter("annuler")!=null){
				b=null;
			}else
			for(int i=0;i<14; i++){
				for(int j=0; j<14;j++){
					if (request.getParameter("case"+String.valueOf(14*j+i))!=null){
						if(joueur.getVillage().getCarte().get(i).get(j)==null){
								joueur.getVillage().deplacerBatiment(b, i, j);
								villageDao.deplacerBatiment(joueur.getLogin(), b, i, j);
						}
						b=null;
					}	
				}
			}
		}
		session.setAttribute(ATT_SESSION_JOUEUR, joueur);
		session.setAttribute(ATT_SESSION_BATIMENT_A_DEPLACER, b);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
