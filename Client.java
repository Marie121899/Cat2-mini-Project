import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

public class Client extends Application {

    DAOInterface dao = (DAOInterface)Naming.lookup("DAO");

    public Client() throws RemoteException, NotBoundException, MalformedURLException {
    }

    @Override
    public void start(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        Text wlcmTxt1 = new Text("Welcome to the Strathmore Bank");

        Button wlcmBtn1 = new Button("Create Account");
        Button wlcmBtn2 = new Button("Deposit Money");
        Button wlcmBtn3 = new Button("Withdraw");
        Button wlcmBtn4 = new Button("Balance Enquiry");
        Button cancel = new Button("Cancel");



        //creating a grid pane and import relevant classes
        GridPane wlcmGridPane = new GridPane();

        //set up size for the pane
        wlcmGridPane.setMinSize(400, 400);

        //Set padding
        wlcmGridPane.setPadding(new Insets(10, 10, 10, 10));

        //Set the vertical and horizontal gaps between the columns
        wlcmGridPane.setVgap(10);
        wlcmGridPane.setHgap(10);

        //Set Grid alignment
        wlcmGridPane.setAlignment(Pos.TOP_LEFT);

        wlcmGridPane.add(wlcmTxt1, 0, 0);
        wlcmGridPane.add(wlcmBtn1, 0, 2);
        wlcmGridPane.add(wlcmBtn2, 0, 4);
        wlcmGridPane.add(wlcmBtn3, 0, 6);
        wlcmGridPane.add(wlcmBtn4, 0, 8);

        wlcmTxt1.setStyle("-fx-font: normal bold 20px 'serif' ");

        wlcmGridPane.setStyle("-fx-background-color: beige;");
        wlcmBtn1.setStyle("-fx-background-color: skyblue;");
        wlcmBtn2.setStyle("-fx-background-color: skyblue;");
        wlcmBtn3.setStyle("-fx-background-color: skyblue;");
        wlcmBtn4.setStyle("-fx-background-color: skyblue;");
        cancel.setStyle("-fx-background-color: red;");
        cancel.setMinSize(150,20);

        wlcmBtn1.setMinSize(150,20);
        wlcmBtn2.setMinSize(150,20);
        wlcmBtn3.setMinSize(150,20);
        wlcmBtn4.setMinSize(150,20);

        //creating a scene object
        Scene wlcmScene = new Scene(wlcmGridPane);
        //setting title for the stage
        stage.setTitle("Welcome");



        //================= Create Account Scene ============
        Text createTxt1 = new Text("New Customer");
        Text createTxt2 = new Text("First name");
        Text createTxt3 = new Text("Last name");
        Text createTxt4 = new Text("Surname");
        Text createTxt5 = new Text("Phone");
        Text createTxt6 = new Text("Email");

        TextField createTxtFld1 = new TextField();
        TextField createTxtFld2 = new TextField();
        TextField createTxtFld3 = new TextField();
        TextField createTxtFld4 = new TextField();
        TextField createTxtFld5 = new TextField();

        Button createBtn1 = new Button("Create");
        Button createBtn2= new Button("Cancel");

        //step 10: creating a grid pane and import relevant classes
        GridPane createGridPane = new GridPane();

        //step 11: set up size for the pane
        createGridPane.setMinSize(400, 400);

        //step 12: Set padding
        createGridPane.setPadding(new Insets(10, 10, 10, 10));

        //step 13: Set the vertical and horizontal gaps between the columns
        createGridPane.setVgap(10);
        createGridPane.setHgap(10);

        //step 14: Set Grid alignment
        createGridPane.setAlignment(Pos.TOP_LEFT);

        createGridPane.add(createTxt1, 0, 0);
        createGridPane.add(createTxt2, 0, 2);
        createGridPane.add(createTxt3, 0, 4);
        createGridPane.add(createTxt4, 0, 6);
        createGridPane.add(createTxt5, 0, 8);
        createGridPane.add(createTxt6, 0, 10);

        createGridPane.add(createTxtFld1, 1, 2);
        createGridPane.add(createTxtFld2, 1, 4);
        createGridPane.add(createTxtFld3, 1, 6);
        createGridPane.add(createTxtFld4, 1, 8);
        createGridPane.add(createTxtFld5, 1, 10);

        createGridPane.add(createBtn2, 0, 12);
        createGridPane.add(createBtn1, 1, 12);

        createTxt1.setStyle("-fx-font: normal bold 30px 'serif' ");
        createGridPane.setStyle("-fx-background-color: beige;");
        createBtn1.setStyle("-fx-background-color: skyblue;");
        createBtn2.setStyle("-fx-background-color: orange;");


        createBtn1.setMinSize(150,20);
        createBtn2.setMinSize(150,20);

        //creating a scene object
        Scene createScene = new Scene(createGridPane);

        createBtn1.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                Random r = new Random( System.currentTimeMillis() );
                String accNo = "" +(1 + r.nextInt(2)) * 10000 + r.nextInt(10000);
                boolean created;
                created = dao.createAccount(createTxtFld1.getText(), createTxtFld2.getText(), createTxtFld3.getText(), createTxtFld4.getText(), createTxtFld5.getText(), accNo);
                if(created){
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Account Created Successfully");

                }else{
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("There was an error while creating your account. Please Try again");
                }
                alert.show();
                createTxtFld1.clear();
                createTxtFld2.clear();
                createTxtFld3.clear();
                createTxtFld4.clear();
                createTxtFld5.clear();


            }
        });

        createBtn2.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                //setting title for the stage
                stage.setTitle("Welcome");

                //adding scene to the stage
                stage.setScene(wlcmScene);

                //displaying he contents of the stage
                stage.show();
            }
        });



        //==================== Deposit Scene =====================
        Text depHeader = new Text("Deposit Customer's Account");
        Text depAccNo = new Text("Account Number");
        Text depAmount = new Text("Amount");

        TextField depAccTxtField = new TextField();
        TextField depAmntTxtField = new TextField();

        Button depBtn1 = new Button("Deposit");
        Button depBtn2 = new Button("Cancel");

        //creating a grid pane and import relevant classes
        GridPane depGridPane = new GridPane();

        //set up size for the pane
        depGridPane.setMinSize(300, 300);

        //Set padding
        depGridPane.setPadding(new Insets(5, 5, 5, 5));

        //Set the vertical and horizontal gaps between the columns
        depGridPane.setVgap(5);
        depGridPane.setHgap(5);

        //Set Grid alignment
        depGridPane.setAlignment(Pos.TOP_LEFT);

        depGridPane.add(depHeader, 0, 0);
        depGridPane.add(depAccNo, 0, 2);
        depGridPane.add(depAmount, 0, 4);

        depGridPane.add(depAccTxtField, 1, 2);
        depGridPane.add(depAmntTxtField, 1, 4);

        depGridPane.add(depBtn2, 0, 6);
        depGridPane.add(depBtn1, 1, 6);

        depHeader.setStyle("-fx-font: normal bold 20px 'serif' ");
        depGridPane.setStyle("-fx-background-color: beige;");
        depBtn1.setStyle("-fx-background-color: skyblue;");
        depBtn2.setStyle("-fx-background-color: orange;");

        depBtn1.setMinSize(150,20);
        depBtn2.setMinSize(150,20);

        //creating a scene object
        Scene depScene = new Scene(depGridPane);

        depBtn1.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                double balance;
                balance = dao.deposit(depAccTxtField.getText(), Double.parseDouble(depAmntTxtField.getText()));
                if(balance > 0){
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Account Deposited Successfully. New Balance: Kshs "+ balance);
                }else{
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("There was an error while depositing your account. Please Try again");
                }
                depAccTxtField.clear();
                depAmntTxtField.clear();
                alert.show();

            }
        });

        depBtn2.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                //setting title for the stage
                stage.setTitle("Welcome");

                //adding scene to the stage
                stage.setScene(wlcmScene);

                //displaying he contents of the stage
                stage.show();
            }
        });


        //==================Withdraw Scene ==========================
        Text withTxt1 = new Text("Widthraw Customer's Account");
        Text withTxt2 = new Text("Account Number");
        Text withTxt3 = new Text("Amount");

        TextField withTxtFld1 = new TextField();
        TextField withTxtFld2 = new TextField();

        Button withBtn1 = new Button("Widthraw");
        Button withBtn2 = new Button("Cancel");

        //creating a grid pane and import relevant classes
        GridPane withGridPane = new GridPane();

        //set up size for the pane
        withGridPane.setMinSize(300, 300);

        //Set padding
        withGridPane.setPadding(new Insets(5, 5, 5, 5));

        //Set the vertical and horizontal gaps between the columns
        withGridPane.setVgap(5);
        withGridPane.setHgap(5);

        //Set Grid alignment
        withGridPane.setAlignment(Pos.TOP_LEFT);

        withGridPane.add(withTxt1, 0, 0);
        withGridPane.add(withTxt2, 0, 2);
        withGridPane.add(withTxt3, 0, 4);

        withGridPane.add(withTxtFld1, 1, 2);
        withGridPane.add(withTxtFld2, 1, 4);

        withGridPane.add(withBtn2, 0, 6);
        withGridPane.add(withBtn1, 1, 6);

        withTxt1.setStyle("-fx-font: normal bold 20px 'serif' ");
        withGridPane.setStyle("-fx-background-color: beige;");
        withBtn1.setStyle("-fx-background-color: skyblue;");
        withBtn2.setStyle("-fx-background-color: orange;");

        withBtn1.setMinSize(150,20);

        //creating a scene object
        Scene withScene = new Scene(withGridPane);
        withBtn1.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                double balance;
                balance = dao.deposit(withTxtFld1.getText(), Double.parseDouble(withTxtFld2.getText()));
                if(balance > 0){
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Account Withdraw Successful. New Balance: Kshs " + balance);
                }else{
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("There was an error while withdrawing your account. Please Try again");
                }
                withTxtFld1.clear();
                withTxtFld2.clear();
                alert.show();

            }
        });

        withBtn2.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                //setting title for the stage
                stage.setTitle("Welcome");

                //adding scene to the stage
                stage.setScene(wlcmScene);

                //displaying he contents of the stage
                stage.show();
            }
        });


        //=======================Balance Enquiry =====================
        Text balTxt1 = new Text("Customer's Balance Enquiry");
        Text balTxt2 = new Text("Account number");
        TextField balTxtFld1 = new TextField();

        Button balBtn = new Button("Enquire");
        Button balBtn1 = new Button("Cancel");

        GridPane balGridPane = new GridPane();

        //set up size for the pane
        balGridPane.setMinSize(300, 300);

        //Set padding
        balGridPane.setPadding(new Insets(5, 5, 5, 5));

        //Set the vertical and horizontal gaps between the columns
        balGridPane.setVgap(5);
        balGridPane.setHgap(5);

        //Set Grid alignment
        balGridPane.setAlignment(Pos.TOP_LEFT);

        balGridPane.add(balTxt1, 0, 0);
        balGridPane.add(balTxt2, 0, 2);

        balGridPane.add(balTxtFld1, 1, 2);

        balGridPane.add(balBtn1, 0, 4);
        balGridPane.add(balBtn, 1, 4);

        balTxt1.setStyle("-fx-font: normal bold 20px 'serif' ");
        balGridPane.setStyle("-fx-background-color: beige;");
        balBtn.setStyle("-fx-background-color: skyblue;");
        balBtn1.setStyle("-fx-background-color: orange;");

        balBtn.setMinSize(150,20);

        //creating a scene object
        Scene balScene = new Scene(balGridPane);

        balBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                double balance;
                balance = dao.getBalance(balTxtFld1.getText());
                if(balance > 0){
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Balance Enquiry Successful. Available Balance: Kshs "+ balance);
                }else{
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("There was an error while checking  your balance. Please Try again");
                }
                balTxtFld1.clear();
                alert.show();

            }
        });

        balBtn1.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                //setting title for the stage
                stage.setTitle("Welcome");

                //adding scene to the stage
                stage.setScene(wlcmScene);

                //displaying he contents of the stage
                stage.show();
            }
        });


        //adding scene to the stage
        stage.setScene(wlcmScene);

        //displaying he contents of the stage
        stage.show();

        wlcmBtn1.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                stage.setTitle("Create Account");

                //adding scene to the stage
                stage.setScene(createScene);

            }
        });

        wlcmBtn2.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                //setting title for the stage
                stage.setTitle("Deposit Money");

                //adding scene to the stage
                stage.setScene(depScene);

            }
        });

        wlcmBtn3.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                //setting title for the stage
                stage.setTitle("Widthraw Money");

                //adding scene to the stage
                stage.setScene(withScene);

            }
        });

        wlcmBtn4.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                //setting title for the stage
                stage.setTitle("Balance Enquiry");

                //adding scene to the stage
                stage.setScene(balScene);
            }
        });

        cancel.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                //setting title for the stage
                stage.setTitle("Welcome");

                //adding scene to the stage
                stage.setScene(wlcmScene);

                //displaying he contents of the stage
                stage.show();
            }
        });
    }

    public static void main(String[] args) throws Exception
    { launch(args); }
}
