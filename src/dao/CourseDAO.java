package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Course;

/**
 * trainingmov.t_course　テーブルのDAOクラス
 */
public class CourseDAO extends DAO {
    public List<Course> selectAll() throws Exception {

        List<Course> list = new ArrayList<>();

        // DB接続
        Connection connection = getConnection();

        // SQL作成
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM trainingmov.t_course");

        // SQL実行
        ResultSet result = statement.executeQuery();

        // データをセット
        while (result.next()) {
            Course course = new Course();
            course.setCourseId(result.getString("course_id"));
            course.setCourseName(result.getString("course_name"));
            list.add(course);
        }

        // 接続解除
        statement.close();
        connection.close();

        return list;
    }

    public List<Course> select(String course_id) throws Exception {

        List<Course> list = new ArrayList<>();

        // DB接続
        Connection connection = getConnection();

        // SQL作成
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM trainingmov.t_course Where course_id = ?");
        statement.setString(1, course_id);

        // SQL実行
        ResultSet result = statement.executeQuery();

        // データをセット
        while (result.next()) {
            Course course = new Course();
            course.setCourseId(result.getString("course_id"));
            course.setCourseName(result.getString("course_name"));
            list.add(course);
        }

        // 接続解除
        statement.close();
        connection.close();

        return list;
    }
}