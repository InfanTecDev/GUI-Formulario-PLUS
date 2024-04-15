import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import MiLibreria.PostLogin;

public class Registro extends JFrame implements PostLogin{

    private final JTextField nombreT, edadT, pesoT, alturaT;
    private final JLabel nombreL, edadL, pesoL, alturaL, IMCL, IMCv, contadorL;
    private final JButton buscar, vaciar, eliminar, guardar, ant, sig, calcular, cargar;
    private ArrayList<Persona> listado;
    private final ArrayList<JTextField> jtextFields;
    private int index;
    private final DecimalFormat numForm;

    public Registro() {
        super("Registro de personas");
        listado = new ArrayList<>();
        jtextFields = new ArrayList<>();
        index = 0;
        numForm = new DecimalFormat("#.####");

        //Instancias de los JTextField
        nombreT = new JTextField("Ingrese su nombre aquí");
        edadT = new JTextField("Ingrese su edad");
        pesoT = new JTextField("Ingrese su peso");
        alturaT = new JTextField("Ingrese su altura");

        //Instancia de JLabels
        nombreL = new JLabel("Nombre: ");
        edadL = new JLabel("Edad: ");
        pesoL = new JLabel("Peso(Kg): ");
        alturaL = new JLabel("Altura(m): ");
        IMCL = new JLabel("IMC: ");
        IMCv = new JLabel("0");
        contadorL = new JLabel("Persona 0 de 0");

        //Instancia de JButton
        buscar = new JButton("Buscar");
        vaciar = new JButton("Vaciar");
        eliminar = new JButton("Eliminar");
        guardar = new JButton("Guardar");
        ant = new JButton("<");
        sig = new JButton(">");
        calcular = new JButton("Calcular");
        cargar = new JButton("Cargar archivo");
    }

