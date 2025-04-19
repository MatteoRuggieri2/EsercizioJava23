package esercizi;

import java.util.Objects;

public class Book {
	String isbn;
	String desc;
	String author;
	String nazione;
	int price;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		return Objects.hash(author, desc, isbn, nazione, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(desc, other.desc)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(nazione, other.nazione) && price == other.price;
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", desc=" + desc + ", author=" + author + ", nazione=" + nazione + ", price="
				+ price + "]";
	}
	
	
}
