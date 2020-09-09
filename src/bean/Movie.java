package bean;

/**
 * trainingmov.t_movie　のModelクラス
 */
public class Movie implements java.io.Serializable {
    private String movie_id;
    private String movie_name;
    private String movie_url;
    private int movie_second;

    public void setMovieId(String movie_id) {
        this.movie_id = movie_id;
    }

    public void setMovieName(String movie_name) {
    	this.movie_name = movie_name;
    }
    
    public void setMovieUrl(String movie_url) {
    	this.movie_url = movie_url;
    }
    
    public void setMovieSecond(int movie_second) {
    	this.movie_second = movie_second;
    }
   
    
}