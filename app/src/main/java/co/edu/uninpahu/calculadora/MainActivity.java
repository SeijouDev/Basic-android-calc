package co.edu.uninpahu.calculadora;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private Context mContext;
    private String prevRes;

    private Suma suma;
    private Resta resta;
    private Mult mult;
    private Div div;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        screen = (TextView) findViewById(R.id.textView);
        configButtons();

    }

    private View.OnClickListener clickFunction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String tx = ((Button) view).getText().toString();
            print(screen.getText() + tx);
        }
    };


    private void cls(){
        screen.setText("");
    }

    private void print(String s){
        Log.d("calc" , s);
        screen.setText(s);
    }

    private String getInput(){
        return ((TextView) findViewById(R.id.textView)).getText().toString().trim();
    }

    private double getDoubleInput(){
        return Double.parseDouble(getInput());
    }

    private void configButtons(){
        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btn_p = (Button) findViewById(R.id.btn_point);
        Button btnback = (Button) findViewById(R.id.btn_back);
        Button btnans = (Button) findViewById(R.id.btn_ans);


        (findViewById(R.id.btn_sum)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                div = null;
                mult = null;
                resta = null;
                suma = new Suma();

                if(getInput() != null && getInput().length() > 0){
                    suma.a = getDoubleInput() ;
                    cls();
                }
                else return;
            }
        });

        (findViewById(R.id.btn_res)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                div = null;
                mult = null;
                resta = new Resta();
                suma = null;

                if(getInput() != null && getInput().length() > 0){
                    resta.a = getDoubleInput() ;
                    cls();
                }
                else return;
            }
        });

        (findViewById(R.id.btn_mul)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                div = null;
                mult = new Mult();
                resta = null;
                suma = null;

                if(getInput() != null && getInput().length() > 0){
                    mult.a = getDoubleInput() ;
                    cls();
                }
                else return;
            }
        });

        (findViewById(R.id.btn_div)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                div = new Div();
                mult = null;
                resta = null;
                suma = null;

                if(getInput() != null && getInput().length() > 0){
                    div .a = getDoubleInput() ;
                    cls();
                }
                else return;
            }
        });

        (findViewById(R.id.btn_result)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = "";
                if(suma != null){
                    if(getInput() == null || (getInput().length() <  1))
                        return;
                    else{
                        suma.b = getDoubleInput();
                        cls();
                        r = ((suma.ejecutar() - (int) suma.ejecutar()) == 0) ? String.valueOf((int) suma.ejecutar()) : String.valueOf(suma.ejecutar());
                        print(r);
                    }
                }
                else if(resta != null){
                    if(getInput() == null || (getInput().length() <  1))
                        return;
                    else{
                        resta.b = getDoubleInput();
                        cls();
                        r = ((resta.ejecutar() - (int) resta.ejecutar()) == 0) ? String.valueOf((int) resta.ejecutar()) : String.valueOf(resta.ejecutar());
                        print(r);
                    }
                }
                else if (mult != null){
                    if(getInput() == null || (getInput().length() <  1))
                        return;
                    else{
                        mult.b = getDoubleInput();
                        cls();
                        r = ((mult.ejecutar() - (int) mult.ejecutar()) == 0) ? String.valueOf((int) mult.ejecutar()) : String.valueOf(mult.ejecutar());
                        print(r);
                    }
                }
                else if(div != null){
                    if(getInput() == null || (getInput().length() <  1))
                        return;
                    else{

                        div.b = getDoubleInput();
                        if (div.b == 0){
                            print("Error!");
                        }
                        else{
                            cls();
                            r = ((div.ejecutar() - (int) div.ejecutar()) == 0) ? String.valueOf((int) div.ejecutar()) : String.valueOf(div.ejecutar());
                            print(r);
                        }

                    }
                }
                else {
                    Toast.makeText(mContext, "Error",Toast.LENGTH_SHORT).show();
                }

                prevRes = r;

            }
        });


        Button[] misBotoncitos = {btn1, btn2, btn3, btn4,btn5,btn6,btn7,btn8,btn9};
        for ( int i = 0 ; i < misBotoncitos.length ; i++)
            misBotoncitos[i].setOnClickListener(clickFunction);

        Button btnClear = (Button) findViewById(R.id.btn_cls);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevRes = "";
                cls();
            }
        });

        btn_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(getInput() != null && (getInput().length() >  0 && !String.valueOf(getInput()).contains("."))))
                    return;
                else
                    screen.append(".");
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getInput().length() == 0 || getInput().length() > 0 && getDoubleInput() != 0)
                    print(screen.getText() + ((Button) view).getText().toString());
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getInput().length() > 0)
                    print(screen.getText().toString().substring(0, screen.getText().toString().length() - 1));
            }
        });

        btnans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prevRes != null && prevRes.length() > 0)
                    print(screen.getText().toString().concat(prevRes));
            }
        });
    }





}
