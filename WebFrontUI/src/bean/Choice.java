package bean;

/**
 * trainingmov.t_choice　のModelクラス
 */
public class Choice implements java.io.Serializable {
    private String movie_id;
    private String choice_id;
    private String choice_name;
    private String next_movie;
    
    public String getMovieId(){
    	return this.movie_id;
    }

    public String getChoiceId(){
    	return this.choice_id;
    }
    
    public String getChoiceName(){
    	return this.choice_name;
    }
    
    public String getNextMovie(){
    	return this.next_movie;
    }
    
    public void setMovieId(String movie_id){
    	this.movie_id = movie_id;
    }

    public void setChoiceId(String choice_id){
    	this.choice_id = choice_id;
    }
    
    public void setChoiceName(String choice_name){
    	this.choice_name = choice_name;
    }
    
    public void setNextMovie(String next_movie){
    	this.next_movie = next_movie;
    }
    

    
}