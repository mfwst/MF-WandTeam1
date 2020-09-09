package dao;

import java.sql.*;
import java.util.*;
import bean.Movie;
import bean.Choice;

/**
 * trainingmov.t_choice　テーブルのDAOクラス
 */
public class ChoiceDAO extends DAO {
    public List<Choice> select(String movie_id) throws Exception {
    	
        List<Choice> list = new ArrayList<>();
        
        // DB接続
        Connection connection = getConnection();

        // SQL作成
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM trainingmov.t_choice Where movie_id = ?");
        statement.setString(1, movie_id);

        // SQL実行
        ResultSet result = statement.executeQuery();

        // データをセット
        while (result.next()) {
        	Choice choice = new Choice();
        	choice.setMovieId(result.getString("movie_id"));
        	choice.setChoiceId(result.getString("choice_id"));
        	choice.setChoiceName(result.getString("choice_name"));
        	choice.setNextMovie(result.getString("next_movie"));
            list.add(choice);
        }

        // 接続解除
        statement.close();
        connection.close();

        return list;
    }
}