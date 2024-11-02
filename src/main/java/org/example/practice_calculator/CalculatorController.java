package org.example.practice_calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField textDisplay;
    private int decimalClick = 0;

    private double firstNumber = 0.0; // Store the first number
    private String operation = ""; // Store the current operation
    private boolean isSecondNumber = false; // Track when entering the second number
    private boolean resultDisplayed = false; // Track if the result has been displayed

    @FXML
    void handlerActionOperation(ActionEvent event) {
        String generalOperation = ((Button) event.getSource()).getText();

        // Check if the display is empty and user pressed an operator
        if (textDisplay.getText().isEmpty() || textDisplay.getText().equals("Please enter a number first")) {
            textDisplay.setText("Please enter a number first");
            return;
        }

        if (resultDisplayed) {
            textDisplay.setText(""); // Clear display if new calculation starts
            resultDisplayed = false; // Reset the flag for new input
        }

        switch (generalOperation) {
            case "AC": // Clear button
                textDisplay.setText("");
                decimalClick = 0;
                firstNumber = 0.0;
                operation = "";
                isSecondNumber = false;
                resultDisplayed = false;
                break;

            case "+/-": // Plus-minus toggle
                double plusMinus = Double.parseDouble(textDisplay.getText());
                plusMinus = -plusMinus;
                textDisplay.setText(String.valueOf(plusMinus));
                break;

            case "+": // Arithmetic operations
            case "-":
            case "*":
            case "/":
                // Store the first number and the selected operation
                firstNumber = Double.parseDouble(textDisplay.getText());
                operation = generalOperation;
                // Display the operation in the text display
                textDisplay.setText(firstNumber + " " + operation + " ");
                decimalClick = 0; // Reset decimal flag for the new number
                isSecondNumber = true; // Enable entering the second number
                break;
        }
    }

    @FXML
    void handlerEqualeAction(ActionEvent event) {
        if (operation.isEmpty() || !isSecondNumber) {
            return; // If no operation selected or second number not entered, return
        }

        double secondNumber = Double.parseDouble(textDisplay.getText().replace(firstNumber + " " + operation + " ", ""));
        double result = 0.0;

        switch (operation) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    textDisplay.setText("Error"); // Handle division by zero
                    operation = ""; // Clear operation for next input
                    return;
                }
                break;
        }

        // Display the full calculation and result
        textDisplay.setText(firstNumber + " " + operation + " " + secondNumber + " = " + result);
        decimalClick = 0; // Reset decimal flag after calculation
        operation = ""; // Clear operation for next input
        isSecondNumber = false; // Reset for new calculation
        resultDisplayed = true; // Set the flag to indicate result is shown
    }

    @FXML
    void handlerDecimalAction(ActionEvent event) {
        if (decimalClick == 0) {
            if (resultDisplayed || textDisplay.getText().equals("Please enter a number first")) {
                textDisplay.setText(""); // Clear display if new calculation starts
                resultDisplayed = false; // Reset the flag for new input
            }
            String decimalObject = ((Button) event.getSource()).getText();
            String oldText = textDisplay.getText();
            String newText = oldText + decimalObject;
            textDisplay.setText(newText);
            decimalClick = 1; // Prevent adding another decimal
        }
    }

    @FXML
    void handlerDigitAction(ActionEvent event) {
        String digitObject = ((Button) event.getSource()).getText();

        // Clear any error message if present
        if (textDisplay.getText().equals("Please enter a number first") || resultDisplayed) {
            textDisplay.setText(""); // Clear display for new input
            resultDisplayed = false; // Reset the flag for new input
        }

        // Append the digit to the display
        textDisplay.setText(textDisplay.getText() + digitObject);
    }

    @FXML
    void handlerClearAction(ActionEvent actionEvent) {
        textDisplay.clear(); // Clear the display
        decimalClick = 0; // Reset decimal flag
        firstNumber = 0.0; // Reset stored number
        operation = ""; // Reset operation
        isSecondNumber = false; // Reset for new calculation
        resultDisplayed = false; // Clear result display flag
    }
}
