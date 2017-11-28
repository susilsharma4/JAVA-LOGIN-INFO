package login.datacom;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javaclass.dot.com.UserBean;
import javaclass.dot.com.UserDAO;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	    try {
	    	UserBean user=new UserBean();
	    	user.setUsername(request.getParameter("uname"));
	    	user.setPassword(request.getParameter("pass"));
	    	
	    	user=UserDAO.login(user);
	    	
	    	if(user.isValid()) {
	    		HttpSession session=request.getSession(true);
	    		session.setAttribute("currentSessionUser",user);
	    		response.sendRedirect("userLogged.jsp");
	    	}else {
	    		response.sendRedirect("invalidLogin.jsp");
	    	}
	    }
	    catch(Throwable theException) {
	    	System.out.println(theException);
	    }
	}

}
