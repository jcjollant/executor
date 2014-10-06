package executor;

public abstract class ExecutionReport extends Message {
	public ExecutionReport( Endianness e, int size) {
		super( e, size);
		this.seqNum = 0;
	}
	
	void encode() {
		reset();
		this.seqNum++;
	}
	
	public void populate( NewOrder no) {
		this.side = no.side;
		this.clOrdID = no.clOrdID;
		this.quantity = no.quantity;
	}
	
	protected int seqNum;
	
	protected byte side;
	protected String clOrdID;
	protected int quantity;
}
