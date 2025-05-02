package esercizi;

import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertListToMap implements StreamListToMap {

	/* Questo metodo restituisce una Map con gli elementi forniti all'interno
	 * della lista passata come parametro. */
	@Override
	public Map<String, Book> listToMapOldStyle(List<Book> list) {
		
		Map<String, Book> bookMap = new HashMap<>();
		for (Book book : list) {
			bookMap.put(book.getIsbn(), book);
		}
		
		return bookMap;
	}

	/* Questo metodo restituisce una Map come il metodo precedente,
	 * utilizza però le lambda expression. */
	@Override
	public Map<String, Book> listToMapWithLambda(List<Book> list) {
		
		return list.stream()                 // key                  // value
			       .collect(Collectors.toMap(book -> book.getIsbn(), book -> book));
		
		/* Precedentemente avevo salvato in una variabile "Stream<Book> booksMap"
		 * il risultato del mio stream. Facendo però successivamente il return 
		 * di quella variabile, il tipo era "Stream<Book>" e andava in conflitto
		 * con il tipo ritornato dal metodo, ovvero "Map<String, Book>".
		 * Siccome a me serve solo la mappa, e non lo stream, ritorno direttamente 
		 * il risultato. */
		
		/* Come terzo argomento posso anche mettere ad esempio "TreeMap::new", in 
		 * questo modo invece di crearmi un HashMap (quella di default) mi creerà
		 * una TreeMap. */
	
	}

	/* Questo metodo restituisce una Map come il metodo precedente,
	 * utilizza però la method reference nella lambda expression. */
	@Override
	public Map<String, Book> listToMapWithReference(List<Book> list) {
		return list.stream()                 // key         // value
			       .collect(Collectors.toMap(Book::getIsbn, book -> book));
		
		/* Book::getIsbn
		 * Questo mi permette di richiamare il meodo getIsbn del Book corrente.*/
	}

	/* Questo metodo restituisce una Map come il metodo precedente,
	 * utilizza però il metodo identity() della classe Function. */
	@Override
	public Map<String, Book> listToMapWithFunctionIdentity(List<Book> list) {
		return list.stream()                 // key         // value
			       .collect(Collectors.toMap(Book::getIsbn, Function.identity()));
		
		/* Function.identity() è un metodo statico della classe Function che 
		 * restituisce una funzione che ritorna esattamente l'oggetto che riceve.
		 * In pratica, equivale a scrivere: book -> book
		 * cioè una funzione che prende un Book e restituisce lo stesso Book
		 * senza modificarlo.
		 * 
		 * Quindi Function.identity() serve a dire: "Voglio come valore della
		 * mappa lo stesso oggetto della lista, senza trasformarlo." */
	}

	/* Questo metodo restituisce una Map come il metodo precedente,
	 * utilizzando groupingBy che però mi elimina i duplicati in base
	 * al valore che gli passiamo come argomento. */
	@Override
	public Map<String, List<Book>> listToMapWithNoDuplicatesList(List<Book> list) {
		return list.stream()                      // key
			       .collect(Collectors.groupingBy(Book::getIsbn));
		
		/* .groupingBy(Book::getIsbn)
		 * "groupingBy" mi permette di raggruppare tutti gli elementi duplicati all'interno di
		 * una mappa. In pratica nelle parentesi gli passo il valore che deve controllare (in questo
		 * caso quindi controlla se ci sono doppioni in base al ISBN. Il value non abbiamo bisogno
		 * di specificarlo perchè capisce che è l'elemento corrente, e capisce che si tratta di 
		 * un book per via del tipo dichiarato (Book) quando lanciamo la funzione dell'isbn */
	}

	/* Questo metodo restituisce una Map come il metodo precedente,
	 * utilizzando groupingBy, ma in più è presente la gestione dei duplicati. */
	@Override
	public Map<String, Book> listToMapWithNoDuplicates(List<Book> list) {
		return list.stream()                 // key         // value             // Gestione duplicati
			       .collect(Collectors.toMap(Book::getIsbn, Function.identity(), (first, second) -> first));
		
		/* Per gestire i duplicati, utilizzo questa sintassi: (first, second) -> first 
		 * In pratica con questa sintassi sto dicendo questo: first è l'elemento corrente
		 * dell'iterazione, e se trova un elemento uguale (con la stessa chave), lo salva in second. Dopo la freccia
		 * della lambda expression, viene effettuato un controllo con equals. In questo caso, 
		 * se i 2 elementi sono uguali, esegue il return di first, quindi del valore che ha 
		 * trovato per primo. In questo caso dopo la freccia esegue questo controllo con equals, 
		 * ma io lì posso fare anche altri controlli, ad esempio se il titolo del libro contiene
		 * una determinata parola fai il return del secondo ecc...
		 * 
		 * Per mettere il codice aggiuntivo nella gestione duplicati dovresti fare così:
		 * (first, second) -> {
		 * 						 code...
		 * 						 return first
		 * 					  }*/
	}
	
	/* Questo metodo raggruppa i libri in base all'ISBN, ma solo quelli con il valore maggiore
	 * rispetto all'ISBN fornito come argomento. */
	@Override
	public Map<String, List<Book>> listToMapIsbnGreaterThen(List<Book> books, String isbn) {
		return books.stream()                      // key
			        .filter(book -> book.getIsbn().compareTo(isbn) > 0)
				    .collect(Collectors.groupingBy(Book::getIsbn));
	}

	/* Questo metodo aggiunge i libri con il prezzo più alto rispetto a quello indicato
	 * in una lista all'interno della mappa sotto la chiave true, mentre quelli con prezzo
	 * più basso sotto la chiave false. */
	@Override
	public Map<Boolean, List<Book>> listToMapPriceGreaterThen(List<Book> books, int price) {
		return books.stream()                      // key
		        .collect(Collectors.partitioningBy(book -> book.getPrice() > price));
		
		/* In pratica il metodo partitioningBy separa in 2 parti gli oggetti sottoposti
		 * al controllo. Quelli che soddisfano il requisito vanno (all'interno della mappa)
		 * nella lista sotto la chiave true, gli altri nella lista sotto la chiave false. */
	}

	// Questo metodo restituisce una stringa con tutti i nomi (ISBN) dei libri.
	@Override
	public String bookNamesJoined(List<Book> books) {
		return books.stream()
				.map(Book::getIsbn)
				.sorted()
				.collect(Collectors.joining(", ", "", "."));
		
		/* .map crea un nuovo stream con i risultati del metodo applicato ad ognuno
		 * degli elementi presenti nello stream precedente.
		 * In questo caso quindi per ogni book prendo l'ISBN e quindi map crea uno
		 * stream con tutti i codici.
		 * Fare Book::getIsbn significa fare book -> book.getIsbn() */
	}

	// Restituisce la media del prezzo dei libri
	@Override
	public double averageBookPrize(List<Book> books) {
		return books.stream()
				.collect(Collectors.averagingDouble(Book::getPrice));
	}

	// Restituisce la somma totale del costo dei libri.
	@Override
	public int totalCost(List<Book> books) {
		return books.stream()
				.collect(Collectors.summingInt(Book::getPrice));
	}

	// Ci riporta delle statistiche, tipo valori max, min, medi...
	@Override
	public IntSummaryStatistics booksStatistics(List<Book> books) {
		return books.stream()
				.collect(Collectors.summarizingInt(Book::getPrice));
	}

	// Restituisce tutti gli autori dei libri
	@Override
	public String[] booksAuthors(List<Book> books) {
		return books.stream()
				.map(Book::getAuthor)
				.collect(Collectors.toList()) // Restituisce una List<String>
				.toArray(new String[0]);  // Restituisce String[] partendo da List
	}

	// Restituisce tutti gli autori dei libri filtrati per nazione
	@Override
	public String[] booksAuthors(List<Book> books, String nation) {
		return books.stream()
				.filter(book -> book.getNation().equals(nation))
				.map(Book::getAuthor)
				.collect(Collectors.toList()) // Restituisce una List<String>
				.toArray(new String[0]);  // Restituisce String[] partendo da List
	}

}
