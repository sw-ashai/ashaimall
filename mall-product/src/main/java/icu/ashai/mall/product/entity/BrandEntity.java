package icu.ashai.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import icu.ashai.common.valid.AddGroup;
import icu.ashai.common.valid.ListValue;
import icu.ashai.common.valid.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 品牌
 * 
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@NotNull(message = "修改必须指定ID",groups = {UpdateGroup.class})
	@Null(message = "新增不能指定ID",groups = {AddGroup.class})
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名不能为空",groups = {AddGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotEmpty(message = "品牌logo不能为空",groups = {AddGroup.class})
	@URL(message = "logo必须是一个合法URL",groups = {AddGroup.class,UpdateGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	@NotEmpty(message = "介绍不能为空",groups = {AddGroup.class})
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@ListValue(vals={0,1},groups = {AddGroup.class,UpdateGroup.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty(message = "检索首字母不能为空",groups = {AddGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须是一个字母",groups = {AddGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "检索首字母不能为空",groups = {AddGroup.class})
    @Min(value = 0,message = "排序必须大于等于0",groups = {AddGroup.class,UpdateGroup.class})
	private Integer sort;

}
