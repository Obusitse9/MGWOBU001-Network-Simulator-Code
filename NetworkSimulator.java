
import java.util.Scanner;
/**
 *Bandwidth 1Allocation Schemes in 5G wireless network
 *@author Obusitse Mogwera
 *@supervisor Dr Olabisi Falowo
 */

public class NetworkSimulator
{
	public static void main(String[] args)
	{Scanner input = new Scanner(System.in);
   System.out.println("Welcome to the network simulator!\n");
   
		System.out.println("Choose bandwidth allocation strategy:");
		System.out.println("1=Complete sharing  2=Guard channel 3= Complete partitioning");
	    int h=input.nextInt();
	    
	    while (h!=1 && h!=2 && h!=3)
	    {
	    	System.out.println("Chosen bandwidth allocation strategy is not available, choose again below");
         System.out.println("1=Complete sharing  2=Guard channel 3= Complete partitioning");
         h=input.nextInt();}
         
       Double RAT = 0.0;
        if (h!=4)
    	{	System.out.println("Select the RAT based on service intended to simulate!");
         System.out.println("RAT 1=VOICE, RAT 2=VOICE AND VIDEO, RAT 3= VOICE,VIDEO AND DATA!");
        	RAT=input.nextDouble();
        	while (RAT>3 || RAT<1)
        	{System.out.println("Select the RAT based on service intended to simulate!");
         System.out.println("RAT 1=VOICE, RAT 2=VOICE AND VIDEO, RAT 3= VOICE,VIDEO AND DATA!"); RAT=input.nextDouble();}
        }
	   
	    
	    
	    
	    
	    if (h==2)
	    {	System.out.println("Guard Channel was selected !\n");
	    
	        if (RAT==1)
	        {	System.out.println("Enter the capacity for RAT 1:");
	            double c1 = input.nextDouble();
	           
	         double constant= 0.1;
	         
	            System.out.println("Enter the threshold:");
	            double t1 =input.nextDouble();
	           	System.out.println("Enter the Call arrival rate for voice (calls/minute)");
	            double A= input.nextDouble();
	             System.out.println("Enter the Call departure rate for voice (calls/minute)");
	            double B= input.nextDouble();
               System.out.println("Input the bbu Required:");
	             double bbu=input.nextDouble();
                 while (B>A)
	                {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	                   B=input.nextDouble();}
                 
	            double loading_hand = (1-constant)*(A/B),loading_new = (A/B);
               double cc=c1, Ui=0, G = 0;
	            {for(int n1=0; n1<=t1;n1++)
                {
                  for(int h1=0; h1<=cc;h1++)
                  {
	                        double eN= Math.pow(loading_new, n1);
	                        double eH= Math.pow(loading_hand, h1);
	                        double prob;
							      if(loading_hand!=0)
	                            prob=eN*eH;
	                        else
	                            prob=eN;
                              
	                        double den=(factorial(n1))*(factorial(h1));
	                        double probs=(prob/den); 
	                        G+=probs;
	                        
                           Ui= Ui+ probs*(n1+h1);
                           } cc--;}}
               double U = (bbu*Ui/G);
               
               int n1 = 0, h1 = 0; 
	            G = 0;
	            double prob_drop = 0, prob_block = 0;
	            while (n1 < t1)
	            {   n1 = n1 + 1;
	                h1 = 0;
	                while (h1 < c1)
	                {   h1 = h1 + 1;
	                    double condition1 = bbu * (n1+h1);
	                    double condition2 = bbu * (n1);
	                    if (condition1 <= c1 && condition2 <= t1)
	                    {   double eN= Math.pow(loading_new, n1);
	                        double eH= Math.pow(loading_hand, h1);
	                        double prob;
							      if(loading_hand!=0)
	                            prob=eN*eH;
	                        else
	                            prob=eN;
	                        double den=(factorial(n1))*(factorial(h1));
	                        double probs=(prob/den); 
	                        G+=probs;
	                        
	                        if ( bbu *( n1+h1 +1) > t1 )
	                           {prob_block+= probs;}
	                        if(loading_hand!=0)    
	                        {   if( bbu *( n1+h1 )>(c1 -bbu ))
	                               {prob_drop+=probs;}}
	                        else
	                            prob_drop=0;}}}
	            if(G==0)
	            {   prob_drop=1;
	                prob_block=1;
	                G=1;}                            
	           
	            System.out.println("c1 : "+ c1);
	            System.out.println("t1 : "+ t1);
	            System.out.println("bbu : "+ bbu);
	          
	            System.out.println("RAT 1 blocking probability: "+( prob_block/G ));
	            System.out.println("RAT 1 dropping probability:  "+( prob_drop/G ));
	            System.out.println("RAT 1 average Utilization: "+ U);
	        }            
	        else if( RAT==2)
	        {   System.out.println("Enter the capacity for RAT 2:");
	        	   double c= input.nextDouble();  
	       
	            double c2= c*0.5;//bandwidth share for voice
               double c1=c*0.5;  //bandwidth share for video
	               
	               System.out.println("Enter the Call arrival rate for voice (calls/minute)");
	                double A1= input.nextDouble();
                   System.out.println("Enter the Call arrival rate for video (calls/minute)");
	                double A2= input.nextDouble();
	                
                   System.out.println("Enter the Call departure rate for voice (calls/minute)");
	                double B1= input.nextDouble();
                   System.out.println("Enter the Call departure rate for video (calls/minute)");
	                double B2= input.nextDouble();
                   System.out.println("Input the bbu Required:");
	                double bbu=input.nextDouble();
                   
	                  double constant= 0.1;
         
	    
	    while (B1>A1)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B1=input.nextDouble();}
           while (B2>A2)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B2=input.nextDouble();}

	           
	            System.out.println("Enter the threshold of voice call:");
	            double t1=input.nextDouble();
	            System.out.println("Enter the threshold of video call:");
	            double t2 =input.nextDouble();
	            
	                   
	            double loading_hand_voice=(1-constant)*(A1/B1), loading_new_voice=(A1/B1);
	            double loading_hand_data=(1-constant)*(A2/B2), loading_new_data=(A2/B2);       
	            double prob_drop=0, prob_block=0;      
	            
               double cc1 =c1, cc2 =c2, G =0, Ui=0;
	           {for(int n1=0; n1<=t1; n1++)
	            {   
	                for(int h1=0; h1<= cc1; h1++)
	                {   
	                    for(int n2=0; n2<=t2; n2++)
	                    {   
	                        for(int h2=0; h2<=cc2; h2++)
	                        {       
	                                  double eN1= Math.pow(loading_new_voice,n1),eH1=Math.pow(loading_hand_voice,h1);
	                                   double eN2= Math.pow(loading_new_data,n2),eH2= Math.pow(loading_hand_data, h2);
	                                   double prob;
	                                    if (constant!=1)
	                                    	{prob=eN1*eH1*eN2*eH2;}
	                                    else
	                                        {prob=eN1*eN2;}
	                                    
	                                    double den=(factorial(n1))*(factorial(h1)*factorial(n2)*factorial(h2));
	                                    double probs=(prob/den); 
	                                    G= G + probs;
                                       Ui= Ui+ probs*((n1+h1)+(n2+h2));
                                       }cc2--;}}cc1--;}}
               double U = (bbu*Ui/G);
           
	            double n1 =0, h1 =0, n2 =0,h2 =0; G =0;
	            while (n1 < t1)
	            {   n1 = n1 + 1;h1=0;
	                while (h1 < c1)
	                {   h1 = h1 + 1;n2=0;
	                    while (n2 < t2)
	                    {   n2 = n2 + 1;h2=0;
	                        while (h2 < c2)
	                        {   h2 = h2 + 1;
	                            int m3 = 0;
	                            while (m3 < 30)
	                            {   m3 = m3 + 1;
	                                
	                                double condition1 = bbu * (n1+h1);
	                                double condition2 = bbu * (n1);
	                                double condition3 = (2*bbu) * (n2+h2);
	                                double condition4 = (2*bbu) * (n2);
	                                if (condition1 <= c1 && condition2 <= t1 && condition3 <= c2 && condition4 <= t2 )
	                                {  double eN1= Math.pow(loading_new_voice,n1),eH1=Math.pow(loading_hand_voice,h1);
	                                   double eN2= Math.pow(loading_new_data,n2),eH2= Math.pow(loading_hand_data, h2);
	                                   double prob;
	                                    if (constant!=1)
	                                    	{prob=eN1*eH1*eN2*eH2;}
	                                    else
	                                        {prob=eN1*eN2;}
	                                    
	                                    double den=(factorial(n1))*(factorial(h1)*factorial(n2)*factorial(h2));
	                                    double probs=(prob/den); 
	                                    G= G + probs;
	                                    
	                                    if ( bbu *( n1+h1 +1) > t1 && (2*bbu)*( n2+h2 +1) > t2 )
	                                        {prob_block+= probs;}
	                                        
	                                    if(constant!=1)
	                                        {if ( bbu *( n1+h1 )>(c1 -bbu ) && (2*bbu) *( n2+h2 )>(c2 -(2*bbu) ))
	                                            {prob_drop+=probs;}}
	                                    else
	                                        prob_drop=0;
	                                }}}}}}
	            if(G==0)
	            {   prob_drop=1;
	                prob_block=1;
	                G=1;}                 
	                 
	  
	            System.out.println("c1 : "+ c1);
	            System.out.println("c2 : "+ c2);
	            System.out.println("t1 : "+ t1);
	            System.out.println("t2 : "+ t2);
	            System.out.println("bbu : "+ bbu);
	            
	            System.out.println("RAT 2 blocking probability:  "+( prob_block/G));
	            
	            System.out.println("RAT 2 dropping probability: "+( prob_drop/G ));
	            System.out.println("RAT 2 average Utilization: "+U);
	           
	        } 
	        else if (RAT==3)
	        {   System.out.println("Enter the capacity for RAT 3");
	         	double c=input.nextDouble();
               double c1=c*0.3333333333333;
               double c2=c*0.3333333333333;
               double c3=c*0.3333333333333;
	         	
               
                   System.out.println("Enter the arrival rate for voice calls:");
                 double A1=input.nextDouble();
                 System.out.println("Enter the arrival rate for data calls:");
                 double A2=input.nextDouble();
                 System.out.println("Enter the arrival rate for video calls:");
                 double A3=input.nextDouble();
                 
	            
               System.out.println("Enter the departure rate for voice calls:");
               double B1 =input.nextDouble();
               System.out.println("Enter the departure rate for data calls:");
               double B2 =input.nextDouble();
               System.out.println("Enter the departure rate for video calls:");
               double B3 =input.nextDouble();
               System.out.println("Input the bbu Required:");
	            double bbu=input.nextDouble();
                
	           double  constant= 0.1;
              
               while (B1>A1)
               {System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B1=input.nextDouble();}
           while (B2>A2)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B2=input.nextDouble();}
            while (B3>A3)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B3=input.nextDouble();}
	              
	            
	            System.out.println("Enter the threshold of voice call:");
	            double t1=input.nextDouble(); //threshold for voice call
	            System.out.println("Enter the threshold of data call:"); 
	            double t2 =input.nextDouble();  //threshold for data call
	            System.out.println("Enter the threshold of video call:");
	            double t3 =input.nextDouble();
	           
	            
	            double loading_hand_voice=(1-constant)*(A1/B1), loading_new_voice=(A1/B1);
	            double loading_hand_data=(1-constant)*(A2/B2),loading_new_data=(A2/B2);
	            double loading_hand_video=(1-constant)*(A3/B3),loading_new_video=(A3/B3);
	            double prob_drop=0, prob_block=0;
	            
               double cc1= c1, cc2= c2, cc3= c3, Ui=0, G=0;
	            for(int n1=0; n1<=t1; n1++)
	            {   
	                for(int h1=0; h1<=cc1; h1++)
	                {   
	                    for(int n2=0; n2 <=t2; n2++)
	                    {   
	                        for(int h2=0; h2 <=cc2; h2++)
	                        {   
	                            for(int n3=0; n3 <=t3; n3++)
	                            {   
	                                for(int h3=0; h3 <=cc3; h3++)
	                                {  
                                   
	                                      double eN1= Math.pow(loading_new_voice,n1), eH1= Math.pow(loading_hand_voice,h1), eN2= Math.pow(loading_new_data,n2);
	                                        double eH2= Math.pow(loading_hand_data,h2), eN3= Math.pow(loading_new_video,n3), eH3= Math.pow(loading_hand_video,h3);  
	                                        double prob=eN1*eH1*eN2*eH2*eN3*eH3;
	                                        
	                                        double den=(factorial(n1))*(factorial(h1)*factorial(n2)*factorial(h2)*factorial(h3)*factorial(n3));
	                                        double probs=(prob/den);
	                                        G = G+ probs;     
                                           
                                           Ui = Ui + probs*((n1+h1)+(n2+h2)+(n3+h3));                          
	                                         }cc3--;}}cc2--;}}cc1--;}
               double U = (bbu*Ui/G);
               
               int n1 =0,h1 =0,n2 =0,h2 =0,n3=0,h3=0; G=0; 
	            while (n1 < t1)
	            {   n1 = n1 + 1;h1=0;
	                while (h1 < c1)
	                {   h1 = h1 + 1;n2=0;
	                    while (n2 < t2)
	                    {   n2 = n2 + 1;h2=0;
	                        while (h2 < c2)
	                        {   h2 = h2 + 1;n3=0;
	                            while (n3 < t3)
	                            {   n3 = n3 + 1;h3=0;
	                                while (h3 < c3)
	                                {   h3 = h3 + 1;
	                                    double condition1 = bbu * (n1+h1),condition2 = bbu * (n1);
	                                    double condition3 = (2*bbu) * (n2+h2),condition4 = (2*bbu) * (n2);
	                                    double condition5=(3*bbu)*(n3+h3),condition6=(3*bbu)*n3;
	                                
	                                    if (condition1 <= c1 && condition2 <= t1 && condition3 <= c2 && condition4 <= t2 && condition5<= c3 && condition6<= t3 )
	                                    {   double eN1= Math.pow(loading_new_voice,n1),eH1= Math.pow(loading_hand_voice,h1), eN2= Math.pow(loading_new_data,n2);
	                                        double eH2= Math.pow(loading_hand_data,h2),eN3= Math.pow(loading_new_video,n3),eH3= Math.pow(loading_hand_video,h3);  
	                                        double prob;
	                                        if(constant!=1)
	                                            prob=eN1*eH1*eN2*eH2*eN3*eH3;
	                                        else
	                                            prob=eN1*eN2*eN3;
	                                        
	                                        double den=(factorial(n1))*(factorial(h1)*factorial(n2)*factorial(h2)*factorial(h3)*factorial(n3));
	                                        double probs=(prob/den);
	                                        G= G+ probs;                                
	                                        
	                                        if ( bbu *( n1+h1 +1) > t1 && (2*bbu) *( n2+h2 +1) > t2 && (3*bbu) *( n3+h3 +1) > t3  )
	                                            {prob_block+=probs;}
	                                            
	                                        if(constant!=1)
	                                         {  if ( bbu *( n1+h1 )>(c1 -bbu ) && (2*bbu) *( n2+h2 )>(c2 -(2*bbu) ) && (3*bbu) *( n3+h3 )>(c3 -(3*bbu) ))
	                                                {prob_drop+=probs;}}
	                                        else
	                                            prob_drop=0;
	                                    }}}}}}}
	            if(G==0)
	            {   prob_drop=1;
	                prob_block=1;
	                G=1;}             
	            
	            
	            System.out.println("\nc1 : "+ c1);
	            System.out.println("c2 : "+ c2);
	            System.out.println("c3 : "+ c3);
	            System.out.println("t1 : "+ t1);
	            System.out.println("t2 : "+ t2);
	            System.out.println("t3 : "+ t3);
	            
	            System.out.println("RAT 3 blocking probability: "+( prob_block/G));
	           
	            System.out.println("RAT 3 dropping probability: "+( prob_drop/G ));
	            System.out.println("RAT 3 average Utilization: "+U);
	        }}
	    else if(h==1)
	    {   System.out.println("You have chosen to simulate voice calls using Complete Sharing!\n");
	    	
	    	    if(RAT==1)
	    	    {   
               System.out.println("Your service request has been admitted in RAT 1");
	    	    	System.out.println("Enter the bandwidth capacity for RAT 1:");
	    	        double c1=input.nextDouble(); 
                   System.out.println("Enter the Call arrival rate for voice (calls/minute)");
	            double A= input.nextDouble();
	             System.out.println("Enter the Call departure rate for voice (calls/minute)");
	            double B= input.nextDouble();
               System.out.println("Input the bbu Required:");
	            double bbu=input.nextDouble();
                
	            double constant= 0.1;
               
	    
	    while (B>A)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B=input.nextDouble();} 
	    	        
	    	        
                 
	    	              
	    	        double loading_hand=(1-constant)*(A/B),loading_new=(A/B),prob_drop=0,prob_block=0;        
	    	        double cc1 =c1, G=0, Ui=0;
	    	        for(int n1=0; n1<= c1; n1++)
	    	        {   
	    	            for(int h1=0; h1<= cc1; h1++)
	    	            {   
                        
	    	                    double eN= Math.pow(loading_new, n1),eH= Math.pow(loading_hand, h1);
	    	                	  double prob;
	    	                    if(constant!=1)
	    	                        prob=eN*eH;
	    	                    else
	    	                        prob=eN;
	    	                    double den=(factorial(n1))*(factorial(h1)), probs=(prob/den);
	    	                    G+=probs;
                             Ui= Ui+ probs*(n1+h1);
                             }cc1--;}
                 double U = (bbu*Ui/G);
                      
                 double n1 =0, h1 =0, GG=0; G=0;
	    	        while (n1 < c1)
	    	        {   n1 = n1 + 1;
	    	            h1 = 0;
	    	            while (h1 < c1)
	    	            {   h1 = h1 + 1;
                          
	    	                double condition1 = bbu * (n1+h1);
	    	               
	    	                if (condition1 <= c1)
	    	                {   double eN= Math.pow(loading_new, n1),eH= Math.pow(loading_hand, h1);
	    	                	  double prob;
	    	                    if(constant!=1)
	    	                        prob=eN*eH;
	    	                    else
	    	                        prob=eN;
	    	                    double den=(factorial(n1))*(factorial(h1)), probs=(prob/den);
	    	                    G+=probs;
                             
	    	                    if(constant!=1)
	    	                    {   if ( bbu *( n1+h1 )>(c1 -bbu ))
	    	                    	{  prob_block+= probs;
	    	                            prob_drop+= probs;}}
	    	                    else
	    	                    {   if ( bbu *( n1+h1 )>(c1 -bbu ))
	    	                    	{   prob_block+= probs;
	    	                            prob_drop=0;}}}}}                      
	    	        if(G==0)
	    	        {   prob_drop=1;
	    	            G=1;}
	    	        
	    	        System.out.println("\nc1 : "+c1);           
	    	        System.out.println("bbu : "+ bbu);
	    	        
	    	        System.out.println("RAT 1 blocking probability: "+( prob_block/G ));
	    	                         
	    	        System.out.println("RAT 1 dropping probability: "+( prob_drop/G ));
	    	        System.out.println("RAT 1 average Utilization: "+U);
	    	    }
	    	    else if(RAT==2)
	    	    {
                 System.out.println("Enter the capacity of RAT 2");
                 double c=input.nextDouble();
                 double c1=c*0.5;//capacity reserved for voice
                 double c2=c*0.5;//capacity reserved for video
                 
                   System.out.println("Enter the Call arrival rate for voice (calls/minute)");
	                double A1= input.nextDouble();
                   System.out.println("Enter the Call arrival rate for video (calls/minute)");
	                double A2= input.nextDouble();
	                
                   System.out.println("Enter the Call departure rate for voice (calls/minute)");
	                double B1= input.nextDouble();
                   System.out.println("Enter the Call departure rate for video (calls/minute)");
	                double B2= input.nextDouble();
                   System.out.println("Input the bbu Required:");
	                 double bbu=input.nextDouble();
                    
	                 double constant= 0.1;
                  
	    
	    while (B1>A1)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B1=input.nextDouble();}
           while (B2>A2)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B2=input.nextDouble();}
           
	    	        double loading_hand_voice=(1-constant)*(A1/B1),loading_new_voice=(A1/B1);
	    	        double loading_hand_data=(1-constant)*(A2/B2),loading_new_data=(A2/B2);     
	    	        double prob_drop=0,prob_block=0;             
	    	        
                 double cc1= c1,cc2= c2, G=0, Ui=0;
	    	       {for(int n1=0; n1<=c1; n1++)
	              {   
	                for(int h1=0; h1<= cc1; h1++)
	                {   
	                    for(int n2=0; n2<=c2; n2++)
	                    {   
	                        for(int h2=0; h2<=cc2; h2++)
	                        {
	    	                          double eN1= Math.pow(loading_new_voice, n1),eH1= Math.pow(loading_hand_voice, h1);
	    	                            double eN2= Math.pow(loading_new_data, n2),eH2= Math.pow(loading_hand_data, h2);
	    	                            double prob;
	    	                            if(constant!=1)
	    	                                prob=eN1*eH1*eN2*eH2;
	    	                            else
	    	                                prob=eN1*eN2;
	    	                            double den=(factorial(n1))*(factorial(h1)*factorial(n2)*factorial(h2));
	    	                            double probs=(prob/den); 
	    	                            G+=probs;
                                     Ui = Ui+ probs*((n1+h1)+(n2+h2));
                                     }cc2--;}}cc1--;}}
                 double U = (bbu*Ui/G);
                 
                 double n1 =0,h1 =0,n2=0,h2=0; G=0;
	    	        while (n1 < c1)
	    	        {   n1 = n1 + 1;h1=0;
	    	            while (h1 < c1)
	    	            {   h1 = h1 + 1;n2=0;
	    	                while(n2<c2)
	    	                {   n2=n2+1;h2=0;
	    	                    while(h2<c2)
	    	                    {   h2=h2+1;
	    	                        double condition1 = bbu * (n1+h1), condition2=(2*bbu)*(n2+h2);
	    	                        if (condition1 <= c1 && condition2 <= c2)
	    	                        {   double eN1= Math.pow(loading_new_voice, n1),eH1= Math.pow(loading_hand_voice, h1);
	    	                            double eN2= Math.pow(loading_new_data, n2),eH2= Math.pow(loading_hand_data, h2);
	    	                            double prob;
	    	                            if(constant!=1)
	    	                                prob=eN1*eH1*eN2*eH2;
	    	                            else
	    	                                prob=eN1*eN2;
	    	                            double den=(factorial(n1))*(factorial(h1)*factorial(n2)*factorial(h2));
	    	                            double probs=(prob/den); 
	    	                            G+=probs;
                                     
	    	                            if(constant!=1)
	    	                            {   if ( bbu *( n1+h1 )>(c1 -bbu ) && (2*bbu) *( n2+h2 )>(c2 -(2*bbu) ))
	    	                            	{   prob_block+= probs;
	    	                                    prob_drop+= probs;}}
	    	                            else
	    	                            {   if ( bbu *( n1+h1 )>(c1 -bbu ) && (2*bbu) *( n2+h2 )>(c2 -(2*bbu) ))
	    	                                {   prob_block+= probs;
	    	                                    prob_drop=0;}}}}}}}                                
	    	        if(G==0)
	    	        {   prob_drop=1;
	    	            prob_block=1;
	    	            G=1;}        
	    	        
	    	        System.out.println("c1 : "+ c1);
	    	        System.out.println("c2 : "+c2);
	    	        
	    	        System.out.println("probability of blocking group: "+( prob_block/ G ));                               
	    	        System.out.println("probability of dropping group: "+( prob_drop/G ));         
	    	        System.out.println("RAT 2 average Utilization: "+U);   
	    	    }             
	    	    else if(RAT==3)
	    	    {   System.out.println("Enter the RAT capacity :");
	    	    	double c=input.nextDouble();
					double c1=c*0.333333333333;
               double c2=c*0.333333333333;
               double c3=c*0.333333333333;
                 
                 
                  System.out.println("Enter the arrival rate for voice calls:");
                 double A1=input.nextDouble();
                 System.out.println("Enter the arrival rate for data calls:");
                 double A2=input.nextDouble();
                 System.out.println("Enter the arrival rate for video calls:");
                 double A3=input.nextDouble();
                 
	            double bbu;
               System.out.println("Enter the departure rate for voice calls:");
               double B1 =input.nextDouble();
               System.out.println("Enter the departure rate for data calls:");
               double B2 =input.nextDouble();
               System.out.println("Enter the departure rate for video calls:");
               double B3 =input.nextDouble();
               System.out.println("Input the bbu Required:");
	             bbu=input.nextDouble();
               	            double constant= 0.1;
               
                while (B1>A1)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B1=input.nextDouble();}
           while (B2>A2)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B2=input.nextDouble();}
            while (B3>A3)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B3=input.nextDouble();}

	    	        
	    	        double loading_hand_voice=(1-constant)*(A1/B1), loading_new_voice=(A1/B1);
	    	        double loading_hand_data=(1-constant)*(A2/B2),loading_new_data=(A2/B2);
	    	        double loading_hand_video=(1-constant)*(A3/B3),loading_new_video=(A3/B3);
	    	        double prob_drop=0, prob_block=0;      
	    	         
                 double cc1 =c1,cc2 =c2,cc3=c3, G=0, Ui=0;
	    	        for(int n1=0; n1 <=c1; n1++)
	              {   
	                for(int h1=0; h1 <=cc1; h1++)
	                {   
	                    for(int n2=0; n2 <=c2; n2++)
	                    {   
	                        for(int h2=0; h2 <=cc2; h2++)
	                        {   
	                            for(int n3=0; n3 <=c3; n3++)
	                            {   
	                                for(int h3=0; h3 <=cc3; h3++)
	                                {  
	    	                                    double eN1= Math.pow(loading_new_voice,n1),eH1= Math.pow(loading_hand_voice,h1);
	    	                                    double eN2= Math.pow(loading_new_data,n2), eH2= Math.pow(loading_hand_data,h2);
	    	                                    double eN3= Math.pow(loading_new_video,n3),eH3= Math.pow(loading_hand_video,h3);
	    	                                    double prob;
	    	                                    if(constant!=1)
	    	                                        prob=eN1*eH1*eN2*eH2*eN3*eH3;
	    	                                    else
	    	                                        prob=eN1*eN2*eN3;
	    	                                    double den=(factorial(n1))*(factorial(h1)*factorial(n2)*factorial(h2)*factorial(h3)*factorial(n3));
	    	                                    double probs=(prob/den); 
	    	                                    G+=probs; 
                                             Ui = Ui +probs*((n1+h1)+(n2+h2)+(n3+h3));
                                             }cc3--;}}cc2--;}}cc1--;}
                 double U = (bbu*Ui/G);
                  
                 double n1 =0,h1 =0,n2=0, h2=0, n3=0,h3=0; G=0;
	    	        while (n1 < c1)
	    	        {   n1 = n1 + 1;h1=0;
	    	            while (h1 < c1)
	    	            {   h1 = h1 + 1;n2=0;
	    	                while(n2<c2)
	    	                {   n2=n2+1;h2=0;
	    	                    while(h2<c2)
	    	                    {   h2=h2+1;n3=0;
	    	                        while(n3<c3)
	    	                        {   n3+=1;h3=0;
	    	                            while(h3<c3)
	    	                            {   h3+=1;
	    	                                double condition1 = bbu * (n1+h1),condition2=(2*bbu)*(n2+h2),condition3=(3*bbu)*(n3+h3);
	    	                                
	    	                                if (condition1 <= c1 && condition2 <= c2 && condition3 <= c3)
	    	                                {   double eN1= Math.pow(loading_new_voice,n1),eH1= Math.pow(loading_hand_voice,h1);
	    	                                    double eN2= Math.pow(loading_new_data,n2), eH2= Math.pow(loading_hand_data,h2);
	    	                                    double eN3= Math.pow(loading_new_video,n3),eH3= Math.pow(loading_hand_video,h3);
	    	                                    double prob;
	    	                                    if(constant!=1)
	    	                                        prob=eN1*eH1*eN2*eH2*eN3*eH3;
	    	                                    else
	    	                                        prob=eN1*eN2*eN3;
	    	                                    double den=(factorial(n1))*(factorial(h1)*factorial(n2)*factorial(h2)*factorial(h3)*factorial(n3));
	    	                                    double probs=(prob/den); 
	    	                                    G+=probs; 
	    	                                    if(constant!=1)
	    	                                    {   if ( bbu *( n1+h1 )>(c1 -bbu ) && (2*bbu) *( n2+h2 )>(c2 -(2*bbu) ) && (3*bbu) *( n3+h3 )>(c3 -(3*bbu) ))
	    	                                        {   prob_block+= probs;
	    	                                            prob_drop+= probs;}}
	    	                                    else
	    	                                    {   if ( bbu *( n1+h1 )>(c1 -bbu ) && (2*bbu) *( n2+h2 )>(c2 -(2*bbu) ) && (3*bbu) *( n3+h3 )>(c3 -(3*bbu) ))
	    	                                        {   prob_block+= probs;
	    	                                            prob_drop = 0;}}}}}}}}}                                      
	    	        if(G==0)
	    	        {   prob_drop=1;
	    	            prob_block=1;
	    	            G=1;}        
	    	        
	    	        System.out.println("c1 : "+ c1);
	    	        System.out.println("c2 : "+c2);
	    	        System.out.println("c3 : "+c3);                              
	    	        
	    	        
	    	        System.out.println("RAT 3 blocking probability: "+( prob_block/G));                  
	    	        System.out.println("RAT 3 dropping probability: "+( prob_drop/G ));
	    	        System.out.println("RAT 3 average utilization: "+U);
	    }}   
	    else if(h==3){ 
       {System.out.println("Chosen Complete partition\n");
       if (RAT==3){
       System.out.println("Enter the bandwidth capacity of RAT 3:");
	    	double c=input.nextDouble();
        System.out.println("Input the bbu Required:");
	     double bbu=input.nextDouble();
        
	     double constant= 0.1;
        
	    	System.out.println("For a voice call");
         System.out.println("Enter arrival rate for voice call:");
	    	double  A=input.nextDouble();
         System.out.println("Enter departure rate for voice call:");
	    	double  B=input.nextDouble();
         
         System.out.println("Enter the ratio for voice");
         double voice=input.nextDouble();
         System.out.println("Enter the ratio for video");
         double video=input.nextDouble();
         System.out.println("Enter the ratio for data");
         double data =input.nextDouble();
         
         while ((video*c + voice*c+ data*c)>c){
         System.out.println("Enter ratios such that the entire capacity is not exceeded");
         System.out.println("Enter the ratio for voice");
         voice=input.nextDouble();
         System.out.println("Enter the ratio for s video");
          video=input.nextDouble();
         System.out.println("Enter the ratio for data");
          data =input.nextDouble();}
         
         
         System.out.println("Enter the ratio for voice handoff calls as a decimal figure:");
	    	double c11=input.nextDouble();
         System.out.println("Enter the ratio for voice new calls as a decimal figure:");
	    	double c22=input.nextDouble();
         
          while((c22*voice+c11*voice)>voice)
	    {	System.out.println("Enter the ratios of new calls and handoff calls such that the voice  capacity is not exceeded");
       
	    	System.out.println("Enter the ratio for voice handoff calls as a decimal figure:");
	    	       c11=input.nextDouble();
               System.out.println("Enter the ratio for voice new calls as a decimal figure:");
	    	      c22=input.nextDouble();;}
               
               
                  double v=voice*c;
                  double co=video*c;
                  double d=data*c;
         
	    		    double c1=c11*v, c2=c22*v; 
	    		   double h1=0, n1=0;
	    		    double G2=0,G1=0, Ui1=0, Ui2=0;
	    		    
	    while (B>A)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B=input.nextDouble();}
            
	    		    double loading_hand1=(1-constant)*(A/B), loading_new1=(A/B);//load network
	    		    double prob_drop=0,prob_block=0; 
                          
	    		    while (n1 <= c1)
	    		    {   n1 = n1 +1;
	    		        double condition2 = bbu * (n1);
	    		        if (condition2 <= c1)
	    		        {   double eN= Math.pow(loading_new1,n1);
	    		           
	    		            double prob=eN;
	    		            double den=factorial(n1); 
	    		            double probs=(prob/den); 
	    		            G2+=probs;
                        Ui1 = Ui1+ probs*(n1);
	    		            if ( bbu *( n1 ) > (c1 -bbu )) {prob_block += probs;}
	    		        }}
                    
	    		    if(G2==0)
	    		    {   prob_block=1;
	    		        G2=1;}
	    		     
	    		    while (h1 <= c2)
	    		    {  h1 = h1+ 1;  
	    		        double condition1 = bbu * (h1);
	    		        if (condition1 <= c2)
	    		        {   double eH= Math.pow(loading_hand1,h1), prob=eH,den=(factorial(h1));
	    		            double probs = (prob/den);
	    		            G1+=probs; 
                        Ui2 = Ui2+ probs*(h1);   
	    		            if ( bbu *( h1 )>(c2 -bbu ))
	    		                    prob_drop+= probs;}}
	    		    if(G1==0)
	    		    {   prob_drop=1;
	    		        G1=1;}
                System.out.println("Results for a voice call are shown below:");
	    		    System.out.print("c1 for new call(voice): ");
	    		    System.out.printf("%.5f",c1);
	    		    System.out.print("\nc2 for handoff call(voice): ");
	    		    System.out.printf("%.5f",c2);                    
	    		    System.out.println("\nRAT 3 blocking probability: "+( prob_block/G2 ));    
	    		    
	    		    System.out.println("RAT 3 dropping probability: "+( prob_drop/G1 ));
	    		    System.out.println("RAT 3 average utilization: c1: "+bbu*Ui1/G2+", c2: "+bbu*Ui2/G1);
	    		   
	    		    System.out.println(" for a data call:\n");
	    		      
	    		    System.out.println("Enter the Call arrival rate for data (calls/minute)");
	            double A1= input.nextDouble();
	             System.out.println("Enter the Call departure rate for data (calls/minute)");
	            double B1= input.nextDouble();
               
               System.out.println("Enter the ratio for data handoff calls as a decimal figure:");
	    	      double c1_1=input.nextDouble();
               System.out.println("Enter the ratio for data new calls as a decimal figure:");
	    	      double c2_2=input.nextDouble();
               double c_1;
               double c_2;
               
	    		    c_1=c1_1*d;
                 c_2=c2_2*d;
                
                while((c2_2*d+c1_1*d)>d)
	             {	System.out.println("Enter the ratios of new calls and handoff calls such that the capacity is not exceeded");
	    	         System.out.println("Enter the ratio for data handoff calls as a decimal figure:");
	    	         c1_1=input.nextDouble();
                  System.out.println("Enter the ratio for data new calls as a decimal figure:");
	    	         c2_2=input.nextDouble();;}
                  c_1=c1_1*d;
	    		      c_2=c2_2*d;
	    		     
	    		    G2=0;G1=0; h1=0; n1 =0; Ui1=0; Ui2=0;
	    		    double loading_hand2=(1-constant)*(A1/B1),loading_new2=(A1/B1);
	    		    prob_drop=0;prob_block=0;
	    		          
	    		    while (n1 <= c_1)
	    		    {    n1= n1+ 1;
	    		        double condition2 = (2*bbu) * (n1);
	    		        if (condition2 <= c_1)
	    		        {   double eN= Math.pow(loading_new2, n1);
	    		            
	    		            double prob=eN, den=(factorial(n1)),probs=(prob/den); 
	    		            G2+=probs;
                        Ui1 = Ui1+ probs*(n1);
	    		            if ( (2*bbu) *( n1 )>(c_1 -(2*bbu) )) prob_block+= probs;
	    		            }}
	    		    if(G2==0)
	    		    {   prob_block=1;
	    		        G2=1;}
	    		       
	    		    while (h1 < c_2)
	    		    {   h1 = h1 + 1;
	    		        double condition1 = (2*bbu) * (h1);
	    		        
	    		        if (condition1 <= c_2)
	    		        {   double eH= Math.pow(loading_hand2, h1),prob=eH, den=(factorial(h1)),probs=(prob/den); 
	    		            G1+=probs; 
                        Ui2 = Ui2+ probs*(h1);
	    		            if ( (2*bbu) *( h1 )>(c_2 -(2*bbu) ))
	    		                    prob_drop+= probs;}}
	    		    if(G1==0)
	    		    {   prob_drop=1;
	    		        G1=1;}        
	    		    System.out.println("Results for a data call are shown below:");
	    		    System.out.print("c1 for new call(data): ");
	    		    System.out.printf("%.5f",c_1);
	    		    System.out.print("\nc2 for handoff call(data): ");
	    		    System.out.printf("%.5f",c_2);
	    		                        
	    		    System.out.println("\nRAT 1 blocking probability: "+( prob_block/G2 ));    
	    		    
	    		    System.out.println("RAT 1 dropping probability: "+( prob_drop/G1 ));
	    		    System.out.println("RAT 1 average utilization: c1: "+bbu*Ui1/G2+", c2: "+bbu*Ui2/G1);
                
	    		    System.out.println("VIDEO CALL");
	    		    
	    		  
	    		    n1 =0;
	    		    
	    		    G2=0;G1=0; h1=0; Ui1=0; Ui2=0;
                System.out.println("Enter the Call arrival rate for video (calls/minute)");
	            double A3= input.nextDouble();
	             System.out.println("Enter the Call departure rate for video (calls/minute)");
	            double B3= input.nextDouble();
               
                System.out.println("Enter the ratio for video handoff calls as a decimal figure:");
	    	      double c_1_1=input.nextDouble();
               System.out.println("Enter the ratio for video new calls as a decimal figure:");
	    	      double c_2_2=input.nextDouble();
               
                while((c_2_2*co +c_1_1*co)>co)
	    {	System.out.println("Enter the ratios of new calls and handoff calls such that the capacity is not exceeded");
	    	System.out.println("Enter the ratio for video handoff calls as a decimal figure:");
	    	      c_1_1=input.nextDouble();
               System.out.println("Enter the ratio for video new calls as a decimal figure:");
	    	      c_2_2=input.nextDouble();;}
               double c__1;
               double c__2;
               
               c__1=c_1_1*co;c__2=c_2_2* co;
               
	    		    double loading_hand3=(1-constant)*(A3/B3),loading_new3=(A3/B3);
	    		    prob_drop=0;prob_block=0;
               
                     
	    		    while (n1 < c__1)
	    		    {   n1= n1+1;
	    		        double condition2 = (3*bbu) * (n1);
	    		        if (condition2 <= c__1)
	    		        {   
	    		        	double eN= Math.pow(loading_new3, n1), prob=eN;
	    		            double den=(factorial(n1));
	    		            double probs=(prob/den); 
	    		            G2+=probs;
                        Ui1 = Ui1 + probs*(n1);
	    		            if ( (3*bbu) *( n1 )>(c__1 -(3*bbu) ))
	    		                prob_block+= probs;}}
	    		    if(G2==0)
	    		    {  prob_block=1;
	    		        G2=1;}
	    		            
	    		    while (h1 < c__2)
	    		    {  h1= h1+1;
	    		        double condition1 = (3*bbu) * (h1);
	    		        
	    		        if (condition1 <= c__2)
	    		        {   double eH= Math.pow(loading_hand3,h1), prob=eH;
	    		            double den=(factorial(h1));
	    		            double probs=(prob/den); 
	    		            G1 +=probs;
                        Ui2 = Ui2+ probs*(h1); 
	    		            if ((3*bbu) *( h1 )>(c__2 -(3*bbu) ))
	    		                    prob_drop+= probs;}}
	    		    if(G1==0)
	    		    {   prob_drop=1;
	    		        G1=1;}       
	    		    System.out.println("Results for a video call are shown below:");
	    		    System.out.print("c1 for new call(video): ");
	    		    System.out.printf("%.5f",c__1);
	    		    System.out.print("\nc2 for handoff call(video): ");
	    		    System.out.printf("%.5f",c__2);
	    		                
	    		    System.out.println("\nprobability of blocking group: "+( prob_block/G2 ));    
	    		    
	    		    System.out.println("probability of dropping group: "+( prob_drop/G1 ));
                System.out.println("RAT 3 average utilization: c1: "+bbu*Ui1/G2+", c2: "+bbu*Ui2/G1);}}
                
        if (RAT==2){
        
        
         System.out.println("Enter the bandwidth capacity of RAT 2:");
	    	double c=input.nextDouble();
        System.out.println("Input the bbu Required:");
	     double bbu=input.nextDouble();
        
	     
	     double constant= 0.1;
       
	    	System.out.println("For a voice call");
         System.out.println("Enter arrival rate for voice call:");
	    	double  A=input.nextDouble();
         System.out.println("Enter departure rate for voice call:");
	    	double  B=input.nextDouble();
         
         System.out.println("Enter the ratio for voice");
         double voice=input.nextDouble();
         System.out.println("Enter the ratio for video");
         double video=input.nextDouble();
         
         while ((video*c + voice*c)>c){
         System.out.println("Enter ratios such that the entire capacity is not exceeded");
         System.out.println("Enter the ratio for voice");
         voice=input.nextDouble();
         System.out.println("Enter the ratio for s video");
          video=input.nextDouble();}
         
         
         System.out.println("Enter the ratio for voice handoff calls as a decimal figure:");
	    	double c11=input.nextDouble();
         System.out.println("Enter the ratio for voice new calls as a decimal figure:");
	    	double c22=input.nextDouble();
         
          while((c22*voice+c11*voice)>voice)
	    {	System.out.println("Enter the ratios of new calls and handoff calls such that the voice  capacity is not exceeded");
       
	    	System.out.println("Enter the ratio for voice handoff calls as a decimal figure:");
	    	       c11=input.nextDouble();
               System.out.println("Enter the ratio for voice new calls as a decimal figure:");
	    	      c22=input.nextDouble();;}
                  double v=voice*c;
                  double co=video*c;
                 
         
	    		    double c1=c11*v, c2=c22*v; 
	    		   
	    		    int n1 =0;
	    		  
	    		    double G2=0,G1=0, Ui1=0, Ui2=0;
	    		    int h1=0;
               
	    
	    while (B>A)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B=input.nextDouble();}
            
	    		    double loading_hand1=(1-constant)*(A/B), loading_new1=(A/B);
	    		    double prob_drop=0,prob_block=0; 
                          
	    		    while (n1 < c1)
	    		    {   n1= n1+1;
	    		        double condition2 = bbu * (n1);
	    		        if (condition2 <= c1)
	    		        {   double eN= Math.pow(loading_new1,n1);
	    		           
	    		            double prob=eN;
	    		            double den=factorial(n1); 
	    		            double probs=(prob/den); 
	    		            G2+=probs;
                        Ui1= Ui1+probs*(n1);
	    		            if ( bbu *( n1 ) > (c1 -bbu )) {prob_block += probs;}
	    		        }}
	    		    if(G2==0)
	    		    {   prob_block=1;
	    		        G2=1;}
	    		     
	    		    while (h1 <= c2)
	    		    {    h1= h1+1;
	    		        double condition1 = bbu * (h1);
	    		        
	    		        if (condition1 <= c2)
	    		        {   double eH= Math.pow(loading_hand1,h1), prob=eH,den=(factorial(h1));
	    		            double probs = (prob/den);
	    		            G1+=probs; 
                        Ui2 = Ui2+ probs*(h1);   
	    		            if ( bbu *( h1 )>(c2 -bbu ))
	    		                    prob_drop+= probs;}}
	    		    if(G1==0)
	    		    {   prob_drop=1;
	    		        G1=1;}
                System.out.println("Results for a voice call are shown below:");
	    		    System.out.print("c1 for new call(voice): ");
	    		    System.out.printf("%.5f",c2);
	    		    System.out.print("\nc2 for handoff call(voice): ");
	    		    System.out.printf("%.5f",c1);
	    		                        
	    		    System.out.println("\nprobability of blocking group in RAT 2: "+( prob_block/G2 ));    
	    		    
	    		    System.out.println("probability of dropping group in RAT 2: "+( prob_drop/G1 ));
	    		    System.out.println("RAT 3 average utilization: c1: "+bbu*Ui1/G2+", c2: "+bbu*Ui2/G1);
	    		   
                
	    		    System.out.println("VIDEO CALL");
	    		    
	    		    
	    		    n1 =0;
	    		    
	    		    G2=0;G1=0; h1=0;
                System.out.println("Enter the Call arrival rate for video (calls/minute)");
	            double A3= input.nextDouble();
	             System.out.println("Enter the Call departure rate for video (calls/minute)");
	            double B3= input.nextDouble();
               
                System.out.println("Enter the ratio for video handoff calls as a decimal figure:");
	    	      double c_1_1=input.nextDouble();
               System.out.println("Enter the ratio for video new calls as a decimal figure:");
	    	      double c_2_2=input.nextDouble();
               
                while((c_2_2*co +c_1_1*co)>co)
	    {	System.out.println("Enter the ratios of new calls and handoff calls such that the capacity is not exceeded");
	    	System.out.println("Enter the ratio for video handoff calls as a decimal figure:");
	    	      c_1_1=input.nextDouble();
               System.out.println("Enter the ratio for video new calls as a decimal figure:");
	    	      c_2_2=input.nextDouble();;}
               double c__1;
               double c__2;
               
               c__1=c_1_1*co;c__2=c_2_2* co;
               
	    		    double loading_hand3=(1-constant)*(A3/B3),loading_new3=(A3/B3);
	    		    prob_drop=0;prob_block=0;
	    		    Ui1=0; Ui2=0;
                     
	    		    while (n1 <= c__1)
	    		    {   n1 = n1 + 1;
	    		        double condition2 = (3*bbu) * (n1);
	    		        if (condition2 <= c__1)
	    		        {   
	    		        	double eN= Math.pow(loading_new3, n1), prob=eN;
	    		            double den=(factorial(n1));
	    		            double probs=(prob/den); 
	    		            G2+=probs;
                        Ui1 = Ui1+ probs*(n1);
	    		            if ( (3*bbu) *( n1 )>(c__1 -(3*bbu) ))
	    		                prob_block+= probs;}}
	    		    if(G2==0)
	    		    {  prob_block=1;
	    		        G2=1;}
	    		             
	    		     
	    		    while (h1 <= c__2)
	    		    {   h1 = h1+1;
	    		        double condition1 = (3*bbu) * (h1);
	    		        
	    		        if (condition1 <= c__2)
	    		        {   double eH= Math.pow(loading_hand3,h1), prob=eH;
	    		            double den=(factorial(h1));
	    		            double probs=(prob/den); 
	    		            G1 +=probs;
                        Ui2 = Ui2 + probs*(h1); 
	    		            if ((3*bbu) *( h1 )>(c__2 -(3*bbu) ))
	    		                    prob_drop+= probs;}}
	    		    if(G1==0)
	    		    {   prob_drop=1;
	    		        G1=1;}       
	    		    System.out.println("Results for a video call are shown below:");
	    		    System.out.print("c1 for new call(video): ");
	    		    System.out.printf("%.5f",c__1);
	    		    System.out.print("\nc2 for handoff call(video): ");
	    		    System.out.printf("%.5f",c__2);
	    		                
	    		    System.out.println("\nprobability of blocking group in RAT 2: "+( prob_block/G2 ));    
	    		    
	    		    System.out.println("probability of dropping group in RAT 2: "+( prob_drop/G1 ));
                System.out.println("RAT 2 average utilization: c1: "+bbu*Ui1/G2+", c2: "+bbu*Ui2/G1);
        
        
      		    
	    }
       if (RAT==1){
       
       System.out.println("Enter the bandwidth capacity of RAT 1:");
	    double c=input.nextDouble();
        System.out.println("Input the bbu Required:");
	     double bbu=input.nextDouble();
        
	     double constant= 0.1;
        
	   
         System.out.println("Enter arrival rate for voice call:");
	    	double  A=input.nextDouble();
         System.out.println("Enter departure rate for voice call:");
	    	double  B=input.nextDouble();
       
        System.out.println("Enter the partition ratio for voice handoff calls as a decimal figure:");
	    	double c11=input.nextDouble();
         System.out.println("Enter the partition ratio for voice new calls as a decimal figure:");
	    	double c22=input.nextDouble();
         
            while((c22*c+c11*c)>c)
	    {	System.out.println("Enter the ratios of new calls and handoff calls such that the voice  capacity is not exceeded");
       
	    	System.out.println("Enter the ratio for voice handoff calls as a decimal figure:");
	    	       c11=input.nextDouble();
               System.out.println("Enter the ratio for voice new calls as a decimal figure:");
	    	      c22=input.nextDouble();;}
               
        double c1=c11*c, c2=c22*c; 
	    		   
	    		    int n1 =0;
	    		    double G2=0,G1=0, Ui1=0, Ui2=0;
	    		    int h1=0;
               
	    
	    while (B>A)
	    {	System.out.println("Departure Rate can't be bigger than the Arrival Rate.Repeat Entering the Call Departure Rate (calls/minute)");
	        B=input.nextDouble();}
            
	    		    double loading_hand1=(1-constant)*(A/B), loading_new1=(A/B);
	    		    double prob_drop=0,prob_block=0; 
                          
	    		    while (n1 <= c1)
	    		    {   n1= n1+1;
	    		        double condition2 = bbu * (n1);
	    		        if (condition2 <= c1)
	    		        {   double eN= Math.pow(loading_new1,n1);
	    		           
	    		            double prob=eN;
	    		            double den=factorial(n1); 
	    		            double probs=(prob/den); 
	    		            G2+=probs;
                        Ui1 = Ui1 + probs*(n1);
	    		            if ( bbu *( n1 ) > (c1 -bbu )) {prob_block += probs;}
	    		        }}
	    		    if(G2==0)
	    		    {   prob_block=1;
	    		        G2=1;}
	    		     
	    		    while (h1 <= c2)
	    		    {    h1= h1+1;
	    		        double condition1 = bbu * (h1);
	    		        
	    		        if (condition1 <= c2)
	    		        {   double eH= Math.pow(loading_hand1,h1), prob=eH,den=(factorial(h1));
	    		            double probs = (prob/den);
	    		            G1+=probs; 
                        Ui2 = Ui2 + probs*(h1);   
	    		            if ( bbu *( h1 )>(c2 -bbu ))
	    		                    prob_drop+= probs;}}
	    		    if(G1==0)
	    		    {   prob_drop=1;
	    		        G1=1;}
                System.out.println("Results for a voice call are shown below:");
	    		    System.out.print("c1 for new call(voice): ");
	    		    System.out.printf("%.5f",c2);
	    		    System.out.print("\nc2 for handoff call(voice): ");
	    		    System.out.printf("%.5f",c1);
	    		                        
	    		    System.out.println("\nprobability of blocking group in RAT 1: "+( prob_block/G2 ));    
	    		    
	    		    System.out.println("probability of dropping group in RAT 1: "+( prob_drop/G1 ));
	    		    System.out.println("RAT 1 average utilization: c1: "+bbu*Ui1/G2+", c2: "+bbu*Ui2/G1);
       }}
	    else
	        System.out.println("Sorry something went wrong");  
	input.close();
	}
	public static double factorial(double num)
	{  
	    if (num == 1 || num==0)
	        return 1;
	    else
	        return num * factorial(num - 1);
	}
}