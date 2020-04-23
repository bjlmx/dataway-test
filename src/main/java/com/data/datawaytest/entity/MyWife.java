package com.data.datawaytest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 实体类
 * @author john
 */
@Builder
@Data
@AllArgsConstructor
@TableName("wife")
@NoArgsConstructor
public class MyWife implements Serializable {
    private static final long serialVersionUID = 7628046321872813792L;
    @TableId(type = IdType.INPUT)
	@NotNull(message = "id不能为空")
	private String id;
	@NotNull(message = "用户姓名不能为空")
	private String name;
	private String address;
	private String school;
	@Range(min = 20,max =26,message = "年龄限制，最小20，最大26")
	private Integer age;
	@Email(message = "邮箱格式不对")
	private String email;

	public MyWife(@NotNull(message = "用户姓名不能为空") String name, String address, String school, @Range(min = 20, max = 26, message = "年龄限制，最小20，最大26") int age, @Email(message = "邮箱格式不对") String email) {
		this.name = name;
		this.address = address;
		this.school = school;
		this.age = age;
		this.email = email;
	}

}
