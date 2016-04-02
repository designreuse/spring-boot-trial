package kamegu.trial.web;

import kamegu.trial.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Test2Controller {

	@Autowired
	CityService service;

	@RequestMapping("/test2")
	public String getHtml(@RequestParam(defaultValue = "world") String name, Model model) {
		model.addAttribute("name", name);
		int count = service.cities().size();
		model.addAttribute("count", count);
		return "test";
	}

	@RequestMapping("/test2/add")
	public String addCity(@RequestParam String name, Model model) {
		service.add(name);
		return "redirect:/test2";
	}
}
