package bean;

/**
 * trainingmov.t_course　のModelクラス
 */
public class Course implements java.io.Serializable {
    private String course_id;
    private String course_name;

    public void setCourseId(String course_id) {
        this.course_id = course_id;
    }

    public void setCourseName(String course_name) {
    	this.course_name = course_name;
    }

}