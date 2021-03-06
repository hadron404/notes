### 第1章，微服务

首先介绍微服务的基本概念，包括微服务的主要优点以及一些缺点。

### 第2章，演化式架构师

这一章讨论了架构师需要做出的权衡，以及在微服务架构下具体有哪些方面是我们需要考虑的。

* 演进式架构师应该承担的职责
  * 愿景：确保在系统级有一个经过充分沟通的技术愿景，这个愿景应该可以帮助你满足客户和组织的需求
  * 同理心：理解你所作的决定对客户和同事带来的影响
  * 合作：和尽量多的同事进行沟通，从而更好地对愿景进行定义、修订及执行
  * 适应性：确保在你的客户和组织需要的时候调整技术愿景
  * 自治性：在标准化和团队自治之间寻找一个正确的平衡点
  * 治理：确保系统按照技术愿景的要求实现

### 第3章，如何建模服务

在这一章我们使用领域驱动设计来定义微服务的边界。

* 什么是好的服务
* 如何在问题空间中寻找能达到高内聚低耦合的接缝——通过Bounded Context
* 划分微服务的方法

### 第4章，集成

这一章开始深入具体的技术，讨论什么样的服务集成技术对我们帮助最大。我们还将深入研究用户界面，以及如何集成遗留产品和COTS(Commercial Off-The-Shelf，现成的商业软件)产品这个主题。

* 什么样的集成选择能够最大程度地保证微服务之间的低耦合
  * 无论如何避免数据库集成
  * 理解RREST和RPC之间的取舍，但总是使用REST作为请求/响应模式的起点
  * 相比编排，优先选择协同
  * 避免破坏性修改、理解Postel法则、使用容错性读取器
  * 将用户界面视为一个组合层
* 如何应对那些不完全受控的系统（比如COTS产品）

### 第5章，分解单块系统

很多人对于如何把一个大的、难以变化的单块系统分解成微服务很感兴趣，而这正是我们将在这一章详细介绍的内容。

* 使用循环渐近的办法分解单块系统
* 使用正确的工具分解单块系统

### 第6章，部署

尽管这本书讲述的主要是微服务的理论，但书中的几个主题还是会受到最新技术的影响，部署就是其中之一，我们在这一章会探讨这方面的内容。

* 专注于保持服务能够独立于其他服务进行部署的能力
* 如何简化对多个服务的管理
* CI(持续集成)/CD（持续交付） 

### 第7章，测试

本章会深入测试这个主题，测试在部署多个分散的服务时很重要。特别需要注意的是，消费者驱动的契约测试在确保软件质量方面能够起到什么样的作用。

* 优化快速反馈，并相应地使用不同类型地测试
* 尽可能地使用消费者驱动地契约测试，来替换端到端测试
* 使用消费者驱动的契约测试，提供团队之间的对话要点
* 尝试理解投入更多的努力测试与更快地在生产环境发现问题之间的权衡（MTBF与MTTR权衡的优化）

### 第8章，监控

在部署到生产环境之前的测试并不能完全保证我们上线后没有问题。这一章探讨了细粒度的系统该如何监控，以及如何应对分布式系统的复杂性。

### 第9章，安全

这一章将会研究微服务的安全，考虑如何处理用户对服务及服务间的身份验证和授权。在计算领域，安全是一个非常重要的话题，而且很容易被忽略。尽管我不是安全专家，但我希望这一章至少能帮助你了解在构建系统，尤其是微服务系统时，需要考虑的一些内容。

### 第10章，康威定律和系统设计

这一章的重点是组织结构和系统设计的相互作用。许多组织已经意识到，两者不匹配会导致很多问题。我们将试图弄清楚这一困境的真相，并考虑一些不同的方法将系统设计与你的团队结构相匹配。

### 第11章，规模化微服务

这一章我们将开始了解规模化微服务所面临的问题，以便处理在有大量服务时失败概率增大及流量过载的问题。

### 第12章，总结

最后一章试图分析微服务与其他架构有什么本质上的不同。我列出了微服务的七个原则，并总结了本书的要点。