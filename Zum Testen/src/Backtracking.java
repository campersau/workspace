
import java.lang.Math;

public class Backtracking {

	double Epsilon; 		// Lernrate

	int AnzEingabeschicht  = 2; 	// Anzahl der Neuronen in der Eingabeschicht
	int AnzZwischenschicht = 2; 	// Anzahl der Neuronen in der Zwischenschicht
	int AnzAusgabeschicht  = 1; 	// Anzahl der Neuronen in der Ausgabeschicht  	
	int Steps;			// Traningscyclus  	

	double W_ij[][];  		// Gewichtsmatrix Eingabeschicht(i) - Zwischenschicht(j)
	double W_jk[][];  		// Gewichtsmatrix Zwischenschicht(j) - Ausgabeschicht(k)
	double Biases_j[];		// Biases von Zwischenschicht(j)
	double Biases_k[];		// Biases von Ausgabeschicht(k)

	double O_i[];  			// Eingangswerte(i)
	double O_j[];  			// Zwischenwerte(j)
	double O_k[];  			// Ausgangswerte(k)

	double D_j[];     		// Fehlerrate(Delta) der Zwischenschicht(j)
	double D_k[];    		// Fehlerrate(Delta) der Ausgabeschicht(k)

	double T_k[];			// Gewünschter Output(Target) der Augabeschicht(k) 

	public void Ruecksetzen() 
	{
		Steps	= 0;

		// Rücksetzten der Neuronen 
		for (int i = 0; i < AnzEingabeschicht; i++){
			O_i[i] =0.0;
		}
		for (int i = 0; i < AnzZwischenschicht; i++){
			O_j[i]=0.0;
		}
		for (int i = 0; i < AnzAusgabeschicht; i++){
			O_k[i]  =0.0;
		}

		// Setzen der Gewichte zwischen Eingabeschicht und Zwischenschicht
		// auf eine Zufallszahl zwischen 0.0 und 1.0
		for (int i = 0; i < AnzZwischenschicht; i++)
		{    	
			for (int j = 0; j < AnzEingabeschicht; j++)
			{	    		
				W_ij[i][j] = ((java.lang.Math.random() * 2)-1);
			}
			// Setzen der Biaswerte der Zwischenschicht
			Biases_j[i] =((java.lang.Math.random() * 2)-1);
		}

		// Setzen der Gewichte zwischen Zwischenschicht und Ausgabeschicht
		// auf eine Zufallszahl zwischen 0.0 und 1.0
		for (int i = 0; i < AnzAusgabeschicht; i++) 
		{
			for (int j = 0; j < AnzZwischenschicht; j++)
			{
				W_jk[i][j] = ((java.lang.Math.random() * 2) -1);
			}
			// Setzen der Biaswerte der Ausgabeschicht
			Biases_k[i] =((java.lang.Math.random() * 2)-1);
		}	    
	} 


	// Als Aktivierungsfunktion wird hier die n tangens hyperbolicus Funktion verwendet! 	
	public double tanh_Aktivieren(double X)
	{
		double toDegrees = Math.tanh(X);
		return toDegrees ;
	}

	public void Feedforward() 
	{
		double net_j;
		double net_k;

		// -------------------- Eingabeschicht -> Zwischenschicht --------------------
		for (int i=0; i < AnzZwischenschicht; i++) 
		{
			net_j = 0.0;
			for (int j=0; j < AnzEingabeschicht; j++)
			{
				net_j = net_j + (O_i[j] * W_ij[i][j]);
			}			
			O_j[i]= tanh_Aktivieren(net_j+Biases_j[i]);
		}

		// -------------------- Zwischenschicht -> Ausgabeschicht ------------------
		for (int i=0; i < AnzAusgabeschicht; i++) 
		{
			net_k = 0.0;
			for (int j=0; j < AnzZwischenschicht; j++)
			{
				net_k = net_k + (O_j[j] * W_jk[i][j]);
			}
			O_k[i]= tanh_Aktivieren(net_k+Biases_k[i]);   
		}
	}

	public void Feedbackward() 
	{  

		// Fehlerrate(D_k) für die Ausgabeschicht berechnen
		//  ----------------------------------------
		// |   D_k =(T_k - O_k) * O_k * (1 - O_k)   |
		//  ----------------------------------------		
		for (int i= 0; i < AnzAusgabeschicht; i++)
		{
			D_k[i] = (T_k[i] - O_k[i]) * O_k[i] * (1 - O_k[i]);
		}     	

		// Summe der Gewichte für die Ausgabeschicht berechnen
		//  -----------------------------------------
		// |  Summe(W_jk) = W_jk + E * D_k * O_k     |
		//  -----------------------------------------  
		// und neuen Wert fuer die Biases_k berechnen
		//  -------------------------------------------
		// |  Summe(Biases_k) = Biases_k + E * D_k      |
		//  ------------------------------------------- 		  	
		for (int i= 0; i < AnzAusgabeschicht; i++) 
		{
			for (int j= 0; j < AnzZwischenschicht; j++)
			{
				W_jk[i][j] = W_jk[i][j] + (Epsilon * D_k[i] * O_j[j]);      	
			}	      
			Biases_k[i] = Biases_k[i] + (Epsilon * D_k[i]);
		}  	

		// Fehlerrate(D_j) für die Zwischenschicht berechnen
		//  ----------------------------------------------
		// |  D_j = O_j * (1 - O_j) * Summe(D_k * W_jk)   |
		//  ----------------------------------------------  
		for (int i= 0; i < AnzZwischenschicht; i++) 
		{
			D_j[i]=0;
			for (int j= 0; j < AnzAusgabeschicht; j++)
			{
				D_j[i]=D_j[i] + ( D_k[j] * W_jk[j][i]);
			}
			D_j[i]=D_j[i] * O_j[i] * (1 - O_j[i]);
		}      	

		// Summe der Gewichte für die Zwischenschicht berechnen
		//  -----------------------------------------
		// |  Summe(W_ij) = W_ij + E * D_j * O_j     |
		//  -----------------------------------------  
		// und neuen Wert fuer die Biases_j berechnen
		//  -------------------------------------------
		// |  Summe(Biases_j) = Biases_j + E * D_j     |
		//  ------------------------------------------- 		  	
		for (int i= 0; i < AnzZwischenschicht; i++) 
		{
			for (int j= 0; j < AnzEingabeschicht; j++)
			{
				W_ij[i][j]= W_ij[i][j] + (Epsilon * D_j[i] * O_i[j]);
			}
			Biases_j[i] = Biases_j[i] + (Epsilon * D_j[i]);
		}
	}

	public void Lernen() 
	{    
		Steps++;
		Feedforward();
		Feedbackward();
	}

}
