package com.yzq.demo1.sync;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yzq.demo1.aysn.AsyncTask;
import com.yzq.demo1.aysn.SyncTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SyncTest {

	@Autowired
	private SyncTask syncTask;

	@Autowired
	private AsyncTask asyncTask;

	// 同步
	@Test
	public void syncTask() throws Exception {
		syncTask.doTaskOne();
		syncTask.doTaskTwo();
		syncTask.doTaskThree();
	}

	// 异步
	@Test
	public void asyncTask() throws Exception {
		asyncTask.doTaskOne();
		asyncTask.doTaskTwo();
		asyncTask.doTaskThree();
	}

}
