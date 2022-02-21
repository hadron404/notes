





* Eureka 注册中心
* Ribbon 负载均衡
* Hystrix 断路器
* Feign  Rest客户端
* Zuul API网关



# Eureka：基于AP原则构建的注册中心

### 注册中心

> 注册中心在微服务架构中是必不可少的一部分，主要用来实现服务治理功能

### Eurake

> Spring Cloud Eureka 是Spring Cloud Netflix微服务套件的一部分
>
> 基于Netflix Eureka做了二次封装，主要负责实现**微服务架构**中的**`服务治理`**功能
>
> Spring Cloud Eureka是一个基于REST的服务，并且提供了基于Java的客户端组件，能够非常方便地将服务注册到Spring Cloud Eureka中进行统一管理



CAP定理

* C 数据一致性
* A 服务可用性
* P 服务对网络分区故障的容错性

Eureka是基于AP原则构建的，Zookeeper是基于CP原则构建的



## 一个完整的基于Eureka的服务过程

* 注册中心
  * 依赖包：spring-cloud-starter-netflix-eureka-server
  * @EnableEurekaServer注解，表示开启Eureka Server
  * 访问Eureka提供的Web控制台查看注册中心的客户端
  * 集成Spring-security进行安全认证
* 客户端-服务提供者
  * spring-cloud-starter-netflix-eureka-client
  * @EnableDiscoveryClient，表示当前服务是一个Eureka的客户端
  * 注册Eureka提供的注册方式将当前客户端注册到注册中心
* 服务消费者
  * 通过服务接口地址调用进行消费
  * 改造RestTemplate配置，通过Eureka服务名称进行消费

#### RestTemplate

> RestTemplate是Spring提供的用于访问Rest服务的客户端，RestTemplate提供了多种便携访问远程Http服务的方法，能够大大提高客户端的编写效率

## 高可用Eureka

* 高可用原理
* 搭建方式

### 常用配置

* 关闭自我保护
* 自定义Eureka的InstanceID
* 自定义实例跳转链接
* 快速移除已经失效的服务信息

### 扩展使用

* Eureka REST API
* 元数据使用
* EurekaClient
* 健康检查
* 服务上下线监控



# Ribbon：基于客户端的负载均衡

主流的负载方案

* 集中式负载均衡（消费者和服务提供方中间使用独立的代理方式进行负载，硬件比如F5，软件比如Nginx）
* 客户端负载均衡（根据自己的请求情况做负载）

> Ribbon是Netflix开源的一款用于客户端负载均衡的工具软件
>
> Ribbon 属于客户端负载均衡



## Ribbon模块

* ribbon-loadbalancer
  * 负载均衡模块
  * 可独立使用，也可以和别的模块一起使用
  * ribbon内置的负载均衡算法都实现在其中
* ribbon-eureka
  * 基于Eureka封装的模块
  * 能够快速、方便地集成Eureka
* ribbon-transport
  * 基于Netty实现多协议的支持
  * 比如HTTP、Tcp、Udp等
* ribbon-httpclient
  * 基于Apach HttpClient封装的REST客户端
  * 继承了负载均衡模块，
  * 也可以直接在项目中使用来调用接口
* ribbon-example
  * Ribbon使用代码示例
* ribbon-core
  * 一些比较核心且具有通用性的代码
  * 客户端API的一些配置和其他API的定义



## Ribbon使用

* 作为独立模块使用 `com.netflix.ribbon`
* 通过SpringCloud整合模块 `spring-cloud-starter-netflix-ribbon` 使用



### 独立模块使用

* 整合netflix.ribbon依赖
* 具体使用示例



### SpringCloud整合模块结合Eureka使用

* 集成 spring-cloud-starter-netflix-ribbon
* Eureka已集成该模块
* 为RestTemplate 添加**`@LoadBalanced`**注解 提供负载均衡能力 

#### @LoadBalanced 原理

> 给RestTemplate增加拦截器，在请求之前对请求的地址进行替换,或者根据具体的负载策略选择服务地址，
>
> 然后再去调用



### Ribbon的负载均衡策略

* BestAvailabl

* AvailabilityFilteringRule
* ZoneAvoidanceRule
* RandomRule
* （默认）RoundRobinRule
* RetryRule
* ResponseTimeWeightedRule(同WeightedResponseTimeRule)
* WeightedResponseTimeRule
* 自定义负载策略



### 常用配置

* 禁用Eureka
* 配置接口地址列表（在禁用Eureka的情况下，无法使用服务名称调用接口）
* 配置负载均衡策略
* （请求连接和请求处理）超时时间

### Ribbon的重试机制

基于Nginx做的集中式负载均衡的优势

