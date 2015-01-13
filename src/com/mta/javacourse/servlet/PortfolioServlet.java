package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;
import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.service.PortfolioService;

/**
 * An instance of this class represents portfolio servlet.
 * @author Yaniv Elgay
 * @since 2014
 * date 22/12/2014
 */

public class PortfolioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/html");

		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio;

		try {
			portfolio = portfolioService.getPortfolio();
			resp.getWriter().println(portfolio.getHtmlPortfolio());
		}
		catch(BalanceException e)
		{
			resp.getWriter().println(e.getMessage());
		}
		catch(PortfolioFullException e)
		{
			resp.getWriter().println(e.getMessage());
		}
		catch(StockAlreadyExistsException e) 
		{
			resp.getWriter().println(e.getMessage());
		}
		catch(StockNotExistException e)
		{
			resp.getWriter().println(e.getMessage());
		}
	}
}
