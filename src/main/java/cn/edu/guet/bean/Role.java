package cn.edu.guet.bean;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roles.ROLE_ID
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roles.ROLE_NAME
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    private String roleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roles.CREATED_BY
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roles.CREATED_TIME
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    private Date createdTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roles.UPDATED_BY
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    private String updatedBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roles.UPDATED_TIME
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    private Date updatedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roles.ROLE_ID
     *
     * @return the value of roles.ROLE_ID
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public Role withRoleId(Integer roleId) {
        this.setRoleId(roleId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roles.ROLE_ID
     *
     * @param roleId the value for roles.ROLE_ID
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roles.ROLE_NAME
     *
     * @return the value of roles.ROLE_NAME
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public Role withRoleName(String roleName) {
        this.setRoleName(roleName);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roles.ROLE_NAME
     *
     * @param roleName the value for roles.ROLE_NAME
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roles.CREATED_BY
     *
     * @return the value of roles.CREATED_BY
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public Role withCreatedBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roles.CREATED_BY
     *
     * @param createdBy the value for roles.CREATED_BY
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roles.CREATED_TIME
     *
     * @return the value of roles.CREATED_TIME
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public Role withCreatedTime(Date createdTime) {
        this.setCreatedTime(createdTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roles.CREATED_TIME
     *
     * @param createdTime the value for roles.CREATED_TIME
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roles.UPDATED_BY
     *
     * @return the value of roles.UPDATED_BY
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public Role withUpdatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roles.UPDATED_BY
     *
     * @param updatedBy the value for roles.UPDATED_BY
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roles.UPDATED_TIME
     *
     * @return the value of roles.UPDATED_TIME
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public Role withUpdatedTime(Date updatedTime) {
        this.setUpdatedTime(updatedTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roles.UPDATED_TIME
     *
     * @param updatedTime the value for roles.UPDATED_TIME
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Mon Jul 17 17:14:50 CST 2023
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append("]");
        return sb.toString();
    }
}