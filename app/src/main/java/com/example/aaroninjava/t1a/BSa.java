package com.example.aaroninjava.t1a;

/**
 * Created by Student on 2018/2/5.
 */
import org.apache.commons.math3.distribution.NormalDistribution;
public class BSa {

    final static double api=Math.PI;
    double x,d1,d2,pdfa,cdfa;
    double Pr,S,K,T,sigma,r,y;
    String CP;
    NormalDistribution n1 = new NormalDistribution();

    public bsa2(){}
    public bsa2(double x){}
    public bsa2(double S,double K,double T,double sigma,double r,double y){
        this.S=S;
        this.K=K;
        this.T=T;
        this.sigma=sigma;
        this.r=r;
        this.y=y;
        d1 =( Math.log(S/K) +
                (r-y + sigma * sigma / 2)* T ) / (sigma * Math.sqrt(T));
        d2 = d1- sigma * Math.sqrt(T) ;

    }

    public bsa2(String CP,double Pr,double S,double K,double T,double sigma,double r,double y){
        this.CP=CP;
        this.Pr=Pr;
        this.S=S;
        this.K=K;
        this.T=T;
        this.sigma=sigma;
        this.r=r;
        this.y=y;

        //    d1 =( Math.log(S/K) +
        //            (r-y + sigma * sigma / 2)* T ) / (sigma * Math.sqrt(T));
        //    d2 = d1- sigma * Math.sqrt(T) ;
    }

    public double ad1()
    {
        double ad11;
        ad11 =( Math.log(S/K) +
                (r-y + sigma * sigma / 2)* T ) / (sigma * Math.sqrt(T));
        return ad11;
    }
    public double ad2()
    {
        double ad22;
        ad22 = ad1()- sigma * Math.sqrt(T) ;
        return ad22;
    }

    public double cdf(double x)
    {
        double cdf1;
        cdf1=n1.cumulativeProbability(x);
        return cdf1;
    }

    public double pdf(double x)
    {
        double pdf1;
        pdf1=n1.density(x);
        return pdf1;
    }

    //Op_Cvalue = S * NorCdf(d1) - K * Exp(-r * T) * NorCdf(d2)
    public double call()
    {
        double calla;
        d1=ad1();
        d2=ad2();
        calla=S*cdf(d1)- K*Math.exp(-r*T)* cdf(d2);
        return calla;
    }

    //Op_Pvalue = K * Exp(-r * T) * NorCdf(-d2) - S * NorCdf(-d1)
    public double put()
    {
        double puta;
        puta=K*Math.exp(-r*T)* cdf(-d2) - S*cdf(-d1);
        return puta;
    }

    //cdelta = Math.exp(-y * T) * b1.cdfapa(d1);
    //public double deltaC()
    public double delta(String CP)
    {
        double delta1;
        if (CP=="C")
        {delta1 = Math.exp(-y * T) * cdf(d1);}
        else
        {delta1 = Math.exp(-y * T) * ( cdf(d1)-1 );}
        System.out.println("delta="+delta1);
        return delta1;
    }

    public double gamma() //call.put相同
    {
        double gamma1;
        gamma1 = Math.exp(-y * T) * pdf(d1)/(S * sigma * Math.sqrt(T));
        System.out.println("gamma="+gamma1);
        return gamma1;
    }

    public double vega() //call.put相同..要除以100% ??
    {
        double vega1=0;
        //gamma1 = Math.exp(-y * T) * pdf(d1)/(S * sigma * Math.sqrt(T));
        vega1 =  S * Math.exp(-y*T) * pdf(d1) * Math.sqrt(T)  ;
        System.out.println("vega="+vega1);
        return vega1;
    }

