package executor;

import java.util.Random;

public class Display {
final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
public static String bytesToHex(byte[] bytes, int length) {
	StringBuffer sb = new StringBuffer();
	int count = 0;
	int last16[] = new int[16];
	
//    char[] hexChars = new char[bytes.length * 2];
    for ( int j = 0; j < length; j++ ) {
    	
        int v = bytes[j] & 0xFF;
        last16[count] = v;
 //       hexChars[j * 2] = hexArray[v >>> 4];
 //       hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        sb.append( ' ');
        sb.append( hexArray[v >>> 4]);
        sb.append( hexArray[v & 0x0F]);
        if( ++count == 8) {
        	sb.append( " -");
        } else if( count == 16) {
        	sb.append( " | ");
        	for( int index = 0; index < 16; index++) {
        		if( index == 8) sb.append( " - ");
        		if( v < 32) {
        			sb.append('.');
        		} else {
        			sb.append( (char)last16[index]);
        		}
        	}
        	sb.append( "\n");
        	count = 0;
        	
        }
    }
    return sb.toString();
}

public static String randomString( int length) {
	char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	StringBuilder sb = new StringBuilder();
	Random random = new Random();
	for (int i = 0; i < length; i++) {
	    char c = chars[random.nextInt(chars.length)];
	    sb.append(c);
	}
	return sb.toString();	
}

}
