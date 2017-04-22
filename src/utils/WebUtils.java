package utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	public static <O>  void skip(HttpServletRequest request,HttpServletResponse response, O url){
		if(url instanceof RequestDispatcher){
			try {
				((RequestDispatcher) url).forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}else if(url instanceof String){
			try {
				response.sendRedirect(request.getContextPath()+ url);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}
