package trainingmov;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;

import dao.HistoryDAO;
import bean.History;

/**
 * Servlet implementation class HistoryAPIView
 */
@WebServlet("/histories")
public class HistoryAPIView extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// CORS対応
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	
    	try {
	    	// クエリパラメータの取得
	    	String queryParam = request.getParameter("user_id");
	    	
    		// Historyデータ一覧取得
	    	List<History> historyList = null;
	    	HistoryDAO dao = new HistoryDAO();
	    	
	    	if (queryParam != null){
	    		historyList = dao.select(queryParam); 
	    		
	    		// JSONの返却
		    	response.setContentType("application/json");
		    	response.setCharacterEncoding("utf-8");
		    	response.getWriter().println(gson.toJson(historyList));
	    
	    	}
	    	
    	}catch (Exception e){
    		//TODO エラー処理未実装
    	}
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// CORS対応
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	
    	try {
    		
    		//リクエストBodyの取得
    		BufferedReader reader = request.getReader();
    	    Stream<String> lines = reader.lines();
    	    String jsonString = lines.collect(Collectors.joining("\r\n"));
    	    
    	    // modelオブジェクトにマッピング
	    	History history = gson.fromJson(jsonString, History.class);
    		
	    	// History に insert
	    	HistoryDAO dao1 = new HistoryDAO();
	    	dao1.insert(history);
	    	
    	}catch (Exception e){
    		//TODO エラー処理未実装
    	}
    }

}