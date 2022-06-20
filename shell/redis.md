<font face="Simsun" size=3>


- redis
~~~
redis-cli -h 180.76.156.43 -p 6379 -a c.c.5664
# local
select 15
TTL key
get key
~~~
- 集群
~~~
redis-cli -c -h 127.0.0.1 -p 6381
redis-cli -c -h 127.0.0.1 -p 6382
redis-cli -c -h 127.0.0.1 -p 6386
redis-cli -c -h 127.0.0.1 -p 6384
redis-cli -c -h 127.0.0.1 -p 6385
redis-cli -c -h 127.0.0.1 -p 6386
~~~

</font>