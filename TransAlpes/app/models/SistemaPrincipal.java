package models;

import java.util.List;
/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
public class SistemaPrincipal {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public String nombre;

    public List<Informe> informeList;
}
