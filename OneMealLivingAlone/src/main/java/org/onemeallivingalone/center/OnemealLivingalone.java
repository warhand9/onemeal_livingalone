package org.onemeallivingalone.center;

import org.onemeallivingalone.center.ui.MainMenuManagerUI;
import org.onemeallivingalone.database.FileManager;
import org.onemeallivingalone.database.RecordReader;
import org.onemeallivingalone.database.RecordWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class.
 *
 * 메인 클래스입니다.
 * 
 * 로깅을 위해 디버그 환경에서는 아래 VM 인수를 씁니다.<br>
 * -Dlog4j.configurationFile=log4j2-debug.xml
 * <br>릴리스 환경에서는 아래 VM 인수를 씁니다.<br>
 * -Dlog4j.configurationFile=log4j2.xml
 */
public class OnemealLivingalone {

	private static transient final Logger logger = LoggerFactory.getLogger("omla-center");

	public static void main(String[] args) {
		logger.info("OneMealLivingAlone just started");

		// Import files
		{
			RecordReader recordReader = new RecordReader(new FileManager());
			recordReader.readRecordsToLists();
		}

		MainMenuManagerUI mainMenuManager = new MainMenuManagerUI();
		mainMenuManager.interact();

		// Export files
		{
			RecordWriter recordWriter = new RecordWriter(new FileManager());
			recordWriter.writeRecordsFromLists();
		}

		logger.info("OneMealLivingAlone is closing now");
	}

}
