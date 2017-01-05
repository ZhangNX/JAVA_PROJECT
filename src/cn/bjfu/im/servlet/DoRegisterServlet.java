package cn.bjfu.im.servlet;

import java.io.IOException;

import javax.imageio.spi.RegisterableService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bjfu.im.dao.MemberDAO;
import cn.bjfu.im.vo.MemberVO;

/**
 * Servlet implementation class DoRegisterServlet
 */
@WebServlet("/register.do")
public class DoRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		System.out.println("name:"+name+"pwd:"+pwd);
		MemberDAO dao = new MemberDAO();
		String page = "register.jsp";
		
		if (dao.isNoneByName(name)) {//没有同名用户
			MemberVO vo = new MemberVO(name, pwd);
			dao.add(vo);
			request.setAttribute("msg", "success");
		}else{//有同名用户
			request.setAttribute("msg", "existed");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

}
