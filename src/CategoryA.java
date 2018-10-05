import java.util.Arrays;

public class CategoryA {

    /** Метод, определяющий, принадлежит ли точка тругольнику
     * Передаются попарно координаты трёх вершин треугольника и координаты точки
     *
     * Идея состоит в поочередном обходе сторон треугольника, зафикисировав направление обхода,
     * рассматриваем стороны как прямые, смотрим, по какую сторону находится точка (по знаку k1, k2, k3).
     * Если все три числа одного знака, то точка внутри треугольника, если какое-то число равно 0, то точка лежит на стороне,
     * иначе - точка вне треугольника.
     */
    public static void task1(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy) {
        // Вывод на экран входных данных для наглядности и удобства проверки
        System.out.println("Input:\nA(" + ax + "; " + ay + "), B(" + bx + "; " + by + "), " +
                "C(" + cx + "; " + cy + "), D(" + dx + "; " + dy + ")");
        System.out.println("Output:");
        // ---------
        
        // Простейшая проверка на вырожденность треугольника
        if ((ax == bx && bx == cx) || (ay == by && by == cy)) {
            System.out.println("Bad triangle");
        } else {
            double k1, k2, k3;
            k1 = (ax - dx) * (by - ay) - (bx - ax) * (ay - dy);
            k2 = (bx - dx) * (cy - by) - (cx - bx) * (by - dy);
            k3 = (cx - dx) * (ay - cy) - (ax - cx) * (cy - dy);
            if ((k1 >= 0 && k2 >= 0 && k3 >= 0) || (k1 <= 0 && k2 <= 0 && k3 <= 0)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    /**
     * Метод подсчета абсолютной разности между суммой главной и побочной диагоналей квадратной матрицы
     * @param a - квадратная матрица
     */
    public static void task2(int[][] a){
        // Вывод на экран входных данных для наглядности и удобства проверки
        System.out.println("Input:");
        for (int[] as : a) {
            for (int i : as) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("Output:");
        // ---------

        int n = a.length;   // размер матрицы
        int mainSum = 0;    // сумма главной диагонали
        int submainSum = 0; // сумма побочной диагонали
        for (int i = 0; i < n; i++){
            mainSum += a[i][i];
            submainSum += a[i][n - 1 - i];
        }
        System.out.println(Math.abs(mainSum - submainSum));
    }

    /**
     * Метод, печатающий "лесенку"
     * @param n - число элементов в основании
     */
    public static void task3(int n) {
        // Вывод на экран входных данных для наглядности и удобства проверки
        System.out.println("Input:\n" + n);
        System.out.println("Output:");
        // ---------

        for (int i = 1; i <= n; ++i){
            // заполнение пробелами
            for (int j = 1; j <= (n - i); ++j){
                System.out.print(' ');
            }
            // заполнение #
            for (int j = 1; j <= i; ++j){
                System.out.print("#");
            }
            System.out.println();
        }
    }

    /**
     * Метод, подсчитывающий кол-во пар (i, j) в массиве a, таких что i < j и что a[i]+a[j] делится на число k
     * @param a - массив
     * @param k - число k
     */
    public static void task4(int[] a, int k) {
        // Вывод на экран входных данных для наглядности и удобства проверки
        System.out.println("Input:");
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("k = " + k);
        System.out.println("Output:");
        // ---------


        int count = 0; // количество искомых пар
        // проходим по всем возможным парам (i, j), таким что i < j, с помощью двух циклов
        for (int i = 0; i < a.length; ++i){
            for (int j = i + 1; j < a.length; ++j){
                // если сумма делится на k, увеличиваем счётчик
                if ((a[i] + a[j]) % k == 0){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * Метод, отыскивающий левую верхнюю координату заданного "окна" в матрице
     * @param matrix - матрица
     * @param window - "окно"
     */
    public static void task5(int[][] matrix, int[][] window) {
        // Вывод на экран входных данных для наглядности и удобства проверки
        System.out.println("Input:\nmatrix");
        for (int[] as : matrix) {
            for (int i : as) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("window");
        for (int[] as : window) {
            for (int i : as) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("Output:");
        // ---------

        // Если какая-то из матриц имеет 0 строк, то она неправильно задана
        if (matrix.length == 0 || window.length == 0) {
            System.out.println("Bad data");
        } else
            // Если какая-то из матриц имеет 0 столбцов, то она неправильно задана
            if (matrix[0].length == 0 || window[0].length == 0) {
                System.out.println("Bad data");
            } else {
                // размер матриц ненулевой

                int n = matrix.length;      // кол-во строк исходной матрицы
                int m = matrix[0].length;   // кол-во столбцов исходной матрицы
                int k = window.length;      // кол-во строк окна
                int f = window[0].length;   // кол-во столбцов окна

                // Если размеры окна не меньше размеров исходной матрицы, то это неверно
                if (k >= n || f >= m) {
                    System.out.println("Window must be smaller than the main matrix");
                } else {
                    // всё задано корректно

                    int ans_i = -1; // i-координата будущего ответа
                    int ans_j = -1; // j-координата будущего ответа
                    boolean found = false; // найден ли ответ

                    // ищем левый верхний угол возможного ответа -- проходим по всем клеткам исходной матрицы,
                    // которые в принципе могут являться левым верхним углом окна
                    for (int mi = 0; !found && (mi <= n - k); ++mi) {
                        for (int mj = 0; !found && (mj <= m - f); ++mj) {
                            if (matrix[mi][mj] == window[0][0]) {
                                // нашли подходящиий левый верхний угод

                                // фиксируем его координаты
                                ans_i = mi;
                                ans_j = mj;

                                // Надо проверить остальные элементы окна

                                // переменная, true если элементы заданного окна и найденного кандидата на ответ совпадают
                                boolean ok = true;
                                for (int i = 0; ok && (i < k); ++i) {
                                    for (int j = 0; ok && (j < f); ++j) {
                                        if (matrix[mi + i][mj + j] != window[i][j]) {
                                            ok = false;
                                        }
                                    }
                                }
                                if (ok) {
                                    found = true; // если все элементы совпали, ответ найден
                                }
                            }
                        }
                    }
                    if (found) {
                        System.out.println("(" + ans_i + ", " + ans_j + ")");
                    } else {
                        System.out.println("FAIL");
                    }
                }
            }
    }
}
