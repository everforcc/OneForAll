/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-16 11:41
 */

package cn.cc.dawn.config.cache;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.unit.DataSize;

import java.time.Duration;

/**
 *  @Description @Cacheable 的rediscache管理
 */
@Getter
@Setter
@ToString
public class AppCache {

    private String cachekey;
    /**
     * 过期时间
     */
    private Duration expired;
    /**
     * 堆内保持数据行数
     */
    private long heap;
    /**
     * 堆外占用内存
     */
    private DataSize offheap;
    /**
     * 磁盘占用空间
     */
    private DataSize disk;

}
