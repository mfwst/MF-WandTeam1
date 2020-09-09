package bean;

/**
 * trainingmov.t_history　のModelクラス
 */
public class History implements java.io.Serializable {
    private String user_id;
    private String course_id;
    private String movie_id;

    public String getUserId(){
    	return this.user_id;
    }
    
    public String getCourseId(){
    	return this.course_id;
    }
    
    public String getMovieId(){
    	return this.movie_id;
    }
    
    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public void setCourseId(String course_id) {
    	this.course_id = course_id;
    }
    
    public void setMovieId(String movie_id) {
    	this.movie_id = movie_id;
    }
    
}