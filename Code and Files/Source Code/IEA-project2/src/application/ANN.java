package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ANN {
	ArrayList <ArrayList<Perceptron>> layers;
	int input;
	double error;
	int errfct;
	int iteration;
	public ANN(ArrayList<Integer> Nbr, int input , double error, int errfct, int iteration) {
		this.error=error;
		this.input= input;
		this.errfct = errfct;
		this.iteration = iteration;
		layers= new ArrayList<>();
		for(int i=0; i<Nbr.size();i++)
		{	ArrayList<Perceptron> l = new ArrayList<>();
		if(i==0) {
			for(int j=0; j<Nbr.get(i); j++)
				l.add(new Perceptron(input));
		}else
		{
			for(int j=0; j<Nbr.get(i); j++)
				l.add(new Perceptron(Nbr.get(i-1)));
		}
			
			layers.add(l);
		
		}
		for	(int i=0; i<layers.size();i++) {
			for(int j=0; j<layers.get(i).size();j++) {
				{	double a ;
					if(i!=0)
					{a= layers.get(i-1).size();}
				else 
				{a= input;}
			
				
				int s = layers.get(i).get(j).weights.size();
				layers.get(i).get(j).weights= new ArrayList<>();
				for(int l=0; l<s;l++)
					{layers.get(i).get(j).weights.add((Math.random()*(layers.get(i).size()-a)+a)*Math.sqrt(2.0/a));
					}
				}
				
				

				}
				
			}
		}
	

	public ArrayList<Double> getOutput(ArrayList <Double> input) {
		if(input.size()==this.input ) {

		ArrayList<Double> in = input; 
		for( int i=0; i<layers.size();i++) {
			ArrayList <Double > c = new ArrayList<>();
			for(int j=0; j<layers.get(i).size();j++) {
		layers.get(i).get(j).inputs= in;		
		layers.get(i).get(j).getOutput();
		double a = layers.get(i).get(j).output;
		c.add(a);
			}
		in = c;
		}
		ArrayList<Double> out = new ArrayList<>();
		for(int i=0; i< layers.get(layers.size()-1).size();i++){
			out.add(layers.get(layers.size()-1).get(i).output);
		}
		return out;
		
		}
		return null;//????????
		
	}
	
	public void UpdateWeights(ArrayList<Double> in,ArrayList<Double> out, ArrayList<Double> desired) throws Exception {
		
		ArrayList<Double> error = Difference(desired,out);
		Matrix Z;
		Matrix Err = new Matrix(getArrayV(error));

		for(int i= layers.size()-1;i>=0; i--) {

		if(i ==0) {
			 Z = new Matrix(getArrayV(in) );
		}else {
		 Z = new Matrix(getArrayV(getOutputFromLayer(i-1)));
		}
		
		Matrix DW = MatrixMethods.transpose(MatrixMethods.Multiply(Err, MatrixMethods.transpose(Z)));
		
		Err= MatrixMethods.Multiply(new Matrix(getWeights(in, i)), Err);
		
		double [][] arr = DW.data;	
		for(int k=0; k<layers.get(i).size();k++) {
			
			layers.get(i).get(k).weights = Addition(layers.get(i).get(k).weights, getArrayList(DW.data,k ));
		}
		

		
		}
		
		
	}
	public int train(ArrayList<Double> in , ArrayList<Double> desired) throws Exception {
		if(in.size()==this.input && desired.size()==layers.get(layers.size()-1).size()) {
			ArrayList <ArrayList<Perceptron>> save = new ArrayList <ArrayList<Perceptron>>(this.layers);
		ArrayList<Double> o = getOutput(in);
		
		int tt=0;
		while(!(areEqual(o, desired,errfct)<=error) && tt<iteration) {
			
			UpdateWeights(in, o, desired);
			
			o = getOutput(in);
			for(int g=0; g<o.size();g++)
				System.out.println(o.get(g));
			tt++;
		}
		
		if(tt>= iteration) {
			this.layers= save;
		System.out.println("Network restored");	
		}
		return tt;
		}
		return -1;
	}
	private static double areEqual(ArrayList<Double> d, ArrayList<Double> a, int c) {// 0 MDE 1 MAE 2 MSE
		if(c==0) {
			double t=0;
			for(int i=0; i<a.size();i++) {
				t+= d.get(i)-a.get(i);
			}
			t/=a.size()*1.0;
			System.out.println("Error "+t);

			return Math.abs(t);
		}else if(c==1) {
			double t=0;
			for(int i=0; i<a.size();i++) {
				t+= Math.abs(d.get(i)-a.get(i));
			}
			t/=a.size()*1.0;
			System.out.println("Error "+t);

			return t;
		}else {
			double t=0;
			for(int i=0; i<a.size();i++) {
				t+= Math.pow(d.get(i)-a.get(i),2);
			}
			t/=a.size()*1.0;
			System.out.println("Error "+t);
			return t;
		}
		
	}
	public double [][] getWeights(ArrayList<Double> in ,int layer){
		double [][] d;
		if(layer==0) {
			d = new double [in.size()][layers.get(layer).size()];
		}else {
		d = new double [layers.get(layer-1).size()][layers.get(layer).size()];
		}
		for(int k=0; k<layers.get(layer).size();k++) {
			ArrayList<Double> w =layers.get(layer).get(k).weights ; 
			for(int i=0; i<w.size();i++) {
				d[i][k] = w.get(i); 
			}
		}
		return d;
	}
	public ArrayList<Double> getArrayList(double [][] array, int col){
		ArrayList<Double> al = new ArrayList<>();
		for(int i=0; i<array.length;i++) {
			al.add(array[i][col]);
		}
	return al;
	}
	public ArrayList<Double> getOutputFromLayer(int layer) throws Exception{
		if(layer>=layers.size())
			throw new Exception("Layer not existent");
	ArrayList <Double> a = new ArrayList<>();
		for(int i=0; i<layers.get(layer).size();i++) {
			a.add(layers.get(layer).get(i).output);
	}
	return a;
	}
	private double[][] getArrayH (ArrayList<Double> e) {
		double[][] arr = new double [1][e.size()];
		for (int i=0; i<e.size();i++) {
			arr[0][i]= e.get(i);
		}
	return arr;
	}
	private double[][] getArrayV(ArrayList<Double> e){
		double[][] arr = new double [e.size()][1];
		for (int i=0; i<e.size();i++) {
			arr[i][0]= e.get(i);
		}
	return arr;
	}
	private ArrayList <Double> Difference(ArrayList<Double> desired, ArrayList<Double> out){
		ArrayList<Double> t = new ArrayList<>();
		for(int i =0; i< out.size();i++) {
			t.add(desired.get(i)-out.get(i));
		}
		return t;
	}
	private ArrayList <Double> Addition(ArrayList<Double> desired, ArrayList<Double> out){
		ArrayList<Double> t = new ArrayList<>();
		for(int i =0; i< desired.size();i++) {
			t.add(desired.get(i)+out.get(i));
		}
		return t;
	}
	public Perceptron getPerceptron( int layer, int p) throws Exception {
		if(layer< layers.size())
		{
			if(p>=layers.get(layer).size()) {
				
				throw new Exception("Perceptron does not exist");
			}
	}
		else {
			throw new Exception("Layer does not exist");
		}
		return layers.get(layer).get(p);
		
	}
	
	public double SeperateValidation(String[][] inout , double trainingPerc) throws Exception {
		if(trainingPerc<=100 && trainingPerc>=0) {
		int size = inout.length-1;
		
		int toTrain = (int) Math.ceil(trainingPerc*size/100.0);
		int toValidate = size-toTrain;
		if(toValidate==0 ){
			if(toTrain!=1)
			{
			 toValidate++;
			 toTrain--;
			}else {
				return 1;
			}
		}
		System.out.println("IEA "+toTrain+" "+toValidate);
		 ArrayList<String> a2= new ArrayList<>();
		 ArrayList<String> a3= new ArrayList<>();

         for(int i=1; i<inout.length;i++) {
         		a2.add(inout[i][1]);
         		a3.add(inout[i][2]);	
         }
         double correct=0,total=0;
         
		for(int i=0; i<a2.size() ;i++) {
			String in = a2.get(i);
    		String out = a3.get(i);
    	
    		ArrayList<Double> inp = new ArrayList<>();
    		ArrayList<Double> outp = new ArrayList<>();
    		String [] arr1 = in.split(",");
    		String [] arr2 = out.split(",");
    		for(int j=0; j<arr1.length;j++)
    			inp.add(Double.parseDouble(arr1[j]));
    		
    		
    		for(int j=0; j<arr2.length;j++)
    			outp.add(Double.parseDouble(arr2[j]));
    		
    		if(inp.size()!=input || outp.size()!=layers.get(layers.size()-1).size())
    			return -1;
    		System.out.println(in);
    		System.out.println(out);

			if(i<toTrain) {
				System.out.println("TRAIN "+train(inp, outp));
			}else {
				if(areEqual(getOutput(inp), outp,errfct)<error) {
					System.out.println(1);
					correct++;
					System.out.println("correct: "+correct);
				}
				total++;
			}
			
		}
		System.out.println("SEPERATION "+ correct/total);
		correct = correct/total*1.0;
		
		return correct;
		}
		return -1;
	}
	
	public double MonteCarlo(String[][] inout,  int times) throws Exception {
		
		int size = inout.length-1;
		
		
		 ArrayList<String> a2= new ArrayList<>();
		 ArrayList<String> a3= new ArrayList<>();

         for(int i=1; i<inout.length;i++) {
         		a2.add(inout[i][1]);
         		a3.add(inout[i][2]);	
         }

		double hits = 0;
		int total = 0;
		while(times>0) {
			List<Integer> l =Randomize(a2.size());
			a2=shuffleAL(a2,l);
			a3=shuffleAL(a3,l);
				         
			for(int i=0; i<a2.size() ;i++) {
				String in = a2.get(i);
	    		String out = a3.get(i);
	    	
	    		ArrayList<Double> inp = new ArrayList<>();
	    		ArrayList<Double> outp = new ArrayList<>();
	    		String [] arr1 = in.split(",");
	    		String [] arr2 = out.split(",");
	    		
	    		for(int j=0; j<arr1.length;j++)
	    			inp.add(Double.parseDouble(arr1[j]));
	    		
	    		for(int j=0; j<arr2.length;j++)
	    			outp.add(Double.parseDouble(arr2[j]));
	    		if(inp.size()!=input || outp.size()!=layers.get(layers.size()-1).size())
	    			return -1;
				if(i<a2.size()/2) {
					train(inp, outp);
				}else {
					if(areEqual(getOutput(inp), outp,errfct)<error) {
						hits++;
					}
				total ++;
				}
			}
					
			times--;
		}
		System.out.println("Monte Carlo " +hits*1.0/total);
		return hits*1.0/total;
		
	}
	
public double KFoldValidationJ(String[][] inout,int k) throws Exception {

		ArrayList<String> a2= new ArrayList<>();
		 ArrayList<String> a3= new ArrayList<>();

         for(int i=1; i<inout.length;i++) {
         		a2.add(inout[i][1]);
         		a3.add(inout[i][2]);	
         }
         
         int sub = a2.size()/k;
         int hits =0;
         int total = 0;
         ArrayList<ArrayList<Integer>> list = new ArrayList<>();
         ArrayList<Integer> l = new ArrayList<>();
         int t=0;
         System.out.println("SIZE "+a2.size() +" K "+sub);
         int ss=0;
         while(ss<a2.size()+1)
         {
        	
        	if(t<sub) {
        		l.add(ss);
        		ss++;
            	t++;

        	}else {
        		list.add(l);
        		l = new ArrayList<>();
        		t=0;
        	}
  
         }
       for(ArrayList<Integer> al: list) {
    	   for(int i: al)
    		   System.out.print(i);
       System.out.println();
       }
         for(int i=0; i<list.size();i++) {
        	 
        	 for(int j=0; j<list.size() ;j++) {
        		 String in = a2.get(j);
  	    		String out = a3.get(j);
  	    		ArrayList<Double> inp = new ArrayList<>();
 	    		ArrayList<Double> outp = new ArrayList<>();
 	    		String [] arr1 = in.split(",");
 	    		String [] arr2 = out.split(",");

 	    		for(int s=0; s<arr1.length;s++)
 	    			inp.add(Double.parseDouble(arr1[s]));
 	    		
 	    		for(int s=0; s<arr2.length;s++)
 	    			outp.add(Double.parseDouble(arr2[s]));
 	    		
        		if(list.get(i).contains(j)) {
        		
 					train(inp, outp);

        		}else {
        			if(areEqual(getOutput(inp), outp,errfct)<error) {
 						hits++;
 					}
        			total ++;
        		}
        			
        		
        	 }			
         }
         System.out.println("KFOLDDDD "+hits+"/"+total);
         
         return hits/total;
	}
	
public double KFoldValidation(String[][] inout, int k) throws Exception {
	
	
	ArrayList<String> a2 = new ArrayList<>();// input
	ArrayList<String> a3 = new ArrayList<>();// output
	for (int i = 1; i < inout.length; i++) {
		a2.add(inout[i][1]);
		a3.add(inout[i][2]);
	}
	int sub = (int) Math.ceil(a2.size() * 1.0 / k); // divide into k folds
	if(sub<=0)
		return -1;
	System.out.println(sub);
	int total = 0;
	int hits = 0;
	ArrayList<ArrayList<Integer>> list = new ArrayList<>();

	ArrayList<Integer> l = new ArrayList<>();
	int c = 0;
	for (int i = 0; i < a2.size(); i++) {
		if (c < k) {
			l.add(i);

		} else {
			list.add((ArrayList<Integer>) l.clone());
			l = new ArrayList<>();
			c = 0;
			l.add(i);
			System.out.println("LIST internal" + list);

		}
		c = c + 1;
		if (i == a2.size() - 1) // at the last input, we should add the
								// arrayList l
			list.add(l);
	}
	System.out.println("LIST" + list);

	for (int i = 0; i < sub; i++) { // test all inputs in fold i and train
									// all the inputs in (sub-i)
		for (int j = 0; j < list.get(i).size(); j++) {// testing all inputs
														// of i
			String in = a2.get(list.get(i).get(j));
			String out = a3.get(list.get(i).get(j));

			String[] arr1 = in.split(",");
			String[] arr2 = out.split(",");
			ArrayList<Double> inp = new ArrayList<>();
			ArrayList<Double> outp = new ArrayList<>();
			for (int s = 0; s < arr1.length; s++)
				inp.add(Double.parseDouble(arr1[s]));

			for (int s = 0; s < arr2.length; s++)
				outp.add(Double.parseDouble(arr2[s]));

			if (areEqual(outp, getOutput(inp), errfct) < error) { // testing
																	// if
																	// input
																	// is
																	// equal
																	// to
																	// desired
																	// output
				hits++;
				total++;

			} else {
				total++;

			}
		}

		for (int m = 0; m < sub; m++)// training all inputs of sub except i
			if (m != i) {
				for (int j = 0; j < list.get(m).size(); j++) {
					String in = a2.get(list.get(m).get(j));
					String out = a3.get(list.get(m).get(j));

					String[] arr1 = in.split(",");
					String[] arr2 = out.split(",");
					ArrayList<Double> inp = new ArrayList<>();
					ArrayList<Double> outp = new ArrayList<>();
					for (int s = 0; s < arr1.length; s++)
						inp.add(Double.parseDouble(arr1[s]));

					for (int s = 0; s < arr2.length; s++)
						outp.add(Double.parseDouble(arr2[s]));

					train(inp, outp);// train (k-1 folds) --- folds=list(i)

				}
			}

	}
System.out.println("KFOLLLD :"+(hits * 1.0) / total);
	return (hits * 1.0) / total;
}
	private ArrayList<String> shuffleAL (ArrayList<String> input, List<Integer> l ){
		
		ArrayList<String> toReturn = new ArrayList<>();
		
		String [] d = new String [input.size()];
		for(int i=0; i<l.size();i++)
			d[i] = input.get(l.get(i));
		for(int i=0; i<d.length;i++)
			toReturn.add(d[i]);
		return toReturn;
	}
	/*private void save() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("ann.txt"))));
		for(int i=0; i<layers.size();i++) {
			pw.print("Layer"+i);
			for(int j=0; j<layers.get(i).size();j++) {
			pw.print("Perceptron"+j);
			pw.print("Weights:");
			for(int k=0; k<layers.get(i).get(j).weights.size();k++) {
				pw.print(layers.get(i).get(j).weights.get(k)+" ");
			}
			pw.print(layers.get(i).get(j).functionNbr);
			

			}
			pw.println();
		}
		pw.close();
	}*/
	private List<Integer> Randomize(int size){
		int [] nbr = new int [size];
		
		List<Integer> solution = new ArrayList<>();
		for (int i = 0; i < size; i++) {
		    solution.add(i);
		}
		Collections.shuffle(solution);
		return solution;
	}
	public void saveNetwork(String name) throws IOException {
		File f = new File(name+".txt");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		pw.println("Error: "+this.error);
		pw.println("Input#: "+this.input);
		pw.println("Error_Fct: "+this.errfct);
		pw.println("Iteration#: "+this.iteration);
		pw.println("Layers: "+this.layers.size());
		for(int i=0; i<this.layers.size();i++) {
			pw.println("Layer#"+i+" "+this.layers.get(i).size());
			for(int j=0; j<this.layers.get(i).size();j++) {
			Perceptron p = this.layers.get(i).get(j);
			
			pw.print("Perceptron#"+j+" ");
			pw.print(p.inputNbr+" "+p.a_bounded+" "+p.a_guassian+" "+p.a_sigmoid+" "+p.a_unit+" "+p.functionNbr+" "+p.k+" "+p.output+" "+p.sigma+" "+p.threshhold+" "+p.v+" "+p.v0+" "+p.v_max+" "+p.v_min+" ");
			
		
			for(double l : p.weights)
				pw.print(l+" ");
			pw.println();
			
			}
			
			
		}
		pw.close();
	}
	public void loadNetwork(File f) throws FileNotFoundException {
		Scanner s = new Scanner(f);
		
		s.next(); 
		error= s.nextDouble();
		s.next();
		input = s.nextInt();
		s.next();
		errfct = s.nextInt();
		s.next();
		iteration = s.nextInt();
		s.next();
		ArrayList<ArrayList<Perceptron>> l = new ArrayList<>();
		
		int layer =s.nextInt();
		int n=0;
		while(n<layer) {
			ArrayList<Perceptron> lay= new ArrayList<>();
			s.next();
			int o =s.nextInt();
			int r=0;
			while(r<o)
			{
				s.next();
				
				Perceptron p = new Perceptron(s.nextInt());
				p.a_bounded=s.nextDouble();
				p.a_guassian=s.nextDouble();
				p.a_sigmoid=s.nextDouble();
				p.a_unit=s.nextDouble();
				p.functionNbr=s.nextInt();
				p.k=s.nextDouble();
				p.output=s.nextDouble();
				p.sigma=s.nextDouble();
				p.threshhold=s.nextDouble();
				p.v=s.nextDouble();
				p.v0=s.nextDouble();
				p.v_max=s.nextDouble();
				p.v_min=s.nextDouble();
				ArrayList<Double> w = new ArrayList<>();
				while(s.hasNextDouble())
					w.add(s.nextDouble());
				p.weights = w;
			
				r++;
				lay.add(p);
			}
			l.add(lay);
			n++;
			
		}
		this.layers= l;
	}
}
