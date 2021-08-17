package com.desafio.prevent.log.services;

import com.desafio.prevent.log.dao.LogDao;
import com.desafio.prevent.log.domain.Log;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class FileService {
    @Autowired
    private LogDao repo;

    public void save(MultipartFile file) throws FileUploadException {
        BufferedReader br;
        List<String> result = new ArrayList<>();
        try {
            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
            proccessList(result);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void proccessList(List<String> result) {
        for(String strLine : result) {
            String[] parts = strLine.split(Pattern.quote("|"));
            Log cLog = new Log();
            cLog.setData(parts[0]);
            cLog.setIp(parts[1]);
            cLog.setRequest(parts[2]);
            cLog.setStatus(parts[3]);
            cLog.setAgent(parts[4]);
            repo.insert(cLog);
        }
    }
}