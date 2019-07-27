package cn.ycl.designmode.factory.pizzastore.method;


import cn.ycl.designmode.factory.pizzastore.pizza.LDCheesePizza;
import cn.ycl.designmode.factory.pizzastore.pizza.LDPepperPizza;
import cn.ycl.designmode.factory.pizzastore.pizza.Pizza;

public class LDOrderPizza extends OrderPizza {

	@Override
	Pizza createPizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		return pizza;

	}

}
