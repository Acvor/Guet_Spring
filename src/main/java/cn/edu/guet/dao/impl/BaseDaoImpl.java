package cn.edu.guet.dao.impl;

import cn.edu.guet.dao.BaseDao;
import cn.edu.guet.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class BaseDaoImpl<T> implements BaseDao<T> {

    public static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
    PreparedStatement pstmt;
    Connection conn;
    private Class<T> persistentClass;

    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        persistentClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public int save(T t) throws SQLException {
        conn = DBConnection.getConn();
        String sql = "INSERT INTO " + t.getClass().getSimpleName().toLowerCase() + " (";
        List<Method> list = this.matchPojoMethods(t, "get");
        Iterator<Method> iter = list.iterator();
        Object obj[] = new Object[list.size()];
        int i = 0;
        // 拼接字段顺序 insert into table name(id,name,email,
        try {
            while (iter.hasNext()) {
                Method method = iter.next();
                sql += method.getName().substring(3).toLowerCase() + ",";
                if (method.getReturnType().getSimpleName().indexOf("Timestamp") != -1) {
                    obj[i] = Timestamp.valueOf(method.invoke(t, new Object[]{}).toString());
                } else {
                    obj[i] = method.invoke(t, new Object[]{});
                }
                i++;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 去掉最后一个,符号insert insert into table name(id,name,email) values(
        sql = sql.substring(0, sql.lastIndexOf(",")) + ") values(";
        // 拼装预编译SQL语句insert insert into table name(id,name,email) values(?,?,?,
        for (int j = 0; j < list.size(); j++) {
            sql += "?,";
        }
        // 去掉SQL语句最后一个,符号insert insert into table name(id,name,email) values(?,?,?);
        sql = sql.substring(0, sql.lastIndexOf(",")) + ")";
        // 到此SQL语句拼接完成,打印SQL语句
        System.out.println(">>>>>>>>The Auto-create Save-SQL is : >>>" + sql);
        logger.info(">>>>>>>>Current SQL: {}",sql);
        int affectRow = 0;
        pstmt = conn.prepareStatement(sql);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = uuid.replaceAll("[a-z]", "").substring(0, 15);
        Long id = Long.parseLong(uuid);
        for (int j = 0; j < obj.length; j++) {
            if ( list.get(j).getName().equals("getId")) {
                pstmt.setObject(j + 1, id);
            } else {
                pstmt.setObject(j + 1, obj[j]);
            }
        }
        affectRow = pstmt.executeUpdate();
        return affectRow;
    }

    /*
    * 根据Id更新数据
    * */
    @Override
    public int updateObjectById(Long id,T t) {
        conn = DBConnection.getConn();
        String sql = "UPDATE " + t.getClass().getSimpleName().toLowerCase() + " SET ";
        List<Method> list = this.matchPojoMethods(t, "get");
        Iterator<Method> iter = list.iterator();
        Object obj[] = new Object[list.size()];
        int i = 0;
        // 拼接字段顺序 update table_name SET id=?,name=?,email=?,
        try {
            while (iter.hasNext()) {
                Method method = iter.next();
                sql += method.getName().substring(3).toLowerCase() + "=?,";
                if (method.getReturnType().getSimpleName().indexOf("Timestamp") != -1) {
                    obj[i] = Timestamp.valueOf(method.invoke(t, new Object[]{}).toString());
                } else {
                    obj[i] = method.invoke(t, new Object[]{});
                }

                i++;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 去掉最后一个,即 update table_name SET id=?,name=?,email=? WHERE id=
        sql = sql.substring(0, sql.lastIndexOf(",")) + "WHERE id=";
        // 将Id拼接上去，即 update table_name SET id=?,name=?,email=? WHERE id=id;
        sql += id + ";";
        // 到此SQL语句拼接完成,打印SQL语句
        System.out.println(">>>>>>>>The Auto-create Update-SQL is : >>>" + sql);
        logger.info(">>>>>>>>Current SQL: {}",sql);
        int affectRow = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            //保持Id不变，修改其他数据
            pstmt.setObject(1, id);
            //从第二个待定问号开始
            for (int j = 1; j < obj.length; j++){
                pstmt.setObject(j + 1, obj[j]);
            }
            logger.info(">>>>>>>>>>>>>>>The Comit SQL is : >>> " + sql);
            affectRow = pstmt.executeUpdate();
            return affectRow;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConn(conn);
        }
        return 0;
    }

    @Override
    public int deleteObjectById(Long id) {
        conn = DBConnection.getConn();
        String sql = "DELETE FROM " + persistentClass.getSimpleName().toLowerCase() + " WHERE id=?";
        int affectRow = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, id);
            System.out.println(">>>>>>>>The Auto-created GetById-SQL is : >>>"+sql);
            affectRow=pstmt.executeUpdate();
            return affectRow;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Method> matchPojoMethods(T entity, String methodName) {
        // 获得当前Pojo所有方法对象
        Method[] methods = entity.getClass().getDeclaredMethods();
        // List容器存放所有带get字符串的Method对象
        List<Method> list = new ArrayList<Method>();
        // 过滤当前Pojo类所有带get字符串的Method对象,存入List容器
        for (int index = 0; index < methods.length; index++) {
            if (methods[index].getName().indexOf(methodName) != -1) {
                list.add(methods[index]);
            }
        }
        return list;
    }

    /*
    * 根据Id查找
    * 如果找不到则返回的是空
    * */
    @Override
    public T getObjectById(Long id) {
        conn = DBConnection.getConn();
        T t = null;
        String sql = "SELECT * FROM " + persistentClass.getSimpleName().toLowerCase() + " WHERE id=?";
        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                t = persistentClass.newInstance();// 反射动态创建对象
                List<Method> list = this.matchPojoMethods(t, "set");
                Iterator<Method> iter = list.iterator();
                while (iter.hasNext()) {
                    Method method = iter.next();
                    if (method.getParameterTypes()[0].getName().indexOf("String") != -1) {
                        // 反射调用setXXX方法给属性赋值
                        method.invoke(t, rs.getString(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getName().indexOf("int") != -1) {
                        method.invoke(t, rs.getInt(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getName().indexOf("Long") != -1) {
                        method.invoke(t, rs.getLong(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getName().indexOf("Timestamp") != -1) {
                        method.invoke(t, rs.getTimestamp(method.getName().substring(3).toLowerCase()));
                    }
                }
                System.out.println(">>>>>>>>The Auto-created GetById-SQL is : >>>"+sql);
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 查找全部
     * */
    @Override
    public List<T> getObjects() {
        conn = DBConnection.getConn();
        T t = null;
        List<T> objectList = new ArrayList<>();
        String sql = "SELECT * FROM " + persistentClass.getSimpleName().toLowerCase();
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                t = persistentClass.newInstance();// 反射动态创建对象
                objectList.add(t);
                List<Method> list = this.matchPojoMethods(t, "set");
                Iterator<Method> iter = list.iterator();
                while (iter.hasNext()) {
                    Method method = iter.next();
                    if (method.getParameterTypes()[0].getName().indexOf("String") != -1) {
                        // 反射调用setXXX方法给属性赋值
                        method.invoke(t, rs.getString(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getName().indexOf("int") != -1) {
                        method.invoke(t, rs.getInt(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getName().indexOf("Long") != -1) {
                        method.invoke(t, rs.getLong(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getName().indexOf("Timestamp") != -1) {
                        method.invoke(t, rs.getTimestamp(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getName().indexOf("Integer") != -1) {
                        method.invoke(t, rs.getInt(method.getName().substring(3).toLowerCase()));
                    }
                }
                System.out.println(">>>>>>>>The Auto-created GetAll-SQL is : >>>"+sql);
            }
            return objectList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
