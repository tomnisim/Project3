package resources;


public abstract class User {
    protected String userName;
    protected String password;
    protected boolean status;

    public User(String userName,String password){
        this.status=false;
        this.password=password;
        this.userName=userName;
    }

    public void login(){
        this.status=true;
    }

    public void logout(){
        this.status=false;
    }

    public String getPassword(){
        return password;
    }

    public boolean getStatus() {
        return status;
    }

    public String getUserName() { return userName;
    }

    public abstract void unregister(int course);
}
