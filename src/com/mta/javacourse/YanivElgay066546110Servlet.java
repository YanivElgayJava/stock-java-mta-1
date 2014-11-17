package com.mta.javacourse;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class YanivElgay066546110Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		int radius = 50;
		double area = (Math.pow(radius,2)) * (Math.PI);
		String circleArea = new String("Area of circle with radius "+radius+" is : "+area+" square-cm");
		
		int hypotenuse = 50;
		double angleB;
		float opposite;
		
		angleB = 30 * (Math.PI/180);
		opposite = (float)(hypotenuse * Math.sin(angleB));
		String calcOpposite = new String("Length of opposite where angle B is 30 degrees and hypotenuse length is "+hypotenuse+" cm is :"+opposite+" cm ");
		
		int base = 20;
		int exp = 13;
		double power = Math.pow(base,exp);
		String calcPower = new String("Power of "+base+" with exp of "+exp+" is "+power+" ");
		
		String line1 = new String(circleArea);
		String line2 = new String(calcOpposite);
		String line3 = new String(calcPower);
	
		String resultStr = line1 + "<br>" + line2 + "<br>" + line3;
		resp.getWriter().println(resultStr);
	}
}
