package cn.cc.dawn.config.handler;

import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.enums.BaseEnum;
import cn.cc.dawn.utils.exception.AppCode;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 目前全局枚举都使用 int作为值来存入数据库
 * 参考 https://blog.csdn.net/H900302/article/details/109286827
 * @param <E>
 */
public class EnumIntegerHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    private Class<E> type;
    private E[] enums;


    public EnumIntegerHandler(Class<E> type) {
        AppCode.A00304.assertHasTrue(RObjectsUtils.nonNull(type));
        this.type = type;

        /**
         * 拿到枚举的集合
         */
        this.enums = type.getEnumConstants();

        if (RObjectsUtils.isNull(this.enums)) {
            throw AppCode.A00304.toUserException(type.getSimpleName() + " 没有枚举值");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E e, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, (Integer) e.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 根据数据库存储类型决定获取类型，本 类 中数据库中存放 int 类型
        int key = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            /**
             * 根据下坐标来处理
             */
            return getEnumByCode(key);
        }
    }

    /**
     * 索引
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    /**
     * 存储过程
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }

    private E getEnumByCode(int code) {
        for(E e : enums) {
            if(e.getCode().equals(code)) {
                return e;
            }
        }
        throw AppCode.A00304.toUserException("未知的枚举类型：" + code);
    }


}
