import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printMatrix(int[][] matrix, int n) {
        System.out.println("______________________________");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if ((i + j) % 2 == 0)
                    System.out.print(ANSI_BLACK + matrix[i][j] + "\t");
                else
                    System.out.print(ANSI_WHITE + matrix[i][j] + "\t");
            System.out.println();
        }
        System.out.print(ANSI_RESET);
        System.out.println("______________________________");
    }

    public static void quickSort(int[] array, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder, rightMarker = rightBorder;
        int pivot = array[(leftMarker + rightMarker) / 2];
        do{
            while(array[leftMarker] < pivot)
                leftMarker++;
            while (array[rightMarker] > pivot)
                rightMarker--;
            if (leftMarker <= rightMarker){
                if (leftMarker < rightMarker){
                    int tmp = array[leftMarker];
                    array[leftMarker] = array[rightMarker];
                    array[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while(leftMarker <= rightMarker);
        if (leftMarker < rightBorder)
            quickSort(array, leftMarker, rightBorder);
        if (leftBorder < rightMarker)
            quickSort(array, leftBorder, rightMarker);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line;
        int t = 2, n = 0;
        int[][] matrix = new int[8][8];
        int[] array = new int[32];
        Random rnd = new Random();
        System.out.println("Генерирую случайную матрицу, результат:");
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                matrix[i][j] = Math.abs(rnd.nextInt() % 101);
        printMatrix(matrix, 8);
        System.out.println("Числа какого цвета отсортировать? (ч - черный, б - белый)");
        while (t == 2) {
            line = scan.nextLine();
            switch (line) {
                case "Б":
                case "б":
                    t = 1;
                    break;
                case "Ч":
                case "ч":
                    t = 0;
                    break;
                default:
                    System.out.println("Чисел такого цвета в матрице нет, введите корректное значение (ч - черный, б - белый)");
            }
        }
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if ((i + j) % 2 == t) {
                    array[n] = matrix[i][j];
                    n++;
                }
        System.out.println("Из чисел такого цвета получился массив:");
        System.out.println(Arrays.toString(array));
        quickSort(array, 0, 31);
        System.out.println("Сортирую массив, результат:");
        System.out.println(Arrays.toString(array));
    }
}