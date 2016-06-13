package org.ymdroid.rnb;

/**
 * Created by kimminyoung on 2016-05-14.
 */
public class UserInfo {

    private volatile static  UserInfo UserInstance;

    public String user_id;
    private String password;
    public String name;
    //public String birth;

    private UserInfo(){}

    public static UserInfo getInstance(){
        if(UserInstance==null){
            synchronized (UserInfo.class){
                if(UserInstance==null)
                    UserInstance = new UserInfo();
            }
        }
        return UserInstance;
    }

    public void setUserData(String user_id, String password, String name){
        this.user_id = user_id;
        this.password = password;
        this.name = name;
        // this.birth = birth;
    }

    public  void setUser_id(String user_id){
        this.user_id=user_id;
    }
    public  void setPassword(String password){
        this.password=password;
    }
    public  void setName(String name){
        this.name=name;
    }
    /*
    public  void setBirth(String birth){
        this.birth=birth;
    }
    */
    public String getUserPassword(){ return password; }

}
