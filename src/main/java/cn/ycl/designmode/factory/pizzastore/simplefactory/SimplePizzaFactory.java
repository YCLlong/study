package cn.ycl.designmode.factory.pizzastore.simplefactory;


import cn.ycl.designmode.factory.pizzastore.pizza.CheesePizza;
import cn.ycl.designmode.factory.pizzastore.pizza.GreekPizza;
import cn.ycl.designmode.factory.pizzastore.pizza.PepperPizza;
import cn.ycl.designmode.factory.pizzastore.pizza.Pizza;

public class SimplePizzaFactory {

	public Pizza CreatePizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new CheesePizza();
		} else if (ordertype.equals("greek")) {
			pizza = new GreekPizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new PepperPizza();
		}
		return pizza;

	}

}
