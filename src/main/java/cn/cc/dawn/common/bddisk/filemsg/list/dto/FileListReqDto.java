/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-27 22:17
 * Copyright
 */

package cn.cc.dawn.common.bddisk.filemsg.list.dto;

import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * <a href="https://pan.baidu.com/union/doc/nksg0sat9">...</a>
 * 请求文件列表接口
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileListReqDto extends CommonFiledDto {

    /**
     * 本接口固定为list
     */
    @NotEmpty
    private final String method = "list";

    /**
     * 接口鉴权参数
     */
    @NotEmpty
    private String access_token;

    /**
     * %2F%E6%B5%8B%E8%AF%95%E7%9B%AE%E5%BD%95
     * <p>
     * 需要list的目录，以/开头的绝对路径, 默认为/
     * 路径包含中文时需要UrlEncode编码
     * 给出的示例的路径是/测试目录的UrlEncode编码
     */
    private String dir;

    /**
     * 排序字段：默认为name；
     * time表示先按文件类型排序，后按修改时间排序；
     * name表示先按文件类型排序，后按文件名称排序；
     * size表示先按文件类型排序，后按文件大小排序。
     */
    private String order;

    /**
     * 默认为升序，设置为1实现降序 （注：排序的对象是当前目录下所有文件，不是当前分页下的文件）
     */
    private String desc;

    /**
     * 起始位置，从0开始
     */
    private String start;

    /**
     * 查询数目，默认为1000，建议最大不超过1000
     */
    private String limit;

    /**
     * 值为1时，返回dir_empty属性和缩略图数据
     */
    private String web;

    /**
     * 是否只返回文件夹，0 返回所有，1 只返回文件夹，且属性只返回path字段
     */
    private String folder;

    /**
     * 是否返回dir_empty属性，0 不返回，1 返回
     */
    private String showempty;

    public String toStringWithBaseUrl(String guideUserAuthBaseUrl) {
        return guideUserAuthBaseUrl + CommonCharConstant.QUERY_SPLIT + HttpParamUtils.asUrlParams(this);
    }

}
