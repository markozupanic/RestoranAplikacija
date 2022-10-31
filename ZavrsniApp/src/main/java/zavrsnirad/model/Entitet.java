package zavrsnirad.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
@MappedSuperclass
public abstract class Entitet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer sifra;
	

	public Entitet(Integer sifra) {
		super();
		this.sifra = sifra;
	}

	public Entitet() {
		super();
	}

	public Integer getSifra() {
		return sifra;
	}

	
	
	
}
