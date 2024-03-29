package cn.cc.dawn.utils.entity;

import cn.cc.dawn.utils.constant.HttpHeadersConstant;
import cn.cc.dawn.utils.constant.NumberConstant;
import cn.cc.dawn.utils.exception.Code;
import cn.cc.dawn.utils.exception.ICode;
import cn.cc.dawn.utils.exception.UserException;
import cn.cc.dawn.utils.i.ICall;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
public class ResultE<E> implements IJson {

    /**
     * 全局code
     */
    private String code;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 本次行数
     */
    private long rowCount;
    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 总行数
     */
    private long totalCount;
    /**
     * 数据集合
     */
    private List<E> eList;

    /**
     * log日志追踪
     */
    private String mdc;

    /**
     * 系统中请求日志追踪
     *
     * @return
     */
    public String getMdc() {
        return MDC.get(HttpHeadersConstant.MDC_header);
    }

    /**
     * 执行 setSuccess方法 保证能捕获到代码中的异常
     */
    public ResultE<E> execute(final Consumer<ResultE<E>> consumer) {
        try {
            setICode(Code.A00000);
            consumer.accept(this);
        } catch (Exception e) {
            setException(e);
        }
        return this;
    }

    public ResultE<String> thread(final ICall iCall) {
        try {
            setICode(Code.T00000);
            RExecute rExecute = new RExecute(iCall, this);
            Thread thread = new Thread(rExecute);
            thread.start();
        } catch (Exception e) {
            setException(e);
        }
        ResultE<String> resultE = new ResultE<>();
        resultE.setException("已放入线程");
        return resultE;
    }

    public ResultE<E> call(final ICall iCall) {
        try {
            setICode(Code.A00000);
            iCall.call();
        } catch (Exception e) {
            setException(e);
        }
        return this;
    }

    /**
     * 1. 单行数据
     *
     * @param data
     * @return
     */
    public ResultE<E> setSuccess(final E data) {
        log.info("resut公共返回设置成功数据------");
        setICode(Code.A00000);
        if (Objects.nonNull(data)) {
            this.eList = Collections.singletonList(data);
        } else {
            this.eList = Collections.emptyList();
        }
        this.rowCount = this.eList.size(); // 设置结果集大小
        return this; // 保证链式请求，返回:this

    }

    /**
     * 2. page数据
     *
     * @param pageE
     * @return
     */
    public ResultE<E> setSuccess(PageE<E> pageE) {
        log.info("resut公共返回设置成功数据------");
        setICode(Code.A00000);
        if (Objects.nonNull(pageE)) {
            this.pageCount = pageE.getPageCount();
            this.totalCount = pageE.getTotalCount();
            setData(pageE.getEList());
        }
        this.rowCount = this.eList.size(); // 设置结果集大小
        return this; // 保证链式请求，返回:this
    }

    /**
     * 3.1 全部查询后需要重新逻辑计算的数据
     *
     * @param data
     * @param number
     * @param size
     * @return
     */
    public ResultE<E> setSuccess(final List<E> data, int number, int size) {
        setICode(Code.A00000);
        if (Objects.nonNull(data) && data.size() > 0) {
            this.totalCount = data.size();
            this.pageCount = new Double(Math.ceil(new Double(data.size()) / size)).intValue();
            setData(data.stream().skip((number - 1) * size).limit(size).collect(Collectors.toList()));
        }
        return this; // 保证链式请求，返回:this
    }

    /**
     * 3.2 全部查询后 不 需要重新逻辑计算的数据
     *
     * @param data
     * @return
     */
    public ResultE<E> setSuccess(final List<E> data) {
        setICode(Code.A00000);
        if (Objects.nonNull(data) && data.size() > 0) {
            this.totalCount = data.size();
            this.pageCount = NumberConstant.N_1;
            setData(data);
        }
        return this; // 保证链式请求，返回:this
    }

    /**
     * 设置返回数据
     *
     * @param data
     * @return
     */
    private ResultE<E> setData(final List<E> data) {
        eList = data;
        return this; // 保证链式请求，返回:this
    }

    /**
     * 空构造和code构造
     */
    public ResultE() {
    }

    public ResultE(ICode code) {
        this.code = code.name();
        this.exception = code.getComment();
    }

    /**
     * 设置异常信息
     *
     * @param exception 异常
     * @return 返回对象
     */
    public ResultE setException(String exception) {
        this.exception = exception;
        return this;
    }

    /**
     * 设置异常信息
     *
     * @param exception 异常
     * @return 返回对象
     */
    public ResultE setException(Exception e) {

        if (e instanceof ConstraintViolationException) {
            // 框架内 数据校验异常
            ConstraintViolationException exception = (ConstraintViolationException) e;
            String message = exception.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .findFirst()
                    .orElse(null);
            this.code = Code.A00004.name();
            this.exception = message;
        } else if (e instanceof ValidationException) {
            // java 原生校验的注解
            ValidationException validationException = (ValidationException) e;
            String localizedMessage = validationException.getLocalizedMessage();
            this.code = Code.A00004.name();
            this.exception = localizedMessage;

            // 其他api记录下,暂时没用
            String message = validationException.getMessage();
            Throwable throwable = validationException.getCause();
            StackTraceElement[] stackTraceElements = validationException.getStackTrace();
            Throwable[] throwables = validationException.getSuppressed();
            // 其他api记录下,暂时没用

        } else if (e instanceof UserException) {
            UserException userException = (UserException) e;
            this.code = userException.getCode().name();
            //this.exception = userException.getCode().getComment();
            this.exception = e.getMessage();
        } else {
            this.code = Code.A00001.name();
            this.exception = e.getMessage();
        }

        log.error("公共异常: {}", exception, e);
        return this;
    }

    /**
     * 设置code信息
     *
     * @param code
     * @return
     */
    public ResultE setICode(Code code) {
        this.code = code.name();
        this.exception = code.getComment();
        return this;
    }

    /**
     * 重写tostring为json
     *
     * @return
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
