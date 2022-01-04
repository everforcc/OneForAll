
cd ..

# 1. 最新代码
git pull

# 2. 卸载容器 -f 指定文件
docker-compose -f shell/docker-compose-prod.yml down

# 清理悬挂镜像
docker image prune -f
# docker image prune -f

# maven编译
# -P,--activate-profiles <arg>           Comma-delimited list of profiles to activate
# -D,--define <arg>                      Define a system property
# maven.test.skip 跳过单元测试
mvn clean package -P bdy -D maven.test.skip=true

# -d 在后台运行而不是直接把执行命令的结果输出在当前宿主机下
# build 构建（重新构建）项目中的服务容器。
# up 启动的容器都在前台
# -d 在后台启动并运行所有的容器
docker-compose -f shell/docker-compose-prod.yml up -d --build