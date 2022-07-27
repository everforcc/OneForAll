/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 18:53
 */

package cn.cc.dawn.common.bddisk.upload.api;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;

/**
 * 3. 合并文件
 */
public interface IBDDiskFileCreateApi {

    /**
     * 3. 合并文件
     *
     * @param bdFileVo 文件信息
     * @param userid   用户id
     * @return 合并结果
     */
    boolean createUploadFile(BDFileVo bdFileVo, int userid);

}
