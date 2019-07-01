package util;


import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

import util.FileManager;

// To recognize the dialog to form Markov's chain
//===============================================
public class InputDialog {
	
	public ArrayList<String> gList = new ArrayList<String>();
	public ArrayList<String> iList = new ArrayList<String>();
	public ArrayList<String> oList = new ArrayList<String>();
	public ArrayList<String> tList = new ArrayList<String>();
	
	public ArrayList<String> oListAll = new ArrayList<String>();
	public ArrayList<String> iListAll = new ArrayList<String>();
	
	
	private int row = 200;
	private int col = 200;
	private String fileStr = "";
	private int[] mScoreI; //array of input phrases into dialog (phrase1 - action)
	private int[] mScoreO; //array of input phrases into dialog (phrase2 - reaction)
	private int[][] mScoreIO; //array of quantity of the input phrases (phrase1) and its transitions to phrase2
	private int[][] mScoreOI; //array of quantity of the output phrases (phrase2) and its transitions to phrase1
	private int[][] mScoreII; //array of quantity of the input phrases (phrase1) and its transitions to phrase1
	private int[][] mScoreOO; //array of quantity of the output phrases (phrase2) and its transitions to phrase2
	private double[][] mPIO; //array of probability of the input phrases (phrase1) and its transitions to phrase2
	private double[][] mPOI; //array of probability of the output phrases (phrase2) and its transitions to phrase1
	private double[][] mPII; //array of probability of the input phrases (phrase1) and its transitions to phrase1
	private double[][] mPOO; //array of probability of the output phrases (phrase2) and its transitions to phrase2
	private double p_input = 1.0; //quantity of input phrases in generally
	private double p_output = 1.0; //quantity of output phrases in generally
	private int q_input = 1; //probability of appearance of transitions to input phrase1 
	private int q_output = 1; //probability of appearance of transitions to output phrase2

	private int[] mScoreIAll; //for all
	private int[] mScoreOAll; //for all 
	private int[][] mScoreIOAll; //for all
	private int[][] mScoreOIAll; //for all 
	private int[][] mScoreIIAll; //for all 
	private int[][] mScoreOOAll; //for all 
	private double[][] mPIOAll; //for all 
	private double[][] mPOIAll; //for all 
	private double[][] mPIIAll; //for all 
	private double[][] mPOOAll; //for all 
	private int qa_input = 1; //probability of appearance of transitions to input phrase1 for all
	private int qa_output = 1; //probability of appearance of transitions to output phrase2 for all
	
	private	String[][] arrayMarkov; 
	
	// Initialization of data
	//===========================================
	public InputDialog()
	{
		for(int i=0; i<2; i++) {
			readFile();
			setMarkovScore(iList, oList, gList);
//			toFillData();
			toAddNewDialog();
			writeFile();
		}
//		writeAllFile();
		
	}
	
	// Main 
	//===========================================
	public static void main(String[] args) {
		new InputDialog();
		
	}
	
	public void toFillData(){
		
		
	}
	
