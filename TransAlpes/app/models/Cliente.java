package models;
import com.avaje.ebean.Model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Cliente extends Model{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    public String name;

    public int telefono;

    public String tarjetaBancaria;

    @OneToMany(cascade = CascadeType.ALL)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="cliente_id")
    public List<Reserva> reservasMobibus;

    @OneToMany(cascade = CascadeType.ALL)
    @OnDelete(action= OnDeleteAction.CASCADE)
    public List<Prestamo> prestamosVcubs;

    public static Model.Finder<Long,Cliente> find = new Model.Finder<Long, Cliente>(Cliente.class);

    public final void agregarReserva(Reserva reserva)
    {
        if(reservasMobibus==null)
           reservasMobibus = new ArrayList<>();
        reservasMobibus.add(reserva);
    }

    public final void agregarPrestamo(Prestamo prestamo)
    {
        if(prestamosVcubs==null)
            prestamosVcubs = new ArrayList<>();
        prestamosVcubs.add(prestamo);
    }

    public final void eliminarReserva(Long idReserva)
    {
        for(int i=0;i<reservasMobibus.size();i++)
        {
            if(reservasMobibus.get(i).id==idReserva)
                reservasMobibus.get(i).delete();
        }
    }

    public final void actualizarReserva(Reserva reserva, Long idReserva)
    {
        for(int i=0;i<reservasMobibus.size();i++)
        {
            if(reservasMobibus.get(i).id==idReserva) {
                reservasMobibus.get(i).delete();
                reservasMobibus.add(i,reserva);
            }
        }
    }

    public final void actualizarPrestamo(Prestamo prestamo)
    {
        for(int i=0;i<prestamosVcubs.size();i++)
        {
            if(prestamosVcubs.get(i).id==prestamo.id) {
                    prestamosVcubs.set(i,prestamo);
            }
        }
    }

}
