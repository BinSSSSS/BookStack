package cn.tblack.utils;

public class FactoryConnection {

	public static TransactionConnection getTransactionConnection() {
		return new TransactionConnection();
	}
}
