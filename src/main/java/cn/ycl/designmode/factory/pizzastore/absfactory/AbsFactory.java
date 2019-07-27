package cn.ycl.designmode.factory.pizzastore.absfactory;


import cn.ycl.designmode.factory.pizzastore.pizza.Pizza;

public interface AbsFactory {
	public Pizza CreatePizza(String ordertype) ;
}
