package trainingmov;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;

import bean.Choice;
import dao.ChoiceDAO;

/**
 * Servlet implementation class ChoiceListAPIView
 */
@WebServlet("/choices")
public class ChoiceListAPIView extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// CORS対応
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	
    	try {
	    	// クエリパラメータの取得
	    	String queryParam = request.getParameter("movie_id");
	    	
    		// Movieデータ一覧取得
	    	List<Choice> choiceList;
	    	ChoiceDAO dao = new ChoiceDAO();

	    	if (queryParam != null){
	    		choiceList = dao.select(queryParam); 
	    		
	    		// JSONの返却
		    	response.setContentType("application/json");
		    	response.setCharacterEncoding("utf-8");
		    	response.getWriter().println(gson.toJson(choiceList));
	    
	    	}
	    	
    	}catch (Exception e){
    		//TODO エラー処理未実装
    	}
    }

}