package Chat;

import UI.ChatRoomUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChatRoomController implements ActionListener{
    private ChatRoomUI crui;
    private ChatRoomModel crm;
    
    public ChatRoomController(ChatRoomUI ui) throws IOException{
        crui = ui;
        crm = new ChatRoomModel(crui);
    }
    public void sendMessage(String message){
        
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(source == crui.jbSend){
            if(!crui.getMessage().equals("")){
                String message = crui.getMessage();
                crm.sendMessage(message);
                crui.clearTF();
            }
        }
    }
    public void quit()
    {
        crm.quit();
    }
}
