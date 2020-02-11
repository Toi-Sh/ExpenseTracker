package Expense;

import Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUserPageContoller {


    //  Create User Page
    @FXML
    private Button btnHome; @FXML private  Button btnSubmit;
    @FXML private TextField tfCUserName; @FXML private TextField tfCPassword;
    @FXML private TextField tfCFullName; @FXML private TextField tfCInitialBalance;
    //end Create user Page
    Stage stage = new Stage();
    Parent root ;
    public void SubmitBtnCLicked(ActionEvent actionEvent){
        if (tfCFullName.getText().isEmpty()|| tfCPassword.getText().isEmpty()|| tfCInitialBalance.getText().isEmpty() ||
        tfCUserName.getText().isEmpty()){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill out the form");
            alert.show();
        }
        else{
            System.out.println("In Initialize");
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            try{
                PreparedStatement st = connection.prepareStatement("INSERT INTO UserTable (FullName, userName, password,balance)"+" VALUES (?,?,?,?)" );
                //int value = Integer.parseInt(rgistertxtField.getText());

                st.setString(1, String.valueOf(tfCFullName.getText()));
                st.setString(2, String.valueOf(tfCUserName.getText()));
                st.setString(3, String.valueOf(tfCPassword.getText()));
                st.setString(4, String.valueOf(tfCInitialBalance.getText()));
                int row= st.executeUpdate();

                tfCPassword.clear();
                tfCUserName.clear();
                tfCInitialBalance.clear();
                tfCFullName.clear();
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Added!");
                alert.show();

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void HomeBtnclicked(ActionEvent actionEvent) throws IOException {
        stage = (Stage) btnHome.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}
