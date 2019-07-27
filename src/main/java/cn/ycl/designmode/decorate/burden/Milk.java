package cn.ycl.designmode.decorate.burden;

import cn.ycl.designmode.decorate.Burden;
import cn.ycl.designmode.decorate.Drink;

public class Milk extends Burden {

    public Milk(Drink drink) {
        super(drink);
        setDescription("牛奶");
        setPrice(3);
    }
}
