package cn.bjfu.im.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bjfu.im.dao.GoodsInfoInitDAO;
import cn.bjfu.im.vo.GoodsInfoVO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search.do")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String se = request.getParameter("searchname");
		int ipage=1;
		if (request.getParameter("iPage") != null) {
			String spage = request.getParameter("iPage");
			ipage = Integer.parseInt(spage);
		}
		GoodsInfoInitDAO dao = new GoodsInfoInitDAO();
		int count = dao.getSearchedGoodsCount(se);
		HashMap<Integer, GoodsInfoVO> map = dao.getSearchedGoodsPage(se, ipage,
				4);
		request.setAttribute("searchname", se);
		request.setAttribute("secount", count);
		request.setAttribute("map", map);
		System.out.println(se);
		System.out.println(ipage);
		RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
		rd.forward(request, response);
	}

}
