package tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tool {
  
  public static String comma(int num) {
    return  String.format("%,d", num);
  }

/*
  public static boolean isImage(String fileName) {
    if (fileName.endsWith("jpg") || fileName.endsWith("gif") || fileName.endsWith("png")) {
      return true;
    }
    
    return false;
  }
*/

/*  
  public static String getStringByLength(String title, int pre) {
    if (title.length() > pre) {
      return title.substring(0, pre) + "...";
    } else {
      return title;
    }
  }
*/
  
/*
  public static String checkNull(String msg) {
    if (msg == null) {
      return "";
    }
    return msg;
  }
*/  
  
  
  /**
   * 이미지인지 검사
   * 
   * @param file 파일명
   * @return
   */
  public static boolean isImage(String file) {
    boolean sw = false;
    if (file != null) {
      file = file.toLowerCase();
      if (file.endsWith("jpg") || file.endsWith(".jpeg") || file.endsWith(".png") || file.endsWith("gif")) {
        sw = true;
      }
    }
    return sw;
  }

  /**
   * 파일 업로드 가능 파일 체크
   * 
   * @param file
   * @return
   */
  public static boolean checkUploadFile(String file) {
    file = file.toLowerCase();
    String[] names = { "jpg", "jpeg", "png", "gif", "txt", "csv", "hwp", "xls", "xlsx", "ppt", "pptx", "zip", "tar",
        "gz", "ipynb", "doc", "mp3", "mp4" };

    for (String name : names) {
      if (file.endsWith(name)) {
        return true;
      }
    }

    return false;
  }
  
  
  
  /**
   * 문자열의 길이가 length 보다 크면 "..."을 표시하는 메소드
   * 
   * @param str    원본 문자열
   * @param length 출력할 문자열 길이
   * @return 특정 길이의 문자열
   */
  public static String getStringByLength(String str, int length) {
    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0, length) + "..."; // 범위: 0 ~ length - 1
      }
    } else {
      str = "";
    }

    return str;
  }
  
  /**
   * byte 수를 전달받아 자료의 단위를 적용합니다. Byte -> KB -> MB -> GB -> TB -> PT -> EX -> ZB ->
   * YB
   * 
   * @param size
   * @return 1000 → 1 KB
   */
  public static String unit(long size) {
    String str = "";

    if (size < 1024) { // 1 KB 이하, 1024 byte 미만이면
      str = size + " Byte";
    } else if (size < 1024 * 1024) { // 1 MB 이하, 1048576 byte 미만이면 KB
      str = (int) (Math.ceil(size / 1024.0)) + " KB";
    } else if (size < 1024 * 1024 * 1024) { // 1 GB 이하, 1073741824 byte 미만
      str = (int) (Math.ceil(size / 1024.0 / 1024.0)) + " MB";
    } else if (size < 1024L * 1024 * 1024 * 1024) { // 1 TB 이하, 큰 정수 표현을 위해 int -> long형식으로 변환
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0)) + " GB";
    } else if (size < 1024L * 1024 * 1024 * 1024 * 1024) { // 1 PT 이하
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " TB"; // 테라
    } else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024) { // 1 EX 이하
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " PT"; // 페타
    } else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024 * 1024) { // 1 ZB 이하
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " EX"; // 엑사
    } else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024) { // 1 YB 이하
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " ZB"; // Google이
                                                                                                            // 사용 중인 단위,
                                                                                                            // 제타
    } else { // 1 YB 이상
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " YB"; // 요타
    }

    return str;
  }
  
  /**
   * 문자열이 null이면 공백으로 리턴되고, null이 아니면 원본 문자열이
   * 리턴됩니다.
   * @param str
   * @return
   */
  public static String checkNull(String str){
    if (str == null){
      return "";  // null -> "null" 방지
    }else if (str.equals("null")){
      return "";
    }else{
      return str;
    }
  }
  
  /**
   * 고유한 파일명 생성 2019-12-06_123020_100
   * 
   * @return
   */
  public static String getRandomDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hhmmss");
    String date = sdf.format(new Date());
    date = date + "_" + (int) (Math.random() * 1000); // 0 ~ 999

    return date;
  }

  /**
   * 고유한 파일명 생성, MP4_20210723-154253_6995 형식의 날짜를 리턴합니다.
   * 
   * @return MP4_20210723-154253_6995 형식의 문자열 리턴
   */
  public static String getRandomDateHeader(String header) {
    SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd-HHmmss");

    String date = sd.format(new Date());

    Random rnd = new Random();
    int rnd_val = rnd.nextInt(100000); // 0 ~ 99999
    date = header + "_" + date + "_" + rnd_val;

    return date;
  }
  
  
}
