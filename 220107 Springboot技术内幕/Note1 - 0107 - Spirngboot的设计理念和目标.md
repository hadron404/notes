# SpringBoot的设计理念和目标

> 随着项目的发展，Spring慢慢地集成了更多的开源软件，引入大量配置文件，这会导致程序出错率高、运行效率地下的问题，为了解决这些状况，Spring Boot应运而生
>
> **Spring Boot本身并不提供Spring的核心功能，而是作为Spring的脚手架框架，以达到快速构建项目、预置三方配置、开箱即用的目的**



## 设计理念

Spring Boot的核心设计完美遵从了约定优于配置（又称按约定编程）这一软件设计范式,这种设计范式旨在 减少软件开发人员需要做决定的数量，执行起来简单而又不失灵活。



### 哪些核心设计遵从了该范式？

Spring Boot的功能从细节到整体都是基于该范式开发的。有基础框架的搭建、配置文件、中间件的集成、内置容器以及其生态中各种`Starters`。

* 如何构造Spring Boot的starters以及 starters的构造原理？
* Spring Boot的自动配置原理

## 设计目标

Spring Boot不是为已解决的问题提供新的解决方案，而是为平台和开发者带来一种全新的体验：整合成熟技术框架、屏蔽系统复杂性、简化已有技术的使用，从而降低软件的使用门槛，提升软件开发和运维的效率。