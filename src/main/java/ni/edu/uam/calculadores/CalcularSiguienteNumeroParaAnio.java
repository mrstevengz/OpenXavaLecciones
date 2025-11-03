package ni.edu.uam.calculadores;

import lombok.Getter;
import lombok.Setter;
import org.openxava.calculators.*;
import org.openxava.jpa.XPersistence;

import javax.persistence.*;

public class CalcularSiguienteNumeroParaAnio implements ICalculator {
    @Getter @Setter
    int year;

    public Object calculate ()throws Exception {
        Query query = (Query) XPersistence.getManager().createQuery("select max (f.numero) from Factura f where f.year = :year");
        query.setParameter("year", year);
        Integer ultimoNumero = (Integer) query.getSingleResult();
        return ultimoNumero == null ? 1 : ultimoNumero + 1;
    }
}
