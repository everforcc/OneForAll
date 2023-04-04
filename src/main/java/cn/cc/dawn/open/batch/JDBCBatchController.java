package cn.cc.dawn.open.batch;

import cn.cc.dawn.config.data.batch.JDBCBatchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 jdbc batch
 */
@RequestMapping("/open/jdbc")
@RestController
public class JDBCBatchController {

    @Autowired
    JDBCBatchDao JDBCBatchDao;

    @GetMapping("/insert")
    @Transactional
    public void insert() {
        String sql = "insert into cc_t_stock (`id`, `stocknum`) values ( ?, ? ) ";
        List<Object[]> params = new ArrayList<>();
        Object[] objects = new Object[]{3, 30};
        params.add(objects);
        try {
            JDBCBatchDao.batchExcuteTx(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
