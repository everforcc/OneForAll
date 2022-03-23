<font face="Simsun" size=3>

### 直播

#### ffmpeg 启动脚本

~~~
nginx.exe -c conf\nginx-win-rtmp.conf

ffmpeg -re -i E:/test/oneforall/01.flv -vcodec libx264 -acodec aac -f flv rtmp://127.0.0.1:1935/live/home

ffmpeg -re -i E:/test/oneforall/test.mkv -vcodec libx264 -acodec aac -f mkv rtmp://127.0.0.1:1935/live/home

rtmp://127.0.0.1:1935/live/obs
~~~

#### 报错

~~~
No mapping for the Unicode character exists in the target multi-byte code page
不能用中文目录
~~~

</font>