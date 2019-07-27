package cn.ycl.designmode.decorate;

import lombok.Data;


@Data
public abstract class Drink {
    private String description;

    /**
     * 计算价格的方法，让子类实现
     * @return
     */
    public abstract float cost();
}
