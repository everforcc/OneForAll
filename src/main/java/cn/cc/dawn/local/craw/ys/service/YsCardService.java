package cn.cc.dawn.local.craw.ys.service;

import javax.validation.constraints.NotNull;
import java.util.Map;

public interface YsCardService {

    int htmlUrlFlow(@NotNull(message = "【查询参数】不能为null") String json);

    int jsonUrlFlow(@NotNull(message = "【查询参数】不能为null") String json);

    Map analyse(@NotNull(message = "【查询参数】不能为null") String json);

}
