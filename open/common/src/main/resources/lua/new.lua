

local request_method = ngx.var.request_method

local args=nil

if "GET" == request_method then
   args = ngx.req.get_uri_args()
end

upgrade = args["upgrade"]
if upgrade == "true" then
   return ngx.exec("@upgrade");
end


 return ngx.exec("@default");