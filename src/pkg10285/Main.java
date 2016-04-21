package pkg10285;

import java.util.*;

class Main {
	
    static int dimR, dimC, resultado;
    static int[][] matriz;
    static boolean[][] visitado;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        while(T-- > 0) {
            String nombre = in.next();
            dimR = in.nextInt();
            dimC = in.nextInt();
            matriz = new int[dimR][dimC];
            visitado = new boolean[dimR][dimC];

            for(int r = 0; r < dimR; ++r){
                for (int c = 0; c < dimC; ++c){
                    matriz[r][c] = in.nextInt();
                }
            }
            resultado = 0;

            for(int i = 0; i < dimR; ++i){
                for(int j = 0; j < dimC; ++j){
                    solucion(i, j, 101, 0);
                }
            }
            
            System.out.println(nombre + ": " + resultado);
        }
    }
    
    static void solucion(int r, int c, int h, int sol) {
        if (!validar(r, c, h)) {
            resultado = Math.max(resultado, sol);
            return;
        }
        visitado[r][c] = true;
        solucion(r - 1, c, matriz[r][c], sol + 1);
        solucion(r + 1, c, matriz[r][c], sol + 1);
        solucion(r, c - 1, matriz[r][c], sol + 1);
        solucion(r, c + 1, matriz[r][c], sol + 1);

        visitado[r][c] = false;
    }
    
    static boolean validar(int r, int c, int h) {
        return r >= 0 && c >= 0 && r < dimR && c < dimC && matriz[r][c] < h && !visitado[r][c];
    }
}