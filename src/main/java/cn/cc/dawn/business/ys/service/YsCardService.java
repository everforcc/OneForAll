package cn.cc.dawn.business.ys.service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface YsCardService {

    int htmlUrlFlow(@NotNull(message = "【查询参数】不能为null") String json);

    int jsonUrlFlow(@NotNull(message = "【查询参数】不能为null") String json);

    Map analyse(@NotNull(message = "【查询参数】不能为null") String json);

}