	// To add new dialog for analysis
	//===========================================
	public void toAddNewDialog(){
		int lthR1, lthR2, lthC1, lthC2 = 0;
		int ind1, ind2 = 0;
		
		lthR1 = iList.size();
		lthR2 = iList.size();
		lthC1 = iListAll.size();
		lthC2 = iListAll.size();
		
		for (int i=0; i<iList.size(); i++)
			if (iListAll.indexOf(iList.get(i).toString()) == -1) { iListAll.add(iList.get(i).toString()); }

		for (int i=0; i<oList.size(); i++)
			if (oListAll.indexOf(oList.get(i).toString()) == -1) { oListAll.add(oList.get(i).toString()); }

		mScoreIOAll = new int[iListAll.size()][oListAll.size()];
		mScoreOIAll = new int[oListAll.size()][iListAll.size()];
		mScoreIIAll = new int[iListAll.size()][iListAll.size()];
		mScoreOOAll = new int[oListAll.size()][oListAll.size()];
		
		
		for (int i=0; i<iList.size(); i++) {
			ind1 = iListAll.indexOf(iList.get(i).toString());
			for (int j=0; j<iList.size(); j++){
				if(mScoreII[i][j]==0) {continue;}
				ind2 = iListAll.indexOf(iList.get(j).toString());
				mScoreIIAll[ind1][ind2] = mScoreII[i][j];
			}
		}
		
		for (int i=0; i<oList.size(); i++) {
			ind1 = oListAll.indexOf(oList.get(i).toString());
			for (int j=0; j<mScoreOO[0].length; j++){
				if(mScoreOO[i][j]==0) {continue;}
				ind2 = oListAll.indexOf(oList.get(j).toString());
				mScoreOOAll[ind1][ind2] = mScoreOO[i][j];
			}
		}
		
		for (int i=0; i<oList.size(); i++) {
			ind1 = oListAll.indexOf(oList.get(i).toString());
			for (int j=0; j<mScoreOI[0].length; j++){
				if(mScoreOI[i][j]==0) {continue;}
				ind2 = iListAll.indexOf(iList.get(j).toString());
				mScoreOIAll[ind1][ind2] = mScoreOI[i][j];
			}
		}
		
		for (int i=0; i<iList.size(); i++) {
			ind1 = iListAll.indexOf(iList.get(i).toString());
			for (int j=0; j<mScoreIO[0].length; j++){
				if(mScoreIO[i][j]==0) {continue;}
				ind2 = oListAll.indexOf(oList.get(j).toString());
				mScoreIOAll[ind1][ind2] = mScoreIO[i][j];
			}
		}
		
		
		
		for (int i=0; i<oListAll.size(); i++) {
			System.out.print("\t"+oListAll.get(i).toString());
			System.out.println();
		}	
		System.out.println("\n\n\n");

		for (int i=0; i<iListAll.size(); i++) {
			System.out.print("\t"+iListAll.get(i).toString());
			System.out.println();
		}	

			
	}
	
