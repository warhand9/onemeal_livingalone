
public class OnemealLivingalone {
	
	private static void readRecordsFromFiles()
	{}
	
	private static void writeRecordsToFiles()
	{}
	
	public static void main(String[] args)
	{
		readRecordsFromFiles();
		
		MainMenuManagerUI mainMenuManager = new MainMenuManagerUI();
		mainMenuManager.interact();
		
		writeRecordsToFiles();
	}

}
