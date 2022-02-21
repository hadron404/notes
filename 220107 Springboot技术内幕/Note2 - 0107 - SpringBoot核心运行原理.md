# SpringBoot的核心运行原理

## SpringBoot的自动配置功能

​	使用Springboot时，**只需引入对应的starter，SpringBoot启动时便会自动加载相关依赖，配置相应的初始化参数，以最快捷、简单的形式对第三方软件进行集成**，这便是Spring Boot的自动配置功能。



### 核心实现机制

![](C:\Users\Administrator\Desktop\220107 Springboot技术内幕\images\Springboot自动配置功能核心运行原理.png)

> 一言以蔽之：
>
> Spring Boot 通过 `@EnableAutoConfiguration` 注解开启自动配置，加载 `Spring.facatories` 文件中的注册的各种`AutoConfiguration类`，当某个AutoConfiguration类满足其注解 `@Conditional` 指定的生效条件时，实例化该AutoConfiguration类中定义的Bean，并注入Spring容器，就可以完成依赖框架的自动配置。



![](C:\Users\Administrator\Desktop\220107 Springboot技术内幕\images\@SpringbootApllicatio组合结构图.png)

