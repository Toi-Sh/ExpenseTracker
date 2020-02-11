package Expense;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class uesrDataClass {
    private SimpleIntegerProperty id;
    private SimpleStringProperty userName;
    private SimpleStringProperty password;
    private SimpleStringProperty balance;

    public uesrDataClass(int id, String UsrName, String Pwd, String Balance){
        this.id=new SimpleIntegerProperty(id);
        this.userName = new SimpleStringProperty(UsrName);
        this.password = new SimpleStringProperty(Pwd);
        this.balance = new SimpleStringProperty(Balance);;

    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id=new SimpleIntegerProperty(id);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName = new SimpleStringProperty(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    public String getBalance() {
        return balance.get();
    }

    public void setBalance(String balance) {
        this.balance = new SimpleStringProperty(balance);
    }
}
