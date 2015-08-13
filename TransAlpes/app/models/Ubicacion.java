package models;

/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
public class Ubicacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public int latitud;

    public int longitud;

    public long hora;

    public String estado;

    public int kilometraje;
}
