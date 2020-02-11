package Expense;

import Connectivity.ConnectionClass;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller  implements Initializable {

    static ResultSet rs;
    private java.sql.PreparedStatement pst;

    //LogIn Page

    @FXML private RadioButton rdoUser; @FXML private RadioButton rdoAdmin;
    @FXML private Button btnSignIn; @FXML private Button btnCreate;
    @FXML private TextField tfUserName; @FXML private TextField tfPassword;

    //end LogIn Page
    //ConnectionClass connectionClass = new ConnectionClass();
    //Connection connection = connectionClass.getConnection();


    public void btnLogInActionHandler(Event e) throws IOException, SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Stage stage = new Stage();
        Parent root ;
        if(e.getSource()== btnSignIn){
            System.out.println("In Initialize");

            if (rdoAdmin.isSelected()){
                //do something
                String q1="Select * from AdminTable where userName=? and password=?";
                try{
                pst = connection.prepareStatement(q1);
                pst.setString(1,tfUserName.getText());
                pst.setString(2,tfPassword.getText());
                ResultSet rs = pst.executeQuery();

                int count= 0;
                while (rs.next()){
                    count=count+1;
                }
                if(count==1){
                    System.out.println("LogIn Successful");
                    stage = (Stage) btnCreate.getScene().getWindow();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adminMain.fxml")));
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Invalid Credentials!");
                    alert.setContentText("No Account Matched!");
                    alert.showAndWait();
                    count=0;
                }
                }
                catch(SQLException e1){
                    e1.printStackTrace();
                }
                finally {
                    try{connection.close();
                    System.out.println("Hello");}
                    catch(SQLException e1){
                        e1.printStackTrace();
                    }
                }
            }
            else if(rdoUser.isSelected()){
                String q1="Select * from UserTable where userName=? and password=?";
                try{
                    pst = connection.prepareStatement(q1);
                    pst.setString(1,tfUserName.getText());
                    pst.setString(2,tfPassword.getText());
                    ResultSet rs = pst.executeQuery();

                    int count= 0;
                    while (rs.next()){
                        count=count+1;
                    }
                    if(count==1){
                        System.out.println("LogIn Successful");

                        String usrName= tfUserName.getText();
                        FXMLLoader loader= new FXMLLoader();
                        loader.setLocation(getClass().getResource("userMain.fxml"));
                        Parent detailScreen = loader.load();
                        Scene detailScene= new Scene(detailScreen);

                        UserMain userMain = loader.getController();
                        userMain.populateData(usrName);
                        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                        stage.setScene(detailScene);
                        stage.show();





//                        stage = (Stage) btnCreate.getScene().getWindow();
//                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("userMain.fxml")));
//                        String usrName= tfUserName.getText();
//
//                        UserMain userMain=loader.get
//                        stage.setScene(scene);
//                        stage.show();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("Invalid Credentials!");
                        alert.setContentText("No Account Matched!");
                        alert.showAndWait();
                        count=0;
                    }
                }
                catch(SQLException e1){
                    e1.printStackTrace();
                }
                finally {
                    try{connection.close();
                        System.out.println("Hello");}
                    catch(SQLException e1){
                        e1.printStackTrace();
                    }
                }

            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("User Type Not Selected!");
                alert.setContentText("Specify User or Admin!");
                alert.showAndWait();
            }
        }
        else{
            stage = (Stage) btnCreate.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("CreateUser.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    tfUserName.requestFocus();
    btnSignIn.setDefaultButton(true);
    }
}
