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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class UserMain implements Initializable {
    // Start Spending controls
    @FXML private Button btnSubmitSpend;
    @FXML private Button btnClear;
    @FXML private ChoiceBox chTransType;
    //end Spending control
    @FXML private Text txtBal;
    @FXML private Text txtBal2;
    // Start Deposit Controls
    @FXML private Button btnSubmitDeposit;
    @FXML private Button btnCLear2;
    @FXML private ChoiceBox getChTransType2;
    @FXML private Text txtuName;
    // end Deposit controls

    @FXML private TextField tfDeposit;
    @FXML private TextField tfSpent;

    @FXML private PieChart BalPie;

    Stage stage = new Stage();
    Parent root ;


    public String userName;
    public Double intCurBal;
    public void SubmitBtnClicked(ActionEvent actionEvent) throws SQLException{
        ResultSet rs = null;
        Double newI = null; String newBalance;
        Double spentAmt = null;

        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        if (actionEvent.getSource()== btnSubmitDeposit) {

            spentAmt = Double.parseDouble(tfDeposit.getText());
            newI = intCurBal + Double.parseDouble(tfDeposit.getText());
            newBalance = Double.toString(newI);


            PreparedStatement st = connection.prepareStatement("update UserTable set" +
                    " balance=?" +
                    "where userName=?");


            st.setString(1, String.valueOf(String.valueOf(newBalance)));
            st.setString(2, String.valueOf(String.valueOf(userName)));

            int row = st.executeUpdate();
            txtBal.setText("$ " + (newBalance));
            txtBal2.setText("$ " + ((newBalance)));
            tfDeposit.clear();

        }
        if (actionEvent.getSource()==btnSubmitSpend){
            spentAmt=Double.parseDouble(tfSpent.getText());
            newI=intCurBal-Double.parseDouble(tfSpent.getText());
            newBalance= Double.toString(newI);

            System.out.println("Thoi");

                PreparedStatement sts = connection.prepareStatement("update UserTable set" +
                        " balance=?" +
                        "where userName=?");


                sts.setString(1, String.valueOf(String.valueOf(newBalance)));
                sts.setString(2, String.valueOf(String.valueOf(userName)));

                int rows= sts.executeUpdate();
                txtBal.setText("$ "+ (newBalance));
                txtBal2.setText("$ " +((newBalance)));
                tfDeposit.clear();
        }
        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
                new PieChart.Data("Balance",newI),
                new PieChart.Data("Expense",spentAmt)
        );
        BalPie.setData(piechartData);


    }
    public void ClearBtnClicked(ActionEvent actionEvent){
        tfDeposit.clear();
        tfSpent.clear();

    }

    public void populateData(String strUsr){
    userName= strUsr;
        ResultSet rs = null;
        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try{
            PreparedStatement st = connection.prepareStatement("select balance from UserTable where userName=?");
            //int value = Integer.parseInt(rgistertxtField.getText());
            st.setString(1, String.valueOf(userName));
            rs= st.executeQuery();
            while (rs.next()){
                txtBal.setText("$ "+ rs.getString("balance"));
                txtBal2.setText("$ " +rs.getString("balance"));
                intCurBal= Double.parseDouble(rs.getString("balance"));
            }
//            ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
//                    new PieChart.Data("Balance",intCurBal)
//            );
//            BalPie.setData(piechartData);

        }catch (SQLException e){
            e.printStackTrace();
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfSpent.requestFocus();
//        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
//                new PieChart.Data("Balance",5000)
//                );
//        BalPie.setData(piechartData);
     //   ResultSet rs = null;
//        System.out.println("In Initialize");
//        ConnectionClass connectionClass = new ConnectionClass();
//        Connection connection = connectionClass.getConnection();
//        try{
//            PreparedStatement st = connection.prepareStatement("select balance from UserTable where userName=?");
//            //int value = Integer.parseInt(rgistertxtField.getText());
//            st.setString(1, String.valueOf(userName));
//            rs= st.executeQuery();
//            while (rs.next()){
//                txtBal.setText(rs.getString("balance"));
//
//
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

    }
}
