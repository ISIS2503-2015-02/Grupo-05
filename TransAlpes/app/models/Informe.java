package models;

/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
public class Informe {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public int numeroGravedad;

    public String tipo;

    public String contenido;
}
