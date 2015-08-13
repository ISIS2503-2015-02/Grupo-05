package models;




import java.util.List;
import javax.persistence.*;
/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
public class SistemaPrincipal {


    public String nombre;

    public List<Informe> informeList;
}
