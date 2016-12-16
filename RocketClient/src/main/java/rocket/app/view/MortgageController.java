package rocket.app.view;

import java.text.DecimalFormat;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;

	@FXML
	private TextField txtIncome;	
	@FXML
	private TextField txtExpenses;	
	@FXML
	private TextField txtCreditScore;	
	@FXML
	private TextField txtHouseCost;
	
	//combo box with items added??? third try
    @FXML
    private ComboBox cmbTerm;
	@FXML
	private void initialize(){
		cmbTerm.getItems().removeAll(cmbTerm.getItems());
		cmbTerm.getItems().addAll("15","30");
}
    
	@FXML
	private Button btnMortgagePayment;

	@FXML
	private Label lblIncome;
	@FXML
	private Label lblExpenses;
	@FXML
	private Label lblCreditScore;
	@FXML
	private Label lblHouseCost;
	@FXML
	private Label lblMortgagePayment;

	//	RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		// RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		lblMortgagePayment.setText("");
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.setiTerm(Integer.parseInt((String)cmbTerm.getSelectionModel().getSelectedItem()));
		//	RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq

		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		double payment;
		double PITI28;
		double PITI36;
		
		payment = lRequest.getdPayment();
		
		PITI28 = lRequest.getIncome() / 12 * 0.28;
		PITI36 = (lRequest.getIncome()/12 * 0.36) - lRequest.getExpenses();
		
		// PITI calculations (from above) returns lowest as PITI
		double PITI = (PITI28<PITI36 ? PITI28 : PITI36);
		
		DecimalFormat df = new DecimalFormat("###.##");
		
		if (PITI>payment){
			lblMortgagePayment.setText("Monthly Mortgage Payment: "+ df.format(payment));
		} else{
			lblMortgagePayment.setText("House Cost too High"); }
		//	RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
	}
}
