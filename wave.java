package Wavepath;

public class wave {
    void doWave(int arr[][]) {
        int collLength = arr.length;
        int rowLength = arr[0].length;
        for (int stapWave = 0; stapWave < rowLength*collLength; stapWave++) {
            for (int i=0; i<collLength; i++) {
                for (int j=0; j<rowLength; j++) {
                    if(arr[i][j] == stapWave) {
                        if((i-1 >= 0) && (arr[i-1][j] == -1)) arr[i-1][j] = stapWave + 1; // up
                        if((j+1 < rowLength) && (arr[i][j+1] == -1)) arr[i][j+1] = stapWave + 1; // rigth
                        if((i+1 < collLength) && (arr[i+1][j] == -1)) arr[i+1][j] = stapWave + 1; // down
                        if((j-1 >= 0) && (arr[i][j-1] == -1)) arr[i][j-1] = stapWave + 1; // left
                    } 
                }
            }    
        }
    }

    static void prnTwoDemArray(int arr[][]) {
        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<arr[0].length; j++)
                System.out.print(" " + arr[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int field[][] = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-2,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{0,-1,-1,-1,-1,-1}};
        prnTwoDemArray(field);
        wave object = new wave();
        object.doWave(field);
        prnTwoDemArray(field);
    }
}