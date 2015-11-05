package com.varela.mq.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class FastJsonMessageConverter extends AbstractJsonMessageConverter {

	private String defaultCharset = "utf-8";

	public String getDefaultCharset() {
		return defaultCharset;
	}

	public void setDefaultCharset(String defaultCharset) {
		this.defaultCharset = defaultCharset;
	}

	@Override
	protected Message createMessage(Object objectToConvert,
			MessageProperties messageProperties) { 
		byte[] bytes = null;
		try {
			String jsonString = JSONObject.toJSONString(objectToConvert);
			bytes = jsonString.getBytes(getDefaultCharset());
		} catch (IOException e) {
			throw new MessageConversionException(
					"Failed to convert Message content", e);
		}
		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
		messageProperties.setContentEncoding(getDefaultCharset());
		if (bytes != null) {
			messageProperties.setContentLength(bytes.length);
		}
		// 不要使用,会丢掉对象属性,缺少实现
		return new Message(bytes, messageProperties);
	}

	@Override
	public Object fromMessage(Message message)
			throws MessageConversionException {
		Object content = null;
		try {
			MessageProperties properties = message.getMessageProperties();
			String encoding = properties.getContentEncoding();
			if (encoding == null) {
				encoding = this.defaultCharset;
			}
			content = new String(message.getBody(), encoding);
			content = JSONObject.parse((String) content);
		} catch (UnsupportedEncodingException e) {
			throw new MessageConversionException(
					"failed to convert text-based Message content", e);
		}
		return content;
	}

}
