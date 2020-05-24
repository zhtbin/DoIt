### 添加自定义异常
* 复制org.springframework.jdbc.support下的sql-error-codes.xml到项目resources下
* 新建异常类：CustomJdbcException，一定要继承DataAccessException类
* 修改对象数据库bean 以H2为例，重新定义了主键重复的异常，如下
    `<property name="customTranslations">
        <bean class="org.springframework.jdbc.support.CustomSQLErrorCodesTranslation">
           <property name="errorCodes" value="23001,23505" />
         	<property name="exceptionClass" value="z.t.b.druid.demo.exception.CustomJdbcException" />
         </bean>
     </property>`
* 测试：
`@Test(expected = CustomJdbcException.class)
     public void testThrowingCustomException(){
         jdbcTemplate.execute("insert into foo(id, bar) values (1, 'x')");
         jdbcTemplate.execute("insert into foo(id, bar) values (1, 'y')");
     }`
* the end