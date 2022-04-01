package cn.cc.dawn.open.menu.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MenuAdminService {

    /**
     * 此类需要admin权限
     *
     * 1. 初始化menu
     * 2. save
     * 3. update
     * 4. delete
     * 5.
     *
     */

    public void initMenu(){
        log.info("返回初始化之后的menu结构");
    }

    public void dealData(){
        log.info("尚未分类的数据");
    }


    /**
     * 每次处理都要先校验更新时间,以下代码都需要上锁
     */

    public void save(){

    }


    public void update(){

    }

    /**
     * 删除前必须保证没有数据，否则无法删除
     */
    public void delete(){

    }


}
