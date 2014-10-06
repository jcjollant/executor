package executor;

public class NYSENewOrder extends NewOrder {

	public NYSENewOrder() {
		super( Endianness.BIG, 84);
	}
	
	@Override
	public void decode(byte[] bb) throws Exception {
		
		this.quantity = decodeInt32( 8);
		this.price = decodeInt32(16);
		this.clOrdID = decodeString( 64, 17);
		this.side = decodeInt8( 37);
		this.symbol = decodeString( 25, 11);
	}

	public String symbol;
	public int price;
}
