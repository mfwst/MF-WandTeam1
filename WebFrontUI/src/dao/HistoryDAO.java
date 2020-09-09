package dao;

import java.sql.*;
import java.util.*;
import bean.History;

/**
 * trainingmov.t_history　テーブルのDAOクラス
 */
public class HistoryDAO extends DAO {
    public List<History> select(String user_id) throws Exception {

        List<History> list = new ArrayList<>();

        // DB接続
        Connection connection = getConnection();

        // SQL作成
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM trainingmov.t_history Where user_id = ?");
        statement.setString(1, user_id);

        // SQL実行
        ResultSet result = statement.executeQuery();

        // データをセット
        while (result.next()) {
            History history = new History();
            history.setUserId(result.getString("user_id"));
            history.setCourseId(result.getString("course_id"));
            history.setMovieId(result.getString("movie_id"));
            list.add(history);
        }

        // 接続解除
        statement.close();
        connection.close();

        return list;
    }
    
    public void insert(History history) throws Exception {
    	
    	Connection connection = null;
    	PreparedStatement statement = null;
    	
    	try{
	    	// DB接続
	        connection = getConnection();
	
	        // SQL作成
	        statement = connection.prepareStatement("INSERT INTO trainingmov.t_history values (?, ?, ?)");
	        statement.setString(1, history.getUserId());
	        statement.setString(2, history.getCourseId());
	        statement.setString(3, history.getMovieId());
	
	        // SQL実行
	        statement.executeUpdate();
	        connection.commit();
        
    	} catch (Exception e){
    		connection.rollback();
    		e.printStackTrace();
    	} finally{
            // 接続解除
            statement.close();
            connection.close();
    	}

    }
}