package com.example.tipcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TipCalculatorController {
    @FXML
    private TextField billAmountField;

    @FXML
    private TextField tipPercentageField;

    @FXML
    private Label tipAmountLabel;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private void calculateTip() {
        errorMessageLabel.setText("");

        try {
            BigDecimal billAmount = new BigDecimal(billAmountField.getText().trim());
            BigDecimal tipPercentage = new BigDecimal(tipPercentageField.getText().trim());

            if (billAmount.signum() < 0 || tipPercentage.signum() < 0) {
                tipAmountLabel.setText("");
                errorMessageLabel.setText("Error: Amounts must be zero or greater.");
                return;
            }

            BigDecimal tipAmount = billAmount
                    .multiply(tipPercentage)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            tipAmountLabel.setText("Tip Amount: $" + tipAmount.toPlainString());
        } catch (NumberFormatException exception) {
            tipAmountLabel.setText("");
            errorMessageLabel.setText("Error: Invalid input. Please enter numbers.");
        }
    }

    @FXML
    private void reset() {
        billAmountField.clear();
        tipPercentageField.clear();
        tipAmountLabel.setText("");
        errorMessageLabel.setText("");
    }
}
