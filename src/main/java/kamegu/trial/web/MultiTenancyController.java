package kamegu.trial.web;

import kamegu.trial.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MultiTenancyController {

	@Autowired
	CityService service;

	@RequestMapping("/mt/{tenantid}")
	public String getHtml(@RequestParam(defaultValue = "world") String name, Model model) {
		model.addAttribute("name", name);
		int count = service.cities().size();
		model.addAttribute("count", count);
		return "test";
	}

	@RequestMapping("/mt/{tenantid}/add")
	public String addCity(@RequestParam String name, @PathVariable String tenantid) {
		service.add(name);
		return "redirect:/mt/" + tenantid;
	}
}
