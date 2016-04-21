package pkg10285;

import java.io.*;
import java.util.*;

class Main
{
    static String ReadLn (int maxLg)  
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  
        return (new String (lin, 0, lg));
    }

    public static void main (String args[])  
    {
        Main myWork = new Main();  
        myWork.Begin();            
    }
    
    private static int dimR, dimC;
    private static int[][] matriz = new int[100+3][100+3];
    private static int[][] dp = new int[100+3][100+3];
    
    void Begin()
    {
        String input, nombre = "";
        StringTokenizer idata;
        int casos = 0, contPrincipal = 1, cont = 0;
        
        input = Main.ReadLn (255);
        idata = new StringTokenizer (input);
        casos = Integer.parseInt (idata.nextToken());
        
        while ((input = Main.ReadLn (255)) != null){   
            if(contPrincipal == 1){
                idata = new StringTokenizer (input);
                
                nombre = idata.nextToken();
                
                dimR = Integer.parseInt (idata.nextToken());
                
                dimC = Integer.parseInt (idata.nextToken());
                
            }else if(contPrincipal == 2){
                for(int i=0 ; i < dimR ; i++){
                    if(i!=0){
                        input = Main.ReadLn (255);
                    }
                    idata = new StringTokenizer (input);
                    for(int j=0 ; j < dimC ; j++){
                        matriz[i][j] = Integer.parseInt (idata.nextToken());                        
                    }
                }
                
                int max = -2147483647;
                
                for(int i = 0 ; i<dimR ; i++){
                    for(int j = 0 ; j<dimC ; j++){
                        int len = encontrar(i,j);
                        if(len > max){
                            max = len;
                        }
                    }
                }
                System.out.println(nombre+": "+max);
            }
            cont++;
            contPrincipal ++;
            if(contPrincipal == 3) contPrincipal = 1;
        }
    }
    
    int encontrar(int x, int y){
        if ( dp [x] [y] != -1 ) return dp [x] [y];

        int up = 0, down = 0, right = 0, left = 0;

        if ( x != 0 && matriz [x - 1] [y] < matriz [x] [y] )
            up = encontrar (x - 1, y) + 1;

        if ( x != dimR - 1 && matriz [x + 1] [y] < matriz [x] [y] ) 
            down = encontrar (x + 1, y) + 1;

        if ( y != 0 && matriz [x] [y - 1] < matriz [x] [y] ) 
            left = encontrar (x, y - 1) + 1;

        if ( y != dimC - 1 && matriz [x] [y + 1] < matriz [x] [y] ) 
            right = encontrar (x, y + 1) + 1;

        return dp [x] [y] = Math.max (up, Math.max (down, Math.max (right, left)));

    }
}