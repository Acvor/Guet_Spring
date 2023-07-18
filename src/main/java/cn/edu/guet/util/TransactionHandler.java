package cn.edu.guet.util;

import cn.edu.guet.common.ResponseData;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

//JDK动态代理
public class TransactionHandler implements InvocationHandler {
    public static Logger logger = LoggerFactory.getLogger(TransactionHandler.class);

    //目标对象
    private Object targetObject;
    //根据目标对象，创建代理对象
    public Object createProxyObject(Object targetObject){
        this.targetObject=targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        SqlSession sqlSession = null;
        try {
            sqlSession = DBConnection.getSqlSession();
            // 方法名称的开头
            // 增：create、save、new
            // 删：delete
            // 改：update
            logger.info("Methos>>>{}", method.getName());
            //返回结果
            Object retValue = null;
            if (method.getName().startsWith("create") || method.getName().startsWith("save")
                    || method.getName().startsWith("new")
                    || method.getName().startsWith("delete")
                    || method.getName().startsWith("update")
                    || method.getName().startsWith("my")) {
                //调用目标方法
                retValue = method.invoke(targetObject, args);
                //提交事务
                sqlSession.commit();
            }
            else{
                // 不开事务，直接调用目标方法
                retValue = method.invoke(targetObject, args);
            }
            return retValue;
        }catch (InvocationTargetException e) {
            e.printStackTrace();
            sqlSession.rollback();
            logger.info("数据库进行了回滚操作.SqlSession rollback.");
            return ResponseData.fail("数据库操作失败！");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
