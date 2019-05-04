package learn.test.trainapi;

public class Train {
	private String trainNumber;
	
	private String trainStartStation;
	
	private String trainEndStation;
	
	private String fromStation;
	
	private String fromStationDeparture;
	
	private String toStation;
	
	private String toStationArrival;

	@Override
	public String toString() {
		return trainNumber + " " + trainStartStation + " " + trainEndStation + "\n" +fromStation + " " +fromStationDeparture + "\n" +toStation + " " +toStationArrival;
	}
	
	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTrainStartStation() {
		return trainStartStation;
	}

	public void setTrainStartStation(String trainStartStation) {
		this.trainStartStation = trainStartStation;
	}

	public String getTrainEndStation() {
		return trainEndStation;
	}

	public void setTrainEndStation(String trainEndStation) {
		this.trainEndStation = trainEndStation;
	}

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public String getFromStationDeparture() {
		return fromStationDeparture;
	}

	public void setFromStationDeparture(String fromStationDeparture) {
		this.fromStationDeparture = fromStationDeparture;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	public String getToStationArrival() {
		return toStationArrival;
	}

	public void setToStationArrival(String toStationArrival) {
		this.toStationArrival = toStationArrival;
	}
	
}
