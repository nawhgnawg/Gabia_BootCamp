package dev.mvc.resort_v1sbm3c;

import dev.mvc.cate.CateDAOInter;
import dev.mvc.cate.CateVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResortV1sbm3cApplicationTests {

	// CateDAOInter interface 객체를 만들 수 없으나,
	// Spring 이 CateDAOInter interface를 자동으로 구현하여 객체를 생성하여 cateDAO 변수에 할당함
	@Autowired
	private CateDAOInter cateDAO;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate() {
		CateVO cateVO = new CateVO();
		cateVO.setCateno(1);
		cateVO.setGrp("1");
		cateVO.setName("카테1");
		cateVO.setCnt(1);
		cateVO.setSeqno(1);
		cateVO.setVisible("Y");
		cateVO.setRdate("2025-03-18 15:00:00");

		// 일반적으로 interface는 객체를 만들 수 없지만, Spirng이 객체를 생성하여 cateDAO 변수에 할당
		int cnt = this.cateDAO.create(cateVO);
		System.out.printf("-> cnt: " + cnt);
	}
}