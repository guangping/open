package com.varela.open.client;


import com.alibaba.fastjson.JSONObject;
import com.varela.open.Constants;
import com.varela.open.MessageFormat;
import com.varela.open.RopRequest;
import com.varela.open.annotation.IgnoreSign;
import com.varela.open.annotation.Temporary;
import com.varela.open.client.response.ErrorResponse;
import com.varela.open.config.SystemParameterNames;
import com.varela.open.params.base.BaseRopRequest;
import com.varela.open.utils.HttpUtils;
import com.varela.open.utils.RopUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-30 16:30
 * To change this template use File | Settings | File Templates.
 */
public class DefaultRopHttpClient implements RopHttpClient {
    //服务地址
    private String serverUrl;

    //应用键
    private String appKey;

    //应用密钥
    private String appSecret;

    private String sessionId;

    //报文格式
    private MessageFormat messageFormat = MessageFormat.json;

    private Locale locale = Locale.getDefault();

    //请求类所有请求参数
    private Map<Class<?>, List<Field>> requestAllFields = new HashMap<Class<?>, List<Field>>();

    //请求类所有不需要进行签名的参数
    private Map<Class<?>, List<String>> requestIgnoreSignFieldNames = new HashMap<Class<?>, List<String>>();


    public DefaultRopHttpClient(String serverUrl, String appKey, String appSecret) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public DefaultRopHttpClient(String serverUrl, String appKey, String appSecret, MessageFormat messageFormat) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.messageFormat = messageFormat;
    }

    public DefaultRopHttpClient(String serverUrl, String appKey, String appSecret, MessageFormat messageFormat, Locale locale) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.messageFormat = messageFormat;
        this.locale = locale;
    }


    public MessageFormat getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(MessageFormat messageFormat) {
        this.messageFormat = messageFormat;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public RopHttpClient setAppKeyParamName(String paramName) {
        SystemParameterNames.setAppKey(paramName);
        return this;
    }


    public RopHttpClient setSessionIdParamName(String paramName) {
        SystemParameterNames.setSessionId(paramName);
        return this;
    }


    public RopHttpClient setMethodParamName(String paramName) {
        SystemParameterNames.setMethod(paramName);
        return this;
    }


    public RopHttpClient setVersionParamName(String paramName) {
        SystemParameterNames.setVersion(paramName);
        return this;
    }


    public RopHttpClient setFormatParamName(String paramName) {
        SystemParameterNames.setFormat(paramName);
        return this;
    }


    public RopHttpClient setLocaleParamName(String paramName) {
        SystemParameterNames.setLocale(paramName);
        return this;
    }


    public RopHttpClient setSignParamName(String paramName) {
        SystemParameterNames.setSign(paramName);
        return this;
    }


    public ClientHttpRequest buildClientRequest() {
        return new DefaultHttpClientRequest(this);
    }

    private class DefaultHttpClientRequest implements ClientHttpRequest {
        private RopHttpClient ropClient;

        private Map<String, String> paramMap = new HashMap<String, String>(20);

        private List<String> ignoreSignParams = new ArrayList<String>();

        private DefaultHttpClientRequest(RopHttpClient ropClient) {
            this.ropClient = ropClient;
            paramMap.put(SystemParameterNames.getAppKey(), appKey);
            paramMap.put(SystemParameterNames.getFormat(), messageFormat.name());
            paramMap.put(SystemParameterNames.getLocale(), locale.toString());
            if (sessionId != null) {
                paramMap.put(SystemParameterNames.getSessionId(), sessionId);
            }
        }


        public ClientHttpRequest addParam(String paramName, Object paramValue) {
            addParam(paramName, paramValue, false);
            return this;
        }


        public ClientHttpRequest addParam(String paramName, Object paramValue, boolean needSign) {
            if (paramName != null && paramName.length() > 0) {
                throw new RuntimeException("参数名不能为空!");
            }
            if (null == paramValue) {
                throw new RuntimeException("参数值不能为null");
            }

            //将参数添加到参数列表中
            String valueAsStr = paramValue.toString();
            paramMap.put(paramName, valueAsStr);

            IgnoreSign typeIgnore = RopUtils.findAnnotation(paramValue.getClass(), IgnoreSign.class);
            if (needSign || typeIgnore != null) {
                ignoreSignParams.add(paramName);
            }
            return this;
        }


        public ClientHttpRequest clearParam() {
            paramMap.clear();
            return this;
        }

        /*
        * post实现
        * */
        private <T> CommonResponse post(Class<T> ropResponseClass, Map<String, String> requestParams) {
            String responseContent = HttpUtils.post(serverUrl, requestParams);
            return toCompositeResponse(responseContent, ropResponseClass);
        }


        public <T> CommonResponse execute(BaseRopRequest ropRequest, Class<T> ropResponseClass) {
            String methodName = ropRequest.getApiMethodName();
            String version = "1.0";
            Map<String, String> requestParams = getRequestForm(ropRequest, methodName, version); //;addOtherParamMap(methodName, version);
            return post(ropResponseClass, requestParams);
        }


        public <T> CommonResponse execute(BaseRopRequest ropRequest, Class<T> ropResponseClass, String version) {
            Map<String, String> requestParams = getRequestForm(ropRequest, ropRequest.getApiMethodName(), version);
            return post(ropResponseClass, requestParams);
        }


        public <T> CommonResponse execute(Class<T> ropResponseClass, String methodName, String version) {
            Map<String, String> requestParams = addOtherParamMap(methodName, version);
            return post(ropResponseClass, requestParams);
        }

        /*
        *返回连接
        * */
        public String buildGetUrl(BaseRopRequest ropRequest) {
            String methodName = ropRequest.getApiMethodName();
            String version = "1.0";
            Map<String, String> requestParams = getRequestForm(ropRequest, methodName, version);
            return buildGetUrl(requestParams);
        }

        public String buildGetUrl(BaseRopRequest ropRequest, String version) {
            Map<String, String> requestParams = getRequestForm(ropRequest, ropRequest.getApiMethodName(), version);
            return buildGetUrl(requestParams);
        }

        public String buildGetUrl(String methodName, String version) {
            Map<String, String> requestParams = addOtherParamMap(methodName, version);
            return buildGetUrl(requestParams);
        }
       /*
        public <T> CommonResponse get(BaseRopRequest ropRequest, Class<T> ropResponseClass) {
            String methodName = ropRequest.getApiMethodName();
            String version = "1.0";
            Map<String, String> requestParams = getRequestForm(ropRequest, methodName, version);
            return get(ropResponseClass, requestParams);
        }


        public <T> CommonResponse get(BaseRopRequest ropRequest, Class<T> ropResponseClass, String version) {
            Map<String, String> requestParams = getRequestForm(ropRequest, ropRequest.getApiMethodName(), version);
            return get(ropResponseClass, requestParams);
        }

        private <T> CommonResponse get(Class<T> ropResponseClass, Map<String, String> requestParams) {
            String responseContent = HttpUtils.get(buildGetUrl(requestParams));
            return toCompositeResponse(responseContent, ropResponseClass);
        }



        public <T> CommonResponse get(Class<T> ropResponseClass, String methodName, String version) {
            Map<String, String> requestParams = addOtherParamMap(methodName, version);
            return get(ropResponseClass, requestParams);
        }*/

        private <T> CommonResponse toCompositeResponse(String content, Class<T> ropResponseClass) {
            boolean successful = isSuccessful(content);
            DefaultCommonResponse<T> ropResponse = new DefaultCommonResponse<T>(successful);
            if (MessageFormat.json == messageFormat) {
                if (successful) {
                    T response = JSONObject.parseObject(content, ropResponseClass);
                    ropResponse.setSuccessRopResponse(response);
                } else {
                    ErrorResponse errorResponse = JSONObject.parseObject(content, ErrorResponse.class);
                    ropResponse.setErrorResponse(errorResponse);
                }
            }
            return ropResponse;
        }

        private boolean isSuccessful(String content) {
            return !(content.contains(Constants.ERROR_TOKEN));
        }

        private Map<String, String> addOtherParamMap(String methodName, String version) {
            paramMap.put(SystemParameterNames.getMethod(), methodName);
            paramMap.put(SystemParameterNames.getVersion(), version);
            String signValue = RopUtils.sign(paramMap, ignoreSignParams, appSecret);
            paramMap.put(SystemParameterNames.getSign(), signValue);
            return paramMap;
        }

        private String buildGetUrl(Map<String, String> form) {
            StringBuilder requestUrl = new StringBuilder();
            requestUrl.append(serverUrl);
            requestUrl.append("?");
            String joinChar = "";
            for (Map.Entry<String, String> entry : form.entrySet()) {
                requestUrl.append(joinChar);
                requestUrl.append(entry.getKey());
                requestUrl.append("=");
                requestUrl.append(entry.getValue());
                joinChar = "&";
            }
            return requestUrl.toString();
        }

        private Map<String, String> getRequestForm(RopRequest ropRequest, String methodName, String version) {

            Map<String, String> form = new LinkedHashMap<String, String>(16);

            //系统级参数
            form.put(SystemParameterNames.getAppKey(), appKey);
            form.put(SystemParameterNames.getMethod(), methodName);
            form.put(SystemParameterNames.getVersion(), version);
            form.put(SystemParameterNames.getFormat(), messageFormat.name());
            form.put(SystemParameterNames.getLocale(), locale.toString());
            if (sessionId != null) {
                form.put(SystemParameterNames.getSessionId(), sessionId);
            }

            //业务级参数
            form.putAll(getParamFields(ropRequest, messageFormat));

            //对请求进行签名
            String signValue = sign(ropRequest.getClass(), appSecret, form);
            form.put("sign", signValue);
            return form;
        }


        /*
        * 对请求参数进行签名
        * @param ropRequestClass
        * @param appSecret
        * @param form
        * @return
        * */
        private String sign(Class<?> ropRequestClass, String appSecret, Map<String, String> form) {
            List<String> ignoreFieldNames = requestIgnoreSignFieldNames.get(ropRequestClass);
            return RopUtils.sign(form, ignoreFieldNames, appSecret);
        }

        /*
        * 获取ropRequest对应的参数名列表
        * */
        private Map<String, String> getParamFields(RopRequest ropRequest, MessageFormat mf) {
            if (!requestAllFields.containsKey(ropRequest.getClass())) {
                parseRopRequestClass(ropRequest);
            }
            return toParamValueMap(ropRequest, mf);
        }

        /*
        * 获取ropRequest对象的对应的参数列表
        * */
        private Map<String, String> toParamValueMap(RopRequest ropRequest, MessageFormat mf) {
            return JSONObject.parseObject(JSONObject.toJSONString(ropRequest), Map.class);
        }
    }


    private void parseRopRequestClass(RopRequest ropRequest) {
        final ArrayList<Field> allFields = new ArrayList<Field>();
        final List<String> ignoreSignFieldNames = RopUtils.getIgnoreSignFieldNames(ropRequest.getClass());
        RopUtils.doWithFields(ropRequest.getClass(), new RopUtils.FieldCallback() {

            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                RopUtils.makeAccessible(field);
                if (!isTemporaryField(field)) {
                    allFields.add(field);
                }
            }

            private boolean isTemporaryField(Field field) {
                Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                if (declaredAnnotations != null) {
                    for (Annotation declaredAnnotation : declaredAnnotations) {
                        Temporary varTemporary = field.getAnnotation(Temporary.class);
                        if (varTemporary != null) {
                            return true;
                        }
                    }
                }
                return false;
            }
        });

        requestAllFields.put(ropRequest.getClass(), allFields);
        requestIgnoreSignFieldNames.put(ropRequest.getClass(), ignoreSignFieldNames);
    }

    /**
     * 获取ropRequest对应的参数名列表
     *
     * @param ropRequest
     * @param mf
     * @return
     */
    private Map<String, String> getParamFields(RopRequest ropRequest, MessageFormat mf) {
        if (!requestAllFields.containsKey(ropRequest.getClass())) {
            parseRopRequestClass(ropRequest);
        }
        return toParamValueMap(ropRequest, mf);
    }

    /**
     * 获取ropRequest对象的对应的参数列表
     *
     * @param ropRequest
     * @param mf
     * @return
     */
    private Map<String, String> toParamValueMap(RopRequest ropRequest, MessageFormat mf) {

        return JSONObject.parseObject(JSONObject.toJSONString(ropRequest), Map.class);
    }
}
