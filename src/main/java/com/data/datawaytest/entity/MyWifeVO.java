package com.data.datawaytest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * 网络传输实体类
 * @author
 * @date
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyWifeVO extends MyPage{
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
}
