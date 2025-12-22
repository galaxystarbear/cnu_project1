package project.reservation.domain;

public class ReservationRequest {
    
    private String time;
    private String name;
    
    public ReservationRequest() {
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}