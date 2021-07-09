
package edu.poly.shop.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import edu.poly.shop.util.Utils;

@Service
public class ParamService {

    @Autowired
    HttpServletRequest rq;

    public String getString(String inp, String def) {
        return inp != null ? inp : def;
    }

    public int getInt(String inp, int def) {
        try {
            return Integer.parseInt(inp);
        } catch (NumberFormatException | NullPointerException e) {
        }
        return def;
    }

    public double getDouble(String inp, double def) {
        try {
            return Double.parseDouble(inp);
        } catch (NumberFormatException | NullPointerException e) {
        }
        return def;
    }

    public boolean getBoolean(String inp, boolean def) {
        try {
            return Boolean.getBoolean(inp);
        } catch (NumberFormatException | NullPointerException e) {
        }
        return def;
    }

    public Date getDate(String inp, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(inp);
        } catch (ParseException ex) {
        }
        return null;
    }

    public File save(MultipartFile multipartFile, String path) {
        String name = multipartFile.getOriginalFilename();
        return save(multipartFile, path, name);
    }

    public ErrorManager saveImg(MultipartFile multipartFile, ErrorManager errorManager, String pathSave) {
        if (multipartFile.isEmpty()) {
            errorManager.add("file is empty!");
            return errorManager;
        }
//        if (!multipartFile.getOriginalFilename().endsWith(".png")) {
//            errorManager.add("Chỉ hỗ trợ ảnh png!");
//            return errorManager;
//        }
        String name = "img_" + Utils.random.nextInt(999999) + ".png";
        File result = save(multipartFile, pathSave, name);
        if (result == null) {
            errorManager.add("lưu ảnh vào db thất bại!");
            return errorManager;
        }
        errorManager.success(pathSave + name);
        return errorManager;
    }

    public File save(MultipartFile multipartFile, String path, String name) {
        String realpath = rq.getServletContext().getRealPath(path + name);
        File f = new File(realpath);
        try {
            multipartFile.transferTo(f);
            return f;
        } catch (IOException | IllegalStateException ex) {
        }
        return null;
    }
}