    public double theta(String CP)
    {
        //double gamma1;
        //gamma1 = Math.exp(-y * T) * pdf(d1)/(S * sigma * Math.sqrt(T));

        //ctheta = ( -S * sigma * b1.pdfapa(d1)/(2*Math.sqrt(T) ) - r*K*Math.exp(-r*T) * b1.cdfapa(d2)) / 365 ;
        double theta1;
        if (CP=="C")
        {theta1 = ( -S*sigma*pdf(d1) / (2*Math.sqrt(T))
                -   r*K*Math.exp(-r*T) * cdf(d2)    )  ;}//要除以365天??
        else
        {theta1 = ( -S*sigma*pdf(d1) / (2*Math.sqrt(T))
                +   r*K*Math.exp(-r*T) * cdf(-d2)    ) ;} //要除以365天??
        //ptheta = ( -S * sigma * b1.pdfapa(d1)/(2*Math.sqrt(T) ) + r*K*Math.exp(-r*T) * b1.cdfapa(-d2)) / 365 ;

        System.out.println("theta="+theta1);
        return theta1;
    }

    public double rho(String CP)
    {
        double rho1;
        if (CP=="C")
        {rho1 = rho1 = T * K * Math.exp(-r*T) * cdf(d2) ;}//要除以100% ?
        else
        {rho1 = -T * K * Math.exp(-r*T) * cdf(-d2);} //要除以100% ?
/*        //crho = T * K * Exp(-r * T) * NorCdf(d2)
        //prho =-T * K * Exp(-r * T) * NorCdf(-d2)*/
        System.out.println("rho="+rho1);
        return rho1;
    }

    //public void double xx(double x)
    public double xx()
    {
        double x1;
        x1=x;
        //x1 =x/2;
        //System.out.println("xx="+x1);
        System.out.println("S="+S);
        System.out.println("K="+K);
        return S/10;
    }


    //public bsa2(String CP,double Pr,double S,double K,double T,double sigma,double r,double y){
//public  static void iv1(double P) {
    //public static void iv1a(String CP,double P,double S,double K,double T,double r,double y) {
    public void iv1a() {
        System.out.println("IV1a");
        //double P=1.414;
        //double P=1.732;

        double value=0;
        double err=0;
        double tol=0.0001;
        double sd1=0.0001;
        double sd2=4;
        double std=0;
        double i=1;
        std = (sd1 + sd2) / 2;
        System.out.println("IV計算前代入sigma="+std);
        System.out.println("IV計算前Pr="+Pr);
        //value=P*P;
        //value=yx(std);
        this.sigma=std;
        //System.out.println("IV計算前 sigma="+this.sigma);
        value=call();
        //System.out.println("IV計算前 代入估計value="+value);
        err = Math.abs(value - Pr);

        //while (err >= tol)
        while ( (err >= tol) && (i<20)  ) //(i<100)
        {
            if (value >= Pr)
            {
                sd2 = std;
                //        System.out.println("value =="+value);
                //        System.out.println("Pr="+Pr);
                //        System.out.println("value >= Pr, sd2="+sd2);
            }
            if (value < Pr)
            {
                sd1 = std;
//                    System.out.println("value =="+value);
//                    System.out.println("Pr="+Pr);
//                    System.out.println("value < Pr, sd1="+sd1);
            }

            std = (sd1 + sd2) / 2;
            this.sigma=std;
            //value=yx(std);
            value=call();
            err = Math.abs(value - Pr);
            i=i+1;
            //System.out.println("二分逼近計算次數="+sigma);
            System.out.println("二分逼近計算次數="+i);
            System.out.println("sd1="+sd1+",  sd2="+sd2);
            System.out.println("二分逼近計算帶入std="+std+",  估計"+value);
        }

        System.out.println("二分法是"+std);
        //System.out.println("y=x*x結果是"+value);
        System.out.println("IV1a");
        //System.out.println("結果誤差="+err);
    }

    public  static double yx(double x) {
        double yx1;
        yx1=x*x;
        //System.out.println("y=x*x結果是"+yx1);
        return yx1;
    }



}
