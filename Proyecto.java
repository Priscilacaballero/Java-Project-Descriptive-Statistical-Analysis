import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
public class Proyecto {
    
    public static void main(String[] args) {
    	
   		
   		DecimalFormat decimal = new DecimalFormat("#.##");
   		int N = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la Cantidad de datos: "));
   		
   		double[] Datos = new double[N];
   		int C = 0;
   		double Max = 0, Min = 0;
   		
   		for(int i = 0; i < N; i++){
   			
   			Datos[i] = Double.parseDouble(JOptionPane.showInputDialog(null, "Teclee el Dato # " + (i+1)));
   
//MAX Y MIN-------------------------------------
  			
   			if(C == 0){
   				
   				Max = Datos[i];
   				Min = Datos[i];
   				C++;
   				
   			}else{
   				
   				if(Datos[i] > Max)
						
						Max = Datos[i];
						
				if(Datos[i] < Min)
						
						Min = Datos[i];
   			}
					
		}
		
//RANGO, TAMAÑO DEL INTERVALO DE CLASE---------------------	
	
		double Rango = 0;
		Rango = (Max - Min);
		double K = 1+3.3*Math.log10(N);
		int Tic = (int)(Rango/K)+1;
		int K2 = (int)K+1;
		double[] Lic = new double[K2];
   		double[] Lsc = new double[K2];
   		
//LIMITE INFERIOR DE CLASE------------------------------------- 
  		
   		Lic[0] = Min;
   		for(int i = 1; i < K2; i++){
   			
   			Lic[i] = Lic[i-1]+Tic;
   		}
   		
   	
   		
//LIMITE SUPERIOR DE CLASE--------------------------------------
   		
   		for(int i = 0; i < K2; i++){
   			
   			Lsc[i] = Lic[i] + Tic;
   		}
   		
   			
   		
//MARCA DE CLASE-------------------------------------------------   
		
   		double[] Xi = new double[K2];
   		
   		for(int i = 0; i < K2; i++){
			
			Xi[i] = (Lic[i] + Lsc[i])/2;
			
		}
		
//FRECUENCIA-----------------------------------------------------
		
		int c1 = 0;
		int[] Fi = new int[K2];
		for(int i = 0; i < K2; i++){
			for(int j = 0; j < N; j++){
				
				if(Datos[j] >= Lic[i] && Datos[j] < Lsc[i]){
				
					Fi[i] = Fi[i] + 1;
					
				}
				
			}
			
		}
		
		
		
//FRECUENCIA ACUMULADA ACENDENTE------------------------------

		double[] Faa = new double[K2];
		Faa[0] = Fi[0];
		double Acu = 0;
		int x = 0;
		
		for(int i = 1; i < K2; i++){
			
			if(x == 0){
				
				Acu = Acu + (Fi[i] + Fi[i-1]);
				Faa[i] = Acu;
				x++;
					
			}else{
				
				Acu = Acu + Fi[i];
				Faa[i] = Acu;	
			}
			
		}
		
//FRECUENCIA ACUMULADA DESCENDENTE------------------------------
		
		Acu = 0;
		x = 0;
		double[] Fad = new double[K2];
		Fad[0] = N - Fi[0];
		
   		for(int i = 1; i < K2; i++){
   			
   			if(x == 0){
   				
   				Acu = Acu + (Fad[i-1] - Fi[i]);
   				Fad[i] = Acu;
   				x++;
   				
   			}else{
   				
   				Acu = Acu - Fi[i];
   				Fad[i] = Acu;
   			}
   			
   		}
   		
   		
//FRECUENCIA RELATIVA-------------------------------------------
		
		double[] Fr = new double[K2];
		for(int i = 0; i < K2; i++){
			
			Fr[i] = (double)Fi[i]/N;
			
		}
		
//FRECUENCIA RELATIVA ACUMULADA ASCENDENTE------------------------------

		double[] Fraa = new double[K2];
		Acu = 0;
		x = 0;
		Fraa[0] = Fr[0];
		
		for(int i = 1; i < K2; i++){
			
			if(x == 0){
				
				Acu = Acu + (Fr[i] + Fr[i-1]);
				Fraa[i] = Acu;
				x++;
				
			}else{
				
				Acu = Acu + Fr[i];
				Fraa[i] = Acu;
				
				
			}
			
		}
		
//FRECUENCIA RELATIVA ACUMULADA DESCENDENTE----------------------

		double[] Frad = new double[K2];
		Acu = 0;
		x = 0;
		Frad[0] = 1 - Fr[0];
		
		for(int i = 1; i < K2; i++){
			
			if(x == 0){
				
				Acu = Acu + (Frad[i-1] - Fr[i]);
				Frad[i] = Acu;
				x++;
				
			}else{
				
				Acu = Acu - Fr[i];
				Frad[i] = Acu;
			}
		}
		
//fi*xi-------------------------------------------------------

		double[] FiXi = new double[K2];
		
		for(int i = 0; i < K2; i++){
			
			FiXi[i] = Fi[i]*Xi[i];
		}
		
//MEDIA---------------------------------------------------------

		Acu = 0; 
		for(int i = 0; i < K2; i++){
			
			Acu = Acu + FiXi[i];
		}
		
		double Media = (double)Acu/N;
		
		
//MODA---------------------------------------------------------

		int Indice = 0;
		int Ma = 0;
		x = 0;
		for(int i = 0; i < K2; i++){
			
			if(x == 0){
				Ma = Fi[i];
				Indice = i;
				x++;
			}else{
				
				if(Fi[i] > Ma){
					
					Ma = Fi[i];
					Indice = i;
				}
					 
			}
		}
		
		double Amplitud = (Lsc[Indice] - Lic[Indice]);
		double LimiteModa = Lic[Indice];
		int Delta1 = (Fi[Indice] - Fi[Indice-1]);
		int Delta2 = (Fi[Indice] - Fi[Indice+1]);
		double FracionModa = (double)(Delta1)/(Delta1 + Delta2);
   		double Moda = (double)(FracionModa * Amplitud) + LimiteModa;
		
		
		
//MEDIANA-------------------------------------------------------


	double Q = N/2;
	Indice = 0;
	if(Q%2 != 0){
		
		Q = (N + 1)/2;
	}
	
	for(int i = 0; i < K2; i++){
		
		if(Faa[i] >= Q){
			
			Indice = i;
			break;
		}
	}
	
	double LimiteMediana = Lic[Indice];
	double FrecuenciaA = Faa[Indice - 1];
	double Mediana = (double)LimiteMediana + ((Q-FrecuenciaA)/(Fi[Indice]))*Amplitud;
	
	
//M1-----------------------------------------------------------

	double[] M1 = new double[K2];
	
	for(int i = 0; i < K2; i++){
		
		M1[i] = Fi[i]*(Xi[i]-Media);
	}
	
//M2-----------------------------------------------------------
	
	double[] M2 = new double[K2];
	
	for(int i = 0; i < K2; i++){
		
		double a = Xi[i]-Media;
		M2[i] = Fi[i]*Math.pow(a, 2);
	}
	
//M3-----------------------------------------------------------
	
	double[] M3 = new double[K2];
	
	for(int i = 0; i < K2; i++){
		
		double a = Xi[i]-Media;
		M3[i] = Fi[i]*Math.pow(a, 3);
	}
	
//M4-----------------------------------------------------------
	
	double[] M4 = new double[K2];
	
	for(int i = 0; i < K2; i++){
		
		double a = Xi[i]-Media;
		M4[i] = Fi[i]*Math.pow(a, 4);
	}
	
//VARIANZA----------------------------------------------------------

	double A = 0;
	
	for(int i = 0; i < K2; i++){
		
		A = A + M2[i];
	}
	
	double Varianza = A/N;
	
//DESVIACION TIPICA--------------------------------------------------

	double s = Math.sqrt(Varianza);
	
//COEFICIENTE DE PEARSON 1------------------------------------------

	double P1 = (Media - Moda)/s;
					
//COEFICIENTE DE PEARSON 2------------------------------------------

	double P2 = (3*(Media - Mediana))/s;
//MOMENTO 1---------------------------------------------------------
	double Am1 = 0;
	for(int i = 0; i < K2; i++){
		
		Am1 = Am1 + M1[i];
		
	}
	
	double m1 = Am1/N;

//MOMENTO 2---------------------------------------------------------
	double Am2 = 0;
	for(int i = 0; i < K2; i++){
		
		Am2 = Am2 + M2[i];
		
	}
	
	double m2 = Am2/N;

//COEFICIENTE DE FISHER G1------------------------------------------

	double Am3 = 0;
	
	for(int i = 0; i < K2; i++){
		
		Am3 = Am3 + M3[i];
		
	}
	
	double m3 = Am3/N;
	double G1 = Am3/Math.pow(s, 3);
	
//COEFICIENTE DE FISHER G2------------------------------------------

	double Am4 = 0;
	
	for(int i = 0; i < K2; i++){
		
		Am4 = Am4 + M4[i];
		
	}
	
	double m4 = Am4/N;
	double G2 = (m4/Math.pow(s, 4))-3;
	
			
//IMPRESION-----------------------------------------------------
		
		System.out.println("MEDIA: " + Media);
		System.out.println("MODA: " + decimal.format(Moda));
		System.out.println("MEDIANA: " + Mediana + "\n\n");
		System.out.println("MOMENTOS:");
		System.out.println("m1: " + decimal.format(m1));
		System.out.println("m2: " + decimal.format(m2));
		System.out.println("m3: " + decimal.format(m3));
		System.out.println("m4: " + decimal.format(m4) + "\n\n");
		System.out.println("VARIANZA: " + Varianza);
		System.out.println("DESVIACION TIPICA: " + decimal.format(s) + "\n\n");
		System.out.println("COEFICIENTES DE PERSON:");
		System.out.println("P1: " + decimal.format(P1));
		System.out.println("P2: " + decimal.format(P2) + "\n\n");
		System.out.println("COEFICIENTE DE FISHER:");
		System.out.println("G1: " + decimal.format(G1));
		System.out.println("G2: " + decimal.format(G2) + "\n\n");
		
		
			
   		System.out.printf("%12s%12s%12s%12s%12s%12s%12s%12s%12s%12s%20s%20s%20s%20s\n", "LIC", "LSC", "XI", "Fi", "Faa", "Fad", "Fr", "Fraa", "Frad", "FiXi", "fi*(xi-MED)^1", "fi*(xi-MED)^2", "fi*(xi-MED)^3", "fi*(xi-MED)^4");
   		for(int i = 0; i < K2; i++){
   			
   			System.out.printf("%12.2f%12.2f%12.2f%12d%12.2f%12.2f%12.2f%12.2f%12.2f%12.2f%20.2f%20.2f%20.2f%20.2f\n", Lic[i], Lsc[i], Xi[i], Fi[i], Faa[i], Fad[i], Fr[i], Fraa[i], Frad[i], FiXi[i], M1[i], M2[i], M3[i], M4[i]);
   		}
   		
   		System.out.println("\n\n");
   		if(G2 == 0){
		
		System.out.println("MESOCURTICA.");
		
		}else if(G2 > 0){
			
			System.out.println("LEPTOCURTICA.");
			
		}else if(G2 < 0){
			
			System.out.println("PLATICURTICA.");
		}

   		
//ASIMETRIA-----------------------------------------------------

	if(Mediana > Media && Mediana < Moda){
		
		System.out.println("ASIMETRIA NEGATIVA.");
		
	}else if(Mediana == Media && Mediana == Moda){
		
		System.out.println("SIMETRICA.");
		
	}else if(Mediana < Media && Mediana > Moda){
		
		System.out.println("ASIMETRIA POSITIVA.");
	}else{
		
		System.out.println("NO HAY SIMETRIA.\n\n");
	}
		
				
		
   	}  
}
