package learn.test.trainapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainController {

	@Autowired
	private TrainService trainService;

	@RequestMapping("/trains")
	public List<Train> getAllTrains(@RequestParam(name = "stnfrom") String stnfrom,
			@RequestParam(name = "stnto") String stnto,
			@RequestParam(name = "date") String date) {
		return trainService.getAllTrains(stnfrom, stnto, date);
	}

}