    //Método para configurar los componentes para la ventana
    public void initComponents() {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setLayout(null);

        contadorL.setBounds(375, 25, 200, 35);
        contadorL.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 25));
        this.add(contadorL);

        nombreL.setBounds(100, 110, 150, 35);
        nombreL.setFont(new Font("Serig", Font.BOLD + Font.ITALIC, 25));
        this.add(nombreL);

        edadL.setBounds(130, 185, 150, 35);
        edadL.setFont(new Font("Serig", Font.BOLD + Font.ITALIC, 25));
        this.add(edadL);

        pesoL.setBounds(90, 260, 150, 35);
        pesoL.setFont(new Font("Serig", Font.BOLD + Font.ITALIC, 25));
        this.add(pesoL);

        alturaL.setBounds(90, 335, 150, 35);
        alturaL.setFont(new Font("Serig", Font.BOLD + Font.ITALIC, 25));
        this.add(alturaL);

        IMCL.setBounds(150, 410, 150, 35);
        IMCL.setFont(new Font("Serig", Font.BOLD + Font.ITALIC, 25));
        this.add(IMCL);

        nombreT.setBounds(220, 110, 200, 35);
        nombreT.setFont(new Font("Serif", Font.ITALIC, 18));
        this.add(nombreT);

        edadT.setBounds(220, 185, 200, 35);
        edadT.setFont(new Font("Serif", Font.ITALIC, 18));
        this.add(edadT);

        pesoT.setBounds(220, 260, 200, 35);
        pesoT.setFont(new Font("Serif", Font.ITALIC, 18));
        this.add(pesoT);

        alturaT.setBounds(220, 335, 200, 35);
        alturaT.setFont(new Font("Serif", Font.ITALIC, 18));
        this.add(alturaT);

        IMCv.setBounds(220, 410, 100, 35);
        IMCv.setFont(new Font("Serig", Font.BOLD + Font.ITALIC, 25));
        this.add(IMCv);

        sig.setBounds(475, 130, 60, 60);
        ant.setBounds(475, 300, 60, 60);
        vaciar.setBounds(100, 470, 80, 60);
        buscar.setBounds(200, 470, 80, 60);
        buscar.setFont(new Font("Serif", Font.BOLD, 12));
        eliminar.setBounds(300, 470, 80, 60);
        guardar.setBounds(400, 470, 80, 60);
        calcular.setBounds(460, 390, 90, 60);
        cargar.setBounds(20, 15, 150, 30);

        sig.addActionListener(new MyHandler());
        ant.addActionListener(new MyHandler());
        vaciar.addActionListener(new MyHandler());
        buscar.addActionListener(new MyHandler());
        eliminar.addActionListener(new MyHandler());
        guardar.addActionListener(new MyHandler());
        calcular.addActionListener(new MyHandler());
        cargar.addActionListener(new MyHandler());

        check();
        jtextFields.add(nombreT);
        jtextFields.add(edadT);
        jtextFields.add(pesoT);
        jtextFields.add(alturaT);

        this.add(sig);
        this.add(ant);
        this.add(vaciar);
        this.add(eliminar);
        this.add(buscar);
        this.add(guardar);
        this.add(calcular);
        this.add(cargar);
        checkNandP();
        this.setVisible(true);
    }
    
    private void reiniciarCampos(){
        nombreT.setText("Ingrese su nombre aquí");
        edadT .setText("Ingrese su edad");
        pesoT.setText("Ingrese su peso");
        alturaT.setText("Ingrese su altura");
        contadorL.setText("Persona 0 de 0");
        IMCv.setText("0");
    }

    private void check() {
        if (listado.isEmpty()) {
            eliminar.setEnabled(false);
            buscar.setEnabled(false);
        } else {
            eliminar.setEnabled(true);
            buscar.setEnabled(true);
        }
    }

    private boolean JTextIsEmpty() {
        boolean centinela = false;
        for (JTextField j : jtextFields) {
            centinela = (j.getText().equals("") || j.getText().contains("Ingrese")) || IMCv.getText().equals("0");
        }
        return centinela;
    }

    private void refrescarDatos(int i) {
        int cont = i + 1;
        nombreT.setText(listado.get(i).getNombre());
        edadT.setText(String.valueOf(listado.get(i).getEdad()));
        pesoT.setText(String.valueOf(listado.get(i).getPeso()));
        alturaT.setText(String.valueOf(listado.get(i).getAltura()));
        IMCv.setText(String.valueOf(listado.get(i).getIMC()));
        contadorL.setText("Persona " + cont + " de " + listado.size());
    }

    private void checkNandP() {
        if (listado.size() <= 1) {
            sig.setEnabled(false);
            ant.setEnabled(false);
        } else {
            sig.setEnabled(true);
            ant.setEnabled(true);
        }
    }

    private boolean in(String persona) {
        boolean centinela = false;
        for (Persona p : listado) {
            centinela = p.getNombre().contains(persona);
            if (centinela == true) {
                return centinela;
            }
        }
        return centinela;
    }

    private int indice(String name) {
        for (Persona p : listado) {
            if (p.getNombre().contains(name)) {
                return listado.indexOf(p);
            }
        }
        return -1;
    }

    @Override
    public void ejecutar() {
        initComponents();
    }

    private class MyHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int contador = 0;
            if (ae.getSource() == ant) {
                index = (index == 0) ? listado.size() - 1 : --index;
                contador = index + 1;
                contadorL.setText("Persona " + contador + " de " + listado.size());
                refrescarDatos(index);
            } else if (ae.getSource() == sig) {
                index = (index == listado.size() - 1) ? 0 : ++index;
                contador = index + 1;
                contadorL.setText("Persona " + contador + " de " + listado.size());
                refrescarDatos(index);
            } else if (ae.getSource() == vaciar) {
                if (!listado.isEmpty()) {
                    boolean confirmar = new EscritorLectorArchivo().escribirArchivo(listado, ".pdf");
                    String respuesta = (confirmar) ? "Personas vaciadas al archivo" : "Error al vaciar al archivo";
                    JOptionPane.showMessageDialog(null, respuesta);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay datos que vaciar");
                }
            } else if (ae.getSource() == buscar) {
                String name = JOptionPane.showInputDialog("Ingrese el nombre de la persona a buscar");
                if (in(name)) {
                    int i = indice(name);
                    refrescarDatos(i);
                } else {
                    JOptionPane.showMessageDialog(null, "Persona no encontrada");
                }
            } else if (ae.getSource() == eliminar) {
                listado.remove(index);
                index = (index == 0) ? listado.size() - 1 : --index;
                if(listado.isEmpty()){
                    reiniciarCampos();
                } else {
                    refrescarDatos(index);
                }
                check();
                checkNandP();
                JOptionPane.showMessageDialog(null, "Persona eliminada");
            } else if (ae.getSource() == guardar) {
                if (!JTextIsEmpty()) {
                    int edad;
                    double peso, altura, IMC;
                    try {
                        edad = Integer.parseInt(edadT.getText());
                        peso = Double.parseDouble((pesoT.getText()));
                        altura = Double.parseDouble(alturaT.getText());
                        IMC = Double.parseDouble(IMCv.getText());
                        listado.add(new Persona(nombreT.getText(), edad, peso, altura, IMC));
                        contadorL.setText("Persona " + ++contador + " de " + listado.size());
                        checkNandP();
                        JOptionPane.showMessageDialog(null, "Persona agregada!!");
                        index = listado.size() - 1;
                        refrescarDatos(index);
                        check();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Número no válido");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Campos incompletos o incorrectos");
                }
            } else if (ae.getSource() == calcular) {
                int p;
                double a;
                try {
                    p = Integer.parseInt(pesoT.getText());
                    a = Double.parseDouble(alturaT.getText());
                    IMCv.setText(String.valueOf(Double.parseDouble(numForm.format(p / Math.pow(a, 2)))));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Peso y/o altura incorrectos");
                }
            } else if (ae.getSource() == cargar) {
                String nombreArch = JOptionPane.showInputDialog("Ingrese el nombre del archivo (.txt) ");
                if (nombreArch != null && !nombreArch.equals("")) {
                    ArrayList<Persona> temp = new EscritorLectorArchivo().cargarDatos(nombreArch);
                    temp = (temp == null) ? new ArrayList<Persona>() : temp;
                    if (!listado.isEmpty()) {
                        for (Persona p : temp) {
                            listado.add(p);
                        }
                    } else {
                        listado = temp;
                    }
                    if(!temp.isEmpty()){
                        index = 0;
                        contador = index + 1;
                        contadorL.setText("Persona " + contador + " de " + listado.size());
                        refrescarDatos(index);
                        checkNandP();
                        check();
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se cargó ningún archivo");
                }
            }
        }
    }
}
