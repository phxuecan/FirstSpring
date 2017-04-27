package com.leehq.spring.contacts;

import com.leehq.spring.contacts.bean.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by putao_lhq on 17-4-27.
 */
@Controller
@RequestMapping("/contacts")
public class ContactsController
{
    @Autowired
    private ContactRepository contactRepository;
    /**
     * 查询到所有联系人,并展示其home视图
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String, Object> model)
    {
        List<Contact> contacts = contactRepository.findAll();
        if (contacts == null || contacts.size() == 0)
        {
            Contact contact = new Contact();
            contact.setEmail("lhq@putao.cn");
            contact.setName("lhq");
            contact.setPhone("15293565456");
            contacts.add(contact);
        }
        model.put("contacts", contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String subcommit(Contact contact)
    {
        contactRepository.save(contact);
        return "redirect:/";
    }
}
