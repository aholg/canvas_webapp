package aholg.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringController {
	Model model;
	aholg.controller.Controller controller = new aholg.controller.Controller();;

	@RequestMapping("/")
	public String draw(Model model) {
		this.model = model;
		
		model.addAttribute("output", controller.printCanvas());
		return "draw";

	}

	@RequestMapping(method = RequestMethod.POST, path = "/draw")
	public ModelAndView newCommand(@RequestParam(value = "input", required = false, defaultValue = "") String input) {

		try {
			controller.newCommand(input);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return new ModelAndView("redirect:/");

	}

//	public void notify(String output) {
//		model.addAttribute("output", output);
//	}
}
