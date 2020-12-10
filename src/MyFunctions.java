

import java.util.List;

public class MyFunctions {
    //运算符指令
    //目前只支持int，double
    public static void operatorInstructions(TokenType calculate, List<Instruction> instructions, String type) throws AnalyzeError{
        Instruction instruction;
        switch (calculate) {
            case FAN:
                if(type.equals("int"))
                    instruction = new Instruction("neg.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("neg.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);
                break;
            //+
            case PLUS:
                if(type.equals("int"))
                    instruction = new Instruction("add.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("add.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);
                break;
            //-
            case MINUS:
                if(type.equals("int"))
                    instruction = new Instruction("sub.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("sub.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);
                break;
            //*
            case MUL:
                if(type.equals("int"))
                    instruction = new Instruction("mul.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("mul.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);
                break;
            ///
            case DIV:
                if(type.equals("int"))
                    instruction = new Instruction("div.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("div.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);
                break;
            //==
            case EQ:
                if(type.equals("int"))
                    instruction = new Instruction("cmp.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("cmp.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);

                instruction = new Instruction("not", -1);
                instructions.add(instruction);
                break;
            //!=
            case NEQ:
                if(type.equals("int"))
                    instruction = new Instruction("cmp.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("cmp.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);
                break;
            //<
            case LT:
                if(type.equals("int"))
                    instruction = new Instruction("cmp.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("cmp.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);

                instruction = new Instruction("set.lt", -1);
                instructions.add(instruction);
                break;
            //>
            case GT:
                if(type.equals("int"))
                    instruction = new Instruction("cmp.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("cmp.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);

                instruction = new Instruction("set.gt", -1);
                instructions.add(instruction);
                break;
            //<=
            case LE:
                if(type.equals("int"))
                    instruction = new Instruction("cmp.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("cmp.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);

                instruction = new Instruction("set.gt", -1);
                instructions.add(instruction);
                instruction = new Instruction("not", -1);
                instructions.add(instruction);
                break;
            //>=
            case GE:
                if(type.equals("int"))
                    instruction = new Instruction("cmp.i", -1);
                else if(type.equals("double"))
                    instruction = new Instruction("cmp.f", -1);
                else
                    throw new AnalyzeError(ErrorCode.InvalidInput);
                instructions.add(instruction);

                instruction = new Instruction("set.lt", -1);
                instructions.add(instruction);
                instruction = new Instruction("not", -1);
                instructions.add(instruction);
                break;
            default:
                break;
        }

    }

    /**
     * 根据函数名字得到函数存储的id
     * @param name
     * @param functionTable
     * @return
     */
    public static int getFunctionId(String name, List<Function> functionTable){
        for (int i=0 ; i<functionTable.size(); i++) {
            if (functionTable.get(i).getName().equals(name)) return i;
        }
        return -1;
//        for (Function function : functionTable) {
//            if (function.getName().equals(name)) return function.getId();
//        }
//        return -1;
    }

    /**
     * 判断函数有没有返回值
     * @param name
     * @param functionTable
     * @return
     */
    public static boolean functionHasReturn(String name, List<Function> functionTable) {
        //如果是库函数
        if (name.equals("getint") || name.equals("getdouble") || name.equals("getchar"))
                return true;
        //如果是自定义函数
        for (Function function : functionTable) {
            if (function.getName().equals(name)) {
                if (function.getReturnType().equals("int") || function.getReturnType().equals("double")) return true;
            }
        }
        return false;
    }
}
