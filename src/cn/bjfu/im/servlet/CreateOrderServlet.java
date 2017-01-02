package cn.bjfu.im.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.javafx.sg.prism.NGWebView;

import cn.bjfu.im.dao.GoodsInfoInitDAO;
import cn.bjfu.im.dao.OrderDAO;
import cn.bjfu.im.dao.OrderDetailDAO;
import cn.bjfu.im.dao.StockDAO;
import cn.bjfu.im.vo.GoodsInfoVO;
import cn.bjfu.im.vo.MemberVO;
import cn.bjfu.im.vo.OrderDetailVO;
import cn.bjfu.im.vo.OrderVO;

/**
 * Servlet implementation class CreateOrderServlet
 */
@WebServlet("/createOrder.do")
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrderServlet() {
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
		int mid = 0;
		int oid = 0;
		Map<Integer, Integer> cartMap = null;
		Collection<Integer> goodsSet = null;
		HttpSession session = request.getSession();
		int totalPrice = 0;
		String address = "北京市海淀区清华东路35号北京林业大学,王小明，15650700000";
		String page = "orderList.jsp";
		String msg = "success";
		
		//判断是否已经登陆
		if(session.getAttribute("userid") == null){
			//msg="noLogin";
			page = "cart.jsp";
		}else{
			mid = (Integer)session.getAttribute("userid");
			cartMap = (Map<Integer, Integer>)session.getAttribute("GOODS_IN_CART");
			if(cartMap == null){
				//msg="noGoods";
				page = "cart.jsp";
			}else{
				//查找商品，计算总价格
				goodsSet =  cartMap.keySet();
				GoodsInfoInitDAO goodsDao = new GoodsInfoInitDAO();
				for(int gid:goodsSet) {
                    GoodsInfoVO vo = goodsDao.getGoodsInfo(gid);
                    totalPrice+=vo.getPrice()*cartMap.get(gid);
                }
				//1.创建orderVO
				OrderVO orderVo = new OrderVO(mid, totalPrice, address);
				//2.创建orderDAO,将Order写入数据库
				OrderDAO orderDao = new OrderDAO();
				oid = orderDao.addOrder(orderVo);
				//3.创建orderDetailVO orderDetailDAO orderDeatilVoList
				OrderDetailVO orderDetailVo = null;
				OrderDetailDAO orderDetailDao = new OrderDetailDAO();
				ArrayList<OrderDetailVO> orderDeatailVoList = new ArrayList<>();
				StockDAO sDao = new StockDAO();
				for(int gid:goodsSet) {
                    GoodsInfoVO vo = goodsDao.getGoodsInfo(gid);
                    orderDetailVo = new OrderDetailVO(oid, gid, cartMap.get(gid));
                    orderDeatailVoList.add(orderDetailVo);
                    //减少库存
					sDao.decrStock(gid, cartMap.get(gid));
                }
				//4.使用orderDetailDao将订单商品列表写入数据库
				boolean detailFlag = orderDetailDao.addOrderDetail(orderDeatailVoList);
				if(detailFlag){
					request.getSession().setAttribute("GOODS_IN_CART", null);
					
				}
			}
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}
	

}
