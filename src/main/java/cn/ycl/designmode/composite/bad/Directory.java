package cn.ycl.designmode.composite.bad;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Directory {
    /**
     * 文件夹名称
     */
    private String name;
    /**
     * 文件夹创建时间
     */
    private Date createTime;
    /**
     * 文件夹所在文件夹
     */
    private Directory parent;
    /**
     * 当前文件夹下的所有文件
     */
    private List<File> fileList;
    /**
     * 当前文件夹下的所有文件夹
     */
    private List<Directory> directoryList;
}
