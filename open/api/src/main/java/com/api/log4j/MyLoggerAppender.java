package com.api.log4j;

import com.mongodb.DBObject;
import org.log4mongo.MongoDbAppender;

/**
 * Created by 51offer on 2016/3/7.
 */
public class MyLoggerAppender extends MongoDbAppender {

    private static String ERROR = "ERROR";

    public void append(DBObject bson) {
     /*   if (this.isInitialized() && bson != null) {
            JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(bson));
            String level = json.getString("level");
            if (StringUtils.isNotBlank(level) && level.equals(ERROR)) {
                JSONObject loggerNameJson = json.getJSONObject("loggerName");
                if (null != loggerNameJson) {
                    String className = loggerNameJson.getString("fullyQualifiedClassName");
                    if(className.startsWith("com.horizon")){
                        //发邮件

                    }
                }
            }
        }*/
        super.append(bson);
    }
}
