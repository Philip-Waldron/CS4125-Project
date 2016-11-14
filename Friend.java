public class Friend {
    private String name;
    private String nickname;
    
    public Friend(String name){
        this.name = name;
        this.nickname = "";
    }
    
    public String friendInfo(String name){
        String info = this.name + " (" + this.nickname + ")";
        return info;
    }
    
    public void editNickname(String nick) {
        this.nickname = "nick";
    } 
}
