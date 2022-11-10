package Wavepath;

public class wave {
    void doWave(int arr[][]) {
        int collLength = arr.length;
        int rowLength = arr[0].length;
        for (int stapWave = 0; stapWave < rowLength*collLength; stapWave++) {
            for (int i=0; i<collLength; i++) {
                for (int j=0; j<rowLength; j++) {
                    if(arr[i][j] == stapWave) {
                        // если (в перделах поля) и (сосед.ячейка свободна)
                        if((i-1 >= 0) && (arr[i-1][j] == -1)) arr[i-1][j] = stapWave + 1; // up
                        if((j+1 < rowLength) && (arr[i][j+1] == -1)) arr[i][j+1] = stapWave + 1; // rigth
                        if((i+1 < collLength) && (arr[i+1][j] == -1)) arr[i+1][j] = stapWave + 1; // down
                        if((j-1 >= 0) && (arr[i][j-1] == -1)) arr[i][j-1] = stapWave + 1; // left
                    } 
                }
            }    
        }
    }
    void doPath(int arr[][],int iEndPath,int jEndPath) {
        int collLength = arr.length;
        int rowLength = arr[0].length;
        int minNumWave = 10; // !!! to do поиск максимального значения (пока условно взял 10 для конкретного примера)
        int iMinNumWave = iEndPath;
        int jMinNumWave = jEndPath;
        // поиск мин. значения волны вокруг конечной точки
        // если соседний элемент (в границах поля) и (его значение меньше минимального волны) и (больше 0 ибо то что меньше не волна)
        if((iEndPath-1 >= 0) && (arr[iEndPath-1][jEndPath] < minNumWave) && (arr[iEndPath-1][jEndPath] > 0)) {
            minNumWave = arr[iEndPath-1][jEndPath]; // up присваиваем минимальному
            iMinNumWave = iEndPath-1;
        }
        if((jEndPath+1 < rowLength) && (arr[iEndPath][jEndPath+1] < minNumWave) && (arr[iEndPath][jEndPath+1] > 0)) {
            minNumWave = arr[iEndPath][jEndPath+1]; // rigth
            iMinNumWave = iEndPath;
            jMinNumWave = jEndPath+1;              
        }
        if((iEndPath+1 < collLength) && (arr[iEndPath+1][jEndPath] < minNumWave) && (arr[iEndPath][jEndPath+1] > 0)) {
            minNumWave = arr[iEndPath+1][jEndPath]; // down
            iMinNumWave = iEndPath+1;
            jMinNumWave = jEndPath;
        }
        if((jEndPath-1 >= 0) && (arr[iEndPath][jEndPath-1] < minNumWave) && (arr[iEndPath][jEndPath-1] > 0)) {
            minNumWave = arr[iEndPath][jEndPath-1]; // left
            iMinNumWave = iEndPath;
            jMinNumWave = jEndPath-1;
        }
        int heightWawe = arr[iMinNumWave][jMinNumWave];
        arr[iMinNumWave][jMinNumWave] = -2;
        System.out.println("координаты точки пути " + iMinNumWave + " " + jMinNumWave);
        if (heightWawe > 1) doPath(arr, iMinNumWave, jMinNumWave);
        // System.out.println("координаты точки пути " + iMinNumWave + " " + jMinNumWave);
        
 
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
        int field[][] = {{-1,-1,-1,-1,-3,-1,-1},{-1,-1,-1,-1,-3,-1,-1},{-1,-1,-1,-1,-3,-1,-1},{-1,-1,-3,-1,-1,-1,-1},{-1,0,-3,-1,-1,-1,-1}};
        int iEndPoint = 1;
        int jEndPoint = 5;
        field[iEndPoint][jEndPoint] = -2; // -2 конечноая точка и путь от нё до начальной 0
        System.out.println("Исходное поле, где: 0 - начальная точка, -1 - свободно для построения пути, -2 - конечная точка и путь, -3 - преграда"); 
        prnTwoDemArray(field);
        wave object = new wave();
        System.out.println("Построение волны");
        object.doWave(field);
        prnTwoDemArray(field);
        System.out.println("Построение пути");
        object.doPath(field, iEndPoint, jEndPoint);
        prnTwoDemArray(field);
    }
}