

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Output {
    /** 虚拟机指令集 */
    Map<String, Integer> operations = Operation.getOperations();
    List<Global> globalTable;
    List<Function> functionTable;
    Function _start;
    List<Byte> output;
    //必输出项
    int magic=0x72303b3e;
    int version=0x00000001;
    public Output(List<Global> globalTable, List<Function> functionTable, Function _start){
        this.globalTable = globalTable;
        this.functionTable = functionTable;
        this._start = _start;
        this.output = new ArrayList<>();
    }

    public List<Byte> transfer(){
        //放magic和version
        addInt(4, magic);
        addInt(4, version);

        //放globals.count
        addInt(4, globalTable.size());


        //放全局变量
        for(Global global:globalTable){
            //1.is_const
            addInt(1, global.getIsConst());

            //2.value.count
            //3.value.items
            //如果是全局变量或者全局常量
            if(global.getItems() == null){
                addInt(4, 8);
                addLong(8, 0L);
            }
            //如果是函数
            else{
                addInt(4, global.getItems().length());
                addString(global.getItems());
            }
        }


        //放functions.count
        addInt(4, functionTable.size() + 1);

        //放_start
        transferFunction(_start);

        //放其他函数
        for(Function function:functionTable)
            transferFunction(function);


        return output;
    }

    private void transferFunction(Function function){
        //1.name
        addInt(4, function.getId());
        //2.ret_slots
        addInt(4, function.getRetSlots());
        //3.param_slots
        addInt(4, function.getParamSlots());
        //4.loc_slots
        addInt(4, function.getLocSlots());
        //5.body.count
        addInt(4, function.getBody().size());
        //6.body.items
        List<Instruction> instructions = function.getBody();
        for(Instruction instruction:instructions){
            String op = instruction.getOp();
            //指令
            int opInt = operations.get(op);
            addInt(1, opInt);
            //操作数
            if(instruction.getX() != -1){
                //只有push的操作数是64位
                if(opInt == 1)
                    addLong(8, instruction.getX());
                else
                    addInt(4,  (int) instruction.getX());
            }
        }
    }

    private void addInt(int length, int x){
        int start = 8 * (length-1);
        for(int i = 0 ; i < length; i++){
            int part = x >> ( start - i * 8 ) & 0xFF;
            byte b = (byte) part;
            output.add(b);
        }
    }

    private void addLong(int length, long x){
        int start = 8 * (length-1);
        for(int i = 0 ; i < length; i++){
            long part = x >> ( start - i * 8 ) & 0xFF;
            byte b = (byte) part;
            output.add(b);
        }
    }


    private void addString(String x) {
        for (int i = 0; i < x.length();i++){
            char c = x.charAt(i);
            output.add((byte) c);
        }
    }
}
