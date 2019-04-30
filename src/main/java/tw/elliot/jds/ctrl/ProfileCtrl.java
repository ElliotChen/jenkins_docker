package tw.elliot.jds.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/profile/*")
@Slf4j
public class ProfileCtrl {

	@Value("${app.profile}")
	private String profile;

	@GetMapping("checkProfile")
	public String checkProfile(HttpSession session) {
		log.warn("Get Session [{}]", session.getId());
		return profile;
	}
}
