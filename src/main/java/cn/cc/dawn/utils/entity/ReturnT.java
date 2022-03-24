package cn.cc.dawn.utils.entity;

/**
 * @author everforcc 2021-09-14
 */
public class ReturnT<T> {

    private boolean success;

    private Exception exception;

    private T data;

}
