package com.kulshreshthaneha.finalcalculator;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private TextView result;

    private TextView but;

    private String operand;

    private String operator;

    private Set<String> numbers;

    private Set<String> operators;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String JSON_STRING = "{\"employee\":{\"name\":\"1\"}}";
    String name;
    TextView employeeName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
        employeeName = (TextView) findViewById(R.id.name);
        but = findViewById(R.id.fragment1);

        sharedPreferences = getSharedPreferences("runtime", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();

        int value = sharedPreferences.getInt("onCreate", 0);
        int finished = value + 1;
        editor.putInt("onCreate", finished);
        editor.apply();
        but.setText("# of times app ran: "+finished);

        final Fragment first = new FirstFragment();
        findViewById(R.id.fragment1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but.setText("hello!");
            }
        });

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(JSON_STRING);
            // fetch JSONObject named employee
            JSONObject employee = obj.getJSONObject("employee");
            // get employee name and salary
            name = employee.getString("name");
            // set employee name and salary in TextView's
            employeeName.setText("app version: "+name);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initNumbers() {
        numbers = new HashSet<String>();
        for (int i = 0; i < 10; i++) {
            numbers.add(Integer.toString(i));
        }
    }


    private void initOperators() {
        operators = new HashSet<String>();
        String[] ops = { "+", "-", "*", "/" };
        for (String operator : ops) {
            operators.add(operator);
        }
    }


    public void handleClick(View view) {
        Button clicked = (Button) view;
        String label = clicked.getText().toString();
        String display = result.getText().toString();

        if (isClear(label)) {
            result.setText(R.string.result_default);
        } else if (isNumerical(label)) {
            if (isDefaultResult(display) || isOperator(display)) {
                result.setText(label);
            } else {
                result.setText(display + label);
            }
        } else if (isOperator(label)) {
            operator = label;
            operand = display;
            result.setText(label);
        } else if (label.equals("=")) {
            double a, b, c;

            if (operator == null || operand == null) {
                return;
            }

            try {
                a = Double.parseDouble(operand);
                b = Double.parseDouble(display);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            if (operator.equals("+")) {
                c = a + b;
            } else if (operator.equals("-")) {
                c = a - b;
            } else if (operator.equals("*")) {
                c = a * b;
            } else  {
                c = a / b;
            }

            operand = Double.toString(c);
            result.setText(operand);
        }
    }


    private boolean isClear(String value) {
        return value.equals(getString(R.string.buttonClear));
    }


    private boolean isOperator(String value) {
        if (operators == null) {
            initOperators();
        }
        return operators.contains(value);
    }


    private boolean isDefaultResult(String value) {
        return value.equals(getString(R.string.result_default));
    }


    private boolean isNumerical(String value) {
        if (numbers == null) {
            initNumbers();
        }
        return numbers.contains(value);
    }

}