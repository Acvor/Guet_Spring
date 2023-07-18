package cn.edu.guet.mapper;

import cn.edu.guet.bean.Permission;
import cn.edu.guet.bean.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    long countByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int deleteByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int deleteByPrimaryKey(Integer permissionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int insert(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int insertSelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    List<Permission> selectByExampleWithRowbounds(PermissionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    List<Permission> selectByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    Permission selectByPrimaryKey(Integer permissionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permissions
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int updateByPrimaryKey(Permission record);
}