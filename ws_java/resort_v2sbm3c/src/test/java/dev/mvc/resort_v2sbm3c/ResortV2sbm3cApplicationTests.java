package dev.mvc.resort_v2sbm3c;

import dev.mvc.cate.CateDAOInter;
import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ResortV2sbm3cApplicationTests {

	// CateDAOInter interface 객체를 만들 수 없으나,
	// Spring 이 CateDAOInter interface를 자동으로 구현하여 객체를 생성하여 cateDAO 변수에 할당함
	@Autowired
	private CateDAOInter cateDAO;

	@Autowired
	@Qualifier("dev.mvc.cate.CateProc")
	private CateProcInter cateProc;

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
		int cnt = cateDAO.create(cateVO);
		System.out.printf("-> cnt: " + cnt);
		assertThat(cnt).isEqualTo(1);
	}

	@Test
	void procCreateTest() {
		CateVO cateVO = new CateVO();
		cateVO.setCateno(1);
		cateVO.setGrp("2");
		cateVO.setName("카테2");
		cateVO.setCnt(1);
		cateVO.setSeqno(1);
		cateVO.setVisible("Y");
		cateVO.setRdate("2025-03-19 10:00:00");

		int cnt = cateProc.create(cateVO);

		System.out.printf("-> cnt: " + cnt);
		assertThat(cnt).isEqualTo(1);
	}

	@Test
	void readTest() {
		// given
		CateVO cateVO = new CateVO();
		cateVO.setCateno(1);
		cateVO.setGrp("2");
		cateVO.setName("카테2");
		cateVO.setCnt(1);
		cateVO.setSeqno(1);
		cateVO.setVisible("Y");
		cateVO.setRdate("2025-03-19 10:00:00");
		cateProc.create(cateVO);

		// when
		CateVO read = cateProc.read(1);

		// then
		System.out.println(cateVO);
		System.out.println(read);	// 진짜 DB에서 가져옴
		assertThat(read.getCateno()).isEqualTo(1);
		assertThat(read.getGrp()).isEqualTo("드라마");
	}
}
