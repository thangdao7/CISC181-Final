package rocket.app.view;

import eNums.eAction;
import exceptions.RateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;

	// TODO - RocketClient.RocketMainController

	// Create private instance variables for:
	// TextBox - txtIncome
	// TextBox - txtExpenses
	// TextBox - txtCreditScore
	// TextBox - txtHouseCost
	// ComboBox - loan term... 15 year or 30 year
	// Labels - various labels for the controls
	// Button - button to calculate the loan payment
	// Label - to show error messages (exception throw, payment exception)

	@FXML
	private TextField txtIncome;

	@FXML
	private TextField txtExpenses;

	@FXML
	private TextField txtCreditScore;

	@FXML
	private TextField txtRate;
	
	@FXML
	private TextField txtHouseCost;

	@FXML
	private TextField txtDownPayment;

	@FXML
	private ComboBox<String> cbTerm;

	@FXML
	private TextField txtMonthlyPayment;

	@FXML
	private Button Payment;

	@FXML
	private Label CreditScore;

	@FXML
	private Label HouseCost;

	@FXML
	private Label DownPayment;

	@FXML
	private Label Rate;

	@FXML
	private Label Income;

	@FXML
	private Label Expenses;

	@FXML
	private Label Term;

	@FXML
	private Label Error;

	@FXML
	private Label MortgagePayment;

	@FXML
	private void initialize() {
		cbTerm.getItems().add("15");
		cbTerm.getItems().add("30");
		// set default cbTerm
		cbTerm.getSelectionModel().select("30");
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	// TODO - RocketClient.RocketMainController
	// Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		
		Object message = null;
		// TODO - RocketClient.RocketMainController
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		// TODO - RocketClient.RocketMainController
		// set the loan request details... rate, term, amount, credit score,
		// downpayment
		// I've created you an instance of lq... execute the setters in lq
		
		// set total cost of house to be HouseCost - DownPayment
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiTerm(Integer.parseInt(cbTerm.getSelectionModel().getSelectedItem().toString()));
		a.setLoanRequest(lq);

		// send lq as a message to RocketHub
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		// TODO - RocketClient.HandleLoanRequestDetails
		// lRequest is an instance of LoanRequest.
		// after it's returned back from the server, the payment (dPayment)
		// should be calculated.
		// Display dPayment on the form, rounded to two decimal places
		double rate = lRequest.getdRate();
		double pmt = Math.abs(lRequest.getdPayment());
		if (rate == 0) {
			txtRate.setText("Check your balance");
			txtMonthlyPayment.setText("No credit score match-");
		} else {
			
			String pmtString = String.format("%1$,.2f", Math.abs(lRequest.getdPayment()));
			MortgagePayment.setText(pmtString);
			
			String rateString = String.format("%1$,.2f", Math.abs(lRequest.getdRate()));
			MortgagePayment.setText(Double.toString(rate));
			
			txtRate.setText(rateString);
			if (lRequest.getIncome() > lRequest.getExpenses()){
				txtMonthlyPayment.setText(pmtString);
			} else {
				txtMonthlyPayment.setText("MeCasaNoEsTuCasa");
			}
		}

	}

}