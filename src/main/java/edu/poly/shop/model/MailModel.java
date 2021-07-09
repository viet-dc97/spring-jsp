
package edu.poly.shop.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailModel {

    String from = "vietdcpd03375@fpt.edu.vn";
    String to;
    String subject;
    String body;
    List<String> cc = new ArrayList<>();
    List<String> bcc = new ArrayList<>();
    List<File> files = new ArrayList<>();

    public MailModel(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
