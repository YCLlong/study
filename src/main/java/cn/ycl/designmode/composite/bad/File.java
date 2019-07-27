package cn.ycl.designmode.composite.bad;

import lombok.Data;

import java.util.Date;

@Data
public class File {
    /**
     * 当前文件所在的文件夹对象向
     */
    private Directory parent;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 文件创建时间
     */
    private Date createTime;
    /**
     * 文件最后修改时间
     */
    private Date lastModifyTime;
}
