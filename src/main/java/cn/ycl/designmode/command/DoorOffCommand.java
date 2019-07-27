package cn.ycl.designmode.command;

/**
 * 关灯命令对象
 * 命令对象封装了要执行的任务
 *
 */
public class DoorOffCommand implements Command{
    Opration dorrOpration = new Door("次卧");


    @Override
    public void execute() {
        dorrOpration.off();
    }

    @Override
    public void undo() {
        dorrOpration.on();
    }
}
