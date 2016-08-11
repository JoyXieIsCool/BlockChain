package blockchain.third.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blockchain.third.bean.GlobalVariable;

/**
 * 前端轮询，查看是否有响应操作需要用户处理，如果有则告知前端
 */
public class QueryResponseServlet extends HttpServlet{

	/**
	 * 前端查询是否需要弹窗响应
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
		
		PrintWriter writer = response.getWriter();
		
		if (GlobalVariable.needResponse == true) {
			writer.write("{\"alert\": \"1\", \"msg\":" + GlobalVariable.alertMessage + "}");
		} else {
			writer.write("{\"alert\": \"0\"}");
		}
	}

	/**
	 * 前端返回用户的点击选择结果
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
		
		// 用户已经响应完了，无需再弹窗提醒
		GlobalVariable.needResponse = false;
		String isAck = req.getParameter("isAck");
		if ("Y".equals(isAck)) {
			// 发送确认广播
			MakeConcensus.ackRequest(true);
		} else {
			// 发送否认广播
			MakeConcensus.ackRequest(false);
		}
	}

}
