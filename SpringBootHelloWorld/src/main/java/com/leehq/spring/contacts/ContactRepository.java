package com.leehq.spring.contacts;

import com.leehq.spring.contacts.bean.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by putao_lhq on 17-4-27.
 */
@Repository
public class ContactRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Contact> findAll()
    {
        return jdbcTemplate.query("SELECT * FROM contacts", new RowMapper<Contact>()
        {
            @Override
            public Contact mapRow(ResultSet resultSet, int i) throws SQLException
            {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong(1));
                contact.setName(resultSet.getString(2));
                contact.setPhone(resultSet.getString(3));
                contact.setEmail(resultSet.getString(4));
                return contact;
            }
        });
    }

    public void save(Contact contact)
    {
        jdbcTemplate.update("INSERT INTO contacts" +
                "(name, phone, email) VALUES (?, ?, ?)", contact.getName(), contact.getPhone(), contact.getEmail());
    }
}
