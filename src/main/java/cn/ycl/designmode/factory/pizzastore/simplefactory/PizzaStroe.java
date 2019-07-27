package cn.ycl.designmode.factory.pizzastore.simplefactory;



public class PizzaStroe {
	public static void main(String[] args) {
		SimplePizzaFactory mSimplePizzaFactory = new SimplePizzaFactory();
		OrderPizza mOrderPizza;
		mOrderPizza=new	OrderPizza(mSimplePizzaFactory);
		
	}

	

}
