package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.User;

/**
 * trainingmov.t_user　テーブルのDAOクラス
 */
public class UserDAO extends DAO {
    public List<User> selectAll() throws Exception {

        List<User> list = new ArrayList<>();

        // DB接続
        Connection connection = getConnection();

        // SQL作成
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM trainingmov.t_user");

        // SQL実行
        ResultSet result = statement.executeQuery();

        // データをセット
        while (result.next()) {
            User user = new User();
            user.setUserId(result.getString("user_id"));
            user.setUserName(result.getString("user_name"));
            list.add(user);
        }

        // 接続解除
        statement.close();
        connection.close();

        return list;
    }

    public List<User> select(String user_id) throws Exception {

        List<User> list = new ArrayList<>();

        // DB接続
        Connection connection = getConnection();

        // SQL作成
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM trainingmov.t_user Where user_id = ?");
        statement.setString(1, user_id);

        // SQL実行
        ResultSet result = statement.executeQuery();

        // データをセット
        while (result.next()) {
            User user = new User();
            user.setUserId(result.getString("user_id"));
            user.setUserName(result.getString("user_name"));
            list.add(user);
        }

        // 接続解除
        statement.close();
        connection.close();

        return list;
    }
}