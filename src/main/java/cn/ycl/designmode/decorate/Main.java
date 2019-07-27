package cn.ycl.designmode.decorate;

import cn.ycl.designmode.decorate.burden.Milk;
import cn.ycl.designmode.decorate.burden.Pearl;
import cn.ycl.designmode.decorate.drink.OolongDrink;

public class Main {
    public static void main(String[] args) {
        //创建单品,
        Drink drink = new OolongDrink();
        //添加2分牛奶和1份珍珠
        drink = new Milk(drink);
        drink = new Milk(drink);
        drink = new Pearl(drink);
        System.out.println(drink.getDescription());
        System.out.println(drink.cost());
    }
}