> 在应用是无状态、可以滚动发布的，重启应用对用户的影响较小，**因为Nginx在转发请求失败后会重新将该请求转发到别的实例**

Ribbon 基于客户端的负载均衡

**Eureka是基于AP原则构建的，牺牲了数据一致性，会导致Ribbon可能拿到失效的服务信息，就会发生失败的请求**



#### 解决方案

1. RetryRule重试
2. Spring Retry重试





# Feign：声明式REST客户端

Java中常见的接口调用方式

* HttpClient
* Okhttp
* HttpURLConnection
* RestTempate
* Feign（目前最流行的，更易用、更方便）



> 原生Feign是由Netflix团队开源
>
> 一般来说，所说的feign指的是SpirngCloud对Feign进行了封装的openfeign，使其支持SpringMvc的注解
>
> （ ？）好像是netflix开源的feign闭源或者不维护了，springcloud团队对其进行二次封装，并且开源持续维护

## Springboot项目集成Feign

* 添加依赖：spring-cloud-starter-openfeign
* 启动类添加 @EnableFeignClients 注解（也可自定义扫描包名）

## OpenFeign使用

* 定义接口
* 接口添加注解@FeignClient
  * 标识一个Feign的客户端
* 定义请求（函数签名）
  * 请求参数
  * 请求类型（@GetMapping、@PostMapping等）

## OpenFeign自定义配置

1. 日志配置
2. 契约配置
   1. 更改Feign的使用方式
   2. 使用原生注解 Contract.Default
   3. 使用SpringMvc注解 SpringMvcContract
3. Basic认证配置
   * 用于请求接口的权限控制，默认支持Basic认证配置
   * 自定义认证方式 通过自定义Feing拦截器实现自定义认证
4. 超时时间配置
5. 客户端组件配置
   * 默认使用JDK原生的URLConnection发送HTPP请求
   * 集成别的组件替换（如Apache HttpClient、OkHttp)
6. GZIP压缩配置
7. 编码器解码器配置
   1. 自定义编码解码器配置、
   2. 默认提供了多种编码器的实现Gson、Jaxb、Jackson
8. 通过配置文件（application.propertioes)自定义Feign配置
9. 继承特性
   1. 提供公共API
   2. 服务提供方继承公共API项目，实现相关接口
   3. 服务消费方引入公共API项目，进行相关接口的调用
10. 多参数请求构造
    * Get请求
      1. 使用@RequestParam
      2. 使用Map（不推荐）
    * Post请求
      1. 定义参数类，通过@RequestBody

## Feign作为独立模块使用

> Feign的GitHub地址：https://github.com/OpenFeign/feign
>
> 原生Feign不支持SpringMvc注解的



# Hystrix：服务容错处理

### 服务雪崩效应

> 服务存在调用时出现故障会导致连锁效应，从而导致整个系统变得不可用，这种情况称为**服务雪崩效应**



## Hystrix：断路器

> Hystrix是Netflix针对微服务分布式系统采用的熔断保护中间件，相当于电路中的保险丝
>
> 
>
> Hystrix通过HystrixCommand对调用进行隔离，这样可以阻止故障的连锁效应，能够让接口调用快速失败并迅速恢复正常，或者回退并优雅降级



* 支持回退
* 信号量策略配置
* 线程隔离策略配置
* 结果缓存
* 缓存清除
* 合并请求



## 使用SpringCloudHystrix

### Springboot项目集成Hystrix

* 添加依赖：spring-cloud-starter-netflix-hystrix
* 启动类上添加@EnableHystrix或者@EnableCircuitBreaker
* @HystrixCommand注解，用于指定依赖服务调用延迟或失败时调用的方法

#### HystrixCommand配置详解

> 官方的配置信息文档请参考：https://github.com/Netflix/Hystrix/wiki/Configuration

* hystrix.command.default.execution.isolation.strategy
  * THREAD
  * SEMAPHORE

### Feign 整合Hystrix服务容错

* 添加EurekaClient、Feign、Hystrix的依赖
* 开启Feign对Hystrix的支持

## Hytrix监控

> 在微服务架构中，Hystrix除了实现容错外，还提供了实时监控功能
>
> Hystrix会实时累积关于HystrixCommand的执行信息，比如每秒的请求数、成功数等
>
> 更多的指标信息请查看官方文档：https://github.com/Netflix/Hystrix/wiki/Metrics-and-Monitoring



实现监控的必备条件

* 必须有Actuator的依赖
* 必须有Hystrix的依赖，并开启Hystrix

## 整合Dashboard查看监控数据

> Hystrix提供了监控的功能，
>
> 可以通过hystrix.stream端点来获取监控数据，但是这些数据是以字符串的形式展现的，实际使用中不方便查看
>
> 我们可以借助hystrix-dashboard对监控进行图形化展示

