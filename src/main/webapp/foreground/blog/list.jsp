<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach items="${blogList}" var="blog">
    <div class="panel panel-success">
        <div class="panel-heading">
            <b><span class="text-right"><fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy 年 MM 月 dd 日"/></span></b>
            <b>
                <h3 class="panel-title">
                    <a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">
                        ${blog.title}
                    </a>
                </h3>
            </b>
        </div>
        <div class="panel-body">
            摘要：${blog.summary}
            <p class="text-right">
                发表于 <fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/> 阅读(${blog.clickHit}) 评论(${blog.replyHit})
            </p>
        </div>
    </div>
</c:forEach>

${pageCode}

<%-- 使用 Bootstrap 分页组件 --%>

<%--
<nav>
    <ul class="pagination">
        <li>
            <a href="#" aria-label="Previous">
                <span aria-hidden="true">首页</span>
            </a>
        </li>
        <li class="active"><a href="#">1</a></li>
        <li class="disabled"><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li>
            <a href="#" aria-label="Next">
                <span aria-hidden="true">下一页</span>
            </a>
        </li>
    </ul>
</nav>
--%>



<%-- 使用面板组的循环来完成 --%>
<%--<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">博客文章1：Spring Boot 实战</h3>
    </div>
    <div class="panel-body">
        spring-boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，Boot致力于在蓬勃发展的快速应用开发领域（rapid application development）成为领导者。
        多年以来，Spring IO平台饱受非议的一点就是大量的XML配置以及复杂的依赖管理。在去年的SpringOne 2GX会议上，Pivotal的CTO Adrian Colyer回应了这些批评，并且特别提到该平台将来的目标之一就是实现免XML配置的开发体验。Boot所实现的功能超出了这个任务的描述，开发人员不仅不再需要编写XML，而且在一些场景中甚至不需要编写繁琐的import语句。在对外公开的beta版本刚刚发布之时，Boot描述了如何使用该框架在140个字符内实现可运行的web应用，从而获得了极大的关注度，该样例发表在tweet上。
    </div>
</div>

&lt;%&ndash; 以下内容须要通过循环来完成，这里先制作一些数据填充 &ndash;%&gt;
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">博客文章2：Spring 实战</h3>
    </div>
    <div class="panel-body">
        你可能正在想“Spring不过是另外一个的framework”。当已经有许多开放源代码（和专有）J2EEframework时，我们为什么还需要Spring Framework？
        Spring是独特的，因为若干个原因：
        它定位的领域是许多其他流行的framework没有的。Spring致力于提供一种方法管理你的业务对象。
        Spring是全面的和模块化的。Spring有分层的体系结构，这意味着你能选择使用它孤立的任何部分，它的架构仍然是内在稳定的。因此从你的学习中，你可得到最大的价值。例如，你可能选择仅仅使用Spring来简单化JDBC的使用，或用来管理所有的业务对象。
        它的设计从底部帮助你编写易于测试的代码。Spring是用于测试驱动工程的理想的framework。
        Spring对你的工程来说，它不需要一个以上的framework。Spring是潜在地一站式解决方案，定位于与典型应用相关的大部分基础结构。它也涉及到其他framework没有考虑到的内容。
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">博客文章2：Spring 实战</h3>
    </div>
    <div class="panel-body">
        你可能正在想“Spring不过是另外一个的framework”。当已经有许多开放源代码（和专有）J2EEframework时，我们为什么还需要Spring Framework？
        Spring是独特的，因为若干个原因：
        它定位的领域是许多其他流行的framework没有的。Spring致力于提供一种方法管理你的业务对象。
        Spring是全面的和模块化的。Spring有分层的体系结构，这意味着你能选择使用它孤立的任何部分，它的架构仍然是内在稳定的。因此从你的学习中，你可得到最大的价值。例如，你可能选择仅仅使用Spring来简单化JDBC的使用，或用来管理所有的业务对象。
        它的设计从底部帮助你编写易于测试的代码。Spring是用于测试驱动工程的理想的framework。
        Spring对你的工程来说，它不需要一个以上的framework。Spring是潜在地一站式解决方案，定位于与典型应用相关的大部分基础结构。它也涉及到其他framework没有考虑到的内容。
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">博客文章3：Git 权威指南</h3>
    </div>
    <div class="panel-body">
        Git是一个分布式的版本控制系统，最初由Linus Torvalds编写，用作Linux内核代码的管理。在推出后，Git在其它项目中也取得了很大成功，尤其是在Ruby社区中。目前，包括Rubinius、Merb和Bitcoin在内的很多知名项目都使用了Git。Git同样可以被诸如Capistrano和Vlad the Deployer这样的部署工具所使用。
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">博客文章4：Maven 实战</h3>
    </div>
    <div class="panel-body">
        Maven项目对象模型(POM)，可以通过一小段描述信息来管理项目的构建，报告和文档的软件项目管理工具。
        Maven 除了以程序构建能力为特色之外，还提供高级项目管理工具。由于 Maven 的缺省构建规则有较高的可重用性，所以常常用两三行 Maven 构建脚本就可以构建简单的项目。由于 Maven 的面向项目的方法，许多 Apache Jakarta 项目发文时使用 Maven，而且公司项目采用 Maven 的比例在持续增长。
        Maven这个单词来自于意第绪语（犹太语），意为知识的积累，最初在Jakata Turbine项目中用来简化构建过程。当时有一些项目（有各自Ant build文件），仅有细微的差别，而JAR文件都由CVS来维护。于是希望有一种标准化的方式构建项目，一个清晰的方式定义项目的组成，一个容易的方式发布项目的信息，以及一种简单的方式在多个项目中共享JARs。
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">博客文章5：Gradle 实战</h3>
    </div>
    <div class="panel-body">
        Gradle是一个基于Apache Ant和Apache Maven概念的项目自动化建构工具。它使用一种基于Groovy的特定领域语言(DSL)来声明项目设置，抛弃了基于XML的各种繁琐配置。
        面向Java应用为主。当前其支持的语言限于Java、Groovy和Scala，计划未来将支持更多的语言。
    </div>
</div>--%>
