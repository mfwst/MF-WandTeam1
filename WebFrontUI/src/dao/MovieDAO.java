package dao;

import java.sql.*;
import java.util.*;
import bean.Movie;

/**
 * trainingmov.t_movie　テーブルのDAOクラス
 */
public class MovieDAO extends DAO {
    public List<Movie> selectAll() throws Exception {

        List<Movie> list = new ArrayList<>();

        // DB接続
        Connection connection = getConnection();

        // SQL作成
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM trainingmov.t_movie");

        // SQL実行
        ResultSet result = statement.executeQuery();

        // データをセット
        while (result.next()) {
        	int movieSecond = Integer.parseInt(result.getString("movie_second"));
        	
            Movie movie = new Movie();
            movie.setMovieId(result.getString("movie_id"));
            movie.setMovieName(result.getString("movie_name"));
            movie.setMovieUrl(result.getString("movie_url"));
            movie.setMovieSecond(movieSecond);
            list.add(movie);
        }

        // 接続解除
        statement.close();
        connection.close();

        return list;
    }
    
    public List<Movie> select(String movie_id) throws Exception {

        List<Movie> list = new ArrayList<>();

        // DB接続
        Connection connection = getConnection();

        // SQL作成
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM trainingmov.t_movie Where movie_id = ?");
        statement.setString(1, movie_id);

        // SQL実行
        ResultSet result = statement.executeQuery();

        // データをセット
        while (result.next()) {
        	int movieSecond = Integer.parseInt(result.getString("movie_second"));
        	
            Movie movie = new Movie();
            movie.setMovieId(result.getString("movie_id"));
            movie.setMovieName(result.getString("movie_name"));
            movie.setMovieUrl(result.getString("movie_url"));
            movie.setMovieSecond(movieSecond);
            list.add(movie);
        }

        // 接続解除
        statement.close();
        connection.close();

        return list;
    }
}