package com.roadgovern.vo;

public class RoadVO {
	
	private int roadId;
	private String roadType;
	private String roadName;
	private float roadWidth;
	private float roadLength;
	private String medianAvailable;
	private String pedestrianAvailable;
	private String streetLightAvailable;
	
	
	public int getRoadId() {
		return roadId;
	}
	public void setRoadId(int roadId) {
		this.roadId = roadId;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public float getRoadWidth() {
		return roadWidth;
	}
	public void setRoadWidth(float roadWidth) {
		this.roadWidth = roadWidth;
	}
	public float getRoadLength() {
		return roadLength;
	}
	public void setRoadLength(float roadLength) {
		this.roadLength = roadLength;
	}
	public String getMedianAvailable() {
		return medianAvailable;
	}
	public void setMedianAvailable(String medianAvailable) {
		this.medianAvailable = medianAvailable;
	}
	public String getPedestrianAvailable() {
		return pedestrianAvailable;
	}
	public void setPedestrianAvailable(String pedestrianAvailable) {
		this.pedestrianAvailable = pedestrianAvailable;
	}
	public String getStreetLightAvailable() {
		return streetLightAvailable;
	}
	public void setStreetLightAvailable(String streetLightAvailable) {
		this.streetLightAvailable = streetLightAvailable;
	}
	
	
}
