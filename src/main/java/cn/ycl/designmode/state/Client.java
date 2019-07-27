package cn.ycl.designmode.state;

public class Client{
    State state;
    public Client(){
        changeState();
    }
    public void changeState(){
        //根据不同的条件装换状态,这儿只是随机改变状态，没有什么意义，实际情况会根据业务逻辑改变状态
        int n = (int) (Math.random() * 10);
        if (n<=3){
            state = new HappyState();
        }else if(n<=6){
            state=new SadState();
        }else {
            state=new SadState();
        }
    }

    public void describe() {
        state.describe();
    }

    public void speek() {
        state.speek();
    }

    public void helpMe() {
        state.helpMe();
    }

    public static void main(String[] args) {
        Client client = new Client();
        for(int i=0;i<10;i++){
            System.out.println("---------------------第"+ i +"次改变状态--------------------");
            client.changeState();
            client.describe();
            client.speek();
            client.helpMe();
        }
    }
}
