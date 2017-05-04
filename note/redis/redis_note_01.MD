 ### 2. 安装配置
 #### 2.1 安装
 Ubuntu下:
 ```
 putao_lhq@putao-lhq:~/workspace/spring$ sudo apt-get install redis-server
 ```
 **启动redis**
 ```
 putao_lhq@putao-lhq:~/workspace/spring$ redis-server 
 26549:C 04 May 17:46:17.213 # Warning: no config file specified, using the default config. In order to specify a config file use redis-server /path/to/redis.conf
 26549:M 04 May 17:46:17.214 * Increased maximum number of open files to 10032 (it was originally set to 1024).
                 _._                                                  
            _.-``__ ''-._                                             
       _.-``    `.  `_.  ''-._           Redis 3.0.6 (00000000/0) 64 bit
   .-`` .-```.  ```\/    _.,_ ''-._                                   
  (    '      ,       .-`  | `,    )     Running in standalone mode
  |`-._`-...-` __...-.``-._|'` _.-'|     Port: 6379
  |    `-._   `._    /     _.-'    |     PID: 26549
   `-._    `-._  `-./  _.-'    _.-'                                   
  |`-._`-._    `-.__.-'    _.-'_.-'|                                  
  |    `-._`-._        _.-'_.-'    |           http://redis.io        
   `-._    `-._`-.__.-'_.-'    _.-'                                   
  |`-._`-._    `-.__.-'    _.-'_.-'|                                  
  |    `-._`-._        _.-'_.-'    |                                  
   `-._    `-._`-.__.-'_.-'    _.-'                                   
       `-._    `-.__.-'    _.-'                                       
           `-._        _.-'                                           
               `-.__.-'                                               
 
 26549:M 04 May 17:46:17.215 # WARNING: The TCP backlog setting of 511 cannot be enforced because /proc/sys/net/core/somaxconn is set to the lower value of 128.
 26549:M 04 May 17:46:17.215 # Server started, Redis version 3.0.6
 26549:M 04 May 17:46:17.215 # WARNING overcommit_memory is set to 0! Background save may fail under low memory condition. To fix this issue add 'vm.overcommit_memory = 1' to /etc/sysctl.conf and then reboot or run the command 'sysctl vm.overcommit_memory=1' for this to take effect.
 26549:M 04 May 17:46:17.215 # WARNING you have Transparent Huge Pages (THP) support enabled in your kernel. This will create latency and memory usage issues with Redis. To fix this issue run the command 'echo never > /sys/kernel/mm/transparent_hugepage/enabled' as root, and add it to your /etc/rc.local in order to retain the setting after a reboot. Redis must be restarted after THP is disabled.
 26549:M 04 May 17:46:17.215 * The server is now ready to accept connections on port 6379

 ```
 **检查redis是否正常工作**  
 输入命令:
 ```
 putao_lhq@putao-lhq:~/workspace/spring$ redis-cli
 ```
 如果正常进入命令行,则证明redis工作正常
 #### 2.2 配置
 redis配置文件在根目录下,文件:redis.conf,路径如:
 ```
 putao_lhq@putao-lhq:/etc/redis$ ls
 redis.conf                redis-server.post-up.d   redis-server.pre-up.d
 redis-server.post-down.d  redis-server.pre-down.d  sentinel.conf

 ```
 redis也可以通过命令行进行设置:  
 **基本语法:**
 - 获取属性:
 ```
 CONFIG GET CONFIG_SETTING_NAME
 ```
 如:
 ```
 127.0.0.1:6379> CONFIG GET loglevel
 1) "loglevel"
 2) "notice"
 127.0.0.1:6379> 
 ```
 - 设置属性:
  ```
  CONFIG SET CONFIG_SETTING_NAME "待设置值"
  ```