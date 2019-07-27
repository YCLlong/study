package cn.ycl.designmode.decorate.drink;

import cn.ycl.designmode.decorate.Drink;

/**
 * 乌龙茶
 */
public class OolongDrink extends Drink {
    private String description;

    public OolongDrink(){
        description = "乌龙茶";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float cost() {
        return 10;
    }
}
