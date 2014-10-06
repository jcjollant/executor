package executor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Exchange extends Thread {
	public Exchange( String name, int port, ExecutionReport er, NewOrder no) {
		this.name = name;
		this.port = port;
		this.executionReport = er;
		this.newOrder = no;
	}
	
	@Override
	public void run() {
		
		ServerSocket socket = null;
		try {
			socket = new ServerSocket( this.port);
		} catch( IOException ioe) {
			System.out.println( "Could create " + this.name + " server socket because " + ioe.getMessage());
			return;
		}
		System.out.println( "Listenning for " + this.name + " incomming connections on " + socket.getLocalPort());
		
		// this loop if for all connections
		while( true) {
			Socket clientSocket = null;
			try {
				clientSocket = socket.accept();
			} catch( IOException ioe) {
				System.out.println( "Could not accept connection because " + ioe.getMessage());
				return;
			}
			
			// We have a hit
			System.out.println( this.name + " Incomming Connection from " + clientSocket);
			OutputStream os = null;
			InputStream is = null;
			try {
				os = clientSocket.getOutputStream();
				is = clientSocket.getInputStream();
			} catch( IOException ioe) {
				System.out.println( this.name + "Could get outputstream because " + ioe.getMessage());
				continue;
			}
			
			
			while( true) { // this is the incomming orders loop
				try {
					byte inputBuffer[] = new byte[1024];
					int bufferSize = 0;
					while( ( bufferSize += is.read( inputBuffer)) != -1 ) {
						if( bufferSize >= this.newOrder.getSize()) {
							this.newOrder.decode( inputBuffer);
							System.arraycopy( inputBuffer, this.newOrder.getSize(), inputBuffer, 0, bufferSize - this.newOrder.getSize());
							try {
								this.executionReport.populate( this.newOrder);
								this.executionReport.encode();
							} catch( Exception e) {
								System.out.println( "Could not encode response because " + e.getMessage());
							}
							
						}
					}
				} catch( Exception e) {
					System.out.println( "Could not process order because " + e.getMessage() );
					break;
				}
				// just send the response
				try {
					byte bb[] = this.executionReport.getBytes();
					os.write( bb);
					System.out.println("Sent " + bb.length + " bytes to destination");
					System.out.println( Display.bytesToHex(bb));
				} catch(IOException ioe) {
					System.out.println("Could not send response because " + ioe.getMessage());
				}
			}
			
			
		}
	}

	private String name;
	private int port;
	private ExecutionReport executionReport;
	private NewOrder newOrder;
	
}