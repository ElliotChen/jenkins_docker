# jenkins_docker

## 目的

利用此project來展示jenkins可以build，並建立docker image。
再利用Nginx ＋ Spring Session + Redis，直接展示一個可進行負載平衡的系統。

不用ip_hash，直接使用Redis + Spring Session，使得使用者打任一台都是相同的。


## Build

```mvn -P${profile} package dockerfile:build```

例如

```
mvn -Pdev package dockerfile:build
```

## Run

至docker目錄執行

```
docker-compose up
```

然後在browser執行

```
http://localhost:7080/api/profile/checkProfile
```

就能看到回覆，在console裡也能看到session的id資料，也可以觀察兩個spring application哪一個被呼叫到。

## Spring Session

可以參考Spring doc - [Spring Session](https://docs.spring.io/spring-session/docs/current/reference/html5/guides/boot-redis.html)


## docker-compose

相關設定可以參考 [docker-compose.yml](./docker/docker-compose.yml)

Redis啟動後才能開啟兩個Spring boot程式，再來才是Nginx。

另外重點是network，加入

```
networks:
  app_jds:
    driver: bridge
```

讓其他VM使用，確保在同一網路內。

## Nginx

相關設定 [default.conf](./docker/data/nginx/conf.d/default.conf)


### upstream

Nginx 的 load balance文件 [upstream](http://nginx.org/en/docs/http/ngx_http_upstream_module.html)

在此簡單設定一個Pool，正常的話會輪流打spring01及spring02，但目前不知道為何，用Chrome來測時經常只打同一台，但用Safari就很正常。

```
upstream spring {
      server spring01:8080;
      server spring02:8080;
}
```

### server

簡單設定80 port都往```http://spring```送。

```
server {
    listen       80;
    server_name  localhost;
    location / {
        proxy_pass http://spring;
    }
}
```

## Spring Boot Application

也就是這個project本身會產生的docker image

同時啟用兩個來做load balancing 測試。

## Redis

Redis用來保存各server的session，基本上除了有關實體檔案或目錄的操作外，都變得無差異化。

