//package cn.cc.dawn.config.data.redis;
//
//public class JedisConfig {

    //    @Bean
//    public JedisCluster getJedisCluster() {
//        Set<HostAndPort> nodes = new HashSet<>();
//        String[] cluster = clusterNodes.split(",");
//        for (String node : cluster) {
//            String[] hostAndPort = node.split(":");
//            nodes.add(new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
//        }
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(100);
//        jedisPoolConfig.setMaxIdle(20);
//        jedisPoolConfig.setMinIdle(10);
//        jedisPoolConfig.setMaxWaitMillis(10000);
//
//        return new JedisCluster(nodes, timeout, timeout, maxRedirects, password, jedisPoolConfig);
//    }

//}
