
public class ExerciseCreate  {

    /**
     * 生成args[0]个算式，args[1]为每个数字最大值
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Exercise.exercise(Integer.valueOf(args[0]),Integer.valueOf(args[1]));
    }
}
