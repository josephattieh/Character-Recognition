package com.proj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GenerateFeatures {

	public static void main (String [] args) throws Exception {
		
		String s ="C:\\Users\\Joseph Attieh\\Desktop\\Project 2\\A-Z";
		File d = new File("Diagonal.txt");
		File z = new File("Zoning.txt");
		File h = new File("Histogram.txt");
		PrintWriter pwd = new PrintWriter(new BufferedWriter(new FileWriter(d)));
		PrintWriter pwz = new PrintWriter(new BufferedWriter(new FileWriter(z)));
		PrintWriter pwh = new PrintWriter(new BufferedWriter(new FileWriter(h)));
		String a ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		File f = new File(s);
		for(File file: f.listFiles()) {
		
			double[] zone = Features.getZoning(file);
			double[][] diag = Features.getDiagonalFeature(file.toString()); 
			ArrayList<ArrayList<Integer>> hist = Features.ProjectionHistogram(file.toString()); 
		
			pwz.print("(");
			for(int i=0; i<zone.length-1;i++)
				pwz.print(zone[i]+",");
			pwz.print(zone[zone.length-1]+"),(");
			
			int[] arr = new int[a.length()];
			arr[a.indexOf(file.getName().charAt(0))]=1;
			
			for(int i=0; i<arr.length-1;i++)
				pwz.print(arr[i]+",");
			pwz.println(zone[arr.length-1]+");");
			
			pwd.print("(");
			for(int i=0; i<diag.length;i++) {
				for(int j=0; j<diag[i].length;j++) {
					if(!(i== diag.length-1 && j==diag[i].length-1))
					pwd.print(diag[i][j]+",");
				}
			}
			pwd.print(diag[diag.length-1][diag[0].length-1]+"),(");
			
			for(int i=0; i<arr.length-1;i++)
				pwd.print(arr[i]+",");
			pwd.println(zone[arr.length-1]+");");
			
			double last=0;
			pwh.print("(");
			for(int i=0; i<hist.size();i++) {
				for(int j=0; j<hist.get(i).size();j++) {
					if(!(i== hist.size()-1 && j==hist.get(i).size()-1))
					pwh.print(hist.get(i).get(j)+",");
					else 
						last = hist.get(i).get(j);
				}
			}
			pwh.print(last+"),(");
			
			for(int i=0; i<arr.length-1;i++)
				pwh.print(arr[i]+",");
			pwh.println(zone[arr.length-1]+");");
			
			
			}
		pwz.close();

		}
	}

