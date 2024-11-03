package vn.edu.iuh.fit.session01_re.ex1.beans;

public class Loginbean {
    public boolean login(String us, String pwd){
        if (us.equalsIgnoreCase("chung") && pwd.equals("2003"))
            return true;
        return false;
    }
}
