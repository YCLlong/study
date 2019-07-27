package cn.ycl.designmode.factory.pizzastore.absfactory;


import cn.ycl.designmode.factory.pizzastore.pizza.LDCheesePizza;
import cn.ycl.designmode.factory.pizzastore.pizza.LDPepperPizza;
import cn.ycl.designmode.factory.pizzastore.pizza.Pizza;

public class LDFactory implements AbsFactory {

	@Override
	public Pizza CreatePizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		return pizza;

	}

}
