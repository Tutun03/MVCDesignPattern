package in.sp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.sp.dbcon.DBConnection;
import in.sp.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/loginForm")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String myemail=request.getParameter("email1");
		String mypass=request.getParameter("pass1");
		try {
			Connection con=DBConnection.getConnection();
			String query= "select * from register where email=? and password=?";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1, myemail);
			ps.setString(2, mypass);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				User user=new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setCity(rs.getString("city"));
				
				HttpSession session=request.getSession();
				session.setAttribute("session_user", user);
				
				RequestDispatcher rd=request.getRequestDispatcher("/profile.jsp");
				rd.forward(request, response);
				
			}
			else
			{
				out.println("<h3 style='color:red'> Email and password did not match</h3>");
			}
		}
		catch(Exception e)
		{
			
		}
		
	}

}
