# Servlet工作原理解析

## Servlet与Servlet容器

> Servlet与Servlet容器的关系类似与枪与子弹的关系。
>
> 虽然它们是彼此依存的，但是又相互独立发展，这一切都是为了适应工业化发展。
>
> 从技术角度来说是为了解耦，通过标准化接口来相互协作。

## What Is a Servlet?

> A **servlet** is a Java programming language class that is used to extend the capabilities of servers that host applications accessed by means of a request-response programming model. Although servlets can respond to any type of request, they are commonly used to extend the applications hosted by web servers. For such applications, Java Servlet technology defines HTTP-specific servlet classes.
>
> The `javax.servlet` and `javax.servlet.http` packages provide interfaces and classes for writing servlets. All servlets must implement the `Servlet` interface, which defines life-cycle methods. When implementing a generic service, you can use or extend the `GenericServlet` class provided with the Java Servlet API. The `HttpServlet` class provides methods, such as `doGet` and `doPost`, for handling HTTP-specific services.

