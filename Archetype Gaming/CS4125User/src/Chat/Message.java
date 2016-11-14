package Chat;

/**
 *
 * @author Dean
 */


public class Message {
    private String username, message;
    
    public Message(String u, String m)
    {
        username = u;
        message = m;
    }
    
    public String[] get()
    {
        String[] msg = {username, message};
        return msg;
    }
    
}
