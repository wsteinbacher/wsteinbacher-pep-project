package Service;

import DAO.MessageDAO;
//import Service.AccountService;
import Model.Message;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message createMessage(Message message) {
        AccountService accountService = new AccountService();

        if(!accountService.checkExists(message.posted_by) || message.message_text.length() > 255 || message.message_text.length() == 0) {
            return null;
        }

        return messageDAO.createMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
    
    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public Message deleteMessage(int id) {
        Message message = messageDAO.getMessageById(id);
        messageDAO.deleteMessage(id);
        return message;
    }

    public Message updateMessage(Message message, int id) {
        if(messageDAO.getMessageById(id) == null || message.message_text.length() > 255 || message.message_text.length() == 0) {
            return null;
        }

        messageDAO.updateMessage(message, id);

        return messageDAO.getMessageById(id);

        //return messageDAO.updateMessage(updateText, id);
    }

    public List<Message> getMessagesByUser(int id) {
        return messageDAO.getMessagesByUser(id);
    }
}