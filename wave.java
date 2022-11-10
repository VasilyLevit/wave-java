package Wavepath;

public class wave {
    void doWave(int arr[][]) {
        int collLength = arr.length-2;
        int rowLength = arr[0].length-2;
        for (int stapWave = 0; stapWave < rowLength*collLength; stapWave++) {
            for (int i = 1; i<=collLength; i++) {
                
                for (int j = 1; j<=rowLength; j++) {
                    if(arr[i][j] == stapWave) {
                        if (arr[i-1][j] == -2 || arr[i][j+1] == -2 || arr[i+1][j] == -2 || arr[i][j-1] == -2) return;
                        else {
                            if(arr[i-1][j] == -1) arr[i-1][j] = stapWave + 1; // up
                            if(arr[i][j+1] == -1) arr[i][j+1] = stapWave + 1; // rigth
                            if(arr[i+1][j] == -1) arr[i+1][j] = stapWave + 1; // down
                            if(arr[i][j-1] == -1) arr[i][j-1] = stapWave + 1; // left
                        }
                    } 
                }
            }
        }
    }
    void doPath(int arr[][],int iEndPath,int jEndPath, int minNumWave) {
        // int minNumWave = 10; // !!! to do поиск максимального значения (пока условно взял 10 для конкретного примера)
        int iMinNumWave = iEndPath;
        int jMinNumWave = jEndPath;
        // поиск мин. значения волны вокруг конечной точки
        // если соседний элемент (в границах поля) и (его значение меньше минимального волны) и (больше 0 ибо то что меньше не волна)
        if((arr[iEndPath-1][jEndPath] < minNumWave) && (arr[iEndPath-1][jEndPath] > 0)) {
            iMinNumWave = iEndPath-1;
        }
        if((arr[iEndPath][jEndPath+1] < minNumWave) && (arr[iEndPath][jEndPath+1] > 0)) {
            iMinNumWave = iEndPath;
            jMinNumWave = jEndPath+1;
        }
        if ((arr[iEndPath+1][jEndPath] < minNumWave) && (arr[iEndPath+1][jEndPath] > 0)) {
            iMinNumWave = iEndPath+1;
            jMinNumWave = jEndPath;
        }
        if((arr[iEndPath][jEndPath-1] < minNumWave) && (arr[iEndPath][jEndPath-1] > 0)) {
            iMinNumWave = iEndPath;
            jMinNumWave = jEndPath-1;
        }
        int heightWawe = arr[iMinNumWave][jMinNumWave];
        minNumWave--;
        arr[iMinNumWave][jMinNumWave] = -2;
      
        if (heightWawe > 1) doPath(arr, iMinNumWave, jMinNumWave, minNumWave);
    }
    static void prnTwoDemArray(int arr[][]) {
        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<arr[0].length; j++)
                System.out.printf("%2d  ", arr[i][j]);
                // System.out.print(" " + arr[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String args[]) {
        // 0 - начальная точка, -1 - свободно для построения пути, -2 - конечная точка и путь, -3 - преграда
        int field[][] = {{-3,-3,-3,-3,-3,-3,-3,-3,-3},{-3,-1,-1,-1,-1,-3,-1,-1,-3},{-3,-1,-1,-1,-1,-3,-1,-1,-3},
        {-3,-1,-1,-3,-1,-1,-1,-1,-3},{-3,-1,-1,-3,-1,-1,-1,-1,-3},{-3,-3,-3,-3,-3,-3,-3,-3,-3}};
        int iEndPoint = 1; // координаты конечной точи
        int jEndPoint = 6;        
        field[iEndPoint][jEndPoint] = -2; // -2 конечноая точка и путь от нё до начальной 0
        int iStartPoint = 4; // координаты начальной точки
        int jStartPoint = 1;
        field[iStartPoint][jStartPoint] = 0; // начальная точка
        System.out.println("Исходное поле, где: 0 - начальная точка, -1 - свободно для построения пути, -2 - конечная точка и путь, -3 - преграда"); 
        prnTwoDemArray(field);
        wave object = new wave();
        System.out.println("Построение волны");
        object.doWave(field);
        prnTwoDemArray(field);
        //int maxWave = object.doWave(field);
        int maxWave = 10; // сделать передачу значения из метода doWave
        System.out.println("Построение пути");
        object.doPath(field, iEndPoint, jEndPoint, maxWave);
        prnTwoDemArray(field);
    }
}