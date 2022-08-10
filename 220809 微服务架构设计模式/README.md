# 微服务架构设计模式

## 箴言
1. 微服务不是解决所有问题的万能 **银弹（silver bullet）**
2. 编写*整洁的代码*和使用*自动化测试*至关重要，因为这是现代软件开发的基础
3. 关注微服务的本质，即服务的分解和定义，而不是技术，如容器和其他工具
4. 确保你的服务松耦合，并且可以独立开发、测试和部署，不要搞成**分布式单体（Distributed Monolith）**，那将是巨大的灾难。
5. **不能只是在技术上采用微服务架构。拥抱 `DevOps` 的原则和实践，在组织结构上实现跨职能的自治团队，这必不可少。**

>软件的架构设计，就是选择和取舍。
>面对围绕微服务的众多杂音，开发者和架构师应该具备选择和取舍的能力，应该站在比较高的角度俯瞰全局、权衡利弊，做出正确的架构和技术选择。



## 阅读前的准备
* 熟悉企业应用程序架构和设计的基础知识
* 了解三层机构、Web应用程序设计、关系型数据库、使用消息和基于REST的进程间通信以及引用程序安全性的基础知识等概念
* 了解Spring框架

## 内容概述
**Chapter1** 描述了所谓“单体地狱”的症状，当单体应用超出其架构时会出现这种问题，这可以通过采用微服务架构来规避。这一章还概述了微服务架构模式语言，这也是本书大部分的内容。
**Chapter2** 解释了为什么软件架构很重要，描述了可用于将应用程序分解为服务集合的模式，并解释了如何克服在此过程中遇到的各种障碍。
**Chapter3** 介绍了微服务架构中强大的进程间通信的几种模式，解释了为什么异步和基于消息的通信通常是最佳选择。
**Chapter4** 介绍如何使用 `Saga` 模式维护服务间的数据一致性。Saga 是通过传递异步消息的方式进行协调的一系列本地事务。
**Chapter5** 介绍如何使用 `领域驱动设计（DDD）` 的聚合和领域事件等模式为服务设计业务逻辑。
**Chapter6** 以 **Chapter5** 为基础，解释了如何使用 `事件溯源模式` 开发业务逻辑，事件溯源模式是一种以事件为中心的设计丝路，用来构建业务逻辑和持久化领域对象。
**Chapter7** 介绍如何使用 `API 组合` 模式或 `命令查询职责隔离（CQRS）` 模式，这两个模式用来实现查询分散在多个服务中的数据。
**Chapter8** 介绍了处理来自各种外部客户端请求的外部 API 模式，例如移动应用程序、基于浏览器的 Javascript 应用程序和第三方应用程序
**Chapter9** 关于微服务自动化测试技术的两章中的第一章，介绍了重要的测试概念，例如`测试金字塔`，描述了测试套件中每种测试类型的相对比例，还展示了如何编写构成测试金字塔基础的单元测试。
	**Chapter10** 以 **Chapter9**为基础，描述了如何在测试金字塔中编写其他类型的测试，包括集成测试、`消费者契约测试` 和组件测试等
**Chapter11** 介绍了开发生产就绪服务的各个方面，包括安全性、外部化配置模式和`服务可观测性模式`。服务可观测性模式包括日志聚合、应用指标和分布式追踪。
**Chapter12** 介绍了可用于部署服务的各种部署模式，包括虚拟机、容器和 `Serverless` 模式。还介绍了使用服务网格的好处，服务网格是在微服务架构中处理服务间通信的一个网络软件层。
**Chapter13** 介绍了如何通过采用绞杀者（Strangler）模式逐步将单体架构重构为微服务架构，`绞杀者模式` 是指以服务形式来实现新功能，从单体中提取模块将其转换为服务。

# Reference
*  #BOOK   《The Art of Scalability: Scalable Web Architecture, Processes, and Organizations for the Modern Enterprise》
*  #TALK  《[Decomposing Applications for Scalability and Deployability]([Decomposing Applications for Scalability and Deployability (April 201… (slideshare.net)](https://www.slideshare.net/chris.e.richardson/decomposing-applications-for-scalability-and-deployability-april-2012?qid=f4848382-4584-4f86-ba87-28135b6f4f0d&v=&b=&from_search=2))》
* #ARTICLE 《[Mircroservices]([Microservices (martinfowler.com)](https://martinfowler.com/articles/microservices.html))》
* #BOOK  《POJOS In Action》
* #BOOK 《企业应用架构模式》