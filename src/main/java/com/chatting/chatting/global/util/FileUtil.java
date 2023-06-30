package com.chatting.chatting.global.util;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtil {

    private final String path = "C:\\Users\\ty_ty\\OneDrive\\바탕 화면\\코딩자료\\Spring\\chatting\\src\\main\\resources\\static";

    public List<String> saveFileList(String userId, List<MultipartFile> files) {
        List<String> fileNameList = new ArrayList<>();
        File directirFile = new File(path + "\\" + userId);
        for (MultipartFile file : files) {
            fileNameList.add(saveFile(file, directirFile, userId));
        }
        return fileNameList;
    }

    private String saveFile(MultipartFile file, File directirFile,String userId)  {

        //존재 하지 않을경우
        if (!directirFile.exists()) {
            // 디렉터리 생성에 실패했을 경우
            if (!directirFile.mkdirs()) {
                System.out.println("file: was not successful");
            }
        }
        String contentType = file.getContentType(); //확장자 추출
        //확장자 존재 하지 않을 경우 TODO: 에러 처리 해야함.
        if (ObjectUtils.isEmpty(contentType)) {
            return null;
        }
        if (!(
                contentType.equals("image/jpg") ||
                        contentType.equals("image/jpeg") ||
                        contentType.equals("image/png"))
        ) {
            //확장자 이상할 경우 TODO: 에러 처리 해야함.
            return null;
        }else{
            contentType = switch (contentType){
                case "image/jpg" -> ".jpg";
                case "image/jpeg" -> ".jpeg";
                case "image/png" -> ".png";
                default -> "";//TODO: 에러 처리
            };
        }
        //중복 방지로 나노초로 방지.
        String new_file_name = System.nanoTime() + contentType;
        File downloadFile = new File(path+"\\"+userId+"\\" + new_file_name);

        try {
            file.transferTo(downloadFile);
        }catch (IOException e){
            //TODO: 에러 처리 저장 안되는 에러 처리.
            System.out.println(e);
        }

        return userId+"\\"+new_file_name;
    }


}
