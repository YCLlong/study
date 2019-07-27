package cn.ycl.designmode.command;

/**
 * 关灯命令对象
 * 命令对象封装了要执行的任务
 *
 */
public class LightOffCommand implements Command{
    Opration lightOpration;
    public LightOffCommand(String location){
        lightOpration = new Light(location);
    }
    @Override
    public void execute() {
        lightOpration.off();
    }

    @Override
    public void undo() {
        lightOpration.on();
    }
}
