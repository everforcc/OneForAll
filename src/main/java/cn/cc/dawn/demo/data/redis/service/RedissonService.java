package cn.cc.dawn.demo.data.redis.service;

import cn.cc.dawn.demo.data.redis.dao.TOrderDaoMapper;
import cn.cc.dawn.demo.data.redis.dao.TStockDaoMapper;
import cn.cc.dawn.demo.data.redis.dto.TOrderDto;
import cn.cc.dawn.utils.commons.codec.JUUIDUtils;
import cn.cc.dawn.utils.data.redis.RedisLockConstant;
import cn.cc.dawn.utils.exception.AppCode;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service("redissonService")
@Slf4j
//@RequiredArgsConstructor
public class RedissonService {

    @Resource
    TOrderDaoMapper tOrderDao;
    @Resource
    TStockDaoMapper tStockDao;
    //    @Resource
//    Redisson redisson;
    @Resource
    private RedissonClient redissonClient;

    @Transactional(rollbackFor = Exception.class)
    public boolean redisLock(int productId){
        Date lockstartDate = new Date();
        RLock lock = redissonClient.getLock(RedisLockConstant.TEST_SHOP);
        boolean flag = false;
        try {
            flag = lock.tryLock(1,1, TimeUnit.SECONDS);
            Date lockandDate = new Date();
            log.info("获取lock耗时:" + (lockandDate.getTime()-lockstartDate.getTime()));
            log.info("用户名: flag: {} " , flag);
            AppCode.A00112.assertHasTrue(flag);
            if(flag){
                Date startDate = new Date();
                //lock.lock(10,TimeUnit.SECONDS);
                /**
                 * 因为redis不能回滚，所以修改值的操作放到最后执行
                 */
                final RBucket<Integer> intRBucket = redissonClient.getBucket("lock-" + productId);
                int stockNum;
                if(intRBucket.isExists()){
                    stockNum = intRBucket.get();
                }else {
                    /**
                     * 1. 假如一直没问题，那么为0的时候更新一次
                     * 2. 假如出现异常，redis坏掉之类的，那么就从列表查出总数和详情总数，相减得出当前的redis数量
                     * 3. 只有三种情况更新redis，初始化|中途宕机从数据库读数| 正常买卖
                     * 4. 只有两种情况更新数据库，初始化|售空
                     */
                    int primitiveNum = tStockDao.selectCount(productId);
                    int usageNum = tOrderDao.selectCount(null);
                    log.info("数据不存在，从数据库读取: primitiveNum:{} --- usageNum:{} ",primitiveNum,usageNum);
                    /**
                     * primitiveNum 不能等于0
                     *
                     */
                    AppCode.A00113.assertHasTrue(primitiveNum>0&&primitiveNum>usageNum);
                    if(0!=usageNum){
                        stockNum = primitiveNum-usageNum;
                        tStockDao.updateCount(productId,stockNum);
                    }else {
                        stockNum = primitiveNum;
                    }
                    //intRBucket.set(stockNum);
                }


                Date endsqlDate = new Date();
                log.info("sql1耗时:" + (endsqlDate.getTime()-startDate.getTime()));
                log.info("剩余数量:" + stockNum);

                AppCode.A00113.assertHasTrue(stockNum>0);
                // AppCode.A01001.assertHasUpdate(tStockDao.updateCount(productId,--stockNum));

                String orderNo = JUUIDUtils.uuid32().toUpperCase();
                TOrderDto tOrderDto = new TOrderDto();
                tOrderDto.setOrderno(orderNo);
                tOrderDto.setProductid(productId);
                tOrderDto.setUserid("1");
                Date startsqlDate = new Date();
                tOrderDao.insertOrder(tOrderDto);
                Date endDate = new Date();
                log.info("sql2耗时:" + (endDate.getTime()-startsqlDate.getTime()));

                if(1 == stockNum){
                    // 本次就抢购完毕,更新数据库
                    tStockDao.updateCount(productId,0);
                    // 结束后再存一秒
                    intRBucket.set(0,1,TimeUnit.SECONDS);
                }else {
                    intRBucket.set(--stockNum);
                }
                log.info("总耗时:" + (endDate.getTime()-startDate.getTime()));
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //if(false&&lock.isLocked()){
            if(flag){
                lock.unlock();
            }
        }
        return flag;
    }

}
