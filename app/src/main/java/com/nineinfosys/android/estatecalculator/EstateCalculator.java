package com.nineinfosys.android.estatecalculator;

/**
 * Created by Dev on 15-03-2017.
 */

public class EstateCalculator {
    double residence;
    double stocks;
    double savings;
    double vehicles;
    double retirement;
    double lifeinsurance;
    double otherassets;
    double debts;
    double funerals;
    double charitable;
    double state;
    Taxvalues tax;

    EstateCalculator(double residence,double stocks,double savings,double vehicles,double retirement,double lifeinsurance,double otherassets,double debts,double funerals,double charitable,double state){
        this.residence=residence;
        this.stocks=stocks;
        this.savings=savings;
        this.vehicles=vehicles;
        this.retirement=retirement;
        this.lifeinsurance=lifeinsurance;
        this.otherassets=otherassets;
        this.debts=debts;
        this.funerals=funerals;
        this.charitable=charitable;
        this.state=state;
    }

    public Taxvalues getvalues(){

        tax=new Taxvalues();
        tax.setTotaltax(totaltax());
        tax.setFederabletax(federabletax());
        return tax;

    }


    public double federabletax() {

        double value=((residence+stocks+savings+vehicles+retirement+lifeinsurance+otherassets)-(debts+funerals+charitable+state));

        if(value>5490001){

            return (value-5490001)*40/100;

        }else{

            return 0;

        }
    }

    public double totaltax() {

        double value=((residence+stocks+savings+vehicles+retirement+lifeinsurance+otherassets)-(debts+funerals+charitable+state));

        return value;
    }






    class Taxvalues{

        private double totaltax;
        private double federabletax;

        public double getTotaltax() {
            return totaltax;
        }
        public void setTotaltax(double totaltax) {
            this.totaltax = totaltax;
        }
        public double getFederabletax() {
            return federabletax;
        }
        public void setFederabletax(double federabletax) {
            this.federabletax = federabletax;
        }




    }

}
