package com.api.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("DeveloperApi")
public class DeveloperApi implements Serializable {

	private long id;
	private long developerId;
	private long apiId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(long developerId) {
		this.developerId = developerId;
	}

	public long getApiId() {
		return apiId;
	}

	public void setApiId(long apiId) {
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
