package esercizi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ConvertListToMapTest {
	
	static ConvertListToMap cltm;
	static List<Book> bookList;
	static List<Book> duplicateBookList;
	static Map<String, Book> testBookMap;
	static Map<String, List<Book>> testDuplicateBookMap;
	static Book book1;
	static Book book2;
	static Book book3;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		cltm = new ConvertListToMap();
		bookList = new ArrayList<Book>();
		
		// Aggiungo 3 libri alla lista di libri base (bookList)
		book1 = new Book();
		book2 = new Book();
		book3 = new Book();
		cltm.setBook(book1, "Autore1", "Questa è la descr 1", "54324", "it", 34);
		cltm.setBook(book2, "Autore2", "Questa è la descr 2", "65475", "en", 65);
		cltm.setBook(book3, "Autore3", "Questa è la descr 3", "13454", "es", 12);
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		
		// Creo una mappa di test uguale alla lista "booklist"
		testBookMap = new HashMap<>();
		for (Book book : bookList) {
			testBookMap.put(book.getIsbn(), book);
		}
		
		// Creo una lista con un doppione (duplicateBookList)
		duplicateBookList = new ArrayList<Book>(bookList);
		duplicateBookList.add(book1);
		
		// Creo una mappa di test uguale alla lista "duplicateBookList" (con un doppione al book1)
		testDuplicateBookMap = new HashMap<>();
		for (Book book : bookList) {
			List<Book> list = new ArrayList<Book>();
			list.add(book);
			
			// Se è il primo libro ne aggiungo un altro (per fare il test del doppione)
			if (book.getIsbn() == "54324") {
				list.add(book);
			}
			testDuplicateBookMap.put(book.getIsbn(), list);
		}
		
	}

	// Metodo 1
	@Test
	void testListToMapOldStyle() {
		/* Confronto la mappa di test con quella restituita dalla funzione "listToMapOldStyle"*/
		assertEquals(testBookMap, cltm.listToMapOldStyle(bookList));
	}
	
	// Metodo 2
	@Test
	void testListToMapWithLambda() {
		/* Confronto la mappa di test con quella restituita dalla funzione "listToMapWithLambda"*/
		assertEquals(testBookMap, cltm.listToMapWithLambda(bookList));
	}
	
	// Metodo 3
	@Test
	void testListToMapWithReference() {
		/* Confronto la mappa di test con quella restituita dalla funzione "listToMapWithReference"*/
		assertEquals(testBookMap, cltm.listToMapWithReference(bookList));
	}
	
	// Metodo 4
	@Test
	void testListToMapWithFunctionIdentity() {
		/* Confronto la mappa di test con quella restituita dalla funzione "listToMapWithFunctionIdentity"*/
		assertEquals(testBookMap, cltm.listToMapWithFunctionIdentity(bookList));
	}
	
	// Metodo 5
	@Test
	void testListToMapWithNoDuplicatesList() {
		/* Confronto la mappa di test con quella restituita dalla funzione "listToMapWithNoDuplicatesList"*/
		assertEquals(testDuplicateBookMap, cltm.listToMapWithNoDuplicatesList(duplicateBookList));
	}
		
	// Metodo 6
	@Test
	void testListToMapWithNoDuplicates() {
		/* Confronto la mappa di test con quella restituita dalla funzione "listToMapWithNoDuplicates"*/
		assertEquals(testBookMap, cltm.listToMapWithNoDuplicates(duplicateBookList));
	}
	
	// Metodo 7
	@Test
	void testListToMapIsbnGreaterThen() {
		Map<String, List<Book>> expectedBookMap = new HashMap<>();
		expectedBookMap.put(book1.getIsbn(), new ArrayList<>(List.of(book1)));
		expectedBookMap.put(book2.getIsbn(), new ArrayList<>(List.of(book2)));
		assertEquals(expectedBookMap, cltm.listToMapIsbnGreaterThen(bookList, "20000"));
		assertEquals(new HashMap<>(), cltm.listToMapIsbnGreaterThen(bookList, "99999"));
	}
	
	// Metodo 8
	@Test
	void testListToMapPriceGreaterThen() {
		Map<Boolean, List<Book>> expectedBookMap = new HashMap<>();
		expectedBookMap.put(true, new ArrayList<>(List.of(book2)));
		expectedBookMap.put(false, new ArrayList<>(List.of(book1, book3)));
		Map<Boolean, List<Book>> expectedBookMapAllFalse = new HashMap<>();
		expectedBookMapAllFalse.put(true, new ArrayList<>());
		expectedBookMapAllFalse.put(false, new ArrayList<>(List.of(book1, book2, book3)));
		assertEquals(expectedBookMap, cltm.listToMapPriceGreaterThen(bookList, 50));
		assertEquals(expectedBookMapAllFalse, cltm.listToMapPriceGreaterThen(bookList, 100));
	}

}
