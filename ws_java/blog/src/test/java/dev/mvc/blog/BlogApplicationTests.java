package dev.mvc.blog;

import dev.mvc.bloguser.UserDAOInter;
import dev.mvc.bloguser.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private UserDAOInter userDAO;

	@Test
	void contextLoads() {
	}

	@Test
	public void createTest() {
		UserVO userVO = new UserVO();

		userVO.setUserName("김광환");
		userVO.setUserEmail("ghlim100@naver.com");
		userVO.setUserPassword("1234");
		userVO.setUserGrade(2);
		userVO.setRdate("2025-03-18");

		int cnt = this.userDAO.create(userVO);
		System.out.println("cnt : " + cnt);
	}

}
