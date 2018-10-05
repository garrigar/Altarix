public class Main {

    public static void main(String[] args) {
        testsA();
        testsB();
        CategoryC.task();
    }

    public static void testsA() {

        // ----A1----
        {
            System.out.println("A1");

            System.out.println("test 1");
            CategoryA.task1(1, 1, 0, -1, -1, -1, 0, 0);

            System.out.println("test 2");
            CategoryA.task1(0, 0, 0, 0, 0, 0, 0, 1);
        }
        // ----------

        // ----A2----
        {
            System.out.println("A2");

            System.out.println("test 1");
            int[][] a1 = {
                    {1, 3, 8},
                    {9, 2, 1},
                    {0, 3, 7}
            };
            CategoryA.task2(a1);

            System.out.println("test 2");
            int[][] a2 = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
            };
            CategoryA.task2(a2);

            System.out.println("test 3");
            int[][] a3 = {
                    {1, 1, 2},
                    {3, 5, 8},
                    {13, 21, 34}
            };
            CategoryA.task2(a3);
        }
        // ----------

        // ----A3----
        {
            System.out.println("A3");

            System.out.println("test 1");
            CategoryA.task3(4);

            System.out.println("test 2");
            CategoryA.task3(10);
        }
        // ----------

        // ----A4----
        {
            System.out.println("A4");

            System.out.println("test 1");
            int[] a1 = {1, 2, 3, 4, 5, 6};
            CategoryA.task4(a1, 5);

            System.out.println("test 2");
            int[] a2 = {1, 1, 1, 1};
            CategoryA.task4(a2, 2);

            System.out.println("test 3");
            int[] a3 = {78, 52, 846, 123, 455, 0};
            CategoryA.task4(a3, 3);
        }
        // ----------

        // ----A5----
        {
            System.out.println("A5");

            int[][] m1 = {
                    {1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                    {2, 2, 2, 2, 1, 2, 1, 2, 3, 1},
                    {2, 7, 7, 8, 1, 1, 2, 1, 1, 0},
                    {6, 7, 7, 8, 1, 2, 3, 4, 6, 8}
            };
            int[][] w1_1 = {
                    {1, 2, 1},
                    {1, 1, 2}
            };
            int[][] w1_2 = {{1, 1}};
            int[][] w1_3 = {
                    {1, 1},
                    {4, 6}
            };
            System.out.println("test 1_1");
            CategoryA.task5(m1, w1_1);
            System.out.println("test 1_2");
            CategoryA.task5(m1, w1_2);
            System.out.println("test 1_3");
            CategoryA.task5(m1, w1_3);

            int[][] m2 = {
                    {1, 1, 1},
                    {1, 1, 1},
                    {1, 1, 1}
            };
            int[][] w2 = {{2}};
            System.out.println("test 2");
            CategoryA.task5(m2, w2);
        }
        // ----------
    }

    public static void testsB() {

        // ----B1----
        {
            System.out.println("B1");

            System.out.println("test 1");
            CategoryB.task1("([ ] [{ }] ) [ ({}) ]({[]}) {[ ()] }");

            System.out.println("test 2");
            CategoryB.task1("     [ ] [{     }  ]      )    { []          } ( { }        )   [ ] ({ [  ] [ ] } )   { [      ()] }");

            System.out.println("test 3");
            CategoryB.task1("[{()}]");

            System.out.println("test 4");
            CategoryB.task1("     [ ] [{     }  ] (((())()()))   [ ] ({ [  ] [ ] } )   { [      ()] }");
        }
        // ----------

        // ----B2----
        {
            System.out.println("B2");

            System.out.println("test 1");
            CategoryB.task2(3);

            System.out.println("test 2");
            CategoryB.task2(5);

            System.out.println("test 3");
            CategoryB.task2(1);

            System.out.println("test 4");
            CategoryB.task2(10);
        }
        // ----------

        // ----B3----
        {
            System.out.println("B3");

            System.out.println("test 1");
            CategoryB.task3(3);

            System.out.println("test 2");
            CategoryB.task3(5);

            System.out.println("test 3");
            CategoryB.task3(10);
        }
        // ----------
    }
}
