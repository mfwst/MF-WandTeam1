package bean;

/**
 * trainingmov.t_user　のModelクラス
 */
public class User implements java.io.Serializable {
    private String user_id;
    private String user_name;

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public void setUserName(String user_name) {
    	this.user_name = user_name;
    }

}