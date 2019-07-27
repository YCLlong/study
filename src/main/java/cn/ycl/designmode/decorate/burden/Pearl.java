package cn.ycl.designmode.decorate.burden;

import cn.ycl.designmode.decorate.Burden;
import cn.ycl.designmode.decorate.Drink;

public class Pearl extends Burden {
    public Pearl(Drink drink) {
        super(drink);
        setDescription("珍珠");
        setPrice(2);
    }
}
