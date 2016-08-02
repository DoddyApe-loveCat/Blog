# Blog 项目

servlet，jsp，jstl 的依赖范围要关注一下。
自定义的 Realm extends AuthorizingRealm，不是使用实现。


以下的配置不推荐这么做，因为事务的控制要很精细，不适合一刀切的配置方式。
<!-- 配置事务通知属性 -->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!-- 定义事务传播属性 -->
        <tx:attributes>
            <tx:method name="insert*" propagation="REQUIRED" />
            <tx:method name="update*" propagation="REQUIRED" />
            <tx:method name="edit*" propagation="REQUIRED" />
            <tx:method name="save*" propagation="REQUIRED" />
            <tx:method name="add*" propagation="REQUIRED" />
            <tx:method name="new*" propagation="REQUIRED" />
            <tx:method name="set*" propagation="REQUIRED" />
            <tx:method name="remove*" propagation="REQUIRED" />
            <tx:method name="delete*" propagation="REQUIRED" />
            <tx:method name="change*" propagation="REQUIRED" />
            <tx:method name="check*" propagation="REQUIRED" />
            <tx:method name="get*" propagation="REQUIRED" read-only="true" />
            <tx:method name="find*" propagation="REQUIRED" read-only="true" />
            <tx:method name="load*" propagation="REQUIRED" read-only="true" />
            <tx:method name="*" propagation="REQUIRED" read-only="true" />
        </tx:attributes>
    </tx:advice>

    <!-- 配置事务切面 -->
    <aop:config>
        <aop:pointcut id="serviceOperation"
            expression="execution(* com.java1234.service.*.*(..))" />
        <aop:advisor advice-ref="txAdvice" pointcut-ref="serviceOperation" />
    </aop:config>


2016 年 8 月 1 日开发总结
拿出做项目的感觉来，以前给朗鹰做项目的时候用心用力，现在是给自己做项目，做一个标榜自己的项目，更应该要上心。
现在是没有工作在做项目，所以应该要和时间赛跑。


敲了代码才会有感觉。
几个配置文件的作用要了解清楚。
SQL 语句的编写要形成自己的习惯。
Druid 数据源几个参数的意义要很明确，可以参考慕课网秒杀的视频去加深自己的理解。

2016 年 8 月 2 日开发总结
画页面其实没有那么繁琐，自己只要静下心来画页面就好很多了。
看十几遍视频不如手敲一遍，心里来得清楚。
试着站在更高的角度看问题，其实有的时候是我们把问题想得复杂了。
Bootstrap 多查一下文档就有感觉了，很简单的东西。