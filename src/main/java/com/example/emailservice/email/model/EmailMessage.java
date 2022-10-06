package com.example.emailservice.email.model;

import com.example.emailservice.email.config.EmailProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Evelina Tubalets
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {

    private EmailProperties emailProperties;

    private List<BirthdayUser> users;

}
