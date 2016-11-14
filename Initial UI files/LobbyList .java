public class LobbyList {
    
    ArrayList<Game> list;
    
    public LobbyList()
    {
        
    }
    
    public boolean addGame(Game g)
    {
        
        /*
                
        Verify that the game doesn't already exist on the server. If it does, return false.
        
        */
        
        list.add(g);
        
        return true;
    }
   
    public boolean removeGame(Game g)
    {
        
        /*
        
        Search the array list for the game and remove it from the list. If successfully deleted. return true/
        
        */
        return true;
    }
}
