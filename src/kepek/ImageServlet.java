package kepek;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
@ManagedBean
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName="Szavazas")
	private EntityManager em;
    
    public Kep findKep(String param1){
		System.out.println(param1+"<-cucc");
		Kep k=em.find(Kep.class, Integer.parseInt(param1));
		return k;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param1 = request.getParameter("param1");
		
		
		Kep k=findKep(param1);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getOutputStream().write(k.getKep());
	}

}
