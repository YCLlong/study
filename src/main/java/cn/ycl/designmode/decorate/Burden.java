package cn.ycl.designmode.decorate;

/**
 * 配料类
 * 相当于装饰者
 */
public class Burden extends Drink {
    /**
     * 在哪个单品奶茶中加配料，使用组合实现功能扩展
     */
    private Drink drink;
    private String description;
    private float price;

    public Burden(Drink drink){
        this.drink = drink;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + "," + description;
    }

    public void setPrice(float price){
        this.price = price;
    }

    @Override
    public float cost() {
        return this.price + drink.cost();
    }
}
