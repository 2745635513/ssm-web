package com.example.demo.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@Controller
// @RestController
@RequestMapping("/order")

public class OrderController {

	private static final Log logger = (Log) LogFactory.getLog(OrderController.class);

	@Resource
	private OrderService orderService;

	/*
	 * @RequestMapping(value = "/showOrder", method = RequestMethod.GET) public
	 * String toIndex(HttpServletRequest request, Model model){ // int userId =
	 * Integer.parseInt(request.getParameter("id")); List<Map<String, Object>> order
	 * = this.orderService.getOrderById(2); return "index"; }
	 */

	@ResponseBody
	@RequestMapping(value = "/ajaxshowOrder", method = RequestMethod.GET)
	public Map<String, Integer> ajax(HttpServletRequest request, Model model) {
		logger.info("时间段订单");
		Map<String, Integer> order = this.orderService.getOrderById(1);
		return order;
	}


	// 时间筛选订单
	@ResponseBody
	@RequestMapping(value = "/ajaxshowOrderT", method = RequestMethod.GET)
	public List<Map<String, Object>> ajaxT(HttpServletRequest request, Model model) {

		// int userId = Integer.parseInt(request.getParameter("id"));
		logger.info("时间筛选订单");
		String stime = request.getParameter("stime");
		String etime = request.getParameter("etime");
		System.out.println(stime);
		System.out.println(etime);
		List<Map<String, Object>> order = this.orderService.getOrderByIdT(1, stime, etime);
		return order;
	}

	// 月现金收入
	@ResponseBody
	@RequestMapping(value = "/ajaxshowMonMoney", method = RequestMethod.GET)
	public List<Map<String, Object>> ajaxshowMonMoney(HttpServletRequest request, Model model) {
		logger.info("现金收入");
		List<Map<String, Object>> order = this.orderService.getOrderById1(1);
		return order;
	}

	//月总金额
	@ResponseBody
	@RequestMapping(value = "/ajaxshowMonTotal", method = RequestMethod.GET)
	public List<Map<String, Object>> ajaxshowMonTotal(HttpServletRequest request, Model model) {
		logger.info("总金额");
		List<Map<String, Object>> order = this.orderService.getOrderById2(1);
		return order;
	}


	// 删除一条订单记录
	@RequestMapping(value="/delete",method = RequestMethod.DELETE)
	public ResponseEntity<Map<String,Object>> deleteOrderById(String id)
			throws Exception {
		int result = this.orderService.deleteOrder(Integer.parseInt(id));
		Map<String,Object> map = new HashMap<String,Object>();
		if (result > 0) {
			logger.info("删除订单成功..");
			String message="删除订单成功";
			map.put("message", message);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);

		} else {
			logger.error("删除订单失败..");
			String message="删除订单失败";
			map.put("message", message);
			//返回状态码400，代表请求错误
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
	
	}
	
	//
	@ResponseBody
	@RequestMapping(value="/updateorder",method = RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> updateorder( @RequestBody Order order) throws IOException, ServletException {
		int result = this.orderService.updateOrder(order);
		Map<String,Object> map = new HashMap<String,Object>();
		if (result > 0) {
			logger.info("更改订单数据成功");
			String message="更新订单数据成功";
			map.put("message", message);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		} else {
			logger.error("更新订单数据失败");
			String message="更新订单数据失败";
			map.put("message", message);
			//返回状态码400，代表请求错误
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
	}
	
	//添加订单
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> insert(HttpServletRequest request, HttpServletResponse response,@RequestBody Order order)
			throws IOException, ServletException {
		int result = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		
		if (order != null) {
			System.out.println(order);
			result = orderService.addOrder(order);
		}
		if (result > 0) {
			logger.info("添加成功..");
			String message="添加订单成功";
			map.put("message", message);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		} else {
			logger.error("添加失败");
			String message="添加订单失败";
			map.put("message", message);
			//返回状态码400，代表请求错误
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@RequestMapping("/showList")
	public Map<String, Object> showList(HttpServletRequest request,Model model,String page,String limit) {
		logger.info("orderList");
		System.out.println(page);
		System.out.println(limit);
		int offset=Integer.parseInt(limit)*(Integer.parseInt(page)-1);
		List<Order> orderlist = this.orderService.getOrder(Integer.parseInt(limit), offset);
		int count = this.orderService.getOrderNum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", orderlist);
		map.put("code",0);
		map.put("msg","");
		map.put("count",count);
		return map;
		//return this.orderService.getOrder(Integer.parseInt(limit), Integer.parseInt(limit)*(Integer.parseInt(page)-1));
	} 

}


/*// 平均订单
@ResponseBody
@RequestMapping(value = "/ajaxshowAveOrder", method = RequestMethod.GET)
public Map<String, Integer> ajaxshowAveOrder(HttpServletRequest request, Model model) {
	 System.out.println("---11--1"+new Date()); 
	Map<String, Integer> order = this.orderService.getOrderById2(1);
	 System.out.println("----33-22"+new Date()); 
	return order;
}

// 平均订单消费
@ResponseBody
@RequestMapping(value = "/ajaxshowOrderMoney", method = RequestMethod.GET)
public Map<String, Integer> ajaxshowOrderMoney(HttpServletRequest request, Model model) {
	Map<String, Integer> order = this.orderService.getOrderById3(1);
	return order;
}*/

/*
// 月订单
@ResponseBody
@RequestMapping(value = "/ajaxshowMonthOrder", method = RequestMethod.GET)
public List<Map<String, Object>> ajaxshowMonthOrder(HttpServletRequest request, Model model) {
	List<Map<String, Object>> order = this.orderService.getOrderById6(1);
	return order;
}*/