	// To make the assessments for Markov's chain
	//===========================================
	public void setMarkovScore(ArrayList inList, ArrayList outList, ArrayList genList){
		inList = DelDupl(inList); 
		outList = DelDupl(outList); 

		int ilength = inList.size();
		int olength = outList.size();
		int glength = genList.size();
		String tStr1, tStr2 = "";
		int is1i, is1o, is2i, is2o = 0;

		mScoreI = new int[ilength];
		mScoreO = new int[olength];
		mScoreIO = new int[ilength][olength];
		mScoreOI = new int[olength][ilength];
		mScoreII = new int[ilength][ilength];
		mScoreOO = new int[olength][olength];
		mPIO = new double[ilength][olength];
		mPOI = new double[olength][ilength];
		mPII = new double[ilength][ilength];
		mPOO = new double[olength][olength];
		for (int i=0; i<ilength; i++)	for (int j=0; j<olength; j++)	{ mScoreIO[i][j] = 0; }
		for (int i=0; i<olength; i++)	for (int j=0; j<ilength; j++)	{ mScoreOI[i][j] = 0; }
		for (int i=0; i<ilength; i++)	for (int j=0; j<ilength; j++)	{ mScoreII[i][j] = 0; }
		for (int i=0; i<olength; i++)	for (int j=0; j<olength; j++)	{ mScoreOO[i][j] = 0; }
			for (int i=0; i<ilength; i++)	{ mScoreI[i] = 0; }
			for (int i=0; i<olength; i++)	{ mScoreO[i] = 0; }
		
		for (int i=1; i<glength; i++){
			tStr1 = genList.get(i-1).toString();
			tStr2 = genList.get(i).toString();
				is1i = inList.indexOf(tStr1.subSequence(3, tStr1.length()));
				is2i = inList.indexOf(tStr2.subSequence(3, tStr2.length()));
					is1o = outList.indexOf(tStr1.subSequence(3, tStr1.length()));
					is2o = outList.indexOf(tStr2.subSequence(3, tStr2.length()));
					
			if (is1i>=0) {mScoreI[is1i] +=1;}
			if (is1o>=0) {mScoreO[is1o] +=1;}
			
			if (tStr1.contains(">>") && tStr2.contains(">>")) {
				mScoreII[is1i][is2i] +=1; 
			}
			if (tStr1.contains("<<") && tStr2.contains("<<")) {
				mScoreOO[is1o][is2o] +=1; 
			}
			if (tStr1.contains(">>") && tStr2.contains("<<")) {
				mScoreIO[is1i][is2o] +=1; 
			}
			if (tStr1.contains("<<") && tStr2.contains(">>")) {
				mScoreOI[is1o][is2i] +=1; 
			}
			
		}
		
		// To get probability criteria
		q_input = mScoreI.length;
		q_output = mScoreO.length;
		p_input = 1.0 / q_input;
		p_output = 1.0 / q_output;
				
		for(int i=0; i<q_input; i++){
			for(int j=0; j<q_output; j++){
//				mPIO[i][j] = getBernully(mScoreIO[i][j], q_input, p_input);
				mPIO[i][j] = normValue(mScoreIO[i][j], q_input);
				System.out.print(mPIO[i][j]+"\t");
			}
			System.out.println();
		}
		
		for(int i=0; i<q_output; i++){
			for(int j=0; j<q_input; j++){
//				mPOI[i][j] = getBernully(mScoreOI[i][j], q_output, p_output);
				mPOI[i][j] = normValue(mScoreOI[i][j], q_output);
				System.out.print(mPOI[i][j]+"\t");
			}
			System.out.println();
		}
		
		for(int i=0; i<q_input; i++){
			for(int j=0; j<q_input; j++){
//				mPII[i][j] = getBernully(mScoreII[i][j], q_input, p_input);
				mPII[i][j] = normValue(mScoreII[i][j], q_input);
				System.out.print(mPII[i][j]+"\t");
			}
			System.out.println();
		}
		
		for(int i=0; i<q_output; i++){
			for(int j=0; j<q_output; j++){
//				mPOO[i][j] = getBernully(mScoreOO[i][j], q_output, p_output);
				mPOO[i][j] = normValue(mScoreOO[i][j], q_output);
				System.out.print(mPOO[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	
	// Normalize the data transformation
	//=========================================================================
	public double normValue(int a, int b){
//		double temp = (b-a) / (b*1.0);
		double temp = 1/ ((b-a) / (b*1.0))-1;
		return temp;		
	};
	
	// Factorial
	//=========================================================================
    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n-1);
    }
	
	// Calculate the Bernully formula
	//=========================================================================
	private double getBernully(int a, int b, double p){
		double t1, t2, t3, f = 0.0;
		f = factorial(a);
		t1 = factorial(b) / (f * factorial(b-a));
		t2 = Math.pow(p, a);
		t3 = Math.pow((1-p), (b-a));
		t1 = t1 * t2 * t3;
		return t1;
	};
		
	
	// To make input data distribution
	//===========================================
	public void setInputData(String id){
//    	if((id.contains(">>")) && (!id.contains(">!>"))) iList.add(id.substring(3, id.length()));
//    	if((id.contains("<<")) && (!id.contains(">!>"))) oList.add(id.substring(3, id.length()));
    	if(id.contains(">>")) iList.add(id.substring(3, id.length()));
    	if(id.contains("<<")) oList.add(id.substring(3, id.length()));
        gList.add(id);  
	}

	//To delete duplication and to sort after the list of array
	//===========================================
	public ArrayList<String> DelDupl(ArrayList<String> array){
        ArrayList<String> result = new ArrayList<String>(new HashSet<String>(array));
        Collections.sort(result);
//        System.out.println(result);
        return result;
    }	
	
	// To read file by FileManager
	//===========================================
	public void readFile(){
		fileStr = FileManager.main("Open file with dialog");
		BufferedReader reader = null;
		try {
		    reader = new BufferedReader(new FileReader(fileStr));
		    String textLine = null;
		    while ((textLine = reader.readLine()) != null) {
		    	setInputData(textLine);
//		        System.out.println(textLine);
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}
	}

	// To write Markov's data to file
	//===========================================
	public void writeFile(){
		fileStr = FileManager.main("Save the assessments to file");
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter(new FileWriter(fileStr));
		    writer.write("==Input=phrases=="+"\n");
		    for(int i=0; i<iList.size(); i++)
		    	writer.write(iList.get(i).toString()+"\n");
		    iList.clear();

		    writer.write("\n==Input=phrases==quantity=="+"\n");
		    for(int i=0; i<mScoreI.length; i++)
		    	writer.write(mScoreI[i]+" ");
		    for(int i=0; i<mScoreI.length; i++) mScoreI[i] = 0;

		    writer.write("\n==Output=phrases=="+"\n");
		    for(int i=0; i<oList.size(); i++)
		    	writer.write(oList.get(i).toString()+"\n");
		    oList.clear();

		    writer.write("\n==Output=phrases==quantity=="+"\n");
		    for(int i=0; i<mScoreO.length; i++)
		    	writer.write(mScoreO[i]+" ");
		    for(int i=0; i<mScoreO.length; i++) mScoreO[i] = 0;

		    writer.write("\n==Input=Output=transitions=probability=="+"\n");
		    for(int i=0; i<mPIO.length; i++) {
			    for(int j=0; j<mPIO[0].length; j++)
			    	writer.write(mPIO[i][j]+"\t");
			    writer.write("\n");
		    }    
		    for(int i=0; i<mPIO.length; i++) for(int j=0; j<mPIO[0].length; j++) mPIO[i][j]=0;

		    writer.write("\n==Output=Input=transitions=probability=="+"\n");
		    for(int i=0; i<mPOI.length; i++) {
			    for(int j=0; j<mPOI[0].length; j++)
			    	writer.write(mPOI[i][j]+"\t");
			    writer.write("\n");
		    }    
		    for(int i=0; i<mPOI.length; i++) for(int j=0; j<mPOI[0].length; j++) mPOI[i][j]=0;
		    
		    writer.write("\n==Input=Input=transitions=probability=="+"\n");
		    for(int i=0; i<mPII.length; i++) {
			    for(int j=0; j<mPII[0].length; j++)
			    	writer.write(mPII[i][j]+"\t");
			    writer.write("\n");
		    }    
		    for(int i=0; i<mPII.length; i++) for(int j=0; j<mPII[0].length; j++) mPII[i][j]=0;
		    
		    writer.write("\n==Output=Output=transitions=probability=="+"\n");
		    for(int i=0; i<mPOO.length; i++) {
			    for(int j=0; j<mPOO[0].length; j++)
			    	writer.write(mPOO[i][j]+"\t");
			    writer.write("\n");
		    }    
		    for(int i=0; i<mPOO.length; i++) for(int j=0; j<mPOO[0].length; j++) mPOO[i][j]=0;
		    
		    writer.write("\n==Input=Input=transitions=="+"\n");
		    for(int i=0; i<mScoreII.length; i++) {
			    for(int j=0; j<mScoreII[0].length; j++)
			    	writer.write(mScoreII[i][j]+"\t");
			    writer.write("\n");
		    }    
		    for(int i=0; i<mScoreII.length; i++) for(int j=0; j<mScoreII[0].length; j++) mScoreII[i][j]=0;
		    
		    writer.write("\n==Input=Output=transitions=="+"\n");
		    for(int i=0; i<mScoreIO.length; i++) {
			    for(int j=0; j<mScoreIO[0].length; j++)
			    	writer.write(mScoreIO[i][j]+"\t");
			    writer.write("\n");
		    }    
		    for(int i=0; i<mScoreIO.length; i++) for(int j=0; j<mScoreIO[0].length; j++) mScoreIO[i][j]=0;
		    
		    writer.write("\n==Output=Intput=transitions=="+"\n");
		    for(int i=0; i<mScoreOI.length; i++) {
			    for(int j=0; j<mScoreOI[0].length; j++)
			    	writer.write(mScoreOI[i][j]+"\t");
			    writer.write("\n");
		    }    
		    for(int i=0; i<mScoreOI.length; i++) for(int j=0; j<mScoreOI[0].length; j++) mScoreOI[i][j]=0;
		    
		    writer.write("\n==Output=Output=transitions=="+"\n");
		    for(int i=0; i<mScoreOO.length; i++) {
			    for(int j=0; j<mScoreOO[0].length; j++)
			    	writer.write(mScoreOO[i][j]+"\t");
			    writer.write("\n");
		    }    
		    for(int i=0; i<mScoreOO.length; i++) for(int j=0; j<mScoreOO[0].length; j++) mScoreOO[i][j]=0;
		    
		}
		catch ( IOException e)	{}
		finally
		{        
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}
	
	
	// To write Markov's data to file for all terms
	//===========================================
	public void writeAllFile(){
		fileStr = FileManager.main("Save the assessments to file");
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter(new FileWriter(fileStr));
		    writer.write("==Input=phrases=="+"\n");
		    for(int i=0; i<iListAll.size(); i++)
		    	writer.write(iListAll.get(i).toString()+"\n");

		    writer.write("\n==Input=phrases==quantity=="+"\n");
		    for(int i=0; i<mScoreIAll.length; i++)
		    	writer.write(mScoreIAll[i]+" ");

		    writer.write("\n==Output=phrases=="+"\n");
		    for(int i=0; i<oListAll.size(); i++)
		    	writer.write(oListAll.get(i).toString()+"\n");
		    
		    writer.write("\n==Output=phrases==quantity=="+"\n");
		    for(int i=0; i<mScoreOAll.length; i++)
		    	writer.write(mScoreOAll[i]+" ");

		    writer.write("\n==Input=Output=transitions=probability=="+"\n");
		    for(int i=0; i<mPIOAll.length; i++) {
			    for(int j=0; j<mPIOAll[0].length; j++)
			    	writer.write(mPIOAll[i][j]+"\t");
			    writer.write("\n");
		    }    
		    
		    writer.write("\n==Output=Input=transitions=probability=="+"\n");
		    for(int i=0; i<mPOIAll.length; i++) {
			    for(int j=0; j<mPOIAll[0].length; j++)
			    	writer.write(mPOIAll[i][j]+"\t");
			    writer.write("\n");
		    }    
		    
		    writer.write("\n==Input=Input=transitions=probability=="+"\n");
		    for(int i=0; i<mPIIAll.length; i++) {
			    for(int j=0; j<mPIIAll[0].length; j++)
			    	writer.write(mPIIAll[i][j]+"\t");
			    writer.write("\n");
		    }    
		    
		    writer.write("\n==Output=Output=transitions=probability=="+"\n");
		    for(int i=0; i<mPOOAll.length; i++) {
			    for(int j=0; j<mPOOAll[0].length; j++)
			    	writer.write(mPOOAll[i][j]+"\t");
			    writer.write("\n");
		    }    
		    
		    writer.write("\n==Input=Input=transitions=="+"\n");
		    for(int i=0; i<mScoreIIAll.length; i++) {
			    for(int j=0; j<mScoreIIAll[0].length; j++)
			    	writer.write(mScoreIIAll[i][j]+"\t");
			    writer.write("\n");
		    }    
		    
		    writer.write("\n==Input=Output=transitions=="+"\n");
		    for(int i=0; i<mScoreIOAll.length; i++) {
			    for(int j=0; j<mScoreIOAll[0].length; j++)
			    	writer.write(mScoreIOAll[i][j]+"\t");
			    writer.write("\n");
		    }    
		    
		    writer.write("\n==Output=Intput=transitions=="+"\n");
		    for(int i=0; i<mScoreOIAll.length; i++) {
			    for(int j=0; j<mScoreOIAll[0].length; j++)
			    	writer.write(mScoreOIAll[i][j]+"\t");
			    writer.write("\n");
		    }    
		    
		    writer.write("\n==Output=Output=transitions=="+"\n");
		    for(int i=0; i<mScoreOIAll.length; i++) {
			    for(int j=0; j<mScoreOOAll[0].length; j++)
			    	writer.write(mScoreOOAll[i][j]+"\t");
			    writer.write("\n");
		    }    
		    
		}
		catch ( IOException e)	{}
		finally
		{        
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}
	
	
	
	
}
