package com.example.easyexceldemo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.example.easyexceldemo.converter.LocalDateStringConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qqq
 * @since 2022-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = false)
@TableName("t_people")
@ContentRowHeight(18)
@HeadRowHeight(25)
@ColumnWidth(20)
@ExcelIgnoreUnannotated
public class People implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ExcelIgnore
    private String id;

    /**
     * 姓名
     */
    @TableField("name")
    @ExcelProperty(value = "姓名",index = 0)
    private String name;

    /**
     * 年龄
     */
    @TableField("age")
    @ExcelProperty(value = "年龄",index = 1)
    private Integer age;

    /**
     * 性别( 0:女 1：男)
     */
    @TableField("sex")
    @ExcelProperty(value = "性别",index = 2)
    private String sex;

    /**
     * 生日
     */
    @TableField("birthday")
    @ExcelProperty(value = "生日",index = 3,converter = LocalDateStringConverter.class)
    private LocalDateTime birthday;

    /**
     * 创建人
     */
    @TableField("create_by")
    @ExcelIgnore
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ExcelIgnore
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    @ExcelIgnore
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ExcelIgnore
    private LocalDateTime updateTime;

    /**
     * 删除标记(0：未删除 1：已删除)
     */
    @TableField("del_flag")
    @TableLogic
    @ExcelIgnore
    private String delFlag;


}
