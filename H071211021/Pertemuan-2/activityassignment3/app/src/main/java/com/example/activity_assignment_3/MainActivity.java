package com.example.activity_assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_div, btn_mul, btn_min, btn_plus, btn_equ, btn_del, btn_AC;
    TextView display;
    String[] inputArray = {"0","",""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        display = findViewById(R.id.display);
        display.setText(inputArray[0] + inputArray[1] + inputArray[2]);

        btn_0 = findViewById(R.id.btn_0);
        btn_0.setOnClickListener(this);

        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);

        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);

        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);

        btn_4 = findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);

        btn_5 = findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);

        btn_6 = findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);

        btn_7 = findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);

        btn_8 = findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);

        btn_9 = findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);

        btn_div = findViewById(R.id.btn_div);
        btn_div.setOnClickListener(this);

        btn_mul = findViewById(R.id.btn_mul);
        btn_mul.setOnClickListener(this);

        btn_min = findViewById(R.id.btn_min);
        btn_min.setOnClickListener(this);

        btn_plus = findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(this);

        btn_equ = findViewById(R.id.btn_equ);
        btn_equ.setOnClickListener(this);

        btn_AC = findViewById(R.id.btn_AC);
        btn_AC.setOnClickListener(this);

        btn_del = findViewById(R.id.btn_del);
        btn_del.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_0:
                setEqu = true;
                operation("0");
                break;
            case R.id.btn_1:
                setEqu = true;
                operation("1");
                break;
            case R.id.btn_2:
                setEqu = true;
                operation("2");
                break;
            case R.id.btn_3:
                setEqu = true;
                operation("3");
                break;
            case R.id.btn_4:
                setEqu = true;
                operation("4");
                break;
            case R.id.btn_5:
                setEqu = true;
                operation("5");
                break;
            case R.id.btn_6:
                setEqu = true;
                operation("6");
                break;
            case R.id.btn_7:
                setEqu = true;
                operation("7");
                break;
            case R.id.btn_8:
                setEqu = true;
                operation("8");
                break;
            case R.id.btn_9:
                setEqu = true;
                operation("9");
                break;
            case R.id.btn_mul:
                operation("*");
                break;
            case R.id.btn_div:
                operation("/");
                break;
            case R.id.btn_min:
                operation("-");
                break;
            case R.id.btn_plus:
                operation("+");
                break;
            case R.id.btn_equ:
                operation("=");
                break;
            case R.id.btn_AC:
                reset();
                display = findViewById(R.id.display);
                display.setText(inputArray[0] + inputArray[1] + inputArray[2]);
                break;
            case R.id.btn_del:
                delete();
                display = findViewById(R.id.display);
                display.setText(inputArray[0] + inputArray[1] + inputArray[2]);
                break;
        }
    }

    int i = 0;
    boolean set = true;
    boolean setEqu = true;
    public void operation(String x) {
        display = findViewById(R.id.display);
        boolean flag1 = isOperation(x);

        if(!inputArray[2].equals("")) {
            set = false;
        }
        else {
            set = true;
        }

        if (!isEquals(x) && setEqu) {
            if (flag1 && set && inputArray[0]!="") {
                inputArray[1] = x;
                set = false;
                i = 2;
            }
            else if (!flag1){
                if(inputArray[i] == "0"){
                    inputArray[i] = x;
                }
                else {
                    inputArray[i] += x;
                }
            }
            display.setText(inputArray[0] + inputArray[1] + inputArray[2]);
        }
        else {
            try {
                switch (inputArray[1]) {
                    case "+": {
                        BigInteger num1 = new BigInteger(inputArray[0]);
                        BigInteger num2 = new BigInteger(inputArray[2]);
                        display.setText(String.valueOf(num1.add(num2)));
                        reset();
                        break;
                    }
                    case "-": {
                        BigInteger num1 = new BigInteger(inputArray[0]);
                        BigInteger num2 = new BigInteger(inputArray[2]);
                        display.setText(String.valueOf(num1.subtract(num2)));
                        reset();
                        break;
                    }
                    case "*": {
                        BigInteger num1 = new BigInteger(inputArray[0]);
                        BigInteger num2 = new BigInteger(inputArray[2]);
                        display.setText(String.valueOf(num1.multiply(num2)));
                        reset();
                        break;
                    }
                    case "/": {
                        Double num1 = Double.parseDouble(inputArray[0]);
                        Double num2 = Double.parseDouble(inputArray[2]);
                        if (inputArray[2].equals("0")) {
                            Toast.makeText(getApplicationContext(),"Cannot divide by zero", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            display.setText(String.valueOf(num1/num2));
                            reset();
                        }
                        break;
                    }
                }
            }
            catch (NumberFormatException ex) {
                Toast.makeText(getApplicationContext(), "Input melebihi batas", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    public boolean isOperation(String x) {
        return x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/");
    }

    public  boolean isEquals(String x) {
        return x.equals("=");
    }

    public void delete() {
        if(!inputArray[2].equals("")) {
            if (inputArray[2].length() == 1) {
                inputArray[2] = "";
            }
            else {
                inputArray[2] = inputArray[2].substring(0,inputArray[2].length()-1);
            }
        }
        else if(!inputArray[1].equals("")) {
            inputArray[1] = "";
            set = true;
            i = 0;
        }
        else if(!inputArray[0].equals("")) {
            if (inputArray[0].length() == 1) {
                inputArray[0] = "0";
                set = true;
            }
            else {
                inputArray[0] = inputArray[0].substring(0,inputArray[0].length()-1);
            }
        }
    }

    public void reset() {
        inputArray[0] = "0";
        inputArray[1] = "";
        inputArray[2] = "";
        set = true;
        setEqu = false;
        i = 0;
    }
}