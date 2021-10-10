package com.example.calculatorandr;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableString;

import java.lang.Math;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   private EditText enteredText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredText=findViewById(R.id.entText);
        enteredText.setShowSoftInputOnFocus(false);
        enteredText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.enterText).equals(enteredText.getText().toString())){
                    enteredText.setText("");
                }
            }
        });

    }

    private void upDateTxt(String str) {
        String tmpStr = enteredText.getText().toString();
        int cursorPos = enteredText.getSelectionStart();
        String leftStr = tmpStr.substring(0, cursorPos);
        String rightStr = tmpStr.substring(cursorPos);
        if(getString(R.string.enterText).equals(enteredText.getText().toString())){
            enteredText.setText(str);
            enteredText.setSelection(cursorPos-14);
        }
        else {
            enteredText.setText(String.format("%s%s%s", leftStr, str, rightStr));
            enteredText.setSelection(cursorPos + 1);
        }

    }

    private void upDateTxtFr(String str) {
        String tmpStr = enteredText.getText().toString();
        int cursorPos = enteredText.getSelectionStart();
        String leftStr = tmpStr.substring(0, cursorPos);
        String rightStr = tmpStr.substring(cursorPos);
        if(getString(R.string.enterText).equals(enteredText.getText().toString())){
            enteredText.setText(str);
        }
        else {
            enteredText.setText(String.format("%s%s%s", leftStr, str, rightStr));
            enteredText.setSelection(rightStr.length());
        }

    }

    public void clearButton(View view){
        enteredText.setText("");
    }

    public void equalButton(View view){
        String values=enteredText.getText().toString();
        values=values.replaceAll("÷", "/");
        values=values.replaceAll("×", "*");

        Expression expr = new Expression(values);
        String outCome = String.valueOf(expr.calculate());
        if(outCome.equals("NaN")){
            outCome="";
            Toast.makeText(getApplicationContext(),"Incorrect actions!",Toast.LENGTH_SHORT).show();
        }

        enteredText.setText(outCome);
        enteredText.setSelection(outCome.length());
    }

    public void zeroButton(View view){
        upDateTxt("0");
    }

    public void oneButton(View view){
        upDateTxt("1");
    }

    public void twoButton(View view){
        upDateTxt("2");
    }

    public void threeButton(View view){
        upDateTxt("3");
    }

    public void fourButton(View view){
        upDateTxt("4");
    }

    public void fiveButton(View view){
        upDateTxt("5");
    }

    public void sixButton(View view){
        upDateTxt("6");
    }

    public void sevenButton(View view){
        upDateTxt("7");
    }

    public void eightButton(View view){
        upDateTxt("8");
    }

    public void nineButton(View view){
        upDateTxt("9");
    }

    public void backButton(View view){
        int cursorPos=enteredText.getSelectionStart();
        int textLength=enteredText.getText().length();

        if(cursorPos!=0 && textLength!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) enteredText.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            enteredText.setText(selection);
            enteredText.setSelection(cursorPos - 1);

        }
    }

    public void mulButton(View view){
        upDateTxt("×");
    }

    public void divButton(View view){
        upDateTxt("÷");
    }

    public void addButton(View view){
        upDateTxt("+");
    }

    public void subButton(View view){
        upDateTxt("-");
    }

    public void plusMinusButton(View view){
        if(!enteredText.getText().toString().isEmpty()) {
            double num = Double.parseDouble(String.valueOf(enteredText.getText()));
            num = num * (-1);
            enteredText.setText(String.valueOf(num));
        }
        upDateTxtFr("");
    }

    public void dotButton(View view){
        upDateTxt(".");
    }

    public void rootButton(View view){
        String values=enteredText.getText().toString();
        Expression expr = new Expression(values);
        double result = Math.sqrt(expr.calculate());
        String outCome = String.valueOf(result);
        if(outCome.equals("NaN")){
            outCome="";
            Toast.makeText(getApplicationContext(),"Incorrect actions!",Toast.LENGTH_SHORT).show();
        }
        enteredText.setText(outCome);
        enteredText.setSelection(outCome.length());
    }
}