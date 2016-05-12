

-- ngx.say("hello world!".."北京");
ngx.log(ngx.ERR,"err err");
--[[
if true then
  ngx.say("测试");   

end

ngx.say("测试");   
location @web{
     proxy_pass http://web_servers/;
}

return ngx.exec("@web");]]


 
--location=@client{
--   proxy_pass http://web_servers/;
--}

--正确
--return ngx.exec("@web"); 

--return ngx.redirect("http://baidu.com");
--[[header
local headers = ngx.req.get_headers()
ngx.say("headers begin", "<br/>")
ngx.say("Host : ", headers["Host"], "<br/>")
ngx.say("user-agent : ", headers["user-agent"], "<br/>")
ngx.say("user-agent : ", headers.user_agent, "<br/>")
for k, v in pairs(headers) do
    if type(v) == "table" then
        ngx.say(k, " : ", table.concat(v, ","), "<br/>")
    else
        ngx.say(k, " : ", v, "<br/>")
    end
end
]]
--[[get
local uri_args = ngx.req.get_uri_args();
for k, v in pairs(uri_args) do
    if type(v) == "table" then
        ngx.say(k, " : ", table.concat(v, ", "), "<br/>")
    else
        ngx.say(k, ": ", v, "<br/>")
    end
end
]]



---redis相关
--[[
local redis = require "resty.redis"    
    
local cache = redis.new()    
    
local ok, err = cache.connect(cache, '127.0.0.1', '6379')    
    
cache:set_timeout(60000)    
    
if not ok then    
        ngx.say("failed to connect:", err)    
        return    
end    

---设置值
res, err = cache:set("dog", "an aniaml")    
if not ok then    
        ngx.say("failed to set dog: ", err)    
        return    
end    
    
ngx.say("set result: ", res)    


local res, err = cache:get("dog")    
if not res then    
        ngx.say("failed to get dog: ", err)    
        return    
end    
    
if res == ngx.null then    
        ngx.say("dog not found.")    
        return    
end    
    
ngx.say("dog: ", res)    
    
    
local ok, err = cache:close()    
    
if not ok then    
        ngx.say("failed to close:", err)    
        return    
end  
 ]]


 
mytable ={};
mytable['id']=1;
mytable['name']='测试';
mytable['sex']='F';


ngx.say(type(mytable));
ngx.say('<br/>');
for k,v in pairs(mytable) do
    ngx.say(k,":",v,'<br/>');
end

json = require 'cjson';

jsonStr = json.encode(mytable);
ngx.say(jsonStr);

--resultStr=json.decode(jsonStr);
--ngx.say(resultStr);    
 



















