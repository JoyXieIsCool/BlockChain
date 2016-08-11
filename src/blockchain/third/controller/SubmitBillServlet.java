package blockchain.third.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blockchain.third.bean.Message;

/**
 * 接收用户的提交请求
 */
public class SubmitBillServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// 前端提供的是拼接好的命令字符串，格式为：1000_A_B_999
		String info = req.getParameter("info");
		SimpleDateFormat formatter = new SimpleDateFormat("_yyyyMMddhhmmssSSS");
		String timestamp = formatter.format(new Date());
		// 拼接时间戳
		info += timestamp;
		System.out.println("SubmitBillServlet_24: " + info);
		
		Message msg = new Message(info);
		MakeConcensus.insertMeassage(msg);
		response.getWriter().write("{\"status\":\"1\"}");
		response.getWriter().flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	public static void main(String[] args) {
		SimpleDateFormat formatter = new SimpleDateFormat("_yyyyMMddhhmmssSSS");
		System.out.println(formatter.format(new Date()));
		System.out.println("{\"status\":\"1\"}");
	}
}
