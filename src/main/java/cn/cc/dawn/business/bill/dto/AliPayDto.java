/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-09 11:48
 * Copyright
 */

package cn.cc.dawn.business.bill.dto;

import com.alibaba.fastjson.JSONObject;
import com.github.crab2died.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 支付宝导出账单excle对象
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AliPayDto {

    @ExcelField(title = "交易号", order = 1)
    private String billNo;

    @ExcelField(title = "商家订单号", order = 2)
    private String itemNo;

    @ExcelField(title = "交易创建时间", order = 3)
    private String createDate;

    @ExcelField(title = "付款时间", order = 4)
    private String payDate;

    @ExcelField(title = "最近修改时间", order = 5)
    private String modifyDate;

    @ExcelField(title = "交易来源地", order = 6)
    private String payAddress;

    @ExcelField(title = "类型", order = 7)
    private String type;

    @ExcelField(title = "交易对方", order = 8)
    private String payObj;

    @ExcelField(title = "商品名称", order = 9)
    private String goodsName;

    @ExcelField(title = "金额（元）", order = 10)
    private String amount;

    @ExcelField(title = "收/支", order = 11)
    private String expend;

    @ExcelField(title = "交易状态", order = 12)
    private String payStatus;

    @ExcelField(title = "服务费（元）", order = 13)
    private String serviceAmount;

    @ExcelField(title = "成功退款（元）", order = 14)
    private String successRefund;

    @ExcelField(title = "备注", order = 15)
    private String remark;

    @ExcelField(title = "资金状态", order = 16)
    private String billStatus;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
