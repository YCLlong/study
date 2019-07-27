package cn.ycl.designmode.command;

/**
 * 开灯命令对象
 * 命令对象封装了要执行的任务
 *
 */
public class LightOnCommand implements Command{
    Opration lightOpration;
    public LightOnCommand(String location){
        lightOpration = new Light(location);
    }

    @Override
    public void execute() {
        lightOpration.on();
    }

    @Override
    public void undo() {
        lightOpration.off();
    }
}
