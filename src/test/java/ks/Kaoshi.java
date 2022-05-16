package ks;

import org.junit.jupiter.api.Test;

public class Kaoshi {

    /**
     * 7.
     */

    @Test
    void t_5(){
        int b[][]={{1},{2,2},{2,2,2}};
        int sum = 1;
        for(int i=0;i<b.length;i++){
            for(int j=0;j<b[i].length;j++){
                sum*=b[i][j];
            }
        }
        System.out.println(sum);
    }

    @Test
    void t_10(){

        for(int i=0;i<3;i++){
            System.out.println(i);
        }
        //System.out.println(i);
    }

    @Test
    void t_11(){

        int i = 10;
        i = ++i;
        System.out.println(i);
        //System.out.println(i);
    }

}
