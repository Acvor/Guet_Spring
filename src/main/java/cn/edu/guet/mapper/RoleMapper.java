package cn.edu.guet.mapper;

import cn.edu.guet.bean.Role;
import cn.edu.guet.bean.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    long countByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int deleteByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    List<Role> selectByExampleWithRowbounds(RoleExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    List<Role> selectByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    Role selectByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    int updateByPrimaryKey(Role record);
}