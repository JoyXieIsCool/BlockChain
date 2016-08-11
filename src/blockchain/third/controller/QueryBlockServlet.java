package blockchain.third.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blockchain.third.bean.Block;
import blockchain.third.bean.DB;
import blockchain.third.utils.JsonUtil;

/**
 * 根据当前页面已经有的区块，展示最新的区块
 */
@SuppressWarnings("serial")
public class QueryBlockServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int currentBlock = Integer.parseInt(req.getParameter("currentBlock"));
		List<Block> blocks = DB.getDBInstance().queryBlock(currentBlock);
		String blockJsonString = JsonUtil.transBlock2JsonStr(blocks);
		
		PrintWriter writer = resp.getWriter();
		writer.write(blockJsonString);
		writer.flush();
	}

}
