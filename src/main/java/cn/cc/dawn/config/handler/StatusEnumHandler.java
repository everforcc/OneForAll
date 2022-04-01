package cn.cc.dawn.config.handler;

import cn.cc.dawn.utils.enums.impl.StatusEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理枚举和数据库的映射
 * @author guokailong 2021-09-07
 * @Deprecated 使用 cn.cc.dawn.config.handler.EnumIntegerHandler
 */
@Deprecated
public class StatusEnumHandler extends BaseTypeHandler<StatusEnum> {


    /**
     * 用于定义设置参数时，该如何把Java类型的参数转换为对应的数据库类型
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, StatusEnum parameter, JdbcType jdbcType)
            throws SQLException {
        // baseTypeHandler已经帮我们做了parameter的null判断
        // 第二个参数 : 存入到数据库中的值
        //ps.setString(i, parameter.comment);
        ps.setInt(i, parameter.comment);
    }

    /**
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     */
    @Override
    public StatusEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {

        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型
        int key = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的key值，定位StatusEnum子类
            return StatusEnum.values()[key];
        }
    }

    /**
     * 用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     */
    @Override
    public StatusEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型

        int key = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的key值，定位StatusEnum子类
            return StatusEnum.values()[key];
        }
    }

    /**
     * 用定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     */
    @Override
    public StatusEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型
        //String key = cs.getString(columnIndex);
        int key = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的key值，定位StatusEnum子类
            return StatusEnum.values()[key];
        }
    }

}
