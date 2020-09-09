package trainingmov;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.User;
import dao.UserDAO;

/**
 * Servlet implementation class UserInfoAPIView
 */
@WebServlet("/users")
public class UserInfoAPIView extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	// CORS対応
    	response.setHeader("Access-Control-Allow-Origin", "*");

    	try {
	    	// クエリパラメータの取得
	    	String queryParam = request.getParameter("user_id");

    		// Userデータ一覧取得
	    	List<User> userInfo;
	    	UserDAO dao = new UserDAO();

	    	if (queryParam == null){
	    		userInfo = dao.selectAll();
	    	}else{
	    		userInfo = dao.select(queryParam);
	    	}

	    	// JSONの返却
	    	response.setContentType("application/json");
	    	response.setCharacterEncoding("utf-8");
	    	response.getWriter().println(gson.toJson(userInfo));

    	}catch (Exception e){
    		//TODO エラー処理未実装
    	}
    }

}