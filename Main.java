package com.company;
import jdk.dynalink.beans.StaticClass;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String file = "Calc1.stk";
        Path path = Paths.get(file);
        List<String> lines = Files.readAllLines(path);
        String expression="";
        int aux = 0;
        while(aux< lines.size()){
            expression=expression + lines.get(aux)+" ";
            aux++;
        }
        System.out.println(math(expression));

    }

    public static double math(String expr) {
        int start = 0;
        Stack stack = new Stack();
        do
        {
            int space = expr.substring(start).indexOf(' ');
            int end = space == -1 ? expr.length() : start + space;
            String current = expr.substring(start,end);
            if("+-*/".indexOf(current.charAt(0)) != -1){
                Double aux = (Double) stack.pop();
                Double aux1 = (Double) stack.pop();
                stack.push(operate(current.charAt(0),aux1,aux));
            }
            else{
                stack.push(Double.parseDouble(current));
            }
            start=end+1;
        }while(start<expr.length());

        Double result = (Double) stack.pop();

        while(!stack.isEmpty()){
            Double current= (Double) stack.pop();
            result = current > result ? current:result;
        }
        return result;
    }
    public static double operate(char op,Double aux,Double aux1) {
        Hashtable<Character,Double> opHash = new Hashtable<>();

        opHash.put('+',aux + aux1);
        opHash.put('-',aux - aux1);
        opHash.put('*',aux * aux1);
        opHash.put('/',aux / aux1);

        return opHash.get(op);
    }
}
