package cn.ycl.designmode.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class MyData {
    private String name;
    private Integer math;
    private Integer english;
}
