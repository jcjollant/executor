package executor;

public abstract class NewOrder extends Message {
	public NewOrder( Endianness e, int size) {
		super( e, size);
	}
	
	public abstract void decode( byte bb[]) throws Exception;
	
	public String clOrdID;
	public byte side;
	public int quantity;
}
