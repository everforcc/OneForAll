/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-07 11:21
 * Copyright
 */

package cn.cc.dawn.utils.entity;

import cn.cc.dawn.utils.i.ICall;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@AllArgsConstructor
@NoArgsConstructor
public class RExecute<E> implements Runnable{

    //private Consumer<ResultE<E>> consumer;

    private ICall iCall;

    private ResultE<E> resultE;

    @Override
    public void run() {
        //consumer.accept(resultE);
        iCall.call();
    }

}