### Springboot项目集成Dashboard

* 添加Dashboard依赖：spring-cloud-starter-netflix-hystrix-dashboard
* 在启动类上添加@EnableHystrixDashboard注解
* 配置端口访问dashboard主页

## Turbine聚合集群信息

> Turbine是聚合服务器发送事件流数据的一个工具
>
> **Hystrix只能监控单个节点，然后通过dashboard进行展示，实际生产中都为集群**
>
> Turbine监控集群下Hystrix的metrics情况，通过Eureka来发现Hystrix服务



### Springbot集成Turbine

* 添加依赖spring-cloud-starter-netflix-turbine
* 启动类上增加@EnableTurbine和@EnableDiscoveryClient
* 属性文件进行配置



# API网关

> API网关是对外服务的一个入口，其隐藏了内部架构的实现，是微服务架构中必不可少的一个组件

API网关可以做什么？

* 管理大量的API接口
* 对接客户
* 适配协议
* 进行安全认证
* 转发路由
* 限制流量
* 监控日志
* 防止爬虫
* 进行灰度发布

## Zuul

1. Zuul过滤器
   * Zuul过滤器的类型
   * 过滤器的使用以及自定义过滤器
   * 禁用过滤器
   * 过滤器传递数据
   * 过滤器拦截请求
   * 过滤器异常处理
2. Zuul容错和回退

> Zuul是Netflix OSS中的一员，是一个基于JVM路由和服务端的负载均衡器。提供路由、监控、弹性、安全等方面的服务框架。Zuul能够与Eureka、Ribbon、Hystrix等组件配合使用

### Springboot项目集成Zuul 构建服务网关

* 添加依赖spring-cloud-starter-netflix-zuul
* 启动类添加注解 @EnableZuulProxy开启路由代理功能
* 访问页面

### 集成Eureka

> 用Zuul来代理请求转发到内部的服务上去，统一为外部提供服务
>
> 内部的服务数量会很多，而且可以随时扩展，通过结合Eureka来实现动态的路由转发功能，就不用增加一个服务就改一次路由的配置

默认的转发规则： API网关地址 + 访问的服务名称 + 接口URI



### Zuul路由配置

1. 指定具体服务路由
   * 可以为每一个服务都配置一个路由转发规则
2. 路由前缀
3. 本地跳转

### Zuul过滤器

Zuul过滤器的类型

* pre：可以在请求路由之前调用
  * 适合身份认证场景
* route：在路由请求时被调用
  * 适用于灰度发布场景，在将要路由的时候可以做一些自定义的逻辑
* post：在route和error过滤器之后被调用
  * 这种过滤器将请求路由到达具体的服务之后执行
  * 适用于需要添加响应头，记录响应日志等应用场景
* error：处理请求时发生错误时被调用
  * 在执行过程中发生错误时会进入error过滤器
  * 适用于统一记录错误信息

#### 过滤器的生命周期

#### 使用过滤器以及自定义过滤器

自定义过滤器需要集成ZuulFilter，并且需要实现以下方法

* shouldFilter：是否执行该过滤器
* filterType：过滤器类型，可选值：pre、route、post、error
* filterOrder：过滤器的执行舒徐，数值越小，优先级越高
* run：执行自己的业务逻辑

#### 过滤器中的异常处理

>过滤器中的异常主要发生在run方法中，可以用try catch来处理。Zuul中也为我们提供了一个异常处理的过滤器，当过滤器在执行过程中发生异常，若没有被捕获到，就会进入error过滤器中

**因为@ControllerAdvice注解主要用来针对Controller中的方法做处理，作用于@RequestMapping标注的方法上，只对我们定义的接口异常有效，在Zuul中是无效的**



Zuul容错与回退

容错机制

> 容错，简单来说就是当某个服务不可用时，能够切换到其他可用的服务上去，也就是需要有重试机制



在Zuul中开启重试机制需要依赖spring-retry

* 添加依赖 org.springframework.retry:spring-rety

回退机制

> 在Spring Cloud中，Zuul默认整合了Hystrix，当后端服务异常时，可以为Zuul添加回退功能，返回默认的数据给客户端



### Zuul高可用

跟业务相关的服务我们都是注册到Eureka中，通过Ribbon来进行负载均衡，服务可以通过水平扩展来实现高可用。

现实使用中，API网关这层往往是给APP、Webapp、客户来调用接口的，如果我们将Zuul也注册到Eureka中是达不到高可用的，因为你不可能让你的客户也去操作你的注册中心。

这时最好的办法就是用额外的负载均衡器来实现Zuul的高可用，比如我们最常用的Nginx，或者HAProxy、F5等

