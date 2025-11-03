package ni.edu.uam.OpenXava.modelo;

import lombok.Getter;
import lombok.Setter;
import ni.edu.uam.calculadores.CalcularSiguienteNumeroParaAnio;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;
import org.openxava.calculators.CurrentYearCalculator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity @Getter
@Setter

@View(members =
        "anyo, numero, fecha;" + "cliente;" + "detalles;" + "observaciones")

public class Factura {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Hidden
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    String oid;

    @Column(length = 4)
    @DefaultValueCalculator(CurrentYearCalculator.class)
    int year;

    @Column(length = 6)
    @DefaultValueCalculator(value = CalcularSiguienteNumeroParaAnio.class, properties =@PropertyValue(name = "anio"))
    int numero;

    @Required
    @DefaultValueCalculator(CurrentYearCalculator.class)
    LocalDate fecha;

    @TextArea
    String observaciones;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @ReferenceView("Simple")
    Cliente cliente;

    @ElementCollection
    @ListProperties("producto.numero, producto.descripcion, cantidad")
    Collection<Detalle> detalles;

}
