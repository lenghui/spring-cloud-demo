package com.model;

import com.google.common.base.MoreObjects;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 学生表
 * @author http://kailing.pub
 */
@SuppressWarnings("serial")
@Entity
@Table(name="student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(generator="GEN_student")
    @GenericGenerator(name="GEN_student", strategy="native")
    @Column(name="id", nullable=false)
    private Integer id;

    @Column(name="STU_NAME", nullable=true, length=50)
    private String stuName;

    @Column(name="AGE", nullable=true)
    private Integer age;

    @Column(name="sex", nullable=true, length=4)
    private String sex;

    public static final String _id = "id";

    public static final String _stuName = "stuName";

    public static final String _age = "age";

    public static final String _sex = "sex";

    /**
     * <p>主键</p>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <p>主键</p>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <p>学生姓名</p>
     */
    public String getStuName() {
        return stuName;
    }

    /**
     * <p>学生姓名</p>
     */
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    /**
     * <p>年龄</p>
     */
    public Integer getAge() {
        return age;
    }

    /**
     * <p>年龄</p>
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * <p>性别</p>
     */
    public String getSex() {
        return sex;
    }

    /**
     * <p>性别</p>
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
        	.addValue(this.id)
        	.addValue(this.stuName)
        	.addValue(this.age)
        	.addValue(this.sex)
        	.toString();
    }
}