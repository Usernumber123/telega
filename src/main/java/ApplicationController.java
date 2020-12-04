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
public class ApplicationController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private FriendsRepository friendsRepository ;
    @Autowired
    private MessageRepository messageRepository;

   /* Validator validator = new Validator();
    Friends friend;

    User user=new User();
    User user1=new User();
   // User user2=new User();
    User user22=new User();

    List<Friends> friends;*/

    @GetMapping("/chat")
    public String chat(Model model,@RequestParam( required=false)String user) {

        model.addAttribute("usermessage", messageRepository.findAll());
        model.addAttribute("friends",friendsRepository.findByUsername(user) );
        return "chat";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(params = "LogIn", method = RequestMethod.POST)
    public String loginButton(Model model, String username, String password) {

        boolean b=false;
        for(User a : repository.findAll()){
            if(a. getUsername().equals(username)) {
                if (a.getPassword().equals(password)) b=true;
            }

        }
        if (b)//validator.Valid(username,password))
        {
            User user=new User(username,password);
            model.addAttribute("user1",user);
           model.addAttribute("message", "You have been logged on successfully.");
           // model.addAttribute("usermessage", messageRepository.findByFromAndTo(user.getUsername(),user2.getUsername()));
            model.addAttribute("friends",friendsRepository.findByUsername(user.getUsername()) );

            return "chat";
        }
        else{
            model.addAttribute("error", "Your username and password is invalid.");
            return "login";}
    }



 /*   @RequestMapping(params = "Yes", method = RequestMethod.GET)

    public String Yes(Model model,@RequestParam String friend1, String user, String user2) {
        System.out.println(friend1+" asda");

                                               for (Friends a :  friendsRepository.findAll())
                                                {
                                                    if(a.getFriends().equals(friend1)&& a.getUsername().equals(user)) {
                                                        friend = new Friends( a.getUsername(),a.getFriends(), true);
                                                        friendsRepository.delete(a);
                                                        friendsRepository.save(friend);
                                                    }
                                                }
        user1=new User(user);
        model.addAttribute("user1",user1 );
        user22 = new User(user2);
        model.addAttribute("user2",user22 );
        model.addAttribute("friends",friendsRepository.findByUsername(user) );
        return "chat";
    }
    @RequestMapping(params = "Delete", method = RequestMethod.GET)

    public String Delete(Model model,String friend1, String user, String user2) {
        System.out.println(friend1+"asda");
        for (Friends a :  friendsRepository.findAll())
        {
            System.out.println(a.getUsername() + "  n");
            if(a.getFriends().equals(friend1)&& a.getUsername().equals(user))
            friendsRepository.delete(a);
            if(a.getFriends().equals(user)&& a.getUsername().equals(friend1))
                friendsRepository.delete(a);
        }
        user1=new User(user);
        model.addAttribute("user1",user1 );
        user22 = new User(user2);
        model.addAttribute("user2",user22 );
        model.addAttribute("friends",friendsRepository.findByUsername(user) );
        return "chat";
    }*/


    @RequestMapping(params = "Submit", method = RequestMethod.POST)
        public String Save(Model model,String username1,String password1){

        if(username1 != ""&& password1 != "" ){
             User  user=new User(username1,password1);

              repository.save(user);
            return "login";
        }
        else   model.addAttribute("error", "Error,all fields must be filled");
        //model.addAttribute("persons", user);
        return "registration";


    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

}