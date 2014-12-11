package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;
import com.mta.javacourse.service.PortfolioService;

/**
 * An instance of this class represents portfolio servlet.
 * @author Yaniv Elgay
 * @since 2014
 * date 11/12/2014
 */

public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/html");


		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();

		Portfolio portfolio2 = new Portfolio(portfolio);

		resp.getWriter().println(portfolio.getHtmlPortfolio());
		portfolio2.setTitle(" <h1> Portfolio 2# </h1> ");
		resp.getWriter().println(portfolio2.getHtmlPortfolio());

		portfolio.removeStock(0);
		resp.getWriter().println(portfolio.getHtmlPortfolio() + "<br>");
		resp.getWriter().println(portfolio2.getHtmlPortfolio() + "<br>");
		portfolio2.getStocks()[2].setBid(55.55f);

		resp.getWriter().println(portfolio.getHtmlPortfolio() + "<br>");
		resp.getWriter().println(portfolio2.getHtmlPortfolio() + "<br>");
	}
}
