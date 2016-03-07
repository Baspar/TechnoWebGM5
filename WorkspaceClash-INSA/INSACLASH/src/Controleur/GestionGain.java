package Controleur;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/GestionGain")
public class GestionGain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE_RETOUR="/WEB-INF/joueurConnecte/vueCaserne.jsp";
	public static final String VUE="/WEB-INF/joueurConnecte/vueGain.jsp";
	public static final String ATT_SESSION_JOUEUR = "sessionJoueur";  
	public static final String ATT_SESSION_ADVERSAIRE = "sessionAdversaire"; 
	public static final String GAIN="gain";
	public static final String VUE_GUERRE="/WEB-INF/joueurConnecte/vueGuerre.jsp";
	
   @Override
	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("caserne")!=null){
			this.getServletContext().getRequestDispatcher( VUE_RETOUR ).forward( request, response );
			return;
		} else{
			int nbTour=0;
			try{		
				String fichier ="/tmp/nbTour.txt";
				
				InputStream ips=new FileInputStream(fichier); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				ligne=br.readLine();
					nbTour=Integer.parseInt(ligne);
					br.close(); 	
			}		
			catch (Exception e){
				e.printStackTrace();
			}
			request.setAttribute("tourCourant", 0);
			session.setAttribute("nbTour", nbTour);
			session.setAttribute("tourCourant", 0);
				this.getServletContext().getRequestDispatcher( VUE_GUERRE ).forward( request, response); 
		}

	}

}
