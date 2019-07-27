package cn.ycl.designmode.factory.pizzastore.method;


import cn.ycl.designmode.factory.pizzastore.pizza.NYCheesePizza;
import cn.ycl.designmode.factory.pizzastore.pizza.NYPepperPizza;
import cn.ycl.designmode.factory.pizzastore.pizza.Pizza;

public class NYOrderPizza extends OrderPizza {

	@Override
	Pizza createPizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new NYCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new NYPepperPizza();
		}
		return pizza;

	}

}
