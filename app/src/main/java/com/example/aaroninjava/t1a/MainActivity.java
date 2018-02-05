package com.example.aaroninjava.t1a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aaroninjava.t1a.BSa;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String CP="C";
        //double Pr=1.732;
        double Pr=0.84;
        double S=100;
        double K=110;
        double T=0.1;
        double r=0.01;
        double y=0;
        //double sigma=0.3;
        double sigma=0.3;

        BSa b = new BSa(CP,Pr,S,K,T,sigma,r,y);

        //b.iv1a(CP,Pr,S,K,T,r,y);
        //System.out.println("call="+b.call());
        b.iv1a();

    }


}
