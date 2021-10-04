package com.example.student_loan_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button mCalcButton;
    private Button mClearButton;
    private TextView mTotalInterestTextView, mMonthlyPaymentOutput;
    private EditText mPrincipleInput, mLoanPercentage, mYearsBorrowed;


    private void clearOutputs(){
        int blank = R.string.clear;
        mTotalInterestTextView.setText(blank);
        mMonthlyPaymentOutput.setText(blank);
    }

    private void doMathWithLoanFee() {
       double loanAmount = Integer.parseInt(mPrincipleInput.getText().toString()) * 1.05;
        double interestRate = Integer.parseInt(mLoanPercentage.getText().toString());
        double loanLengthInYears = Integer.parseInt(mYearsBorrowed.getText().toString());

        double r = (interestRate)/(100);
        double r1 = 1 + r;

        double r2 = Math.pow(r1,loanLengthInYears);
        double totalPayment = loanAmount*r2;
        double numberOfPayments = loanLengthInYears * 12;

        double monthlyPayment = (totalPayment)/(numberOfPayments);
        String printableMonthlyPayment = new Double(monthlyPayment).toString();
        mMonthlyPaymentOutput.setText(printableMonthlyPayment);

        double totalInterestPayment = totalPayment-loanAmount;
        String printableTotalInterest = new Double(totalInterestPayment).toString();
        mTotalInterestTextView.setText(printableTotalInterest);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTotalInterestTextView = (TextView)findViewById(R.id.totalInterestOutput);
        mMonthlyPaymentOutput = (TextView) findViewById(R.id.monthlyPaymentOutput);

        mPrincipleInput = (EditText) findViewById(R.id.PrincipleInput);
        mLoanPercentage = (EditText) findViewById(R.id.LoanPercentage);
        mYearsBorrowed = (EditText) findViewById(R.id.YearsBorrowed);


        mCalcButton = (Button) findViewById(R.id.CalcButton);
        mCalcButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doMathWithLoanFee();
            }
        });

        mClearButton = (Button) findViewById(R.id.ClearButton);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearOutputs();
            }
        });

    }
}
