package tw.elliot.jds.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/profile/*")
public class ProfileCtrl {

	@Value("${app.profile}")
	private String profile;

	@GetMapping("checkProfile")
	public String checkProfile(HttpSession session) {
		return profile;
	}
}
