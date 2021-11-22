package application;

import java.util.ArrayList;

public class Perceptron {
	ArrayList <Double> inputs;
	double output;
	ArrayList <Double> weights;
	int functionNbr ;
	double threshhold;
	int inputNbr;
	double  a_unit;
	double a_bounded, v_min, v_max;
	double v;
	double a_sigmoid, k;
	double a_guassian, v0, sigma;
	
	public Perceptron( int input ) {
		inputNbr = input;
		this.weights= new ArrayList<>();
		for(int i=0; i<input;i++)
			this.weights.add(Math.random()*10-5);
		this.inputs = new ArrayList<>();
		functionNbr=0;
		threshhold=0;
		a_unit=1;
	
		
		
		
	}
	public double useActivationFuction(double in) {
		if(functionNbr ==0) { //unit
			if (in>0)
				return a_unit;
			 return 0;
		}else 
			if(functionNbr==1) { //bounded
				if(in< v_min)
					return 0;
				else if(v>= v_min && v<=v_max)
					return a_bounded*(in-v_min)/(v_max-v_min);
				else return a_bounded;
			}else if(functionNbr==2) { //identity
				return in;
			}else if(functionNbr==3) {//sigmoid
			//return a_sigmoid*(Math.exp(k*in)-1)/(Math.exp(k*in)+1);
		return 1/(1+Math.exp(-in))-0.01;
			}else if(functionNbr==4) { //guassian
				return a_guassian*Math.exp(Math.pow((in-v0)/sigma,2));
			}
		
		return in;
	}
	public void getOutput() {
		double a=0;
		for(int i=0 ; i<inputs.size();i++)
			a += inputs.get(i)*weights.get(i);
		output = useActivationFuction(a- threshhold);
	}
	
	
	
}
