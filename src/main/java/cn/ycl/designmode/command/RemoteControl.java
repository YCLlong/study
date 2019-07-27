package cn.ycl.designmode.command;

import lombok.Data;

import java.util.List;

@Data
public class RemoteControl {
    private Command command;
    private List<Command> commandList;

    public RemoteControl(Command command){
        this.command = command;
    }

    public RemoteControl(List<Command> commandList){
        this.commandList = commandList;
    }

    public void onPress(){
        command.execute();
    }

    public void onRepeal(){
        command.undo();
    }

    public void onGroupPress(){
        for(Command command : commandList){
            command.execute();
        }
    }

    public void onGroupRepeal(){
        for(Command command : commandList){
            command.undo();
        }
    }
}
