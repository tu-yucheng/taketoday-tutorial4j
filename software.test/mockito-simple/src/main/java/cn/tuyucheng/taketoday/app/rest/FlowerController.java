package cn.tuyucheng.taketoday.app.rest;

import cn.tuyucheng.taketoday.app.api.Flower;
import cn.tuyucheng.taketoday.domain.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flowers")
public class FlowerController {

	@Autowired
	private FlowerService flowerService;

	@PostMapping("/isAFlower")
	public String isAFlower(@RequestBody String flower) {
		return flowerService.analyze(flower);
	}

	@PostMapping("/isABigFlower")
	public Boolean isABigFlower(@RequestBody Flower flower) {
		return flowerService.isABigFlower(flower.getName(), flower.getPetals());
	}
}
