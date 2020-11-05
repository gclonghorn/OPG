package opg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class OPG {
    /*
       E -> E '+' T | T
       T -> T '*' F | F
       F -> '(' E ')' | 'i'
    */
    static int[][] priMatrix = new int[6][6]; //优先关系矩阵
    static Stack<Character> symbolStack=new Stack<Character>();
    static Map<Character,Integer> map=new HashMap<Character, Integer>();
    static void set_priMatrix()
    {
        map.put('+', 0);
        map.put('*', 1);
        map.put('i', 2);
        map.put('(', 3);
        map.put(')', 4);
        map.put('#', 5);
        priMatrix[0] = new int[]{1,-1,-1,-1,1,1};
        priMatrix[1] = new int[]{1, 1, -1, -1, 1, 1};
        priMatrix[2] = new int[]{1, 1, 10, 10, 1, 1};
        priMatrix[3] = new int[]{-1, -1, -1, -1, 0, 1};
        priMatrix[4] = new int[]{1, 1, 10, 10, 1, 1};
        priMatrix[5] = new int[]{-1, -1, -1, -1, -1, 0};
    }
    static void analyze(char out)
    {
        if(map.get(out)==null) //不能识别的符号
        {
            System.out.println("E");
            System.exit(0);
        }
        char in=symbolStack.peek();
        if(out=='#' && in=='#')return; //分析结束
        if(in=='N')  //若栈顶是非终结符,换成次栈顶
        {
            char tmp=symbolStack.pop();
            in=symbolStack.peek();
            symbolStack.push(tmp);
        }

        int relation=priMatrix[map.get(in)][map.get(out)];
        if(relation==-1||relation==0)//移进
        {
            symbolStack.push(out);
            System.out.println("I"+out);
        }
        else if(relation==10)//无法比较符号优先关系
        {
            System.out.println("E");
            System.exit(0);
        }
        else if(relation==1)//规约
        {
            StringBuilder sb= new StringBuilder();
            boolean success=false;
            for(;;) {
                if (symbolStack.peek() == '#') break;
                sb.append(symbolStack.pop());
                if(checkRule(sb.toString()))
                {
                    symbolStack.push('N');
                    success=true;
                    System.out.println("R");
                    break;
                }
            }
            if(success)analyze(out);
            else
                {
                    System.out.println("RE");
                    System.exit(0);
                }

        }
    }
    static boolean checkRule(String sb)
    {
         List<String> rules=List.of("N+N","N*N",")N(","i");
         return rules.contains(sb);
    }

    public static void main(String[] args) throws IOException {
        String filePath="F:\\软件系统分析\\untitled\\src\\opg\\input";
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        set_priMatrix();
        String line = null;
        while ((line = br.readLine()) != null)
        {
            symbolStack.clear();
            symbolStack.push('#');
            line=line+"#";
            //System.out.println(line.length());
            for (int i=0;i<line.length();i++)
            {
                char out=line.charAt(i);
                analyze(out);
            }
        }
        br.close();

    }
}
