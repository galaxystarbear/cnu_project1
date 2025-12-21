package project.reservation.domain;

public class NewUserDto {
    private String userid;
    private String pass;
    private String newPass;
    
    public String getUserid() {
        return userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String getNewPass() {
        return newPass;
    }
    
    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
