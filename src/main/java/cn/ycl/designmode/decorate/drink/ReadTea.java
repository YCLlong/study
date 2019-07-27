package cn.ycl.designmode.decorate.drink;

import cn.ycl.designmode.decorate.Drink;

/**
 * 红茶
 */
public class ReadTea extends Drink {
    private String description;

    public ReadTea(){
        description = "红茶";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float cost() {
        return 8;
    }
}
