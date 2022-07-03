package jdk;

import org.junit.jupiter.api.Test;

public class LocalVariable {

    @Test
    public void test_1(){
        boolean type = false;
        System.out.println("1." + type);
        test_1_1(type);
        System.out.println("3." + type);
    }

    public void test_1_1(boolean type){
        type = true;
        System.out.println("2." + type);
    }

}
