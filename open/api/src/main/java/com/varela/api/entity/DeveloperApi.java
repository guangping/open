package com.varela.api.entity;

import java.io.Serializable;


public class DeveloperApi implements Serializable {

	private long id=-1;
	private long developerId=-1;
	private long apiId=-1;

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
