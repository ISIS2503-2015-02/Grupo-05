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
    @JoinColumn(name="reservas")
    public List<Reserva> reservasMobibus;

    @OneToMany(cascade = CascadeType.ALL)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name="prestamos")
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
}