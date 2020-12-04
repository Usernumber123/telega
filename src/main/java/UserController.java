
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
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private FriendsRepository friendsRepository ;
    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(params = "choose", method = RequestMethod.GET)
    public String choose(Model model,String choose,String user) {
        User user2=new User();
        for (User a : repository.findAll())
        {
            if(a.getUsername().equals(choose))user2=a;

        }
        List<Message> Messages=new ArrayList<Message>();
        for (Message a1 : messageRepository.findAll())
        {
            if(a1.getFrom().equals(user)&& (a1.getTo().equals(user2.getUsername())) || a1.getFrom().equals(user2.getUsername())&& (a1.getTo().equals(user)))
                Messages.add(a1);


        }
        User user1=new User(user);
        model.addAttribute("user1",user1 );
        model.addAttribute("user2",user2 );
        model.addAttribute("usermessage",Messages);
        model.addAttribute("friends",friendsRepository.findByUsername(user) );

        return "chat";


    }

    @RequestMapping(params = "Add", method = RequestMethod.GET)

    public String Add(Model model,String findUser, String user, String user2) {

        boolean trying=true;
        if (repository.findByUsername(findUser)==null)model.addAttribute("message1","User Not Found!");
        else
        {
            Friends  friend = new Friends(user, repository.findByUsername(findUser).getUsername(),true);
            for (Friends a :  friendsRepository.findAll())
            {

                if(a.getFriends().equals(friend.getFriends())&& a.getUsername().equals(friend.getUsername()))trying=false;

            }
            if(trying) {
                friendsRepository.save(friend);
                if(!friend.getFriends().equals(user)) {
                    friend = new Friends(repository.findByUsername(findUser).getUsername(), user, false);
                    friendsRepository.save(friend);
                }
            }
            else model.addAttribute("message1","User already add!");
        }


        User user1=new User(user);
        model.addAttribute("user1",user1 );
        User user22 = new User(user2);
        model.addAttribute("user2",user22 );
        model.addAttribute("friends",friendsRepository.findByUsername(user) );

        return "chat";

    }
}
