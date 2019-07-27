package cn.ycl.designmode.command;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========单个命令演示===========");
        RemoteControl remoteControl = new RemoteControl(new LightOnCommand("主卧"));
        remoteControl.onPress();
        remoteControl.onRepeal();

        //操作一组命令，批量操作
        System.out.println("==========宏命令演示===========");
        List<Command> commandList = new ArrayList<>();
        commandList.add(new LightOffCommand("次卧"));
        commandList.add(new DoorOffCommand());
        commandList.add(new DoorOnCommand());
        remoteControl = new RemoteControl(commandList);
        remoteControl.onGroupPress();
        System.out.println("-----撤销之后的状态-----");
        remoteControl.onGroupRepeal();
    }
}
