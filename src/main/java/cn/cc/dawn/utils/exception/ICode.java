package cn.cc.dawn.utils.exception;

import cn.cc.dawn.utils.check.ObjectUtils;

import java.util.Collection;
import java.util.Objects;

public interface ICode {


    String name();
    String getComment();


    /**
     * 枚举的异常信息
     * @return
     */
    default UserException toUserException() {
        return new UserException(this, this.getComment());
    }
    /**
     * 自定义异常信息
     * @param exception
     * @return
     */
    default UserException toUserException(final String exception) {
        return new UserException(this, exception);
    }
    /**
     * 自定义异常信息并格式化
     * @param pattern
     * @param args
     * @return
     */
    default UserException toUserException(final String pattern, final Object... args) {
        return new UserException(this, String.format(pattern, args));
    }


    /**
     *
     * @param value
     */
    default void assertHasTrue(final boolean value) {
        if (!value) {
            throw toUserException();
        }
    }
    /**
     * 判断结果是否为真，自定义异常信息
     * @param value
     * @param exps
     * @param args
     */
    default void assertHasTrue(final boolean value, final String exps, final Object... args) {
        if (!value) {
            throw toUserException(exps, args);
        }
    }

    default void assertHasUpdate(final int result) {
        if (!(1==result)) {
            throw toUserException();
        }
    }
    default void assertHasInsert(final int result) {
        if (!(1==result)) {
            throw toUserException();
        }
    }


    default void assertNonNull(final Object value) {
        if(ObjectUtils.isNull(value)){
            throw toUserException();
        }
    }
//    default void assertNonNull(final Object value, final String exps, final Object... args) {
//        if (Objects.isNull(value)) {
//            throw toUserException(exps, args);
//        }
//    }
//    default void assertNonBlank(final String value) {
//        if (Objects.isNull(value) || value.trim().isEmpty()) {
//            throw toUserException();
//        }
//    }
//    default void assertNonBlank(final String value, final String exps, final Object... args) {
//        if (Objects.isNull(value) || value.trim().isEmpty()) {
//            throw toUserException(exps, args);
//        }
//    }
//    default void assertNonEmpty(final Collection<?> value) {
//        if (Objects.isNull(value) || value.isEmpty()) {
//            throw toUserException();
//        }
//    }
//    default void assertNonEmpty(final Collection<?> value, final String exps, final Object... args) {
//        if (Objects.isNull(value) || value.isEmpty()) {
//            throw toUserException(exps, args);
//        }
//    }
//    default void assertNonEmpty(final Object[] value, final String exps, final Object... args) {
//        if (Objects.isNull(value) || value.length == 0) {
//            throw toUserException(exps, args);
//        }
//    }
}
