package com.zhaoxp.fastdemo.domain.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName User
 * @Description TODO
 * @Author zhaoxp
 * @Date 2019/12/19 15:37
 * @Version 1.0
 **/
@Data
@ToString
@TableName("user")
public class User {
    @TableId
    private int id;
    private String name;
    private int companyId;
    @Version
    private Integer version;
}
