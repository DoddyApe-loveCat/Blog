# Blog 项目

该项目的索引存放在 /Users/liwei/codes/luceneIndex


一些常用的数据我存放在 Servlet 的系统应用变量里面。
例如,博客类型列表数据。


1、思考部署方案：
（1）开发环境：
（2）百度 bae 环境：

异常处理。

记住我要实现一下。

## 遗留问题
1. 输入博客内容,方便测试搜索;
2. MySQL 事务未配置,并且还应该有相应的测试;
3. 加索引的时候要将文本去格式化。


首页：http://localhost:8080/index.html
后台登陆：http://localhost:8080/login.jsp

Spring MVC 通过@Value注解读取.properties配置
http://blog.csdn.net/white__cat/article/details/42103155

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



easyui 使用的过程中，应该频繁地去查看官方的 demo 和翻译过来的中文资料。
由简单到复杂，由常量到变量去使用里面的组件。

分页传给前端的数据形式：
{"total":28,"rows":[
	{"productid":"FI-SW-01","productname":"Koi","unitcost":10.00,"status":"P","listprice":36.50,"attr1":"Large","itemid":"EST-1"},
	{"productid":"K9-DL-01","productname":"Dalmation","unitcost":12.00,"status":"P","listprice":18.50,"attr1":"Spotted Adult Female","itemid":"EST-10"},
	{"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":38.50,"attr1":"Venomless","itemid":"EST-11"},
	{"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":26.50,"attr1":"Rattleless","itemid":"EST-12"},
	{"productid":"RP-LI-02","productname":"Iguana","unitcost":12.00,"status":"P","listprice":35.50,"attr1":"Green Adult","itemid":"EST-13"},
	{"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":158.50,"attr1":"Tailless","itemid":"EST-14"},
	{"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":83.50,"attr1":"With tail","itemid":"EST-15"},
	{"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"P","listprice":23.50,"attr1":"Adult Female","itemid":"EST-16"},
	{"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"P","listprice":89.50,"attr1":"Adult Male","itemid":"EST-17"},
	{"productid":"AV-CB-01","productname":"Amazon Parrot","unitcost":92.00,"status":"P","listprice":63.50,"attr1":"Adult Male","itemid":"EST-18"}
]}

前端传来的数据格式：
page 第几页
rows 每页多少条数据（pageSize）


登录错误的部分还未开发好

