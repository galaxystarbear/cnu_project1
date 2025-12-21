package project.reservation.domain;

public class Member {

    private long id;
    private String name;
    private String encodedPass;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEncodedPass() {
        return encodedPass;
    }
    
    public void setEncodedPass(String encodedPass) {
        this.encodedPass = encodedPass;
    }
}
