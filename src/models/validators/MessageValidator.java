package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Message;

public class MessageValidator {


    public static List<String> validate(Message m){
        List<String> error = new ArrayList<String>();//jspのitems="${error}"部分

        String error_content = _validateContent(m.getContent());
        if(!error_content.equals("")){
            error.add(error_content);
        }
        return error;
    }
    public static String _validateContent(String content){//jspの・<c:out value="${error}" />部分
        if(content == null || content.equals("")){
            return "メッセージを入力してください。";
        }
            return "";
    }
}
