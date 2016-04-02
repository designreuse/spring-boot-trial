package kamegu.trial.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Test2Controller {

	@RequestMapping("/test2")
	public String getHtml(@RequestParam(defaultValue = "world") String name, Model model) {
		model.addAttribute("name", name);
		return "test";
	}
}
