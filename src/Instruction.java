

import java.util.Objects;

public class Instruction {
    //操作
    String op;
    //参数
//    Integer x;

    long x;

    public Instruction(String op, long x){
        this.op = op;
        this.x = x;
//        this.isLong = 0;
    }

//    public Instruction(String op){
//        this.op = op;
//        this.x = null;
//        this.isLong = 0;
//    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public long getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

//    public long getIsLong() {
//        return isLong;
//    }

//    public void setIsLong(long isLong) {
//        this.isLong = isLong;
//    }

    @Override
    public String toString() {
            return "" + op + " " + x + '\n';
    }

}
