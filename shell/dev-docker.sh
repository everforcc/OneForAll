
# 1. 最新代码
git pull

# 2. 卸载容器 -f 指定文件
docker-compose -f docker-compose-prod.yml down

# 清理悬挂镜像
docker image prune -f
# docker image prune -f

#docker-compose -f ~/docker/redis/docker-compose.yml up -d
#export IMAGE_TAG=`date +"%Y%m%d%H%M"`
# maven编译
mvn clean package -P dev -D maven.test.skip=true

# -d 在后台运行而不是直接把执行命令的结果输出在当前宿主机下
# build 构建（重新构建）项目中的服务容器。
# up 启动的容器都在前台
# -d 在后台启动并运行所有的容器
docker-compose up -d --build


#cd app-demo && bash dev-docker.sh                 # 更新 app-demo

#echo '
## **********************************************************************************************************************
## 单个服务更新脚本整理
## **********************************************************************************************************************
##cd app-demo && bash dev-docker.sh && cd ..                     # 更新 app-demo 网关，包含跨域环境
#
## **********************************************************************************************************************
## 查看日志
## **********************************************************************************************************************
#tail -F ~/docker/logs/*.log                   # 滚动查看全部日志
#tail -F ~/docker/logs/demo.log                # 应用服务日志
#'
