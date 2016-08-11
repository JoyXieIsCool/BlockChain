package blockchain.third.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blockchain.third.utils.JsonUtil;

/**
 * 查询当前有哪些节点
 */
@SuppressWarnings("serial")
public class QueryNodeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Writer writer = resp.getWriter();
		writer.write(JsonUtil.getNodeInfo());
		writer.flush();
	}

}
