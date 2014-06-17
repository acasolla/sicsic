package it.abc.sicsic.security;

public class User {
    public User() {
        super();
    }
    
    private String username = null;
    private String psw = null;


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPsw() {
        return psw;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nusername=" + getUsername());
        sb.append("\npassword=" + getPsw());
        return sb.toString();
        
    }
}