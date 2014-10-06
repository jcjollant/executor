package executor;

public class LSENewOrder extends NewOrder {
	static final int SIZE = 97;
	public LSENewOrder() {
		super( Endianness.LITTLE, SIZE);
	}
	
	@Override	
	public void decode( byte bb[]) throws Exception {

		this.clOrdID = decodeString( 4, 20);
		this.instrumentID = decodeInt32( 46);
		this.side = decodeInt8( 58);
		this.quantity = decodeInt32( 59);
		this.price = decodeInt64( 67);
	}
	
	public int instrumentID;
	public long price;
}
