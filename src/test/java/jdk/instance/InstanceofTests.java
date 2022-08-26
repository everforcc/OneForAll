/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-26 10:57
 * Copyright
 */

package jdk.instance;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class InstanceofTests {

    public static void main(String[] args) {
        List<InstanceofBase> instanceofBaseList = new ArrayList<>();

        InstanceofBase instanceofBase = new InstanceofBase();
        instanceofBase.setBase("base");

        InstanceofA instanceofA = new InstanceofA();
        BeanUtils.copyProperties(instanceofBase, instanceofA);
        instanceofBaseList.add(instanceofA);

//        System.out.println(instanceofA);
//
        for (InstanceofBase i : instanceofBaseList) {

            if (i instanceof InstanceofA) {
                InstanceofA iA = (InstanceofA) i;
                iA.setA("A");
                System.out.println("if: \r\n" + iA.toString());
            } else {
                System.out.println("else");
            }
        }

        InstanceofB instanceofB = new InstanceofB();
        InstanceofBB instanceofBB = new InstanceofBB();
        System.out.println("1_" + (instanceofB instanceof InstanceofBase));
        System.out.println("2_" + (instanceofBB instanceof InstanceofBase));

    }

}
