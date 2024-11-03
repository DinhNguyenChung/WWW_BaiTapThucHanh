package vn.edu.iuh.fit.demo1.entity;

public class Person {
    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;

    public Person(String FirstName, String LastName, String UserName, String Password) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.UserName = UserName;
        this.Password = Password;

    }
    public Person()   {}
    //Contructer
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;

    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
    //ToString


    @Override
    public String toString() {

        return "Person [FirstName=" + FirstName + ", LastName=" + LastName + ", UserName=" + UserName +" ,PassWord" + Password;
    }
}
