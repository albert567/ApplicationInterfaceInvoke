package config;

public final class Res {
	public final static class MENU{
		public final static int ROOT=0;
		public final  static int MENU=1;
		public final  static int CONFIG=2;
		public static final  int APPLICATION=101;
		public final  static int DATABASE=102;
		public final  static int DICT=103;
		public final  static int LOG=105;
		public final  static int RIGHT=104;
		public final static int GET_BY_ID(String id){
			switch (id) {
			case RIGHTID.APPLICATION:
				return APPLICATION;
			case RIGHTID.DATABASE:
				return DATABASE;
			case RIGHTID.DICT:
				return DICT;
			case RIGHTID.LOG:
				return LOG;
			case RIGHTID.RIGHT:
				return RIGHT;
			default:
				return ROOT;
			}
		}
	};
	public final static class ROLE{
		public final static String NONE="ROLE_NONE";
		public final static String APPLICATION="ROLE_APPLICATION";
		public final static String DATABASE="ROLE_DATABASE";
		public final static String DICT="ROLE_DICT";
		public final static String RIGHT="ROLE_RIGHT";
		public final static String LOG="ROLE_LOG";
		public final static String GET_BY_ID(String id){
			switch (id) {
			case RIGHTID.APPLICATION:
				return APPLICATION;
			case RIGHTID.DATABASE:
				return DATABASE;
			case RIGHTID.DICT:
				return DICT;
			case RIGHTID.LOG:
				return LOG;
			case RIGHTID.RIGHT:
				return RIGHT;
			default:
				return NONE;
			}
		}
	};
	public final static class RIGHTID{
		public final static String APPLICATION="BBBFF452-8D79-4114-A7CD-76D4A9B48B20";
		public final static String DATABASE="4719A20D-3136-46D2-89BE-A36FDFEAC9E2";
		public final static String DICT="C5F0E12D-E5E2-4D33-8F39-254EEEAE9D20";
		public final static String RIGHT="F3C0F5B5-52CD-40CE-9957-CCA7D01CDDA6";
		public final static String LOG="AF8DD742-7658-4346-9446-0AB4DDBAFC19";
		public final static String[]ALL=new String[]{APPLICATION,DATABASE,DICT,RIGHT,LOG};
		public final static String[]APPLICATION_AND_DATABASE=new String[]{APPLICATION,DATABASE};
	};
	public final static class LOG{
		public final static String OPERATION_INSERT="新增";
		public final static String OPERATION_UPDATE="修改";
		public final static String OPERATION_DELETE="删除";
		public final static String OPERATION_IMPORT="导入";
		public final static String OPERATION_ROLLBACK="撤销导入";
		public final static String[]OPERATIONS={OPERATION_INSERT,OPERATION_DELETE,OPERATION_UPDATE,OPERATION_IMPORT,OPERATION_ROLLBACK};
	}

}
