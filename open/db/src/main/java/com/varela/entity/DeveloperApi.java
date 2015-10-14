package com.varela.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("DeveloperApi")
public class DeveloperApi implements Serializable {

	private String id;
	private String developerId;
	private String apiId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(String developerId) {
		this.developerId = developerId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	@Override
	public String toString() {
		return "DeveloperApi{" +
				"id='" + id + '\'' +
				", developerId='" + developerId + '\'' +
				", apiId='" + apiId + '\'' +
				'}';
	}
}
