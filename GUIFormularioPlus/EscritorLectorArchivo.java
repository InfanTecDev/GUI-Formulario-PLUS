import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class EscritorLectorArchivo {

    private FileWriter escritor;
    private final String nombre;
    private String linea;
    private int num;

    public EscritorLectorArchivo() {
        nombre = "Personas ";
        num = 1;

    }

    public boolean escribirArchivo(ArrayList<Persona> listado, String tipoArchivo) {
        String name = asignarNombre(tipoArchivo), RUTA_ARCHIVO = "Registros/" + name + tipoArchivo;
        try {
            if (tipoArchivo.equals(".txt")) {
                File archivo = new File(RUTA_ARCHIVO);
                archivo.createNewFile();
                escritor = new FileWriter(RUTA_ARCHIVO);
                for (Persona p : listado) {
                    linea = p.toString() + "\n";
                    escritor.write(linea);
                }
                escritor.close();
                return true;
            } else if (tipoArchivo.equals(".pdf")) {
                FileOutputStream archivo = null;
                try {
                    archivo = new FileOutputStream(RUTA_ARCHIVO);
                } catch (FileNotFoundException ex) {
                    System.out.println("No encontrado");
                    return false;
                }
                Document documento = new Document();
                PdfWriter.getInstance(documento, archivo);
                documento.open();
                for (Persona p : listado) {
                    linea = p.toString() + "\n";
                    documento.add(new Paragraph(linea));
                }
                documento.close();
                return true;
            }

        } catch (IOException ex) {
            System.err.println("Archivo no encontrado");
            return false;
        } catch (DocumentException ex) {
            System.out.println("No encontrado");
            return false;
        }
        return false;
    }

    public ArrayList<Persona> cargarDatos(String nomA) {
        ArrayList<Persona> lista = new ArrayList<>();
        String[] x;
        String RUTA_ARCHIVO = "Registros/" + nomA + ".txt";
        try {
            Scanner s = new Scanner(new File(RUTA_ARCHIVO));
            while (s.hasNext()) {
                x = s.nextLine().split(", ");
                lista.add(new Persona(x[0], Integer.parseInt(x[1]), Double.parseDouble(x[2]), Double.parseDouble(x[3]), Double.parseDouble(x[4])));
            }
            return lista;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado");
        }
        return null;
    }

    private String asignarNombre(String tipo) {
        String nombreArchivo = nombre + num;
        while (new File("Registros/" + nombreArchivo + tipo).exists()) {
            nombreArchivo = nombre + ++num;
        }
        return nombreArchivo;
    }
}
