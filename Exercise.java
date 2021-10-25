import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Exercise {

    /**
     * 约分方法
     * @param a 分子
     * @param b 分母
     * @return 约分结果，且为真分数形式
     */
    public static String yuefen(int a, int b) {
        String s = new String("");
        if (a / b == 0) {
            s += Integer.toString(a / getContract(a, b)) + "/" + Integer.toString(b / getContract(a, b));
        } else if(a * b > 0){
            //i是真分数整数部分
            int i = a / b, j = a % b;
            //temp为真分数分数部分分子与分母的最大公约数
            int temp = getContract(b, j);
            s += (j == 0 ? Integer.toString(i) : Integer.toString(i) + "'" + Integer.toString(j / temp) + "/" + Integer.toString(b / temp));
        }else {
            if(a < 0) a = -a;
            else b = -b;
            //i是真分数整数部分
            int i = a / b, j = a % b;
            //temp为真分数分数部分分子与分母的最大公约数
            int temp = getContract(b, j);
            s += (j == 0 ? "-" + Integer.toString(i) : "-" + Integer.toString(i) + "'" + Integer.toString(j / temp) + "/" + Integer.toString(b / temp));
        }
        return s;
    }

    /**
     * 求两数的最大公约数
     * @param a
     * @param b
     * @return a 和 b 的最大公约数
     */
    public static int getContract(int a, int b) {

        if (b == 0) {
            return a;
        } else {
            int r = a % b;
            a = b;
            b = r;
            return getContract(a, b);
        }
    }

    /**
     * 生成题目和答案
     * @throws IOException
     */
    public static void exercise(int nums, int max) throws IOException {
        //m和n用于载入随机数，以此生成随机的算数和符号
        int m, n;
        int mark = 0;
        //x和y分别是每一题的分数形式答案的分子分母
        int x = 1, y = 1;
        //记录每一题的答案
        String ans = "";

        //这个数组用来记录分子和分母
        int[][] a = new int[3][2];
        //这个数组用来记录符号
        int[] b = new int[2];
        //表示符号的字符串
        String s2 = "+-*÷";

        //写入题目
        StringBuffer strb = new StringBuffer(10);
        File f = new File("D:\\idea project\\exercise\\src\\exercise");
        BufferedWriter file = new BufferedWriter(new FileWriter(f));
        //写入答案
        StringBuffer strb1 = new StringBuffer(10);
        File f1 = new File("D:\\idea project\\exercise\\src\\answer");
        BufferedWriter file1 = new BufferedWriter(new FileWriter(f1));

        //设置一个hash函数存储题目，判断是否重复
        Set<String> hash = new HashSet<>();
        //生成nums道题目
        for (int i = 0; i < nums; i++) {
            mark = 0;
            m = new Random().nextInt(10) + 1;

            //此为式子第一位数字
            strb.append((i + 1) + ".  " + m + "");
            a[0][0] = m;
            a[0][1] = 1;
//            n = new Random().nextInt(100);
            //num+1 表示此式子的数字个数
            int num = new Random().nextInt(2) + 1;
            //生成一条式子
            for (int j = 0; j < num; j++) {
                m = new Random().nextInt(max) + 1;
                n = new Random().nextInt(100);
                //随机生成一个整数或分数
                if (new Random().nextInt(3) != 0) {
                    strb.append(" " + s2.charAt(n % 4) + " " + String.valueOf(m));
                    a[j + 1][0] = m;
                    b[j] = n % 4;
                    a[j + 1][1] = 1;
                } else {
                    m = new Random().nextInt(10) + 1;
                    int ran = new Random().nextInt(max * m) + 1;
                    strb.append(" " + s2.charAt(n % 4) + " " + String.valueOf(ran) + "/" + String.valueOf(m));
                    a[j + 1][0] = ran;
                    b[j] = n % 4;
                    a[j + 1][1] = m;
                }
            }
            //假如题目已存在，重新出一题
            if(hash.contains(strb)) i--;
            //否则继续出
            else{
                hash.add(new String(strb));
                //计算答案的分子与分母
                if (num == 1) {
                    switch (b[0]) {
                        case 0: {
                            x = a[0][0] * a[1][1] + a[0][1] * a[1][0];
                            y = a[0][1] * a[1][1];
                            break;
                        }
                        case 1: {
                            x = a[0][0] * a[1][1] - a[0][1] * a[1][0];
                            y = a[0][1] * a[1][1];
                            break;
                        }
                        case 2: {
                            x = a[0][0] * a[1][0];
                            y = a[0][1] * a[1][1];
                            break;
                        }
                        case 3: {
                            x = a[0][0] * a[1][1];
                            y = a[0][1] * a[1][0];
                            break;
                        }
                    }
                } else {
                    if (b[0] == 3) {
                        b[0] = 2;
                        a[1][0] = a[1][0] + a[1][1];
                        a[1][1] = a[1][0] - a[1][1];
                        a[1][0] = a[1][0] - a[1][1];
                    }
                    if (b[1] == 3) {
                        b[1] = 2;
                        a[2][0] = a[2][0] + a[2][1];
                        a[2][1] = a[2][0] - a[2][1];
                        a[2][0] = a[2][0] - a[2][1];
                    }
                    if (b[1] < 2 && b[0] < 2) {
                        if (b[0] == 0 && b[1] == 0) {
                            x = a[0][0] * a[1][1] * a[2][1] + a[1][0] * a[0][1] * a[2][1] + a[2][0] * a[0][1] * a[1][1];
                            y = a[0][1] * a[1][1] * a[2][1];
                        } else if (b[0] == 1 && b[1] == 1) {
                            x = a[0][0] * a[1][1] * a[2][1] - a[1][0] * a[0][1] * a[2][1] - a[2][0] * a[0][1] * a[1][1];
                            y = a[0][1] * a[1][1] * a[2][1];
                        } else if (b[0] == 0 && b[1] == 1) {
                            x = a[0][0] * a[1][1] * a[2][1] + a[1][0] * a[0][1] * a[2][1] - a[2][0] * a[0][1] * a[1][1];
                            y = a[0][1] * a[1][1] * a[2][1];
                        } else if (b[0] == 1 && b[1] == 0) {
                            x = a[0][0] * a[1][1] * a[2][1] - a[1][0] * a[0][1] * a[2][1] + a[2][0] * a[0][1] * a[1][1];
                            y = a[0][1] * a[1][1] * a[2][1];
                        }
                    }
                    else if (b[0] == 2 && b[1] == 2) {
                        x = a[0][0] * a[1][0] * a[2][0];
                        y = a[0][1] * a[1][1] * a[2][1];
                    }
                    else if (b[0] == 2) {
                        if (b[1] == 0) {
                            x = a[0][0] * a[1][0] * a[2][1] + a[0][1] * a[1][1] * a[2][0];
                            y = a[0][1] * a[1][1] * a[2][1];
                        } else {
                            x = a[0][0] * a[1][0] * a[2][1] - a[0][1] * a[1][1] * a[2][0];
                            y = a[0][1] * a[1][1] * a[2][1];
                        }
                    } else if(b[1] == 2){
                        if (b[0] == 0) {
                            x = a[0][0] * a[1][1] * a[2][1] + a[0][1] * a[1][0] * a[2][0];
                            y = a[0][1] * a[1][1] * a[2][1];
                        } else {
                            x = a[0][0] * a[1][1] * a[2][1] - a[0][1] * a[1][0] * a[2][0];
                            y = a[0][1] * a[1][1] * a[2][1];
                        }
                    }
                }
                ans = yuefen(x, y);
                if(x*y < 0) {
                    i--;
                }else{
                    strb.append(" " + "=");
                    System.out.println(strb);
                    //写入题目
                    file.write(new String(strb));
                    file.newLine();
                    //写入答案
                    strb1.append((i + 1) + ".  " + ans);
                    file1.write(new String(strb1));
                    file1.newLine();
                    //清除两字符串内容，用以循环利用
                    strb1.delete(0, strb1.capacity());

                }
                strb.delete(0, strb.capacity());

            }





        }
        file1.close();
        file.close();
    }
}
