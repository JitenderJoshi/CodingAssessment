package learn.test.trainapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TrainService {

	@Autowired
	JdbcTemplate jdbcTemplate; 


	public List<Train> getAllTrains(String stnfrom, String stnto, String date) {
		List<String> trainNumberList = getTrainsBetweenStations(stnfrom, stnto);

		String dayforDate = getDayforDate(date);

		List<Train> trainList = getTrainListonthisDate(trainNumberList, stnfrom, stnto, dayforDate);

		return trainList;
	}

	private List<String> getTrainsBetweenStations(String stnfrom, String stnto) {
		String query1 = "SELECT A.trainnumber FROM trainroute A, trainroute B "
				+ "WHERE A.Arrivalstation = ? AND B.Arrivalstation =?  AND A.trainnumber = B.trainnumber";

		List<Map<String, Object>> trainnumberrows = jdbcTemplate.queryForList(query1, stnfrom, stnto);

		List<String> trainNumberList = new ArrayList<>();

		for(Map<String, Object> row : trainnumberrows) {
			trainNumberList.add((String) row.get("TRAINNUMBER"));
		}

		return trainNumberList;
	}

	private String getDayforDate(String dateStr) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyy").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (new SimpleDateFormat("EE").format(date)).toUpperCase().substring(0, 2);
	}

	private List<Train> getTrainListonthisDate(List<String> trainNumberList, String stnfrom, String stnto, String dayforDate) {
		String query2 = "SELECT A.* FROM train A "
				+ "WHERE A.trainnumber = ? AND A.RunningDays LIKE ?";

		List<Train> trainList = new ArrayList<>();

		for(String trainNumber : trainNumberList) {
			List<Map<String, Object>> traindetailsrows = jdbcTemplate.queryForList(query2, trainNumber, "%"+dayforDate+"%");
			for(Map<String, Object> row : traindetailsrows) {
				Train train = new Train();
				train.setTrainNumber((String)row.get("TRAINNUMBER"));
				train.setTrainStartStation((String)row.get("STARTSTATION"));
				train.setTrainEndStation((String)row.get("ENDSTATION"));
				train.setFromStation(stnfrom);
				train.setToStation(stnto);

				train = updateTimings(train, trainNumber, stnfrom, stnto);

				trainList.add(train);
			}

		}

		return trainList; 
	}

	private Train updateTimings(Train train, String trainNumber, String stnfrom, String stnto) {
		String query3 = "SELECT to_char(A.depaturetime,'HH12:MI:SS AM') FROM trainroute A "
				+ "WHERE A.trainnumber = ? AND A.Arrivalstation = ?";

		String query4 = "SELECT to_char(A.depaturetime,'HH12:MI:SS AM') FROM trainroute A "
				+ "WHERE A.trainnumber = ? AND A.Arrivalstation = ?";

		String depTime = jdbcTemplate.queryForObject(query3, new Object[] { trainNumber, stnfrom }, String.class);
		String arrTime = jdbcTemplate.queryForObject(query4, new Object[] { trainNumber, stnto }, String.class);


		train.setFromStationDeparture(depTime);
		train.setToStationArrival(arrTime);

		return train;
	}

}
