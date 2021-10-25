import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ExerciseCheck {
    /**
     * 比较两个文件的每一行答案是否一样
     * @param args args[0]和args[1]分别是两个文件的路径
     */
    public static void main(String[] args) {

        //输入的文件一
        String str1 = args[0];
        //输入的文件二
        String str2 = args[1];
        //存储正确的题目号
        List<Integer> list1 = new ArrayList<>();
        //存储错误的题目号
        List<Integer> list2 = new ArrayList<>();

        try {
            int n = 1;
            //统计正确和错误题目数
            int Correct_num = 0, Wrong_num = 0;
            BufferedReader in = new BufferedReader(new FileReader(str1));
            BufferedReader in1 = new BufferedReader(new FileReader(str2));
            String s = " ";
            String s1 = " ";
            while (s != null) {
                s = in.readLine();
                if (s != null && s1 != null) {
                    s = s.replaceAll(" ", "");
                    s1 = in1.readLine();
                    s1 = s1.replaceAll(" ", "");
                    if (s.equals(s1)) {
                        list1.add(n++);
                        Correct_num++;
                    } else {
                        list2.add(n++);
                        Wrong_num++;
                    }
                }
            }
            in.close();
            in1.close();
            System.out.print("Correct：" + Correct_num + "(");
            int i;
            for (i = 0; i < list1.size() - 1; i++){
                System.out.print(list1.get(i) + ",");
            }
            System.out.print(list1.get(i));
            System.out.println(")");
            System.out.print("Wrong：  " + Wrong_num + "(");
            for (i = 0; i < list2.size() - 1; i++){
                System.out.print(list2.get(i) + ",");
            }
            System.out.print(list2.get(i));
            System.out.println(")");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
