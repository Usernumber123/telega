
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.SchemaOutputResolver;

import org.springframework.data.mongodb.core.MongoTemplate;
@Controller
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private FriendsRepository friendsRepository ;


    @RequestMapping(params = "submitmsg", method = RequestMethod.POST)
    public String submitmsg(Model model, String usermsg,  String user, String user2) {
        Message message=new Message();
        if(user2 != null) {
            message = new Message(user, user2, usermsg);
            messageRepository.save(message);
            List<Message> Messages = new ArrayList<Message>();

            for (Message a1 : messageRepository.findAll()) {
                if (a1.getFrom().equals(user) && (a1.getTo().equals(user2)) || a1.getFrom().equals(user2) && (a1.getTo().equals(user)))
                          Messages.add(a1);


            }
            User   user1 = new User(user);
            User     user22 = new User(user2);
            model.addAttribute("user1", user1);
            model.addAttribute("user2", user22);
            model.addAttribute("usermessage", Messages);
            model.addAttribute("friends", friendsRepository.findByUsername(user));
        }
        else{
            User    user1=new User(user);
            model.addAttribute("user1",user1 );
            model.addAttribute("message", "Choose Friend!");
            model.addAttribute("friends",friendsRepository.findByUsername(user) );
        }
        return "chat";


    }
}
