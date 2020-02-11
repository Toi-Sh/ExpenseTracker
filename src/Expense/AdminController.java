package Expense;

import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    static ResultSet rs;
    ObservableList<uesrDataClass> obList = FXCollections.observableArrayList();
    // Admin Page
    //btn
    @FXML
    private Button btnNext; @FXML private  Button btnPrev;
    @FXML private Button btnDelete; @FXML private  Button btnUpdate;
    @FXML private Button btnDisplay;
    //tfs
    @FXML private TextField tfAUserName; @FXML private TextField tfAPassword;
    @FXML private TextField tfID; @FXML private TextField tfABalance;
    @FXML private TextField tfAFullName;
    //txt
    @FXML private Text txtAUserName; @FXML private Text txtAPassword;
    @FXML private Text txtID; @FXML private Text txtABalance;
    // table columns
    public TableView<uesrDataClass> tblView;
    public TableColumn<uesrDataClass, Integer> colID;
    public TableColumn<uesrDataClass,String> colUserName;
    public TableColumn<uesrDataClass,String> colPassword;
    public TableColumn<uesrDataClass,String> colBalance;

    Stage stage = new Stage();
    Parent root ;

    private java.sql.PreparedStatement pst;
    //end Admin Page

    //create User
    //textField
    @FXML private TextField tfCUFullName; @FXML private TextField tfCUuserName;
    @FXML private TextField tfCUPassword; @FXML private TextField tfCUBalance;
    //Buttons
    @FXML private Button btnHome;
    @FXML private Button btnSubmit;
    //end Create User



//    ConnectionClass connectionClass = new ConnectionClass();
//    Connection connection = connectionClass.getConnection();
    //Create User Controls
    public void SubmitBtnClicked(ActionEvent actionEvent) throws SQLException{
        if (tfCUBalance.getText().isEmpty() || tfCUFullName.getText().isEmpty() || tfCUPassword.getText().isEmpty() || tfCUuserName.getText().isEmpty()){
            //start if
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill out the form");
            alert.show();
        } //end if
        else{ //start else
            ResultSet rs= null;
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            //try{
                //PreparedStatement st =
            //}

        }
    }


    //end Create User
//Admin Page Controls
    public void updateBtnClicked(ActionEvent actionEvent) throws SQLException{
        if(tfABalance.getText().isEmpty() ||tfAUserName.getText().isEmpty()){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill out the form");
            alert.show();
        } //end if
        else{
            //do something
//            delete(Integer.parseInt(rgisterNberup_deltxtF.getText()));
            System.out.println("In Initialize");
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            try{
                PreparedStatement st = connection.prepareStatement("update UserTable set" +
                        " FullName=?,userName=?,password=?,balance=?" +
                        "where userID=?");
                //int value = Integer.parseInt(rgistertxtField.getText());
                st.setString(5, String.valueOf(tfID.getText()));
                st.setString(1, String.valueOf(tfAFullName.getText()));
                st.setString(2, String.valueOf(tfAUserName.getText()));
                st.setString(4, String.valueOf(tfABalance.getText()));
                st.setString(3, String.valueOf(tfAPassword.getText()));
                int row= st.executeUpdate();

                tfAPassword.clear();
                tfAUserName.clear();
                tfABalance.clear();
                tfAFullName.clear();
                tfID.clear();
                stage = (Stage) btnDelete.getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adminMain.fxml")));
                stage.setScene(scene);
                stage.show();


            } //end try
            catch (SQLException | IOException e){
                e.printStackTrace();
            } //end catch
        } //end else
    } //end updateBtnHandler

    public void deleteBtnClicked(ActionEvent actionEvent) throws SQLException{
        if(tfABalance.getText().isEmpty() ||tfAUserName.getText().isEmpty()){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill out the form");
            alert.show();
        }else{
            //do something
//            delete(Integer.parseInt(rgisterNberup_deltxtF.getText()));
            System.out.println("In Initialize");
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            try{
                PreparedStatement st = connection.prepareStatement("delete from UserTable where userID=?");
                //int value = Integer.parseInt(rgistertxtField.getText());
                st.setString(1, String.valueOf(tfID.getText()));
                int row= st.executeUpdate();

                tfAPassword.clear();
                tfAUserName.clear();
                tfABalance.clear();
                tfAFullName.clear();
                tfID.clear();
                stage = (Stage) btnDelete.getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adminMain.fxml")));
                stage.setScene(scene);
                stage.show();


            }catch (SQLException | IOException e){
                e.printStackTrace();
            }
        }
    }
    public void displayBtnClicked(ActionEvent actionEvent) throws SQLException{
        ResultSet rs = null;
        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        int has=0;
        try{
            PreparedStatement st = connection.prepareStatement("Select * from UserTable where userID=?");
            //int value = Integer.parseInt(rgistertxtField.getText());
            st.setString(1, String.valueOf(tfID.getText()));
            rs= st.executeQuery();
            while (rs.next()){
                tfAUserName.setText(rs.getString("userName"));
                tfAFullName.setText(rs.getString("FullName"));
                tfAPassword.setText(rs.getString("password"));
                tfABalance.appendText(rs.getString("balance"));
                has=1;
            }
            if (has==0){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Doesn't Exist");
                alert.show();
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void prevUserClicked(ActionEvent actionEvent)throws SQLException {
        if (!rs.isFirst()){
            rs.previous();
        }
        txtID.setText(rs.getString(1));
        txtAUserName.setText(rs.getString(2));
        txtAPassword.setText(rs.getString(3));
        txtABalance.setText(rs.getString(4));
    }
    public void nextUserClicked (ActionEvent actionEvent) throws SQLException{
        if (!rs.isLast()){
            rs.next();
        }
        txtID.setText(rs.getString(1));
        txtAUserName.setText(rs.getString(2));
        txtAPassword.setText(rs.getString(3));
        txtABalance.setText(rs.getString(4));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try{
            rs = connection.createStatement().executeQuery("SELECT userID, userName, password, balance FROM UserTable;");
            rs.first();

        }catch(SQLException e){
            e.printStackTrace();
        }
        try (
                ResultSet rs =
                        connection.createStatement().executeQuery("SELECT userID, userName, password, balance FROM UserTable;")){
            while(rs.next()){
                obList.add(new uesrDataClass(rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("balance")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        colID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        //tblView.setItems(obsList);
        tblView.setItems(obList);

    }







}
