package esercizi;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

public interface StreamListToMap {
	
	//1 Use list loop old style
	Map<String, Book> listToMapOldStyle(List<Book> list) ;
	
	//2 Use Lambda notation
	Map<String, Book> listToMapWithLambda(List<Book> list) ;
	
	//3 Use method reference operator instead of lambda
	Map<String, Book> listToMapWithReference(List<Book> list) ;
	
	//4 Duplicates not managed
	Map<String, Book> listToMapWithFunctionIdentity(List<Book> list) ;

	//5 Manage duplicates with a List as value
	Map<String, List<Book>> listToMapWithNoDuplicatesList(List<Book> list) ;
	
	//6 Manage duplicates without a List as value
	Map<String, Book> listToMapWithNoDuplicates(List<Book> list) ;
	
	//7 Returns Books with isbn > of that supplied
	Map<String, List<Book>> listToMapIsbnGreaterThen(List<Book> books, String isbn) ;
	
	//7.1 Returns Books with price > of that supplied
	// +those with price <=
	Map<Boolean, List<Book>> listToMapPriceGreaterThen(List<Book> books, int price) ;
	
	//8 Returns books names in one string [book1, book22,..]
	String bookNamesJoined(List<Book> books) ;
	
	//9 Returns the average price of all books
	double averageBookPrize(List<Book> books) ;
	
	//10 Returns the sum of the price of input books
	int totalCost(List<Book> books) ;
	
	//11 Returns books statistics
	IntSummaryStatistics booksStatistics(List<Book> books) ;
	
	//12 returns all authors
	String[] booksAuthors(List<Book> books) ;
	
	//13 returns all authors of a specific nation
	String[] booksAuthors(List<Book> books, String nation) ;
	
}
