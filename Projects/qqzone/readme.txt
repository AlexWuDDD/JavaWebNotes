1. 熟悉QQZone业务要求
    1）用户登录
    2）登录成功，显示主界面，左侧显示好友列表；上端显示欢迎词，如果不是自己的空间，显示超链接：返回自己的空间；下端显示日志列表
    3）查看日志详情：
        - 日志本身的信息（作者头像、昵称、日志标题、日志内容、日志的日期）
        - 回复列表（回复者的头像、昵称、回复内容、回复日期）
        - 主人回复
    4）删除日志
    5）删除特定回复
    6）删除特定主人回复
    7）添加日志、添加回复、添加主人回复
    8）点击左侧好友链接，进入好友的空间
2. 数据库设计
    1）抽取实体：用户登录信息、用户详情信息、日志、回帖、主人回复
    2）分析其中的属性：
        - 用户登录信息：账号、密码、头像、昵称
        - 用户详情信息：真实姓名、星座、血型、邮箱、手机号......
        - 日志：标题、内容、日期、作者
        - 回复: 内容、日期、作者、日志
        - 主人回复：内容、日期、作者、回复
    3）分析实体之间的关系
        - 用户登录信息:用户详情信息       1:1 PK
        - 用户: 日志                   1:N
        - 日志: 回复                   1:N
        - 回复: 主人回复                1:1 UK
        - 用户: 好友                   N:N
3: 数据库的范式：
    1）第一范式：列不可再分
    2）第二范式：一张表只表达一层含义（只描述一件事情）
    3）第三范式：表中的每一列和主键都是直接关系，而不是间接依赖
4. 数据库设计的范式和数据库的查询很多时候是相悖的，我们需要根据实际的业务情况做一个选择：
    - 查询频次不高的情况下，我们更倾向于提高数据库的设计范式，从而提高存储效率
    - 查询频次较高的情形，我们更倾向于牺牲数据库的规范度，降低数据库设计的范式，允许特定的冗余，从而提高查询性能

5. 页面没有样式，同时数据也不显示，原因是：我们是直接请求的静态页面资源，那么并没有执行super.processTemplate(), 也就是thymeleaf没有起作用
   (之前的表单也是这个原因)

6. 删除回复
    1）如果回复有关联的主人回复，需要先删除主人回复
    2）删除回复

7. 删除日志
    1）删除日志，首先需要考虑是否有关联的回复
    2）删除回复，首先需要考虑是否有关联的主人回复
    3）另外，如果不是自己的空间，则不能删除日志

8. 目前我们进行javaweb项目开发的“套路”是这样的：
    1）拷贝myssm包
    2）新建配置文件applicationContext.xml
    3）在web.xml文件中配置
        <context-param>
        <param-name>view-prefix</param-name>
        <param-value>/</param-value>
        </context-param>
        <context-param>
            <param-name>view-suffix</param-name>
            <param-value>.html</param-value>
        </context-param>

        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>applicationContext.xml</param-value>
        </context-param>
    4）开发具体的业务模块
        - html页面
        - POJO类
        - DAO接口和实现类
        - Service接口和实现类
        - Controller控制器组件

        - 如果html页面有thymeleaf表达式，一定不能直接访问，必须经过PageController
        - 在applicationContext.xml中配置DAO、Service、Controller，以及三者之间的依赖关系
        - DAO实现类中，继承BaseDAO, 然后实现具体的接口