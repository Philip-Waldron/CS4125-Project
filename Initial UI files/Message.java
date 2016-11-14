public class Message{
	private String messageContents;
	private String sender;
	public Message(String messageContents, String sender){
		this.messageContents = messageContents;
		this.sender = sender;
	}
	public String getContent(){
		return messageContents;
	}
	public String getsender(){
		return sender;
	}
}
