package cn.cc.dawn.utils.entity;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@Getter
@Setter
public class ResultE<E> implements IJson{

    private String exception;

    private List<E> eList;

    public ResultE<E> execute(final Consumer<ResultE<E>> consumer) {
        try {
            consumer.accept(this);
        } catch (Exception e) {
            exception = e.getMessage();
            log.error(e.getMessage(), e);
        }
        return this;
    }

    public ResultE<E> setSuccess(final List<E> data) {
        log.info("");
        eList = data;
        return this; // 保证链式请求，返回:this
    }

    public ResultE<E> setSuccess(final E data) {
        log.info("");
        if (Objects.nonNull(data)) {
            this.eList = Collections.singletonList(data);
        } else {
            this.eList = Collections.emptyList();
        }
        return this; // 保证链式请求，返回:this
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
