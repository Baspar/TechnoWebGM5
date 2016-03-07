package Controleur;

import java.io.IOException;
import java.util.Vector;

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
import Model.Village;
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
	public static final String VUE_ADVERSAIRE="/WEB-INF/joueurConnecte/vueAdversaire.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur";  
	public static final String ATT_SESSION_ADVERSAIRE ="sessionAdversaire";
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
		Vector<String> v=joueurDao.trouverTousLesJoueurs(jou.getLogin());
		Vector<Vector<String> > attribut=new Vector<Vector<String>>();
		for(int i=0; i<v.size();i++){
			attribut.add(new Vector<String>());
			Village v2=villageDao.chargerVillage(v.get(i));
			attribut.get(i).add(v.get(i));
			attribut.get(i).add(String.valueOf(v2.getHDV().getNiveau()));
			int qte=0;
			for(int j=0; j<v2.getBatiment(TypeBatiment.MINEOR).size();j++){
				MineOr m= (MineOr) v2.getBatiment(TypeBatiment.MINEOR, j);
				qte+=m.calculProduction();
			}
			qte+=v2.getHDV().getQuantiteActuelle().get(TypeRessource.OR)/3;
			attribut.get(i).add(String.valueOf(qte));
			int qte2=0;
			for(int j=0; j<v2.getBatiment(TypeBatiment.MINECHARBON).size();j++){
				MineCharbon m= (MineCharbon) v2.getBatiment(TypeBatiment.MINECHARBON, j);
				qte2+=m.calculProduction();
			}
			qte2+=v2.getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON)/3;
			attribut.get(i).add(String.valueOf(qte2));
		}
		session.setAttribute(ATT_SESSION_LISTE_JOUEUR, attribut);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Joueur joueur = (Joueur) session.getAttribute(ATT_SESSION_JOUEUR);
		Joueur joueurAdverse;
		Vector<String> v=joueurDao.trouverTousLesJoueurs(joueur.getLogin());
		for(int i=0; i<v.size();i++){
			if(request.getParameter(v.get(i))!=null){
				joueurAdverse=new Joueur();
				joueurAdverse.setLogin(v.get(i));
				joueurAdverse.setVillage(villageDao.chargerVillage(v.get(i)));
				session.setAttribute(ATT_SESSION_ADVERSAIRE, joueurAdverse);
				this.getServletContext().getRequestDispatcher( VUE_ADVERSAIRE ).forward( request, response );
				return ;
			}				
		}
		//System.out.println(t);
		session.setAttribute(ATT_SESSION_JOUEUR, joueur);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
